package com.exedosoft.plat.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.DOApplication;



public class ReadTxtFile {

	public ReadTxtFile() {
		// TODO Auto-generated constructor stub
	}

	
	void copyDir(DOApplication dop) {

		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf"));

		try {
			StringUtil.copyDirectiory(prefix + dop.getName(), prefix
					+ "exedo/baseproject/");

			File indexFile = new File(prefix + dop.getName() +"/index.jsp");
			


			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(indexFile),"utf-8"));   
			StringBuffer sb = new StringBuffer();
			while (true) {
				String aLine = in.readLine();
				if(aLine==null){
					break;
				}
				System.out.println(aLine);
				if(aLine.indexOf("pane_dorgauth.pml")!=-1){
					aLine =	aLine.replace("pane_dorgauth", "pane_aaaaa" + dop.getName()); 
				}
				sb.append(aLine).append("\n");//\n\r
			}
			in.close();

			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new  FileOutputStream(indexFile),"utf-8"));
			out.append(sb.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(prefix);

	}



	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		URL url = ReadTxtFile.class.getResource("/fm_change.ftl");

		
		File file = new File(url.getFile());
		

		FileReader fr = new FileReader(file);
		BufferedReader in =	new BufferedReader(	fr);
		
//		List  allChs = new ArrayList();
		StringBuffer sb = new StringBuffer();
		while (true) {

			String aLine = in.readLine();
		
			
			if(aLine==null){
				break;
			}
			
			char[] chars = aLine.toCharArray();
		
			StringBuilder zuci = new StringBuilder();
		
			List lineChs = new ArrayList();
			for(int i = 0; i < chars.length ; i ++){
				
			   char aChar = chars[i];
			   int p = (int) chars[i];
			   ////大于160是汉字
				if (p > 160) {
					zuci.append(aChar);
					//System.out.println(aChar);
				}else{
					if(zuci.length()>0){
						//System.out.println(zuci);
						//allChs.add(zuci.toString());
						lineChs.add(zuci.toString());
						zuci.delete(0, zuci.length());
					}
				}
			}
			for(Iterator it = lineChs.iterator(); it.hasNext();){
				String aChi = (String)it.next();
				////这个地方获取拼音后应该加上随机数
				String theRep = "${" + PinYin.getPingyin(aChi) + "?if_exists}";
				aLine = aLine.replace(aChi, theRep);
				StringBuilder theOutStr =  new StringBuilder("<label name=\"");
				theOutStr.append(PinYin.getPingyin(aChi))
				.append("\">")
				.append(aChi)
				.append("</label>");

				System.out.println(theOutStr);
				
			}
			
			
			
			
			sb.append(aLine).append("\n");

		}
		
	
//		for(Iterator it = allChs.iterator();it.hasNext();){
//			
//			String aCh = (String)it.next();
//			
//			String theRep = "${" + PinYin.getPingyin(aCh) + "?if_exists}";
//			
//			//${dt_apply_usefullife_start?if_exists}
//			theAllString =  theAllString.replace(aCh, theRep);
//			
//			StringBuilder theOutStr =  new StringBuilder("<label name=\"");
//			theOutStr.append(PinYin.getPingyin(aCh))
//			.append("\">")
//			.append(aCh)
//			.append("</label>");
//			
//		//	<label name="tbsj">填 表 时 间</label>
//			System.out.println(theOutStr);
//			
//		}
		
		
		File file2 = new File("c:\\first2.ftl");
	
		FileWriter fw = new FileWriter(file2);
		
		BufferedWriter out = new BufferedWriter(fw);
		out.write(sb.toString());
		out.flush();
		out.close();
		//System.out.println(allChs);
		
		
//		写文件
		
		//读文件同时写文件java  不运行，可以一遍读一遍创建新的文件。独到内存中再处理 o了。

//		String content = jTextField1.getText() ;
//		                FileWriter fw = null;
//		                try {
//		                    fw = new FileWriter("src\\home\\bb.properties");
//		                    fw.write(content);
//		                    fw.close();



	}

}
