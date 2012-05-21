package com.exedosoft.plat.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {
	
	private final static Log log = LogFactory.getLog(FreeMarkerUtils.class);
	
	private final static String STRINGTEMPLATENAME = "_STRINGTEMPLATENAME";
	
	/**
	 * 处理字符串模板后获得模板内容。
	 * @param map
	 * @param templateContent
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String processTemplate(Map<String, Object> map,String templateContent) throws IOException, TemplateException{
		return processTemplate(map, templateContent,Locale.CHINA,"GBK");
	}
	
	/**
	 * 处理字符串模板后获得模板内容。
	 * @param map
	 * @param templateContent
	 * @param locale
	 * @param encoding
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static String processTemplate(Map<String, Object> map,String templateContent,Locale locale,String encoding) throws IOException, TemplateException{
		Configuration cfg = new Configuration();
		cfg.setEncoding(locale, encoding);
		cfg.setWhitespaceStripping(true);
		cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(STRINGTEMPLATENAME, templateContent);
		cfg.setTemplateLoader(stringLoader);
		Template template = cfg.getTemplate(STRINGTEMPLATENAME, locale, encoding);
		StringWriter sw = new StringWriter();
		template.process(map, sw);
		return sw.toString();
	}
	
	
	/**
	 * 获取模板处理后的字符串
	 * @param clazz
	 * @param templateName
	 * @param map
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unchecked")
	public static String processTemplate(Class clazz,String templateName,Map<String, Object> map) throws IOException, TemplateException{
		StringWriter sw = new StringWriter();
		Template template = getTemplate(clazz, templateName);
		template.process(map, sw);
		log.debug("FreeMarker处理后信息:"+sw.toString());
		return sw.toString();
	}
	
	/**
	 * 获取Freemarker的模板
	 * @param clazz
	 * @param templateName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Template getTemplate(Class clazz,String templateName) throws IOException{
		log.info("加载FreeMarker的Template");
		log.debug("加载FreeMarker的Template:class"+clazz.getName()+",templateName:"+templateName);
		return getConfiguration(clazz).getTemplate(templateName);
	}
	

	/**
	 * 获取Freemarker的配置对象
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Configuration getConfiguration(Class clazz){
		return getConfiguration(clazz, "ftl", Locale.CHINA, "GBK");
	}
	
	/**
	 * 获取Freemarker的配置对象
	 * @param clazz
	 * @param pathPrefix
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Configuration getConfiguration(Class clazz,String pathPrefix){
		return getConfiguration(clazz, pathPrefix, Locale.CHINA, "GBK");
	}
	
	/**
	 * 获取Freemarker的配置对象
	 * @param clazz
	 * @param pathPrefix
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Configuration getConfiguration(Class clazz,String pathPrefix,String encoding){
		return getConfiguration(clazz, pathPrefix, Locale.CHINA, encoding);
	}
	
	/**
	 * 获取Freemarker的配置对象
	 * @param clazz
	 * @param pathPrefix
	 * @param encoding
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Configuration getConfiguration(Class clazz,String pathPrefix,Locale locale,String encoding){
		log.info("获取Freemarker的Configuration");
		log.debug("获取Freemarker的Configuration：class:"+clazz.getName()+",pathPrefix:"+pathPrefix+",locale:"+locale.getDisplayCountry()+",encoding:"+encoding);
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(clazz, pathPrefix);
		cfg.setEncoding(locale, encoding);
		cfg.setWhitespaceStripping(true);
		return cfg;
	}
	
	public static void main(String[] args) throws IOException, TemplateException {
		String content = "[#if 1==1]hello,${user}[/#if]";
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("user", "world");
		System.out.println(FreeMarkerUtils.processTemplate(map, content));
		
	}
}
