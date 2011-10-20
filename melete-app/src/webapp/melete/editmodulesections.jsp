<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--
 ***********************************************************************************
 * $URL$
 * $Id$  
 ***********************************************************************************
 *
 * Copyright (c) 2008,2009,2010,2011 Etudes, Inc.
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
--%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t" %>
<%@ taglib uri="http://javascript4jsf.dev.java.net/" prefix="j4j" %>

<f:view>
<sakai:view title="Modules: Edit Module Sections" toolCssHref="/etudes-melete-tool/rtbc004.css">
<%@include file="accesscheck.jsp" %>
<script type="text/javascript" language="javascript" src="/etudes-melete-tool/js/sharedscripts.js"></script>

<%@ page import="org.sakaiproject.util.ResourceLoader, javax.faces.application.FacesMessage,org.etudes.tool.melete.AddResourcesPage, org.etudes.tool.melete.AuthorPreferencePage, org.etudes.tool.melete.EditSectionPage"%> 

<% 
	ResourceLoader bundle = new ResourceLoader("org.etudes.tool.melete.bundle.Messages");
	String mensaje=bundle.getString("editmodulesections_uploading");

	final javax.faces.context.FacesContext facesContext = javax.faces.context.FacesContext.getCurrentInstance();
	final EditSectionPage eSectionPage = (EditSectionPage)facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "editSectionPage");
	if(eSectionPage.getSection() != null && eSectionPage.getSection().getSectionId() != null)
	{
		request.setAttribute("attr_sId",eSectionPage.getSection().getSectionId().toString());	
	}
  final AuthorPreferencePage authorPreferencePage = (AuthorPreferencePage)facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "authorPreferences");
	
%>

<script type="text/javascript" language="javascript1.2">

function saveEditor()
{
	var result = true;
	var sferyxdisplay = document.getElementById("EditSectionForm:contentEditorView:sferyxDisplay");
	if ((sferyxdisplay != undefined )&&(document.htmleditor!=undefined && document.htmleditor!= null))
	{	  	
		// document.htmleditor.saveToDefaultLocation();  
		document.htmleditor.addAdditionalDynamicParameter('mode',document.getElementById("EditSectionForm:mode").value);
        document.htmleditor.addAdditionalDynamicParameter('sId',document.getElementById("EditSectionForm:sId").value);
        if(document.getElementById("EditSectionForm:rId") != undefined || document.getElementById("EditSectionForm:rId") != null)
      	  document.htmleditor.addAdditionalDynamicParameter('resourceId',document.getElementById("EditSectionForm:rId").value);
       document.htmleditor.addAdditionalDynamicParameter('uId',document.getElementById("EditSectionForm:uId").value);		  
		
		result = document.htmleditor.uploadMultipartContent(true);			    	
	}	
	return result;	
}

</script>

      <!-- This Begins the Main Text Area -->
	<h:form id="EditSectionForm" enctype="multipart/form-data" onsubmit="if (saveEditor()){ return true;}else {return false;}"> 	
	<j4j:param name="sectionId" value="#{editSectionPage.editId}" method="get" />
			  <h:inputHidden id="formName" value="EditSectionForm"/>  
			  <h:inputHidden id="mode" value="Edit"/>
			  <h:inputHidden id="sId" value="#{editSectionPage.section.sectionId}"/>
			  <h:inputHidden id="rId" value="#{editSectionPage.meleteResource.resourceId}" rendered="#{editSectionPage.meleteResource !=null}"/>
			  <h:inputHidden id="uId" value="#{editSectionPage.currUserId}"/>
		<!-- top nav bar -->
		<f:subview id="top">
			<jsp:include page="topnavbar.jsp"/> 
		</f:subview>
		
		<t:saveState id="worksection" value="#{editSectionPage.section}" />
		<t:saveState id="workLicense" value="#{licensePage.licenseCodes}" />
		<t:saveState id="workId" value="#{editSectionPage.editId}" />
		
		<div class="meletePortletToolBarMessage"><img src="/etudes-melete-tool/images/document_edit.gif" alt="" width="16" height="16" align="absbottom"><h:outputText id="captionText" value="#{msgs.editmodulesections_editing_section}" /> </div>
		<h:messages id="editsectionerror"  layout="table" showDetail="true" showSummary="false" infoClass="BlueClass" errorClass="RedClass"/>
        <div id="errMsg1" style="color:red"><p> </p></div>
        <table class="maintableCollapseWithBorder">
     	   <tr>
            <td class="maintabledata3">
				  <table class="maintableCollapseWithNoBorder">
                   <!-- table header -->
                   <tr>
			            <td width="100%" colspan="2" height="20" class="maintabledata2"> 
				            <table  width="100%"> 
			                   <tr >
					            <td width="70%" >  
					            	<h:commandButton id="editPrevButton" actionListener="#{editSectionPage.editPrevSectionListener}" disabled="#{!editSectionPage.hasPrev}" value="#{msgs.editmodulesections_edit_prev}" accesskey="#{msgs.prev_access}" title="#{msgs.im_prev_text}" styleClass="BottomImgPrev">
					            	    <f:attribute name="otherId" value="#{editSectionPage.editId}" />					    
					       			</h:commandButton>      	   
									<h:commandButton id="TOCButton" action="#{editSectionPage.goTOC}" value="#{msgs.editmodulesections_TOC}" accesskey="#{msgs.toc_access}" title="#{msgs.im_toc_text}" styleClass="BottomImgTOC"/>
					       			<h:commandButton id="editNextButton" actionListener="#{editSectionPage.editNextSectionListener}" disabled="#{!editSectionPage.hasNext}" value="#{msgs.editmodulesections_edit_next}" accesskey="#{msgs.next_access}" title="#{msgs.im_next_text}" styleClass="BottomImgNext">
					       				<f:attribute name="otherId" value="#{editSectionPage.editId}" />					       	
					       			</h:commandButton>
						     	
						            <h:outputText id="text4_3" value="  " styleClass="ExtraPaddingClass"/>	
									<h:commandButton id="editAddNewButton" actionListener="#{editSectionPage.saveAndAddAnotherSection}" value="#{msgs.editmodulesections_add_new}" accesskey="#{msgs.add_access}" title="#{msgs.im_save_text}" styleClass="BottomImgAdd">
										<f:attribute name="otherId" value="#{editSectionPage.editId}" />										 
									</h:commandButton>  			
						        </td>
						        <td class="right" width="30%" >    
									 <h:commandButton id="bookmarkSectionLink" action="#{editSectionPage.saveAndAddBookmark}" value="#{msgs.bookmark_text}" 
										 onclick="var w=OpenBookmarkWindow(#{editSectionPage.section.sectionId},document.getElementById('EditSectionForm:title').value,'','','','Melete Bookmark Window');"
										  accesskey="#{msgs.bookmark_access}" title="#{msgs.im_bmrk_text}" styleClass="BottomImgBookmarkIt">
									</h:commandButton>
						     		<h:commandButton id="myBookmarksLink" action="#{editSectionPage.gotoMyBookmarks}" value="#{msgs.my_bookmarks}" accesskey="#{msgs.mybookmarks_access}" title="#{msgs.im_mybmrks_text}" styleClass="BottomImgMyBookmarks"/>
								</td>
					          </tr>
				          </table>
			          </td>
			          </tr>		
			          <tr>
			          	<td colspan="2" class="maintabledata9" >
			     			<h:outputText id="text4" value="#{editSectionPage.module.title}" /> &raquo; <h:outputText id="text4_1" value="#{editSectionPage.section.title}" />
			          	</td>
			          </tr>	 
	                   <!-- end table header -->
                                   <tr>
                                    <td class="col1" align="left" valign="top"><h:outputText id="text7" value="#{msgs.editmodulesections_section_title}" /><span class="required">*</span></td>
                                    <td class="col2" align="left" valign="top">
									<h:inputText id="title" value="#{editSectionPage.section.title}" size="45" styleClass="formtext" />
									</td>
                                  </tr>                           
								  <tr>
                                    <td class="col1" align="left" valign="top"><h:outputText id="text9" value="#{msgs.editmodulesections_instructions}" /></td>
                                    <td class="col2" align="left" valign="top">
										  <h:inputTextarea id="instr" cols="45" rows="5" value="#{editSectionPage.section.instr}" styleClass="formtext" />
									</td>
                                  </tr>
                                  <tr>
                                    <td class="col1" align="left" valign="top"> <h:outputText id="text10" value="#{msgs.editmodulesections_modality}" /><span class="required">*</span></td>
                                    <td class="col2" align="left" valign="top"><h:outputText id="text11" value="#{msgs.editmodulesections_message1} "/>
									</td>
									  </tr>	
								  <tr>
								  <td class="col1">&nbsp;</td>
                                    <td class="col2" valign="top">
                        			<h:selectBooleanCheckbox id="contentext" title="textualContent" value="#{editSectionPage.section.textualContent}" >
									</h:selectBooleanCheckbox>
									<h:outputText  id="text12" value="#{msgs.editmodulesections_textual_content}"/>
									</td>
									  </tr>	
								  <tr>
								    <td class="col1">&nbsp;</td>
                                    <td class="col2" valign="top">									
									<h:selectBooleanCheckbox id="contentvideo" title="videoContent" value="#{editSectionPage.section.videoContent}" >
									</h:selectBooleanCheckbox>
									<h:outputText  id="text13" value="#{msgs.editmodulesections_visual_content}"/>
									</td>
									  </tr>	
								  <tr>
								    <td class="col1">&nbsp;</td>
                                    <td class="col2" valign="top">
									<h:selectBooleanCheckbox id="contentaudio" title="audioContent" value="#{editSectionPage.section.audioContent}" >
									</h:selectBooleanCheckbox>
									<h:outputText id="text14" value="#{msgs.editmodulesections_auditory_content}"/>			
										</td>
									  </tr>	
									  <tr>
								  	  <td  class="col1" align="left" valign="middle"><h:outputText id="text15" value="#{msgs.editmodulesections_content_type}" rendered="#{editSectionPage.shouldRenderContentTypeSelect}" /></td>
                                 	  <td class="col2"> 
										<h:inputHidden id="contentType"  value="#{editSectionPage.section.contentType}"  />	  								  	
										  <h:selectOneMenu id="contentType1" value="#{editSectionPage.section.contentType}" valueChangeListener="#{editSectionPage.showHideContent}" onchange="this.form.submit();"  rendered="#{editSectionPage.shouldRenderContentTypeSelect}">
											<f:selectItems value="#{editSectionPage.allContentTypes}" />											
										 </h:selectOneMenu>
											 </td>
											 </tr>
									<tr><td colspan="2" >
										 <f:subview id="ContentLinkView" rendered="#{editSectionPage.shouldRenderLink}">
											<jsp:include page="editContentLinkView.jsp"/> 
										</f:subview>
										 <f:subview id="ContentLTIView" rendered="#{editSectionPage.shouldRenderLTI}">
											<jsp:include page="editContentLTIView.jsp"/> 
										</f:subview>
										<f:subview id="ContentUploadView" rendered="#{editSectionPage.shouldRenderUpload}">
											<jsp:include page="editContentUploadView.jsp"/> 
										</f:subview>	
									</td></tr>	
									<tr> 
										 <td colspan="2">
						 									
											 <f:subview id="contentEditorView" rendered="#{editSectionPage.shouldRenderEditor && authorPreferences.shouldRenderSferyx}">
												<%if (authorPreferencePage.isShouldRenderSferyx() && eSectionPage.getShouldRenderEditor())
                                               		   { %> 
                                           				<jsp:include page="contentSferyxEditor.jsp" />
													<% } %> 
											
     											 <h:inputHidden id="sferyxDisplay" value="#{authorPreferences.shouldRenderSferyx}" />
											</f:subview>

											<f:subview id="othereditor" rendered="#{editSectionPage.shouldRenderEditor && authorPreferences.shouldRenderFCK}">
												<sakai:inputRichText id="otherMeletecontentEditor" value="#{editSectionPage.contentEditor}"  rows="50" cols="90" width="700" rendered="#{editSectionPage.shouldRenderEditor && authorPreferences.shouldRenderFCK}" collectionBase="#{editSectionPage.FCK_CollId}" />
											</f:subview>										

											</td>
									  </tr>	
								  <tr>
								   <td colspan="2">
												<f:subview id="ResourcePropertiesPanel" rendered="#{editSectionPage.meleteResource !=null && !editSectionPage.shouldRenderNotype}">
													<jsp:include page="edit_sec_resourcePropertiesPanel.jsp"/>
												</f:subview>
									</td>	
									</tr>																									
		                           </table>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="actionBar" align="left">
                	<h:commandButton id="FinishButton" action="#{editSectionPage.Finish}" value="#{msgs.im_done}" accesskey="#{msgs.done_access}" title="#{msgs.im_done_text}" styleClass="BottomImgFinish"/>
              		<h:commandButton id="submitsave" actionListener="#{editSectionPage.save}" value="#{msgs.im_save}" accesskey="#{msgs.save_access}" title="#{msgs.im_save_text}" styleClass="BottomImgSave">
              			<f:attribute name="sectionId" value="#{editSectionPage.editId}" />              	
					</h:commandButton>
					<h:commandButton id="preview" actionListener="#{editSectionPage.getPreviewPageListener}" value="#{msgs.im_preview}" accesskey="#{msgs.preview_access}" title="#{msgs.im_preview_text}" styleClass="BottomImgPreview">
						<f:attribute name="sectionId" value="#{editSectionPage.editId}" />						
					</h:commandButton>
					<h:commandButton id="saveAddAnotherbutton"  actionListener="#{editSectionPage.saveAndAddAnotherSection}" value="#{msgs.im_add_another_section}"  accesskey="#{msgs.add_access}" title="#{msgs.im_add_another_section_text}" styleClass="BottomImgAdd">
						<f:attribute name="otherId" value="#{editSectionPage.editId}" />		
					</h:commandButton>
				
       			 </div></td>
              </tr>
              
            </table>
			<table class="maintableCollapseWithNoBorder">
			<tr>
				<td rowspan="2" class="left">
					<span class="required">*</span>&nbsp; <h:outputText value="#{msgs.edit_module_required}" />
				</td>
				<td align="right" class="footnoteDates">
					<h:outputText value="#{msgs.editmodulesections_author}"/>&nbsp;<h:outputText value="#{editSectionPage.section.createdByFname}" />&nbsp;<h:outputText value="#{editSectionPage.section.createdByLname}"/><h:outputText value=","/>&nbsp;
                    <h:outputText value="#{editSectionPage.section.creationDate}" styleClass="italics"><f:convertDateTime type="both" dateStyle="long" timeStyle="short"/></h:outputText>
                             
				</td>
			</tr>
			<tr>
				<td align="right" class="footnoteDates">
					<h:outputText value="#{msgs.editmodulesections_author_edit}"/>&nbsp;<h:outputText value="#{editSectionPage.section.modifiedByFname}" />&nbsp;<h:outputText value="#{editSectionPage.section.modifiedByLname}" /><h:outputText value=","/>&nbsp;
                    <h:outputText value="#{editSectionPage.section.modificationDate}"><f:convertDateTime type="both" dateStyle="long" timeStyle="short"/></h:outputText>
                </td>
			</tr>
		</table>	
	  
  </h:form>
	 

  <!-- This Ends -->
</sakai:view>
</f:view>

