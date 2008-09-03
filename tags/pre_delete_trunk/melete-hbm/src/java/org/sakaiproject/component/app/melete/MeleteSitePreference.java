/**********************************************************************************
*
* $Header: 
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
import org.apache.commons.lang.builder.ToStringBuilder;

import org.sakaiproject.api.app.melete.MeleteSitePreferenceService;

/** @author Hibernate CodeGenerator */
public class MeleteSitePreference implements Serializable,MeleteSitePreferenceService{

    /** identifier field */
    private String prefSiteId;

    /** nullable persistent field */
    private boolean printable;   
   
	/** full constructor
	 * @param userId
	 * @param editorChoice
	 */
	public MeleteSitePreference(String prefSiteId, boolean printable) {
		this.prefSiteId = prefSiteId;		
		this.printable = printable;
	}
		
	/**
	 *  default
	 */
	public MeleteSitePreference() {
		this.printable = false;
	}
		
	/**
	 * @return Returns the userId.
	 */
	public String getPrefSiteId() {
		return prefSiteId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setPrefSiteId(String prefSiteId) {
		this.prefSiteId = prefSiteId;
	}
    public String toString() {
        return new ToStringBuilder(this)
            .append("prefSiteId", getPrefSiteId())
            .toString();
    }

	/**
	 * @return the printable
	 */
	public boolean isPrintable()
	{
		return this.printable;
	}

	/**
	 * @param printable the printable to set
	 */
	public void setPrintable(boolean printable)
	{
		this.printable = printable;
	}
}