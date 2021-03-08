let loginDiv = document.getElementById("loginDiv");
let hasNoMessage = true;
let inputs = document.getElementsByTagName('input');

function validatePassword() {
	
	let username = inputs[0].value;
	let userPass = inputs[1].value;

	if (userPass.length < 8) {
		if(hasNoMessage === true){
			let loginForm = document.getElementById('loginForm');
			let errorMessage = document.createElement('p');
			errorMessage.innerText = "Password Must Be at Least 8 Characters";
			loginForm.append(errorMessage);
			hasNoMessage = false;
		}
		if (Event.cancelable){
			Event.preventDefault();
		}
	}
}

let button = document.querySelector('button');
button.addEventListener('click', validatePassword);







//window.alert("");