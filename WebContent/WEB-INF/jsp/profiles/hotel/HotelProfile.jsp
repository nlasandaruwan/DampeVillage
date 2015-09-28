<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.dampevillage.domain.HotelProfile"%>
<html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>

<%
	HotelProfile hotelProfile = (HotelProfile) request.getAttribute("hotelProfileObject");
%>

<div align="center">
  <div class="body_details">
	<table>
	<tr>
		<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
	</tr>
	
	<tr>
		<td>
		<table class="form_data_table">
			<html:form action="hotelProfile">
				<tr>
					<td align="left" colspan="3" class="table_header">Edit Hotel Profile</td>
				
				</tr>
				<tr>
					<td width="20%">&nbsp;&nbsp;</td>
					<td width="30%">&nbsp;&nbsp;</td>
					<td width="40%">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">
						<font color="Red"> <%=request.getAttribute("successMessage") != null ? request
							.getAttribute("successMessage") : ""%>
						</font>
					</td>
				</tr>
				<tr>
					<td>Address 1 :<font color="Red">*</font></td>
					<td>
						<html:textarea property="addressOne" styleClass="input_text_area"
							value="<%=hotelProfile!= null? hotelProfile.getAddressOne():"" %>" />
					</td>
					
					<td>
						<font color="Red"> <%=request.getAttribute("address1EmptyError") != null ? request
							.getAttribute("address1EmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("address1TooLongError") != null ? request
								.getAttribute("address1TooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Address 2 :<font color="Red">*</font></td>
					<td>
						<html:textarea property="addressTwo" styleClass="input_text_area"
							value="<%=hotelProfile!= null? hotelProfile.getAddressTwo():"" %>" />
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("address2TooLongError") != null ? request
							.getAttribute("address2TooLongError") : ""%> </font>
					</td>
				</tr>

				<tr>
					<td>Description :<font color="Red">*</font></td>
					<td>
						<html:textarea property="description"  styleClass="input_text_area"
							value="<%=hotelProfile!= null? hotelProfile.getDescription():"" %>" />
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("descriptionEmptyError") != null ? request
							.getAttribute("descriptionEmptyError") : ""%> 
						</font>
						 <font color="Red">
							<%=request.getAttribute("descriptiontooLongError") != null ? request
								.getAttribute("descriptionTooLongError") : ""%> 
						</font>	
					</td>
				</tr>

				<tr>
					<td>Direction :<font color="Red">*</font></td>
					<td>
						<html:textarea property="direction" styleClass="input_text_area"
							value="<%=hotelProfile!= null? hotelProfile.getDirection():"" %>" />
					</td>				
					<td>
						<font color="Red"> <%=request.getAttribute("directionEmptyError") != null ? request
							.getAttribute("directionEmptyError") : ""%> 
						</font>
						 <font color="Red">
							<%=request.getAttribute("directionTooLongError") != null ? request
							.getAttribute("directionTooLongError") : ""%> 
						</font>	
					</td>
				</tr>

				<tr>
					<td>City :<font color="Red">*</font></td>
					<td>
						<html:text property="city" styleClass="input_big" maxlength="100"
							value="<%=hotelProfile!= null? hotelProfile.getCity():"" %>" /> 
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("cityEmptyError") != null ? request.getAttribute("cityEmptyError")
							: ""%> 
						</font> 
						<font color="Red"> <%=request.getAttribute("cityTooLongError") != null ? request
							.getAttribute("cityTooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Hotel Name :<font color="Red">*</font></td>
					<td>
						<html:text property="hotelName"  styleClass="input_big" maxlength="100"
							value="<%=hotelProfile!= null? hotelProfile.getHotelName():"" %>" />
					</td>
						
					<td>
						<font color="Red"> <%=request.getAttribute("hotelNameEmptyError") != null ? request
							.getAttribute("hotelNameEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("hotelNameTooLongError") != null ? request
							.getAttribute("hotelNameTooLongError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("invalidNameError") != null ? request
							.getAttribute("invalidNameError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Country :<font color="Red">*</font></td>
					<td>
						<html:select property="country" styleClass="input_big"
							value="<%=hotelProfile!= null? hotelProfile.getCountry():"" %>">
							<html:option value="">--Select--</html:option>
							<html:option value="Sri Lanka">Sri Lanka</html:option>
							<html:option value="India">India</html:option>
							<html:option value="Moldives">Moledives</html:option>
						</html:select></td>
					<td>
						<font color="Red"> <%=request.getAttribute("countryEmptyError") != null ? request
							.getAttribute("countryEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("countryTooLongError") != null ? request
							.getAttribute("countryTooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Contact Number 1 :<font color="Red">*</font></td>
					<td>
						<html:text property="contactNumberOne" styleClass="input_big" maxlength="45"
							value="<%=hotelProfile!= null? hotelProfile.getContactNumberOne():"" %>" />
					</td>
						
					<td>
						<font color="Red"> <%=request.getAttribute("contNum1EmptyError") != null ? request
							.getAttribute("contNum1EmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("contNum1TooLongError") != null ? request
							.getAttribute("contNum1TooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Contact Number 2 :</td>
					<td>
						<html:text property="contactNumberTwo" styleClass="input_big" maxlength="45"
							value="<%=hotelProfile!= null? hotelProfile.getContactNumberTwo():"" %>" />
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("contNum2TooLongError") != null ? request
								.getAttribute("contNum2TooLongError") : ""%> 
							</font>
						</td>
				</tr>

				<tr>
					<td>Fax :<font color="Red">*</font></td>
					<td>
						<html:text property="fax" styleClass="input_big" maxlength="45"
							value="<%=hotelProfile!= null? hotelProfile.getFax():"" %>" />
					</td>
						
					<td>
						<font color="Red"> <%=request.getAttribute("contNum1EmptyError") != null ? request
							.getAttribute("faxEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("contNum1TooLongError") != null ? request
							.getAttribute("faxTooLongError") : ""%> 
						</font>
					</td>
				</tr>
				
				<tr>
					<td>Postal Code :<font color="Red">*</font></td>
					<td>
						<html:text property="postalCode" styleClass="input_big" maxlength="20"
							value="<%=hotelProfile!= null? hotelProfile.getPostalCode():"" %>" />
					</td>
						
						<td>
							<font color="Red"> <%=request.getAttribute("postalCodeEmptyError") != null ? request
								.getAttribute("postalCodeEmptyError") : ""%> 
							</font>
							 <font color="Red">
								<%=request.getAttribute("postalCodeTooLongError") != null ? request
								.getAttribute("postalCodeTooLongError") : ""%> 
							</font>
						</td>
				</tr>

				<tr>
					<td>Other Web :</td>
					<td>
						<html:text property="otherWeb" styleClass="input_big" maxlength="100"
							value="<%=hotelProfile!= null? hotelProfile.getOtherWeb():"" %>" />
					</td>
					
					<td>
						<font color="Red"> <%=request.getAttribute("otherWebTooLongError") != null ? request
							.getAttribute("otherWebTooLongError") : ""%>
						</font>
					</td>
				</tr>

				<tr>
					<td>Email :<font color="Red">*</font></td>
					<td><html:text property="email" styleClass="input_big" maxlength="45"
						value="<%=hotelProfile!= null? hotelProfile.getEmail():"" %>" />
					</td>
						
						<td>
							<font color="Red"> <%=request.getAttribute("emailEmptyError") != null ? request
								.getAttribute("emailEmptyError") : ""%> 
							</font> 
							<font color="Red">
								<%=request.getAttribute("emailTooLongError") != null ? request
								.getAttribute("emailTooLongError") : ""%> 
							</font> 
							<font color="Red">
								<%=request.getAttribute("emailFormatError") != null ? request
								.getAttribute("emailFormatError") : ""%> 
							</font>
						</td>
				</tr>
				
				<tr>
					<td><html:submit value="Edit Hotel Profile" /></td>
					<td><html:hidden property="id"
						value="<%=hotelProfile!= null? hotelProfile.getId()+"":"" %>" />

					<html:hidden property="version"
						value="<%=hotelProfile!= null? hotelProfile.getVersion()+"":"" %>" />
					</td>
				</tr>
			</html:form>
		</table>

		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
</table>
  </div>
</div>


</body>

</html>
