

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<html>
<head>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>
<body>

<%
	String reservationRedirect = (String) request
			.getAttribute("reservationRedirect");
%>

<div align="center">
  	<div class="body_details">
  		<table>
	<tr>
		<td id="header"><jsp:include page="/LoginHeader.jsp"></jsp:include></td>
	</tr>
	
	<tr><td>&nbsp;&nbsp;</td></tr>
	<tr><td>&nbsp;&nbsp;</td></tr>
	
	<tr align="center">
		<td>
		<div>
			<table align="center" width="65%">
				
				<html:form action="goLogin">
					<tr>
						<td width=15%>&nbsp;&nbsp;</td>
						<td width=20%>&nbsp;&nbsp;</td>
						<td width=30%>&nbsp;&nbsp;</td>
					</tr>
					
					<tr>
						<td colspan="2"  class="table_header">User Login</td>
					</tr>
					<tr>
						<td width=15%>&nbsp;&nbsp;</td>
						<td width=20%>&nbsp;&nbsp;</td>
						<td width=30%>&nbsp;&nbsp;</td>
					</tr>
	
					<tr>
						<td>
							User Name :<font color="Red">*</font>
						</td>
						
						<td align="right">
							<html:text property="userName" styleClass="input_big"/>
								
						</td>
						<td>
							<font color="Red"> 
									<%=request.getAttribute("usernameEmptyError") != null ? request.getAttribute("usernameEmptyError") : ""%>
							</font>
							
						</td>
					</tr>
	
					<tr>
						<td>
							Password :<font color="Red">*</font>
						</td>
					
						<td align="right">
							<html:password property="password" styleClass="input_big"/>
							
						</td>
						
						<td>
							<font color="Red"> 
								<%=request.getAttribute("passwordEmptyError") != null ? request.getAttribute("passwordEmptyError") : ""%>
							</font>
							<font color="Red"> 
								<%=request.getAttribute("exceptionOccured") != null ? request.getAttribute("exceptionOccured") : ""%>
							</font>
						</td>
						
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td><html:submit value="Login" styleClass="btn" /></td>
						
						<td>&nbsp;</td><td>&nbsp;</td>
						
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<html:hidden property="reservationRedirect"
						value="<%= reservationRedirect != null?reservationRedirect:"" %>" />
				</html:form>
			</table>
		</div>
		</td>
		
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td width="40%">&nbsp;</td>
					<td width="30%"><A href="showNewUser">New User?</A> &nbsp; <A href="showPasswordRecover">Forgot Password?</A></td>
					<td width="30%">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
</table>
  	</div>
</div>

</body>
</html>
