/**********************************************************************************
*
* $Header: /usr/src/sakai/melete-2.4/melete-api/src/java/org/sakaiproject/api/app/melete/MeleteSecurityService.java,v 1.6 2007/09/21 23:02:40 mallikat Exp $
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

/**
 * <p>MeleteSecurityService provides the access permissions to the melete</p>
 * 
 * @author Foot hill college
 * @version $Revision: 13507 $
 */
public interface MeleteSecurityService{
	
	/** Security function name for author. */
	public static final String SECURE_AUTHOR = "melete.author";

	/** Security function name for student. */
	public static final String SECURE_STUDENT = "melete.student";

    /**
	 * The type string for this application: should not change over time as it may be stored in various parts of persistent
	 * entities.
	 */
	public static final String APPLICATION_ID = "sakai:meleteDocs";


	/** This string starts the references to resources in this service. */
	public static final String REFERENCE_ROOT = "/meleteDocs/";
	
	/**
	 * Check if the current user has permission as author.
	 * @return true if the current user has permission to perform this action, false if not.
	 */
	boolean allowAuthor()throws Exception;

	
	/**
	 * Check if the current user has permission as student.
	 * @return true if the current user has permission to perform this action, false if not.
	 */
	boolean allowStudent()throws Exception;
	
	public boolean isSuperUser(String userId);
	
	public void pushAdvisor();
	
	public void popAdvisor();

        public MeleteImportService getMeleteImportService();
}