package com.exedosoft.plat.login;

import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.I18n;
import com.exedosoft.plat.util.StringUtil;

public class ResetPasswordXes extends DOAbstractAction {

	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		//do.bx.user.update.passowrd
		
		// 	do.bx.user.findbynameAndPassword
		String old_password = this.actionForm.getValue("old_password");
		String new_password1 = this.actionForm.getValue("new_password1");
		String new_password2 = this.actionForm.getValue("new_password2");
		
		if(old_password==null || "".equals(old_password.trim())){
			this.setEchoValue(I18n.instance().get("旧密码不能为空"));
			return NO_FORWARD;
		}
		
		if(!new_password1.equals(new_password2)){
		   this.setEchoValue(I18n.instance().get("两次输入的新密码不一致"));
		   return NO_FORWARD;
		   
		}
		
		DOService findUser = DOService.getService("tbemployee.findby.loginid.pwd");
		String userName = DOGlobals.getInstance().getSessoinContext().getUser().getValue("FDLOGINID");
	  
		List users =  findUser.invokeSelect(userName,StringUtil.MD5(old_password));
		
		if(users==null || users.size()==0){
			this.setEchoValue(I18n.instance().get("您输入的的旧密码不正确"));
			return NO_FORWARD;
		}else{
			DOService updatePassword = DOService.getService("tbemployee.change.password");
			updatePassword.invokeUpdate(StringUtil.MD5(new_password1),DOGlobals.getInstance().getSessoinContext().getUser().getUid());
		}
	
		return DEFAULT_FORWARD;
	}

}
