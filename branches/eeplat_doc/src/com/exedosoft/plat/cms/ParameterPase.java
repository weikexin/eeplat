package com.exedosoft.plat.cms;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.exedosoft.plat.bo.BOInstance;

import freemarker.template.TemplateModel;

public class ParameterPase {
	
	
	@SuppressWarnings("unchecked")
	public static BOInstance paseParams(Map entyMap,Map requestMap){
		//放页面传递的参数。
		//从两个地方获取 一是freemarker指令的参数，另一个是httpServletRequest
		//最后都合并到这个BOInstance中，这不是最好的解决方案，需要有新思路来解决这个问题
		//TODO: 解决思路?
		BOInstance paramBO = new BOInstance();
		
		//拿自定义的指令参数
		String key ;
		String value ;
		Iterator runtimeParam = entyMap.entrySet().iterator();
		while (runtimeParam.hasNext()){
			Map.Entry ent = (Entry) runtimeParam.next();
			key = (String) ent.getKey();
			value = ((TemplateModel) ent.getValue()).toString();
			paramBO.putValue(key, value);
		}
		//拿httpservletrequest请求中的参数
		runtimeParam = null ;
		if (requestMap != null && !requestMap.isEmpty()){
			runtimeParam = requestMap.keySet().iterator();
			while (runtimeParam.hasNext()){
				key = (String) runtimeParam.next();
				Object obj = requestMap.get(key);
				if (obj instanceof String[]){
					value = ((String[]) obj)[0];
					paramBO.putValue(key, value);
				}
			}
		}
		return paramBO;
		
	}

}
