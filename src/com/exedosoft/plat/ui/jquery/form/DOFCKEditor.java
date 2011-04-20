package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;


public class DOFCKEditor extends DOBaseForm {

	public String getHtmlCode(DOIModel property) {

		DOFormModel fm = (DOFormModel) property;

		StringBuffer buffer = new StringBuffer();
//		buffer.append("	<script type='text/javascript'>");
//		buffer.append("var sBasePath = '/")
//		.append(DOGlobals.URL)
//		.append("/FCKeditor/';");
//		
//		buffer.append(" var oFCKeditor = new FCKeditor( '")
//		.append(fm.getFullColName())
//		.append("' ) ;");
//		
//		buffer.append("oFCKeditor.BasePath	= sBasePath ; oFCKeditor.Height	= 300 ;");
//		
//		buffer.append("oFCKeditor.Value	= '")
//		.append(StringUtil.unFilterXss(fm.getValue()))
//		.append("' ;oFCKeditor.Create() ;");
//		buffer.append("</script>");
//		
		

		buffer.append("<input type='hidden' id='")
		.append(fm.getFullColName()).append("' name='")
		.append(fm.getFullColName())
		.append("' value='")
		.append(StringUtil.unFilterXss(fm.getValue()))
		.append("'/>");	
		
		buffer.append("<input type='hidden' id='")
		.append(fm.getFullColName())
		.append("___Config' ");
        buffer.append("	style='DISPLAY: none' type=hidden>");

		
		buffer.append("<IFRAME id='").append(fm.getFullColName())
		.append("___Frame' style='BORDER-RIGHT: 0px; PADDING-RIGHT: 0px; BORDER-TOP: 0px; PADDING-LEFT: 0px; BACKGROUND-IMAGE: none; PADDING-BOTTOM: 0px; MARGIN: 0px; BORDER-LEFT: 0px; WIDTH: 100%; PADDING-TOP: 0px; BORDER-BOTTOM: 0px; HEIGHT: 300px; BACKGROUND-COLOR: transparent' src='/")
		.append(DOGlobals.URL)
		.append("/FCKeditor/editor/fckeditor.html?InstanceName=")
		.append(fm.getFullColName())
		.append("&amp;Toolbar=Default' frameBorder=0 width='100%' scrolling=no height=300></IFRAME>");
		
		
		//		buffer.append("<div id='exedo_fckeditor'>\n");
//		
//		buffer.append("<input type='hidden' id='")
//		.append(fm.getFullColName()).append("' name='")
//		.append(fm.getFullColName())
//		.append("' value='")
//		.append(StringUtil.unFilterXss(fm.getValue()))
//		.append("'/>");
//		
//		
//		buffer.append("<input type='hidden' id='")
//		.append(fm.getFullColName())
//		.append("___Config' ");
//        buffer.append("	value='FlashUploadURL=/abp/FCKeditor/editor/filemanager/upload/simpleuploader?Type%3DFlash&FlashBrowserURL=/abp/FCKeditor/editor/filemanager/browser/default/browser.html?");
//        buffer.append("Type%3DFlash%26Connector%3Dconnectors/jsp/connector&ImageBrowserURL=/abp/FCKeditor/editor/filemanager/browser/default/browser.html?");
//        buffer.append("Type%3DImage%26Connector%3Dconnectors/jsp/connector&ImageUploadURL=/abp/FCKeditor/editor/filemanager/upload/simpleuploader?Type%3DImage&LinkUploadURL=/abp/FCKeditor/editor/filemanager/upload/simpleuploader?Type%3DFile&LinkBrowserURL=/abp/FCKeditor/editor/filemanager/browser/default/browser.html?");
//        buffer.append("Connector%3Dconnectors/jsp/connector&FontNames=宋体;黑体;隶书;楷体_GB2312;Arial;Comic Sans MS;Courier");
//        buffer.append("New;Tahoma;Times New Roman;Verdana'>");
//        buffer.append("<iframe id='")
//        .append(fm.getFullColName())
//        .append("___Frame' src='/abp/FCKeditor/editor/fckeditor.html?InstanceName=")
//        .append(fm.getFullColName())
//        .append("&Toolbar=Default' width='100%' height='320' frameborder='no' scrolling='no'></iframe></div>");
//        
//        buffer.append("</div>");
         
 		return buffer.toString();
	}

	
	
	

}
