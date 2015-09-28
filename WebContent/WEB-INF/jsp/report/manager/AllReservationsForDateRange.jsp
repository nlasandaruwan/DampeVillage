<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/AnchorPosition.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/date.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/PopupWindow.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CalendarPopup.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calender/CommonValidator.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/confirmation/Confirm.js"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript" >
	var cal1x = new CalendarPopup("testdiv1");
	var cal1xx = new CalendarPopup("testdiv2");
</SCRIPT>

</head>
<body>

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
  			<form action="reports.do">
  				<table class="form_data_table">
  					<tr>
						<td align="left" colspan="3" class="table_header">View customer reservation for given date range</td>
						
					</tr>
					<tr><td width="20%">&nbsp;</td><td width="30%">&nbsp;</td><td>&nbsp;</td></tr>
  					<tr>
  						<td>
  							From Date:<font color="Red">*</font>
  						</td>
  						
  						<td>
							<table>
								<tr>
									<td>
										<input type="text" name="fromDate" size="16" maxlength="10" "/>
										<DIV ID="testdiv1" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
									</td>
									
									<td>
										<A HREF="#"	onClick="cal1x.select(document.forms[1].fromDate,'anchor1x','yyyy/MM/dd'); return false;"
											TITLE="cal1x.select(document.forms[1].fromDate,'anchor1x','yyyy/MM/dd'); return false;"
											NAME="anchor1x" ID="anchor1x"><img src="images/images.jpeg" border="0" height="25"/></A>			
									</td>
								</tr>
							</table>
						</td>
  						
  						
  						<td>
							<font color="Red"> <%=request.getAttribute("fromDateEmptyError") != null ? request
								.getAttribute("fromDateEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromDateTooLongError") != null ? request
								.getAttribute("fromDateTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromDateLessThanToError") != null ? request
								.getAttribute("fromDateLessThanToError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("fromInvalidDateFormatError") != null ? request
								.getAttribute("fromInvalidDateFormatError") : ""%> 
							</font>
						</td>
  					</tr>
  					
  					<tr>
  						<td>
  							To Date:<font color="Red">*</font>
  						</td>
  						
  						<td>
							<table>
								<tr>
									<td>
										<input type="text" name="toDate" size="16" maxlength="10"/>
											<DIV ID="testdiv2" STYLE="position:absolute;visibility:hidden;background-color:white;layer-background-color:white;"></DIV>
										<input type="hidden" name="hdnMode" value="allRervationsForDateRange">
									</td>
								
									<td>
										<A HREF="#"	onClick="cal1xx.select(document.forms[1].toDate,'anchor2x','yyyy/MM/dd'); return false;"
											TITLE="cal1xx.select(document.forms[1].toDate,'anchor2x','yyyy/MM/dd'); return false;"
											NAME="anchor2x" ID="anchor2x"><img src="images/images.jpeg" border="0" height="25"/></A>
									</td>
								</tr>
							</table>		
						</td>
						
  						<td>
							<font color="Red"> <%=request.getAttribute("toDateEmptyError") != null ? request
								.getAttribute("toDateEmptyError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("toDateTooLongError") != null ? request
								.getAttribute("toDateTooLongError") : ""%> 
							</font>
							<font color="Red"> <%=request.getAttribute("toInvalidDateFormatError") != null ? request
								.getAttribute("toInvalidDateFormatError") : ""%> 
							</font>
						</td>
  					</tr>
  			
  					<tr>
  						<td><button value="View Report" type="submit">View Report</button> </td>
  					</tr>
  				</table>
  			</form>
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