/**********************************************************************************
*
* $Header: /usr/src/sakai/melete-2.4/melete-api/src/java/org/sakaiproject/api/app/melete/ModuleObjService.java,v 1.4 2007/05/07 21:16:21 mallikat Exp $
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
package org.sakaiproject.api.app.melete;

import java.util.Date;
import java.util.Map;
import java.util.Set;



/**
 * Filename:
 * Description:
 * Author:
 * Date:
 * Copyright 2004, Foothill College
 */
public interface ModuleObjService {
	public abstract Integer getModuleId();

	public abstract void setModuleId(Integer moduleId);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract String getLearnObj();

	public abstract void setLearnObj(String learnObj);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract String getKeywords();

	public abstract void setKeywords(String keywords);

	public abstract String getCreatedByFname();

	public abstract void setCreatedByFname(String createdByFname);

	public abstract String getCreatedByLname();

	public abstract void setCreatedByLname(String createdByLname);

    public abstract String getUserId();

	public abstract void setUserId(String userId);

	public abstract String getModifiedByFname();

	public abstract void setModifiedByFname(String modifiedByFname);

	public abstract String getModifiedByLname();

	public abstract void setModifiedByLname(String modifiedByLname);

	public abstract String getInstitute();

	public abstract void setInstitute(String institute);

	public abstract String getWhatsNext();

	public abstract void setWhatsNext(String whatsNext);

	public abstract Date getCreationDate();

	public abstract void setCreationDate(Date creationDate);

	public abstract Date getModificationDate();

	public abstract void setModificationDate(Date modificationDate);

        public abstract String getSeqXml();

	public abstract void setSeqXml(String seqXml);


	public abstract int getVersion();

	public abstract void setVersion(int version);	

	public abstract Map getSections();

	public abstract void setSections(Map sections);
	
    public abstract org.sakaiproject.api.app.melete.ModuleShdatesService getModuleshdate();
   
    public abstract void setModuleshdate(org.sakaiproject.api.app.melete.ModuleShdatesService moduleshdate);

	public abstract Set getModulestudentprivs();

	public abstract void setModulestudentprivs(Set modulestudentprivs);
	
	public abstract org.sakaiproject.api.app.melete.CourseModuleService getCoursemodule();
    
    public abstract void setCoursemodule(org.sakaiproject.api.app.melete.CourseModuleService coursemodule);

	public abstract String toString();
}