<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.dampevillage.domain.Reservation"%>
<%@page import="com.dampevillage.common.util.CommonUtil"%><html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

	<SCRIPT LANGUAGE="JavaScript">
		function dissable(){

			if(document.getElementById("agreed").checked){

				document.getElementById("submit").disabled=false;
			}
			else{
				document.getElementById("submit").disabled=true;
			}
		}
	</SCRIPT>
	
</head>

<body onload="dissable()">

<%
	Reservation reservation = (Reservation) request
			.getAttribute("reservationObject");

	String charge = (String)request.getAttribute("charge");
%>

<div align="center">
  <div class="body_details">
	<table>
	<tr>
		<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
	</tr>
	<tr><td>&nbsp;&nbsp;</td></tr>
	<tr><td>&nbsp;&nbsp;</td></tr>
	<tr>
		<td align="center">
				
		<html:form action="reservationConfirmation">
			
			<table class="result_data_table">
				<tr>
					<td align="left" colspan="2" class="table_header">Reservation Confirmation Page</td>
				</tr>
				<tr><td>&nbsp;&nbsp;</td></tr>
				<tr>
					<td class="result_data_table td" width="50%" align="center"><b>Check-in Date :</b></td>
					<td class="result_data_table td" width="50%" align="center"><b><html:hidden property="fromDate" value="<%=reservation!= null? CommonUtil.dateToString(reservation.getFromDate()):"" %>"/>
					<%=reservation!= null? CommonUtil.dateToString(reservation.getFromDate()):"" %></b></td>
				
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Check-out Date :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="toDate" value="<%=reservation!= null? CommonUtil.dateToString(reservation.getToDate()):"" %>"/>
					<%=reservation!= null? CommonUtil.dateToString(reservation.getToDate()):"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Arrival From :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="arrivalFrom" value="<%=reservation!= null? reservation.getArrivalFrom():"" %>"/>
					<%=reservation!= null? reservation.getArrivalFrom():"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Arrive For :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="arriveFor" value="<%=reservation!= null? reservation.getArriveFor():"" %>"/>
					<%=reservation!= null? reservation.getArriveFor():"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Leave After :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="leaveAfter" value="<%=reservation!= null? reservation.getLeaveAfter():"" %>"/>
					<%=reservation!= null? reservation.getLeaveAfter():"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Air Port Pick-Up :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="airportPickup" value="<%=reservation!= null && reservation.getAirportPickup().trim().equals("1")? "Required" :"Not Required" %>"/>
					<%=reservation!= null && reservation.getAirportPickup().trim().equals("Required")? "Required" :"Not Required" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Accommodation :</b></td>
					<td class="result_data_table td" align="center">
						<%  
						String accommodation = "";
						int accommodationId = 0;
						if(reservation.getAccomodationMode()!= null){
							accommodationId = reservation.getAccomodationMode().getId();
						
							switch (accommodationId) {
				            	case 1:  
				            		accommodation = "Full Board"; 
				            	break;
				            	case 2:  
				            		accommodation = "Half Board"; 
				            	break;
				            	case 3:  
				            		accommodation = "Bed & Breakfast"; 
				            	break;
			
					            default: 
					            	accommodation = "Not Specified"; 
				    	        break;
				        	}
						}
						%><b><html:hidden property="accomodationMode" value="<%=accommodationId+"" %>"/>
						<%= accommodation %></b>
					</td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Nationality :</b></td>
					<td class="result_data_table td" align="center">
					
					<%  
						String nationality = "";
					int nationalityId = 0;
						if(reservation.getAccomodationMode()!= null){
							nationalityId = Integer.valueOf(reservation.getNationality()).intValue();
						
							switch (nationalityId) {
				            	case 1:  
				            		nationality = "Sri Lankan"; 
				            	break;
				            	case 2:  
				            		nationality = "Non Sri Lankan"; 
				            	break;
				            	case 3:  
				            		nationality = "Non Sri Lankan with Residence Visa"; 
				            	break;
			
					            default: 
					            	nationality = "Sri Lankan"; 
				    	        break;
				        	}
						}
						%><b><html:hidden property="nationality" value="<%=nationalityId+"" %>"/>
						<%= nationality %></b>
					</td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>No. of Rooms :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="noOfRooms" value="<%=reservation!= null? reservation.getNoOfRooms()+"":"" %>"/>
					<%=reservation!= null? reservation.getNoOfRooms()+"":"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>No. of Adults :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="adults" value="<%=reservation!= null? reservation.getAdults()+"":"" %>"/>
					<%=reservation!= null? reservation.getAdults()+"":"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>No. of Children :</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="children" value="<%=reservation!= null? reservation.getChildren()+"":"" %>"/>
					<%=reservation!= null? reservation.getChildren()+"":"" %></b></td>
				</tr>
				
				<tr>
					<td class="result_data_table td" align="center"><b>Charge (US $$):</b></td>
					<td class="result_data_table td" align="center"><b><html:hidden property="charge" value="<%=charge %>"/>
					<%=charge %></b></td>
				</tr>
				
				<tr><td>&nbsp;&nbsp;</td></tr>
				
				<tr>
					<td colspan="2">
						<input type="checkbox" name="agreed" id="agreed" onclick="dissable()">
						I agreed to Dampe Village reservation terms and conditions.
					</td>
				</tr>
				<tr><td>&nbsp;&nbsp;</td></tr>
				<tr>
					<td colspan="2" align="center">
						<html:submit value="Confirm" styleId="submit"/>
					</td>
					
				</tr>
			</table>
			
			<html:hidden property="roomCategory" value="<%=reservation!= null? reservation.getRoomCategory():"" %>" />
			<html:hidden property="roomType"	value="<%=reservation!= null? reservation.getRoomType():"" %>" />	
			<html:hidden property="roomCategoryId" value="<%=reservation!= null? reservation.getRoomCategoryId()+"":"" %>" />
			<html:hidden property="roomTypeId"	value="<%=reservation!= null? reservation.getRoomTypeId()+"":"" %>" />	
			<html:hidden property="id" value="<%=reservation!= null? reservation.getId()+"":"" %>" />
			<html:hidden property="version"	value="<%=reservation!= null? reservation.getVersion()+"":"" %>" />
		
			</html:form>
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
