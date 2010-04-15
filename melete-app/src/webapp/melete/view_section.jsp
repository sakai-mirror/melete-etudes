<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
 ***********************************************************************************
 * $URL$
 * $Id$  
 ***********************************************************************************
 *
 * Copyright (c) 2008, 2009,2010 Etudes, Inc.
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
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<f:view>
<sakai:view title="Modules: Student View" toolCssHref="rtbc004.css">
<%@include file="accesscheck.jsp" %>
<script type="text/javascript" language="javascript" src="js/sharedscripts.js"></script>

<h:form id="viewsectionform"> 
	<f:subview id="top">
	  <jsp:include page="topnavbar.jsp"/> 
	</f:subview>  
<p></p>   
<table  border="0" cellpadding="3" cellspacing="0" bordercolor="#EAEAEA" width="100%">
<tr>
<td colspan="2" align="center">
<f:subview id="topmod">
 	<jsp:include page="view_navigate.jsp"/>
</f:subview>

	<h:panelGroup id="bcsecpgroup" binding="#{viewSectionsPage.secpgroup}"/>
</td>
<tr>
<td colspan="2" align="right">
<h:commandLink id="myBookmarksLink" action="#{viewSectionsPage.gotoMyBookmarks}">
 <h:outputText id="mybks" value="#{msgs.my_bookmarks}" />									
</h:commandLink>
<h:outputText value="|"/> 														
  <h:outputLink id="bookmarkSectionLink" value="view_section" onclick="OpenBookmarkWindow(#{viewSectionsPage.section.sectionId},'#{viewSectionsPage.section.title}','Melete Bookmark Window');">
		    	<f:param id="sectionId" name="sectionId" value="#{viewSectionsPage.section.sectionId}" />
	  			<f:param id="sectionTitle" name="sectionTitle" value="#{viewSectionsPage.section.title}" />
	  			<h:graphicImage id="bul_gif" value="images/icon_bookmark.gif" />
				      <h:outputText id="bookmarktext" value="#{msgs.bookmark_text}" > </h:outputText>
 	</h:outputLink>				  
</td>
</tr>
<tr>
<td colspan="2" align="left">
     <h:outputText id="mod_seq" value="#{viewSectionsPage.moduleSeqNo}. " styleClass="bold style6" rendered="#{viewSectionsPage.autonumber}"/>
	 <h:outputText id="modtitle" value="#{viewSectionsPage.module.title}" styleClass="bold style6" />
</td>
</tr>    

<tr>
<td colspan="2" align="left">
<h:panelGrid id="sectionContentGrid" columns="1" width="100%" border="0" rendered="#{viewSectionsPage.section != null}">
<h:column>
 	<h:outputText id="sec_seq" value="#{viewSectionsPage.sectionDisplaySequence}. " styleClass="bold style7" rendered="#{viewSectionsPage.autonumber}"/>
 	<h:outputText id="title" value="#{viewSectionsPage.section.title}" styleClass="bold style7"></h:outputText>     
</h:column>
<h:column>
	<h:outputText value="#{msgs.view_section_student_instructions} " rendered="#{((viewSectionsPage.section.instr != viewSectionsPage.nullString)&&(viewSectionsPage.section.instr != viewSectionsPage.emptyString))}" styleClass="italics"/>
	<h:outputText id="instr" value="#{viewSectionsPage.section.instr}" rendered="#{((viewSectionsPage.section.instr != viewSectionsPage.nullString)&&(viewSectionsPage.section.instr != viewSectionsPage.emptyString))}"/>
</h:column>
<h:column rendered="#{viewSectionsPage.section.contentType != viewSectionsPage.nullString}">
	<h:inputHidden id="contentType" value="#{viewSectionsPage.section.contentType}"/>
	<h:inputHidden id="openWindow" value="#{viewSectionsPage.section.openWindow}"/>
	<h:outputText escape="false" value="<a target='new_window' href='" rendered="#{((viewSectionsPage.section.contentType == viewSectionsPage.typeLink || viewSectionsPage.section.contentType == viewSectionsPage.typeUpload || viewSectionsPage.section.contentType == viewSectionsPage.typeLTI)&&(viewSectionsPage.contentLink != viewSectionsPage.nullString)&&(viewSectionsPage.section.openWindow == true))}"/>
       <h:outputText value="#{viewSectionsPage.contentLink}" rendered="#{((viewSectionsPage.section.contentType == viewSectionsPage.typeLink || viewSectionsPage.section.contentType == viewSectionsPage.typeUpload || viewSectionsPage.section.contentType == viewSectionsPage.typeLTI)&&(viewSectionsPage.contentLink != viewSectionsPage.nullString)&&(viewSectionsPage.section.openWindow == true))}"/>
       <h:outputText escape="false" value="'>#{viewSectionsPage.linkName}</a>" rendered="#{((viewSectionsPage.section.contentType == viewSectionsPage.typeLink || viewSectionsPage.section.contentType == viewSectionsPage.typeUpload || viewSectionsPage.section.contentType == viewSectionsPage.typeLTI)&&(viewSectionsPage.contentLink != viewSectionsPage.nullString)&&(viewSectionsPage.section.openWindow == true))}"/>
    
    <h:outputText id="contentFrame" value="<iframe id=\"iframe1\" src=\"#{viewSectionsPage.content}\" style=\"visibility:visible\" scrolling= \"auto\" width=\"100%\" height=\"700\" border=\"0\" frameborder= \"0\"></iframe>" rendered="#{((viewSectionsPage.section.contentType ==viewSectionsPage.typeLink)&&(viewSectionsPage.linkName !=
    viewSectionsPage.nullString)&&(viewSectionsPage.section.openWindow == false))}" escape="false" />
    
 	<h:outputText id="contentUploadFrame" value="<iframe id=\"iframe2\" src=\"#{viewSectionsPage.contentLink}\" style=\"visibility:visible\" scrolling= \"auto\" width=\"100%\" height=\"700\"
    border=\"0\" frameborder= \"0\"></iframe>" rendered="#{((viewSectionsPage.section.contentType == viewSectionsPage.typeEditor)&&(viewSectionsPage.content != viewSectionsPage.nullString))||((viewSectionsPage.section.contentType ==viewSectionsPage.typeUpload)&&(viewSectionsPage.section.openWindow == false))}" escape="false" />
	
	<h:outputText id="contentLTI" value="#{viewSectionsPage.contentLTI}" 
              rendered="#{((viewSectionsPage.section.contentType == viewSectionsPage.typeLTI)&&(viewSectionsPage.section.openWindow == false))}" escape="false" />	
</h:column>
</h:panelGrid>

</td>

</tr>    
<tr>
<td colspan="2" align="center">
					<f:subview id="bottommod">
						<jsp:include page="view_navigate.jsp"/>
					</f:subview>
</td>
</tr>
</table>
</td>
</tr>
<tr><td>
<table width="100%" height="20" border="0" cellpadding="3" cellspacing="0" >
   	<tr>
	 <td align="center" class="meleteLicenseMsg center"><B>
  			<jsp:include page="license_info.jsp"/>      
         </B></td></tr>
	    </table>
</h:form>

</sakai:view>
</f:view>

