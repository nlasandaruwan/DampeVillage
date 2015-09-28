<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.dampevillage.domain.RoomRate"%>
<%@page import="com.dampevillage.util.MasterDataLoaderUtil"%>
<%@page import="com.dampevillage.domain.RoomCategory"%>
<%@page import="com.dampevillage.domain.RoomType"%>
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

<title>Room Rates</title>
</head>

<body>
<%
	RoomRate roomRate = (RoomRate) request
			.getAttribute("roomRateObject");

	String rateDatesOverLap = (String) request
			.getAttribute("rateDatesOverLap");
%>

<div align="center">
  <div class="body_details">
	<table>
	<tr>
		<td id="header"><jsp:include page="/AdminHeader.jsp"></jsp:include></td>
	</tr>
			
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	
	<tr>
		<td align="left">
		<table class="form_data_table">
			
			<html:form action="roomRates" method="post">
				<tr>
					<td align="left" colspan="3" class="table_header">Add Modify Delete Room Rate</td>
				</tr>
				<tr>
					<td width="20%">&nbsp;</td>
					<td colspan="2">
						<font color="Red"> <%=request.getAttribute("successMessage") != null ? request
							.getAttribute("successMessage") : ""%>
						</font>
						<font color="Red"> <%=request.getAttribute("successDeleteMessage") != null ? request
							.getAttribute("successDeleteMessage") : ""%>
						</font>
					</td>
				</tr>
				
				<tr>
					<td width="20%">Room Category :<font color="Red">*</font> </td>
					<td width="30%">
						<%
							List listRoomCategories = MasterDataLoaderUtil
										.getAllRoomCategories();
						%> <html:select property="roomCategory"
							value="<%=roomRate!= null? roomRate.getRoomCategory().getId()+"":"" %>" styleClass="dd_medium">
							<html:option value="">--Select--</html:option>
	
							<%
								if (listRoomCategories != null
												&& !listRoomCategories.isEmpty()) {
	
											for (int a = 0; a < listRoomCategories.size(); a++) {
												RoomCategory roomCategory = (RoomCategory) listRoomCategories
														.get(a);
							%>
							<html:option value="<%= roomCategory.getId()+""%>"><%=roomCategory.getRoomCategoryType()
											+ ""%></html:option>
							<%
								}
										}
							%>
	
						</html:select>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomCategoryEmptyError") != null ? request
								.getAttribute("roomCategoryEmptyError") : ""%> 
						</font> 
					</td>
				</tr>

				<tr>
					<td>Room Type :<font color="Red">*</font> </td>
					<td>
						<%
							List listRoomTypes = MasterDataLoaderUtil.getAllRoomTypes();
						%> <html:select property="roomType"
							value="<%=roomRate!= null? roomRate.getRoomType().getId()+"":"" %>" styleClass="dd_medium">
							<html:option value="">--Select--</html:option>
							<%
								if (listRoomTypes != null && !listRoomTypes.isEmpty()) {
	
											for (int a = 0; a < listRoomTypes.size(); a++) {
												RoomType currentType = (RoomType) listRoomTypes
														.get(a);
							%>
							<html:option value="<%= currentType.getId()+""%>"><%=currentType.getRoomType() + ""%></html:option>
							<%
								}
										}
							%>
						</html:select>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomTypeEmptyError") != null ? request
									.getAttribute("roomTypeEmptyError") : ""%> 
						</font> 
					</td>
				</tr>

				<tr>
					<td>Accommodation Id :<font color="Red">*</font> </td>
					<td>
						<html:select property="accomodationMode"
							value="<%=roomRate!= null? roomRate.getAccomodationMode().getId()+"":"" %>" styleClass="dd_medium">
							<html:option value="">--Select--</html:option>
							<html:option value="1">Full Board</html:option>
							<html:option value="2">Half Board</html:option>
							<html:option value="3">Bed & Breakfast</html:option>
						</html:select>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("accormodationModeEmptyError") != null ? request
									.getAttribute("accormodationModeEmptyError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Valid from :<font color="Red">*</font> </td>
					<td>
					
						<table>
							<tr>
								<td>
									<html:text property="validFrom" maxlength="10"
										value="<%=roomRate != null && roomRate.getValidFrom() != null ? CommonUtil.dateToString(roomRate.getValidFrom()):"" %>" />
									<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;">
		      						</DIV>
								</td>
								<td>
									<A HREF="#"	onClick="cal1x.select(document.forms[1].validFrom,'anchor1x','yyyy/MM/dd'); return false;"
										TITLE="cal1x.select(document.forms[1].validFrom,'anchor1x','yyyy/MM/dd'); return false;"
										NAME="anchor1x" ID="anchor1x"><img src="images/images.jpeg" border="0" height="25"/></A>	
								</td>
							</tr>
						</table>
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("validFromEmptyError") != null ? request
							.getAttribute("validFromEmptyError") : ""%> 
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
					</td>

				</tr>

				<tr>
					<td>Valid to :<font color="Red">*</font> </td>
					<td>
					
						<table>
							<tr>
								<td>
									<html:text property="validTo" maxlength="10"
										value="<%=roomRate!= null && roomRate.getValidTo() != null? CommonUtil.dateToString(roomRate.getValidTo()):"" %>" />
									<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;">
									</DIV>	
								</td>
								<td>
									<A HREF="#"	onClick="cal1xx.select(document.forms[1].validTo,'anchor2x','yyyy/MM/dd'); return false;"
									TITLE="cal1xx.select(document.forms[1].validTo,'anchor2x','yyyy/MM/dd'); return false;"
									NAME="anchor2x" ID="anchor2x"><img src="images/images.jpeg" border="0" height="25"/></A>
									
									
								</td>
							</tr>
						</table>
					
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("validToEmptyError") != null ? request
							.getAttribute("validToEmptyError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("toDateTooLongError") != null ? request
							.getAttribute("toDateTooLongError") : ""%> 
						</font>
						<font color="Red"> <%=request.getAttribute("toInvalidDateFormatError") != null ? request
							.getAttribute("toInvalidDateFormatError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Price (US $$):<font color="Red">*</font> </td>
					<td>
						<html:text property="price" maxlength="5"
							value="<%=roomRate!= null? roomRate.getPrice()+"":"" %>" />
								
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomPriceZeroError") != null ? request
									.getAttribute("roomPriceZeroError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td><html:submit value="Add/Edit Room Rate" /> <html:hidden
						property="id"
						value="<%=roomRate!= null? roomRate.getId()+"":"" %>" /> <html:hidden
						property="version"
						value="<%=roomRate!= null? roomRate.getVersion()+"":"" %>" /></td>
				</tr>
				
			</html:form>
		</table>

		</td>
	</tr>
	
	<tr>
		<td>
			<table>
				<tr>
					<td align="left"><font color="Red"><%=rateDatesOverLap != null
										&& !rateDatesOverLap.equals("") ? rateDatesOverLap
										: ""%></font></td>
				</tr>
			</table>
		</td>
	</tr>
				
	<tr>
		<td align="left">
		<table class="result_data_table">

			<tr>
				<th class="result_data_table th">Room Category</th>
				<th class="result_data_table th">Room Type</th>
				<th class="result_data_table th">Room Accommodation mode</th>
				<th class="result_data_table th">Valid from</th>
				<th class="result_data_table th">Valid to</th>
				<th class="result_data_table th">Price</th>
				<th class="result_data_table th">Edit Operation</th>
				<th class="result_data_table th">Delete Operation</th>
			</tr>

			<logic:iterate name="roomRateList" id="currentRoomRate"
				type="com.dampevillage.domain.RoomRate">

				<tr>
					<td class="result_data_table td"><%=currentRoomRate.getRoomCategory()
								.getRoomCategoryType()%></td>
					<td class="result_data_table td"><%=currentRoomRate.getRoomType().getRoomType()%></td>
					<td class="result_data_table td"><%=currentRoomRate.getAccomodationMode()
								.getAccomodation()%></td>
					<td class="result_data_table td"><%=currentRoomRate.getValidFrom().toString()%></td>
					<td class="result_data_table td"><%=currentRoomRate.getValidTo().toString()%></td>
					<td class="result_data_table td"><%=currentRoomRate.getPrice()%></td>
					<td class="result_data_table td"><a
						href="roomRates.do?hdnMode=editRoomRate&id=<%=currentRoomRate.getId()%>&version=<%=currentRoomRate.getVersion()%>" onClick="javascript:return confirmEdit()">Edit</a></td>
					<td class="result_data_table td"><a
						href="roomRates.do?hdnMode=deleteRoomRate&id=<%=currentRoomRate.getId()%>&version=<%=currentRoomRate.getVersion()%>" onClick="javascript:return confirmDelete()">Delete</a></td>
				</tr>

			</logic:iterate>
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
