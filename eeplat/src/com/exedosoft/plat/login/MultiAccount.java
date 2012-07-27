package com.exedosoft.plat.login;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.safe.TenancyValues;


/**
 * 账号 account 和  user 一定要分开
 * 
 * user 可能不能登录
 * 
 * account  是登录信息 可能和 计费相关。
 * @author weikx
 *
 */

public class MultiAccount extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4475309511115937205L;
	private String name;
	private String tenancyId;
	private String password;
	private String asrole;
	private String default_app_uid;
	private String creator;
	private String modifier;
	private String modifyDate;
	private String mVersion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenancyId() {
		return tenancyId;
	}

	public void setTenancyId(String tenancyId) {
		this.tenancyId = tenancyId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getmVersion() {
		return mVersion;
	}

	public void setmVersion(String mVersion) {
		this.mVersion = mVersion;
	}
	
	public String getDefault_app_uid() {
		return default_app_uid;
	}

	public void setDefault_app_uid(String defaultAppUid) {
		default_app_uid = defaultAppUid;
	}

	
	
	
	public String getAsrole() {
		return asrole;
	}

	public void setAsrole(String asrole) {
		this.asrole = asrole;
	}

	public static BOInstance findAccountToBI(String userName,String pwd){
		
		MultiAccount ma = findAccount(userName,pwd);
		if(ma==null){
			return null;
		}
		BOInstance bi = new BOInstance();
		bi.fromObject(ma);
		return bi;
	}
	
	public static MultiAccount findAccount(String userName,String pwd){
		
		if(userName==null || pwd==null){
			return null;
		}
		
		MultiAccount ma = DAOUtil.currentDataSource("/ds_multi.xml").getBySql(
				MultiAccount.class, "select * from multi_account where name=? and password=?",userName,StringUtil.MD5(pwd));
		
		if(ma!=null && ma.getName()!=null){
			return ma;
		}
		
		return null;
	} 
	
	
	public static MultiAccount findAccount(String userName,String pwd,String tenantId){
		
		if(userName==null || pwd==null){
			return null;
		}
		
		MultiAccount ma = DAOUtil.currentDataSource("/ds_multi.xml").getBySql(
				MultiAccount.class, "select * from multi_account where name=? and password=? and  tenancyId=?",userName,StringUtil.MD5(pwd),tenantId);
		
		if(ma!=null && ma.getName()!=null){
			return ma;
		}
		
		return null;
	} 
	
	public static void deleteAccount(String accountUid){

		System.out.println("Delete multi_account :::" + accountUid);
		try {
			DAOUtil.currentDataSource("/ds_multi.xml")
			.delete("delete from  multi_account where objuid = ?",
					accountUid
					);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void storeAccount(BOInstance<?> paraInstance){

		
		String objuid = paraInstance.getUid();
		String name = paraInstance.getValue("user_code");
		TenancyValues tv = DOGlobals.getInstance().getSessoinContext()
		.getTenancyValues();
		String tenancyId = paraInstance.getValue("tenancyId");
		if(tenancyId==null){
			tenancyId = tv.getTenant().getValue("name");
		}
		String password = paraInstance.getValue("password");
		password = StringUtil.MD5(password);

		String asrole = "0";
		String default_app_uid = "ff80808131275dcc0131275e2fdf0001";
		
		
		try {
			DAOUtil.currentDataSource("/ds_multi.xml")
			.store("insert into multi_account(objuid,name,tenancyid,password,asrole,default_app_uid) values(?,?,?,?,?,?)",
					objuid,
					name,
					tenancyId,
					password,
					asrole,
					default_app_uid
					);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

		MultiAccount ma = MultiAccount.findAccount("jlf@jlf.com", "1");
		
		System.out.println("MultiAccount:::" + ma);
		
		
		BOInstance bi = MultiAccount.findAccountToBI("jlf@google.com", "2");
		
		System.out.println("BOInstance:::" + bi);

	//	System.out.println(ms);

	}

}
