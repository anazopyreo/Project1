document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("submit-req-btn").addEventListener("click", submit_request);
document.getElementById("pending-req-btn").addEventListener("click", get_pending_requests);
document.getElementById("resolved-req-btn").addEventListener("click", get_resolved_requests);
document.getElementById("manager-page-btn").addEventListener("click", forward_to_manager);


function logout(){
    sessionStorage.removeItem("token");
    window.location.href = "index.html";
//    window.location.href = "http://localhost:8082/Project1/static/login.html";
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
            return Promise.reject(response);
            })
        .catch(error => console.log(error.message));
    }
}



function get_resolved_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "resolved"}})
//        fetch("http://localhost:8082/Project1/reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "resolved"}})
        .then(response => response.json())
        .then(data => updateTable(data));
    }
}

function get_pending_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "pending"}})
//        fetch("http://localhost:8082/Project1/reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "pending"}})
        .then(response => response.json())
        .then(data => updateTable(data));
    }
}

function setHidden(value){
    document.getElementByIdById("manager-id").hidden = value;
    document.getElementByIdById("decision-date").hidden = value;
}

function updateTable(data){
    let tableBody = document.getElementById("request-table");
    while(tableBody.hasChildNodes()){
        tableBody.removeChild(tableBody.firstChild);
    }
    for (item of data){
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td style="text-align:right">\$${item.amount.toFixed(2)}</td>
        <td>${item.category}</td>
        <td>${item.status}</td>
        <td>${item.reqDate}</td>
        <td>${item.decDate}</td>
        <td>${item.decManagerID}</td>`
        tableBody.appendChild(tableRow);
    }
}

function showDiv(){
    document.getElementById("request-form").reset();
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
//        fetch("http://localhost:8082/Project1/reimbursment", {method: "POST", headers: {"Authorization" : sessionStorage.getItem("token"),"Amount" : amount,"Category" : category}})
        .then(response => response.json())
        .then(showDiv());

    } else {
        window.location.href = "index.html";
//        window.location.href = "http://localhost:8082/Project1/static/login.html";
    }

}

function displayProfile(){
    const token = sessionStorage.getItem("token");
    if("token"){
        fetch("employees", {method: "GET", headers: {"Authorization" : token}})
//        fetch("http://localhost:8082/Project1/employees", {method: "GET", headers: {"Authorization" : token}})
        .then(response => response.json())
        .then(data => displayData(data));
    } else {
        window.location.href = "login.html";
//        window.location.href = "http://localhost:8082/Project1/static/login.html";
    }
}

function displayData(employee){
    document.getElementById("employee-id").innerText = employee.employeeID;
    document.getElementById("employee-name").innerText = `${employee.fname} ${employee.lname}`;
    document.getElementById("personal-email").innerText = employee.pemail;
    document.getElementById("work-email").innerText = employee.wemail;
}