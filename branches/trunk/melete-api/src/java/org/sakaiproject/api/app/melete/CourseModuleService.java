/**********************************************************************************
 *
 * $URL$
 *
 ***********************************************************************************
 *
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
 *
 **********************************************************************************/
package org.sakaiproject.api.app.melete;

import java.util.Date;

/**
 * Filename:
 * Description:
 * Author:
 * Date:
 * Copyright 2004, Foothill College
 */
public interface CourseModuleService {
	public abstract String getCourseId();

	public abstract void setCourseId(String courseId);

	public abstract int getSeqNo();

	public abstract void setSeqNo(int seqNo);

	public abstract boolean isArchvFlag();

	public abstract void setArchvFlag(boolean archvFlag);

	public abstract boolean isDeleteFlag();

	public abstract void setDeleteFlag(boolean deleteFlag);

	public abstract Date getDateArchived();

	public abstract void setDateArchived(Date dateArchived);


	public abstract org.sakaiproject.api.app.melete.ModuleObjService getModule();

	public abstract void setModule(
			org.sakaiproject.api.app.melete.ModuleObjService module);

	public abstract String toString();

	public abstract boolean equals(Object other);

	public abstract int hashCode();
}