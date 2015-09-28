<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.dampevillage.domain.Reservation"%>
<%@page import="com.dampevillage.domain.Payment"%>
<%@page import="com.dampevillage.common.util.CommonUtil"%><html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>

<body>

<%

	Payment payment = (Payment) request
				.getAttribute("paymentObject");
%>

<%= payment!= null? payment.getPaymentAmount()+" Rupies" : ""%>
<div align="center">
  <div class="body_details">
	<table>
	<tr>
		<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
	</tr>
	
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="left">
				<table class="form_data_table">
					<html:form action="payment">
						<tr>
							<td align="left" colspan="3" class="table_header">Payment Page</td>
						</tr>
						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td width="20%">Card Type :<font color="Red">*</font> </td>
							<td width="30%">
								<html:select property="cardType"	value="<%=payment!= null? payment.getCardType():"" %>" >
									<html:option value="">--Select--</html:option>
									<html:option value="Visa">Visa</html:option>
									<html:option value="Master">Master</html:option>
									<html:option value="Discover">Discover</html:option>
									<html:option value="American Express">American Express</html:option>
								</html:select>	
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("cardTypeEmptyError") != null ? request
									.getAttribute("cardTypeEmptyError") : ""%> 
								</font>
							</td>
						</tr>
						
						<tr>
							<td>Card Number :<font color="Red">*</font> </td>
							<td>
								<html:text property="cardNumber" maxlength="50" styleClass="input_big"
										value="<%=payment!= null? payment.getCardNumber():"" %>"/>
								
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("cardNumberEmptyError") != null ? request
									.getAttribute("cardNumberEmptyError") : ""%> 
								</font>		
							</td>
						</tr>
						
						<tr>
							<td>Card Holder Name :<font color="Red">*</font> </td>
							<td>
								<html:text property="cardHolderName" maxlength="25" styleClass="input_big"
										value="<%=payment!= null? payment.getCardHolderName():"" %>"/>
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("holderNameEmptyError") != null ? request
									.getAttribute("holderNameEmptyError") : ""%> 
								</font>
								<font color="Red"> <%=request.getAttribute("holderNameTooLongError") != null ? request
									.getAttribute("holderNameTooLongError") : ""%> 
								</font>
								<font color="Red"> <%=request.getAttribute("cardHolderNameError") != null ? request
									.getAttribute("cardHolderNameError") : ""%> 
								</font>
								
							</td>
						</tr>
						
						<tr>
							<td>Card Expire Month : <font color="Red">*</font> </td>
							<td>
								<html:select property="expireMonth" styleClass="dd_medium"	value="<%=payment!= null? payment.getCardExpirityMonth():"" %>" >
									<html:option value="">--Select--</html:option>
									<html:option value="1">1</html:option>
									<html:option value="2">2</html:option>
									<html:option value="3">3</html:option>
									<html:option value="4">4</html:option>
									<html:option value="5">5</html:option>
									<html:option value="6">6</html:option>
									<html:option value="7">7</html:option>
									<html:option value="8">8</html:option>
									<html:option value="9">9</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									
								</html:select>
								
								
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("expireMonthEmptyError") != null ? request
									.getAttribute("expireMonthEmptyError") : ""%> 
								</font>
							</td>
						</tr>
						
						<tr>
							<td>Card Expire Year : <font color="Red">*</font> </td>
							<td>
								<html:select property="expireYear" styleClass="dd_medium"	value="<%=payment!= null? payment.getCardExpirityYear():"" %>">
									<html:option value="">--Select--</html:option>
									<html:option value="2011">2011</html:option>
									<html:option value="2011">2011</html:option>
									<html:option value="2012">2012</html:option>
									<html:option value="2013">2013</html:option>
									<html:option value="2014">2014</html:option>
									<html:option value="2015">2015</html:option>
									<html:option value="2016">2016</html:option>
									<html:option value="2017">2017</html:option>
									<html:option value="2018">2018</html:option>
									<html:option value="2019">2019</html:option>
									<html:option value="2020">2020</html:option>
									<html:option value="2021">2021</html:option>
									<html:option value="2022">2022</html:option>
									<html:option value="2023">2023</html:option>
								</html:select>
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("expireYearEmptyError") != null ? request
									.getAttribute("expireYearEmptyError") : ""%> 
								</font>
							</td>
						</tr>
						
						<tr>
							<td>Email : <font color="Red">*</font> </td>
							<td>
								<html:text property="email" maxlength="45" styleClass="input_XL" value="<%=payment!= null? payment.getEmail()+"":"" %>"/>
								
								
								
							</td>
							<td>
								<font color="Red"> <%=request.getAttribute("emailEmptyError") != null ? request
									.getAttribute("emailEmptyError") : ""%> 
								</font>
								<font color="Red"> <%=request.getAttribute("emailTooLongError") != null ? request
									.getAttribute("emailTooLongError") : ""%> 
								</font>
								<font color="Red"> <%=request.getAttribute("emailInvalidError") != null ? request
									.getAttribute("emailInvalidError") : ""%> 
								</font>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<html:submit value="Submit" />
								<html:cancel value="Cancel" />
							</td>
						</tr>
						
						<html:hidden property="paymentAmount" value="<%=payment!= null? payment.getPaymentAmount()+"":"" %>" />
						<html:hidden property="id" value="<%=payment!= null? payment.getId()+"":"" %>" />
						<html:hidden property="version"	value="<%=payment!= null? payment.getVersion()+"":"" %>" />
						
				</html:form>
			</table>
	
		</td>
	</tr>
	
	<tr>
		<td align="left"><img src="images/CreditCard/credit_card_logos_16.gif" /></td>
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
