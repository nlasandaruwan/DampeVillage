<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.dampevillage.domain.RoomType"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Add user</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

</head>

<body>
<%
	RoomType roomType = (RoomType) request
			.getAttribute("roomTypeObject");

	String roomTypeException = (String) request
			.getAttribute("RoomTypeException");
	List roomTypeList = (List) request.getAttribute("roomTypeList");
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
			
			<html:form action="roomType" method="post">
				<tr>
					<td align="left" colspan="3" class="table_header">Add Modify Delete Room Type</td>
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
					<td width="20%">Room Type :<font color="Red">*</font> </td>
					<td width="30%">
						<html:text property="roomType" styleClass="input_big"
							value="<%=roomType!= null? roomType.getRoomType():"" %>" />
					</td>
					<td>
						<font color="Red"> <%=request.getAttribute("roomTypeEmptyError") != null ? request
							.getAttribute("roomTypeEmptyError") : ""%> 
						</font> 
						<font color="Red">
							<%=request.getAttribute("roomTypeTooLongError") != null ? request
								.getAttribute("roomTypeTooLongError") : ""%> 
						</font>
						<font color="Red">
							<%=request.getAttribute("invalidTypeNameError") != null ? request
								.getAttribute("invalidTypeNameError") : ""%> 
						</font>	
					</td>
				</tr>

				<tr>
					<td>Description :<font color="Red">*</font> </td>
					<td>
						<html:textarea property="description"  styleClass="input_text_area" 
							value="<%=roomType!= null? roomType.getDescription():"" %>" />
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
					<td><html:submit value="Add/Edit Room Type" /> <html:hidden
						property="id"
						value="<%=roomType!= null? roomType.getId()+"":"" %>" /> <html:hidden
						property="version"
						value="<%=roomType!= null? roomType.getVersion()+"":"" %>" /></td>
				</tr>
			</html:form>
		</table>

		</td>
	</tr>

	<tr>
		<td align="left"><font color="Red"><%=roomTypeException != null
					&& !roomTypeException.equals("") ? roomTypeException : ""%></font></td>
	</tr>
	<tr>
		<td align="left">
		<%
			if (roomTypeList != null && !roomTypeList.isEmpty()) {
		%>
		<table class="result_data_table">
			<tr>
				<th class="result_data_table th">Room Type</th>
				<th class="result_data_table th">Room Type Description</th>
				<th class="result_data_table th">Edit Operation</th>
				<th class="result_data_table th">Delete Operation</th>
			</tr>
			<logic:iterate name="roomTypeList" id="currentRoomType"
				type="com.dampevillage.domain.RoomType">

				<tr>
					<td class="result_data_table td">
						<%=currentRoomType.getRoomType()%>
					</td>
					<td class="result_data_table td">
						<%=currentRoomType.getDescription()%>
					</td>
					<td class="result_data_table td">
						<a
						href="roomType.do?hdnMode=editRoomType&id=<%=currentRoomType.getId()%>&version=<%=currentRoomType.getVersion()%>" onClick="javascript:return confirmEdit()">Edit</a>
					</td>
					<td class="result_data_table td">
						<a
						href="roomType.do?hdnMode=deleteRoomType&id=<%=currentRoomType.getId()%>&version=<%=currentRoomType.getVersion()%>" onClick="javascript:return confirmDelete()">Delete</a>
					</td>
				</tr>
			</logic:iterate>
		</table>
		<%
			}
		%>
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
