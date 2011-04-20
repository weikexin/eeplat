package com.exedosoft.plat.cms;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import freemarker.cache.TemplateLoader;

/**
 * @author lenovo
 * 自定义的模板加载类 。
 */
public class DefaultTemplateLoader implements TemplateLoader{
	
	private String templateFileName ;
	private InputStream in ;
	private byte[] readerBuffer ; 
	
	public DefaultTemplateLoader(String fileName){
		this.templateFileName = fileName ;
	}

	public void setTemplateFileName(String fileName){
		this.templateFileName = fileName ;
	}

	public void closeTemplateSource(Object arg0) throws IOException {
		
		
	}


	public Object findTemplateSource(String arg0) throws IOException {
		
		return templateFileName;
	}


	public long getLastModified(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	public Reader getReader(Object arg0, String arg1) throws IOException {
		if (arg0 != null ){
		
			in = new FileInputStream(arg0.toString());
			readerBuffer = new byte[in.available()];
			in.read(readerBuffer);
		}
		return new StringReader(new String(readerBuffer,arg1));
	}

}
