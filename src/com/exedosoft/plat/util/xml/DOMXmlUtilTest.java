package com.exedosoft.plat.util.xml;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;

import java.io.InputStream;
import java.io.BufferedInputStream;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DOMXmlUtilTest {
	
	public static void main(String[] args) throws SAXException, IOException{
		
		
		
		
		
		
		try {
			
			StringBufferInputStream sr = new StringBufferInputStream("<a><c  gender='nan'/> </a>");
			 Document doc   = DOMXmlUtil.getInstance().getDocumentBuilder().parse(sr);
			 
			 
			 
			 
			 

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// InputStream is = InputStream.class.
		 
//		SimpleXMLUtil  sxu = new SimpleXMLUtil("C:\\Documents and Settings\\IBM\\桌面\\申请xml文件\\探矿权\\ExpXML_2.xml");
//		String applyId = sxu.getProperty("DATA.ID_APPLYID");
//		sxu.setProperty("DATA.ID_LICENCEID", "22222222");
//		System.out.println("ApplyID:::::" + applyId);
		
		
	}

}
