document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("pending-req-btn").addEventListener("click", get_pending_requests);
document.getElementById("resolved-req-btn").addEventListener("click", get_resolved_requests);
document.getElementById("resolve").addEventListener("click", resolve_requests);
document.getElementById("emp-btn").addEventListener("click", get_employees);
document.getElementById("employee-home-btn").addEventListener("click", forward_to_home);
document.getElementById("new-employee").addEventListener("click", create_employee);



function logout(){
    sessionStorage.removeItem("token");
    window.location.href = "index.html";
}

function create_employee(){
    let sub_emp = document.getElementById("submit-employee");
    sub_emp.addEventListener("click", submit_employee);
    sub_emp.hidden = false;
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    document.getElementById("new-employee").hidden = true;

    clearTable();
    let tableRow = document.createElement("tr");
    tableRow.innerHTML = `<th>First Name</th>
                          <th>Middle Name</th>
                          <th>Last Name</th>
                          <th>Personal Email</th>`
    tableHead.appendChild(tableRow);

    tableRow = document.createElement("tr");
    tableRow.innerHTML = `<td><input type = "text" name = "fname" id = "fname"></td>
                          <td><input type = "text" name = "mname" id = "mname"></td>
                          <td><input type = "text" name = "lname" id = "lname"></td>
                          <td><input type = "email" name = "pemail" id = "pemail"></td>`
    tableBody.appendChild(tableRow);

}

function submit_employee(){
    const token = sessionStorage.getItem("token");
    document.getElementById("submit-employee").hidden = true;
    let fname = document.getElementById("fname").value;
    let mname = document.getElementById("mname").value;
    let lname = document.getElementById("lname").value;
    let pemail = document.getElementById("pemail").value;
    document.getElementById("fname").value = "";
    document.getElementById("mname").value = "";
    document.getElementById("lname").value = "";
    document.getElementById("pemail").value = "";
    if("token"){
        fetch("manager", {method: "POST", headers: {"Authorization" : token, "fname":fname, "mname":mname, "lname":lname, "pemail":pemail}})
        .then(response => response.json());
    } else {
        window.location.href = "index.html";
    }

}

function displayProfile(){
    const token = sessionStorage.getItem("token");
    document.getElementById("new-employee").hidden = true;
    if("token"){
        fetch("employees", {method: "GET", headers: {"Authorization" : token}})
        .then(response => response.json())
        .then(data => displayData(data));
    } else {
        window.location.href = "index.html";
    }
}

function displayData(employee){
    document.getElementById("new-employee").hidden = true;
    document.getElementById("employee-id").innerText = employee.employeeID;
    document.getElementById("employee-name").innerText = `${employee.fname} ${employee.lname}`;
    document.getElementById("personal-email").innerText = employee.pemail;
    document.getElementById("work-email").innerText = employee.wemail;
}

function get_resolved_requests(){
    const token = sessionStorage.getItem("token");
    document.getElementById("new-employee").hidden = true;
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),status : "resolved","managerReq" : "true"}})
        .then(response => response.json())
        .then(data => updateResolvedTable(data));
    } else {
        window.location.href = "index.html";
    }
}

function get_pending_requests(){
    const token = sessionStorage.getItem("token");
    document.getElementById("new-employee").hidden = true;
    if(token){
        fetch("reimbursment", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token"),status : "pending","managerReq" : "true"}})
        .then(response => response.json())
        .then(data => updatePendingTable(data));
    } else {
        window.location.href = "index.html";
    }
}

function get_employees(){
    const token = sessionStorage.getItem("token");
    document.getElementById("new-employee").hidden = false;
    if(token){
        fetch("manager", {method: "GET", headers: {"Authorization" : sessionStorage.getItem("token")}})
        .then(response => response.json())
        .then(data => updateEmployeesTable(data));
    } else {
        window.location.href = "index.html";
    }
}

function clearTable(){
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    while(tableHead.hasChildNodes()){
        tableHead.removeChild(tableHead.firstChild);
    }
    while(tableBody.hasChildNodes()){
        tableBody.removeChild(tableBody.firstChild);
    }
}

function updateEmployeesTable(data){
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
    clearTable();
    document.getElementById("resolve").hidden = true;
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
    clearTable();
    let tableHead = document.getElementById("display-table-head");
    let tableBody = document.getElementById("display-table-body");
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
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td style="text-align:right">${item.requestID}</td>
        <td style="text-align:right">${item.reqEmployeeID}</td>
        <td>${item.reqDate}</td>
        <td>${item.category}</td>
        <td style="text-align:right">\$${item.amount.toFixed(2)}</td>
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

function forward_to_home(){
    const token = sessionStorage.getItem("token");
    if(token){
        fetch("login",{method: "GET", headers: {"Authorization": sessionStorage.getItem("token")}})
        .then(response => {
            if(response.ok){
                window.location.href = "home.html";
                return response.json();
            }
            throw new Error("failed to authenticate token");
            return Promise.reject(response);
            })
        .catch(error => console.log(error.message));
    } else {
        window.location.href = "index.html";
    }
}


