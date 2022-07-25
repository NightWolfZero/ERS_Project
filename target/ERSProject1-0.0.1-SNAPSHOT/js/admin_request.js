let currentUser  = JSON.parse(sessionStorage.getItem("currentUser"));
let requestToUpdate = {};

//let showAccount = document.getElementById("showEmpInfo").addEventListener('click', showAccountInfo);

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

let formUpdateAccount = document.getElementById("updateRequest").addEventListener('submit', update);
let formSearchAccount = document.getElementById("searchRequest").addEventListener('submit', findRequest);
let allReq = document.getElementById("showAllCurrentRequests").addEventListener('click', getAllRequests);

async function update(e){

    e.preventDefault();
    await findAccount();
    console.log(currentUser)
    console.log("Here is Managed User"+ managedUser)

    let request_id = document.getElementById("requestid").value;
    let amount = document.getElementById("requestAmount").value;
    let submitted = document.getElementById("submittedDate").value;
    let resolved = new Date().toJSON().slice(0, 10);
    let description = document.getElementById("description").value;
    let author_id = document.getElementById("requestBy").value;
    let resolver_id = managedUser.id;
    let reimbursementType = document.getElementById("reimbursementType").value;
    let reimbursementStatus = document.getElementById("reimbursementStatus").value;

    let request = {
        request_id,
        amount,
        submitted,
        resolved,
        description,
        author_id,
        resolver_id,
        reimbursementType,
        reimbursementStatus
    }

    try{
        let req = await fetch('http://localhost:8080/api/updateReimbursement', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(request)
        });
        let res = await req.json();
        requestToUpdate = res;
        console.log(requestToUpdate);
        sessionStorage.setItem("requestToUpdate", JSON.stringify(requestToUpdate));
        requestToUpdate  = JSON.parse(sessionStorage.getItem("requestToUpdate"));
        console.log(res)
        alert( `The Request was Updated`);
        showRequestInfo();
    } catch(e){
        alert('There Was an Issue With The Request');
        return;
    }

}

async function findRequest(e){

    e.preventDefault();

    let request_id = document.getElementById("searchRequestById").value;
    console.log(request_id)

    let user = {
        request_id
    }

    try{
        let req = await fetch('http://localhost:8080/api/findReimbursementById', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        requestToUpdate = res;
        console.log(requestToUpdate);
        sessionStorage.setItem("requestToUpdate", JSON.stringify(requestToUpdate));
        requestToUpdate  = JSON.parse(sessionStorage.getItem("requestToUpdate"));
        showRequestInfo();
    } catch(e){
        alert('Could not Find User');
        return;
    }

}

async function getAllRequests() {
    try {
        let req = await fetch('http://localhost:8080/api/findAllReimbursements');
        let res = await req.json();
        console.log(res)
        let requests = res;
        console.log(requests);
        let td = document.getElementById("reqAllTableTD");
        for (let row in requests) {
            console.log(requests[row]);
            td.innerHTML += `<tr><td>${requests[row].id}</td>
                     <td>${requests[row].amount}</td>
                     <td>${requests[row].submitted}</td>
                     <td>${requests[row].resolved}</td>
                     <td>${requests[row].description}</td>
                     <td>${requests[row].resolver_id}</td>
                     <td>${requests[row].reimbursementType}</td>
                     <td>${requests[row].reimbursementStatus}</td>
                     </tr>`
        }
    } catch (e) {
        console.log(e)
        // temp.innerHTML = "UserName or Pass is inCorrect";
        return;
    }

}


function showRequestInfo() {
    document.getElementById("requestid").value = requestToUpdate.id;
    document.getElementById("requestAmount").value = requestToUpdate.amount;
    document.getElementById("description").value = requestToUpdate.description;
    document.getElementById("requestBy").value = requestToUpdate.author_id;
    document.getElementById("submittedDate").value = requestToUpdate.submitted;
    document.getElementById("reimbursementType").value = requestToUpdate.reimbursementType;
    document.getElementById("reimbursementStatus").value = requestToUpdate.reimbursementStatus;
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