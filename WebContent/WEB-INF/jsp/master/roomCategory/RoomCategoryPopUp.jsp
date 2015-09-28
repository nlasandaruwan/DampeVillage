<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<%@page import="com.dampevillage.domain.RoomCategory"%>

<html>
<head>
<title>Room Category Details</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

</head>

<body class="body_details">
<%
	RoomCategory roomCategory = (RoomCategory) request.getAttribute("roomCategoryObject");
	String imageName = roomCategory.getImageName();
%>
<table>
	
	<tr>
		<td width="100%">
			<img src="images/LOGO copy.png"/>
		</td>
			
	</tr>
	
	<tr>
		<td align="left">
			<table>	
				<tr>
					<td align="left" colspan="2" class="table_header"><%=roomCategory.getRoomCategoryType() %> Room Category Description</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				
				<tr>
					<td>
					<%
						int catId = roomCategory.getId();
	
						if (catId == 1) {
							%> <img src="images/roomCategories/Deluxe.jpg" width="380px" height="340px"/> 
							
							<%
	 					}
						if (catId == 2) {
							%> <img src="images/roomCategories/Semi Delux.jpg" width="380px" height="340px" /> 
							
							<%
	 					}
						if (catId == 3) {
							%> <img src="images/roomCategories/Standard.jpg" width="380px" height="340px" /> 
							
							<%
	 					}
					 %>
					</td>
	
					<td>
						<table>
							<tr>
								<td><%=roomCategory.getDescription()%></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	
	
	<tr>
		<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
	</tr>
</table>
</body>
</html>
