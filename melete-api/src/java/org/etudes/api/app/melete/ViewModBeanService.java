/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2007 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.etudes.api.app.melete;

import java.util.Date;
import java.util.List;

/**
 * 
 */
public interface ViewModBeanService
{

	public abstract boolean isSelected();

	public abstract void setSelected(boolean selected);

	public abstract boolean isDateFlag();

	public abstract void setDateFlag(boolean dateFlag);

	public abstract boolean isVisibleFlag();

	public abstract void setVisibleFlag(boolean visibleFlag);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract int getModuleId();

	public abstract void setModuleId(int moduleId);

	public abstract List getVsBeans();

	public abstract void setVsBeans(List vsBeans);

	public abstract void setRowClasses(String rowClasses);

	public abstract String getRowClasses();

	public abstract String toString();

	public abstract Date getEndDate();

	public abstract void setEndDate(Date endDate);

	public abstract int getSeqNo();

	public abstract void setSeqNo(int seqNo);

	public abstract Date getStartDate();

	public abstract void setStartDate(Date startDate);

	public abstract String getWhatsNext();

	public abstract void setWhatsNext(String whatsNext);

	public abstract String getSeqXml();

	public abstract void setSeqXml(String seqXml);

}
