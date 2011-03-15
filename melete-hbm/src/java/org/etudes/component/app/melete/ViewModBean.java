/**********************************************************************************
 *
 * $URL$
 * $Id$  
 ***********************************************************************************
 *
 * Copyright (c) 2009, 2010, 2011 Etudes, Inc.
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
package org.etudes.component.app.melete;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.etudes.api.app.melete.*;

/** @author Hibernate CodeGenerator */
/* Mallika - 3/22/05 - changed to implement moduledatebeanservice
 *
 */
public class ViewModBean implements Serializable, ViewModBeanService {

    /** identifier field */
    protected int moduleId;

    protected boolean selected;

    protected boolean dateFlag;

    protected boolean visibleFlag;

    protected String title;

    protected String rowClasses;
    
    protected String whatsNext;

    /** nullable persistent field */
    protected Date startDate;
    
    protected Date endDate;

    protected int seqNo;
    
    protected String seqXml;
    
    protected Date readDate;

    protected List vsBeans;

    private int vsBeansSize;
    
    private String nextStepsNumber;
    
    private String blockedBy;
    
    protected boolean closedBeforeFlag;
    
    protected boolean openLaterFlag; 
    
    protected Integer noOfSectionsRead = 0;
    /**
	 * {@inheritDoc}
	 */
    public boolean isSelected()
    {
    	return selected;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setSelected(boolean selected)
    {
    	this.selected = selected;
    }
    /**
	 * {@inheritDoc}
	 */
    public boolean isDateFlag()
    {
    	return dateFlag;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setDateFlag(boolean dateFlag)
    {
    	this.dateFlag = dateFlag;
    }

    /**
	 * {@inheritDoc}
	 */
    public boolean isVisibleFlag()
    {
    	return visibleFlag;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setVisibleFlag(boolean visibleFlag)
    {
    	this.visibleFlag = visibleFlag;
    }


    /**
	 * {@inheritDoc}
	 */
    public String getTitle()
    {
    	return title;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setTitle(String title)
    {
    	this.title = title;
    }

    /** full constructor */
   
    /** default constructor */
    public ViewModBean() {
    }

    /**
	 * {@inheritDoc}
	 */
    public int getModuleId() {
        return this.moduleId;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
   
    /**
	 * {@inheritDoc}
	 */
    public List getVsBeans() {
    	if(this.vsBeans != null) this.vsBeansSize = this.vsBeans.size();
    	else this.vsBeansSize = 0;
    	return this.vsBeans;        
    }

    /**
	 * {@inheritDoc}
	 */
    public void setVsBeans(List vsBeans) {
        this.vsBeans = vsBeans;
    }

    /**
	 * {@inheritDoc}
	 */
    public void setRowClasses(String rowClasses) {
      this.rowClasses = rowClasses;
    }

    /**
	 * {@inheritDoc}
	 */
    public String getRowClasses() {
    	return this.rowClasses;
    }

    /**
	 * {@inheritDoc}
	 */
    public String toString() {
        return new ToStringBuilder(this)
            .append("moduleId", getModuleId())
            .toString();
    }

	/**
	 * {@inheritDoc}
	 */
	public Date getEndDate()
	{
		return this.endDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSeqNo()
	{
		return this.seqNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSeqNo(int seqNo)
	{
		this.seqNo = seqNo;
	}

	/**
	 * {@inheritDoc}
	 */
	public Date getStartDate()
	{
		return this.startDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getWhatsNext()
	{
		return this.whatsNext;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setWhatsNext(String whatsNext)
	{
		this.whatsNext = whatsNext;
	}

	

	/**
	 * @param moduleId
	 * @param selected
	 * @param dateFlag
	 * @param visibleFlag
	 * @param title
	 * @param rowClasses
	 * @param whatsNext
	 * @param startDate
	 * @param endDate
	 * @param seqNo
	 * @param seqXml
	 * @param vsBeans
	 */
	public ViewModBean(int moduleId, boolean selected, boolean dateFlag, boolean visibleFlag, String title, String rowClasses, String whatsNext, Date startDate, Date endDate, int seqNo, String seqXml, List vsBeans)
	{
		this.moduleId = moduleId;
		this.selected = selected;
		this.dateFlag = dateFlag;
		this.visibleFlag = visibleFlag;
		this.title = title;
		this.rowClasses = rowClasses;
		this.whatsNext = whatsNext;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seqNo = seqNo;
		this.seqXml = seqXml;
		this.vsBeans = vsBeans;
		if(vsBeans != null)	this.vsBeansSize = vsBeans.size();
		else this.vsBeansSize = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getSeqXml()
	{
		return this.seqXml;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setSeqXml(String seqXml)
	{
		this.seqXml = seqXml;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Date getReadDate()
	{
		return this.readDate;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadDate(Date readDate)
	{
		this.readDate = readDate;
	}
	

	public String getNextStepsNumber() {
		return nextStepsNumber;
	}

	public void setNextStepsNumber(String nextStepsNumber) {
		this.nextStepsNumber = nextStepsNumber;
	}

	public String getBlockedBy() {
		return blockedBy;
	}

	public void setBlockedBy(String blockedBy) {
		this.blockedBy = blockedBy;
	}
	
	 public boolean isClosedBeforeFlag()
	 {
		 if (visibleFlag == false && blockedBy == null)
		 {
			 Date currentDate = new java.util.Date();
			 if ((getEndDate() != null)&&(getEndDate().before(currentDate)))
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public boolean isOpenLaterFlag()
	 {
		 if (visibleFlag == false && blockedBy == null)
		 {
			 Date currentDate = new java.util.Date();
			 if ((getStartDate() != null)&&(getStartDate().after(currentDate)))
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 /**
	 * {@inheritDoc}
	 */
	public Integer getNoOfSectionsRead()
	{
		return noOfSectionsRead;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNoOfSectionsRead(Integer noOfSectionsRead)
	{
		this.noOfSectionsRead = noOfSectionsRead;
	}
	 

}
