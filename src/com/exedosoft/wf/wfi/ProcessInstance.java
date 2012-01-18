package com.exedosoft.wf.wfi;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.bo.DOBO;
//import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.HbmDAO;
//import com.exedosoft.plat.dao.WFDAO;
import com.exedosoft.wf.*;
import com.exedosoft.wf.pt.PTNode;
import com.exedosoft.wf.pt.ProcessTemplate;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;


/**
 * @todo  current在并行状态下会出问题，所以多个流程 并行的话不能用current的参数
 * @todo  应该加一种类型,子机发送失败
 */

public class ProcessInstance extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2602916331402415908L;

	public static int STATUS_INIT = 1;

	public static int STATUS_RUN = 2;

	public static int STATUS_FINISH = 3;

	public static int STATUS_HANGUP = 4;

	public static int STATUS_RESUMING = 5;

	public static int STATUS_FAILURE = 6;

	public static int STATUS_KILLED = 7;

	// /////////////////


	private String wfiName;


	private String ptName;

	private String wfiDesc;




	private String ptUid;


	private Integer exeStatus;

	/**
	 * 当前流程状态显示,这个状态不是上面的执行状态，而是节点执行到那一步需要显示什么状态。 当然，一个流程可能同时有多个活动节点。每个节点都有可能设置nodeStateShow。 以最后一个更新为准。 在实际应用中，如果一个节点的执行并不影响整个流程的状态，可以不设置nodeStateShow 这样引擎就不会处理。
	 */
	private String curState;


	private Timestamp curStateTime;


	private String curStateUser;


	private String rejectTxt;


	private Timestamp startTime;


	private String startUser;


	private String instanceUid;


	private String instanceUid2;


	private String instanceUid3;

	/** default constructor */
	public ProcessInstance() {
	}

	/** minimal constructor */
	public ProcessInstance(Long objUID, java.lang.String wfiName,
			java.lang.String ptName) {
		this.wfiName = wfiName;
		this.ptName = ptName;
	}

	/**
	 * @return
	 * @uml.property  name="wfiName"
	 */
	public java.lang.String getWfiName() {
		return this.wfiName;
	}

	/**
	 * @param wfiName
	 * @uml.property  name="wfiName"
	 */
	public void setWfiName(java.lang.String wfiName) {
		this.wfiName = wfiName;
	}

	/**
	 * @return
	 * @uml.property  name="ptName"
	 */
	public java.lang.String getPtName() {
		return this.ptName;
	}

	/**
	 * @param ptName
	 * @uml.property  name="ptName"
	 */
	public void setPtName(java.lang.String ptName) {
		this.ptName = ptName;
	}

	/**
	 * @return
	 * @uml.property  name="wfiDesc"
	 */
	public java.lang.String getWfiDesc() {
		return this.wfiDesc;
	}

	/**
	 * @param wfiDesc
	 * @uml.property  name="wfiDesc"
	 */
	public void setWfiDesc(java.lang.String wfiDesc) {
		this.wfiDesc = wfiDesc;
	}

	/**
	 * @param exeStatus
	 * @uml.property  name="exeStatus"
	 */
	public void setExeStatus(Integer exeStatus) {
		this.exeStatus = exeStatus;
	}


	public com.exedosoft.wf.pt.ProcessTemplate getProcessTemplate() {
		
		return DAOUtil.INSTANCE().getByObjUid(ProcessTemplate.class,
					this.ptUid);
//		HbmDAO dao = new HbmDAO();
//		ProcessTemplate pt = null;
//		try {
//			pt = (ProcessTemplate) dao.retrieve(ProcessTemplate.class,
//					this.ptUid);
//		} catch (DAOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return pt;
	}

	/**
	 * @return
	 * @uml.property  name="exeStatus"
	 */
	public Integer getExeStatus() {
		return exeStatus;
	}
	
	public static ProcessInstance getProcessInstance(String piUid){
		
		String hql = "select pi.* from do_wfi_processinstance pi where pi.objuid = ?";
		try {
			List list = DAOUtil.BUSI().select(ProcessInstance.class, hql, piUid);
			if (list != null && list.size() > 0) {
				return (ProcessInstance)list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static ProcessInstance getHisProcessInstance(String piUid){
		
		String hql = "select pi.* from do_wfi_his_processinstance pi where pi.objuid = ?";
		try {
			List list = DAOUtil.BUSI().select(ProcessInstance.class, hql, piUid);
			if (list != null && list.size() > 0) {
				return (ProcessInstance)list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	/**
	 * 无条件杀死一个工作流实例。
	 * 
	 * @throws WFException
	 */
	public static ProcessInstance getProcessInstanceByBusiUId(String aInstanceUid) {
//
//		WFDAO dao = new WFDAO();
		String hql = "select pi.* from do_wfi_processinstance pi where pi.INSTANCE_UID = ?";
		try {
			List list = DAOUtil.BUSI().select(ProcessInstance.class, hql, aInstanceUid);
			if (list != null && list.size() > 0) {
				return (ProcessInstance)list.get(0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 无条件杀死一个工作流实例。
	 * 
	 * @throws WFException
	 */
	public static boolean isExistsOfInstanceUid(String aInstanceUid) {
//
//		WFDAO dao = new WFDAO();
		String hql = "select pi.* from do_wfi_processinstance pi where pi.INSTANCE_UID = ?";
		try {
			List list = DAOUtil.BUSI().select(ProcessInstance.class, hql, aInstanceUid);
			if (list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 无条件杀死一个工作流实例。
	 * 
	 * @throws WFException
	 */
	public void killProcessInstance() throws WFException {
//		WFDAO dao = new WFDAO();
		setExeStatus(new Integer(ProcessInstance.STATUS_KILLED));
		try {
			DAOUtil.BUSI().store(this);
		} catch (Exception ex) {
			throw new WFException("杀死工作流实例时出错", ex);
		}

	}

	/**
	 * 挂起一个工作流实例。
	 * 
	 * @throws WFException
	 */
	public ProcessInstance hangUpProcessInstance() throws WFException {
//		WFDAO dao = new WFDAO();
		setExeStatus(new Integer(ProcessInstance.STATUS_HANGUP));
		try {
			DAOUtil.BUSI().store(this);
		} catch (Exception ex) {
			throw new WFException("挂起一个工作流时出错", ex);
		}
		return this;
	}

	/**
	 * 获得该工作流实例对应的所有变量
	 * 
	 * @return 变量集合
	 */
	public List retrieveVarInstances() {
//		WFDAO dao = new WFDAO();
		String hql = "select vi.* from do_wfi_varinstance vi where vi.PI_UID= ?";
		try {
			return DAOUtil.BUSI().select(VarInstance.class, hql, this.getObjUid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List retrieveNodeInstances() {
//
//		WFDAO dao = new WFDAO();
		String hql = "select ni.* from do_wfi_nodeinstance ni where ni.PI_UID = ?";
		try {
			return DAOUtil.BUSI().select(NodeInstance.class, hql, this.getObjUid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 继续执行本流程
	 */
	public ProcessInstance reStartProcess() throws WFException {
//		WFDAO dao = new WFDAO();
//		dao.setAutoClose(false);
		NodeInstance niStart = null;
		try {
			this.setExeStatus(new Integer(ProcessInstance.STATUS_RUN));
			DAOUtil.BUSI().store(this);
			for (Iterator it = this.retrieveNodeInstances().iterator(); it.hasNext();) {
				NodeInstance ni = (NodeInstance) it.next();
				if (ni.getNodeType() != null
						&& ni.getNodeType().intValue() == PTNode.TYPE_START) {
					niStart = ni;
					break;
				}
			}
		} catch (Exception ex) {
			throw new WFException("无法重新启动流程", ex);
		}
//		finally {
//			dao.closeSession();
//		}
		niStart.execute();
		return this;
	}

	/**
	 * 获取这个模板定义的Start 节点实例。
	 * 
	 * @return
	 */
	public NodeInstance getStartNode() {

		List nodes = this.retrieveNodeInstances();
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			NodeInstance node = (NodeInstance) it.next();

			if (node.getNodeType() != null
					&& node.getNodeType().intValue() == PTNode.TYPE_START) {
//				List preNodes = node.getPreNodes();
//				if (preNodes != null && preNodes.size() > 0) {
					return node;
				//}
			}
		}
		return null;

	}

	/**
	 * 获取开始的人工节点。
	 * 
	 * @return
	 */
	public NodeInstance getFirstActivityNode() {

		List nodes = this.retrieveNodeInstances();
		
		PTNode fnode = this.getProcessTemplate().getFirstActivityNode();
		if(fnode==null){
			return null;
		}
		for (Iterator it = nodes.iterator(); it.hasNext();) {
			NodeInstance node = (NodeInstance) it.next();
			
			if (node.getExeStatus()!=NodeInstance.STATUS_FINISH && node.getNodeUid().equals(fnode.getObjUid())) {
				return node;
			}
		}
		return null;

	}

	public String getCreator() {

		NodeInstance aStartNodeI = this.getStartNode();
		if (aStartNodeI != null) {
			for (Iterator it = aStartNodeI.getPostNodes().iterator(); it
					.hasNext();) {
				NodeInstance fan = (NodeInstance) it.next();
				if (fan.getPerformer() != null) {
					return fan.getPerformer();
				}

			}
		}
		return null;

	}

	public List getRunNodes() {

		List list = new ArrayList();

		for (Iterator it = this.retrieveNodeInstances().iterator(); it
				.hasNext();) {
			NodeInstance ni = (NodeInstance) it.next();
			if (ni.getExeStatus() != null
					&& ni.getExeStatus().intValue() == NodeInstance.STATUS_RUN) {
				list.add(ni);
			}
		}
		return list;

	}

//	public String getParentNodeInstanceUID() {

//		WFDAO dao = new WFDAO();
//		String hql = "select pi.wfiExeTask.parentNI from DO_WFI_PROCESSINSTANCE pi where pi.wfiExeTask is not null and pi.id = ?";
		
//		List list = null;
//		try {
//			list = dao.list(hql, this.getObjUid());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (list == null || list.size() == 0) {
//			return null;
//		} else {
//			return (String) list.get(0);
//		}
//	}

	/**
	 * 一种特殊的取回操作，直接取回到开始节点。
	 * 
	 * @throws WFException
	 */
	public void withDrawStartNode() throws WFException {

		if (getExeStatus() != null
				&& (getExeStatus().intValue() != ProcessInstance.STATUS_RUN)) {
			throw new WFException("流程已不在运行中，无法撤回！");
		}

//		WFDAO dao = new WFDAO();
//		dao.setAutoClose(false);
		try {
			NodeInstance startNode = this.getStartNode();

			List postNodes = startNode.getPostNodes();
			for (Iterator it = postNodes.iterator(); it.hasNext();) {
				NodeInstance aPost = (NodeInstance) it.next();
				aPost.setExeStatus(new Integer(NodeInstance.STATUS_RUN));
				aPost.setBackType(new Integer(NodeInstance.BACK_WITHDRAW));
				DAOUtil.BUSI().store(aPost);
			}

			for (Iterator it = this.retrieveNodeInstances().iterator(); it
					.hasNext();) {
				NodeInstance aNI = (NodeInstance) it.next();
				if (!(aNI != null && aNI.getNodeType() != null
						&& aNI.getNodeType().intValue() == PTNode.TYPE_START || (aNI
						.getBackType() != null))) {

					aNI.setExeStatus(new Integer(NodeInstance.STATUS_INIT));
					DAOUtil.BUSI().store(aNI);
				}
			}

		} catch (Exception e) {
			throw new WFException("处理回退时出错:" + this, e);
		} 
//		finally {
//			dao.closeSession();
//		}

	}

	/**
	 * 直接取回到开始节点，并且初始化流程把业务instanceuid置为空
	 * 
	 * @throws WFException
	 */
	public void withDrawStartNodeAndInitProcess() throws WFException {

		this.setInstanceUid(null);
//		WFDAO dao = new WFDAO();
		try {
			DAOUtil.BUSI().store(this);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		this.withDrawStartNode();
	}

	/**
	 * @return
	 * @uml.property  name="instanceUid"
	 */
	public String getInstanceUid() {
		return instanceUid;
	}

	public BOInstance getBOInstance() {
		return this.getProcessTemplate().getDoBO().getInstance(
				this.getInstanceUid());

	}

	/**
	 * @param instanceUid
	 * @uml.property  name="instanceUid"
	 */
	public void setInstanceUid(String instanceUid) {
		this.instanceUid = instanceUid;
	}



	public String toString() {
		return wfiName;
	}

	/**
	 * @return
	 * @uml.property  name="ptUid"
	 */
	public String getPtUid() {
		return ptUid;
	}

	/**
	 * @param ptUid
	 * @uml.property  name="ptUid"
	 */
	public void setPtUid(String ptUid) {
		this.ptUid = ptUid;
	}

	/**
	 * @return
	 * @uml.property  name="instanceUid2"
	 */
	public String getInstanceUid2() {
		return instanceUid2;
	}

	/**
	 * @param instanceUid2
	 * @uml.property  name="instanceUid2"
	 */
	public void setInstanceUid2(String instanceUid2) {
		this.instanceUid2 = instanceUid2;
	}

	/**
	 * @return
	 * @uml.property  name="instanceUid3"
	 */
	public String getInstanceUid3() {
		return instanceUid3;
	}

	/**
	 * @param instanceUid3
	 * @uml.property  name="instanceUid3"
	 */
	public void setInstanceUid3(String instanceUid3) {
		this.instanceUid3 = instanceUid3;
	}

	/**
	 * @return
	 * @uml.property  name="startTime"
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 * @uml.property  name="startTime"
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return
	 * @uml.property  name="startUser"
	 */
	public String getStartUser() {
		return startUser;
	}

	/**
	 * @param startUser
	 * @uml.property  name="startUser"
	 */
	public void setStartUser(String startUser) {
		this.startUser = startUser;
	}

	/**
	 * @return
	 * @uml.property  name="curState"
	 */
	public String getCurState() {
		return curState;
	}

	/**
	 * @param curState
	 * @uml.property  name="curState"
	 */
	public void setCurState(String curState) {
		this.curState = curState;
	}

	/**
	 * @return
	 * @uml.property  name="curStateTime"
	 */
	public Timestamp getCurStateTime() {
		return curStateTime;
	}

	/**
	 * @param curStateTime
	 * @uml.property  name="curStateTime"
	 */
	public void setCurStateTime(Timestamp curStateTime) {
		this.curStateTime = curStateTime;
	}

	/**
	 * @return
	 * @uml.property  name="curStateUser"
	 */
	public String getCurStateUser() {
		return curStateUser;
	}

	/**
	 * @param curStateUser
	 * @uml.property  name="curStateUser"
	 */
	public void setCurStateUser(String curStateUser) {
		this.curStateUser = curStateUser;
	}

	/**
	 * @return
	 * @uml.property  name="rejectTxt"
	 */
	public String getRejectTxt() {
		return rejectTxt;
	}

	/**
	 * @param rejectTxt
	 * @uml.property  name="rejectTxt"
	 */
	public void setRejectTxt(String rejectTxt) {
		this.rejectTxt = rejectTxt;
	}
	
	public static void main(String[] args) throws ExedoException{
		
//		ProcessInstance pi = new ProcessInstance();
//		pi.setCurState("aaa");
//		pi.setPtName("fsdfsfdsf");
//		pi.setInstanceUid("111111");
//		pi.setRejectTxt("fsdfdsf");
//		
//		com.exedosoft.wf.wfi.ProcessInstance pp = new com.exedosoft.wf.wfi.ProcessInstance();
//		
//		DAOUtil.BUSI().store(pi);
//			
		
		DOBO aBO = DOBO.getDOBOByName("do_wfi_processinstance");
		System.out.println("DOBO::::" + aBO);

	}

}
