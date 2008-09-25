
/*
 * Copyright (c) 2008 Etudes, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. 
 * Created on Oct 10, 2006  @author Rashmi
 *
 * Captures author preferences
 *
 * modified by rashmi - 10/16/06 - pref editor cancel functionality
 * Rashmi - 10/16/06 - nav rules from pref_editor are now pref_editor.
 * Needs to changed later back to author_preferences
 * Rashmi - 10/16/06 - read available editors from sakai.properties
 * Rashmi - 10/18/06 - default wysiwyg property - add more conditions to make it foolproof
 * Rashmi - 10/24/06 - pref_editor page is now author_pref page so later fro 3.0 change return stmts to pref_editor
 * Rashmi - 12/6/06 - add length check in defaultEditorChoice() to make sure melete default editor is not specified
 */
package org.sakaiproject.tool.melete;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.sakaiproject.util.ResourceLoader;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.api.app.melete.MeleteAuthorPrefService;
import org.sakaiproject.component.app.melete.MeleteUserPreference;
import org.sakaiproject.component.app.melete.MeleteSitePreference;
import org.sakaiproject.component.cover.ServerConfigurationService;

public class AuthorPreferencePage {
  final static String SFERYX = "Sferyx Editor";
  final static String FCKEDITOR = "FCK Editor";
  private String editorChoice;
  private String userEditor;
  private String userView="true";
  private ArrayList availableEditors;
  private boolean displaySferyx=false;

  //rendering flags
  private boolean shouldRenderSferyx=false;
  private boolean shouldRenderFCK=false;
  private boolean shouldRenderEditorPanel = false;
  private MeleteAuthorPrefService authorPref;
  private MeleteUserPreference mup;
  private MeleteSitePreference msp;
  private String materialPrintable="false";
  private String materialAutonumber="false";

  /** Dependency:  The logging service. */
	protected Log logger = LogFactory.getLog(AuthorPreferencePage.class);

  public AuthorPreferencePage()
  {
  }

  private void getUserChoice()
  {
		FacesContext context = FacesContext.getCurrentInstance();
  		Map sessionMap = context.getExternalContext().getSessionMap();

  		mup = (MeleteUserPreference) getAuthorPref().getUserChoice((String)sessionMap.get("userId"));
  		msp = (MeleteSitePreference) getAuthorPref().getSiteChoice((String)sessionMap.get("courseId"));

  		// if no choice is set then read default from sakai.properties
  		if ((mup == null)||(mup.getEditorChoice() == null))
  		{
  			editorChoice = getMeleteDefaultEditor();
  		}
  		else
  		{
  			editorChoice = mup.getEditorChoice();
  		}
  		if(editorChoice.equals(SFERYX))
  		{
  			shouldRenderSferyx = true;
  		  	shouldRenderFCK = false;
  		}
  		else if(editorChoice.equals(FCKEDITOR))
  		{
  			shouldRenderSferyx = false;
  		  	shouldRenderFCK = true;
  		 }

  		if (mup != null && !mup.isViewExpChoice())
  			userView = "false";
  		else
  			userView = "true";

  		if(msp != null && msp.isPrintable())
  			materialPrintable = "true";

  		if(msp != null && msp.isAutonumber())
  			materialAutonumber = "true";
  	return;
  	}



  public String getMeleteDefaultEditor()
	{
		String defaultEditor = ServerConfigurationService.getString("melete.wysiwyg.editor", "");
		if (logger.isDebugEnabled()) logger.debug("getMeleteDefaultEditor:"+defaultEditor+"...");

		if(defaultEditor == null || defaultEditor.length() == 0)
		{
			defaultEditor = ServerConfigurationService.getString("wysiwyg.editor", "");
			if (logger.isDebugEnabled()) logger.debug("default editor ie from wysiwyg.editor is" + defaultEditor);
			if(defaultEditor.equalsIgnoreCase(FCKEDITOR) || defaultEditor.startsWith("FCK") || defaultEditor.startsWith("fck"))
				defaultEditor = FCKEDITOR;
		}

		return defaultEditor;
	}

  public ArrayList getAvailableEditors()
  {
  	if( availableEditors == null)
  	{
  		availableEditors = new ArrayList();
  		int count = ServerConfigurationService.getInt("melete.wysiwyg.editor.count", 0);
  		for(int i=1;i <=count; i++)
  			{
  			String label = ServerConfigurationService.getString("melete.wysiwyg.editor"+i, "");
  			if(label.equalsIgnoreCase(FCKEDITOR)) label = FCKEDITOR;
  			if(label.equalsIgnoreCase(SFERYX)) label = SFERYX;
  			availableEditors.add(new SelectItem(label, label));
  			}
  	}
  	return availableEditors;
  }
  
  public boolean isShouldRenderEditorPanel()
  {
	  int count = ServerConfigurationService.getInt("melete.wysiwyg.editor.count", 0);
	  if (count > 1) shouldRenderEditorPanel = true;
	  return shouldRenderEditorPanel;
  }
/**
 * @return Returns the shouldRenderFCK.
 */
public boolean isShouldRenderFCK() {
	getUserChoice();
	return shouldRenderFCK;
}
/**
 * @param shouldRenderFCK The shouldRenderFCK to set.
 */
public void setShouldRenderFCK(boolean shouldRenderFCK) {
		this.shouldRenderFCK = shouldRenderFCK;
}
/**
 * @return Returns the shouldRenderSferyx.
 */
public boolean isShouldRenderSferyx() {
	getUserChoice();
	return shouldRenderSferyx;
}
/**
 * @param shouldRenderSferyx The shouldRenderSferyx to set.
 */
public void setShouldRenderSferyx(boolean shouldRenderSferyx) {
	this.shouldRenderSferyx = shouldRenderSferyx;
}


// editor settings
public String goToEditorPreference()
{
	return "pref_editor";
}

/**
 * Default editor is Sferyx
 * @return Returns the userEditor.
 */
public String getUserEditor() {
	getEditorChoice();
	if (editorChoice == null)
		userEditor = SFERYX;
	else userEditor = editorChoice;
	return userEditor;
}

public String getEditorChoice()
{
	getUserChoice();
	return editorChoice;
}
/**
 * @param userEditor The userEditor to set.
 */
public void setUserEditor(String userEditor) {
	this.userEditor = userEditor;
}
public String getUserView() {
	getUserChoice();
	return userView;
}
/**
 * @param userView The userView to set.
 */
public void setUserView(String userView) {
	this.userView = userView;
}
public String setUserChoice()
{
		FacesContext context = FacesContext.getCurrentInstance();
		Map sessionMap = context.getExternalContext().getSessionMap();
		ResourceLoader bundle = new ResourceLoader("org.sakaiproject.tool.melete.bundle.Messages");
        if (mup == null)
        {
        	mup = new MeleteUserPreference();
        }
		try
		{
			if (userEditor != null)
			{
				mup.setEditorChoice(userEditor);
			}
			if (userView.equals("true"))
			{
				mup.setViewExpChoice(true);
			}
			else
			{
				mup.setViewExpChoice(false);
			}

		mup.setUserId((String)sessionMap.get("userId"));
		authorPref.insertUserChoice(mup);

		// set Site Preferences
		if(msp == null) {
			msp = new MeleteSitePreference();
			msp.setPrefSiteId((String)sessionMap.get("courseId"));
		}

		//set print preference
		if (materialPrintable.equals("true"))msp.setPrintable(true);
		else msp.setPrintable(false);

		//set autonumber preference
		if (materialAutonumber.equals("true"))msp.setAutonumber(true);
		else msp.setAutonumber(false);

		authorPref.insertUserSiteChoice(msp);
		}
		catch(Exception e)
		{
			String errMsg = bundle.getString("Set_prefs_fail");
			context.addMessage (null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Set_prefs_fail",errMsg));
			return "author_preference";
		//	return "pref_editor";
		}

	String successMsg = bundle.getString("Set_prefs_success");
	context.addMessage (null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Set_prefs_success",successMsg));

	return "author_preference";
	//return "pref_editor";
}

public boolean isMaterialPrintable(String site_id)
{
	MeleteSitePreference checkMsp = (MeleteSitePreference)getAuthorPref().getSiteChoice(site_id);
	if(checkMsp != null)return checkMsp.isPrintable();
	else return false;
}
public boolean isMaterialAutonumber(String site_id)
{
	MeleteSitePreference checkMsp = (MeleteSitePreference)getAuthorPref().getSiteChoice(site_id);
	if(checkMsp != null)return checkMsp.isAutonumber();
	else return false;
}

public String backToPrefsPage()
{
	userEditor = editorChoice;
	return "author_preference";
//	return "pref_editor";
}

/**
 * @return Returns the authorPref.
 */
public MeleteAuthorPrefService getAuthorPref() {
	return authorPref;
}
/**
 * @param authorPref The authorPref to set.
 */
public void setAuthorPref(MeleteAuthorPrefService authorPref) {
	this.authorPref = authorPref;
}

/**
 * @param logger The logger to set.
 */
public void setLogger(Log logger) {
	this.logger = logger;
}

/**
 * @return Returns the displaySferyx.
 */
public boolean isDisplaySferyx() {
	return displaySferyx;
}
/**
 * @param displaySferyx The displaySferyx to set.
 */
public void setDisplaySferyx(boolean displaySferyx) {
	this.displaySferyx = displaySferyx;
}

public MeleteUserPreference getMup() {
		return mup;
}

public void setMup(MeleteUserPreference mup) {
	this.mup = mup;
}

/**
 * @return the materialPrintable
 */
public String getMaterialPrintable()
{
	return this.materialPrintable;
}

/**
 * @param materialPrintable the materialPrintable to set
 */
public void setMaterialPrintable(String materialPrintable)
{
	this.materialPrintable = materialPrintable;
}
/**
 * @return the materialAutonumber
 */
public String getMaterialAutonumber()
 {
       return this.materialAutonumber;
 }

 /**
  * @param materialAutonumber the materialAutonumber to set
  */
public void setMaterialAutonumber(String materialAutonumber)
 {
       this.materialAutonumber = materialAutonumber;
 }

}
