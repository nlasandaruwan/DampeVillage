<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.dampevillage.common.util.SessionConstants"%><html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Slid Show related -->

	<script type="text/javascript" src="js/slideShow/jquery.js"></script>
	<script type="text/javascript" src="js/slideShow/slideshow.js"></script>
	<link rel="stylesheet" type="text/css" href="css/slideshow/slideshow.css" />

<!-- Slid Show related -->

<!-- Menu related -->

	<link type="text/css" href="css/menu/menu.css" rel="stylesheet" />
	<script type="text/javascript" src="js/menu/jquery.js"></script>
	<script type="text/javascript" src="js/menu/menu.js"></script>
	
<!-- Menu related -->	

</head>

<body>
	
	<%
		String userName = (String)request.getSession().getAttribute(SessionConstants.Common.CURRENT_USER_NAME);
	%>
	
<table width="100%">	
	<tr>
		<td width="30%">
			<img src="images/LOGO copy.png"/>
		</td>
		<td align="right">
			<img src="images/welcomeMe.png"/>
		</td>	
	</tr>
	
	<tr>
		<td colspan="2">
			<div id="menu">
				  <ul class="menu">				
					<%
						Integer logedInUserPrivilage = (Integer) request.getSession().getAttribute(SessionConstants.Common.CURRENT_USER_PRIVILAGE_ID);
							if(logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 4){
					%>
						<li><a href="showStaffProfile"><span>Home</span></a></li>
					<%
							}else{
					%>
						<li><a href="showProfile"><span>Home</span></a></li>
					<%
							}
					%>
						
					<%
							if(userName == null){
					%>
						<li><a href="showLogin"><span>Login</span></a></li>
					<%
						}else{
					%>
						<li><a href="goLogin.do?hdnMode=logout"><span>Logout</span></a></li>	
						
						<!-- User Operations main menu -->
						<li><a href="#" class="parent"><span>User Operations</span></a>
							<div>
								<ul>
									<li><A href="customer.do?hdnMode=editCustomer"><span>Edit User Data</span></a></li>
									<li><A href="reservation.do?hdnMode=viewAllReservations"><span>Reservation History</span></a></li>
								</ul>
							</div>
						</li>
					<%
					}
					%>
						
					<% if(logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 1){ %>
							
						<!-- Administrator Operations main menu -->
						<li><a href="#" class="parent"><span>Administrator Operations</span></a>
							<div>
								<ul>
									<li><A href="hotelProfile.do?hdnMode=getHotelProfile"><span>Modify Hotel Profile</span></A></li>
									<li><A href="notifyMail"><span>Send an Email</span></A></li>
									<li><a href="#" class="parent"><span>Room Operations</span></a>
								    	<div>
								    		<ul>
												<li><A href="room.do?hdnMode=getAllRooms"><span>Add Modify Rooms</span></A></li>
												<li><A href="DampeVillageroomCategory.do?hdnMode=getAllRoomCategory"><span>Add Modify Room Category</span></A></li>
												<li><A href="roomType.do?hdnMode=getAllRoomType"><span>Add Modify Room Types</span></A></li>
												<li><A href="roomRates.do?hdnMode=getAllRoomRates"><span>Add Modify Room Rates</span></A></li>
												<li><A href="roomPool.do?hdnMode=getAllRoomPools"><span>Add Modify Room Pool</span></A></li>											
											</ul>
										</div>
									</li>
									<li><a href="#" class="parent"><span>User Operations</span></a>
										<div>
											<ul>
												<li><A href="customer.do?hdnMode=addManager"><span>Add Delete Manager</span></A></li>
												<li><A href="customer.do?hdnMode=addStaff"><span>Add Delete Staff</span></A></li>
												<li><A href="customer.do?hdnMode=getAllCustomers"><span>Delete Customers</span></A></li>
												
											</ul>
										</div>
									</li>			
								</ul>
							</div>
						</li>
					<%
					}
					%>
						
					<% if(logedInUserPrivilage != null && logedInUserPrivilage.intValue() == 2){ %>
							
						<!-- Manager Operations main menu -->	
						<li><a href="#" class="parent"><span>Manager Operations</span></a>
							<div>
								<ul>
									<li><A href="notifyMail"><span>Send an Email</span></A></li>
									<li><A href="roomRates.do?hdnMode=getAllRoomRates"><span>Add Modify Room Rates</span></A></li>					
								</ul>
							</div>
						</li>
					<%
					}
					%>
						
					<!-- Common reports for both Administrator and managers -->		
					<% if(logedInUserPrivilage != null && ((logedInUserPrivilage.intValue() == 1)||(logedInUserPrivilage.intValue() == 2))){ %>
						
						<!-- Report generation main menu -->
						
							
					<% if(logedInUserPrivilage != null && (logedInUserPrivilage.intValue() == 1)){ %>
						<li><a href="#" class="parent"><span>Reports</span></a>	
							<div>
								<ul>
									<!-- Administrator specific reports -->
									<li><a href="reports.do?hdnMode=viewAllCustomers"><span>View All Customers</span></a></li>
									<li><a href="reports.do?hdnMode=viewCustomersByCountry"><span>View Customers By Country</span></a></li>
									<li><a href="viewCustomerDetails"><span>View Customer Details</span></a></li>	
									
								</ul>
							</div>		
						</li>
					<%
					}
					%>			
									
					<% if(logedInUserPrivilage != null && (logedInUserPrivilage.intValue() == 2)){ %>
						<li><a href="#" class="parent"><span>Reports</span></a>	
							<div>
								<ul>
									<!-- Manager specific reports -->
									<li><a href="#" class="parent"><span>Customer Reports</span></a>
										<div>
											<ul>
												<li><a href="reports.do?hdnMode=viewAllCustomers"><span>View All Customers</span></a></li>
												<li><a href="reports.do?hdnMode=viewCustomersByCountry"><span>View Customers By Country</span></a></li>
												<li><a href="viewCustomerDetails"><span>View Customer Details</span></a></li>				
											</ul>
										</div>
									</li>
									
									<li><a href="#" class="parent"><span>Reservation Reports</span></a>
										<div>
											<ul>
												<li><a href="reports.do?hdnMode=allReservationStatus"><span>View All Reservation Status</span></a></li>
												<li><a href="viewCustomerReservationStatus"><span>View Customer Reservation Status</span></a></li>
												<li><a href="viewReservationsForDateRange"><span>View Reservation For Date Range</span></a></li>
												<li><a href="viewIncomesForDateRange"><span>View Income For Date Range</span></a></li>
												<li><a href="viewCustomerReservations"><span>View Customer Reservations</span></a></li>
											</ul>
										</div>
									</li>	
								</ul>
							</div>		
						</li>
					<%
					}
					%>		
						
					<%
					}
					%>	
						<!-- Gallery -->
						<li><a href="" onClick="window.open('viewGallery','mywindow','width=448,height=336'); return false;"><span>Photo gallery</span></a></li>
						
						<!-- Contact Us -->
						<li><a href="contactUs"><span>Contact Us</span></a></li>
						
						<div class="menu div">
							<ul>
								<li><a href="http://apycom.com/"></a></li>
							</ul>
						</div>
						
						<!-- About Us -->		
						<li class="last"><a href=""><span>About Us</span></a></li>	
				</ul>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<%= userName!=null ? "Welcome "+userName +"..!" :"" %>
		</td>
	</tr>
	<tr>
		<td><jsp:include page="corner.jsp"></jsp:include></td>
		<td align="right">
			<div id="slideshow">
            	
            	<img src="images/slideShow/Customer/1.jpg" class="active"  width="550" height="300"/>
                <img src="images/slideShow/Customer/2.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/3.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/4.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/5.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/6.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/8.tif" width="550" height="300" />
                <img src="images/slideShow/Customer/9.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/10.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/11.jpg" width="550" height="300" />
                <img src="images/slideShow/Customer/12.jpg" width="550" height="300" />
            	
          </div>
			<!--<img src="images/nashville-tennessee-hotel-top.jpg" width="500" />
		--></td>
	</tr>
</table>

</body>
</html>