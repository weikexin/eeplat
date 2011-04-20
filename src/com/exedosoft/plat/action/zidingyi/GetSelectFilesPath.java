package com.exedosoft.plat.action.zidingyi;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0 
 */

public class GetSelectFilesPath extends DOAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4798265805984034747L;
	private static Log log = LogFactory.getLog(GetSelectFilesPath.class);

	public GetSelectFilesPath() {
	}

	@Override
	public String excute()  {
		BOInstance archfile = new BOInstance();
		
		String[] checks = DOGlobals.getInstance().getSessoinContext()
		.getFormInstance().getValueArray("checkinstance");
		if (checks == null) {
			System.out.println("没有数据");
			archfile.putValue("filespath", null);
			this.setInstance(archfile);
			return DEFAULT_FORWARD;
		}
		StringBuffer filespath = new StringBuffer();
		try {
			
			DOService fileSer = DOService.getService("archfiles_browse_by_form_objuid");
			for(int i = 0; i < checks.length ; i++){
				List<BOInstance> fileList = fileSer.invokeSelect(checks[i]);	
				if(fileList != null && fileList.size() > 0) {
					BOInstance biFile = fileList.get(0);
					String file = biFile.getValue("filepath");
					if(file != null && isPic(file)){
						filespath.append(file).append(";");
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		archfile.putValue("filespath", filespath);
		this.setInstance(archfile);
		return DEFAULT_FORWARD;
	}

	/**
	 * 判断是否为图片类型
	 */

	public static boolean isPic(String fileName) {

		boolean isPic = false;
		fileName = fileName.toLowerCase();
		isPic = fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".bmp")
				|| fileName.endsWith(".gif") || fileName.endsWith(".png")
				|| fileName.endsWith(".tif") || fileName.endsWith(".pcx");

		return isPic;
	}
}