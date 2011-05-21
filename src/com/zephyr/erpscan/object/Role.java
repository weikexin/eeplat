/**
 * 
 */
package com.zephyr.erpscan.object;

/**
 * 
 * Class Name：Role.java
 * Note：角色表对象
 * Date: Jan 18, 2010,3:06:52 PM
 * @Author:liBing
 */
public class Role {

	private String priv_name;
	private int range;
	private String rangexp;
	
	/**
	 * 
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getPriv_name() {
		return priv_name;
	}
	
	public void setPriv_name(String priv_name) {
		this.priv_name = priv_name;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public String getRangexp() {
		return rangexp;
	}
	
	public void setRangexp(String rangexp) {
		this.rangexp = rangexp;
	}
	
		

}
