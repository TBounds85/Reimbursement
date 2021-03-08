window.onload = () => {
	getInfo()
}

function getInfo(){
    let url= 'http://localhost:8080/Reimbursement/api/pages/info'
    let xhr = new XMLHttpRequest() //RS0
    
	xhr.onreadystatechange = function(){
		
		if(xhr.readyState === 4 && xhr.status === 200){
			
			//JSON.parse is a convenience function for parsing JSON as a JavaScript object
			let response=JSON.parse(xhr.responseText)
			
			let mainDiv = document.getElementById("personalData")
			let contactDiv = document.getElementById("contact")
			let addressDiv = document.getElementById("address")
			let cityDiv =document.getElementById("city")
			let stateDiv = document.getElementById("state")
			let zipDiv = document.getElementById("zip")
			
			let newDiv = document.createElement('div')
			let newDiv1 = document.createElement('div')
			let newDiv2 = document.createElement('div')
			let newDiv3 = document.createElement('div')
			let newDiv4 = document.createElement('div')
			let newDiv5 = document.createElement('div')
			
			let employeeId = document.createElement('h2')
			let name = document.createElement('p')
			let department = document.createElement('p')
			let dob = document.createElement('p')
			let contact = document.createElement('div')
			let address = document.createElement('div')
			let city = document.createElement('div')
			let state = document.createElement('div')
			let zip = document.createElement('div')
			
			employeeId.innerText = "Employee ID: "+response.employeeId
			name.innerText = "Employee Name: "+response.info.lastName+", "+response.info.firstName
			department.innerText = "Department: "+response.department.departmentName
			dob.innerText = "DoB : "+response.info.dob
			
			contact.innerText =	"Phone Number: "+response.info.contact
			address.innerText = "Address: "+response.info.address
			city.innerText = "City: "+response.info.city
			state.innerText = "State: "+response.info.state
			zip.innerText = "Zip Code: "+response.info.zipcode
			
				
			newDiv.append(employeeId)
			newDiv.append(name)
			newDiv.append(department)
			newDiv.append(dob)
			
			newDiv1.append(contact)
			newDiv2.append(address)
			newDiv3.append(city)
			newDiv4.append(state)
			newDiv5.append(zip)
			
			mainDiv.append(newDiv)
			
			contactDiv.append(newDiv1)
			addressDiv.append(newDiv2)
			cityDiv.append(newDiv3)
			stateDiv.append(newDiv4)
			zipDiv.append(newDiv5)
        }
     }
    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}