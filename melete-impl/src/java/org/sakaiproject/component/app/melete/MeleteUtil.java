/**********************************************************************************
 *
 * $URL$
 *
 ***********************************************************************************
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
 ****************************************************************************/
package org.sakaiproject.component.app.melete;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MeleteUtil {
	/** Dependency:  The logging service. */
	protected Log logger = LogFactory.getLog(MeleteUtil.class);

	public MeleteUtil(){

	}
	public String replace(String s, String one, String another) {
		// In a string replace one substring with another
		if (s.equals(""))
			return "";
		if ((one == null)||(one.length() == 0))
		{
			return s;
		}
		String res = "";
		int i = s.indexOf(one, 0);
		int lastpos = 0;
		while (i != -1) {
			res += s.substring(lastpos, i) + another;
			lastpos = i + one.length();
			i = s.indexOf(one, lastpos);
		}
		res += s.substring(lastpos); // the rest
		return res;
}
	/*
	 * REMOVE WITH MELTEDOCS MIGRATION PROGRAMME
	 */
	public byte[] readFromFile(File contentfile) throws Exception{

		FileInputStream fis = null;
		try{
			fis = new FileInputStream(contentfile);

			byte buf[] = new byte[(int)contentfile.length()];
			fis.read(buf);
			return buf;
	  	}catch(Exception ex){
	  		throw ex;
	  		}finally{
	  		if (fis != null)
	  			fis.close();
	  		}
	}

	 public boolean checkFileExists(String filePath)
	 {
	 boolean success = false;
	 try {
	        File file = new File(filePath);

	        // Create file if it does not exist
	        success = file.exists();
	        if (success) {

	        } else {
 //	        	 File did not exist and was created
	        	logger.debug("File "+filePath+" does not exist");
	        }
	    } catch (Exception e) {
	    	logger.error("error in checkFileExists"+ e.toString());
	  		e.printStackTrace();
	    }

	    return success;
	 }

		public ArrayList findEmbedItemPattern(String checkforimgs)
		{
			ArrayList returnData = new ArrayList();
			Pattern p1 = Pattern.compile("<[iI][mM][gG]\\s|<[aA]\\s");
			Pattern pi = Pattern.compile(">|\\s[sS][rR][cC]\\s*=");
			Pattern pa = Pattern.compile(">|\\s[hH][rR][eE][fF]\\s*=");
			Pattern ps = Pattern.compile("\\S");
			Pattern pe = Pattern.compile("\\s|>");

			int startSrc = 0;
			int endSrc = 0;
			String foundPattern = null;
			while(checkforimgs !=null) {
					foundPattern = null;
			        // look for <img or <a
			        Matcher m = p1.matcher(checkforimgs);
				if (!m.find()) // found anything?
				    break;
				checkforimgs = checkforimgs.substring(m.start());
				// look for src= or href=
				if (checkforimgs.startsWith("<i") ||
					    checkforimgs.startsWith("<I") ||
					    checkforimgs.startsWith("<e") ||
					    checkforimgs.startsWith("<E"))
				    m = pi.matcher(checkforimgs);
				else
				    m = pa.matcher(checkforimgs);

				if(m.pattern().pattern().equals(pa.pattern())){foundPattern = "link";}
				// end = start+1 means that we found a >
				// i.e. the attribute we're looking for isn't there
				if (!m.find() || (m.end() == m.start() + 1)) {
				    // prevent infinite loop by consuming the <
				    checkforimgs = checkforimgs.substring(1);
				    continue;
				}

				checkforimgs = checkforimgs.substring(m.end());

				// look for start of arg, a non-whitespace
			        m = ps.matcher(checkforimgs);
				if (!m.find()) // found anything?
				    break;

				checkforimgs = checkforimgs.substring(m.start());

				startSrc = 0;
				endSrc = 0;

				// handle either quoted or nonquoted arg
				if (checkforimgs.startsWith("\"") ||
				    checkforimgs.startsWith("\'")) {
				    String quotestr = checkforimgs.substring(0,1);
				    startSrc = 1;
				    endSrc = checkforimgs.indexOf(quotestr, startSrc);
				    break;
				} else {
				    startSrc = 0;
				    // ends with whitespace or >
				    m = pe.matcher(checkforimgs);
				    if (!m.find()) // found anything?
					continue;
				    endSrc = m.start();
				}

			} //while end
			returnData.add(checkforimgs);
			if (endSrc != 0) {returnData.add(new Integer(startSrc)); returnData.add(new Integer(endSrc)); returnData.add(foundPattern);}

			return returnData;
		}

		/*
		 *  remove form tag if found in the composed content as IE chokes on nested forms
		 */
		public String findFormPattern(String checkforimgs)
		{
			ArrayList returnData = new ArrayList();
			Pattern pi = Pattern.compile("<\\s*<[fF][oO][rR][mM]");

			// look for <table tr td form
			Matcher m = pi.matcher(checkforimgs);
			if (!m.find())
			{
				int formIdx = -1;
				int endFormIdx = -1;
				if((formIdx = checkforimgs.indexOf("<form")) != -1 || (formIdx = checkforimgs.indexOf("<FORM")) != -1)
				{
					logger.debug("formIdx and m.end() " + formIdx );

						//replace and add table tag
						String afterForm = checkforimgs.substring(formIdx + 6);
						afterForm = afterForm.substring(afterForm.indexOf(">")+1);
						checkforimgs = checkforimgs.substring(0, formIdx) + afterForm;
						//now look for end of form

						if((endFormIdx = checkforimgs.indexOf("</form>")) != -1 || (endFormIdx = checkforimgs.indexOf("</FORM>")) != -1)
							checkforimgs = checkforimgs.substring(0, endFormIdx) +  checkforimgs.substring(endFormIdx + 8);
				}
			}
			return checkforimgs;

		}
}
