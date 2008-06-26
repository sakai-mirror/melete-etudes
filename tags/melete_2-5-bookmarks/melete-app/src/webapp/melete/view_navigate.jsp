<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<h:panelGrid id="navSectionsItem" columns="5"  style=" border-width:medium; border-color: #E2E4E8">
	<h:column>
		<h:commandLink id="prevItem" action="#{viewSectionsPage.goPrevNext}" immediate="true"  rendered="#{viewSectionsPage.prevSecId != 0}">
			<h:outputText id="prevMsg" value="#{msgs.view_navigate_prev}"/>	
			<f:param name="modid" value="#{viewSectionsPage.moduleId}" />
			<f:param name="secid" value="#{viewSectionsPage.prevSecId}" />
     	</h:commandLink>
     	<h:commandLink id="prevMod" action="#{viewSectionsPage.goPrevModule}" immediate="true"  rendered="#{viewSectionsPage.prevSecId == 0}">
			<h:outputText  id="prevMsg1" value="#{msgs.view_navigate_prev}"/>	
			<f:param name="modid" value="#{viewSectionsPage.moduleId}" />
			<f:param name="secid" value="#{viewSectionsPage.prevSecId}" />
     	</h:commandLink>
	</h:column>
		<h:column>
				<h:outputText id="seperatorMsg1" value=" | "/>	
		</h:column>
	<h:column>
	<h:commandLink id="TOCitem" action="#{viewSectionsPage.goTOC}" immediate="true">
		  <h:outputText  id="TOCMsg" value="#{msgs.view_navigate_TOC}"/>
	</h:commandLink>    
	</h:column>
			<h:column>
				<h:outputText id="seperatorMsg2" value=" | "/>	
		</h:column>
	<h:column>
		<h:commandLink id="nextItem" action="#{viewSectionsPage.goPrevNext}" immediate="true" rendered="#{viewSectionsPage.nextSecId != 0}">
		  <h:outputText id="nextMsg"  value="#{msgs.view_navigate_next}"></h:outputText>
		    <f:param name="modid" value="#{viewSectionsPage.moduleId}" />
            <f:param name="secid" value="#{viewSectionsPage.nextSecId}" />
     </h:commandLink>
      <h:commandLink id="whatsNext" action="#{viewSectionsPage.goWhatsNext}" immediate="true" rendered="#{((viewSectionsPage.module != null && viewSectionsPage.module.whatsNext != viewSectionsPage.nullString)&&(viewSectionsPage.module != null && viewSectionsPage.module.whatsNext != viewSectionsPage.emptyString)&&(viewSectionsPage.nextSecId == 0))}">
		  <h:outputText  id="whatsNextMsg" value="#{msgs.view_navigate_next2}"></h:outputText>
		    <f:param name="modseqno" value="#{viewSectionsPage.moduleSeqNo}" />
     </h:commandLink>   
    <h:commandLink id="nextMod" action="#{viewSectionsPage.goNextModule}" immediate="true" rendered="#{(((viewSectionsPage.module != null && viewSectionsPage.module.whatsNext == viewSectionsPage.nullString)||(viewSectionsPage.module != null && viewSectionsPage.module.whatsNext == viewSectionsPage.emptyString))&&(viewSectionsPage.nextSecId == 0)&&(viewSectionsPage.moduleSeqNo < viewSectionsPage.nextSeqNo))}">
		  <h:outputText  id="nextModMsg" value="#{msgs.view_navigate_next3}"></h:outputText>
		    <f:param name="modseqno" value="#{viewSectionsPage.moduleSeqNo}" />
     </h:commandLink>     
	</h:column>
</h:panelGrid>	