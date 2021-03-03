window.onload = () => {
	getRequest()
}

function getRequest(){
    let url= 'http://localhost:8080/Reimbursement/pages/home'
    let xhr=new XMLHttpRequest() //RS0
    let DivEmployeeData = document.getElementById('employeeDataDiv')
	
   myStorage.getItem('employeeData')
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			let myStorage = window.sessionStorage;
			let employeeInfo=JSON.parse(xhr.responseText)
			var name = '<%= session.getAttribute("employeeData") %>';
			DivEmployeeData.append(name);
			DivEmployeeData.append(employeeInfo);
			DivEmployeeData.append(myStorage.getItem());
        }
     }

    

    xhr.open('GET', url) //RS 1
    xhr.send() //RS2
}

