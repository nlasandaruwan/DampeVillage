<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.dampevillage.domain.Reservation"%>
<%@page import="com.dampevillage.common.util.CommonUtil"%><html>
<head>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/AnchorPosition.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/date.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/PopupWindow.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CommonValidator.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" >
var cal1x = new CalendarPopup("testdiv1");
var cal1xx = new CalendarPopup("testdiv2");
</SCRIPT>


</head>

<%
	Reservation reservation = (Reservation) request
			.getAttribute("reservationObject");
%>
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
			<table class="form_data_table">
				<html:form styleId="form" action="reservation">
					<tr>
						<td align="left" colspan="3" class="table_header"><%=reservation != null && reservation.getId()>0 ? "Edit Reservation" : "Add Reservation"%></td>
						
					</tr>
					<tr>
						<td width="20%">&nbsp;&nbsp;</td>
						<td width="30%">&nbsp;&nbsp;</td>
						<td width="40%">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td>Check-in Date :<font color="Red">*</font> </td>
						<td>
							<table>
								<tr>
									<td>
										<html:text property="fromDate" size="16" maxlength="10"
												value="<%=reservation!= null && reservation.getFromDate() != null ? CommonUtil.dateToString(reservation.getFromDate()):"" %>" />
										<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
									</td>
									
									<td>
										<A HREF="#"	onClick="cal1x.select(document.forms[1].fromDate,'anchor1x','yyyy/MM/dd'); return false;"
											TITLE="cal1x.select(document.forms[1].fromDate,'anchor1x','yyyy/MM/dd'); return false;"
											NAME="anchor1x" ID="anchor1x"><img src="images/images.jpeg" border="0" height="25"/></A>			
									</td>
								</tr>
							</table>
						</td>
									
						<td>
							<font color="Red"> <%=request.getAttribute("fromDateEmptyError") != null ? request
								.getAttribute("fromDateEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromDateTooLongError") != null ? request
								.getAttribute("fromDateTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromDateLessThanToError") != null ? request
								.getAttribute("fromDateLessThanToError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromInvalidDateFormatError") != null ? request
								.getAttribute("fromInvalidDateFormatError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromDateGreaterThanTodayError") != null ? request
								.getAttribute("fromDateGreaterThanTodayError") : ""%> 
							</font>
							<font color="Red"> <%= request.getAttribute("noRoomsRatesAvailableForDates")!= null?request
								.getAttribute("noRoomsRatesAvailableForDates"):"" %>
							</font>	
						</td>
					</tr>
							
					<tr>
						<td>Check-out Date :<font color="Red">*</font> </td>
						<td>
							<table>
								<tr>
									<td>
										<html:text property="toDate" size="16" maxlength="10"
											value="<%=reservation!= null && reservation.getToDate() != null ? CommonUtil.dateToString(reservation.getToDate()):"" %>" />
											<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
									</td>
								
									<td>
										<A HREF="#"	onClick="cal1xx.select(document.forms[1].toDate,'anchor2x','yyyy/MM/dd'); return false;"
											TITLE="cal1xx.select(document.forms[1].toDate,'anchor2x','yyyy/MM/dd'); return false;"
											NAME="anchor2x" ID="anchor2x"><img src="images/images.jpeg" border="0" height="25"/></A>
									</td>
								</tr>
							</table>		
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("toDateEmptyError") != null ? request
								.getAttribute("toDateEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("toDateTooLongError") != null ? request
								.getAttribute("toDateTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("toInvalidDateFormatError") != null ? request
								.getAttribute("toInvalidDateFormatError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("toDateGreaterThanTodayError") != null ? request
								.getAttribute("toDateGreaterThanTodayError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>Arrival From :<font color="Red">*</font> </td>
						<td>
							<html:select property="arrivalFrom"	value="<%=reservation!= null? reservation.getArrivalFrom():"" %>">
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
								<font color="Red"> <%=request.getAttribute("arrivalFromEmptyError") != null ? request
									.getAttribute("arrivalFromEmptyError") : ""%> 
								</font>	
							</td>
					</tr>
							
					<tr>
						<td>Arrive For :<font color="Red">*</font> </td>
						<td>
							<html:select property="arriveFor"	styleClass="dd_medium" value="<%=reservation!= null? reservation.getArriveFor():"" %>">
								<html:option value="">--Select--</html:option>
								<html:option value="Brekfest">Brekfest</html:option>
								<html:option value="Lunch">Lunch</html:option>
								<html:option value="Dinner">Dinner</html:option>
							</html:select>
						</td>
								
						<td>
							<font color="Red"> <%=request.getAttribute("arriveForEmptyError") != null ? request
								.getAttribute("arriveForEmptyError") : ""%> 
							</font>	
						</td>
					</tr>
							
					<tr>
						<td>Leave After :<font color="Red">*</font> </td>
						<td>
							<html:select property="leaveAfter"	styleClass="dd_medium" value="<%=reservation!= null? reservation.getLeaveAfter():"" %>">
								<html:option value="">--Select--</html:option>
								<html:option value="Brekfest">Brekfest</html:option>
								<html:option value="Lunch">Lunch</html:option>
								<html:option value="Dinner">Dinner</html:option>
							</html:select>	
						</td>		
						<td>
							<font color="Red"> <%=request.getAttribute("leaveAfterEmptyError") != null ? request
								.getAttribute("leaveAfterEmptyError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>Air Port Pick-Up :<font color="Red">*</font> </td>
						<td>
							<html:select property="airportPickup" styleClass="dd_medium" value="<%=reservation!= null? reservation.getAirportPickup():"" %>">
								<html:option value="">--Select--</html:option>
								<html:option value="Required">Required</html:option>
								<html:option value="Not Required">Not Required</html:option>
							</html:select>
						</td>		
						<td>
							<font color="Red"> <%=request.getAttribute("airportPickupEmptyError") != null ? request
								.getAttribute("airportPickupEmptyError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>Accommodation :<font color="Red">*</font> </td>
						<td>
							<html:select property="accomodationMode" styleClass="dd_medium" value="<%=reservation!= null && reservation.getAccomodationMode() != null? reservation.getAccomodationMode().getId()+"":"" %>">
								<html:option value="">--Select--</html:option>
								<html:option value="1">Full Board</html:option>
								<html:option value="2">Half Board</html:option>
								<html:option value="3">Bed & Breakfast</html:option>
							</html:select>		
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("accommodationModeEmptyError") != null ? request
								.getAttribute("accommodationModeEmptyError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>Nationality :<font color="Red">*</font> </td>
						<td>
							<html:select property="nationality"	value="<%=reservation!= null? reservation.getNationality():"" %>">
								<html:option value="">--Select--</html:option>
								<html:option value="1">Sri Lankan</html:option>
								<html:option value="2">Non Sri Lankan</html:option>
								<html:option value="3">Non Sri Lankan with Residence Visa</html:option>
							</html:select>							
						</td>		
						<td>
							<font color="Red"> <%=request.getAttribute("nationalityEmptyError") != null ? request
								.getAttribute("nationalityEmptyError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>No. of Rooms :<font color="Red">*</font> </td>
						<td>
							<html:text property="noOfRooms" size="16" maxlength="5" value="<%=reservation!= null? reservation.getNoOfRooms()+"":"" %>"/>&nbsp;&nbsp;&nbsp;&nbsp;													
						</td>
						<td>
							<font color="Red"> <%= request.getAttribute("roonNumberExceed")!= null?request
								.getAttribute("roonNumberExceed"):"" %>
							</font>		
							<font color="Red"> <%=request.getAttribute("roomsEmptyError") != null ? request
								.getAttribute("roomsEmptyError") : ""%> 
							</font>	
							<font color="Red"> <%=request.getAttribute("noRoomsAvailableForNewCriteria") != null ? request
								.getAttribute("noRoomsAvailableForNewCriteria") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>No. of Adults :<font color="Red">*</font> </td>
						<td>
							<html:text property="adults" size="16" maxlength="5" value="<%=reservation!= null? reservation.getAdults()+"":"" %>"/>		
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("adultsEmptyError") != null ? request
								.getAttribute("adultsEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("adultsExceedsError") != null ? request
								.getAttribute("adultsExceedsError") : ""%> 
							</font>
						</td>
					</tr>
							
					<tr>
						<td>No. of Children :<font color="Red">*</font> </td>
						<td>
							<html:text property="children" size="16" maxlength="5" value="<%=reservation!= null? reservation.getChildren()+"":"" %>"/>
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("childernEmptyError") != null ? request
								.getAttribute("childernEmptyError") : ""%> 
							</font>	
							<font color="Red"> <%=request.getAttribute("childrenExceedsError") != null ? request
								.getAttribute("childrenExceedsError") : ""%> 
							</font>	
						</td>
					</tr>
							
					<tr>
						<td colspan="3">
							<font color="Red"> <%=request.getAttribute("illegalEmailError") != null ? request
								.getAttribute("illegalEmailError") : ""%> 
							</font>
						</td>
					</tr>
						<html:hidden property="roomCategory" value="<%=reservation!= null? reservation.getRoomCategory():"" %>" />
						<html:hidden property="roomType"	value="<%=reservation!= null? reservation.getRoomType():"" %>" />
						<html:hidden property="roomCategoryId" value="<%=reservation!= null? reservation.getRoomCategoryId()+"":"" %>" />
						<html:hidden property="roomTypeId"	value="<%=reservation!= null? reservation.getRoomTypeId()+"":"" %>" />	
						<html:hidden property="id" value="<%=reservation!= null? reservation.getId()+"":"" %>" />
						<html:hidden property="version"	value="<%=reservation!= null? reservation.getVersion()+"":"" %>" />
					<tr>
						<td>
							<html:submit value="Add/Edit Reservation"/>
						</td>
					</tr>
				</html:form>
			</table>			
  		</td>
  	</tr>
  	<tr><td>&nbsp;&nbsp;</td></tr>
	<tr><td>&nbsp;&nbsp;</td></tr>
  	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
</table>
  </div>
</div>



</body>
</html>
