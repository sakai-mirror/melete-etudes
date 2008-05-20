<html>
<head>
<link rel="stylesheet" href="rtbc004.css" type="text/css">
<title>Melete - Modules: Student View</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<script type="text/javascript" language="JavaScript" src="js/headscripts.js"></script>
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
<td class="maintabledata3">
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
          <th  height="20"  width="27%" valign="middle" class="leftheader"><h:outputText value="#{msgs.list_modules_stud_start_date}" /></th>
          <th  height="20"   width="28%" valign="middle" class="leftheader"><h:outputText value="#{msgs.list_modules_stud_end_date}" /></th>
        </tr>
	<tr> <td colspan="3" valign="top">
 <h:dataTable id="table" 
                  value="#{listModulesPage.moduleDatePrivBeans}"
                  var="mdbean"   rowClasses="row1,row2" 
              columnClasses="titleWid,dateWid1,dateWid2"
                   border="0" width="100%" >
      <h:column>
                                  
    <h:commandLink id="viewSections" action="#{listModulesPage.showSections}">
        <h:graphicImage id="exp_gif" value="images/expand.gif" rendered="#{((mdbean.moduleId != listModulesPage.showModuleId)&&(mdbean.sectionBeans != listModulesPage.nullList)&&(listModulesPage.expandAllFlag != listModulesPage.trueFlag))}" styleClass="ExpClass"/>
         <h:inputHidden id="moduleShowId" value="#{mdbean.moduleId}"/>
      </h:commandLink>
     <h:commandLink id="hideSections" action="#{listModulesPage.hideSections}">
        <h:graphicImage id="col_gif" value="images/collapse.gif" rendered="#{(((mdbean.moduleId == listModulesPage.showModuleId)&&(mdbean.sectionBeans != listModulesPage.nullList))||((listModulesPage.expandAllFlag == listModulesPage.trueFlag)&&(mdbean.sectionBeans != listModulesPage.nullList)))}" styleClass="ExpClass"/>
         <h:inputHidden id="moduleHideId" value="#{mdbean.moduleId}"/>
      </h:commandLink> 
      <h:outputText id="emp_spacemod" value=" "/>
         <h:commandLink id="viewModule"  actionListener="#{listModulesPage.viewModule}" action="#{listModulesPage.redirectToViewModule}" rendered="#{(((mdbean.startDate == listModulesPage.nullDate)||(mdbean.startDate <= listModulesPage.currentTimestamp))&&((mdbean.endDate == listModulesPage.nullDate)||(mdbean.endDate >= listModulesPage.currentTimestamp)))}">

                  <h:outputText id="title"
                           value="#{mdbean.truncTitle}">
              </h:outputText>             
          </h:commandLink>
          <h:outputText id="titleTxt2" value="#{mdbean.truncTitle}" rendered="#{((mdbean.startDate > listModulesPage.currentTimestamp)||(mdbean.endDate < listModulesPage.currentTimestamp))}"/>         
        
        <h:dataTable id="tablesec" rendered="#{((mdbean.moduleId == listModulesPage.showModuleId)||(listModulesPage.expandAllFlag == listModulesPage.trueFlag))}"
                  value="#{mdbean.sectionBeans}"
                  var="section" rowClasses="#{mdbean.rowClasses}" columnClasses="SectionClass" width="75%">
  ��                <h:column> 
			  <h:outputText id="emp_space" value=" "/>
              <h:graphicImage id="bul_gif" value="images/bullet_black.gif"/>
              <h:outputText id="emp_space2" value=" "/>
			  <h:outputText id="emp_space" value=" "/>
             <h:commandLink id="viewSectionEditor"   actionListener="#{listModulesPage.viewSection}" action="#{listModulesPage.redirectToViewSection}"  rendered="#{((section.section.contentType != listModulesPage.isNull && section.section.contentType == listModulesPage.typeLink)&&(((mdbean.startDate == listModulesPage.nullDate)||(mdbean.startDate <= listModulesPage.currentTimestamp))&&((mdbean.endDate == listModulesPage.nullDate)||(mdbean.endDate >= listModulesPage.currentTimestamp))))}">
               <h:outputText id="sectitleEditor" 
                           value="#{section.truncTitle}">
               </h:outputText>
             </h:commandLink>
             <h:commandLink id="viewSectionLink"   actionListener="#{listModulesPage.viewSection}" action="#{listModulesPage.redirectToViewSectionLink}"  rendered="#{((section.section.contentType != listModulesPage.isNull && section.section.contentType != listModulesPage.typeLink)&&(((mdbean.startDate == listModulesPage.nullDate)||(mdbean.startDate <= listModulesPage.currentTimestamp))&&((mdbean.endDate == listModulesPage.nullDate)||(mdbean.endDate >= listModulesPage.currentTimestamp))))}">
               <h:outputText id="sectitleLink" 
                           value="#{section.truncTitle}">
               </h:outputText>
             </h:commandLink>             
             <h:outputText id="sectitleEditorTxt2" value="#{section.truncTitle}" rendered="#{((mdbean.startDate > listModulesPage.currentTimestamp)||(mdbean.endDate < listModulesPage.currentTimestamp))}"/>
             </h:column>
          </h:dataTable>
          </h:column>
           <h:column>
            <h:outputText id="startDate0" 
                           value="-"    rendered="#{(mdbean.startDate == listModulesPage.nullDate)}">
            </h:outputText>
                <h:outputText id="startDate" 
                           value="#{mdbean.startDate}" rendered="#{(mdbean.startDate != listModulesPage.nullDate)}">
              <f:convertDateTime pattern="yyyy-MMM-d hh:mm a"/>
            </h:outputText>
  ��      </h:column>
      <h:column>
       <h:outputText id="endDate0" 
                           value="-"    rendered="#{(mdbean.endDate == listModulesPage.nullDate)}">
            </h:outputText>
               <h:outputText id="endDate"
                           value="#{mdbean.endDate}" rendered="#{(mdbean.endDate != listModulesPage.nullDate)}">
               <f:convertDateTime pattern="yyyy-MMM-d hh:mm a"/>
            </h:outputText>
 ��      </h:column>
      </h:dataTable>   
      <h:messages showDetail="true" showSummary="false" rendered="#{listModulesPage.nomodsFlag == listModulesPage.trueFlag}" style="text-align:left"/>
	  </td></tr>
	  <tr>
         <td  height="20" colspan="3" class="maintabledata5">&nbsp;   </td>
        </tr></table>
 </td>
 </tr>
 </table>
 <!--End Content-->

 <p><h:outputLink styleClass="style3" value="#top" rendered="#{listModulesPage.nomodsFlag != listModulesPage.trueFlag}">  <f:verbatim><h:outputText value="#{msgs.list_modules_stud_back_to_top}" /></f:verbatim> </h:outputLink>
 </p>
</td>
</tr>
</table>
</body>
  </h:form>
</f:view>
</html>