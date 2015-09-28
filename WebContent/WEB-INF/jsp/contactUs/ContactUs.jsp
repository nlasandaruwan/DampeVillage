<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.dampevillage.domain.HotelProfile"%>
<%@page import="com.dampevillage.util.MasterDataLoaderUtil"%><html>
<head>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	HotelProfile hotelProfile = MasterDataLoaderUtil.getHotelProfile();
%>

 <div align="center">
  	<div class="body_details">
		 <table>
			<tr>
				<td id="header"><jsp:include page="/header.jsp"></jsp:include></td>
			</tr>
		
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			
			<tr>
				<td>
					<table  class="form_data_table">
						<tr>
							<td align="left" colspan="2" class="table_header">Contact Us</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td width="50%">
								<table class="form_data_table">
									<tr>
										<td align="left" colspan="2" class="table_header">Contact Details</td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<b><b><%= hotelProfile.getHotelName() %></b></b>
										</td>
									</tr>
									<tr>
										<td width="50%">
											<b>Address :</b>
										</td>
										<td>
											<b><%= hotelProfile.getAddressOne() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Other Web Sites :</b>
										</td>
										<td>
											<b><%= hotelProfile.getOtherWeb() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Contact Number 1 :</b>
										</td>
										<td>
											<b><%= hotelProfile.getContactNumberOne() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Contact Number 2 :</b>
										</td>
										<td>
											<b><%= hotelProfile.getContactNumberTwo() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Fax :</b>
										</td>
										<td>
											<b><%= hotelProfile.getFax() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Postal Code :</b>
										</td>
										<td>
											<b><%= hotelProfile.getPostalCode() %></b>
										</td>
									</tr>
									
									<tr>
										<td>
											<b>Email :</b>
										</td>
										<td>
											<b><%= hotelProfile.getEmail() %></b>
										</td>
									</tr>
									
								</table>
							</td>
							
							<td width="50%">
								<form action="mailNotify.do" method="post">
									<table class="form_data_table">
											<tr>
												<td align="left" colspan="2" class="table_header">Send Us an Inquiry.</td>
											</tr>
											<tr>
												<td>&nbsp;</td>
												<td colspan="2">
													<font color="Red"> <%=request.getAttribute("successInquery") != null ? request
														.getAttribute("successInquery") : ""%>
													</font>
												</td>
											</tr>
											<tr>
												<td width="50%">Name : <font color="Red">*</font></td>
												<td><input type="text" name="name" id = "name" class="input_big" /></td>
											</tr>
											<tr>
												<td width="50%">Contact Number :<font color="Red">*</font> </td>
												<td><input type="text" name="phone" id = "phone" class="input_big" /></td>
											</tr>
											<tr>
												<td width="50%">Email Address :<font color="Red">*</font> </td>
												<td><input type="text" name="email" id = "email" class="input_big" /></td>
											</tr>
											
											<tr>
												<td>Inquiry :<font color="Red">*</font> </td>
												<td>
													<textarea name="description" name="description" Class="input_text_area" ></textarea>
												</td>
											</tr>
											<tr>
												<td>
													<input type="hidden" name="hdnMode" value="inquire">
													<input type="submit" name="submit" value="Send">
												</td>
											</tr>
									</table>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="form_data_table">
						<tr>
							<td align="left" colspan="2" class="table_header">Map</td>
						</tr>
						<tr>
							<td width="40%"><img src="images/profile/road map.jpg"></td>
							<td>
								<table>
									<tr>
										<td><b>Direction: </b></td>
										<td><b><%= hotelProfile.getDirection() %></b></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
			</tr>
		</table>	
  	</div>
 </div>


</body>
</html>