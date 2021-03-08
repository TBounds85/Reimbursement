let inputs = document.getElementsByTagName('input');
let insertRow = document.createElement('div');
let submitButton = document.getElementsByName("submitButton");

submitButton.addEventListener('click', VerifyInputs);


function VerifyInputs() {

	let amount = inputs[0].value;
	let reason = inputs[1].value;

	if (amount < 0 || amount == "") {

		//set up for insert of error message
		let errorMessageDiv = document.getElementById('errorMessage');
		let errorMessage = document.createElement('p');
		errorMessage.innerText = "Must Be Positive $ Amount";

		//appending error message to page
		errorMessageDiv.append(errorMessage);
		errorMessageDiv.append(insertRow)
		if (Event.cancelable) {
			Event.preventDefault();
		}
	
	} else if (reason == "default") {

		//set up for insert of error message
		let errorMessageDiv = document.getElementById('errorMessage');
		let errorMessage = document.createElement('p');
		errorMessage.innerText = "Please Select A Reason For Request.";

		//appending error message to page
		errorMessageDiv.append(errorMessage);
		errorMessageDiv.append(insertRow)
		if (Event.cancelable) {
			Event.preventDefault();
		}

	} 
	window.alert("Your Request Has Been Submitted");
}