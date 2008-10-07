<!--  Copyright (c) 2008 Etudes, Inc. -->
 
<!--  Licensed under the Apache License, Version 2.0 (the "License"); -->
<!--   you may not use this file except in compliance with the License.-->
<!--   You may obtain a copy of the License at -->
  
<!--   http://www.apache.org/licenses/LICENSE-2.0 -->
  
<!--   Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project -->
  
<!--   Licensed under the Apache License, Version 2.0 (the "License"); you -->
<!--   may not use this file except in compliance with the License. You may -->
<!--   obtain a copy of the License at -->
  
<!--   http://www.apache.org/licenses/LICENSE-2.0 -->
<!--  Unless required by applicable law or agreed to in writing, software -->
<!--  distributed under the License is distributed on an "AS IS" BASIS, -->
<!--  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or -->
<!--  implied. See the License for the specific language governing -->
<!--  permissions and limitations under the License. -->
<html>
<head>
<link rel="stylesheet" href="rtbc004.css" type="text/css">
<title>Melete - Modules: Student View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!--  Copyright (c) 2008 Etudes, Inc. -->
 
<!--  Licensed under the Apache License, Version 2.0 (the "License"); -->
<!--   you may not use this file except in compliance with the License.-->
<!--   You may obtain a copy of the License at -->
  
<!--   http://www.apache.org/licenses/LICENSE-2.0 -->
  
<!--   Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project -->
  
<!--   Licensed under the Apache License, Version 2.0 (the "License"); you -->
<!--   may not use this file except in compliance with the License. You may -->
<!--   obtain a copy of the License at -->
  
<!--   http://www.apache.org/licenses/LICENSE-2.0 -->
<!--  Unless required by applicable law or agreed to in writing, software -->
<!--  distributed under the License is distributed on an "AS IS" BASIS, -->
<!--  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or -->
<!--  implied. See the License for the specific language governing -->
<!--  permissions and limitations under the License. -->
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<script type="text/javascript" language="JavaScript" src="js/headscripts.js"></script>
<script type="text/javascript" language="javascript">
function OpenPrintWindow(print_id, windowName)
{
	
  var _info = navigator.userAgent;
  var _ie = (_info.indexOf("MSIE") > 0 && _info.indexOf("Win") > 0 && _info.indexOf("Windows 3.1") < 0);
	var windowDefaults = "status=no, menubar=no, location=no, scrollbars=yes, resizeable=yes, width=700, height=700, left=20, top=20";
	var newWindow;
	if(!_ie) newWindow = window.open('print_module.jsf?printModuleId='+print_id,windowName,windowDefaults);
	else newWindow = window.open('print_module.jsf?printModuleId='+print_id,null,windowDefaults);
if (window.focus) { newWindow.focus(); } ; // force the window to the front if the browser supports it
return newWindow;

}
</script>
</head>
<f:view>
<body onLoad="setMainFrameHeight('<h:outputText value="#{meleteSiteAndUserInfo.winEncodeName}"/>');">
<h:form id="listmodulesStudentform">
<table border="0" height="350" cellpadding="0" cellspacing="0" class ="table3">
<tr>
		<td valign="top"> &nbsp;		
		</td>
	<td width="1962" valign="top">
<!--Page Content-->
<table width="100%" border="1" cellpadding="3" cellspacing="0" bordercolor="#EAEAEA" style="border-collapse: collapse">
<tr>
					<td colspan="2">
						<f:subview id="top">
							<jsp:include page="topnavbar.jsp"/> 
						</f:subview>
					 </td>
			</tr>
		
<tr>
<td colspan="2" class="maintabledata3">
<h:messages showDetail="true" showSummary="false"/>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#EAEAEA" width="100%" id="AutoNumber1" >
        <tr class="maintabledata5">
          <th  height="20" width="45%" valign="left" class="tableheader2">
          <h:commandLink id="expandAllAction"  action="#{listModulesPage.expandAllAction}" immediate="true">
     	    <h:graphicImage id="exp_all_gif" alt="#{msgs.list_modules_stud_expand_all}" title="#{msgs.list_modules_stud_expand_all}" value="images/expand-collapse.gif"   rendered="#{listModulesPage.expandAllFlag != listModulesPage.trueFlag}" styleClass="ExpClass"/>
          </h:commandLink>
          <h:commandLink id="collapseAllAction"  action="#{listModulesPage.collapseAllAction}" immediate="true">
            <h:graphicImage id="col_all_gif" alt="#{msgs.list_modules_stud_collapse_all}" title="#{msgs.list_modules_stud_collapse_all}" value="images/collapse-expand.gif"   rendered="#{listModulesPage.expandAllFlag == listModulesPage.trueFlag}" styleClass="ExpClass"/>
          </h:commandLink>
          </th>
          <th  height="20"  width="25%" valign="middle" class="leftheader"><h:outputText value="#{msgs.list_modules_stud_start_date}" /></th>
          <th  height="20"   width="25%" valign="middle" class="leftheader"><h:outputText value="#{msgs.list_modules_stud_end_date}" /></th>
          <th  height="20"  width="5%"  class="leftheader"></th>       
        </tr>
	<tr> <td colspan="4" valign="top">
 <h:dataTable id="table" 
                  value="#{listModulesPage.modDataModel}"
                  var="mdbean"   rowClasses="row1,row2" 
              columnClasses="titleWid,dateWid1,dateWid2,ModCheckClass"
                   border="0" width="100%" 
                   binding="#{listModulesPage.modTable}">
      <h:column>                                 
    
    <h:commandLink id="viewSections" action="#{listModulesPage.showSections}" immediate="true">
        <h:graphicImage id="exp_gif" value="images/expand.gif" rendered="#{((mdbean.moduleId != listModulesPage.showModuleId)&&(mdbean.sectionBeans != listModulesPage.nullList)&&(listModulesPage.expandAllFlag != listModulesPage.trueFlag))}" styleClass="ExpClass"/>
         <h:inputHidden id="moduleShowId" value="#{mdbean.moduleId}"/>
      </h:commandLink>
     <h:commandLink id="hideSections" action="#{listModulesPage.hideSections}" immediate="true">
        <h:graphicImage id="col_gif" value="images/collapse.gif" rendered="#{(((mdbean.moduleId == listModulesPage.showModuleId)&&(mdbean.sectionBeans != listModulesPage.nullList))||((listModulesPage.expandAllFlag == listModulesPage.trueFlag)&&(mdbean.sectionBeans != listModulesPage.nullList)))}" styleClass="ExpClass"/>
         <h:inputHidden id="moduleHideId" value="#{mdbean.moduleId}"/>
      </h:commandLink> 
      <h:outputText id="mod_seq" value="#{mdbean.cmod.seqNo}. " rendered="#{listModulesPage.autonumber}"/>
         <h:commandLink id="viewModule"  actionListener="#{listModulesPage.viewModule}" action="#{listModulesPage.redirectToViewModule}" rendered="#{mdbean.visibleFlag == listModulesPage.trueFlag}" immediate="true">
              <f:param name="modidx" value="#{listModulesPage.modTable.rowIndex}" />
                  <h:outputText id="title"
                           value="#{mdbean.module.title}">
              </h:outputText>             
          </h:commandLink>
          <h:outputText id="titleTxt2" value="#{mdbean.module.title}" rendered="#{mdbean.visibleFlag != listModulesPage.trueFlag}"/>         
        
        <h:dataTable id="tablesec" rendered="#{((mdbean.moduleId == listModulesPage.showModuleId)||(listModulesPage.expandAllFlag == listModulesPage.trueFlag))}"
                  value="#{mdbean.sectionBeans}"
                  var="section" rowClasses="#{mdbean.rowClasses}" columnClasses="SectionClass" width="95%" binding="#{listModulesPage.secTable}">
  ��                <h:column> 
              <h:graphicImage id="bul_gif" value="images/bullet_black.gif" rendered="#{!listModulesPage.autonumber}"/>
             
	       <h:outputText id="sec_seq" value="#{section.displaySequence}. " rendered="#{listModulesPage.autonumber}"/>    
             <h:commandLink id="viewSectionEditor"   actionListener="#{listModulesPage.viewSection}" action="#{listModulesPage.redirectToViewSection}"  rendered="#{((section.section.contentType != listModulesPage.isNull && section.section.contentType == listModulesPage.typeLink)&&(mdbean.visibleFlag == listModulesPage.trueFlag))}" immediate="true">
               <f:param name="modidx" value="#{listModulesPage.modTable.rowIndex}" />
               <f:param name="secidx" value="#{listModulesPage.secTable.rowIndex}" />
 
               <h:outputText id="sectitleEditor" 
                           value="#{section.section.title}">
               </h:outputText>
             </h:commandLink>
             <h:commandLink id="viewSectionLink"   actionListener="#{listModulesPage.viewSection}" action="#{listModulesPage.redirectToViewSectionLink}"  rendered="#{((section.section.contentType != listModulesPage.isNull && section.section.contentType != listModulesPage.typeLink)&&(mdbean.visibleFlag == listModulesPage.trueFlag))}" immediate="true">
                <f:param name="modidx" value="#{listModulesPage.modTable.rowIndex}" />
               <f:param name="secidx" value="#{listModulesPage.secTable.rowIndex}" />
            
               <h:outputText id="sectitleLink" 
                           value="#{section.section.title}">
               </h:outputText>
             </h:commandLink>             
             <h:outputText id="sectitleEditorTxt2" value="#{section.section.title}" rendered="#{mdbean.visibleFlag != listModulesPage.trueFlag}"/>
             </h:column>
          </h:dataTable>
          </h:column>
           <h:column>
            <h:outputText id="startDate0" 
                           value="-"    rendered="#{(mdbean.moduleShdate.startDate == listModulesPage.nullDate)}">
            </h:outputText>
                <h:outputText id="startDate" 
                           value="#{mdbean.moduleShdate.startDate}" rendered="#{(mdbean.moduleShdate.startDate != listModulesPage.nullDate)}">
              <f:convertDateTime pattern="yyyy-MMM-d hh:mm a"/>
            </h:outputText>
  ��      </h:column>
      <h:column>
       <h:outputText id="endDate0" 
                           value="-"    rendered="#{(mdbean.moduleShdate.endDate == listModulesPage.nullDate)}">
            </h:outputText>
               <h:outputText id="endDate"
                           value="#{mdbean.moduleShdate.endDate}" rendered="#{(mdbean.moduleShdate.endDate != listModulesPage.nullDate)}">
               <f:convertDateTime pattern="yyyy-MMM-d hh:mm a"/>
            </h:outputText>
 ��      </h:column>
		 <h:column rendered="#{listModulesPage.printable}">  
         <h:outputText id="emp_space5" value="  " styleClass="ExtraPaddingClass" />
	     <h:graphicImage id="printImgLink" value="images/printer.png"  alt="#{msgs.list_auth_modules_alt_print}" title="#{msgs.list_auth_modules_alt_print}" styleClass="AuthImgClass" onclick="OpenPrintWindow(#{listModulesPage.printModuleId},'Melete Print Window');" rendered="#{mdbean.visibleFlag}"/>
	    </h:column>  
      </h:dataTable>   
      <h:outputText id="nomodstext" value="#{msgs.no_modules}" rendered="#{listModulesPage.nomodsFlag}" style="text-align:left"/>    
	  </td></tr>
	  <tr>
         <td  height="20" colspan="4" class="maintabledata5">&nbsp;   </td>
        </tr></table>
 </td>
 </tr>
 </table>
 <!--End Content-->
</td>
</tr>
</table>
</body>
  </h:form>
</f:view>
</html>