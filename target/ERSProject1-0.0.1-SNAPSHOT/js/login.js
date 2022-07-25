let form = document.getElementById("login").addEventListener('submit', login);
let temp = document.getElementById("placeHolder");
async function login(e){

    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let user = {
        username,
        password
    }

    try {
        let req = await fetch('http://localhost:8080/ERSProject1/login.html', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res);
        const currentUser = {
            user_id : res.user_id,
            username:res.username,
            password:res.password,
            firstname:res.firstName,
            lastname:res.lastName,
            email:res.email,
            userRole:res.userRole,
        };
        console.log(currentUser);
        sessionStorage.setItem("currentUser", JSON.stringify(currentUser));
        if (currentUser.userRole === "MANAGER"){
            location.href = '../admin_updateEmpoyee.html';
        }else {
            location.href = '../employee_account.html';
        }

    } catch(e){
        console.log(e);
         temp.innerHTML = "Username or Password is incorrect";
        return;
    }

}