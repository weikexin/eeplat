package com.exedosoft.plat.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class SessionAttribute {
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	private static SessionAttribute sessionAttribute ;
	
	public static SessionAttribute getInstance(){
		if(sessionAttribute == null){
			sessionAttribute = new SessionAttribute();
			return sessionAttribute ;
		}else {
			return sessionAttribute ;
		}
		
	}
	
	public Map<String,Object> getAttributeMap(){
		return map ;
	}
	
	public Map getOptionsMap(){
		Map map = new HashMap();
		DOService service = DOService.getService("cms_options_list");
		BOInstance bo = new BOInstance();
		List l = service.invokeSelect(bo);
		if (!l.isEmpty()){
			bo = (BOInstance) l.get(0);
			map = bo.getMap();
			map.put("comment_redirect_url", "/" + DOGlobals.URL + "/exedo/webv3/template/cms/index.ftl");
		}
		return map ;
	}
	
	public String getCurrentTheme(){
		String templatePath = "" ;
		DOService service  = DOService.getService("cms_options_browse_by_key");
		BOInstance bo = new BOInstance();
		bo.putValue("opt_key", "themes_dir") ;
		List<BOInstance> l = service.invokeSelect(bo);
		if ( !l.isEmpty()){
			templatePath = "" + l.get(0).getValue("opt_value");
		}else {
			templatePath = "test";
		}
		return templatePath ;
	}

}
