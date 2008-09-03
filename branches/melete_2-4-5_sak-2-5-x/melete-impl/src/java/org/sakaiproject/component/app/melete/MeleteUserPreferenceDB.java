/*
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
 * Created on Oct 11, 2006
 *
 * author Rashmi
 */
package org.sakaiproject.component.app.melete;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;


public class MeleteUserPreferenceDB {
	private HibernateUtil hibernateUtil;
	private Log logger = LogFactory.getLog(MeleteUserPreferenceDB.class);
	private String userEditorChoice;

	/**
	 * default constructor
	 */
	public MeleteUserPreferenceDB() {

	}

	public MeleteUserPreference getUserPreferences(String userId)
	{
		MeleteUserPreference mup = null;
		try{
		     Session session = getHibernateUtil().currentSession();
		     Query q=session.createQuery("select mup from MeleteUserPreference as mup where mup.userId =:userId");
			  q.setParameter("userId",userId);
			 mup = (MeleteUserPreference)q.uniqueResult();

		     getHibernateUtil().closeSession();

		} catch(Exception ex)
		{
			//ex.printStackTrace();
			logger.error(ex.toString());
		}
		return mup;

	}

	public void setUserPreferences(MeleteUserPreference mup) throws Exception
	{
		Transaction tx = null;
	 	try
		{

	      Session session = hibernateUtil.currentSession();

	      tx = session.beginTransaction();

          //Need to split it up this way, was throwing batch update error
	      if (mup.getPrefId() == 0)
	      {
	    	  session.save(mup);
	      }
	      else
	      {
	    	  session.update(mup);
	      }
	      //session.saveOrUpdate(mup);
	      tx.commit();

	    }
	 	catch(StaleObjectStateException sose)
	     {
			if(tx !=null) tx.rollback();
			logger.error("stale object exception" + sose.toString());
			throw sose;
	     }
	    catch (HibernateException he)
	    {
		  logger.error(he.toString());
		  throw he;
	    }
	    catch (Exception e) {
	      if (tx!=null) tx.rollback();
	      logger.error(e.toString());
	      throw e;
	    }
	    finally
		{
	    	try
			  {
		      	hibernateUtil.closeSession();
			  }
		      catch (HibernateException he)
			  {
				  logger.error(he.toString());
				  throw he;
			  }
		}
	}
	/**
	 * @return Returns the hibernateUtil.
	 */
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}
	/**
	 * @param hibernateUtil The hibernateUtil to set.
	 */
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	/**
	 * @param logger The logger to set.
	 */
	public void setLogger(Log logger) {
		this.logger = logger;
	}


}