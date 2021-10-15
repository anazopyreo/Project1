document.getElementById("login-btn").addEventListener("click", attemptLogin);


function attemptLogin(){
    const errorDiv = document.getElementById("error-div");
    errorDiv.hidden = true;

    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;
    const manager = document.getElementById("manager-login").checked;
    console.log(`manager = ${manager}`);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8082/Project1/login");

    xhr.onreadystatechange = function(){
        if(xhr.readyState===4){
            if(xhr.status===401){
                errorDiv.hidden = false;
                errorDiv.innerText = "invalid login credentials";
            } else if(xhr.status===200){
                const token = xhr.getResponseHeader("Authorization");
                sessionStorage.setItem("token",token);
                if(manager===true){
                    window.location.href="http://localhost:8082/Project1/static/manager.html";
                } else {
                window.location.href="http://localhost:8082/Project1/static/home.html";
                }
            } else{
                errorDiv.hidden = false;
                errorDiv.innerText = "unknown error";
            }
        }
    }
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    const requestBody = `username=${username}&password=${password}`;
    xhr.send(requestBody);

}