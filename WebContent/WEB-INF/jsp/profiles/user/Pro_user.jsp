<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<%@page import="java.util.List"%>

<html>  
<head>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

  <body>
  
  <div align="center">
  	<div class="body_details">
  	<table width="900">
  	<tr>
  		<td width="900"><jsp:include page="/header.jsp"></jsp:include></td>
  	</tr>
  	
  	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	  
	<!-- search data panel related messages -->		
  	<tr>
  		<td align="center">
  			<font color="Red"> <%=request.getAttribute("fromDateLessThanToError") != null ? request
				.getAttribute("fromDateLessThanToError") : ""%> 
			</font>
  			<font color="Red"> <%=request.getAttribute("fromDateEmptyError") != null ? request
				.getAttribute("fromDateEmptyError") : ""%> 
			</font>
			<font color="Red"> <%=request.getAttribute("fromDateTooLongError") != null ? request
				.getAttribute("fromDateTooLongError") : ""%> 
			</font>
			<font color="Red"> <%=request.getAttribute("fromInvalidDateFormatError") != null ? request
				.getAttribute("fromInvalidDateFormatError") : ""%> 
			</font>
			<font color="Red"> <%=request.getAttribute("fromDateGreaterThanTodayError") != null ? request
				.getAttribute("fromDateGreaterThanTodayError") : ""%> 
			</font>
  		</td>
  	</tr>
  	
  	<tr>
  		<td align="center">
  			<font color="Red"> <%=request.getAttribute("toDateEmptyError") != null ? request
				.getAttribute("toDateEmptyError") : ""%> 
			</font>
  			<font color="Red"> <%=request.getAttribute("toInvalidDateFormatError") != null ? request
				.getAttribute("toInvalidDateFormatError") : ""%> 
			</font>
			<font color="Red"> <%=request.getAttribute("toDateTooLongError") != null ? request
				.getAttribute("toDateTooLongError") : ""%> 
			</font>
			<font color="Red"> <%=request.getAttribute("toDateGreaterThanTodayError") != null ? request
				.getAttribute("toDateGreaterThanTodayError") : ""%> 
			</font>
			
  		</td>
  	</tr>
  
  	<tr>	
  		<td align="center">
  			<font color="Red"> <%=request.getAttribute("roomCategoryEmptyError") != null ? request
				.getAttribute("roomCategoryEmptyError") : ""%> 
			</font> 
			<font color="Red"> <%=request.getAttribute("roomCategoryTooLongError") != null ? request
				.getAttribute("roomCategoryTooLongError") : ""%> 
			</font> 				
  		</td>
  	</tr>
  	
  	<tr>	
  		<td align="center">
  			<font color="Red"> <%=request.getAttribute("roomTypeEmptyError") != null ? request
				.getAttribute("roomTypeEmptyError") : ""%> 
			</font> 	
			<font color="Red"> <%=request.getAttribute("roomTypeTooLongError") != null ? request
				.getAttribute("roomTypeTooLongError") : ""%> 
			</font>			
  		</td>
  	</tr>
  	  	
  		<tr>
			<td align="center">
			<%
				String DataIntegrityViolationExceptionDelete = (String)request.getAttribute("DataIntegrityViolationExceptionDelete");
			  		if(DataIntegrityViolationExceptionDelete != null){
			%>
				<font color="Red"> <%= DataIntegrityViolationExceptionDelete %></font>
			<%
			  		}
			%>
				<font color="Red"> <%=request.getAttribute("successDeleteMessage") != null ? request
					.getAttribute("successDeleteMessage") : ""%>
				</font>
				<font color="Red"> <%=request.getAttribute("successMessage") != null ? request
					.getAttribute("successMessage") : ""%>
				</font>
				<font color="Red"> <%=request.getAttribute("noRoomsAvailable") != null ? request
					.getAttribute("noRoomsAvailable") : ""%>
				</font>
				
				<font color="Red"> <%=request.getAttribute("tooLateToEditReservation") != null ? request
					.getAttribute("tooLateToEditReservation") : ""%>
				</font>
				
				<font color="Red"> <%=request.getAttribute("tooLateToCancelReservation") != null ? request
					.getAttribute("tooLateToCancelReservation") : ""%>
				</font>
				
			</td>
		</tr>
    		
    		<tr>
  				<td align="center">
  					<!-- Show room search result -->		
			  		<%
			    	List roomList = (List)request.getAttribute("roomDetails");
			    	if(roomList != null && !roomList.isEmpty()){	
			    	%>	
			    	
		    			<table class="result_data_table">
		    				<tr>
	    						<td colspan="5" class="table_header">Available Rooms</td>
	    					</tr>
	    					<tr><td height="5px">&nbsp;</td></tr>
		    				<tr>
								<th class="result_data_table th">Room Category</th>
								<th class="result_data_table th">Room Type</th>
								<th class="result_data_table th">From Date</th>
								<th class="result_data_table th">To Date</th>
								<th class="result_data_table th">Available Number Of Rooms</th>				
							</tr>	
		    					<logic:iterate name="roomDetails" id="currentRoom" type="String" >
									
									<tr>
									<%
										String [] arrRoomDetails = currentRoom.split("-");
									%>
										<!-- Room Category -->
										<td class="result_data_table td"><%= arrRoomDetails[0] %>
										<a href="" onClick="window.open('DampeVillageroomCategory.do?hdnMode=viewRoomCategory&id=<%=arrRoomDetails[7]%>','mywindow','width=500,height=600')">
										View</a>
										</td>
										<td class="result_data_table td"><%= arrRoomDetails[1] %></td>
										<td class="result_data_table td"><%= arrRoomDetails[5] %></td>
										<td class="result_data_table td"><%= arrRoomDetails[6] %></td>
										<td class="result_data_table td"><%= arrRoomDetails[4]%>
										<a href="reservation.do?hdnMode=initLoadPage&roomCategory=<%=arrRoomDetails[0]%>&roomType=<%=arrRoomDetails[1]%>&fromDate=<%=arrRoomDetails[5]%>&toDate=<%=arrRoomDetails[6]%>&roomCategoryId=<%=arrRoomDetails[7]%>&roomTypeId=<%=arrRoomDetails[8]%>">Reserve</a>
										</td>
									</tr>										
								</logic:iterate>
						</table>
			  		<%
				    	}
				    %>
	    			
	    			<!-- Show All Customer users in the system to delete -->		
			  		<%
			    	List allCustomerUsers = (List)request.getAttribute("customersToDelete");
			    	if(allCustomerUsers != null && ! allCustomerUsers.isEmpty()){
			    	%>
			    				<table class="result_data_table">
			    			
				    					<tr>						
											<th class="result_data_table th">Title</th>
											<th class="result_data_table th">First Name</th>
											<th class="result_data_table th">Last Name</th>
											<th class="result_data_table th">Nic/Passport Number</th>
											<th class="result_data_table th">Email</th>
											<th class="result_data_table th">Delete Customer</th>				
										</tr>	
			    					<logic:iterate name="customersToDelete" id="customer" type="com.dampevillage.domain.Customer" >
									
										<tr>
											<td class="result_data_table td"><%= customer.getTitle()%></td>
											<td class="result_data_table td"><%= customer.getFirstName() %></td>
											<td class="result_data_table td"><%= customer.getLasteName() %></td>
											<td class="result_data_table td"><%= customer.getNicPassportNumber() %></td>
											<td class="result_data_table td"><%= customer.getEmail() %></td>
											<td class="result_data_table td">
												<a href="customer.do?hdnMode=deleteCustomer&id=<%=customer.getId()%>&version=<%=customer.getVersion()%>&firstName=<%=customer.getFirstName()%>&email=<%=customer.getEmail()%>" onClick="javascript:return confirmDelete()">Delete</a>
											</td>
										</tr>										
									</logic:iterate>
								</table>
			    	<%
			    	}else{
			    		if(allCustomerUsers != null && allCustomerUsers.isEmpty()){
				    	%>	
				    		<h1><font color="red">System don't have Customers.</font></h1>	
				    	<%
				    	}
			    	}
			    	%>
	    			
	    			<!-- Show All Reservations don by particular user -->		
			  		<%
			    	List allReservationsForUser = (List)request.getAttribute("allReservationsForUser");
			    	
			    	if(allReservationsForUser != null && !allReservationsForUser.isEmpty()){
			    	%>
				    			<table class="result_data_table">
				    				
				    				<tr>
										<th class="result_data_table th">From Date</th>
										<th class="result_data_table th">To Date</th>
										<th class="result_data_table th">Room Category</th>
										<th class="result_data_table th">Room Type</th>
										<th class="result_data_table th">Arrive For</th>
										<th class="result_data_table th">Arrival From</th>
										<th class="result_data_table th">No Of Rooms</th>
										<th class="result_data_table th">View</th>
										<th class="result_data_table th">Edit</th>
										<th class="result_data_table th">Cancel</th>
									</tr>
				    				
				    				<logic:iterate name="allReservationsForUser" id="currentReservation" type="com.dampevillage.domain.Reservation" >
											
										<tr>
											<td class="result_data_table td"><%= currentReservation.getFromDate() %></td>
											<td class="result_data_table td"><%= currentReservation.getToDate() %></td>
											<td class="result_data_table td"><%= currentReservation.getRoomCategory() %></td>
											<td class="result_data_table td"><%= currentReservation.getRoomType() %></td>
											<td class="result_data_table td"><%= currentReservation.getArriveFor() %></td>
											<td class="result_data_table td"><%= currentReservation.getArrivalFrom() %></td>
											<td class="result_data_table td"><%= currentReservation.getNoOfRooms()%></td>
											<td class="result_data_table td">
												<a href="" onClick="window.open('reservation.do?hdnMode=loadReservation&id=<%=currentReservation.getId()%>','mywindow','width=445,height=810'); return false;">View</a>
											</td>
											<td class="result_data_table td"><a href="reservation.do?hdnMode=editReservation&id=<%=currentReservation.getId()%>&version=<%=currentReservation.getVersion()%>&fromDate=<%=currentReservation.getFromDate()%>" onClick="javascript:return confirmEdit()">Edit</a></td>
											<td class="result_data_table td"><a href="reservation.do?hdnMode=deleteReservation&id=<%=currentReservation.getId()%>&version=<%=currentReservation.getVersion()%>&fromDate=<%=currentReservation.getFromDate()%>" onClick="javascript:return confirmDelete()">Cancel</a></td>
										</tr>										
									</logic:iterate>
								</table>
				    <%
				    }else{
				    	if(allReservationsForUser != null && allReservationsForUser.isEmpty()){
				    	%>
				    		<h3><font color="red">You don't have pending reservations.</font></h3>	
				    	<%
				    	}
				    }
				    %>
	    
				</td>
			</tr>
	    	
  	
  		
					
  	<tr>
  		<td>
  			<table>
  				<tr>
  					<td>
  						<table>
  							<tr>
  								<td>
  									<img src="images/HA-Generic 2.JPG" width="280px" height="240px">
  								</td>
  								<td>
  									<p>
			  				Set amidst lush green garden and constantly 
			  				refreshed by the cool breeze of the bolgoda lake, 
			  				Dampe Village provides a dream setting to 
			  				host your function.
							Air conditioned banquet hall with a 
							picturesque view of the swimming pool 
							and the bolgoda lake can 
							accommodate 250 of your loved ones. 
							Lush green garden shadowed by the 
							Kumbuk, Mango and Mangrove creates a 
							relaxing atmosphere and a break from the 
							packed feeling of a city hotel or a reception hall.
  									</p>
  								</td>
  							</tr>
  							<tr>
  								<td>
  									<p>
  										Traditionally made "Maduwa" ideally suites 
  										evening functions or registration and 
  										can accommodate up-to 60 pax.
  										Providing new world cuisine with a local 
  										touch has always been the noem 
  										of our experienced culinary team. 
  										Customized menu options offering crunchy 
  										salads to luscious desserts have stimulated 
  										the taste buds of many of our regulars.
  									</p>
  								</td>
  								<td><img src="images/S5.jpg" height="200" width="400"></td>
  							</tr>
  						</table>
  					</td>
  					<td>
  						<table>
  							<tr>
  								<td>
  									11 well appointed rooms with  A/C, TV,
  									Attached shower with hot & cold water,
									Intercom, colour Television and lake view 
									Private Balcony will make your stay at 
									Dampe Village a pampering experience. 
									Candle lit dinner  by the lake with calypso music 
									will add flare to your dream night.
									Come and feel relaxing atmosphere at 
									Dampe Village with only 45 minutes drive from 
									Colombo and you don't have to 
									worry of hour long traffic jams and 
									inadequate parking spaces as our car 
									park easily accommodate 
									75 vehicles and being situated in a tranquil 
									village you hardly caught up in the traffic.
  								</td>
  							</tr>
  						</table>
  					</td>
  					
  					<td>
  						
  					</td>
  					<td>	
  					</td>
  				</tr>
  			</table>
  		</td>
  	</tr>
  	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
  </table>
  	</div>
  </div>
  
			
  </body>
</html>
