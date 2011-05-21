package com.exedosoft.plat.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.StringUtil;

public class Login {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static boolean processUserLogin(HttpServletRequest request){
		
		String userName = request.getParameter("log");
		String password = request.getParameter("pwd");
		Map map = new HashMap();
		DOService service = DOService.getService("do_org_user_findbynameandpwd");
		BOInstance bo = new BOInstance();
		bo.putValue("user_login_id", userName);
		bo.putValue("password_MD5", password);
		
		List l = service.invokeSelect(bo);
		if (l.isEmpty()){
			map.put("login_result", "用户名或密码错误!");
			SessionAttribute.getInstance().getAttributeMap().put("userinfo", map) ;
			return false;
		}else{
			bo = (BOInstance) l.get(0);
			map.put("user_nicename", bo.getValue("user_nicename"));
			map.put("login_result", "success");
			
			//保存登录信息
			SessionAttribute.getInstance().getAttributeMap().put("userinfo", map) ;
			return true;
		}
	}
	
	public static void processUserLogOff(HttpServletRequest request){
		//清除登录信息
		SessionAttribute.getInstance().getAttributeMap().put("userinfo", null);
		
	}
	
	 

}
