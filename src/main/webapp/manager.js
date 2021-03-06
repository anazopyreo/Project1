document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("pending-req-btn").addEventListener("click", get_pending_requests);
document.getElementById("resolved-req-btn").addEventListener("click", get_resolved_requests);
document.getElementById("resolve").addEventListener("click", resolve_requests);
document.getElementById("emp-btn").addEventListener("click", get_employees);

function logout(){
    sessionStorage.removeItem("token");
    window.location.href = "login.html";
}

function get_resolved_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),status : "resolved","managerReq" : "true"}})
        .then(response => response.json())
        .then(data => updateResolvedTable(data));
    }
}

function get_pending_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),status : "pending","managerReq" : "true"}})
        .then(response => response.json())
        .then(data => updatePendingTable(data));
    }
}

function get_employees(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("manager", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token")}})
        .then(response => response.json())
        .then(data => updateEmployeesTable(data));
    }
}

function updateEmployeesTable(data){
    document.getElementById("resolve").hidden = true;
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    while(tableHead.hasChildNodes()){
        tableHead.removeChild(tableHead.firstChild);
    }
    while(tableBody.hasChildNodes()){
        tableBody.removeChild(tableBody.firstChild);
    }
    let tableRow = document.createElement("tr");
    tableRow.innerHTML = `<th>Employee ID</th>
                          <th>Username</th>
                          <th>First</th>
                          <th>Middle</th>
                          <th>Last</th>
                          <th>Personal Email</th>
                          <th>Company Email</th>`
                          tableHead.appendChild(tableRow);
    let rows = data.length;
    document.getElementById("rowCount").innerText = rows;
    for(i=0;i<rows;i++){
        item = data[i];
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.employeeID}</td>
        <td>${item.uname}</td>
        <td>${item.fname}</td>
        <td>${item.mname}</td>
        <td>${item.lname}</td>
        <td>${item.pemail}</td>
        <td>${item.wemail}</td>`
        tableBody.appendChild(tableRow);
    }
                      }


function updateResolvedTable(data){
    document.getElementById("resolve").hidden = true;
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
                          <th>Employee ID</th>
                          <th>status</th>
                          <th>Category</th>
                          <th>Amount</th>
                          <th>Request Date</th>
                          <th>Decision Date</th>
                          <th>Manager</th>`
                          tableHead.appendChild(tableRow);
    let rows = data.length;
    document.getElementById("rowCount").innerText = rows;
    for(i=0;i<rows;i++){
        item = data[i];
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td style="text-align:right">${item.reqEmployeeID}</td>
        <td>${item.status}</td>
        <td>${item.category}</td>
        <td style="text-align:right">\$${item.amount}</td>
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
                          <th>Employee ID</th>
                          <th>Request Date</th>
                          <th>Category</th>
                          <th>Amount</th>
                          <th>Approve</th>
                          <th>Deny</th>`
                          tableHead.appendChild(tableRow);
    let rows = data.length;
    document.getElementById("rowCount").innerText = rows;
    for(i=0;i<rows;i++){
        item = data[i];
    // for (item of data){
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td style="text-align:right">${item.reqEmployeeID}</td>
        <td>${item.reqDate}</td>
        <td>${item.category}</td>
        <td style="text-align:right">\$${item.amount}</td>
        <td><input type="radio" id="approve${i}" name="row${i}" value="approved"></td>
        <td><input type="radio" id="deny${i}" name="row${i}" value="denied"></td>`
        tableBody.appendChild(tableRow);
    }
    document.getElementById("resolve").hidden = false;
}

async function resolve_requests(){
    let rows = document.getElementById("rowCount").innerText;
    for(i=0;i<rows;i++){
        let rbs = document.querySelectorAll(`input[name="row${i}"]`);
        for (const rb of rbs) {
            if (rb.checked){
                let requestID = document.getElementById("display-table-body").rows[i].cells[0].innerHTML;
                await fetch("managerReimReq", {method: "POST", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : rb.value,"requestID" : requestID}})
                break;
            }
        }
    }
    get_pending_requests();
}


// let displayEmployees = employees => {
//     const managerContent = document.getElementById("manager-content");
//     const employeeList = document.createElement("ul");
//     for(let employee of employees){
//         const employeeListItem = document.createElement("li");
//         employeeListItem.innerText = `${employee.fname} ${employee.lname} (${employee.role[0].toUpperCase() + employee.role.substring(1).toLowerCase()})`;
//         employeeList.appendChild(employeeListItem);
//     }
//     managerContent.appendChild(employeeList);

// }

// if(token){
//     fetch("employees", {headers: {"Authorization" : sessionStorage.getItem("token")}})
// //    fetch("http://localhost:8082/Project1/employees", {headers: {"Authorization" : sessionStorage.getItem("token")}})
//     .then(response => response.json())
//     .then(displayEmployees);
// } else {
//     window.location.href = "login.html";
// //    window.location.href = "http://localhost:8082/Project1/static/login.html";
// }

