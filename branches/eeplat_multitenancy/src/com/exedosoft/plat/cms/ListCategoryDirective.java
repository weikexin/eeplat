package com.exedosoft.plat.cms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class ListCategoryDirective extends CMSFreemarkerServlet implements TemplateDirectiveModel{
	
	private Map paramMap;
	Map data = new HashMap();
	public ListCategoryDirective(){
		
	}
	public ListCategoryDirective(Map map){
		this.paramMap = map ;
	}

	public void execute(Environment arg0, Map arg1, TemplateModel[] arg2,
			TemplateDirectiveBody arg3) throws TemplateException, IOException {
		Map data = new HashMap();
		TemplateModel mdoel ;
		//键值对参数
		for(Iterator paramiter = arg1.entrySet().iterator(); paramiter.hasNext();){
			Map.Entry<Object, Object> ent = (Entry<Object, Object>) paramiter.next();
			arg0.setGlobalVariable(ent.getKey().toString(), (TemplateModel) ent.getValue());
		}
		DOService dao = DOService.getService("cms_category_list");
		BOInstance bo = new BOInstance();
		List l = dao.invokeSelect(bo);
		List value = new ArrayList();
		if (arg3 != null){
			for(int i  = 0 ; i < l.size() ; i++ ){
				bo = (BOInstance) l.get(i);
				data.put("cat_id", bo.getValue("cat_id"));
				data.put("cat_name", bo.getValue("cat_name"));
				data.put("cat_posts_count", bo.getValue("pcount"));
				data.put("url", "?cat_id=" + bo.getValue("cat_id"));
				mdoel = arg0.getConfiguration().getObjectWrapper().wrap(data);
			
				arg0.setGlobalVariable("dataMap", mdoel);
				arg3.render(arg0.getOut());
			}
		}
	}

}
