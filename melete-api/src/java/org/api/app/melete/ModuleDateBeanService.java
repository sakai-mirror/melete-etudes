/**********************************************************************************
 *
 * $URL$
 *
 ***********************************************************************************
 *
 * Copyright (c) 2008 Etudes, Inc.
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
package org.etudes.api.app.melete;
import java.util.List;

/**
 * Filename:
 * Description:
 * Author:
 * Date:
 * Copyright 2004, Foothill College
 */
public interface ModuleDateBeanService {
	public abstract boolean isSelected();

	public abstract void setSelected(boolean selected);

	public abstract boolean isDateFlag();

	public abstract void setDateFlag(boolean dateFlag);

	public abstract String getTruncTitle();

	public abstract void setTruncTitle(String truncTitle);

	public abstract int getModuleId();

	public abstract void setModuleId(int moduleId);

	public abstract org.etudes.api.app.melete.ModuleObjService getModule();

	public abstract void setModule(
			org.etudes.api.app.melete.ModuleObjService module);

	public abstract org.etudes.api.app.melete.ModuleShdatesService getModuleShdate();

	public abstract void setModuleShdate(
			org.etudes.api.app.melete.ModuleShdatesService moduleShdate);

	public abstract org.etudes.api.app.melete.CourseModuleService getCmod();

	public abstract void setCmod(
			org.etudes.api.app.melete.CourseModuleService cmod);

	public abstract List getSectionBeans();

	public abstract void setSectionBeans(List sectionBeans);

	public abstract void setRowClasses(String rowClasses);

	public abstract String getRowClasses();

	public abstract String toString();
}