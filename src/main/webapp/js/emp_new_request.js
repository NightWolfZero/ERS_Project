let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
let managedUser = {};
let newEmpReq = document.getElementById("new_request_bttn").addEventListener("click", newEmpRequest);


/*if (currentUser == null) {
    alert("Please Login First");
    location.href = "/webapp/signin.html";
}*/

let nav = document.getElementsByClassName("navbar-nav");


function logout() {
    sessionStorage.clear();
    location.href = "../signin.html";
}

async function findAccount() {

    let user = {
        username: currentUser.username
    }

    try {
        let req = await fetch('http://localhost:8080/api/finduser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        managedUser = res;
        sessionStorage.setItem("managedUser", JSON.stringify(managedUser));
        managedUser = JSON.parse(sessionStorage.getItem("managedUser"));
        console.log(res)
    } catch (e) {
        alert('Could not Find User');
        return;
    }

}

async function newEmpRequest(e) {

    e.preventDefault();
    await findAccount();

    let description = document.getElementById("description").value;
    let amount = document.getElementById("amount").value;
    let reimbursementType = document.getElementById("reimbursementType").value;

    let user = {
        amount: amount,
        description: description,
        author_id: managedUser.id,
        reimbursementType: reimbursementType
    }
    console.log(user)
    try {
        let req = await fetch('http://localhost:8080/api/saveReimbursement', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res)
        console.log("Request Was Submitted!")
    } catch (e) {
        console.log(e)
        // temp.innerHTML = "UserName or Pass is inCorrect";
        return;
    }

}