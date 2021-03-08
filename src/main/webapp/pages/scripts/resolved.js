window.onload = () => {
	getRequest()
}


function getRequest(){
    let url = 'http://localhost:8080/Reimbursement/api/pages/resolved'
    let xhr = new XMLHttpRequest() //RS0
    
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState == 4 && xhr.status == 200){
			
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let requests=JSON.parse(xhr.responseText)
			
			let table = document.getElementById("insertHere")

			
			console.log(requests)
			
			for(let r of requests){
				
				let row = document.createElement('tr')
				
				let requestId = document.createElement('td')
				requestId.innerHTML = r.requestId;
				
				let employeeid = document.createElement('td')
				employeeid.innerHTML = r.employeeid;
				
				let requestedAmount = document.createElement('td')
				requestedAmount.innerHTML= r.requestedAmount;
				
				let reason = document.createElement('td')
				reason.innerHTML = r.reason;
				
				let status = document.createElement('td')
				status.innerHTML = r.status;
				
				let managerId = document.createElement('td')
				managerId.innerHTML= r.managerId;
				
				row.append(requestId)
				row.append(employeeid)
				row.append(requestedAmount)
				row.append(reason)
				row.append(status)
				row.append(managerId)
				table.append(row)
				
			}
		}
     }

    

    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}

backButton.addEventListener('click', hitstory.back());