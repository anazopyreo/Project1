const token = sessionStorage.getItem("token");

let displayEmployees = employees => {
    document.getElementById("manager-frame").hidden = false;
    const managerContent = document.getElementById("manager-content");
    const employeeList = document.createElement("ul");
    for(let employee of employees){
        const employeeListItem = document.createElement("li");
        employeeListItem.innerText = `${employee.fname} ${employee.lname} (${employee.role[0].toUpperCase() + employee.role.substring(1).toLowerCase()})`;
        employeeList.appendChild(employeeListItem);
    }
    managerContent.appendChild(employeeList);

}

if(token){
    fetch("http://localhost:8082/Project1/employees", {headers: {"Authorization" : sessionStorage.getItem("token")}})
    .then(response => response.json())
    .then(displayEmployees);
} else {
    window.location.href = "http://localhost:8082/Project1/static/login.html";
}

