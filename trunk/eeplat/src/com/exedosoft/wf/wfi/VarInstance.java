package com.exedosoft.wf.wfi;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.exedosoft.plat.bo.BaseObject;
//import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.HbmDAO;
//import com.exedosoft.plat.dao.WFDAO;

import com.exedosoft.wf.pt.PTVar;


import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
/**
 * @author   IBM
 */
public class VarInstance extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3132988971893039642L;

	/**
	 * @uml.property  name="varName"
	 */
	private String varName;

	/**
	 * @uml.property  name="varValue"
	 */
	private String varValue;

	/**
	 * @uml.property  name="ptVarUid"
	 */
	private String ptVarUid;

	private ProcessInstance processInstance;

	public VarInstance() {
	}

	/**
	 * @return
	 * @uml.property  name="varName"
	 */
	public java.lang.String getVarName() {
		return this.varName;
	}

	/**
	 * @param varName
	 * @uml.property  name="varName"
	 */
	public void setVarName(java.lang.String varName) {
		this.varName = varName;
	}

	/**
	 * @return
	 * @uml.property  name="varValue"
	 */
	public java.lang.String getVarValue() {
		return this.varValue;
	}

	/**
	 * @param varValue
	 * @uml.property  name="varValue"
	 */
	public void setVarValue(java.lang.String varValue) {
		this.varValue = varValue;
	}

	/**
	 * 根据名称和模板ID或节点ID获取模板变量
	 * 
	 * @param varName
	 * @param relFk
	 * @return
	 */
	public static VarInstance getInstanceByName(String varName, String pi_uid) {
		String hql = "select vi.* from do_wfi_varinstance vi where vi.varName = ? and vi.PI_UID= ?";
		return DAOUtil.BUSI().getBySql(VarInstance.class,hql,varName,pi_uid);

//		WFDAO dao = new WFDAO();
//		List list = null;
//		try {
//			list = dao.list(hql, varName, pi_uid);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (list != null && list.size() > 0) {
//			return (VarInstance) list.get(0);
//		}
//		return null;
	}

	/**
	 * 根据模板或NodeInstance uid 获取变量实例列表
	 * @throws DAOException
	 */
	public static Collection getVarInstances(String pi_uid) {
		
		String hql = "select vi.* from do_wfi_varinstance vi where vi.PI_UID= ?";
		return DAOUtil.BUSI().select(VarInstance.class, hql,pi_uid);

//		WFDAO dao = new WFDAO();
//		return dao.list(hql, pi_uid);
	}

	/**
	 * @return
	 * @uml.property  name="processInstance"
	 */
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	/**
	 * @param processInstance
	 * @uml.property  name="processInstance"
	 */
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public PTVar getPtVar() {
		
		return DAOUtil.INSTANCE().getByObjUid(PTVar.class, this.ptVarUid);
//		HbmDAO dao = new HbmDAO();
//		PTVar ptVar = null;
//		try {
//			ptVar = (PTVar)dao.retrieve(PTVar.class,this.ptVarUid);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return ptVar;
	}
	
	
	public VarInstance store() {
		
		try {
			return (VarInstance)DAOUtil.INSTANCE().store(this);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * @return
	 * @uml.property  name="ptVarUid"
	 */
	public String getPtVarUid() {
		return ptVarUid;
	}

	/**
	 * @param ptVarUid
	 * @uml.property  name="ptVarUid"
	 */
	public void setPtVarUid(String ptVarUid) {
		this.ptVarUid = ptVarUid;
	}

	
	public boolean equals(Object o){
		return super.equals(o);
	}
	
	public int hashCode(){
		return super.hashCode();
	}
}
