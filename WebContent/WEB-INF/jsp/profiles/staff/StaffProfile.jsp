<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<%@page import="java.util.List"%>
<html>
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

<body>

<div align="center">
  <div class="body_details">
	<table>
	<tr>
  		<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
  	</tr>
  	
  	<tr><td>&nbsp;</td></tr>
  	<tr><td>&nbsp;</td></tr>
  	<tr><td>&nbsp;</td></tr>
  	
  	<tr>
  		<td>
  			<table class="form_data_table">
				<html:form action="searchReservations">
					<tr>
						<td align="left" colspan="3" class="table_header">Search Reservations</td>
					</tr>
					<tr>
						<td width="20%"> From Date: <font color="Red">*</font> </td>
						<td width="30%">
							<table>
								<tr>
									<td>
										<html:text property="fromDate" size="16" maxlength="16" />
										<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
									</td>
									<td>
										<A HREF="#"	onClick="cal1x.select(document.forms[1].fromDate,'anchor1xStaff','yyyy/MM/dd'); return false;"
											TITLE="cal1x.select(document.forms[1].fromDate,'anchor1xStaff','yyyy/MM/dd'); return false;"
											NAME="anchor1xStaff" ID="anchor1xStaff"><img src="images/images.jpeg" border="0" height="25"/></A>
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
						</td>
					</tr>
					<tr>
						<td> To Date: <font color="Red">*</font> </td>
						<td>
							<table>
								<tr>
									<td>
										<html:text property="toDate" size="16" maxlength="16" />
										<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
									</td>
									<td>
										<A HREF="#"	onClick="cal1xx.select(document.forms[1].toDate,'anchor2xStaff','yyyy/MM/dd'); return false;"
											TITLE="cal1xx.select(document.forms[1].toDate,'anchor2xStaff','yyyy/MM/dd'); return false;"
											NAME="anchor2xStaff" ID="anchor2xStaff"><img src="images/images.jpeg" border="0" height="25"/></A>	
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
						</td>
			
					</tr>		
    				<tr>
						<td> Customer User Name : <font color="Red">*</font> </td>
						<td>
							<html:text property="customerName" size="16" maxlength="160" />
							
						</td>
						<td>
							<font color="Red"> <%=request.getAttribute("userNameEmptyError") != null ? request
										.getAttribute("userNameEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("userNameTooLongError") != null ? request
										.getAttribute("userNameTooLongError") : ""%> 
							</font>
						</td>
					</tr> 
					<tr>
						<td><html:submit>Search</html:submit> &nbsp;&nbsp;&nbsp; <html:reset>Clear</html:reset></td>
					</tr>
				</html:form>
			</table>
  		</td>
  	</tr>
  	
  	<tr>
  		<td>
  			<%
    	List allReservationsForUser = (List)request.getAttribute("allReservationsForUser");
    	
    	if(allReservationsForUser != null && !allReservationsForUser.isEmpty()){
    		
    		%>
    			<table  class="result_data_table">
    				<tr>
							<th class="result_data_table th">From Date</th>
							<th class="result_data_table th">To Date</th>
							<th class="result_data_table th">Room Category</th>
							<th class="result_data_table th">Room Type</th>
							<th class="result_data_table th">No Of Rooms</th>
							<th class="result_data_table th">View Reservation</th>
							<th class="result_data_table th">Confirm Reservation</th>
						</tr>	
    				<logic:iterate name="allReservationsForUser" id="currentReservation" type="com.dampevillage.domain.Reservation" >
						
						<tr>
				
							<td class="result_data_table td"><%= currentReservation.getFromDate() %></td>
							<td class="result_data_table td"><%= currentReservation.getToDate() %></td>
							<td class="result_data_table td"><%= currentReservation.getRoomCategory() %></td>
							<td class="result_data_table td"><%= currentReservation.getRoomType() %></td>
							<td class="result_data_table td"><%= currentReservation.getNoOfRooms()%></td>
							<td class="result_data_table td">
								<a href="" onClick="window.open('reservation.do?hdnMode=loadReservation&id=<%=currentReservation.getId()%>','mywindow','width=445,height=810'); return false;"">View</a>
							</td >
							<td class="result_data_table td">
								<a href="reservation.do?hdnMode=confirmReservation&id=<%=currentReservation.getId()%>&version=<%=currentReservation.getVersion()%>" onClick="javascript:return confirmReservation()">Confirm</a>
							</td>
						</tr>										
					</logic:iterate>
				</table>
    		<%
    			}else{
    				if(allReservationsForUser != null && allReservationsForUser.isEmpty()){
    		%>
    			<table  class="result_data_table">	
    				<tr>
						<th class="result_data_table th">From Date</th>
						<th class="result_data_table th">To Date</th>
						<th class="result_data_table th">Room Category</th>
						<th class="result_data_table th">Room Type</th>
						<th class="result_data_table th">No Of Rooms</th>
						<th class="result_data_table th">View Reservation</th>
						<th class="result_data_table th">Confirm Reservation</th>
					</tr>
    				<tr>
    					<td colspan="7" class="result_data_table td">
    						<font color="Red"> Given Customer not have any pending reservation in the system.
							</font>
    					</td>
    				</tr>
    			</table>
    		<%
    				}
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
