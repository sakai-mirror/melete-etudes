/**********************************************************************************
*
* $Header: /usr/src/sakai/melete-2.4/melete-hbm/src/java/org/sakaiproject/component/app/melete/ModuleDateBean.java,v 1.3 2007/05/07 22:02:45 mallikat Exp $
*
***********************************************************************************
*
* Copyright (c) 2004, 2005, 2006, 2007 Foothill College, ETUDES Project 
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
*
**********************************************************************************/


package org.sakaiproject.component.app.melete;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.sakaiproject.api.app.melete.*;

/** @author Hibernate CodeGenerator */
/* Mallika - 3/22/05 - changed to implement moduledatebeanservice 
 * 
 */
public class ModuleDateBean implements Serializable, ModuleDateBeanService {

    /** identifier field */
    protected int moduleId;
  
    protected boolean selected;
    
    protected boolean dateFlag;
    
    protected boolean visibleFlag;
    
    protected boolean bookmarkFlag;
    
    protected String truncTitle;
    
    protected String rowClasses;
    
    /** nullable persistent field */
    protected Module module;
    
    /** nullable persistent field */
    protected ModuleShdates moduleShdate;
    
    protected CourseModule cmod;
    
    private List sectionBeans;
    
 
    public boolean isSelected()
    {
    	return selected;
    }
    
    public void setSelected(boolean selected)
    {
    	this.selected = selected;
    }
    public boolean isDateFlag()
    {
    	return dateFlag;
    }
    
    public void setDateFlag(boolean dateFlag)
    {
    	this.dateFlag = dateFlag;
    }    
    
    public boolean isVisibleFlag()
    {
    	return visibleFlag;
    }
    
    public void setVisibleFlag(boolean visibleFlag)
    {
    	this.visibleFlag = visibleFlag;
    }        

	public boolean isBookmarkFlag()
	{
		return this.bookmarkFlag;
	}

	public void setBookmarkFlag(boolean bookmarkFlag)
	{
		this.bookmarkFlag = bookmarkFlag;
	}   
    public String getTruncTitle()
    {
    	return truncTitle;
    }
    
    public void setTruncTitle(String truncTitle)
    {
    	this.truncTitle = truncTitle;
    }

    /** full constructor */
    public ModuleDateBean(int moduleId, Module module, ModuleShdates moduleShdate,CourseModule cmod, List sectionBeans) {
        this.moduleId = moduleId;
        this.module = module;
        this.moduleShdate = moduleShdate;
        this.cmod = cmod;
        this.sectionBeans = sectionBeans;
    }

    /** default constructor */
    public ModuleDateBean() {
    }  
    
    public int getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    public org.sakaiproject.api.app.melete.ModuleObjService getModule() {
        return this.module;
    }

    public void setModule(org.sakaiproject.api.app.melete.ModuleObjService module) {
        this.module = (Module) module;
    }
    public org.sakaiproject.api.app.melete.ModuleShdatesService getModuleShdate() {
        return this.moduleShdate;
    }

    public void setModuleShdate(org.sakaiproject.api.app.melete.ModuleShdatesService moduleShdate) {
        this.moduleShdate = (ModuleShdates) moduleShdate;
    }
    public org.sakaiproject.api.app.melete.CourseModuleService getCmod() {
        return this.cmod;
    }

    public void setCmod(org.sakaiproject.api.app.melete.CourseModuleService cmod) {
        this.cmod = (CourseModule) cmod;
    }    
    public List getSectionBeans() {
        return this.sectionBeans;
    }

    public void setSectionBeans(List sectionBeans) {
        this.sectionBeans = sectionBeans;
    }

    public void setRowClasses(String rowClasses) {
      this.rowClasses = rowClasses;	
    }
    
    public String getRowClasses() {
    	return this.rowClasses;
    }
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("moduleId", getModuleId())
            .toString();
    }

}