let currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
let managedUser = {};
let currentUserRequests = {};

let showAccount = document.getElementById("showEmpInfo").addEventListener('click', showAccountInfo);

if (currentUser == null){
    alert("Please Login First");
    location.href = "../signin.html";
}
if (currentUser.userRole !== "MANAGER"){
    alert("Logging you out Please Login Back with a Manager Account!!")
    logout();
}
console.log(currentUser);

function logout(){
    sessionStorage.clear();
    location.href = "../signin.html";
}

let formUpdateAccount = document.getElementById("updateAccount").addEventListener('submit', update);
let formSearchAccount = document.getElementById("searchAccount").addEventListener('submit', findAccount);

async function update(e){

    e.preventDefault();

    let user_id = document.getElementById("user_id").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let userRole = document.getElementById("userRole").value;

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
        managedUser = res;
        console.log(managedUser);
        sessionStorage.setItem("managedUser", JSON.stringify(managedUser));
        managedUser  = JSON.parse(sessionStorage.getItem("managedUser"));
        console.log(res)
        alert( `An Employee Account with username ${username} was Updated
		 `);
        showAccountInfo();
    } catch(e){
        alert('Username or password was incorrect!');
        return;
    }

}

async function findAccount(e){

    e.preventDefault();

    let username = document.getElementById("searchUserByUserName").value;

    let user = {
        username
    }

    try{
        let req = await fetch('http://localhost:8080/api/finduser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        managedUser = res;
        console.log(managedUser);
        sessionStorage.setItem("managedUser", JSON.stringify(managedUser));
        managedUser  = JSON.parse(sessionStorage.getItem("managedUser"));
        console.log(res)
        showAccountInfo();
        await getEmpRequests();
    } catch(e){
        alert('Could not Find User');
        return;
    }

}

async function getEmpRequests() {


    let user = {
        author_id: managedUser.id
    }
    console.log(user)
    try {
        let req = await fetch('http://localhost:8080/api/getReimbursementByUserid', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res)
        let requests = res;
        sessionStorage.setItem("currentUserRequests", JSON.stringify(requests));
        currentUserRequests = JSON.parse(sessionStorage.getItem("currentUserRequests"));
        console.log(currentUserRequests);
        let td = document.getElementById("showAllCurrentManagedReqTable");
        console.log("loggin CurrentuserRequ", currentUserRequests);
        console.log("loggin CurrentuserRequts first row", currentUserRequests[0]);
        for (let row in currentUserRequests) {
            console.log(currentUserRequests[row]);
            td.innerHTML += `<tr><td>${currentUserRequests[row].id}</td>
                     <td>${currentUserRequests[row].amount}</td>
                     <td>${currentUserRequests[row].submitted}</td>
                     <td>${currentUserRequests[row].resolved}</td>
                     <td>${currentUserRequests[row].description}</td>
                     <td>${currentUserRequests[row].resolver_id}</td>
                     <td>${currentUserRequests[row].reimbursementType}</td>
                     <td>${currentUserRequests[row].reimbursementStatus}</td>
                     </tr>`
        }
    } catch (e) {
        console.log(e)
        // temp.innerHTML = "Username or Password is incorrect";
        return;
    }

}


function showAccountInfo() {
    document.getElementById("user_id").value = managedUser.id;
    document.getElementById("username").value = managedUser.username;
    document.getElementById("password").value = managedUser.password;
    document.getElementById("firstname").value = managedUser.firstName;
    document.getElementById("lastname").value = managedUser.lastName;
    document.getElementById("email").value = managedUser.email;
    document.getElementById("userRole").value = managedUser.userRole;