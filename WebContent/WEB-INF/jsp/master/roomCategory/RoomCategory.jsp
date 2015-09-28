<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.dampevillage.domain.RoomCategory"%>

<html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>
<%
	RoomCategory roomCategory = (RoomCategory) request
			.getAttribute("roomCategoryObject");

	String roomCategoryException = (String) request
			.getAttribute("RoomCategoryException");
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
			<html:form action="DampeVillageroomCategory" method="post">
				<tr>
					<td align="left" colspan="3" class="table_header">Add Modify Delete Room Category</td>
				</tr>
				<tr>
					<td width="20%">&nbsp;</td>
					<td colspan="3">
						<font color="Red"> <%=request.getAttribute("successMessage") != null ? request
							.getAttribute("successMessage") : ""%>
						</font>
						<font color="Red"> <%=request.getAttribute("successDeleteMessage") != null ? request
							.getAttribute("successDeleteMessage") : ""%>
						</font>
					</td>
				</tr>
				
				<tr>
					<td width="20%">Room Category Type :<font color="Red">*</font> </td>
					<td width="30%">
						<html:text property="categoryType" maxlength="45"
							value="<%=roomCategory!= null? roomCategory.getRoomCategoryType():"" %>" styleClass="input_big" />
					
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomCategoryEmptyError") != null ? request
							.getAttribute("roomCategoryEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("roomCategoryTooLongError") != null ? request
								.getAttribute("roomCategoryTooLongError") : ""%> 
						</font>
						<font color="Red">
							<%=request.getAttribute("invalidCategoryNameError") != null ? request
								.getAttribute("invalidCategoryNameError") : ""%> 
						</font>
					</td>
				</tr>

				<tr>
					<td>Description :<font color="Red">*</font> </td>
					<td>
						<html:textarea property="description"  styleClass="input_text_area" 
							value="<%=roomCategory!= null? roomCategory.getDescription():"" %>" />
						
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("descriptionEmptyError") != null ? request
							.getAttribute("descriptionEmptyError") : ""%> 
						</font> 
						<font color="Red">
						<%=request.getAttribute("descriptionTooLongError") != null ? request
							.getAttribute("descriptionTooLongError") : ""%> 
						</font>
					</td>
				</tr>
				<tr>
					<td><html:submit value="Add/Edit Category" /> <html:hidden
						property="id"
						value="<%=roomCategory!= null? roomCategory.getId()+"":"" %>" />
					<html:hidden property="version"
						value="<%=roomCategory!= null? roomCategory.getVersion()+"":"" %>" />
					</td>
				</tr>
			</html:form>
		</table>
		</td>
	</tr>

	<tr>
		<td align="left"><font color="Red"> <%=roomCategoryException != null
							&& !roomCategoryException.equals("") ? roomCategoryException : ""%></font></td>
	</tr>

	<tr>
		<td align="left">
		<table class="result_data_table">
			<tr>
				<th class="result_data_table th">Room Category</th>
				<th class="result_data_table th">Edit Operation</th>
				<th class="result_data_table th">Delete Operation</th>
					
			</tr>
			<logic:iterate name="roomCategoryList" id="currentRoomCategory"
				type="com.dampevillage.domain.RoomCategory">

				<tr>
					<td class="result_data_table td"><%=currentRoomCategory.getRoomCategoryType()%> </td>
					<td class="result_data_table td">
						<a href="DampeVillageroomCategory.do?hdnMode=editRoomCategory&id=<%=currentRoomCategory.getId()%>&version=<%=currentRoomCategory.getVersion()%>" onClick="javascript:return confirmEdit()">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
					</td>
					<td class="result_data_table td">
						<a href="DampeVillageroomCategory.do?hdnMode=deleteRoomCategory&id=<%=currentRoomCategory.getId()%>&version=<%=currentRoomCategory.getVersion()%>" onClick="javascript:return confirmDelete()">Delete</a>
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
