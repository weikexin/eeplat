package com.exedosoft.plat.login;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.StringUtil;

public class ResetPasswordXes4User extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605528414131574221L;

	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		//do.bx.user.update.passowrd
		
		// 	do.bx.user.findbynameAndPassword
		String userid = this.actionForm.getValue("userid");
		String new_password1 = this.actionForm.getValue("new_password1");
		String new_password2 = this.actionForm.getValue("new_password2");
		
		if(userid==null || "".equals(userid.trim())){
			this.setEchoValue(I18n.instance().get("用户名不能为空"));
			return NO_FORWARD;
		}
		if(!new_password1.equals(new_password2)){
		   this.setEchoValue(I18n.instance().get("两次输入的新密码不一致"));
		   return NO_FORWARD;
		}
		DOService updatePassword = DOService.getService("tbemployee.change.password.by.fdloginid");
		updatePassword.invokeUpdate(StringUtil.MD5(new_password1),userid);
		return DEFAULT_FORWARD;
	}

}
