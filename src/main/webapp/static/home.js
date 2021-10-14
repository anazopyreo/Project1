document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("submit-req-btn").addEventListener("click", submit_request);
document.getElementById("pending-req-btn").addEventListener("click", get_pending_requests);
document.getElementById("resolved-req-btn").addEventListener("click", get_resolved_requests);


function logout(){
sessionStorage.removeItem("token");
window.location.href = "http://localhost:8082/Project1/static/login.html";
}

function get_resolved_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("http://localhost:8082/Project1/reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "resolved"}})
        .then(response => response.json())
        // .then(setHidden(false))
        // .then(document.getElementByIdById("manager-id").hidden = false)
        // .then(document.getElementByIdById("decision-date").hidden = false)
        .then(data => updateTable(data));
    }
}

function get_pending_requests(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("http://localhost:8082/Project1/reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "pending"}})
        .then(response => response.json())
        // .then(setHidden(true))
        // .then(document.getElementByIdById("manager-id").hidden = true)
        // .then(document.getElementByIdById("decision-date").hidden = true)
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
        <td style="text-align:right">\$${item.amount}</td>
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
        fetch("http://localhost:8082/Project1/reimbursment", {method: "POST", headers: {"Authorization" : sessionStorage.getItem("token"),"Amount" : amount,"Category" : category}})
        .then(response => response.json())
        .then(showDiv());
        // fetch("http://localhost:8082/Project1/reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),"status" : "pending"}})
        // .then(response => response.json()).then(data => updateTable(data));

    } else {
        window.location.href = "http://localhost:8082/Project1/static/login.html";
    }

}
