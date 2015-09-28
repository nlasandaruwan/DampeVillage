
function validateForm(theForm){

	if(!ValidateDate()){
		alert('ValidateDate');
		return false;
	}else
	if(!checkEmail(theForm)){
		alert('checkEmail');
		return false;
	}else{
		alert('NO');
		return true;
	}
}

function ValidateDate()
    {
    var SDate = document.getElementById('fromDate').value;    	
    var EDate = document.getElementById('toDate').value;
       
          
    var alertReason1 =  'End Date must be greater than or equal to  Start Date.' 
    var alertReason2 =  'End Date can not be less than Current Date.';
	
    var endDate = new Date(EDate);    	
    var startDate= new Date(SDate);
    
    if(SDate != '' && EDate != '' && startDate > endDate)
    {
	    alert(alertReason1);
	    theForm.fromDate.value = "";
	    return false;
    }
    else if(SDate == '')	
    {
        alert("Please enter Start Date");
        return false;
    }
	else if(EDate == '')	
    {
        alert("Please enter End Date");
        return false;
    }	  
    return true;  
}

	function checkEmail(theForm) {
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(theForm.email.value)){
			return true;
		}
		alert("Invalid E-mail Address! Please re-enter.")
		return false;
	}