/**
 * 
 */
package com.zephyr.erpscan;


/**
 * @author t
 *
 */
public interface IErpScanServiceFactory {
	
	public IErpScanService createService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public SearchPicServiceImpl createSearchPicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public ManagePicServiceImpl createManagePicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public UploadPicServiceImpl createUploadPicService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public PicDataBackupServiceImpl createPicBackupService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public void destroy();

	public UserManageServiceImpl createUserMngService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
	
	public PageInfoServiceImpl createPageInfoService() throws ClassNotFoundException,
	  IllegalAccessException, InstantiationException;
}
