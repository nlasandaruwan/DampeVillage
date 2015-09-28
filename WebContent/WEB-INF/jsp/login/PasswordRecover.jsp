<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@page import="com.dampevillage.log.formbean.PasswordRecoverFormBean"%>

<html>
<head>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>

<body>
<%
	PasswordRecoverFormBean passwordRecoverFormBean = (PasswordRecoverFormBean) request.getAttribute("passwordRecoverFormBean");
%>

<div align="center">
  <div class="body_details">
  	<table>
	<tr>
		<td id="header"><jsp:include page="/LoginHeader.jsp"></jsp:include></td>
	</tr>

	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>

	<tr>
		<td align="center">
			
			<table class="form_data_table">
			<html:form action="goRecover">
				<tr>
					<td colspan="2" class="table_header">Recover Password</td>
					
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td width="35%" >Email : <font color="Red">*</font> </td>
					<td width="33%" >
						<html:text styleClass="input_big" property="email" value="<%=passwordRecoverFormBean!= null? passwordRecoverFormBean.getEmail():"" %>"/>
							
					</td>
					<td width="33%">
						<font color="Red"> 
								<%=request.getAttribute("emailEmptyError") != null ? request.getAttribute("emailEmptyError") : ""%>
							</font>
							<font color="Red"> 
								<%=request.getAttribute("emailTooLongError") != null ? request.getAttribute("emailTooLongError") : ""%>
							</font>
							<font color="Red"> 
								<%=request.getAttribute("emailInvalidError") != null ? request.getAttribute("emailInvalidError") : ""%>
							</font>
					</td>
				</tr>

				<tr>
					<td>User Name : <font color="Red">*</font> </td>
					<td>
						<html:text styleClass="input_big" property="userName" value="<%=passwordRecoverFormBean!= null? passwordRecoverFormBean.getUserName():"" %>" />
						
					</td>
					<td>
						<font color="Red"> 
							<%=request.getAttribute("userNameEmptyError") != null ? request.getAttribute("userNameEmptyError") : ""%>
						</font>
						<font color="Red"> 
							<%=request.getAttribute("userNameTooLongError") != null ? request.getAttribute("userNameTooLongError") : ""%>
						</font>
					</td>
				</tr>

				<tr>
					<td>Select Question to recover your password : <font color="Red">*</font> </td>
					<td>
						<html:select property="recoverQuestion" styleClass="input_big" value="<%=passwordRecoverFormBean!= null? passwordRecoverFormBean.getRecoverQuestion():"" %>">
							<html:option value="">--Select--</html:option>
							<html:option value="quest1">What is your mother's name ?</html:option>
							<html:option value="quest2">What is your home town ?</html:option>
							<html:option value="quest3">What is your father's name?</html:option>
							<html:option value="quest4">What is your pets's name?</html:option>
						</html:select>
						
					</td>
					<td>
						<font color="Red"> 
							<%=request.getAttribute("questionEmptyError") != null ? request.getAttribute("questionEmptyError") : ""%>
						</font>
					</td>
				</tr>

				<tr>
					<td>Answer : <font color="Red">*</font> </td>
					<td>
						<html:text styleClass="input_big" property="recoverAnswer" value="<%=passwordRecoverFormBean!= null? passwordRecoverFormBean.getRecoverAnswer():"" %>" />
						
					</td>
					<td>
						<font color="Red"> 
							<%=request.getAttribute("answerEmptyError") != null ? request.getAttribute("answerEmptyError") : ""%>
						</font>
						<font color="Red"> 
							<%=request.getAttribute("answerTooLongError") != null ? request.getAttribute("answerTooLongError") : ""%>
						</font>
						
					
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">
						<%
								String invalidDetails = (String) request
											.getAttribute("invalidDetails");
									String recoverd = (String) request.getAttribute("recoverd");
									if (invalidDetails != null && !invalidDetails.equals("")) {
							%>
							<p><font color="Red"><%=invalidDetails%></font></p>
							<%
								}
									if (recoverd != null && !recoverd.equals("")) {
							%>
							<p><font color="Blue"><%=recoverd%></font></p>
							<%
								}
							%>
					</td>
					
				</tr>
				<tr>
					<td><html:submit value="Get Password" styleClass="btn" /></td>
				</tr>
			</html:form>
			</table>
		
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
