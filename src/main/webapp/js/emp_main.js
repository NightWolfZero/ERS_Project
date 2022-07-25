console.log(sessionStorage.getItem("currentUser"));
let currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
document.getElementById("showEmpInfo").onclick = function () {showAccountInfo()}

/*if (currentUser == null){
    alert("Please Login First");
    location.href = "/webapp/signin.html";
}*/
console.log(currentUser);

let nav  = document.getElementsByClassName("navbar-nav");


function logout(){
    sessionStorage.clear();
    location.href = "/webapp/signin.html";
}

let formUpdateAccount = document.getElementById("updateAccount").addEventListener('submit', update);

async function update(e){

    e.preventDefault();

    let user_id = currentUser.id;
    let username = currentUser.username;
    let password = document.getElementById("password").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let userRole = currentUser.userRole;

    let user = {
        user_id,
        username,
        password,
        firstname,
        lastname,
        email,
        userRole
    }

    try{
        let req = await fetch('http://localhost:8080/api/updateuser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        currentUser = res;
        console.log(currentUser);
        sessionStorage.setItem("currentUser", JSON.stringify(currentUser));
        currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
        console.log(res)
        alert( `An Employee Account with username ${username} was Updated
		 `);
        showAccountInfo();
    } catch(e){
        alert('Username or Password was incorrect');
        return;
    }

}
function showAccountInfo() {
    document.getElementById("username").value = currentUser.username;
    document.getElementById("password").value = currentUser.password;
    document.getElementById("firstname").value = currentUser.firstName;
    document.getElementById("lastname").value = currentUser.lastName;
   document.getElementById("email").value = currentUser.email;
}