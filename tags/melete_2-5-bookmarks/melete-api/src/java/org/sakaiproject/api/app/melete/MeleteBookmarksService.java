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

package org.sakaiproject.api.app.melete;

import java.util.List;
/**
 *
 */
public interface MeleteBookmarksService
{

	public abstract void insertBookmark(MeleteBookmarksObjService mb) throws Exception;

	public abstract int getBookmarksCount(String userId, String courseId);
	
	public abstract List getBookmarks(String userId, String courseId);
	
	public abstract List getBookmarks(String userId, String courseId, Integer moduleId);
	
	public abstract List getBookmarks(String userId, String courseId, Integer moduleId, Integer sectionId);
	
	
	public abstract void deleteBookmark(MeleteBookmarksObjService mb)  throws Exception;

	public abstract void deleteAllBookmarks(String userId, String courseId) throws Exception;

}