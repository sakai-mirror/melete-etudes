<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<html">
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="description" content="ETUDES-NG Course Management System, Powered by Sakai">
<meta name="keywords" content="ETUDES-NG course management system, e-learning">


<title>Melete - Restore Inactive Modules</title>
<script type="text/javascript" language="JavaScript" src="js/headscripts.js"></script>
</head>

<f:view>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0" onload="setMainFrameHeight('<h:outputText value="#{meleteSiteAndUserInfo.winEncodeName}"/>');" >
<h:form id="RestoreModuleForm">
<!-- This Begins the Main Text Area -->

<table>
	<tr>
		<td valign="top"></td>
    	<td width="1962" valign="top">
        	<table width="100%"  border="1" cellpadding="3" cellspacing="0" bordercolor="#EAEAEA"  style="border-collapse: collapse">
          		<tr>
            		<td width="100%" height="20" bordercolor="#E2E4E8">
					<!-- top nav bar -->
						<f:subview id="top">
								<jsp:include page="topnavbar.jsp"/> 
						</f:subview>
						<div class="meletePortletToolBarMessage"><img src="images/folder_into.gif" alt="" width="16" height="16" align="absmiddle"><h:outputText value="#{msgs.restore_modules_restoring_inactive}" /></div>
					</td>
				</tr>
				<tr>
					<td class="maintabledata3" valign="top">
						<!-- main page contents -->
			 			<h:messages id="restoreerror" layout="table" style="color : red" />
						<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#EAEAEA" width="100%" id="AutoNumber1">
						<tr>
              					<td colspan="2" width="100%" valign="top">
			  						<h:dataTable id="table1"  value="#{manageModulesPage.archiveModulesList}" var="aml" width="110%"  headerClass="tableheader2" columnClasses="style3" >
			  							<h:column>
			  							<f:facet name="header">
																   <h:outputText id="t1" value="#{msgs.restore_modules_select_modules}" />          
										 </f:facet>
											<h:selectBooleanCheckbox value="" valueChangeListener="#{manageModulesPage.selectedRestoreModule}"/>
					 						<h:outputText id="modname" value="#{aml.module.title}"/>
										</h:column>
										<h:column>
										<f:facet name="header">
																   <h:outputText id="t2" value="#{msgs.restore_modules_date_deactivated}" />          
										 </f:facet>
											<h:outputText id="deactivateDate" value="#{aml.dateArchived}"><f:convertDateTime pattern="yyyy-MMM-d hh:mm a"/></h:outputText>
										</h:column>			
			 						</h:dataTable>
			  						<h:outputText value="#{msgs.restore_modules_no_archive_modules}" rendered="#{manageModulesPage.shouldRenderEmptyList}" />
			  					</td>
            				</tr>
            				<tr>
              					<td height="30" colspan="2">
			    					<div align="center">
										<h:commandLink id="restoreModule"  action="#{manageModulesPage.restoreModules}" rendered="#{(manageModulesPage.shouldRenderEmptyList == manageModulesPage.falseBool)}" >
											<h:graphicImage id="restoreModImg" value="#{msgs.im_restore}" styleClass="BottomImgSpace" 
											onmouseover="this.src = '#{msgs.im_restore_over}'" 
											onmouseout="this.src = '#{msgs.im_restore}'" 
											onmousedown="this.src = '#{msgs.im_restore_down}'" 
						   				  	onmouseup="this.src = '#{msgs.im_restore_over}'"/>
                						</h:commandLink>				

									<h:graphicImage id="restoreModImg1" value="#{msgs.im_restore}" styleClass="BottomImgSpace"  rendered="#{manageModulesPage.shouldRenderEmptyList}" 	onmouseover="this.src = '#{msgs.im_restore_over}'" 
											onmouseout="this.src = '#{msgs.im_restore}'" 
											onmousedown="this.src = '#{msgs.im_restore_down}'" 
						   				  	onmouseup="this.src = '#{msgs.im_restore_over}'"/>
						   				  	
										<h:commandLink id="cancel"  action="#{manageModulesPage.cancelRestoreModules}" >
											<h:graphicImage id="cancelModImg" value="#{msgs.im_cancel}" styleClass="BottomImgSpace" 
											onmouseover="this.src = '#{msgs.im_cancel_over}'" 
											onmouseout="this.src = '#{msgs.im_cancel}'" 
											onmousedown="this.src = '#{msgs.im_cancel_down}'" 
						   				  	onmouseup="this.src = '#{msgs.im_cancel_over}'"/>
                						</h:commandLink>		
				  					</div>
				  				</td>
            				</tr>
							<tr><td height="20" class="maintabledata5" colspan="2">&nbsp;</td> </tr>
          				</table>
		  			</td>
		  		</tr>
		  	</table> 
		  	<p>&nbsp;</p>

		</td>
  	</tr>
</table>
</h:form>
<!-- This Ends the Main Text Area -->

</body>
</f:view>
</html>	  