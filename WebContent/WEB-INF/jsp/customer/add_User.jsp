<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.dampevillage.domain.Customer"%>
<%@page import="com.dampevillage.common.util.SessionConstants"%>
<%@page import="java.util.List"%><html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />


<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>
<%
	Integer currentUserPrivilageId = null;

	if (request.getAttribute("hdnMode") != null
			&& request.getAttribute("hdnMode").equals("addManager")) {
		// set manager priv id.
		currentUserPrivilageId = new Integer(2);

	} else {

		if (request.getAttribute("hdnMode") != null
				&& request.getAttribute("hdnMode").equals("addStaff")) {
			// set staff priv id.
			currentUserPrivilageId = new Integer(4);

		} else {
			if (request.getSession().getAttribute(
					SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID) != null) {
				// User himself edit his details.
				currentUserPrivilageId = (Integer) request
						.getSession()
						.getAttribute(
								SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);
			}
		}
	}

	Customer customer = (Customer) request
			.getAttribute("customerObject");
	String DataIntegrityViolationExceptionInsert = (String) request
			.getAttribute("DataIntegrityViolationExceptionInsert");
	
	String passwordRe = (String) request.getAttribute("passwordRe");
%>

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
			<html:form action="customer">
				<tr>
					<td align="left" colspan="3" class="table_header"><%=customer != null && customer.getId() != 0? "Edit User "
						+ customer.getFirstName()+"" : "Add New User" %></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<font color="Red"> <%=request.getAttribute("successMessage") != null ? request
							.getAttribute("successMessage") : "" %>
						</font>
					</td>
				</tr>
				
				<tr>
					<td width="33%">Title :<font color="Red">*</font> </td>
					<td width="33%">
						<html:select property="title"
							value="<%=customer!= null? customer.getTitle():"" %>">
							<html:option value="">--Select--</html:option>
							<html:option value="Mr.">Mr.</html:option>
							<html:option value="Ms.">Ms.</html:option>
							<html:option value="Mrs.">Mrs.</html:option>
							<html:option value="Dr.">Dr.</html:option>
						</html:select>
						
					</td>
					<td width="33%">
						<font color="Red"> <%=request.getAttribute("titleEmptyError") != null ? request
									.getAttribute("titleEmptyError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>First Name :<font color="Red">*</font> </td>
					<td>
						<html:text property="firstName" maxlength="25" styleClass="input_big"
							value="<%=customer!= null? customer.getFirstName():"" %>" />
						
						
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
					<td>Last Name :<font color="Red">*</font> </td>
					<td>
						<html:text property="lasteName" maxlength="25" styleClass="input_big"
							value="<%=customer!= null? customer.getLasteName():"" %>" />
						
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
					<td>Street Number:<font color="Red">*</font> </td>
					<td>
						<html:textarea property="streetNumber" styleClass="input_text_area"
							value="<%=customer!= null? customer.getStreetNumber():"" %>" />
								
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("streeteEmptyError") != null ? request
									.getAttribute("streeteEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("streetTooLongError") != null ? request
									.getAttribute("streetTooLongError") : ""%> 
						</font>		
					</td>
				</tr>
				
				<tr>
					<td>Address1 :<font color="Red">*</font> </td>
					<td>
						<html:textarea property="addressOne" styleClass="input_text_area"
							value="<%=customer!= null? customer.getAddressOne():"" %>" />
								
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("addressOneEmptyError") != null ? request
									.getAttribute("addressOneEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("addressOneTooLongError") != null ? request
									.getAttribute("addressOneTooLongError") : ""%> 
						</font>		
					</td>
				</tr>
				
				<tr>
					<td>Address 2:<font color="Red">*</font> </td>
					<td>
						<html:textarea property="addressTwo" styleClass="input_text_area"
							value="<%=customer!= null? customer.getAddressTwo():"" %>" />
								
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("addressTwoEmptyError") != null ? request
									.getAttribute("addressTwoEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("addressTwoTooLongError") != null ? request
									.getAttribute("addressTwoTooLongError") : ""%> 
						</font>		
					</td>
				</tr>

				<tr>
					<td>Country :<font color="Red">*</font> </td>
					<td>
						<html:select property="country"
							value="<%=customer!= null? customer.getCountry():"" %>">
	
							<html:option value="">--Select--</html:option>
							<html:option value="Afghanistan">Afghanistan</html:option>
							<html:option value="Aland Islands">Aland Islands</html:option>
							<html:option value="Albania">Albania</html:option>
							<html:option value="Algeria">Algeria</html:option>
							<html:option value="American Samoa">American Samoa</html:option>
							<html:option value="Andorra">Andorra</html:option>
							<html:option value="Angola">Angola</html:option>
							<html:option value="Anguilla">Anguilla</html:option>
							<html:option value="Antarctica">Antarctica</html:option>
							<html:option value="Antigua and Barbuda">Antigua and Barbuda</html:option>
							<html:option value="Argentina">Argentina</html:option>
							<html:option value="Armenia">Armenia</html:option>
							<html:option value="Aruba">Aruba</html:option>
							<html:option value="Australia">Australia</html:option>
							<html:option value="Austria">Austria</html:option>
							<html:option value="Azerbaijan">Azerbaijan</html:option>
							<html:option value="Bahamas">Bahamas</html:option>
							<html:option value="Bahrain">Bahrain</html:option>
							<html:option value="Bangladesh">Bangladesh</html:option>
							<html:option value="Barbados">Barbados</html:option>
							<html:option value="Belarus">Belarus</html:option>
							<html:option value="Belgium">Belgium</html:option>
							<html:option value="Belize">Belize</html:option>
							<html:option value="Benin">Benin</html:option>
							<html:option value="Bermuda">Bermuda</html:option>
							<html:option value="Bhutan">Bhutan</html:option>
							<html:option value="Bolivia">Bolivia</html:option>
							<html:option value="Bosnia and Herzegovina">Bosnia and Herzegovina</html:option>
							<html:option value="Botswana">Botswana</html:option>
							<html:option value="Bouvet Island">Bouvet Island</html:option>
							<html:option value="Brazil">Brazil</html:option>
							<html:option value="British Indian Ocean Territory">British Indian Ocean Territory</html:option>
							<html:option value="British Virgin Islands">British Virgin Islands</html:option>
							<html:option value="Brunei">Brunei</html:option>
							<html:option value="Bulgaria">Bulgaria</html:option>
							<html:option value="Burkina Faso">Burkina Faso</html:option>
							<html:option value="Burundi">Burundi</html:option>
							<html:option value="Cambodia">Cambodia</html:option>
							<html:option value="Cameroon">Cameroon</html:option>
							<html:option value="Canada">Canada</html:option>
							<html:option value="Cape Verde">Cape Verde</html:option>
							<html:option value="Cayman Islands">Cayman Islands</html:option>
							<html:option value="Central African Republic">Central African Republic</html:option>
							<html:option value="Chad">Chad</html:option>
							<html:option value="Chile">Chile</html:option>
							<html:option value="China">China</html:option>
							<html:option value="Christmas Island">Christmas Island</html:option>
							<html:option value="Cocos Islands">Cocos Islands</html:option>
							<html:option value="Colombia">Colombia</html:option>
							<html:option value="Comoros">Comoros</html:option>
							<html:option value="Congo - Brazzaville">Congo - Brazzaville</html:option>
							<html:option value="Congo ">Congo - Kinshasa</html:option>
							<html:option value="Cook Islands">Cook Islands</html:option>
							<html:option value="Costa Rica">Costa Rica</html:option>
							<html:option value="Croatia">Croatia</html:option>
							<html:option value="Cuba">Cuba</html:option>
							<html:option value="Cyprus">Cyprus</html:option>
							<html:option value="Czech Republic">Czech Republic</html:option>
							<html:option value="Denmark">Denmark</html:option>
							<html:option value="Djibouti">Djibouti</html:option>
							<html:option value=" Dominica">Dominica</html:option>
							<html:option value=" Dominican Republic">Dominican Republic</html:option>
							<html:option value=" East Timor">East Timor</html:option>
							<html:option value=" Ecuador">Ecuador</html:option>
							<html:option value=" Egypt">Egypt</html:option>
							<html:option value=" El Salvador">El Salvador</html:option>
							<html:option value=" Equatorial Guinea">Equatorial Guinea</html:option>
							<html:option value=" Eritrea">Eritrea</html:option>
							<html:option value=" Estonia">Estonia</html:option>
							<html:option value=" Ethiopia">Ethiopia</html:option>
							<html:option value=" Falkland Islands">Falkland Islands</html:option>
							<html:option value=" Faroe Islands">Faroe Islands</html:option>
							<html:option value=" Fiji">Fiji</html:option>
							<html:option value=" Finland">Finland</html:option>
							<html:option value=" France">France</html:option>
							<html:option value=" French Guiana">French Guiana</html:option>
							<html:option value=" French Polynesia">French Polynesia</html:option>
							<html:option value=" French Southern Territories">French Southern Territories</html:option>
							<html:option value=" Gabon">Gabon</html:option>
							<html:option value=" Gambia">Gambia</html:option>
							<html:option value=" Georgia">Georgia</html:option>
							<html:option value=" Germany">Germany</html:option>
							<html:option value=" Ghana">Ghana</html:option>
							<html:option value=" Gibraltar">Gibraltar</html:option>
							<html:option value=" Greece">Greece</html:option>
							<html:option value=" Greenland">Greenland</html:option>
							<html:option value=" Grenada">Grenada</html:option>
							<html:option value=" Guadeloupe">Guadeloupe</html:option>
							<html:option value=" Guam">Guam</html:option>
							<html:option value=" Guatemala">Guatemala</html:option>
							<html:option value=" Guernsey">Guernsey</html:option>
							<html:option value=" Guinea">Guinea</html:option>
							<html:option value=" Guinea-Bissau">Guinea-Bissau</html:option>
							<html:option value=" Guyana">Guyana</html:option>
							<html:option value=" Haiti">Haiti</html:option>
							<html:option value=" Heard Island and McDonald Islands">Heard Island and McDonald Islands</html:option>
							<html:option value=" Honduras">Honduras</html:option>
							<html:option value=" Hong Kong">Hong Kong</html:option>
							<html:option value=" Hungary">Hungary</html:option>
							<html:option value="Iceland">Iceland</html:option>
							<html:option value="India">India</html:option>
							<html:option value="Indonesia">Indonesia</html:option>
							<html:option value="Iran">Iran</html:option>
							<html:option value="Iraq">Iraq</html:option>
							<html:option value="Ireland">Ireland</html:option>
							<html:option value="Isle of Man">Isle of Man</html:option>
							<html:option value="Israel">Israel</html:option>
							<html:option value="Italy">Italy</html:option>
							<html:option value="Ivory Coast">Ivory Coast</html:option>
							<html:option value="Jamaica">Jamaica</html:option>
							<html:option value="Japan">Japan</html:option>
							<html:option value="Jersey">Jersey</html:option>
							<html:option value="Jordan">Jordan</html:option>
							<html:option value="Kazakhstan">Kazakhstan</html:option>
							<html:option value="Kenya">Kenya</html:option>
							<html:option value="Kiribati">Kiribati</html:option>
							<html:option value="Kuwait">Kuwait</html:option>
							<html:option value="Kyrgyzstan">Kyrgyzstan</html:option>
							<html:option value="Laos">Laos</html:option>
							<html:option value="Latvia">Latvia</html:option>
							<html:option value="Lebanon">Lebanon</html:option>
							<html:option value="Lesotho">Lesotho</html:option>
							<html:option value="Liberia">Liberia</html:option>
							<html:option value="Libya">Libya</html:option>
							<html:option value="Liechtenstein">Liechtenstein</html:option>
							<html:option value="Lithuania">Lithuania</html:option>
							<html:option value="Luxembourg">Luxembourg</html:option>
							<html:option value="Macao">Macao</html:option>
							<html:option value="Macedonia">Macedonia</html:option>
							<html:option value="Madagascar">Madagascar</html:option>
							<html:option value="Malawi">Malawi</html:option>
							<html:option value="Malaysia">Malaysia</html:option>
							<html:option value="Maldives">Maldives</html:option>
							<html:option value="Mali">Mali</html:option>
							<html:option value="Malta">Malta</html:option>
							<html:option value="Marshall Islands">Marshall Islands</html:option>
							<html:option value="Martinique">Martinique</html:option>
							<html:option value="Mauritania">Mauritania</html:option>
							<html:option value="Mauritius">Mauritius</html:option>
							<html:option value="Mayotte">Mayotte</html:option>
							<html:option value="Mexico">Mexico</html:option>
							<html:option value="Micronesia">Micronesia</html:option>
							<html:option value="Moldova">Moldova</html:option>
							<html:option value="Monaco">Monaco</html:option>
							<html:option value="Mongolia">Mongolia</html:option>
							<html:option value="Montenegro">Montenegro</html:option>
							<html:option value="Montserrat">Montserrat</html:option>
							<html:option value="Morocco">Morocco</html:option>
							<html:option value="Mozambique">Mozambique</html:option>
							<html:option value="Myanmar">Myanmar</html:option>
							<html:option value="Namibia">Namibia</html:option>
							<html:option value="Nauru">Nauru</html:option>
							<html:option value="Nepal">Nepal</html:option>
							<html:option value="Netherlands">Netherlands</html:option>
							<html:option value="Netherlands Antilles">Netherlands Antilles</html:option>
							<html:option value="New Caledonia">New Caledonia</html:option>
							<html:option value="New Zealand">New Zealand</html:option>
							<html:option value="Nicaragua">Nicaragua</html:option>
							<html:option value="Niger">Niger</html:option>
							<html:option value="Nigeria">Nigeria</html:option>
							<html:option value="Niue">Niue</html:option>
							<html:option value="Norfolk Island">Norfolk Island</html:option>
							<html:option value="North Korea">North Korea</html:option>
							<html:option value="Northern Mariana Islands">Northern Mariana Islands</html:option>
							<html:option value="Norway">Norway</html:option>
							<html:option value="Oman">Oman</html:option>
							<html:option value="Pakistan">Pakistan</html:option>
							<html:option value="Palau">Palau</html:option>
							<html:option value="Palestinian Territory">Palestinian Territory</html:option>
							<html:option value="Panama">Panama</html:option>
							<html:option value="Papua New Guinea">Papua New Guinea</html:option>
							<html:option value="Paraguay">Paraguay</html:option>
							<html:option value="Peru">Peru</html:option>
							<html:option value="Philippines">Philippines</html:option>
							<html:option value="Pitcairn">Pitcairn</html:option>
							<html:option value="Poland">Poland</html:option>
							<html:option value="Portugal">Portugal</html:option>
							<html:option value="Puerto Rico">Puerto Rico</html:option>
							<html:option value="Qatar">Qatar</html:option>
							<html:option value="Reunion">Reunion</html:option>
							<html:option value="Romania">Romania</html:option>
							<html:option value="Russia">Russia</html:option>
							<html:option value="Rwanda">Rwanda</html:option>
							<html:option value="Saint Barthélemy">Saint Barthélemy</html:option>
							<html:option value="Saint Helena">Saint Helena</html:option>
							<html:option value="Saint Kitts and Nevis">Saint Kitts and Nevis</html:option>
							<html:option value="Saint Lucia">Saint Lucia</html:option>
							<html:option value="Saint Martin">Saint Martin</html:option>
							<html:option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</html:option>
							<html:option value="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</html:option>
							<html:option value="Samoa">Samoa</html:option>
							<html:option value="San Marino">San Marino</html:option>
							<html:option value="Sao Tome and Principe">Sao Tome and Principe</html:option>
							<html:option value="Saudi Arabia">Saudi Arabia</html:option>
							<html:option value="Senegal">Senegal</html:option>
							<html:option value="Serbia">Serbia</html:option>
							<html:option value="Serbia and Montenegro">Serbia and Montenegro</html:option>
							<html:option value="Seychelles">Seychelles</html:option>
							<html:option value="Sierra Leone">Sierra Leone</html:option>
							<html:option value="Singapore">Singapore</html:option>
							<html:option value="Slovakia">Slovakia</html:option>
							<html:option value="Slovenia">Slovenia</html:option>
							<html:option value="Solomon Islands">Solomon Islands</html:option>
							<html:option value="Somalia">Somalia</html:option>
							<html:option value="South Africa">South Africa</html:option>
							<html:option value="GS">South Georgia and the South Sandwich Islands</html:option>
							<html:option value="South Korea">South Korea</html:option>
							<html:option value="Spain">Spain</html:option>
							<html:option value="Sri Lanka">Sri Lanka</html:option>
							<html:option value="Sudan">Sudan</html:option>
							<html:option value="Suriname">Suriname</html:option>
							<html:option value="Svalbard and Jan Mayen">Svalbard and Jan Mayen</html:option>
							<html:option value="Swaziland">Swaziland</html:option>
							<html:option value="Sweden">Sweden</html:option>
							<html:option value="Switzerland">Switzerland</html:option>
							<html:option value="Syria">Syria</html:option>
							<html:option value="Taiwan">Taiwan</html:option>
							<html:option value="Tajikistan">Tajikistan</html:option>
							<html:option value="Tanzania">Tanzania</html:option>
							<html:option value="Thailand">Thailand</html:option>
							<html:option value="Togo">Togo</html:option>
							<html:option value="Tokelau">Tokelau</html:option>
							<html:option value="Tonga">Tonga</html:option>
							<html:option value="Trinidad and Tobago">Trinidad and Tobago</html:option>
							<html:option value="Tunisia">Tunisia</html:option>
							<html:option value="Turkey">Turkey</html:option>
							<html:option value="Turkmenistan">Turkmenistan</html:option>
							<html:option value="Turks and Caicos Islands">Turks and Caicos Islands</html:option>
							<html:option value="Tuvalu">Tuvalu</html:option>
							<html:option value="U.S. Virgin Islands">U.S. Virgin Islands</html:option>
							<html:option value="Uganda">Uganda</html:option>
							<html:option value="Ukraine">Ukraine</html:option>
							<html:option value="United Arab Emirates">United Arab Emirates</html:option>
							<html:option value="United Kingdom">United Kingdom</html:option>
							<html:option value="United States">United States</html:option>
							<html:option value="United States Minor Outlying Islands">United States Minor Outlying Islands</html:option>
							<html:option value="Uruguay">Uruguay</html:option>
							<html:option value="Uzbekistan">Uzbekistan</html:option>
							<html:option value="Vanuatu">Vanuatu</html:option>
							<html:option value="Vatican">Vatican</html:option>
							<html:option value="Venezuela">Venezuela</html:option>
							<html:option value="Vietnam">Vietnam</html:option>
							<html:option value="Wallis and Futuna">Wallis and Futuna</html:option>
							<html:option value="Western Sahara">Western Sahara</html:option>
							<html:option value="Yemen">Yemen</html:option>
							<html:option value="Zambia">Zambia</html:option>
							<html:option value="Zimbabwe">Zimbabwe</html:option>
	
						</html:select>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("countryEmptyError") != null ? request
										.getAttribute("countryEmptyError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>E mail :<font color="Red">*</font> </td>
					<td>
						<html:text property="email" maxlength="45" styleClass="input_XL"
							value="<%=customer!= null? customer.getEmail():"" %>" />
						
						
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
						<font color="Red"> <%=request.getAttribute("illegalEmailError") != null ? request
									.getAttribute("illegalEmailError") : ""%> 
						</font>
					</td>
				</tr>

				

				<tr>
					<td>Contact Mobile :<font color="Red">*</font> </td>
					<td>
						<html:text property="contactMobile" maxlength="45" styleClass="input_XL"
							value="<%=customer!= null? customer.getContactMobile():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("contactMobileEmptyError") != null ? request
										.getAttribute("contactMobileEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("contactMobileTooLongError") != null ? request
									.getAttribute("contactMobileTooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Contact Land :</td>
					<td>
						<html:text property="contactLand" maxlength="45" styleClass="input_XL"
							value="<%=customer!= null? customer.getContactLand():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("contactLandTooLongError") != null ? request
									.getAttribute("contactLandTooLongError") : ""%> 
						</font>
					</td>
					
				</tr>

				<tr>
					<td>NIC/Passport Number :<font color="Red">*</font> </td>
					<td>
						<html:text property="nicPassportNumber" maxlength="30" styleClass="input_big"
							value="<%=customer!= null? customer.getNicPassportNumber():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("nicEmptyError") != null ? request
										.getAttribute("nicEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("nicTooLongError") != null ? request
									.getAttribute("nicTooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>State :</td>
					<td>
						<html:text property="state" maxlength="20" styleClass="input_big"
							value="<%=customer!= null? customer.getState():"" %>" />
							
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("stateTooLongError") != null ? request
									.getAttribute("stateTooLongError") : ""%> 
						</font>
					</td>
				</tr>
				
				<tr>
					<td>Postal Code :</td>
					<td>
						<html:text property="postalCode" maxlength="20" styleClass="input_big"
							value="<%=customer!= null? customer.getPostalCode():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("postalCodeTooLongError") != null ? request
									.getAttribute("postalCodeTooLongError") : ""%> 
						</font>
					</td>
				</tr>
				
				<tr>
					<td>User name :<font color="Red">*</font> </td>
					<td>
						<html:text property="userName" 
								value="<%=customer!= null? customer.getUserName():"" %>" maxlength="20"  styleClass="input_medium" /> 
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("userNameEmptyError") != null ? request
										.getAttribute("userNameEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("userNameTooLongError") != null ? request
									.getAttribute("userNameTooLongError") : ""%> 
						</font>
						<font color="Red"> <%=DataIntegrityViolationExceptionInsert != null && !DataIntegrityViolationExceptionInsert
										.equals("") ? DataIntegrityViolationExceptionInsert : ""%>
						</font>
					</td>

				</tr>

				<tr>
					<td>Password :<font color="Red">*</font> </td>
					<td>
						<html:password property="password"
							value="<%=customer!= null? customer.getPassword():"" %>" maxlength="20" styleClass="input_medium" />
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("passwordEmptyError") != null ? request
										.getAttribute("passwordEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("passwordTooLongError") != null ? request
									.getAttribute("passwordTooLongError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("passwordTooShortError") != null ? request
									.getAttribute("passwordTooShortError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("twoPasswordsMismatchError") != null ? request
									.getAttribute("twoPasswordsMismatchError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Password re-enter :<font color="Red">*</font> </td>
					<td>
						<html:password property="passwordRe" maxlength="20"
							value="<%=passwordRe != null? passwordRe :"" %>"  styleClass="input_medium" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("passwordReEmptyError") != null ? request
										.getAttribute("passwordReEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("passwordReTooLongError") != null ? request
									.getAttribute("passwordReTooLongError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("passwordReTooLongError") != null ? request
									.getAttribute("passwordReTooLongError") : ""%> 
						</font>
						
					</td>
				</tr>

				<tr>
					<td>Select Question to recover your password :<font color="Red">*</font> </td>
					<td>
						<html:select property="recoverQuestion" styleClass="input_big"
							value="<%=customer!= null? customer.getRecoverQuestion():"" %>">
							<html:option value="">--Select--</html:option>
							<html:option value="quest1">What is your mother's name ?</html:option>
							<html:option value="quest2">What is your home town ?</html:option>
							<html:option value="quest3">What is your father's name?</html:option>
							<html:option value="quest4">What is your pets's name?</html:option>
						</html:select>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("recoverQuestionEmptyError") != null ? request
										.getAttribute("recoverQuestionEmptyError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Answer :<font color="Red">*</font> </td>
					<td>
						<html:text property="recoverAnswer" maxlength="30" styleClass="input_big"
							value="<%=customer!= null? customer.getRecoverAnswer():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("recoverAnswerEmptyError") != null ? request
										.getAttribute("recoverAnswerEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("recoverAnswerTooLongError") != null ? request
									.getAttribute("recoverAnswerTooLongError") : ""%> 
						</font>
					</td>

				</tr>

				<tr>
					<td><html:submit value="Add User" /></td>
					<td>&nbsp;
						<html:hidden property="id"
							value="<%=customer!= null? customer.getId()+"":"" %>" />
						<html:hidden property="version"
							value="<%=customer!= null? customer.getVersion()+"":"" %>" />
						<html:hidden property="privilageId"
							value="<%=currentUserPrivilageId != null ? currentUserPrivilageId.intValue()+"" : 3+"" %>" />
					</td>
				</tr>
				</html:form>
			</table>
		</td>
	</tr>

	<tr>
			<td align="center">
				<font color="Red"> <%=request.getAttribute("successDeleteMessage") != null ? request
					.getAttribute("successDeleteMessage") : ""%>
				</font>
			</td>
	</tr>
	<tr>
		
		<td>
		<%
			List usersToDeleteList = (List) request
					.getAttribute("managersToDelete");
			if (usersToDeleteList != null && !usersToDeleteList.isEmpty()) {
		%>
				<table class="result_data_table">
					<tr>
						<th class="result_data_table th">First Name</th>
						<th class="result_data_table th">Last Name</th>
						<th class="result_data_table th">Country</th>
						<th class="result_data_table th">Email</th>
						<th class="result_data_table th">NIC/Passport</th>		
						<th class="result_data_table th">Delete Manager</th>				
					</tr>
								
					<logic:iterate name="managersToDelete" id="manager"
						type="com.dampevillage.domain.Customer">
						<tr>
							<td class="result_data_table td">
							 	<%=manager.getFirstName()%>  
							</td>
							<td class="result_data_table td">
								<%=manager.getLasteName()%>
							</td>
							<td class="result_data_table td">
								<%=manager.getCountry()%>
							</td>
							<td class="result_data_table td">
								<%=manager.getEmail()%>
							</td>
							<td class="result_data_table td">
								<%=manager.getNicPassportNumber()%>
							</td>
							<td class="result_data_table td">
								 <a
								href="customer.do?hdnMode=deleteManager&id=<%=manager.getId()%>&version=<%=manager.getVersion()%>&firstName=<%=manager.getFirstName()%>&email=<%=manager.getEmail()%>" onClick="javascript:return confirmDelete()">Delete</a>
							</td>
						</tr>
					</logic:iterate>
				</table>
		<%
			}
		%>
		
		<%
			List staffToDeleteList = (List) request
					.getAttribute("staffToDelete");
			if (staffToDeleteList != null && !staffToDeleteList.isEmpty()) {
		%>
		<table class="result_data_table">
			<tr>
				<th class="result_data_table th">First Name</th>
				<th class="result_data_table th">Last Name</th>
				<th class="result_data_table th">Country</th>
				<th class="result_data_table th">Email</th>
				<th class="result_data_table th">NIC/Passport</th>	
				<th class="result_data_table th">Delete Staff member</th>						
			</tr>
			
			<logic:iterate name="staffToDelete" id="staff"
				type="com.dampevillage.domain.Customer">
				<tr>
					<td class="result_data_table td">
						<%=staff.getFirstName()%>  
					</td>
					<td class="result_data_table td">
						<%=staff.getLasteName()%>
					</td>
					<td class="result_data_table td">
						<%=staff.getCountry()%>
					</td>
					<td class="result_data_table td">
						<%=staff.getEmail()%>
					</td>
					<td class="result_data_table td">
						 <%=staff.getNicPassportNumber()%>
					</td>
					<td class="result_data_table td">
						<a
						href="customer.do?hdnMode=deleteStaff&id=<%=staff.getId()%>&version=<%=staff.getVersion()%>&firstName=<%=staff.getFirstName()%>&email=<%=staff.getEmail()%>" onClick="javascript:return confirmDelete()">Delete</a>
					</td>
				</tr>
			</logic:iterate>
		</table>

		<%
			}
		%>	
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
