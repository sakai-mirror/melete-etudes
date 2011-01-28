/**********************************************************************************
 *
 * $URL: https://source.sakaiproject.org/contrib/etudes/melete/trunk/melete-hbm/src/java/org/etudes/component/app/melete/SectionTrackView.java $
 * $Id: SectionTrackView.java 60573 2009-05-19 20:17:20Z mallika@etudes.org $  
 ***********************************************************************************
 *
 * Copyright (c) 2010 Etudes, Inc.
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
 **********************************************************************************/
package org.etudes.component.app.melete;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.etudes.api.app.melete.SectionTrackViewObjService;

/** @author Hibernate CodeGenerator */
public class SectionTrackView implements Serializable, SectionTrackViewObjService {

	private int trackId;
	
	/** nullable persistent field */
    private int sectionId;
    
    /** nullable persistent field */
    private int moduleId;
    
    private String userId;

    private Date viewDate;
    
    private org.etudes.component.app.melete.Section section;
    
    public SectionTrackView()
    {
    	this.sectionId = 0;
    	this.userId = null;
    	this.viewDate = null;
    	this.section = null;
    }
	

	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#getTrackId()
	 */
	public int getTrackId() {
		return trackId;
	}

	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#setTrackId(int)
	 */
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#getSectionId()
	 */
	public int getSectionId() {
	     return this.sectionId;
	}

	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#setSectionId(int)
	 */
	public void setSectionId(int sectionId) {
	     this.sectionId = sectionId;
	}
	
	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}	
	    
	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#getUserId()
	 */
	public String getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see org.etudes.component.app.melete.SectionTrackViewObjService#setUserId(java.lang.String)
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getViewDate() {
		return this.viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}	
	/**
	 * @return Returns the section.
	 */
	public org.etudes.api.app.melete.SectionObjService getSection() {
		return section;
	}
	/**
	 * @param section The section to set.
	 */
	public void setSection(
			org.etudes.api.app.melete.SectionObjService section) {
		this.section = (Section)section;
	}	
	
	
}