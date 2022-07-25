let formSignUp = document.getElementById("signup").addEventListener('submit', signup);

async function signup(e){

    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    
	console.log(username, password, firstname, lastname, email);
	
    let user = {
        username,
        password,
        firstname,
        lastname,
        email
    }

    try{
        let req = await fetch('http://localhost:8080/api/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        let res = await req.json();
        console.log(res)
        alert( `An Employee Account with username ${username} was Created
		 you may login now!`);
        location.href = `../login.html`;
    } catch(e){
        alert('Username or password was incorrect!');
        return;
    }
    
}