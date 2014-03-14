/**********************************************************************************
 *
 * $URL$
 *
 ***********************************************************************************
 * Copyright (c) 2008, 2009,2010,2011, 2014 Etudes, Inc.
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
package org.etudes.tool.melete;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.sakaiproject.user.cover.UserDirectoryService;
import org.sakaiproject.util.ResourceLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.etudes.component.app.melete.CourseModule;
import org.etudes.component.app.melete.Module;
import org.etudes.component.app.melete.ModuleDateBean;
import org.etudes.api.app.melete.MeleteExportService;
import org.etudes.api.app.melete.MeleteImportService;
import org.etudes.api.app.melete.ModuleObjService;
import org.etudes.api.app.melete.ModuleService;
import org.etudes.api.app.melete.exception.MeleteException;
import org.etudes.api.app.melete.util.XMLHelper;
import org.sakaiproject.component.cover.ServerConfigurationService;
import org.sakaiproject.util.Validator;
import javax.faces.model.SelectItem;

public class ExportMeleteModules
{

	/** Dependency: The logging service. */
	protected Log logger = LogFactory.getLog(ExportMeleteModules.class);

	protected MeleteSiteAndUserInfo meleteSiteAndUserInfo;

	/** Dependency: The melete import export service. */
	protected MeleteExportService meleteExportService;
	protected MeleteExportService meleteExportScormService;
	protected MeleteImportService meleteImportService;

	/** Dependency: The module service. */
	ModuleService moduleService;

	private List availableModules;
	private List<String> selectedModules;
	private boolean noFlag;
	private String selectFormat;

	final static int BUFFER = 2048;
	final static String IMS_MANIFEST_FILENAME = "imsmanifest.xml";
	final static String SCORM2004_BASE_FILENAME = "SCORM2004base.zip";
	
	private UIData table;
	int listSize;
	boolean selectAllFlag;
	private List selectedModIds = null;
	boolean moduleSelected;

	
	/**
	 * @return value of datatable (in which modules are rendered)
	 */
	public UIData getTable()
	{
		return table;
	}

	/**
	 * @param table module datatable to set
	 */
	public void setTable(UIData table)
	{
		this.table = table;
	}

	/**
	 * constructor
	 */
	public ExportMeleteModules()
	{
		availableModules = null;
		selectedModules = null;
		noFlag = false;
		selectFormat = "IMS";
		selectedModules = new ArrayList<String>(0);
		selectedModules.add("all");
		listSize = 0;
		selectAllFlag = false;
		selectedModIds = null;
		moduleSelected = false;
	}

	/**
	 * Reset values.
	 */
	public void resetValues()
	{
		availableModules = null;
		selectedModules = null;
		noFlag = false;
		selectFormat = "IMS";
		selectedModules = new ArrayList<String>(0);
		selectedModules.add("all");
		listSize = 0;
		selectAllFlag = false;
		selectedModIds = null;
		moduleSelected = false;
	}

	/**
	 * Creates a list of all active modules to show in the select list. If no active modules then gives out a message.
	 *
	 * @return
	 */
	public List getAvailableModules()
	{
		resetSelectedLists();
		ResourceLoader bundle = new ResourceLoader("org.etudes.tool.melete.bundle.Messages");

		if (availableModules == null)
		{
			String userId = getMeleteSiteAndUserInfo().getCurrentUser().getId();
			String courseId = getMeleteSiteAndUserInfo().getCurrentSiteId();
			
			availableModules = moduleService.getModuleDateBeans(userId, courseId);
			
			if (availableModules == null || availableModules.size() == 0)
			{
				String nomsg = bundle.getString("no_course_modules_available");
				noFlag = true;
				listSize = 0;
				return availableModules;
			}
			listSize = availableModules.size();
		}
		return availableModules;
	}
	
	/**Method that triggers when a module is selected
	 * @param event ValueChangeEvent object
	 * @throws AbortProcessingException
	 */
	public void selectedModule(ValueChangeEvent event) throws AbortProcessingException
    {
		if ((availableModules == null)||(availableModules.size() == 0)) return;
		if (selectAllFlag == false) {
			UIInput mod_Selected = (UIInput) event.getComponent();
			/*if (((Boolean) mod_Selected.getValue()).booleanValue() == true)
				count++;
			else
				count--;*/

			String title = (String) mod_Selected.getAttributes().get("title");
			if (title != null) {
				int selectedModId = Integer.parseInt(title);

				if (selectedModIds == null) {
					selectedModIds = new ArrayList();
				}
				selectedModIds.add(new Integer(selectedModId));
				moduleSelected = true;
			}
		}
		return;
	}
	
	/**Method that triggers when all modules are selected
	 * @param event ValueChangeEvent object
	 * @throws AbortProcessingException
	 */
	public void selectAllModules(ValueChangeEvent event)
			throws AbortProcessingException {
		ModuleDateBean mdbean = null;
		selectAllFlag = true;
		int k = 0;
		if (selectedModIds == null) {
			selectedModIds = new ArrayList();
		}
		if ((availableModules != null) && (availableModules.size() > 0)) {
			for (ListIterator i = availableModules.listIterator(); i.hasNext();) {
				mdbean = (ModuleDateBean) i.next();
				mdbean.setSelected(true);
				selectedModIds.add(new Integer(mdbean.getModuleId()));
			}
			/*count = availableModules.size();
			if (count == 1)
				selectedModId = mdbean.getModuleId();*/
			moduleSelected = true;
		}
		return;
	}
	
	/**
	 * Creates a list of modules.
	 *
	 * @return
	 */
	private List<ModuleObjService> createSelectedList()
	{
		if (availableModules == null || availableModules.size() == 0 || selectedModIds == null || selectedModIds.size() == 0) return null;

		List<ModuleObjService> returnList = new ArrayList<ModuleObjService>(0);

		for (Object obj : availableModules)
		{
			ModuleDateBean m = (ModuleDateBean) obj;
			if (selectedModIds.contains(m.getModule().getModuleId()))
			{
				returnList.add(m.getModule());
			}
		}

		return returnList;
	}

	
	public void resetSelectedLists()
	{
		selectedModIds = null;
		selectAllFlag = false;
	}

	/**
	 * Starts Export Action. If "all" modules are selected then adds archived modules in the list. Based on format selected starts IMS or SCORM export.
	 *
	 * @return navigation rule
	 */
	public String exportModules()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceLoader bundle = new ResourceLoader("org.etudes.tool.melete.bundle.Messages");

		try
		{
			List<ModuleObjService> selectList = createSelectedList();
			boolean allFlag = false;

			if (selectList != null && selectList.size() > 0)
			{
				if (selectList.size() == availableModules.size())
				{
					allFlag = true;
					String courseId = getMeleteSiteAndUserInfo().getCurrentSiteId();
					List<?> cmodArchList = getModuleService().getArchiveModules(courseId);
					if ((cmodArchList != null) && (cmodArchList.size() > 0))
					{
						Iterator<?> i = cmodArchList.iterator();
						List<Module> archModList = new ArrayList<Module>();
						while (i.hasNext())
						{
							CourseModule cmod = (CourseModule) i.next();
							archModList.add((Module) cmod.getModule());
						}
						selectList.addAll(archModList);
					}
				}
				if (selectFormat.startsWith("IMS"))
					exportIMSModules(selectList, allFlag);
				else
					exportScormModules(selectList, allFlag);

			}
			else
			{
				FacesMessage msg;
				if (availableModules == null || availableModules.size() == 0)
				{
					// add message for no modules
					String noModMsg = bundle.getString("no_course_modules_available");
					msg = new FacesMessage(null, noModMsg);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
				}
				else
				{
					// add message for no modules
					String selModMsg = bundle.getString("importexportmodules_select");
					msg = new FacesMessage(null, selModMsg);
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				}
				context.addMessage(null, msg);
			}
		}
		catch (Exception e)
		{
			if (logger.isErrorEnabled()) logger.error(this.getClass().getName() + " : " + e.toString());

			e.printStackTrace();
			String errMsg = bundle.getString("error_exporting");
			if (e instanceof MeleteException)
			{
				errMsg = errMsg + e.getMessage() + ". " + bundle.getString("error_contact_admin");
			}
			FacesMessage msg = new FacesMessage(null, errMsg);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}

		return "importexportmodules";
	}

	/**
	 * exports the modules according to imsglobal content packaging specs 1.1.4
	 *
	 * @param selectList
	 *        List of modules
	 * @param allFlag
	 *        Flag to check all modules or selected ones
	 * @return navigation page
	 */
	public void exportIMSModules(List<ModuleObjService> selectList, boolean allFlag) throws Exception
	{
		if (logger.isDebugEnabled()) logger.debug("Starting export IMS Modules....");

		String packagingdirpath = ServerConfigurationService.getString("melete.packagingDir", "");
		String instr_id = getMeleteSiteAndUserInfo().getCurrentUser().getId();
		instr_id = instr_id.trim();
		String courseId = getMeleteSiteAndUserInfo().getCurrentSiteId();
		courseId = courseId.trim();

		File packagedir = null;
		try
		{
			if (packagingdirpath == null || packagingdirpath.length() <= 0)
			{
				logger.warn("Melete Packaging Dir property is not set. Please check melete's readme file. ");
				return;
			}
			File basePackDir = new File(packagingdirpath);
			if (!basePackDir.exists()) basePackDir.mkdirs();

			String exisXmlFile = basePackDir.getAbsolutePath() + File.separator + IMS_MANIFEST_FILENAME;
			File manifestFile = new File(exisXmlFile);

			meleteExportService.initValues();
			Element manifest = createManifestMetadata(manifestFile, meleteExportService);

			String title = getMeleteSiteAndUserInfo().getCourseTitle();
			title = title.trim();
			packagedir = new File(basePackDir.getAbsolutePath() + File.separator + courseId + "_" + instr_id + File.separator
					+ title.replace(' ', '_'));
			// delete existing files
			meleteExportService.deleteFiles(packagedir);

			if (!packagedir.exists()) packagedir.mkdirs();

			// copy the schema files
			File schemaFilesDir = basePackDir;
			String imscp_v1p1 = schemaFilesDir.getAbsolutePath() + File.separator + "imscp_v1p1.xsd";
			File imscp_v1p1_File = new File(imscp_v1p1);
			meleteExportService.createFile(imscp_v1p1_File.getAbsolutePath(), packagedir.getAbsolutePath() + File.separator + "imscp_v1p1.xsd");

			String imsmd_v1p2 = schemaFilesDir.getAbsolutePath() + File.separator + "imsmd_v1p2.xsd";
			File imsmd_v1p2_File = new File(imsmd_v1p2);
			meleteExportService.createFile(imsmd_v1p2_File.getAbsolutePath(), packagedir.getAbsolutePath() + File.separator + "imsmd_v1p2.xsd");

			String imsxml = schemaFilesDir.getAbsolutePath() + File.separator + "xml.xsd";
			File xml_File = new File(imsxml);
			meleteExportService.createFile(xml_File.getAbsolutePath(), packagedir.getAbsolutePath() + File.separator + "xml.xsd");

			List<Element> orgResElements = meleteExportService.generateOrganizationResourceItems(selectList, allFlag, packagedir, title, courseId);

			if (orgResElements != null && orgResElements.size() > 0) manifest.add((Element) orgResElements.get(0));
			if (orgResElements != null && orgResElements.size() > 1) manifest.add((Element) orgResElements.get(1));

			// create xml document and add element
			Document document = XMLHelper.createXMLDocument(manifest);

			String newXmlFile = packagedir.getAbsolutePath() + File.separator + IMS_MANIFEST_FILENAME;
			// generate xml file
			XMLHelper.generateXMLFile(newXmlFile, document);

			// validate new manifest xml
			XMLHelper.parseFile(new File(newXmlFile));

			title = Validator.escapeResourceName(title);

			String outputfilename = null;
			if (allFlag)
			{
				outputfilename = packagedir.getParentFile().getAbsolutePath() + File.separator + title.replace(' ', '_') + "_allModules.zip";
			}
			else
			{
				outputfilename = packagedir.getParentFile().getAbsolutePath() + File.separator + title.replace(' ', '_') + "_fewModules.zip";
			}
			File zipfile = new File(outputfilename);
			// create zip
			createZip(packagedir, zipfile);

			// download zip
			download(new File(zipfile.getAbsolutePath()));

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			// delete the files - Directory courseid_instructorid and
			// it's child
			if (packagedir != null && packagedir.exists()) meleteExportService.deleteFiles(packagedir.getParentFile());

		}

		if (logger.isDebugEnabled()) logger.debug("Exiting exportModules....");

	}

	/**
	 * exports the modules according to ADL SCORM 2004 3rd Edition
	 *
	 *@param selectList
	 *        List of Modules to be exported out
	 * @param allFlag
	 *        flag to check all modules or selected modules
	 * @return navigation page
	 */
	public void exportScormModules(List<ModuleObjService> selectList, boolean allFlag) throws Exception
	{
		if (logger.isDebugEnabled()) logger.debug("Starting exportModules....");

		String instr_id = getMeleteSiteAndUserInfo().getCurrentUser().getId();
		String courseId = getMeleteSiteAndUserInfo().getCurrentSiteId();

		String packagingdirpath = ServerConfigurationService.getString("melete.packagingDir", "");
		if (packagingdirpath == null || packagingdirpath.length() <= 0)
		{
			logger.warn("Melete Packaging Dir property is not set. Please check melete's readme file. ");
			return;
		}
		packagingdirpath = packagingdirpath.concat(File.separator + "packagefilesscorm");
		File packagedir = null;
		try
		{
			File basePackDir = new File(packagingdirpath);
			if (!basePackDir.exists()) basePackDir.mkdirs();

			String title = getMeleteSiteAndUserInfo().getCourseTitle();
			packagedir = new File(basePackDir.getAbsolutePath() + File.separator + courseId + "_" + instr_id + File.separator
					+ title.replace(' ', '_') + "scorm");
			// delete exisiting files
			meleteExportScormService.deleteFiles(packagedir);

			if (!packagedir.exists()) packagedir.mkdirs();

			// 1- unzip the SCORM2004_BASE_FILENAME in the packagedir
			// Directory
			File scorm2004BaseFile = new File(basePackDir.getAbsolutePath() + File.separator + SCORM2004_BASE_FILENAME);
			unZipFile(scorm2004BaseFile, packagedir.getAbsolutePath());

			// 2- Get the manifestBaseFile
			String exisXmlFile = packagedir.getAbsolutePath() + File.separator + IMS_MANIFEST_FILENAME;
			File manifestFile = new File(exisXmlFile);

			meleteExportScormService.initValues();
			Element manifest = createManifestMetadata(manifestFile, meleteExportScormService);

			List<?> orgResElements = meleteExportScormService.generateOrganizationResourceItems(selectList, allFlag, packagedir, title, courseId);

			if (orgResElements != null && orgResElements.size() > 0)
			{
				manifest.add((Element) orgResElements.get(0));
				manifest.add((Element) orgResElements.get(1));
			}

			// create xml document and add element
			Document document = XMLHelper.createXMLDocument(manifest);

			String newXmlFile = packagedir.getAbsolutePath() + File.separator + IMS_MANIFEST_FILENAME;
			// generate xml file
			XMLHelper.generateXMLFile(newXmlFile, document);

			// validate new manifest xml
			XMLHelper.parseFile(new File(newXmlFile));

			title = Validator.escapeResourceName(title);
			String outputfilename = null;
			if (allFlag)
			{
				outputfilename = packagedir.getParentFile().getAbsolutePath() + File.separator + title.replace(' ', '_') + "_allModules_scorm.zip";
			}
			else
			{
				outputfilename = packagedir.getParentFile().getAbsolutePath() + File.separator + title.replace(' ', '_') + "_fewModules_scorm.zip";
			}

			File zipfile = new File(outputfilename);
			// create zip
			createZip(packagedir, zipfile);

			// download zip
			download(new File(zipfile.getAbsolutePath()));

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.responseComplete();

		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			// delete the files - Directory courseid_instructorid and
			// it's child
			if (packagedir != null && packagedir.exists()) meleteExportScormService.deleteFiles(packagedir.getParentFile());

		}
		if (logger.isDebugEnabled()) logger.debug("Exiting exportModules....");

	}

	/**
	 * imports the modules according to imsglobal content packaging specs 1.1.4
	 *
	 * @return navigation page
	 */
	public String importModules()
	{

		if (logger.isDebugEnabled()) logger.debug("Entering importModules....");

		FacesContext context = FacesContext.getCurrentInstance();
		ResourceLoader bundle = new ResourceLoader("org.etudes.tool.melete.bundle.Messages");
		FileItem fileItem = (FileItem) context.getExternalContext().getRequestMap().get("impfile");

		if (fileItem != null)
		{
			String uploadedFileName = fileItem.getName();
			if (uploadedFileName != null && uploadedFileName.trim().length() > 0)
			{
				// check for zip file
				// if (uploadedFileName.endsWith(".zip")) {
				if (uploadedFileName.lastIndexOf('.') != -1
						&& uploadedFileName.substring(uploadedFileName.lastIndexOf('.') + 1).equalsIgnoreCase("zip"))
				{
					File unpackagedir = null;
					try
					{
						String packagingdirpath = ServerConfigurationService.getString("melete.packagingDir", "");
						if (packagingdirpath == null || packagingdirpath.length() <= 0)
						{
							logger.warn("Melete Packaging Dir property is not set. Please check melete's readme file. ");
							String infoMsg = bundle.getString("error_importing");
							FacesMessage msg = new FacesMessage(null, infoMsg);
							msg.setSeverity(FacesMessage.SEVERITY_ERROR);
							context.addMessage(null, msg);
							return "importexportmodules";
						}
						String instr_id = getMeleteSiteAndUserInfo().getCurrentUser().getId();
						String courseId = getMeleteSiteAndUserInfo().getCurrentSiteId();

						File basePackDir = new File(packagingdirpath);
						if (!basePackDir.exists()) basePackDir.mkdirs();
						String title = getMeleteSiteAndUserInfo().getCourseTitle();

						unpackagedir = new File(basePackDir.getAbsolutePath() + File.separator + "import" + File.separator + courseId + "_"
								+ instr_id + File.separator + title.replace(' ', '_'));
						// delete exisiting files
						meleteImportService.deleteFiles(unpackagedir);

						if (!unpackagedir.exists()) unpackagedir.mkdirs();
						String actFileName;
						// write the uploaded zip file to disk
						if (uploadedFileName.indexOf('\\') != -1)
							actFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf('\\') + 1);
						else
							actFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf('/') + 1);
						// write file to disk
						File outputFile = new File(unpackagedir + File.separator + actFileName);
						writeFileToDisk(fileItem, outputFile);
						// unzipping dir
						File unzippeddir = new File(unpackagedir.getAbsolutePath() + File.separator
								+ actFileName.substring(0, actFileName.lastIndexOf('.') + 1));
						if (!unzippeddir.exists()) unzippeddir.mkdirs();
						String unzippedDirpath = unzippeddir.getAbsolutePath();
						// unzip files
						unZipFile(outputFile, unzippedDirpath);

						File imsmanifest = new File(unzippedDirpath + File.separator + IMS_MANIFEST_FILENAME);
						validatemanifest(imsmanifest);

						Document document = XMLHelper.parseFile(imsmanifest);
						// Mallika - line below changes
						meleteImportService.parseAndBuildModules(courseId, document, unzippedDirpath, UserDirectoryService.getCurrentUser().getId());

						// message as import is success
						String infoMsg = bundle.getString("import_success");
						String otherInfo = meleteImportService.getContentSourceInfo(document);
						if (otherInfo != null) infoMsg = infoMsg.concat(otherInfo);
						FacesMessage msg = new FacesMessage(null, infoMsg);
						msg.setSeverity(FacesMessage.SEVERITY_INFO);
						context.addMessage(null, msg);

					}
					catch (Exception e)
					{
						if (logger.isErrorEnabled()) logger.error(this.getClass().getName() + " : " + e.toString());

						String infoMsg = bundle.getString("error_importing");
						FacesMessage msg = new FacesMessage(null, infoMsg);
						msg.setSeverity(FacesMessage.SEVERITY_ERROR);
						context.addMessage(null, msg);
						// e.printStackTrace();
					}
					finally
					{
						// delete the files - Directory courseid_instructorid
						// and
						// it's child
						if (unpackagedir != null && unpackagedir.exists()) meleteImportService.deleteFiles(unpackagedir.getParentFile());
					}
				}
				else
				{
					// add message as file is not a zip file
					String infoMsg = bundle.getString("import_file_not_zip");
					FacesMessage msg = new FacesMessage(null, infoMsg);
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
					context.addMessage(null, msg);
				}
			}
			else
			{
				String infoMsg = bundle.getString("import_content_file_not_selected");
				FacesMessage msg = new FacesMessage(null, infoMsg);
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				context.addMessage(null, msg);
			}

		}

		if (logger.isDebugEnabled()) logger.debug("Exiting importModules....");

		return "importexportmodules";
	}

	/**
	 * validate manifest with imscp spec version 1.1.4
	 *
	 * @param imsmanifest
	 */
	private void validatemanifest(File imsmanifest)
	{
	}

	/**
	 * writes the file to disk
	 *
	 * @param fileItem
	 *        Fileitem
	 * @param outputFile
	 *        out put file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void writeFileToDisk(FileItem fileItem, File outputFile) throws FileNotFoundException, IOException
	{
		FileInputStream in = null;
		FileOutputStream out = null;
		try
		{
			in = (FileInputStream) fileItem.getInputStream();
			out = new FileOutputStream(outputFile);

			int len;
			byte buf[] = new byte[102400];
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (in != null) in.close();
			}
			catch (IOException e1)
			{
			}
			try
			{
				if (out != null) out.close();
			}
			catch (IOException e2)
			{
			}
		}
	}

	/**
	 * unzip the file and write to disk
	 *
	 * @param zipfile
	 * @param dirpath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void unZipFile(File zipfile, String dirpath) throws Exception
	{
		FileInputStream fis = new FileInputStream(zipfile);
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null)
		{
			if (entry.isDirectory())
			{

			}
			else if (entry.getName().lastIndexOf('\\') != -1)
			{
				String filenameincpath = entry.getName();

				String actFileNameIncPath = dirpath;

				while (filenameincpath.indexOf('\\') != -1)
				{
					String subFolName = filenameincpath.substring(0, filenameincpath.indexOf('\\'));

					File subfol = new File(actFileNameIncPath + File.separator + subFolName);
					if (!subfol.exists()) subfol.mkdirs();

					actFileNameIncPath = actFileNameIncPath + File.separator + subFolName;

					filenameincpath = filenameincpath.substring(filenameincpath.indexOf('\\') + 1);
				}

				String filename = entry.getName().substring(entry.getName().lastIndexOf('\\') + 1);
				unzip(zis, actFileNameIncPath + File.separator + filename);
			}
			else if (entry.getName().lastIndexOf('/') != -1)
			{
				String filenameincpath = entry.getName();

				String actFileNameIncPath = dirpath;

				while (filenameincpath.indexOf('/') != -1)
				{
					String subFolName = filenameincpath.substring(0, filenameincpath.indexOf('/'));
					File subfol = new File(actFileNameIncPath + File.separator + subFolName);
					if (!subfol.exists()) subfol.mkdirs();

					actFileNameIncPath = actFileNameIncPath + File.separator + subFolName;

					filenameincpath = filenameincpath.substring(filenameincpath.indexOf('/') + 1);
				}

				String filename = entry.getName().substring(entry.getName().lastIndexOf('/') + 1);
				unzip(zis, actFileNameIncPath + File.separator + filename);
			}
			else
				unzip(zis, dirpath + File.separator + entry.getName());
		}
		zis.close();
	}

	/**
	 * write zip file to disk
	 *
	 * @param zis
	 * @param name
	 * @throws IOException
	 */
	private void unzip(ZipInputStream zis, String name) throws Exception
	{
		BufferedOutputStream dest = null;
		int count;
		byte data[] = new byte[BUFFER];
		try
		{
			// write the files to the disk
			FileOutputStream fos = new FileOutputStream(name);
			dest = new BufferedOutputStream(fos, BUFFER);
			while ((count = zis.read(data, 0, BUFFER)) != -1)
			{
				dest.write(data, 0, count);
			}
			dest.flush();
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (dest != null) dest.close();
			}
			catch (IOException e1)
			{
			}
		}
	}

	/**
	 * creates zip file
	 *
	 * @param inputFile
	 *        - input directory that is to be zipped
	 * @param outputfile
	 *        - out zip file
	 * @throws Exception
	 */
	private void createZip(File inputFile, File outputfile) throws Exception
	{
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(outputfile));
		writeToZip(inputFile, zout, inputFile.getName());
		zout.close();
	}

	/**
	 * writes the zip file to browser
	 *
	 * @param file
	 *        - zip file to download
	 * @throws Exception
	 */
	private void download(File file) throws Exception
	{
		FileInputStream fis = null;
		ServletOutputStream out = null;
		try
		{
			String disposition = "attachment; filename=\"" + file.getName() + "\"";
			fis = new FileInputStream(file);

			FacesContext cxt = FacesContext.getCurrentInstance();
			ExternalContext context = cxt.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) context.getResponse();
			response.setContentType("application/zip"); // application/zip
			response.addHeader("Content-Disposition", disposition);
			// Contributed by Diego for ME-233
			response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "public, post-check=0, must-revalidate, pre-check=0");

			out = response.getOutputStream();

			int len;
			byte buf[] = new byte[102400];
			while ((len = fis.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}

			out.flush();
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (out != null) out.close();
			}
			catch (IOException e1)
			{
			}

			try
			{
				if (fis != null) fis.close();
			}
			catch (IOException e2)
			{
			}
		}
	}

	/**
	 * @param inputFile
	 *        - file to zip
	 * @param zout
	 *        - zip output stream
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void writeToZip(File inputFile, ZipOutputStream zout, String baseFileName) throws FileNotFoundException, IOException
	{
		File files[] = inputFile.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			if (files[i].isFile())
			{
				FileInputStream in = new FileInputStream(files[i]);

				String name = null;
				String filepath = files[i].getAbsolutePath();
				// name = filepath.substring(filepath.indexOf(baseFileName)
				// + baseFileName.length() + 1);

				// fix by Raul Enrique Mengod Lopez
				name = filepath.substring(filepath.indexOf(File.separator + baseFileName + File.separator) + baseFileName.length() + 2);
				zout.putNextEntry(new ZipEntry(name));

				// Transfer bytes from the file to the ZIP file
				int len;
				byte buf[] = new byte[102400];
				while ((len = in.read(buf)) > 0)
				{
					zout.write(buf, 0, len);
				}
				zout.closeEntry();
				in.close();
			}
			else
			{
				writeToZip(files[i], zout, baseFileName);
			}
		}
	}

	/**
	 * get MeleteSiteAndUserInfo
	 *
	 * @return
	 */
	private MeleteSiteAndUserInfo getMeleteSiteAndUserInfo()
	{
		if (meleteSiteAndUserInfo == null)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			ValueBinding binding = Util.getBinding("#{meleteSiteAndUserInfo}");
			meleteSiteAndUserInfo = (MeleteSiteAndUserInfo) binding.getValue(context);

			return meleteSiteAndUserInfo;
		}
		else
			return meleteSiteAndUserInfo;
	}

	/**
	 * creates manifest metadata - course title and description
	 *
	 * @param manifestFile
	 *        - manifest file
	 * @return - the manifest element
	 * @throws Exception
	 */
	private Element createManifestMetadata(File manifestFile, MeleteExportService meleteExportService) throws Exception
	{
		try
		{

			Element manifest = meleteExportService.getManifest(manifestFile);

			// create manifest metadata
			Element manifestMetadata = meleteExportService.createManifestMetadata();

			// imsmd:lom
			Element imsmdlom = meleteExportService.createLOMElement("imsmd:lom", "lom");
			// imsmd:general
			Element imsmdgeneral = meleteExportService.createLOMElement("imsmd:general", "general");

			// title
			String title = getMeleteSiteAndUserInfo().getCourseTitle();
			if (title != null)
			{
				Element metadataTitle = meleteExportService.createMetadataTitle(title);
				imsmdgeneral.add(metadataTitle);
			}

			// description
			String description = getMeleteSiteAndUserInfo().getCourseDescription();
			if (description != null)
			{
				Element metadataDesc = meleteExportService.createMetadataDescription(description);
				imsmdgeneral.add(metadataDesc);
			}

			imsmdlom.add(imsmdgeneral);
			manifestMetadata.add(imsmdlom);

			manifest.add(manifestMetadata);

			return manifest;

		}
		catch (Exception e)
		{
			throw e;
		}
	}

	/**
	 * @return Returns the moduleService.
	 */
	public ModuleService getModuleService()
	{
		return moduleService;
	}

	/**
	 * @param moduleService
	 *        The moduleService to set.
	 */
	public void setModuleService(ModuleService moduleService)
	{
		this.moduleService = moduleService;
	}

	/**
	 * @return Returns the uploadmax.
	 */
	public String getUploadmax()
	{
		int uploadmax = ServerConfigurationService.getInt("content.upload.ceiling", 50);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext.getExternalContext().getRequestParameterMap().get("showMessage") != null)
		{
			String show = (String) facesContext.getExternalContext().getRequestParameterMap().get("showMessage");

			if (show == null || show.length() == 0) return new Integer(uploadmax).toString();

			ResourceLoader bundle = new ResourceLoader("org.etudes.tool.melete.bundle.Messages");
			String errMsg = bundle.getString("error_importing_large");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error_importing_large", errMsg));
		}
		return new Integer(uploadmax).toString();
	}

	/**
	 * @param meleteExportService
	 *        The meleteExportService to set.
	 */
	public void setMeleteExportService(MeleteExportService meleteExportService)
	{
		this.meleteExportService = meleteExportService;
	}

	public void setMeleteExportScormService(MeleteExportService meleteExportScormService)
	{
		this.meleteExportScormService = meleteExportScormService;
	}

	/**
	 * @param meleteImportService
	 *        The meleteImportService to set.
	 */
	public void setMeleteImportService(MeleteImportService meleteImportService)
	{
		this.meleteImportService = meleteImportService;
	}

	/**
	 * @return the selectedModules
	 */
	public List<String> getSelectedModules()
	{
		return this.selectedModules;
	}

	/**
	 * @param selectedModules
	 *        the selectedModules to set
	 */
	public void setSelectedModules(List<String> selectedModules)
	{
		this.selectedModules = selectedModules;
		logger.debug("set list is" + selectedModules.toString());
	}

	/**
	 * @return the noFlag
	 */
	public boolean isNoFlag()
	{
		return this.noFlag;
	}

	/**
	 * @return the selectFormat
	 */
	public String getSelectFormat()
	{
		return this.selectFormat;
	}

	/**
	 * @param selectFormat
	 *        the selectFormat to set
	 */
	public void setSelectFormat(String selectFormat)
	{
		this.selectFormat = selectFormat;
	}
	
	/**
	 * @return boolean value of selectAllFlag
	 */
	public boolean getSelectAllFlag()
	{
		return selectAllFlag;
	}

	/**
	 * @param selectAllFlag boolean value
	 */
	public void setSelectAllFlag(boolean selectAllFlag)
	{
		this.selectAllFlag = selectAllFlag;
	}	
	
	/**
	 * @return integer value of listSize
	 */
	public int getListSize()
	{
		return listSize;
	}
	
	/**
	 * @param listSize integer value
	 */
	public void setListSize(int listSize)
	{
		this.listSize = listSize;
	}
}
