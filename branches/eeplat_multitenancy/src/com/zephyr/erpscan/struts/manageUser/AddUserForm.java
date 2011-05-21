//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.zephyr.erpscan.struts.manageUser;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/** 
 * MyEclipse Struts
 * Creation date: 08-10-2006
 * 
 * XDoclet definition:
 * @struts.form name="addUserForm"
 */
public class AddUserForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables

	/** password property */
	private String password;

	/** role_name property */
	private String role_name;

	/** clkno property */
	private String clkno;

	/** depno property */
	private String depno;

	/** name property */
	private String name;
	
	/** mgr_depno property */
	private String mgr_depno;

	// --------------------------------------------------------- Methods

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		// TODO Auto-generated method stub
		ActionErrors errors = new ActionErrors();
		
		if(this.getClkno()==null || this.getClkno().length()<1){
			errors.add( ActionErrors.GLOBAL_MESSAGE,
	                  new ActionMessage("error.required.clkno" ));
		}
		if(this.getPassword()==null || this.getPassword().length()<1){
			errors.add( ActionErrors.GLOBAL_MESSAGE,
	                  new ActionMessage("error.required.password" ));
		}
		if(this.getName()==null || this.getName().length()<1){
			errors.add( ActionErrors.GLOBAL_MESSAGE,
	                  new ActionMessage("error.required.name" ));
		}
		if(this.getDepno()==null || this.getDepno().length()<1){
			errors.add( ActionErrors.GLOBAL_MESSAGE,
	                  new ActionMessage("error.required.depno" ));
		}
		if(this.getRole_name()==null || this.getRole_name().length()<1){
			errors.add( ActionErrors.GLOBAL_MESSAGE,
	                  new ActionMessage("error.required.role_name" ));
		}
		

		return errors;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// TODO Auto-generated method stub
		this.clkno = null;
		this.password = null;
		this.depno = null;
		this.name = null;
		this.role_name = null;
	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the role_name.
	 * @return String
	 */
	public String getRole_name() {
		return role_name;
	}

	/** 
	 * Set the role_name.
	 * @param role_name The role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	/** 
	 * Returns the clkno.
	 * @return String
	 */
	public String getClkno() {
		return clkno;
	}

	/** 
	 * Set the clkno.
	 * @param clkno The clkno to set
	 */
	public void setClkno(String clkno) {
		this.clkno = clkno;
	}

	/** 
	 * Returns the depno.
	 * @return String
	 */
	public String getDepno() {
		return depno;
	}

	/** 
	 * Set the depno.
	 * @param depno The depno to set
	 */
	public void setDepno(String depno) {
		this.depno = depno;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Set the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the mgr_depno
	 */
	public String getMgr_depno() {
		return mgr_depno;
	}

	/**
	 * @param mgr_depno the mgr_depno to set
	 */
	public void setMgr_depno(String mgr_depno) {
		this.mgr_depno = mgr_depno;
	}

}

