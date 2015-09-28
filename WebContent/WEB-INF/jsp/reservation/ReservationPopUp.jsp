<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.dampevillage.domain.RoomCategory"%>


<%@page import="com.dampevillage.domain.Reservation"%>
<%@page import="com.dampevillage.common.util.CommonUtil"%><html>
<head>
<title>Room Category Details</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>

<body class="body_details">
<%
	Reservation reservation = (Reservation) request.getAttribute("reservationObject");
%>

		<table class="result_data_table">
			<tr>
				<td width="100%" colspan="2" align="left">
					<img src="images/LOGO copy.png"/>
				</td>	
			</tr>
			<tr>
				<td align="left" colspan="2" class="table_header">View Reservation Details</td>
			</tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td class="result_data_table td"><b>Check-in Date :</b></td>
				<td align="left" class="result_data_table td"><b><%=reservation != null && reservation.getFromDate() != null ? CommonUtil
					.dateToString(reservation.getFromDate()) : ""%></b></td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Check-out Date :</b></td>
				<td class="result_data_table td"><b><%=reservation != null && reservation.getToDate() != null ? CommonUtil.dateToString(reservation
					.getToDate()) : ""%></b></td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Arrival From :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getArrivalFrom() : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Arrive For :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getArriveFor() : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Leave After :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getLeaveAfter() : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Air Port Pick-Up :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getAirportPickup() : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Accommodation :</b></td>
				<td class="result_data_table td">
				<%
					String accommodation = "";
					int accommodationId = 0;
					if (reservation.getAccomodationMode() != null) {
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
				%><b><%=accommodation%></b></td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>Nationality :</b></td>
				<td class="result_data_table td">
				<%
					String nationality = "";
					int nationalityId = 0;
					if (reservation.getAccomodationMode() != null) {
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
				%><b><%=nationality%></b></td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>No. of Rooms :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getNoOfRooms() + "" : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>No. of Adults :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getAdults() + "" : ""%></b>
				</td>
			</tr>

			<tr>
				<td class="result_data_table td"><b>No. of Children :</b></td>
				<td class="result_data_table td"><b><%=reservation != null ? reservation.getChildren() + "" : ""%></b>
				</td>
			</tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr><td>&nbsp;&nbsp;</td></tr>
			<tr>
				<td id="footer" colspan="2"><jsp:include page="/Footer.jsp"></jsp:include></td>
			</tr>
		</table>
</body>
</html>
