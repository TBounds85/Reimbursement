window.onload = () => {
	getRequest()
}

function getRequest(){
    let url = 'http://localhost:8080/Reimbursement/api/pages/home'
    let xhr = new XMLHttpRequest() //RS0
    
	

	
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState === 4 && xhr.status === 200){
			
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let response=JSON.parse(xhr.responseText)
			
			let div = document.getElementById('employeeDataDiv')
			
			let newDiv = document.createElement('div')
			
			let employeeId = document.createElement('h2')
			let name = document.createElement('p')
			let department = document.createElement('p')
			
			
			employeeId.innerText = "Employee ID: "+response.employeeId
			name.innerText = "Employee Name: "+response.info.lastName+", "+response.info.firstName
			department.innerText = "Department: "+response.department.departmentName
				
			newDiv.append(employeeId)
			newDiv.append(name)
			newDiv.append(department)
			
			div.append(newDiv)
        }
     }

    

    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}
         
     