package com.exedosoft.plat.login;

/**
 * 注意 我把 date 转换成了 datetime 注意改过来
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.MVCController;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;

//
// //修改本人密码
// public static final String MODIFY_SELF_PWD = "modify_self_pwd";
// //用户管理
// public static final String MANAGE_CLERK = "manage_clerk_p";
// //单据查询
// public static final String BROWSER_IMAGE = "browser_image_p";
// //扫描上传
// public static final String SCAN_UPDATE = "scan_update_p";
// //审核重复单据
// public static final String CHECK_REDUPLICATE = "check_reduplicate_p";

public class LoginAction2 extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5564360845175975061L;

	private static Log log = LogFactory.getLog(LoginAction2.class);
//	private static DOService findByCaCode = DOService.getService("do.bx.user.browse.findbycacode");

	
	

	public String excute() {
		List users = new ArrayList();
		
		if(DOGlobals.LOGIN_CA == DOGlobals.LOGIN_CA_YES){
			
//		    HttpServletRequest request = 	DOGlobals.getInstance().getServletContext().getRequest();
//		    String caID =  (String)request.getSession().getAttribute("UserId");
//		    if(caID==null || "".equals(caID.trim())){
//		    	this.setEchoValue("对不起，您没有通过CA认证！");
//		    	return "notpass";
//		    }
//		    System.out.println(caID);
//		    users = findByCaCode.invokeSelect(caID);
		}else{
			System.out.println(this.actionForm);
			users = service.invokeSelect();
		}
	
		if (users != null && users.size() > 0) {
			BOInstance user = (BOInstance) users.get(0);
			Date invalidTime = user.getDateValue("invalidTime");
			if (invalidTime != null) {
				System.out.println("该用户的过期时间::" + invalidTime);
				if (invalidTime.before(new Date(System.currentTimeMillis()))) {
					this.setEchoValue(I18n.instance().get("该用户账户已经过期！"));
					return NO_FORWARD;
				}
			}
			
			String fdstate = user.getValue("fdstate");
			if("0".equals(fdstate)){
				this.setEchoValue(I18n.instance().get("该用户已冻结，请跟系统管理员联系！"));
				return NO_FORWARD;
			}
			LoginMain.makeLogin(user,DOGlobals.getInstance().getServletContext().getRequest());

			return "success";
		} else {
			this.setEchoValue(I18n.instance().get("用户名或密码错误，请重试!"));
			return "notpass";
		}
	}
}


// ///////////海洋局专用
//
//if (this.actionForm.getValue("inner_user") != null
//		&& Integer.parseInt(maxDegree) < 6) {
//	this.setEchoValue("对不起，您只能以媒体用户进入!");
//	return "notpass";
//}

// ////////////海洋局专用

//BOInstance aDegreeIns = new BOInstance();
//aDegreeIns.putValue("secret_name", user.getName());
//aDegreeIns.putValue("secret_id", maxDegree);
//
//DOBO aDegreeBO = DOBO.getDOBOByName("sea.docsecret");
//DOGlobals.getInstance().getSessoinContext().putCorrInstance(
//		aDegreeBO, aDegreeIns);
// //////////////////海洋局文档系统
