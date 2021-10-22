document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("submit-req-btn").addEventListener("click", submit_request);
document.getElementById("pending-req-btn").addEventListener("click", get_pending_requests);
document.getElementById("resolved-req-btn").addEventListener("click", get_resolved_requests);
document.getElementById("manager-page-btn").addEventListener("click", forward_to_manager);


function logout(){
    sessionStorage.removeItem("token");
    window.location.href = "index.html";
}

function forward_to_manager(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("login",{method: "GET", headers: {"Authorization": sessionStorage.getItem("token"),"managerCHeck":"true"}})
        .then(response => {
            if(response.ok){
                window.location.href = "manager.html";
                return response.json();
            }
            throw new Error("failed to authenticate token");
            })
        .catch(error => console.log(error.message));
    } else {
        window.location.href = "index.html";
    }
}

function get_resolved_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "resolved"}})
        .then(response => response.json())
        .then(data => updateResolvedTable(data));
    } else {
        window.location.href = "index.html";
    }
}

function get_pending_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "pending"}})
        .then(response => response.json())
        .then(data => updatePendingTable(data));
    } else {
        window.location.href = "index.html";
    }
}

function setHidden(value){
    document.getElementByIdById("manager-id").hidden = value;
    document.getElementByIdById("decision-date").hidden = value;
}

function showDiv(){
    document.getElementById("amount").value = "";
    document.getElementById("categories").selectedIndex = 0;

    const messageDiv = document.getElementById("message-div");
    messageDiv.hidden = false;
    messageDiv.innerText = "Request submitted";
}

function submit_request(){
    const token = sessionStorage.getItem("token");
    const amount = document.getElementById("amount").value;
    const category = document.getElementById("categories").value;

    if(token){
        fetch("reimbursment", {method: "POST", headers: {"Authorization" : sessionStorage.getItem("token"),"Amount" : amount,"Category" : category}})
        .then(response => response.json())
        .then(showDiv());

    } else {
        window.location.href = "index.html";
    }

}

function displayProfile(){
    const token = sessionStorage.getItem("token");
    if("token"){
        fetch("employees", {method: "GET", headers: {"Authorization" : token}})
        .then(response => response.json())
        .then(data => displayData(data));
    } else {
        window.location.href = "index.html";
    }
}

function displayData(employee){
    document.getElementById("employee-id").innerText = employee.employeeID;
    document.getElementById("employee-name").innerText = `${employee.fname} ${employee.lname}`;
    document.getElementById("personal-email").innerText = employee.pemail;
    document.getElementById("work-email").innerText = employee.wemail;
}

function updateResolvedTable(data){
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    while(tableHead.hasChildNodes()){
        tableHead.removeChild(tableHead.firstChild);
    }
    while(tableBody.hasChildNodes()){
        tableBody.removeChild(tableBody.firstChild);
    }
    let tableRow = document.createElement("tr");
    tableRow.innerHTML = `<th>Request ID</th>
                          <th>Status</th>
                          <th>Category</th>
                          <th>Amount</th>
                          <th>Request Date</th>
                          <th>Decision Date</th>
                          <th>Manager</th>`
                          tableHead.appendChild(tableRow);
    for(item of data){
        console.log(item);
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td>${item.status}</td>
        <td>${item.category}</td>
        <td style="text-align:right">\$${item.amount.toFixed(2)}</td>
        <td>${item.reqDate}</td>
        <td>${item.decDate}</td>
        <td>${item.decManagerID}</td>`
        tableBody.appendChild(tableRow);
    }
}

function updatePendingTable(data){
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    while(tableHead.hasChildNodes()){
        tableHead.removeChild(tableHead.firstChild);
    }
    while(tableBody.hasChildNodes()){
        tableBody.removeChild(tableBody.firstChild);
    }
    let tableRow = document.createElement("tr");
    tableRow.innerHTML = `<th>Request ID</th>
                          <th>Request Date</th>
                          <th>Category</th>
                          <th>Amount</th>`
                          tableHead.appendChild(tableRow);
    for(item of data){
        console.log(item);
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td>${item.reqDate}</td>
        <td>${item.category}</td>
        <td style="text-align:right">\$${item.amount.toFixed(2)}</td>`
        tableBody.appendChild(tableRow);
    }
}
