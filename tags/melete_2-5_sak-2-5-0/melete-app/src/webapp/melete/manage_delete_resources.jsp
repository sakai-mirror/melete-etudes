<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

 
 <!-- navigation with showing 15 recs --> 
 <h:panelGrid id="selresNavigationPanel" columns="2"  width="100%" border="0" columnClasses="TitleWid4,ActionWid2" rendered="#{manageResourcesPage.listNav.displayNav}" >
<h:column/>
<h:column>
 <h:outputText id="nav_spaces_left1" value="" styleClass="ExtraPaddingClass" />
	<h:outputText value="#{msgs.list_section_resources_viewing}" /><h:outputText value="#{manageResourcesPage.listNav.displayStartIndex}" /><h:outputText value=" - " /><h:outputText value="#{manageResourcesPage.listNav.displayEndIndex}" /> <h:outputText value=" "/><h:outputText value="#{msgs.list_section_resources_of}" /> <h:outputText value="#{manageResourcesPage.listNav.totalSize -1}" /> 		
	 <h:outputText id="nav_spaces_right" value="" styleClass="ExtraPaddingClass" />
</h:column>
<h:column/>
   	<h:column>
   		<h:graphicImage id="leftImg_disable" value="images/nav_left_disable.jpg" alt="#{msgs.list_section_resources_previous}" styleClass="ModCheckClass" style="border:0" rendered="#{!manageResourcesPage.listNav.displayPrev}" />
		<h:commandLink id="prev_nav"  action="#{manageResourcesPage.listNav.goPrev}" immediate="true" rendered="#{manageResourcesPage.listNav.displayPrev}">		
						 <h:graphicImage id="leftImg" value="images/nav_left.jpg" alt="#{msgs.list_section_resources_previous2}" styleClass="ModCheckClass" style="border:0" />
		   </h:commandLink>		 
		   	 <h:outputText id="nav_spaces_left" value="" styleClass="ExtraPaddingClass" />
		  <h:selectOneMenu id="chunkSize">
								<f:selectItem itemValue="15" itemLabel="#{msgs.list_section_resources_show15}"/>	
		 </h:selectOneMenu>
		 <h:outputText id="nav_spaces" value="" styleClass="ExtraPaddingClass" />
				<h:commandLink id="next_nav" action="#{manageResourcesPage.listNav.goNext}" rendered="#{manageResourcesPage.listNav.displayNext}" immediate="true">
							 <h:graphicImage id="rightImg" value="images/nav_right.jpg" alt="#{msgs.list_section_resources_next}" styleClass="ModCheckClass" style="border:0" />
			   </h:commandLink>	 
			   <h:graphicImage id="rightImg_disable" value="images/nav_right_disable.jpg" alt="#{msgs.list_section_resources_next2}" styleClass="ModCheckClass" style="border:0" rendered="#{!manageResourcesPage.listNav.displayNext}"/> 
   	  </h:column>											
</h:panelGrid> 
   <!-- navigation ends -->   
 	 <h:dataTable id="table" value="#{manageResourcesPage.displayResourcesList}"  var="list_resources"  border="0" headerClass="tableheader2" columnClasses="ModCheckClass,TitleWid3,ActionWid2"  rowClasses="row1,row2" width="100%" onclick="this.styleClass='blue';">
		  <h:column>
			   <f:facet name="header">
					<h:panelGroup>
						<h:commandLink id="ascType" action="#{manageResourcesPage.sortResourcesAsc}" immediate="true" rendered="#{manageResourcesPage.sortAscFlag}">
						    <h:graphicImage id="asc_Type_img" alt="#{msgs.manage_res_list_alt_asc}" title="#{msgs.manage_res_list_alt_asc}" value="images/sortascending.gif" styleClass="ExpClass"/>
						 </h:commandLink>     
						 <h:commandLink id="descType" action="#{manageResourcesPage.sortResourcesDesc}" immediate="true" rendered="#{!manageResourcesPage.sortAscFlag}">
						    <h:graphicImage id="des_Type_img" alt="#{msgs.manage_res_list_alt_desc}" title="#{msgs.manage_res_list_alt_desc}" value="images/sortdescending.gif" styleClass="ExpClass"/>
						 </h:commandLink>        
					    <h:outputText value="#{msgs.manage_res_list_type}" />
					 </h:panelGroup> 
				 </f:facet>
			    <h:graphicImage id="contenttype_gif" alt="#{msgs.edit_list_resources_content_url}" title="#{msgs.edit_list_resources_content_url}" value="#{list_resources.resource_gif}" styleClass="ExpClass" />			
			</h:column>
		    <h:column>
		    <f:facet name="header">
					 <h:outputText id="t2-1" value="#{msgs.manage_res_list_title}" />
			 </f:facet>
					<h:outputText id="emp_spacebefore" value="       "  styleClass="ExtraPaddingClass"/>
				  <h:outputLink id="showResourceLink" value="#{list_resources.resource_url}" target="_blank" title="Section Resource" styleClass="a1">
						  <h:outputText value="#{list_resources.resource_title}" />
				  </h:outputLink>
		    </h:column>
		    <h:column>
		    <f:facet name="header">
					 <h:outputText id="t2" value="#{msgs.edit_list_resources_actions2}" />
			 </f:facet>
			 <h:outputText id="emp_space-1" value="     "  styleClass="ExtraPaddingClass" />
			 <h:graphicImage id="delgif" alt="" value="images/delete.gif" styleClass="AuthImgClass" />
			 <h:outputText id="emp_space-2" value=" " />
			 <h:commandLink id="deleteaction" actionListener="#{manageResourcesPage.selectedResourceDeleteAction}"  action="#{manageResourcesPage.redirectDeleteLink}" immediate="true" >
		    		 <h:outputText id="deltext" value="#{msgs.edit_list_resources_del}"  />
			 </h:commandLink>	
		    </h:column>
         </h:dataTable>   
       	