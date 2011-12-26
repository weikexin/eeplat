package com.exedosoft.plat.template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.TService;
import com.exedosoft.plat.ui.jquery.grid.GridList;
import com.exedosoft.plat.ui.jquery.grid.GridSupportMore;
import com.exedosoft.plat.ui.jquery.pane.TPaneTemplate;
import com.exedosoft.plat.util.DOGlobals;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HtmlTemplateGenerator {

	static Configuration cfg = null;

	static Configuration cfgDB = null;

	static String templateFolder = "webv3";
	

	private static Log log = LogFactory.getLog(HtmlTemplateGenerator.class);

	static {

		try {

			if ("ext".equals(DOGlobals.getValue("jslib"))) {
				templateFolder = "webext";
			}
			cfg = new Configuration();
			// cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
			// cfg.setSharedVariable("dataBind", new BindData2FormModel());
			// /缓存的设置，发布后应该加大，开发时可以是实时
			cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(1000, 2500));
			URL url = DOGlobals.class.getResource("/globals.xml");
			String aPath = url.getPath().toLowerCase();
			aPath = aPath.replaceAll("/[.]/", "/");
			String OUT_TEMPLATE = url.getPath().substring(0,
					aPath.indexOf("web-inf"))
					+ "/exedo/" + templateFolder + "/template/";
			cfg.setDefaultEncoding("UTF-8");
			FileTemplateLoader loaderFile = new FileTemplateLoader(new File(
					OUT_TEMPLATE));
			// DBTemplateLoader loaderDB = new DBTemplateLoader();
			// TemplateLoader[] loaders = new TemplateLoader[] { loaderFile,
			// loaderDB };
			// MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
			// cfg.setTemplateLoader(mtl);
			cfg.setTemplateLoader(loaderFile);
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			// ///////////////////////////////////////////以上为file
			// configuration的配置
			cfgDB = new Configuration();
			cfgDB.setDefaultEncoding("UTF-8");
			DBTemplateLoader loaderDB = new DBTemplateLoader();
			cfgDB.setTemplateLoader(loaderDB);
			cfgDB.setObjectWrapper(new DefaultObjectWrapper());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public HtmlTemplateGenerator() {

	}

	/**
	 * 生成静态文件
	 * 
	 * @param ftlTemplate
	 *            ftl模版文件
	 * @param contents
	 *            ftl要用到的动态内容
	 * @param savePath
	 *            文件保存路径
	 * @param saveFilename
	 *            保存文件名
	 * @throws IOException
	 * @throws TemplateException

	 */
	public static void create(String ftlTemplate, Map contents,
			String savePath, String saveFilename) throws IOException,
			TemplateException {
		Template temp = cfg.getTemplate(ftlTemplate);
		/* Merge data model with template */

		String realPath = DOGlobals.getInstance().getServletContext()
				.getRequest().getRealPath(savePath);
		System.out.println(saveFilename + ":" + realPath);
		File file = new File(realPath);
		if (!file.exists())
			file.mkdirs();

		 // TODO GOOGLE IO
		Writer out =  null;
//				new OutputStreamWriter(new FileOutputStream(realPath + "/"
//				+ saveFilename), "UTF-8");
		temp.process(contents, out);
		out.flush();
	}

	/**
	 * 根据模板加载内容，模板存储于文件系统
	 * 
	 * @param ftlTemplate
	 * @param contents
	 * @return
	 * @throws IOException
	 */
	public static String getContentFromTemplate(String ftlTemplate, Map contents)
			throws IOException {
		
//		log.info("Template Start::" + System.currentTimeMillis());
		
		Template temp = cfg.getTemplate(ftlTemplate, "utf-8");
		/* Merge data model with template */
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}

	/**
	 * 根据模板加载内容,模板存储于数据库
	 * 
	 * @param tempUId
	 * @param contents
	 * @return
	 * @throws IOException
	 */
	public static String getContentFromDBTemplate( String tempUId, Map contents)
			throws IOException {
		Template temp = cfgDB.getTemplate(tempUId, "utf-8");
		/* Merge data model with template */
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}
	
	public static String getHtml4GridDetail(DOGridModel model, 
			Map contents) throws IOException {
		Template temp = cfgDB.getTemplate(model.getController().getObjUid(), "utf-8");
		GridSupportMore gsm = new GridSupportMore();
		if(contents==null){
			contents = gsm.putData(model);
		}else{
			contents.putAll(gsm.putData(model));
		}
		/* Merge data model with template */
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}


	public static String getHtml4GridList(DOGridModel model, 
			Map contents) throws IOException {
		Template temp = cfgDB.getTemplate(model.getController().getObjUid(), "utf-8");
		GridList list = new GridList();
		if(contents==null){
			contents = list.putData(model);
		}else{
			contents.putAll(list.putData(model));
		}
		/* Merge data model with template */
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}
	
	public static String getHtml4Form(DOFormModel model, String tempUId,
			Map contents) throws IOException {
		Template temp = cfgDB.getTemplate(model.getController().getObjUid(), "utf-8");
		/* Merge data model with template */
		TService tService = new TService();
		if(contents==null){
			contents = tService.putData(model);
		}else{
			contents.putAll(tService.putData(model));
		}
		
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}
	
	public static String getHtml4Pane(DOPaneModel model,String tempUId,
			Map contents) throws IOException {
		Template temp = cfgDB.getTemplate(model.getController().getObjUid(), "utf-8");
		/* Merge data model with template */
		
		TPaneTemplate tpt = new TPaneTemplate();
		if(contents==null){
			contents = tpt.putData(model);
		}else{
			contents.putAll(tpt.putData(model));
		}
		
		StringWriter sw = new StringWriter();
		try {
			temp.process(contents, sw);
		} catch (TemplateException e) {
			throw new IOException(e.getMessage());
		}
		return sw.toString();
	}

	// 这个暂时不要 屏蔽掉
	// public static String getContentFromTemplate(String ftlTemplate, DOIModel
	// iModel)
	// throws IOException, TemplateException {
	// Template temp = cfg.getTemplate(ftlTemplate,"utf-8");
	// /* Merge data model with template */
	// StringWriter sw = new StringWriter();
	// temp.process(iModel, sw);
	// return sw.toString();
	// }

	public static void main(String[] args) {
		// DOFormModel fm =
		// DOFormModel.getFormModelByID("00456907f2ac44088d186fba5ec08909");
		// // Map fm = new HashMap();
		// fm.put("l10n", "中故宫");
		// try {
		// String s =
		// HtmlTemplateGenerator.getContentFromTemplate("form/Field.ftl", fm);
		// System.out.println(s);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TemplateException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
