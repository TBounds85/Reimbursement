window.onload = () => {
	getRequest()
}

function getRequest(){
    let url = 'http://localhost:8080/Reimbursement/api/viewAllEmployees'
    let xhr = new XMLHttpRequest() //RS0
    
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState == 4 && xhr.status == 200){
			
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let employees=JSON.parse(xhr.responseText)
			
			let table = document.getElementById("insertHere")

			
			//console.log(employees)
			
			for(let e of employees){
				
				let row = document.createElement('tr')
				
				let employeeid = document.createElement('td')
				employeeid.innerHTML = e.employeeId;
				
				let firstName= document.createElement('td')
				firstName.innerHTML = e.info.firstName;
				
				let lastName = document.createElement('td')
				lastName.innerHTML= e.info.lastName;
				
				let department = document.createElement('td')
				department.innerHTML = e.department.departmentName;
				
				let managerId = document.createElement('td')
				managerId.innerHTML = e.department.managerId;
				
				row.append(employeeid)
				row.append(firstName)
				row.append(lastName)
				row.append(department)
				row.append(managerId)
				table.append(row)
			}
		}
    }
    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}
