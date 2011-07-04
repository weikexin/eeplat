package com.exedosoft.plat.multitenancy;

import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.util.StringUtil;

public class MultiAccount extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4475309511115937205L;
	private String name;
	private String tenancyId;
	private String password;
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
	
	
	
	public static BOInstance findBIUser(String userName,String pwd){
		
		MultiAccount ma = findUser(userName,pwd);
		if(ma==null){
			return null;
		}
		BOInstance bi = new BOInstance();
		bi.fromObject(ma);
		return bi;
	}
	
	public static MultiAccount findUser(String userName,String pwd){
		
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

	public static void main(String[] args) {

		MultiAccount ma = MultiAccount.findUser("jlf@google.com", "2");
		
		System.out.println("MultiAccount:::" + ma);
		
		
		BOInstance bi = MultiAccount.findBIUser("jlf@google.com", "2");
		
		System.out.println("BOInstance:::" + bi);

	//	System.out.println(ms);

	}

}
