document.getElementById("login-btn").addEventListener("click", attemptLogin);


function attemptLogin(){
    const errorDiv = document.getElementById("error-div");
    errorDiv.hidden = true;

    const username = document.getElementById("username-input").value;
    const password = document.getElementById("password-input").value;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "login");

    xhr.onreadystatechange = function(){
        if(xhr.readyState===4){
            if(xhr.status===401){
                errorDiv.hidden = false;
                errorDiv.innerText = "invalid login credentials";
            } else if(xhr.status===200){
                const token = xhr.getResponseHeader("Authorization");
                sessionStorage.setItem("token",token);
                window.location.href="home.html";
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