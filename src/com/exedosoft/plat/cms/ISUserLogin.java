package com.exedosoft.plat.cms;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.util.DOGlobals;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class ISUserLogin implements TemplateMethodModel{
	

	public Object exec(List arg0) throws TemplateModelException {
		Map map = SessionAttribute.getInstance().getAttributeMap();
		Map userMap = (Map) map.get("userinfo");
		if (userMap == null || userMap.isEmpty() ){
			return false ;
		}
		if (userMap.get("login_result") == null ){
			return false; 
		}else if (userMap.get("login_result").equals("success")){
			return true;
		}else {
			return false ;
		}
	}
	
}
