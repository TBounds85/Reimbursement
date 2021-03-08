window.onload = () => {
	getRequest()
}

function getRequest(){
    let url = 'http://localhost:8080/Reimbursement/api/pages/decide'
    let xhr = new XMLHttpRequest() //RS0
    
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState == 4 && xhr.status == 200){
			
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let requests=JSON.parse(xhr.responseText)
			
			let insertDiv = document.getElementById("insertHere")

			
			console.log(requests)
			
			for(let r of requests){
				
				let newdiv = document.createElement('div class="container"')
				
				let requestId = document.createElement('div')
				requestId.innerHTML = "Request Id: "+r.requestId;
				
				let employeeid = document.createElement('div')
				employeeid.innerHTML = "Employee Id: "+r.employeeid;
				
				let requestedAmount = document.createElement('div')
				requestedAmount.innerHTML= "Requested Amount: "+r.requestedAmount;
				
				let reason = document.createElement('div')
				reason.innerHTML = "Reason: "+r.reason;
				
				let status = document.createElement('div')
				status.innerHTML = "Status: "+r.status;
				
				let managerId = document.createElement('div')
				managerId.innerHTML= "manager Id: "+r.managerId;
				
				newdiv.append(requestId)
				newdiv.append(employeeid)
				newdiv.append(requestedAmount)
				newdiv.append(reason)
				newdiv.append(status)
				newdiv.append(managerId)
				insertDiv.append(newdiv)
				
			}
		}
     }

    

    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}