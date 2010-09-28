package com.exedosoft.wf.wfi;

import java.io.Serializable;

import com.exedosoft.plat.bo.BaseObject;

/**
 * 节点实例的权限不在保存在DO_Authorization 表里面。
 * 单独的权限表。
 * 权限的判读尽量只通过sql语句。
 * 做完建模后搞这一块
 * @author IBM
 *
 */
public class NodeInstanceAuth  extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2783516570467292021L;
	
	private String niUid;
	
	private String userUid;
	
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

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	private String col1;
	
	private String col2;
	

	
	
	

}
