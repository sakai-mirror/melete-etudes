/**********************************************************************************
 *
 * $URL: https://source.sakaiproject.org/contrib/etudes/melete/trunk/melete-impl/src/java/org/etudes/component/app/melete/BookmarkServiceImpl.java $
 * $Id: BookmarkServiceImpl.java 56408 2008-12-19 21:16:52Z rashmi@etudes.org $
 ***********************************************************************************
 *
 * Copyright (c) 2008 Etudes, Inc.
 *
 * Portions completed before September 1, 2008 Copyright (c) 2004, 2005, 2006, 2007, 2008 Foothill College, ETUDES Project
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
import java.util.Set;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.etudes.api.app.melete.BookmarkService;
import org.etudes.api.app.melete.BookmarkObjService;
import org.etudes.api.app.melete.exception.MeleteException;

public class BookmarkServiceImpl implements Serializable, BookmarkService{
private Log logger = LogFactory.getLog(BookmarkServiceImpl.class);
private BookmarkDB bookmarkDb;


public void insertBookmark(BookmarkObjService mb) throws Exception
	{
		try{
			bookmarkDb.setBookmark((Bookmark)mb);
		}catch(Exception e)
			{
			logger.debug("melete bookmark business --add bookmark failed");
			 throw new MeleteException("add_bookmark_fail");

			}
		return;
	}

public BookmarkObjService getBookmark(String userId, String siteId, int sectionId)
{
  BookmarkObjService bObj = null;
	try{
		bObj = bookmarkDb.getBookmark(userId, siteId, sectionId);
	}catch(Exception e)
		{
		logger.debug("melete bookmark business --get bookmark failed");
		}
	return bObj;
}

public List getBookmarks(String userId, String siteId)
	{
	List mbList = null;
		try{
			mbList = bookmarkDb.getBookmarks(userId, siteId);
		}catch(Exception e)
			{
			logger.debug("melete bookmark business --get bookmarks failed");
			}
		return mbList;
	}



	/**
	 * @return Returns the bookmarkDb
	 */
	public BookmarkDB getBookmarkDb() {
		return bookmarkDb;
	}
	/**
	 * @param bookmarkDb The bookmarkDb to set.
	 */
	public void setBookmarkDb(BookmarkDB bookmarkDb) {
		this.bookmarkDb = bookmarkDb;
	}

}
