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
 */
package org.sakaiproject.component.app.melete;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	    	 // session.saveOrUpdate(mup);
	    	session.update(mup);
	      }
	      
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
		  he.printStackTrace();
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
	
	public MeleteSitePreference getSitePreferences(String siteId)
	{
		MeleteSitePreference msp = null;
		try{
		     Session session = getHibernateUtil().currentSession();
		     Query q=session.createQuery("select msp from MeleteSitePreference as msp where msp.prefSiteId =:siteId");
			  q.setParameter("siteId",siteId);
			 msp = (MeleteSitePreference)q.uniqueResult();

		     getHibernateUtil().closeSession();

		} catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error(ex.toString());
		}
		return msp;

	}

	public void setSitePreferences(MeleteSitePreference msp) throws Exception
	{
		Transaction tx = null;
	 	try
		{
	      Session session = hibernateUtil.currentSession();
	      tx = session.beginTransaction();

	      Query q=session.createQuery("select msp1 from MeleteSitePreference as msp1 where msp1.prefSiteId =:siteId");
		  q.setParameter("siteId",msp.getPrefSiteId());
		  MeleteSitePreference find_msp = (MeleteSitePreference)q.uniqueResult();
		 
	      if(find_msp == null)
	     	  session.save(msp);
	      else 
	      {
	    	 find_msp.setPrintable(msp.isPrintable()); 
	    	 find_msp.setAutonumber(msp.isAutonumber()); 
	    	 session.update(find_msp);
	      }

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
		  he.printStackTrace();
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
	
	public void setSitePreferences(String site_id, boolean printFlag, boolean autonumberFlag) 
	{
		Transaction tx = null;
	 	try
		{
	      Session session = hibernateUtil.currentSession();
	      tx = session.beginTransaction();

	      Query q=session.createQuery("select msp1 from MeleteSitePreference as msp1 where msp1.prefSiteId =:siteId");
		  q.setParameter("siteId",site_id);
		  MeleteSitePreference find_msp = (MeleteSitePreference)q.uniqueResult();
		 
	      if(find_msp == null)
	      {
	    	  MeleteSitePreference msp = new MeleteSitePreference();
	    	  msp.setPrefSiteId(site_id);
	    	  msp.setPrintable(printFlag);
	    	  msp.setAutonumber(autonumberFlag);
	     	  session.save(msp);
	      }
	      else 
	      {
	    	 find_msp.setPrintable(printFlag); 
	    	 find_msp.setAutonumber(autonumberFlag); 
	    	 session.update(find_msp);
	      }

	      tx.commit();
	    }
	 	catch(StaleObjectStateException sose)
	     {
			if(tx !=null) tx.rollback();
			logger.error("stale object exception" + sose.toString());		
	     }
	    catch (HibernateException he)
	    {
		  logger.error(he.toString());
		  he.printStackTrace();		 
	    }
	    catch (Exception e) {
	      if (tx!=null) tx.rollback();
	      logger.error(e.toString());	      
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