document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("submit-req-btn").addEventListener("click", submit_request);

function logout(){
sessionStorage.removeItem("token");
window.location.href = "http://localhost:8082/Project1/static/login.html";
}

function showDiv(){
    document.getElementById("request-form").reset();
    console.log("Got to showDiv")
    const messageDiv = document.getElementById("message-div");
    messageDiv.hidden = false;
    messageDiv.innerText = "Request submitted";
}

function submit_request(){
console.log("Button clicked");
    const token = sessionStorage.getItem("token");
    const amount = document.getElementById("amount").value;
    const category = document.getElementById("categories").value;

    if(token){
        fetch("http://localhost:8082/Project1/reimbursment", {headers: {"Authorization" : sessionStorage.getItem("token"),"Amount" : amount,"Category" : category}})
//        .then(response => response.json())
        .then(showDiv);
    } else {
        window.location.href = "http://localhost:8082/Project1/static/login.html";
    }

}