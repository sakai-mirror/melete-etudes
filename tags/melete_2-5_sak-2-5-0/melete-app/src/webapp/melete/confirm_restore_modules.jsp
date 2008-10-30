<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<html">
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="description" content="ETUDES-NG Course Management System, Powered by Sakai">
<meta name="keywords" content="ETUDES-NG course management system, e-learning">

<title>Melete - Manage Modules</title>
<script type="text/javascript" language="JavaScript" src="js/headscripts.js"></script>
</head>

<f:view>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0" onLoad="setMainFrameHeight('<h:outputText value="#{meleteSiteAndUserInfo.winEncodeName}"/>');">
 <h:form id="RestoreConfirmForm">
<!-- This Begins the Main Text Area -->
 <table >
	<tr>
		<td valign="top"> 
			&nbsp;
		</td>
    <td width="1962" valign="top" >
        <table width="100%"  border="1" cellpadding="3" cellspacing="0" bordercolor="#EAEAEA"  style="border-collapse: collapse">
          <tr>
            <td width="100%" height="20" bordercolor="#E2E4E8">
			<!-- top nav bar --> 
		<f:subview id="top">
			<jsp:include page="topnavbar.jsp"/> 
		</f:subview>
		<div class="meletePortletToolBarMessage"><img src="images/check.gif" alt="" width="16" height="16" align="absbottom"><h:outputText value="#{msgs.confirm_restore_modules_modules_restored}" /></div>
		</td></tr>
		
		<tr><td class="maintabledata3">
		<!-- main contents -->
		<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#EAEAEA" width="100%" id="AutoNumber1">
		  	<tr><td colspan="2" height="20" class="maintabledata5">&nbsp;</td></tr>
              <tr>
                <td width="100%" valign="top">
                  <br>
                  <table width="85%"  border="1" align="center" cellpadding="10" cellspacing="0" bordercolor="#CCCCCC">
                    <tr class="maintabledata3">
                      <td valign="top"><img src="images/right_check.gif" width="24" height="24" align="absbottom" alt="#{msgs.confirm_restore_modules_confirmation signal}"></td>
                      <td align="left"><h:outputText value="#{msgs.confirm_restore_modules_message1}" /></br>
					  		<br>
                       		<h:dataTable id="confirmrestoretable" value="#{manageModulesPage.restoreModulesList}" var="rml" width="140%" columnClasses="bold">
							<h:column>
								 <h:outputText id="modname" value="#{rml.module.title}"/>
							</h:column>
							</h:dataTable>		
							<br>
					   <span class="style5"><h:outputText value="#{msgs.confirm_restore_modules_note}" /></span>                      </td>
                    </tr>
					
                  </table><br></td>
              </tr>
			    <tr>
                <td height="30" colspan="2">         
                <div align="center">				
				<h:commandLink id="ReturntoModules"  action="#{manageModulesPage.returnToModules}" >
					<h:graphicImage id="returnToModulesImg" value="#{msgs.im_return_to_modules}" styleClass="BottomImgSpace" 
						onmouseover="this.src = '#{msgs.im_return_to_modules_over}'" 
						onmouseout="this.src = '#{msgs.im_return_to_modules}'" 
						onmousedown="this.src = '#{msgs.im_return_to_modules_down}'" 
	   				  	onmouseup="this.src = '#{msgs.im_return_to_modules_over}'"
					/>
                </h:commandLink>	
				</div>

				</td>
              </tr>
			 <tr><td colspan="2" height="20" class="maintabledata5">&nbsp;</td></tr>
            </table>
		<!--end  main -->
			  </td></tr></table> 
		</td>
  </tr>
</table>
</h:form>
<!-- This Ends the Main Text Area -->

</body>

</f:view>

</html>	  