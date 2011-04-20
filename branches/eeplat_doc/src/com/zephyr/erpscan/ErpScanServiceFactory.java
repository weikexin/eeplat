/**
 * 
 */
package com.zephyr.erpscan;

import javax.servlet.ServletException;

import com.zephyr.erpscan.framework.util.IConstants;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * @author t
 *
 */
public class ErpScanServiceFactory implements IErpScanServiceFactory, PlugIn {
	
	private ActionServlet servlet = null;
	
	private String serviceClassname = "com.zephyr.erpscan.ErpScanServiceImpl";
	
	private String serviceSearchPicClassname = "com.zephyr.erpscan.SearchPicServiceImpl";
	
	private String serviceManagePicClassname = "com.zephyr.erpscan.ManagePicServiceImpl";

	private String serviceUploadPicClassname = "com.zephyr.erpscan.UploadPicServiceImpl";

	private String serviceDatabackupClassname = "com.zephyr.erpscan.PicDataBackupServiceImpl";
	
	private String serviceUserMngClassname = "com.zephyr.erpscan.UserManageServiceImpl";

	private String servicePageInfoClassname = "com.zephyr.erpscan.PageInfoServiceImpl";

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanServiceFactory#createService()
	 */
	public IErpScanService createService() throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		// TODO Auto-generated method stub

		try {

			IErpScanService instance = (IErpScanService) Class.forName(
					serviceClassname).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SearchPicServiceImpl createSearchPicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException {
		
		try {

			SearchPicServiceImpl instance = (SearchPicServiceImpl) Class.forName(
					serviceSearchPicClassname).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ManagePicServiceImpl createManagePicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException {
		
		try {

			ManagePicServiceImpl instance = (ManagePicServiceImpl) Class.forName(
					serviceManagePicClassname).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public UploadPicServiceImpl createUploadPicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException {
		
		try {
			UploadPicServiceImpl instance = (UploadPicServiceImpl) Class.forName(
					serviceUploadPicClassname ).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PicDataBackupServiceImpl createPicBackupService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException {
		
		try {
			PicDataBackupServiceImpl instance = (PicDataBackupServiceImpl) Class.forName(
					serviceDatabackupClassname  ).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserManageServiceImpl createUserMngService()
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException {
		// TODO Auto-generated method stub
		
		try{
			UserManageServiceImpl instance = (UserManageServiceImpl) Class.forName(this.serviceUserMngClassname).newInstance();
			
			instance.setServletContext(servlet.getServletContext());
			
			return instance;
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	/**
	 * for erp scan upload server only
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public UploadPicServiceImpl createUploadPicServiceForServer () throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException {
		
		try {
			UploadPicServiceImpl instance = (UploadPicServiceImpl) Class.forName(
					serviceUploadPicClassname ).newInstance();

			//instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void init(ActionServlet servlet, ModuleConfig config) throws ServletException{
		this.servlet = servlet;
		
		//save factory into application
		servlet.getServletContext().setAttribute(
				IConstants.SERVICE_FACTORY_KEY, this);
	}

	/* (non-Javadoc)
	 * @see com.zephyr.erpscan.IErpScanServiceFactory#destory()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		//do nothing now
	}

	public PageInfoServiceImpl createPageInfoService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

		try {

			PageInfoServiceImpl instance = (PageInfoServiceImpl) Class.forName(
					servicePageInfoClassname ).newInstance();

			instance.setServletContext(servlet.getServletContext());

			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
