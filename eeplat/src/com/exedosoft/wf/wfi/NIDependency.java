package com.exedosoft.wf.wfi;

import java.util.List;
import java.io.Serializable;

import com.exedosoft.plat.bo.BaseObject;
//import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.HbmDAO;

/**
 * @author   IBM
 */
public class NIDependency extends BaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4175695837161362879L;

	private NodeInstance preNodeInstance;

	private NodeInstance postNodeInstance;

	/**
	 * @uml.property  name="condition"
	 */
	private String condition;
	
	/**
	 * @uml.property  name="nodeStateShow"
	 */
	
   private String showValue;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getShowValue() {
		return showValue;
	}

	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}

	public NIDependency() {
	}

	/**
	 * @return
	 * @uml.property  name="preNodeInstance"
	 */
	public NodeInstance getPreNodeInstance() {
		return preNodeInstance;
	}

	/**
	 * @param preNodeInstance
	 * @uml.property  name="preNodeInstance"
	 */
	public void setPreNodeInstance(NodeInstance preNodeInstance) {
		this.preNodeInstance = preNodeInstance;
	}

	/**
	 * @return
	 * @uml.property  name="postNodeInstance"
	 */
	public NodeInstance getPostNodeInstance() {
		return postNodeInstance;
	}

	/**
	 * @param postNodeInstance
	 * @uml.property  name="postNodeInstance"
	 */
	public void setPostNodeInstance(NodeInstance postNodeInstance) {
		this.postNodeInstance = postNodeInstance;
	}

	/**
	 * 判断关系是否已经存在
	 * @return
	 * @uml.property  name="nodeStateShow"
	 */
//	public static boolean isExist(Long preInstance, Long postInstance) {
//
//		if (preInstance == null || postInstance == null) {
//			return false;
//		}
//		HbmDAO dao = new HbmDAO();
//		String hql = "select nid from NIDependency nid where  nid.preNodeInstance.id = ? and nid.postNodeInstance.id = ?";
//		List list = null;
//		try {
//			list = dao.list(hql, preInstance.toString(), postInstance
//					.toString());
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (list != null && list.size() > 0) {
//			return true;
//		}
//		return false;
//	}



}
