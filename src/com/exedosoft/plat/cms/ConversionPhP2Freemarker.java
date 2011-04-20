package com.exedosoft.plat.cms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lenovo
 *  把wordpress主题中的PHP代码替换成freemarker代码 
 *  功能需要逐步完善 
 *  类名起的有点儿2
 */
public class ConversionPhP2Freemarker {
	
	static Map<String,String> globalMap = new HashMap<String,String>();
	static Map<String,String> postsMap = new HashMap<String,String>();
	static String[] fileName = new String[] {
		"index.php","header.php","footer.php","sidebar.php"
	};
	static {
		globalMap.put("<\\?php.bloginfo('template_directory').+?>", "${bloginfo('current_path')}");
		globalMap.put("<\\?php.bloginfo('name').+?>", "${bloginfo('name')}");
		globalMap.put("<\\?php.bloginfo('siteurl').+?>", "${bloginfo('site_url')}");
		globalMap.put("<\\?php.get_header().+?>", "<#include \"header.ftl\" />");
		globalMap.put("<\\?php.get_sidebar().+?>", "<#include \"sidebar.ftl\" />");
		globalMap.put("<\\?php.get_footer().+?>", "<#include \"footer.ftl\" />");
		globalMap.put("<\\?php.bloginfo('html_type').+?>", "${bloginfo('html_type')}");
		globalMap.put("<\\?php.bloginfo('charset').+?>", "${bloginfo('charset')}");
		globalMap.put("<\\?php.bloginfo('stylesheet_url').+?>", "${bloginfo('current_path')}/style.css");
		
		postsMap.put("<\\?php.the_title().+?>", "\\${dataMap.posts_title}");
		postsMap.put("<\\?php.+endwhile.+endif.+?>", "</@list_posts>\n</#if>");
		//postsMap.put("<\\?php.endif.+?>", "</#if>");
	}
	
	public static String readSourceFile(String filePath){
		byte[] b = null ;
		try {
			InputStream in = new FileInputStream(filePath);
			b = new byte[in.available()] ;
			in.read(b);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(b);
	}
	
	public static void startConversion(String filePath){
		String key = "";
		String value = ""; 
		String fileContent ;
		String tmpstr = "";
		String finalString = "" ;
		for(String nameKey : fileName){
			System.out.println(nameKey);
		    fileContent = readSourceFile(filePath + "/" + nameKey);
			String patternString =  "<\\?php.if.+endif.+?>";
			Pattern pattern = Pattern.compile(patternString, Pattern.DOTALL);
			Matcher matcher = pattern.matcher(fileContent) ;
			while (matcher.find()){
				Pattern tmpPattern = pattern.compile("<\\?php\\s?if\\s?.have_posts.+endif.+?>");
				Matcher m = tmpPattern.matcher(fileContent.substring(matcher.start(),matcher.end()));
				System.out.println(fileContent.substring(matcher.start(),matcher.end()));
				while (m.find()){
					finalString = m.replaceAll("<\\#if have_posts()>\n\t<\\@list_posts/>");
				}
				//在使用Matcher类的replaceAll方法前需要把替换内容的$符号替换掉。
				//否则会报错
				finalString = finalString.replace("$", "#@");
				//替换数据结构
				for(Iterator<String> it = postsMap.keySet().iterator(); it.hasNext();){
					key = it.next();
					finalString = finalString.replaceAll(key, postsMap.get(key));
				}

				finalString = finalString.replace("$", "#@");
				fileContent = matcher.replaceAll(finalString);
			}
			
			for(Iterator<String> it = globalMap.keySet().iterator(); it.hasNext();){
				key = it.next();
				fileContent = fileContent.replaceAll(key, globalMap.get(key));
			}
			//将#@替换为$
			fileContent = fileContent.replace("#@", "$");
			writeFreemarkerFile(filePath + "/" + nameKey.substring(0, nameKey.length() - 4) + ".ftl",fileContent);
		}
	}
	
	public static void writeFreemarkerFile(String file,String content){
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(content.getBytes("UTF-8"));
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String... argvs){
		ConversionPhP2Freemarker.startConversion("D:/asylmf");
//		String ps = "<\\?php.+endwhile.+endif.+?>";
//		Pattern p = Pattern.compile(ps,Pattern.DOTALL);
//		Matcher m = p.matcher("<?php endwhile; endif; ?>");
//		while (m.find()){
//			System.out.println("<?php endwhile; endif; ?>".replaceAll(ps, "</@list_posts>\n</#if>"));
//		}
	}
	
	
	

}
