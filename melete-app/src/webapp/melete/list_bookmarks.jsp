<%@ page import="org.etudes.tool.melete.BookmarkPage,javax.faces.application.*,javax.faces.context.*,javax.faces.component.*, java.util.ResourceBundle"%>

<!--
 ***********************************************************************************
 * $URL: https://source.sakaiproject.org/contrib/etudes/melete/trunk/melete-app/src/webapp/melete/list_bookmarks.jsp $
 * $Id: list_bookmarks.jsp 59695 2009-04-06 23:00:53Z mallika@etudes.org $  
 ***********************************************************************************
 *
 * Copyright (c) 2010 Etudes, Inc.
 *
 * Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 **********************************************************************************
-->
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="description" content="ETUDES-NG Course Management System, Powered by Sakai">
<meta name="keywords" content="ETUDES-NG course management system, e-learning">


<title>Melete - List of bookmarks</title>
<script type="text/javascript" language="JavaScript" src="js/headscripts.js"></script>
<script type="text/javascript" language="javascript" src="js/sharedscripts.js"></script>
</head>

<f:view>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0" onload="setMainFrameHeight('<h:outputText value="#{meleteSiteAndUserInfo.winEncodeName}"/>');" >
 <h:form id="ManageBookmarksForm">
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
		  <div class="meletePortletToolBarMessage"><img src="images/manage_content.png" alt="" width="16" height="16" align="absbottom" border="0"><h:outputText value="#{msgs.list_bookmarks_headtitle}" /></div>				
				</td>
        </tr>
        </table>
        <table width="100%"  border="0" cellpadding="3" cellspacing="0" bordercolor="#EAEAEA"  style="border-collapse: collapse">
        <tr>
          <td class="maintabledata3">
          	<h:messages showDetail="true" showSummary="false" infoClass="BlueClass" errorClass="RedClass"/>
		   
          <h:panelGrid width="100%" border="0" cellpadding="4" cellspacing="0"  style="border-collapse: collapse border-color: #F5F5F5" >											
		   <h:column>		                    	
		   
			 <h:dataTable id="table"  value="#{bookmarkPage.bmList}"  var="bookmark"  border="0" headerClass="tableheader2" columnClasses="col25,col45,col15,col15" rowClasses="row1,row2"  width="100%" summary="#{msgs.list_resources_summary}">
				  <h:column>
					   <f:facet name="header">
							<h:panelGroup>
							  <h:outputText value="#{msgs.list_bookmarks_title}" />
							 </h:panelGroup> 
						 </f:facet>
					 <h:outputText id="emp_space" value="     "  styleClass="ExtraPaddingClass" />	
					 <h:commandLink id="viewSection"  actionListener="#{bookmarkPage.viewSection}" action="#{bookmarkPage.redirectViewSection}">
					   <f:param name="sectionId" value="#{bookmark.sectionId}" />
					   <h:outputText id="bmtitle" value="#{bookmark.title}"/>
					 </h:commandLink>
				    </h:column>
				    <h:column>
				    <f:facet name="header">
							 <h:outputText id="t2" value="#{msgs.list_bookmarks_notes}" />
					 </f:facet>
					 <h:outputText id="bmnotes" value="#{bookmark.notes}"/>					 
					</h:column>
					<h:column>
					  <f:facet name="header">
							 <h:outputText id="t3" value="#{msgs.list_bookmarks_actions}" />
					 </f:facet>
					 <h:outputLink id="editBookmarkLink" value="list_bookmarks" onclick="OpenBookmarkWindow(#{bookmark.sectionId},'Melete Bookmark Window');">
		    	       <f:param id="sectionId" name="sectionId" value="#{bookmark.sectionId}" />
						<h:graphicImage id="delgif0" alt="" value="images/delete.gif" styleClass="AuthImgClass" />
							<h:outputText id="emp_space-20" value=" " />
				    		<h:outputText id="deltext0" value="#{msgs.list_bookmarks_edit}"  />
					 </h:outputLink>						
					</h:column> 
				    <h:column>
					  <h:commandLink id="deleteaction" actionListener="#{bookmarkPage.deleteAction}"  action="#{bookmarkPage.redirectDeleteLink}" immediate="true" >
				    		<f:param name="bookmarkId" value="#{bookmark.bookmarkId}" />
				    		<f:param name="bookmarkTitle" value="#{bookmark.title}" />
				    		<h:graphicImage id="delgif" alt="" value="images/delete.gif" styleClass="AuthImgClass" />
							<h:outputText id="emp_space-2" value=" " />
				    		<h:outputText id="deltext" value="#{msgs.list_bookmarks_delete}"  />
					 </h:commandLink>	
				    </h:column>
                   </h:dataTable>
	          </h:column>					
         </h:panelGrid>
         </td>
         </tr>
         </table>
         </td>
         </tr>
         </table>
         </h:form>
         </body>
    </f:view>
 </html>        