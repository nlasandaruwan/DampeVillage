<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="java.util.List"%>
<%@page import="com.dampevillage.domain.RoomPool"%>
<%@page import="com.dampevillage.util.MasterDataLoaderUtil"%>
<%@page import="com.dampevillage.domain.RoomCategory"%>
<%@page import="com.dampevillage.domain.RoomType"%><html>
<head>
<title>Room Pool</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>
<%
	RoomPool roomPool = (RoomPool) request
			.getAttribute("roomPoolObject");
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
			
			<html:form action="roomPool" method="post">
			
				<tr><td align="left" colspan="3" class="table_header">Add Modify Delete Room Pool</td></tr>
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
					<td width="25%">Room Category :<font color="Red">*</font> </td>
					<td width="30%">
						<% List listRoomCategories = MasterDataLoaderUtil.getAllRoomCategories(); 
										
						%>	
							<html:select property="roomCategory" value="<%=roomPool!= null? roomPool.getRoomCategory().getId()+"":"" %>" styleClass="dd_medium">
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
							<font color="Red"> <%=request.getAttribute("roomCategoryTooLongError") != null ? request
										.getAttribute("roomCategoryTooLongError") : ""%> 
							</font>
						</td>
				</tr>

				<tr>
					<td>Room Type :<font color="Red">*</font> </td>
					<td><% List listRoomTypes = MasterDataLoaderUtil.getAllRoomTypes(); 
									
						%>
				
						 <html:select property="roomType" value="<%=roomPool!= null? roomPool.getRoomType().getId()+"":"" %>" styleClass="dd_medium">
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
						<font color="Red"> <%=request.getAttribute("roomTypeTooLongError") != null ? request
							.getAttribute("roomTypeTooLongError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Total number of rooms : <font color="Red">*</font> </td>
					<td>
						<html:text property="totalRooms" maxlength="3"
							value="<%=roomPool!= null? roomPool.getTotalNumberOfRooms()+"":"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("totalRoomsEmptyError") != null ? request
									.getAttribute("totalRoomsEmptyError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td><html:submit value="Add/Edit Room Pool" /> <html:hidden
						property="id"
						value="<%=roomPool!= null? roomPool.getId()+"":"" %>" /> <html:hidden
						property="version"
						value="<%=roomPool!= null? roomPool.getVersion()+"":"" %>" /></td>
				</tr>
			</html:form>
		</table>
		</td>
	</tr>

	<tr>
		<td align="left">
		<table  class="result_data_table">

			<tr>
				<th class="result_data_table th">Room Category</th>
				<th class="result_data_table th">Room Type</th>
				<th class="result_data_table th">Total Number Of Rooms</th>
				<th class="result_data_table th">Edit Operation</th>
				<th class="result_data_table th">Delete Operation</th>
			</tr>

			<logic:iterate name="roomPoolList" id="currentRoomPool"
				type="com.dampevillage.domain.RoomPool">

				<tr>
					<td class="result_data_table td"><%=currentRoomPool.getRoomCategory()
								.getRoomCategoryType()%></td>
					<td class="result_data_table td"><%=currentRoomPool.getRoomType().getRoomType()%></td>
					<td class="result_data_table td"><%=currentRoomPool.getTotalNumberOfRooms()%></td>
					<td class="result_data_table td"><a
						href="roomPool.do?hdnMode=editRoomPool&id=<%=currentRoomPool.getId()%>&version=<%=currentRoomPool.getVersion()%>" onClick="javascript:return confirmEdit()">Edit</a></td>
					<td class="result_data_table td"><a
						href="roomPool.do?hdnMode=deleteRoomPool&id=<%=currentRoomPool.getId()%>&version=<%=currentRoomPool.getVersion()%>" onClick="javascript:return confirmDelete()">Delete</a></td>
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
