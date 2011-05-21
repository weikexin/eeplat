/**
 * 
 */
package com.zephyr.erpscan.object;

import java.util.ArrayList;
import java.util.Vector;

import com.zephyr.erpscan.framework.exception.PropertyNotFoundException;
import com.zephyr.erpscan.framework.util.Global;


/**
 * 
 * Class Name：User.java
 * Note：用户表对象
 * Date: Jan 18, 2010,3:07:40 PM
 * @Author:liBing
 */
public class User {
	
	private String clkno;
	private String pswd;
	private int pswdflag;
	private String name;
	private int state;
	private String depno;
	private String role_name;
	
	// added for scaner
	private String iceIP;
	private String icePort;
	private String mgr_depno;
	
	private Vector roles = new Vector();

	public String getClkno() {
		return clkno;
	}

	public void setClkno(String clkno) {
		this.clkno = clkno;
	}

	public String getDepno() {
		return depno;
	}

	public void setDepno(String depno) {
		this.depno = depno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public int getPswdflag() {
		return pswdflag;
	}

	public void setPswdflag(int pswdflag) {
		this.pswdflag = pswdflag;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vector getRoles() {
		return roles;
	}

	public void setRoles(Vector roles) {
		this.roles = roles;
	}
	
	private static int INVALID_DOMAIN = 0;
	private static int MAX_EFFECT_DOMAIN = 4;
	
	public boolean hasPriv(String p){
    	if(null==this.getRoles() || null==p) {

    		return false;
    	}
    	
    	for(int i=0; i<this.getRoles().size(); ++i){
    		
    		try{
    			Role s = (Role)this.getRoles().get(i);
    			if(null==s){
    				 return false;
    			}
    			
    			int domain = s.getRange();
     			if(s.getPriv_name().equals(p)){
     				return ( domain > INVALID_DOMAIN && domain <= MAX_EFFECT_DOMAIN ) ? true : false;
    			}
    			
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	return false;
    }

	/**
	 * @return iceIP
	 */
	public String getIceIP() {
		try {
			return Global.getIP();
		} 
		catch (PropertyNotFoundException e) {
			
			return null;
		}

	}

	

	/**
	 * @return icePort
	 */
	public String getIcePort() {
		try {
			return Global.getPort();
		} 
		catch (PropertyNotFoundException e) {
			
			return null;
		}
	}

	public String getMgr_depno() {
		return mgr_depno;
	}

	public void setMgr_depno(String mgr_depno) {
		this.mgr_depno = mgr_depno;
	}
	
/*
	public ArrayList getMgr_depnoes(){
		String []deps = null;
		String mgr_depnos= null;
		ArrayList al = new ArrayList();
		deps = mgr_depno.split("@");
		
		for(int i=0;i<deps.length;i++){
			mgr_depnos=deps[i];
			al.add(mgr_depnos);
		
		}
		return al;
	}
*/
}
