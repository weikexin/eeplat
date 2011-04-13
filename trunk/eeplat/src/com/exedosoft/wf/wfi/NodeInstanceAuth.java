package com.exedosoft.wf.wfi;

import java.io.Serializable;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BaseObject;

/**
 * 节点实例的权限不在保存在DO_Authorization 表里面。 单独的权限表。 权限的判读尽量只通过sql语句。 做完建模后搞这一块
 * 
 * @author IBM
 * 
 */
public class NodeInstanceAuth extends BaseObject implements Serializable {

	public static final int NOT_ACCESS = 0;

	// Field descriptor #242 I
	public static final int ISACCESS = 1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2783516570467292021L;

	private String niUid;

	private String userUid;

	private java.lang.Boolean authority;
	
	public NodeInstanceAuth(){
		
		
	}
	
	public NodeInstanceAuth(String aNiUid,String aUserUid,boolean isAccess){

		this.niUid = aNiUid;
		this.userUid = aUserUid;
		this.authority = Boolean.valueOf(isAccess);
	}

	public String getNiUid() {
		return niUid;
	}

	public void setNiUid(String niUid) {
		this.niUid = niUid;
	}

	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public java.lang.Boolean getAuthority() {
		return authority;
	}

	public void setAuthority(java.lang.Boolean authority) {
		this.authority = authority;
	}

	public boolean isAccess() {
		
		if (this.authority != null) {
			return this.authority.booleanValue();
		}
		return false;

	}
	
	public static void addNodeInstanceAuth(NodeInstanceAuth aNia){
		
		try {
			DAOUtil.BUSI().store(aNia);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean equals(Object o) {
		return super.equals(o);
	}

	public int hashCode() {
		return super.hashCode();
	}

}
