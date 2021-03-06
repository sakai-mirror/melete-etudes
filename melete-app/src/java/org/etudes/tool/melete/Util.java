/*******************************************************************************
 *
 * $URL$
 *
 * **********************************************************************************
 *
 * Copyright (c) 2008, 2011 Etudes, Inc.
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
 ******************************************************************************/
package org.etudes.tool.melete;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.FactoryFinder;
import javax.faces.application.ApplicationFactory;
import javax.faces.el.ValueBinding;

import org.etudes.api.app.melete.exception.MeleteException;
import org.etudes.api.app.melete.exception.UserErrorException;

public class Util
{

	/**
	 * Pattern is restricted to form tags so that their action can take place. For html tag etc, the sferyx content for fonts test doc which had double quotes and single quote renders bad characters.
	 * 
	 * @param str
	 *        String to check pattern on
	 * @return true if string contains pattern, false if not
	 */
	public static boolean FindNestedHTMLTags(String str)
	{
		if (str == null) return false;
		// look for html or form tags in the content
		// Pattern pForm = Pattern.compile("<\\s*[hH][tT][mM][lL]\\s*| <\\s*[hH][eE][aA][dD]\\s*| <\\s*[bB][oO][dD][yY]\\s*| <\\s*[fF][oO][rR][mM]\\s*");
		Pattern pForm = Pattern.compile("<\\s*[fF][oO][rR][mM]\\s*");
		Matcher m = pForm.matcher(str);
		if (m.find()) return true;

		return false;
	}

	/** Get ValueBinding for this binding
	 * @param bindingName String value of binding name
	 * @return ValueBinding
	 */
	public static ValueBinding getBinding(String bindingName)
	{
		ApplicationFactory factory = (ApplicationFactory) FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);
		ValueBinding binding = factory.getApplication().createValueBinding(bindingName);
		return binding;
	}

	/**
	 * In a string replace one substring with another
	 * 
	 * @param s
	 *        String to replace in
	 * @param one
	 *        String to replace
	 * @param another
	 *        String to replace with
	 * @return Replaced/changed string
	 */
	public static String replace(String s, String one, String another)
	{
		if (s.equals("")) return "";
		String res = "";
		int i = s.indexOf(one, 0);
		int lastpos = 0;
		while (i != -1)
		{
			res += s.substring(lastpos, i) + another;
			lastpos = i + one.length();
			i = s.indexOf(one, lastpos);
		}
		res += s.substring(lastpos); // the rest
		return res;
	}

	/**
	 * This method throws a MeleteException if the url does not start with http:// or https://
	 * 
	 * @param linkUrl
	 *        value of link to be tested
	 * @throws UserErrorException
	 *         Exception throw if url does not start with http:// or https://
	 */
	public static void validateLink(String linkUrl) throws UserErrorException
	{
		if (!(linkUrl.startsWith("http://") || linkUrl.startsWith("https://")))
		{
			throw new UserErrorException("add_section_bad_url_format");
		}
	}

	/**
	 * validate uploaded file name to see there are no reserver characters only allowed pattern is [a-zA-z0-9]_-. revised on 3/31 to remove path name
	 * 
	 * @param name
	 *        file name
	 * @throws MeleteException
	 */
	public static void validateUploadFileName(String name) throws MeleteException
	{
		if (name.indexOf("#") != -1)
		{
			throw new MeleteException("embed_img_bad_filename");
		}
	}
}
