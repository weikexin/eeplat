package com.exedosoft.wf.pt;

import java.io.Serializable;

import com.exedosoft.plat.bo.BaseObject;

/**
 * @author   IBM
 */
public class NodeDenpendency extends BaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7312232521660342591L;

	private PTNode preNode;

	private PTNode postNode;

	/**
	 * @uml.property  name="condition"
	 */
	private String condition;
	
	private String showValue;
	
	
	
//	/**
//	 * @uml.property  name="nodeStateShow"
//	 */
//	private String nodeStateShow;

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public NodeDenpendency() {
	}

	/**
	 * @return
	 * @uml.property  name="preNode"
	 */
	public PTNode getPreNode() {
		return preNode;
	}

	/**
	 * @param preNode
	 * @uml.property  name="preNode"
	 */
	public void setPreNode(PTNode preNode) {
		this.preNode = preNode;
	}

	/**
	 * @return
	 * @uml.property  name="postNode"
	 */
	public PTNode getPostNode() {
		return postNode;
	}

	/**
	 * @param postNode
	 * @uml.property  name="postNode"
	 */
	public void setPostNode(PTNode postNode) {
		this.postNode = postNode;
	}

	/**
	 * @return
	 * @uml.property  name="condition"
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 * @uml.property  name="condition"
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

//	/**
//	 * @return
//	 * @uml.property  name="nodeStateShow"
//	 */
//	public String getNodeStateShow() {
//		return nodeStateShow;
//	}
//
//	/**
//	 * @param nodeStateShow
//	 * @uml.property  name="nodeStateShow"
//	 */
//	public void setNodeStateShow(String nodeStateShow) {
//		this.nodeStateShow = nodeStateShow;
//	}
//	
	

}
