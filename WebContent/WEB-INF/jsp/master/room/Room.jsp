<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.dampevillage.domain.Room"%>
<%@page import="com.dampevillage.util.MasterDataLoaderUtil"%>
<%@page import="com.dampevillage.domain.RoomType"%>
<%@page import="com.dampevillage.domain.RoomCategory"%><html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>
<%
	Room room = (Room) request
			.getAttribute("roomObject");

	String duplicateRoomNumberException = (String) request
			.getAttribute("DuplicateRoomNumberException");
	
	String roomAlreadyEngagedException = (String) request
			.getAttribute("RoomAlreadyEngagedException");
	

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
			
			<html:form action="room" method="post">
			
				<tr>
					<td align="left" colspan="3" class="table_header">Add Modify Delete Rooms</td>
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
					<td width="20%">Room Category :<font color="Red">*</font></td>
					<td width="30%">
						<% List listRoomCategories = MasterDataLoaderUtil.getAllRoomCategories(); 
									
						%>
		
				 		<html:select property="roomCategory" value="<%=room!= null? room.getRoomCategory().getId()+"":"" %>" styleClass="dd_medium">
									<html:option value="">--Select--</html:option>
									<% if(listRoomCategories !=null && !listRoomCategories.isEmpty()){
									
										for(int a=0;a<listRoomCategories.size();a++){
											RoomCategory roomCategory = (RoomCategory)listRoomCategories.get(a);
										
											%>
												<html:option value="<%= roomCategory.getId()+""%>"><%= roomCategory.getRoomCategoryType()+""%></html:option>	
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
					<td>Room Type :<font color="Red">*</font></td>
					<td>
						<% List listRoomTypes = MasterDataLoaderUtil.getAllRoomTypes(); 
									
						%>
				
						 <html:select property="roomType" value="<%=room!= null? room.getRoomType().getId()+"":"" %>" styleClass="dd_medium">
						 		<html:option value="">--Select--</html:option>
								<% if(listRoomTypes !=null && !listRoomTypes.isEmpty()){
									
									for(int a=0;a<listRoomTypes.size();a++){
										RoomType currentType = (RoomType)listRoomTypes.get(a);
										%>
											<html:option value="<%= currentType.getId()+""%>"><%= currentType.getRoomType()+""%></html:option>	
										<%
									}
								} %>
						  </html:select>
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomTypeEmptyError") != null ? request
							.getAttribute("roomTypeEmptyError") : ""%> 
						</font> 
					</td>
				</tr>
				
				<tr>
					<td>Description :<font color="Red">*</font></td>
					<td>
						<html:textarea property="description" styleClass="input_text_area" 
							value="<%=room!= null? room.getRoomDescription():"" %>" />
						
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomDescriptionEmptyError") != null ? request
							.getAttribute("roomDescriptionEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("descriptionTooLongError") != null ? request
								.getAttribute("descriptionTooLongError") : ""%> 
						</font>	
					</td>
				</tr>
				<tr>
					<td>Room Number :<font color="Red">*</font></td>
					<td>
						<html:text property="roomNumber" maxlength="20"
							value="<%=room!= null? room.getRoomNumber():"" %>" />
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomNumberEmptyError") != null ? request
							.getAttribute("roomNumberEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("roomNumberTooLongError") != null ? request
								.getAttribute("roomNumberTooLongError") : ""%> 
						</font>
						<font color="Red">
							<%=duplicateRoomNumberException!=null && !duplicateRoomNumberException.equals("")?duplicateRoomNumberException:"" %>
						</font>	
					</td>
				</tr>
				
				<tr>
					<td>Floor Number :<font color="Red">*</font></td>
					<td>
						<html:text property="floorNumber" maxlength="3"
						value="<%=room!= null? room.getFloorNumber()+"":"" %>" />
					</td>
					<td>
						<font color="Red">
							<%=request.getAttribute("floorNumberMinusError") != null ? request
								.getAttribute("floorNumberMinusError") : ""%> 
						</font>
						<font color="Red">
							<%=request.getAttribute("floorNumberLongError") != null ? request
								.getAttribute("floorNumberLongError") : ""%> 
						</font>
					</td>
				</tr>
				
				<tr>
					<td><html:submit value="Add / Edit Room" /></td>
					
				</tr>
				
				<html:hidden property="id"
						value="<%=room!= null? room.getId()+"":"" %>" />
				<html:hidden property="version"
						value="<%=room!= null? room.getVersion()+"":"" %>" />
						
				</html:form>
			</table>

		</td>
	</tr>

	<tr>
		<td align="left"><font color="Red"> <%=roomAlreadyEngagedException!=null && !roomAlreadyEngagedException.equals("")?roomAlreadyEngagedException:"" %></font></td>
	</tr>
	<tr>
		
		<td align="left">
			<table  class="result_data_table">
				<tr>
					<th class="result_data_table th">Room Category</th>
					<th class="result_data_table th">Room Type</th>
					<th class="result_data_table th">Floor number</th>
					<th class="result_data_table th">Room Number</th>
					<th class="result_data_table th">Edit Operation</th>
					<th class="result_data_table th">Delete Operation</th>
				</tr>
				<logic:iterate name="roomList" id="currentRoom" type="com.dampevillage.domain.Room">
					
					<tr>
						<td class="result_data_table td">
							<%= currentRoom.getRoomCategory().getRoomCategoryType()%>
						</td>
						<td class="result_data_table td">
							<%= currentRoom.getRoomType().getRoomType()%>
						</td>
						<td class="result_data_table td">
							<%= currentRoom.getFloorNumber()%>
						</td>
						<td class="result_data_table td">
							<%= currentRoom.getRoomNumber()%>
						</td>
						<td class="result_data_table td">
							<a href="room.do?hdnMode=editRoom&id=<%=currentRoom.getId()%>&version=<%=currentRoom.getVersion()%>" onClick="javascript:return confirmEdit()">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td class="result_data_table td">
							<a href="room.do?hdnMode=deleteRoom&id=<%=currentRoom.getId()%>&version=<%=currentRoom.getVersion()%>" onClick="javascript:return confirmDelete()">Delete</a>
						</td>
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
