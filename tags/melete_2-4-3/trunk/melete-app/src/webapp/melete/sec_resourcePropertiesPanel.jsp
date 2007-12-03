	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<link rel="stylesheet" type="text/css" href="rtbc004.css"> 
	   <h:panelGrid id="propertiesPanel" columns="1" width="100%" styleClass="maintabledata2">
		<h:column>
					<h:outputText id="propertiesPaneltxt" value="#{msgs.resources_proper_pan_properties}" />
					<h:outputText id="propertiesPaneltxt1" value="#{editSectionPage.secResourceName}" />
		</h:column>
	</h:panelGrid>
	<h:panelGrid id="propertiesPanel2" columns="2" width="82%" cellpadding="3" columnClasses="copyrightColumn1,copyrightColumn2" border="0">
		<h:column>
				 <h:outputText value="#{msgs.resources_proper_pan_URL}"  rendered="#{addSectionPage.shouldRenderLink}" /><h:outputText value="*" styleClass="required" rendered="#{addSectionPage.shouldRenderLink}"/>
		</h:column>	 
		<h:column>				
					 <h:inputText id="res_name" size="45" value="#{addSectionPage.secResourceName}" styleClass="formtext" rendered="#{addSectionPage.shouldRenderLink}" />
		</h:column>
		<h:column>
					 <h:outputText value="#{msgs.resources_proper_pan_description}" rendered="#{addSectionPage.shouldRenderLink || addSectionPage.shouldRenderUpload || addSectionPage.shouldRenderResources}"/>
		</h:column>	
		<h:column>		
							<h:inputTextarea id="res_desc" cols="45" rows="3" value="#{addSectionPage.secResourceDescription}" styleClass="formtext"    rendered="#{addSectionPage.shouldRenderLink || addSectionPage.shouldRenderUpload || addSectionPage.shouldRenderResources}" />
					</h:column>
						<!-- copyright license code -->
			<h:column>
						  <h:outputText value="#{msgs.resources_proper_pan_cstatus}" rendered="#{!addSectionPage.shouldRenderNotype}"/>	 
			</h:column>	
			<h:column>						
						 <h:selectOneMenu id="licenseCodes" value="#{addSectionPage.m_license.licenseCodes}" valueChangeListener="#{addSectionPage.m_license.hideLicense}" onchange="transferEditordata(); this.form.submit();" rendered="#{!addSectionPage.shouldRenderNotype}">
												 <f:selectItems value="#{addSectionPage.m_license.licenseTypes}" />							
							 </h:selectOneMenu>
							 <h:outputText value="          " styleClass="ExtraPaddingClass" />
							 <h:outputLink value="licenses_explained.htm" rendered="#{!addSectionPage.shouldRenderNotype}" target="_blank">  <h:graphicImage value="images/help.gif" alt="Learn More about The License Options" width="16" height="16" styleClass="ExpClass"/></h:outputLink>
			</h:column>	
		</h:panelGrid>
		
		<h:panelGrid id="propertiesPanel3" columns="1" width="100%">
					<h:column>
						  <f:subview id="CCLicenseForm" rendered="#{addSectionPage.m_license.shouldRenderCC || addSectionPage.m_license.shouldRenderCopyright || addSectionPage.m_license.shouldRenderPublicDomain || addSectionPage.m_license.shouldRenderFairUse}">	
												<jsp:include page="addsection_cclicenseform.jsp"/> 
											</f:subview>
					</h:column>	
		</h:panelGrid>
		
			        <!-- end license code -->		