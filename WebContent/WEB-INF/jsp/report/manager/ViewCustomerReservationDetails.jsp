<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>
<body>

<div align="center">
  <div class="body_details">
	<table>
	<tr>
  		<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
  	</tr>
  	
  	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	
  	<tr>
  		<td>
  			<form action="reports.do">
  				<table class="form_data_table">
  					<tr>
						<td align="left" colspan="3" class="table_header">View customer reservations report</td>
						
					</tr>
					<tr><td width="20%">&nbsp;</td><td width="30%">&nbsp;</td><td>&nbsp;</td></tr>
  					<tr>
  						<td>
  							First Name:<font color="Red">*</font>
  						</td>
  						<td>
  							<input type="text" name="firstname" id="firstname" value="<%=request.getAttribute("firstName") != null ? request
									.getAttribute("firstName") : ""%> "/>
  						</td>
  						<td>
							<font color="Red"> <%=request.getAttribute("firstNameEmptyError") != null ? request
								.getAttribute("firstNameEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("firstNameTooLongError") != null ? request
								.getAttribute("firstNameTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("firstNameInvalidError") != null ? request
								.getAttribute("firstNameInvalidError") : ""%> 
							</font>
						</td>
  					</tr>
  					<tr>
  						<td>
  							Last Name:<font color="Red">*</font>
  						</td>
  						<td>
  							<input type="text" name="lastname" id="lastname" value="<%=request.getAttribute("lastName") != null ? request
									.getAttribute("lastName") : ""%> "/>
  							<input type="hidden" name="hdnMode" value="customerReservations">
  						</td>
  						<td>
							<font color="Red"> <%=request.getAttribute("lastNameEmptyError") != null ? request
								.getAttribute("lastNameEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("lastNameTooLongError") != null ? request
								.getAttribute("lastNameTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("lastNameInvalidError") != null ? request
								.getAttribute("lastNameInvalidError") : ""%> 
							</font>
						</td>
  					</tr>
  					
  					<tr>
  						<td><button value="View Report" type="submit">View Report</button> </td>
  					</tr>
  				</table>
  			</form>
  		</td>
  	</tr>
  	
  	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	
  	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
</table>
  </div>
</div>


  	
</body>
</html>