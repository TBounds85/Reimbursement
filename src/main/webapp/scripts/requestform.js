let inputs = document.getElementsByTagName('input');
let insertRow = document.createElement('div class="row"')
let submitButton=document.getElementsByName("submitButton")
let backButton = document.getElementsByName("backButton")
submitButton.addEventListener('click', VerifyInputs);
backButton.addEventListener('click', history.back())

function VerifyInputs(){
	
	let requestAmount = inputs[0].value;
	let reason = inputs[1].value
	
	if(requestAmount < 0){
		
		//set up for insert of error message
		let errorMessageDiv = document.getElementById('errorMessage');
		let errorMessage = document.createElement('p');
		errorMessage.innerText = "Must Be Positive $ Amount";
		
		//appending error message to page
		errorMessageDiv.append(errorMessage);
		errorMessageDiv.append(insertRow)
		if (event.cancelable){
			event.preventDefault();
		}
	}else if(reason=="default"){
		
		//set up for insert of error message
		let errorMessageDiv = document.getElementById('errorMessage');
		let errorMessage = document.createElement('p');
		errorMessage.innerText = "Please Select A Reason For Request.";
		
		//appending error message to page
		errorMessageDiv.append(errorMessage);
		errorMessageDiv.append(insertRow)
		if (event.cancelable){
			event.preventDefault();
	}else 
		window.alert("Your Request Has Been Submitted");
	
	}
}