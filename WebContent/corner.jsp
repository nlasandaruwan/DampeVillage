<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@page import="com.dampevillage.util.MasterDataLoaderUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.dampevillage.domain.RoomCategory"%>
<%@page import="com.dampevillage.domain.RoomType"%><html>
<head>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/AnchorPosition.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/date.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/PopupWindow.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CommonValidator.js"></SCRIPT>


<SCRIPT LANGUAGE="JavaScript" >
var cal1xSearch = new CalendarPopup("testdiv1Search");
var cal1xxSearch = new CalendarPopup("testdiv2Search");
</SCRIPT>
<!-- The next line prints out the source in this example page. It should not be included when you actually use the calendar popup code -->

</head>
  <body>
    
	<table align="left" >
		<tr>
		<td><img src="images/cornerLogo.png" width="300"/></td>
	</tr>
		
		<tr>
			<td>
				<html:form styleId="form" action="search">
				<table>
					
					<tr>
						<td>
							<table>      	
						      	<tr>
						      		<td>
						      			From :<font color="Red">*</font>
						      		</td>
						      		<td>
						      			<html:text property="fromDateSearch" size="16" maxlength="10" styleId="fromDateSearch"/>
						      			<DIV ID="testdiv1Search" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;">
						      			</DIV>
						      		</td>	
									<td>
										<A HREF="#"	onClick="cal1xSearch.select(document.forms[0].fromDateSearch,'anchor1xSearch','yyyy/MM/dd'); return false;"
										TITLE="cal1xSearch.select(document.forms[0].fromDateSearch,'anchor1xSearch','yyyy/MM/dd'); return false;"
										NAME="anchor1xSearch" ID="anchor1xSearch"><img src="images/images.jpeg" border="0" height="25"/></A>
									</td>
								</tr>
				      	
						      	<tr>	
						      		<td>
						      			To  :<font color="Red">*</font>
						      		</td>
						      		<td>
						      			<html:text property="toDateSearch" size="16" maxlength="10" styleId="toDateSearch"/>
						      				<DIV ID="testdiv2Search" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
						      		</td>
									<td>
										<A HREF="#"	onClick="cal1xxSearch.select(document.forms[0].toDateSearch,'anchor2xSearch','yyyy/MM/dd'); return false;"
										TITLE="cal1xxSearch.select(document.forms[0].toDateSearch,'anchor2xSearch','yyyy/MM/dd'); return false;"
										NAME="anchor2xSearch" ID="anchor2xSearch"><img src="images/images.jpeg" border="0" height="25"/></A>
									</td>
								
								</tr>
				      	
						      	<tr>	
						      		<td>
						      			Room Category :<font color="Red">*</font>
						      		</td>
						      		<td>
						      			<% List listRoomCategories = MasterDataLoaderUtil.getAllRoomCategories(); 
													
										%>
						      			<html:select property="roomCategory" styleClass="dd_medium">
								
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
						      	</tr>
				      	
						      	<tr>	
						      		<td>
						      			Room Type :<font color="Red">*</font>
						      		</td>
						      		<td>
						      			<% List listRoomTypes = MasterDataLoaderUtil.getAllRoomTypes(); 
													
										%>
						      			<html:select property="roomType" styleClass="dd_medium">
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
						      	</tr>	
				      	
						      	<tr>
						      		<td>
						      			<html:submit >Search</html:submit>&nbsp;<html:reset>Clear</html:reset>
						      		</td>
						      	</tr>
				      	
				      </table>
					</td>
					</tr>
				</table>

   			 </html:form>
			</td>
		</tr>
		
	</table>


  </body>
</html>
