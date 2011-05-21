package com.exedosoft.wf.pt;

import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.bo.DOBO;
/**
 * 工作流变量的概念很重要 。工作流变量可以指向主表单，各种判断的值等。 现在的工作流变量只考虑和ProcessTemplate 关联。 暂时不考虑和PTNode 关联。 下一步考虑PTVar 和 DOParameter 的关系。 和PTNode 关联也是有一定的必要性的。 可以作为PTNode 对应Pane,Pane内表单的扩充.(表单本来就是独立的由Pane 决定的,下一步考虑.)
 * @author   anolesoft
 */
public class PTVar extends BaseObject {

	
	private static final long serialVersionUID = 4825992548081140898L;

	/**
	 * 暂时PTVar 只跟PT关联，不跟PTNode 关联。看下一步需求和应用的情况。
	 */
	public static final int SCOPE_PVAR = 1;

	public static final int SCOPE_NVAR = 2;
	

	public static final int TYPE_NUM = 1;

	public static final int TYPE_STRING = 2;

	public static final int TYPE_TEXT = 3;

	public static final int TYPE_DOC = 4;

	public static final int TYPE_IMAGE = 5;

	public static final int TYPE_OBJ = 6;

	public static final int TYPE_URL = 7;
	
	public static final int TYPE_BO = 10;


	/**
	 * @uml.property  name="varName"
	 */
	private String varName;

	/**
	 * @uml.property  name="varInitValue"
	 */
	private String varInitValue;

	/**
	 * @uml.property  name="showAsWfiName"
	 */
	private Boolean showAsWfiName;

	/**
	 * @uml.property  name="varScope"
	 */
	private Integer varScope;

	/**
	 * @uml.property  name="varType"
	 */
	private Integer varType;
	/**
	 * 只有vartype is Type_Bo 时,才起作用
	 * @uml.property  name="doBO"
	 */
	private DOBO doBO;
	
	private ProcessTemplate processTemplate;
	
	

	/** default constructor */
	public PTVar() {
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
	 * @param varScope
	 * @uml.property  name="varScope"
	 */
	public void setVarScope(Integer varScope) {
		this.varScope = varScope;
	}

	/**
	 * @param varType
	 * @uml.property  name="varType"
	 */
	public void setVarType(Integer varType) {
		this.varType = varType;
	}

	/**
	 * @return
	 * @uml.property  name="varInitValue"
	 */
	public java.lang.String getVarInitValue() {
		return this.varInitValue;
	}

	/**
	 * @param varInitValue
	 * @uml.property  name="varInitValue"
	 */
	public void setVarInitValue(java.lang.String varInitValue) {
		this.varInitValue = varInitValue;
	}


	/**
	 * @return
	 * @uml.property  name="showAsWfiName"
	 */
	public Boolean getShowAsWfiName() {
		return this.showAsWfiName;
	}

	/**
	 * @param showAsWfiName
	 * @uml.property  name="showAsWfiName"
	 */
	public void setShowAsWfiName(Boolean showAsWfiName) {
		this.showAsWfiName = showAsWfiName;
	}


	/**
	 * @return
	 * @uml.property  name="varType"
	 */
	public Integer getVarType() {
		return varType;
	}

	/**
	 * @return
	 * @uml.property  name="varScope"
	 */
	public Integer getVarScope() {
		return varScope;
	}

	/**
	 * @return
	 * @uml.property  name="doBO"
	 */
	public DOBO getDoBO() {
		return doBO;
	}

	/**
	 * @param doBO
	 * @uml.property  name="doBO"
	 */
	public void setDoBO(DOBO doBO) {
		this.doBO = doBO;
	}

	/**
	 * @return
	 * @uml.property  name="processTemplate"
	 */
	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	/**
	 * @param processTemplate
	 * @uml.property  name="processTemplate"
	 */
	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}



	/**
	 * 根据PTVar 返回输入(WFIParamter参数)对应的值
	 * 
	 * @param pv
	 * @param wfiIOMaps
	 * @return 输入(WFIParamter参数)对应的值
	 */
	// public String getCorrValue(Collection wfiIOMaps) {
	//
	// if(wfiIOMaps!=null){
	// for (Iterator it = wfiIOMaps.iterator(); it.hasNext(); ) {
	// WFIParameter wp = (WFIParameter) it.next();
	// //////////////////(工作流启动参数)对应的值
	// if (getVarName() != null &&
	// getVarName().equalsIgnoreCase(wp.getKey())) {
	// return wp.getValue();
	// }
	// }
	// }
	// return null;
	// }
	/**
	 * 根据PTVar 返回输入(WFIParamter参数)对应的值
	 * 
	 * @param pv
	 * @param wfiIOMaps
	 * @return 输入(WFIParamter参数)对应的值
	 */
	// public byte[] getCorrBytes(Collection wfiIOMaps) {
	//
	// if(wfiIOMaps!=null){
	// for (Iterator it = wfiIOMaps.iterator(); it.hasNext(); ) {
	// WFIParameter wp = (WFIParameter) it.next();
	// //////////////////(工作流启动参数)对应的值
	// if (getVarName() != null &&
	// getVarName().equalsIgnoreCase(wp.getKey())) {
	// return wp.getBlob();
	// }
	// }
	// }
	// return null;
	// }
	/**
	 * 根据PTVar 返回输入(WFIParamter参数)对应的值
	 * 
	 * @param pv
	 * @param wfiIOMaps
	 * @return 输入(WFIParamter参数)对应的值
	 */
	// public String getCorrFileName(Collection wfiIOMaps) {
	//
	// if(wfiIOMaps!=null){
	// for (Iterator it = wfiIOMaps.iterator(); it.hasNext(); ) {
	// WFIParameter wp = (WFIParameter) it.next();
	// //////////////////(工作流启动参数)对应的值
	// if (getVarName() != null &&
	// getVarName().equalsIgnoreCase(wp.getKey())) {
	// return wp.getFileName();
	// }
	// }
	// }
	// return null;
	// }
}
