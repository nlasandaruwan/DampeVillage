<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<link rel="stylesheet" type="text/css" href="css/Common.css" />
<link rel="stylesheet" type="text/css" href="css/FormData.css" />

<META HTTP-EQUIV="Content-type" CONTENT="text/html; charset=UTF-8">
<META NAME="keywords"
	CONTENT="cross-browser rich text editor, rte, textarea, htmlarea, content management, cms, blog, internet explorer, firefox, safari, opera, netscape, konqueror">
<META NAME="description"
	CONTENT="The cross-browser rich-text editor (RTE) is based on the designMode() functionality introduced in Internet Explorer 5, and implemented in Mozilla 1.3+ using the Mozilla Rich Text Editing API.">
<META NAME="author" CONTENT="Kevin Roth">
<META NAME="ROBOTS" CONTENT="ALL">

<script language="JavaScript" type="text/javascript" src="js/cbrte/html2xhtml.js"></script>
<script language="JavaScript" type="text/javascript" src="js/cbrte/richtext_compressed.js"></script>

	<SCRIPT LANGUAGE="JavaScript">
		function DisBox(){
			if(document.RTEDemo.all.checked){

				document.RTEDemo.to.disabled=true;
				document.RTEDemo.cc.disabled=true;
				document.RTEDemo.bcc.disabled=true;
			}
			else{
				document.RTEDemo.to.disabled=false;
				document.RTEDemo.cc.disabled=false;
				document.RTEDemo.bcc.disabled=false;
			}

			if(document.RTEDemo.system.checked){

				document.RTEDemo.password.disabled=true;
			}
			else{
				document.RTEDemo.password.disabled=false;
			}
		}
	</SCRIPT>

</head>
<body>

<div align="center">
  <div class="body_details">
  	<table >
		<tr>
			<td id="header"><jsp:include page="/AdminHeader.jsp"></jsp:include></td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td>
				<form name="RTEDemo" action="mailNotify.do" method="post" onsubmit="return submitForm();">
					<table  class="form_data_table">
						<tr>
							<td align="left" colspan="3" class="table_header">Send an email to customer(s).</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td width="10%">
								To :
							</td>
							<td width="30%">
								<textarea rows="2" cols="59" name="to" id="to"></textarea>
							</td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("recipientEmptyError") != null ? request.getAttribute("recipientEmptyError") : ""%>
								</font>
								<font color="Red"> 
									<%=request.getAttribute("toMmailInvalidError") != null ? request.getAttribute("toMmailInvalidError") : ""%>
								</font>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
								CC :
							</td>
							<td>
								<textarea rows="2" cols="59" name="cc" id="cc" ></textarea>
								
							</td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("ccMmailInvalidError") != null ? request.getAttribute("ccMmailInvalidError") : ""%>
								</font>
								
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
								BCC :
							</td>
							<td>
								<textarea rows="2" cols="59" name="bcc" id="bcc"></textarea>
								
							</td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("bccMmailInvalidError") != null ? request.getAttribute("bccMmailInvalidError") : ""%>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td>
								<table>
									<tr>
										<td>
											<input type="checkbox" name="all" id="all" onclick="DisBox()">
										</td>
										<td>Send to all customers.</td>
									</tr>
									<tr>
										
									</tr>
								</table>
							</td>
							<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td>Subject :<font color="Red">*</font></td>
							<td><input type="text" name="subject" id="subject" size="68" maxlength="200" value="<%=request.getAttribute("subject") != null ? request.getAttribute("subject") : ""%>"/></td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("subjectEmptyError") != null ? request.getAttribute("subjectEmptyError") : ""%>
								</font>
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td>
								Message :<font color="Red">*</font>
							</td>
							<td>
								
								<script language="JavaScript" type="text/javascript">
									<!--
									function submitForm() {
										//make sure hidden and iframe values are in sync for all rtes before submitting form
										updateRTEs();
										
										return true;
									}
							
									//Usage: initRTE(imagesPath, includesPath, cssFile, genXHTML, encHTML)
									initRTE("js/cbrte/images/", "js/cbrte/", "", true);
									//-->
								</script>
					
								<noscript>
								<p><b>Javascript must be enabled to use this form.</b></p>
								</noscript>
				
								<script language="JavaScript" type="text/javascript">
									<!--
									//build new richTextEditor
									var rte1 = new richTextEditor('rte1');
										<%
										//format content for preloading
										String rte1 = "";
										if (request.getParameter("rte1") != null) rte1 = request.getParameter("rte1");
										if (rte1 == "") {
											rte1 = "";
											rte1 = rteSafe(rte1);
										} else {
											//retrieve posted value
											rte1 = rteSafe(rte1);
										}
										%>
									rte1.html='<%=rte1%>';
									//rte1.toggleSrc = false;
									rte1.build();
									//-->
								</script>
								
							</td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("messageEmptyError") != null ? request.getAttribute("messageEmptyError") : ""%>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>Password :</td>
							<td><input type="password" name="password" id="password"/></td>
							<td>
								<font color="Red"> 
									<%=request.getAttribute("passwordEmptyError") != null ? request.getAttribute("passwordEmptyError") : ""%>
								</font>
							</td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td>
								<table>
									<tr>
										<td>
											<input type="checkbox" name="system" id="system" onclick="DisBox()">
										</td>
										<td>Send as a system mail.</td>
									</tr>
									<tr>
										
									</tr>
								</table>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><br/></td>
						</tr>
						<tr>
							<td>
								<p><input type="submit" name="submit" value="Send"></p>
							</td>
						</tr>
					</table>
				</form>					
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td id="footer"><jsp:include page="/Footer.jsp"></jsp:include></td>
		</tr>
	</table>
  </div>
</div>
	

		<%!public static String rteSafe(String strText) {
			//returns safe code for preloading in the RTE
			String tmpString = strText;
	
			//convert all types of single quotes
			tmpString = tmpString.replace((char) 145, (char) 39);
			tmpString = tmpString.replace((char) 146, (char) 39);
			tmpString = tmpString.replace("'", "&#39;");
	
			//convert all types of double quotes
			tmpString = tmpString.replace((char) 147, (char) 34);
			tmpString = tmpString.replace((char) 148, (char) 34);
			//	tmpString = tmpString.replace("\"", "\"");
	
			//replace carriage returns & line feeds
			tmpString = tmpString.replace((char) 10, (char) 32);
			tmpString = tmpString.replace((char) 13, (char) 32);
	
			return tmpString;
		}%>

</body>
</html>
