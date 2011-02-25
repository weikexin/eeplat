package com.exedosoft.plat.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.util.DOGlobals;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class BlogInfo implements TemplateMethodModel{


	public Object exec(List arg0) throws TemplateModelException {
		String param = (String) arg0.get(0);
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "whl cms test site");
		map.put("site_url", "/" + DOGlobals.URL + "/exedo/webv3/template/cms");
		map.put("html_type", "html/text");
		map.put("charset", "UTF-8");
		map.put("root_path", DOGlobals.PRE_FULL_FOLDER);
		map.put("current_path",  DOGlobals.PRE_FULL_FOLDER + "template/cms/theme/" + SessionAttribute.getInstance().getCurrentTheme() + "/");
		if (param!=null){
			return map.get(param);
		}else{
			
			return "";
		}
	}

}
