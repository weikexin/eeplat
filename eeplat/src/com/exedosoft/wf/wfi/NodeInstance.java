package com.exedosoft.wf.wfi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.DOAuthorization;
import com.exedosoft.plat.bo.org.OrgAccountability;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.bo.org.OrgParterValue;
import com.exedosoft.plat.js.RunJs;
import com.exedosoft.plat.login.LoginMain;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.StringUtil;
import com.exedosoft.plat.ActionFactory;
import com.exedosoft.wf.WFAccess;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.WFJudge;
import com.exedosoft.wf.WFUtil;
import com.exedosoft.wf.pt.NodeDenpendency;
import com.exedosoft.wf.pt.PTNode;

/**
 * 每个模板的PTVars中至少有一个ptvar 是 TYPE_BO这种类型的。 Node Instance初始化时，
 * DOGlobals.getSessionContext.refresthUid() 在**修改过的地方这个一直没更新
 */

public class NodeInstance extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -799682928889075916L;

	public final static int STATUS_FREE = 0;

	public final static int STATUS_INIT = 1;

	public final static int STATUS_RUN = 2;

	public final static int STATUS_FINISH = 3;

	public final static int STATUS_REJECT = 7;

	public final static int STATUS_HANGUP = 4;

	public final static int STATUS_RESUMING = 5;

	/**
	 * 回退，驳回 一般是只被上层驳回
	 */
	public final static int BACK_RETURN = 1;
	/**
	 * 取回，自己主动取回
	 */
	public final static int BACK_WITHDRAW = 2;

	/**
	 * 全局设置属性 和上面定义的BACK_ 不是一个感念 缺省设置 。 继续一步步向上提交
	 */
	public final static int BACK_FOWARD_DIRECT = 1;

	/**
	 * 提交到驳回的节点，而不是继续一步步往下走 这个是全局的设置 和具体的节点设置无
	 */
	public final static int BACK_FOWARD_HISTORY = 2;

	public final static int PANE_PENDING = 1;

	public final static int PANE_DONE = 2;

	public final static int PANE_RESULT = 3;

	// 1.Activity;
	// 2.Start;
	// 3.End;
	// 4.And Decision And判断,node0---->node1，node2......;两个分支都要执行。 不能回退。
	// 5.XOR Decision 判断，只有一个分支。node0---->node1，node2.......
	// 只有一个node1,node2可能被选择 可以回退
	// 6.And Conjunction 会签；node1，node2.......-----> node0
	// node1,node2都执行完才可以执行node0 不能回退
	// 7.Or Conjunction node1，node2.......-----> node0
	// node1,node2只要有一个执行完就可以执行node0 不能回退
	// 8.Subprocess

	/**
	 * @uml.property name="nodeName"
	 */
	private String nodeName;

	/**
	 * @uml.property name="nodeDesc"
	 */
	private String nodeDesc;

	/**
	 * @uml.property name="nodeDate"
	 */
	private Timestamp nodeDate;

	/**
	 * @uml.property name="nodeStateShow"
	 */
	private String nodeStateShow;

	/**
	 * @uml.property name="nodeStateShowBack"
	 */
	private String nodeStateShowBack;

	private ProcessInstance processInstance;

	/**
	 * @uml.property name="exeStatus"
	 */
	private Integer exeStatus;

	/**
	 * @uml.property name="nodeType"
	 */
	private Integer nodeType;

	/**
	 * @uml.property name="scheduleOUUid"
	 */
	private String scheduleOUUid;

	/**
	 * @uml.property name="performer"
	 */
	private String performer;

	private String specName;

	/**
	 * @uml.property name="nodeUid"
	 */
	private String nodeUid;

	/**
	 * @uml.property name="authType"
	 */
	private Integer authType;

	/**
	 * @uml.property name="authType"
	 */

	/**
	 * 是否被退回的操作类型，包括1，回退(驳回);2,取回.
	 * 
	 * @uml.property name="backType"
	 */
	private Integer backType;

	/**
	 * @uml.property name="passTxt"
	 */
	private String passTxt;

	/**
	 * @uml.property name="rejectTxt"
	 */
	private String rejectTxt;

	/**
	 * @uml.property name="nodeExt1"
	 */
	private String nodeExt1;

	/**
	 * @uml.property name="nodeExt2"
	 */
	private String nodeExt2;

	/**
	 * @uml.property name="nodeExt5"
	 */
	private String retNodeUID;

	public Integer getX() {
		return x;
	}

	public void setX(Integer px) {
		this.x = px;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer py) {
		this.y = py;
	}

	private Integer x;

	private Integer y;

	private static Log log = LogFactory.getLog(NodeInstance.class);

	/** default constructor */
	public NodeInstance() {
	}

	/**
	 * @return
	 * @uml.property name="nodeName"
	 */
	public java.lang.String getNodeName() {
		return this.nodeName;
	}

	/**
	 * @param nodeName
	 * @uml.property name="nodeName"
	 */
	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return
	 * @uml.property name="nodeDesc"
	 */
	public java.lang.String getNodeDesc() {
		return this.nodeDesc;
	}

	/**
	 * @param nodeDesc
	 * @uml.property name="nodeDesc"
	 */
	public void setNodeDesc(java.lang.String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	/**
	 * @param nodeType
	 * @uml.property name="nodeType"
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * @param exeStatus
	 * @uml.property name="exeStatus"
	 */
	public void setExeStatus(Integer exeStatus) {
		this.exeStatus = exeStatus;
	}

	/**
	 * @return
	 * @uml.property name="processInstance"
	 */
	public ProcessInstance getProcessInstance() {
		if (processInstance.getExeStatus() == null  || processInstance.getInstanceUid()==null) {
			processInstance = DAOUtil.BUSI().getByObjUid(ProcessInstance.class,
					processInstance.getObjUid());
		}
		return processInstance;
	}

	/**
	 * @param processInstance
	 * @uml.property name="processInstance"
	 */
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	/**
	 * @return
	 * @uml.property name="nodeType"
	 */
	public Integer getNodeType() {
		return nodeType;
	}

	/**
	 * @return
	 * @uml.property name="exeStatus"
	 */
	public Integer getExeStatus() {
		return exeStatus;
	}

	/**
	 * @return
	 * @uml.property name="nodeUid"
	 */
	public String getNodeUid() {
		return nodeUid;
	}

	/**
	 * @param nodeUid
	 * @uml.property name="nodeUid"
	 */
	public void setNodeUid(String nodeUid) {
		this.nodeUid = nodeUid;
	}

	/**
	 * @return
	 * @uml.property name="nodeExt1"
	 */
	public String getNodeExt1() {
		return nodeExt1;
	}

	/**
	 * @param nodeExt1
	 * @uml.property name="nodeExt1"
	 */
	public void setNodeExt1(String nodeExt1) {
		this.nodeExt1 = nodeExt1;
	}

	/**
	 * @return
	 * @uml.property name="nodeExt2"
	 */
	public String getNodeExt2() {
		return nodeExt2;
	}

	/**
	 * @param nodeExt2
	 * @uml.property name="nodeExt2"
	 */
	public void setNodeExt2(String nodeExt2) {
		this.nodeExt2 = nodeExt2;
	}

	/**
	 * @return
	 * @uml.property name="nodeExt5"
	 */
	public String getRetNodeUID() {
		return retNodeUID;
	}

	/**
	 * @param nodeExt5
	 * @uml.property name="nodeExt5"
	 */
	public void setRetNodeUID(String nodeExt5) {
		this.retNodeUID = nodeExt5;
	}

	/**
	 * @return
	 * @uml.property name="passTxt"
	 */
	public String getPassTxt() {
		return passTxt;
	}

	/**
	 * @param passTxt
	 * @uml.property name="passTxt"
	 */
	public void setPassTxt(String passTxt) {
		this.passTxt = passTxt;
	}

	/**
	 * @return
	 * @uml.property name="rejectTxt"
	 */
	public String getRejectTxt() {
		return rejectTxt;
	}

	/**
	 * @param rejectTxt
	 * @uml.property name="rejectTxt"
	 */
	public void setRejectTxt(String rejectTxt) {
		this.rejectTxt = rejectTxt;
	}

	/**
	 * 上一级别的执行者，要考虑节点的类型，暂时不考虑。
	 * 
	 * @return
	 */
	private List getPrePerformerDepts() {

		List list = this.getPreNodes();
		List deptNames = new ArrayList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			NodeInstance ni = (NodeInstance) it.next();
			String performer = ni.getPerformer();
			if (performer != null) {
				String deptName = ni.getPerformerDept();
				deptNames.add(deptName);
				if (it.hasNext()) {
					deptNames.add(";");
				}
			}
		}
		return deptNames;
	}

	/**
	 * 上一级别的执行者，要考虑节点的类型，暂时不考虑。
	 * 
	 * @return
	 */
	public List getPrePerformers() {

		OrgParter userParter = OrgParter.getDefaultEmployee();

		DOBO userBO = userParter.getDoBO();

		List list = this.getPreNodes();
		List performers = new ArrayList();
		for (Iterator it = list.iterator(); it.hasNext();) {
			NodeInstance ni = (NodeInstance) it.next();
			String performer = ni.getPerformer();
			if (performer != null) {
				performers.add(userBO.getInstance(performer).getName());
				if (it.hasNext()) {
					performers.add(";");
				}
			}
		}
		return performers;
	}

	public String getPerformerDept() {

		String deptName = null;
		try {
			OrgParter userParter = OrgParter.getDefaultEmployee();
			OrgAccountability acctblt = userParter.getDeptAccntblt();
			OrgParter deptParter = acctblt.getLinkParter();
			DOBO userBO = userParter.getDoBO();
			DOBO deptBO = deptParter.getDoBO();
			deptName = null;
			String performer = this.getPerformer();
			if (performer != null) {
				BOInstance bi = userBO.getInstance(performer);
				if (bi != null) {
					String deptUid = "";
					if (acctblt.isNeedOtherTable()) {
						List list = acctblt.getAccessLinkParter().invokeSelect(
								performer);
						if (list != null && list.size() > 0) {
							BOInstance aInstance = (BOInstance) list.get(0);
							deptUid = aInstance.getValue(acctblt
									.getLinkParterCol());
						}

					}
					deptUid = bi.getValue(acctblt.getLinkParterCol());
					deptName = deptBO.getInstance(deptUid).getName();
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deptName;
	}

	/**
	 * 虚拟组织自动路由的一个比较重要的方法，可以暂时不考虑。还是采用传统的方法吧。
	 * 
	 * @return
	 */
	public String getAccessDeptUidOfSelfNode() {

		List preList = this.getPreNodes();
		NodeInstance preNI = null;
		if (preList != null && preList.size() > 0) {
			preNI = (NodeInstance) preList.get(0);
		} else {
			return null;
		}

		OrgParter userParter = OrgParter.getDefaultEmployee();

		DOBO userBO = userParter.getDoBO();
		BOInstance preUser = userBO.getInstance(preNI.getPerformer());
		String deptUid = preUser.getValue("unit_uid");
		DOService service = DOService.getService("zj.unit.link.findByChildUid");
		List parentDeptLinks = service.invokeSelect(deptUid);
		/**
		 * 用nodeExt5代替decisionExpression.
		 */
		String aRule = this.getNode().getRetNodeUID();
		if (parentDeptLinks != null) {
			if (parentDeptLinks.size() == 1
					|| ((aRule == null || aRule.trim().equals("")) && parentDeptLinks
							.size() > 1)) {
				BOInstance deptlink = (BOInstance) parentDeptLinks.get(0);
				return deptlink.getValue("parent_uid");
			} else {
				for (Iterator it = parentDeptLinks.iterator(); it.hasNext();) {
					BOInstance deptlink = (BOInstance) it.next();
					if (aRule.equals(deptlink.getValue("linkrule"))) {
						return deptlink.getValue("parent_uid");
					}
				}
			}
		}
		return null;
	}

	public List getFinishFlowDepts() {

		NodeInstance ni = this.getProcessInstance().getStartNode();
		List list = new ArrayList();
		addPostDept(ni, list);
		return list;
	}

	private void addPostDept(NodeInstance ni, List list) {
		for (Iterator it = ni.getPostNodes().iterator(); it.hasNext();) {
			NodeInstance postI = (NodeInstance) it.next();
			if (postI.getExeStatus() != null
					&& postI.getExeStatus().intValue() == NodeInstance.STATUS_FINISH) {
				list.add(postI.getPerformerDept());
				addPostDept(postI, list);
			}
		}
	}

	public List getFinishFlowPerformers() {

		NodeInstance ni = this.getProcessInstance().getStartNode();
		List list = new ArrayList();
		addPostPerformer(ni, list);
		return list;
	}

	private void addPostPerformer(NodeInstance ni, List list) {

		OrgParter userParter = OrgParter.getDefaultEmployee();

		DOBO userBO = userParter.getDoBO();

		for (Iterator it = ni.getPostNodes().iterator(); it.hasNext();) {
			NodeInstance postI = (NodeInstance) it.next();
			if (postI.getExeStatus() != null
					&& postI.getExeStatus().intValue() == NodeInstance.STATUS_FINISH) {
				if (postI.getPerformer() != null) {
					String userName = userBO.getInstance(postI.getPerformer())
							.getName();
					list.add(postI.getNode().getNodeName() + "(" + userName
							+ ")");
				}
				addPostPerformer(postI, list);
			}
		}
	}

	public String getSelfNodeFlowDeptStrs() {

		List list = this.getFinishFlowDepts();

		System.out.println("Finish Flow Depts==========");
		System.out.println(list);
		StringBuffer buffer = new StringBuffer();
		for (Iterator it = list.iterator(); it.hasNext();) {
			String aDept = (String) it.next();
			buffer.append(aDept);
			if (it.hasNext()) {
				buffer.append("-->");
			}
		}
		List runNodes = this.getProcessInstance().getRunNodes();
		DOBO deptBO = DOBO.getDOBOByName("zj.unit");
		if (runNodes != null && runNodes.size() > 0) {
			NodeInstance aNI = (NodeInstance) runNodes.get(0);
			if (aNI.getNodeType() != null
					&& aNI.getNodeType().intValue() == PTNode.TYPE_SELF) {
				buffer.append("--><font color='red'>").append(
						deptBO.getInstance(aNI.getAccessDeptUidOfSelfNode())
								.getName()).append("</font>");

			}

		}
		return buffer.toString();

	}

	public String getNodePerformerStrs() {

		List list = this.getFinishFlowPerformers();

		System.out.println("Finish Flow Performers==========");
		System.out.println(list);
		StringBuffer buffer = new StringBuffer();
		for (Iterator it = list.iterator(); it.hasNext();) {
			String aPerformer = (String) it.next();
			buffer.append(aPerformer);
			buffer.append("-->");

		}
		List runNodes = this.getProcessInstance().getRunNodes();
		if (runNodes != null && runNodes.size() > 0) {
			NodeInstance aNI = (NodeInstance) runNodes.get(0);
			buffer.append("<font color='red'>").append(
					aNI.getNode().getNodeName()).append("</font>");
		}

		return buffer.toString();

	}

	/**
	 * 获得前续节点
	 * 
	 * @return
	 */
	public java.util.List getPreNodes() {

		String hql = "select  ni.* from do_wfi_nodeinstance ni,do_wfi_ni_dependency nd where ni.objuid = nd.PRE_NID_UID and nd.POST_NID_UID = ?";

		return DAOUtil.BUSI().select(NodeInstance.class, hql, this.getObjUid());

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select nd.preNodeInstance from NIDependency nd where nd.postNodeInstance.id = ?";
		// try {
		// return dao.list(hql, getObjUid());
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 获取一个前置的人工节点
	 * 
	 * @return
	 */
	public NodeInstance getPreOneActivityNode() {

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select nd.preNodeInstance from NIDependency nd where nd.postNodeInstance.id = ?";

		String hql = "select  ni.* from do_wfi_nodeinstance ni,do_wfi_ni_dependency nd where ni.objuid = nd.PRE_NID_UID and nd.POST_NID_UID = ?";

		try {
			List list = DAOUtil.BUSI().select(NodeInstance.class, hql,
					this.getObjUid());
			if (list != null && list.size() > 0) {
				NodeInstance aInstance = (NodeInstance) list.get(0);
				if (aInstance.getNodeType().intValue() == PTNode.TYPE_ACTIVITY) {
					return aInstance;
				} else {
					return aInstance.getPreOneActivityNode();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 获得后续节点
	 * 
	 * @return
	 */
	public List getPostNodes() {

		String hql = "select  ni.* from do_wfi_nodeinstance ni,do_wfi_ni_dependency nd where ni.objuid = nd.POST_NID_UID and nd.PRE_NID_UID = ?";
		return DAOUtil.BUSI().select(NodeInstance.class, hql, this.getObjUid());

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select nd.postNodeInstance from NIDependency nd where  nd.preNodeInstance.id = ?";
		// try {
		// return dao.list(hql, getObjUid().toString());
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 获得后续节点依赖关系
	 * 
	 * @return
	 */
	public List getPostNodeDepes() {

		String hql = "select  nd.* from do_wfi_ni_dependency nd where nd.PRE_NID_UID = ?";
		return DAOUtil.BUSI().select(NIDependency.class, hql, this.getObjUid());

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select nd from NIDependency nd where  nd.preNodeInstance.id = ?";
		// try {
		// return dao.list(hql, getObjUid());
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	private List initPostNodeDepes() {

		PTNode tNode = this.getNode();

		/**
		 * 如果是自连接,先处理自连接.
		 */
		if (this.getNodeType() != null
				&& this.getNodeType().intValue() == PTNode.TYPE_SELF) {

			OrgParter userParter = OrgParter.getDefaultEmployee();
			DOBO userBO = userParter.getDoBO();
			BOInstance bi = userBO.getInstance(this.getPerformer());
			String deptUid = bi.getValue("unit_uid");
			if (!"11".equals(deptUid)) {
				List llist = new ArrayList();
				// WFDAO daoself = new WFDAO();

				//				
				// daoself.setAutoClose(false);

				NodeInstance niPost = NodeInstance.initNodeInstance(this
						.getProcessInstance(), tNode, NodeInstance.STATUS_FREE);
				NIDependency nid = new NIDependency();
				try {
					DAOUtil.BUSI().store(niPost);

					nid.setPreNodeInstance(this);
					nid.setPostNodeInstance(niPost);
					DAOUtil.BUSI().store(nid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// daoself.closeSession();
				}
				llist.add(nid);
				return llist;

			}
		}
		// ////////////如果子连接执行完才执行下面的.

		List list = new ArrayList();
		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		try {
			for (Iterator it = tNode.getPostNodeDepes().iterator(); it
					.hasNext();) {

				NodeDenpendency nd = (NodeDenpendency) it.next();
				NodeInstance niPost = NodeInstance.initNodeInstance(this
						.getProcessInstance(), nd.getPostNode(),
						NodeInstance.STATUS_FREE);
				DAOUtil.BUSI().store(niPost);
				NIDependency nid = new NIDependency(); // ///实例的关联类
				nid.setPreNodeInstance(this);
				nid.setPostNodeInstance(niPost);
				nid.setCondition(nd.getCondition());
				nid.setShowValue(nd.getShowValue());

				DAOUtil.BUSI().store(nid);
				list.add(nid);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// dao.closeSession();
		}
		return list;

	}

	/**
	 * 根据ID 获取NodeInstance 列表
	 * 
	 * @param aUid
	 * @return
	 */
	public static List getNodeInstancesByPTNodeID(String aWfiUid,
			String aPTNodeUid) {

		String hql = "select ni.* from do_wfi_nodeinstance ni where ni.PI_UID = ? and  ni.NODE_UID = ?";
		return DAOUtil.BUSI().select(NodeInstance.class, hql, aWfiUid,
				aPTNodeUid);

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select ni from NodeInstance ni where ni.processInstance.id = ? and  ni.nodeUid = ?";
		// try {
		// List list = dao.list(hql, aWfiUid, aPTNodeUid);
		// return list;
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 根据ID 获取NodeInstance
	 * 
	 * @param aUid
	 * @return
	 */
	public static NodeInstance getNodeInstanceByID(String aUid) {

		return DAOUtil.BUSI().getByObjUid(NodeInstance.class, aUid);

		// WFDAO dao = new WFDAO();
		// String hql = "select ni from NodeInstance ni where  ni.id = ?";
		// try {
		// List list = dao.list(hql, aUid);
		// if (list != null && list.size() > 0) {
		// NodeInstance aNI = (NodeInstance) list.get(0);
		// ///remove by weikx at 20080401 可能对工作流实例上下文 造成混乱
		// // DOBO niBO = DOBO.getDOBOByName("do.wfi.nodeinstance");
		// // niBO.refreshContext(aNI.getObjUid());
		// return aNI;
		// } else {
		// return null;
		// }
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 根据ID 获取NodeInstance
	 * 
	 * @param aUid
	 * @return
	 */
	public static NodeInstance getNodeInstanceByPTNodeID(String aWfiUid,
			String aPTNodeUid,String exeStatus) {

		String hql = "select ni.* from do_wfi_nodeinstance ni where ni.PI_UID = ? and  ni.NODE_UID = ?  and  ni.exeStatus = ?";
		return DAOUtil.BUSI().getBySql(NodeInstance.class, hql, aWfiUid,
				aPTNodeUid,exeStatus);

		//
		// WFDAO dao = new WFDAO();
		// String hql =
		// "select ni from NodeInstance ni where ni.processInstance.id = ? and  ni.nodeUid = ?";
		// try {
		// List list = dao.list(hql, aWfiUid, aPTNodeUid);
		// if (list != null && list.size() > 0) {
		// NodeInstance aNI = (NodeInstance) list.get(0);
		// return aNI;
		// } else {
		// return null;
		// }
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 取回操作是对已作过但流程未走完的任务来说的，而回退是对当前任务来说的。
	 * 
	 * @throws WFException
	 */

	public void withDraw() throws WFException {

		List postNodes = this.getPostNodes();
		NodeInstance postNode = (NodeInstance) postNodes.get(0);
		if (postNode.getProcessInstance().getExeStatus() != null
				&& (postNode.getProcessInstance().getExeStatus().intValue() != ProcessInstance.STATUS_RUN)) {
			throw new WFException("流程已不在运行中，无法撤回！");
		}
		if (postNode.getNodeType() != null
				&& postNode.getNodeType().intValue() != PTNode.TYPE_ACTIVITY) {
			postNodes = postNode.getPostNodes();
		}
		for (Iterator it = postNodes.iterator(); it.hasNext();) {
			NodeInstance aNi = (NodeInstance) it.next();
			if (aNi.getExeStatus() != null
					&& aNi.getExeStatus().intValue() == NodeInstance.STATUS_FINISH) {
				throw new WFException("后续流程已经执行，无法撤回！");
			}
		}

		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		try {
			for (Iterator it = postNodes.iterator(); it.hasNext();) {
				NodeInstance aPost = (NodeInstance) it.next();
				aPost.setExeStatus(Integer.valueOf(NodeInstance.STATUS_INIT));
				DAOUtil.BUSI().store(aPost);
			}
			this.setExeStatus(Integer.valueOf(NodeInstance.STATUS_RUN));
			this.setBackType(Integer.valueOf(NodeInstance.BACK_WITHDRAW));

			DAOUtil.BUSI().store(this);
		} catch (Exception e) {
			throw new WFException("处理回退时出错:" + this, e);
		}
		// finally {
		// dao.closeSession();
		// }

	}

	/**
	 * 回退操作。 应该考虑在任何情况下，都支持回退。 不能回退的情况，由界面否则控制。
	 * 
	 * 
	 * 回退，其实还是很复杂的，应该初始化的是回退到某点（不是从某点回退的那点）的postNodes.
	 * 
	 */

	public NodeInstance returnBack() throws WFException {
		if (DOGlobals.WF_BACK_FORWORD == NodeInstance.BACK_FOWARD_HISTORY) {
			return this.returnBack(NodeInstance.BACK_FOWARD_HISTORY);
		} else {
			return this.returnBack(NodeInstance.BACK_FOWARD_DIRECT);
		}
	}

	public NodeInstance returnBack(int type) throws WFException {


		// if (preNodes == null || preNodes.size() != 1) {
		// throw new WFException("不符合回退条件！");
		// }
		// /普通人工活动节点的前后，只有有一个节点。decition后面可以有多个，conjunction前面可以有多个。
		
		List prePTNodes = this.getNode().getPreNodes();
		
		PTNode preNode = (PTNode) prePTNodes.get(0);
		NodeInstance theNewBackNI = null;
		if (preNode.getNodeType() != null
				&& preNode.getNodeType().intValue() == PTNode.TYPE_START) {
			throw new WFException("前面已经没有节点,无法回退!");
		}
		// PTNode.TYPE_SELF////////////这种情况单独考虑.

		if (preNode.getNodeType() != null
				&& preNode.getNodeType().intValue() != PTNode.TYPE_ACTIVITY) {
			prePTNodes = preNode.getPreNodes();
		}

		String backStr = null;
		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		try {

			for (Iterator it = prePTNodes.iterator(); it.hasNext();) {
				PTNode aPre = (PTNode) it.next();
				// ///////////处理回退
				theNewBackNI = buildNewRelation(aPre, null, type);
				// ////////////////处理回退
			}
			backStr = this.getNode().getNodeStateShowBack();
			if (backStr == null || "".equals(backStr.trim())) {
				backStr = "回退";
			}
			this.setExeStatus(NodeInstance.STATUS_FINISH);
			storePropertyValues();
			DAOUtil.BUSI().store(this);
		} catch (Exception e) {
			throw new WFException("处理回退时出错:" + this, e);
		}
		return theNewBackNI;

	}

	/**
	 * 处理当前节点和回退节点的关系,并生成新的节点
	 * 
	 * @param dao
	 * @param forwardNodeInstance
	 * @param backType
	 *            -1,not back; 1,BACK_FOWARD_DIRECT;3,BACK_FOWARD_HISTORY
	 * 
	 * @throws DAOException
	 * 
	 * 
	 */
	private NodeInstance buildNewRelation(PTNode forwardNode,
			String scheduleOUUid, int backType) {

		NodeInstance theRet = initNodeInstance(this.getProcessInstance(),
				forwardNode, NodeInstance.STATUS_RUN);

		// //用处于活动状态的节点名称 替换未流程的状态

		String state = "回退";
		if (backType == -1) {
			state = theRet.getNodeStateShow();
		} else if (theRet.getNodeStateShowBack() != null
				&& !theRet.getNodeStateShowBack().trim().equals("")) {
			state = theRet.getNodeStateShowBack();
		}

		if (backType != -1) {
			SessionContext us = DOGlobals.getInstance().getSessoinContext();
			BOInstance formI = us.getFormInstance();
			String rTxt = "";
			System.out.println("formI=====" + formI);
			PTNode node = this.getNode();
			System.out.println("node=====" + node);
			if(formI != null && node != null) {
				if (node.getRejectTxt() != null
						&& !"".equals(node.getRejectTxt().trim())) {
					rTxt = formI.getValue(node.getRejectTxt());
				} else if (formI.getValue("reject_txt") != null) {
					rTxt = formI.getValue("reject_txt");
				} else if (formI.getValue("rejecttxt") != null) {
					rTxt = formI.getValue("rejecttxt");
				}

				state = state + "（" + rTxt + "）";				
			}
		}
		dealProcessState(state);

		if (scheduleOUUid != null && scheduleOUUid.trim().length() > 0) {
			theRet.setAuthType(PTNode.AUTH_TYPE_SCHEDULE_USER);
			theRet.setScheduleOUUid(scheduleOUUid);
		}
		

		if (backType != -1) {
			theRet.setBackType(NodeInstance.BACK_RETURN);
		}

		if (backType == NodeInstance.BACK_FOWARD_HISTORY) {
			theRet.setRetNodeUID(this.getObjUid());
		}
		try {
			DAOUtil.BUSI().store(theRet);

			NIDependency nid = new NIDependency(); // ///实例的关联类
			nid.setPreNodeInstance(this);
			nid.setPostNodeInstance(theRet);
			DAOUtil.BUSI().store(nid);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// //创建后面的关系
		// returnInitPostNodes(dao, theRet);
		return theRet;
	}

	/**
	 * 自由转向，自由转向到指定的节点。
	 */

	public NodeInstance forward(String nodeUid, String forwardReason)
			throws WFException {

		if (DOGlobals.WF_BACK_FORWORD == NodeInstance.BACK_FOWARD_HISTORY) {
			return this.forward(nodeUid, forwardReason, null,
					NodeInstance.BACK_FOWARD_HISTORY);
		} else {
			return this.forward(nodeUid, forwardReason, null,
					NodeInstance.BACK_FOWARD_DIRECT);
		}
	}

	public NodeInstance forward(String nodeUid, String forwardReason,
			String scheduleOUUid, int forwardType) throws WFException {

		PTNode aNode = PTNode.getNodeById(nodeUid);
		NodeInstance newInstance = null;
		try {
			newInstance = buildNewRelation(aNode, scheduleOUUid, forwardType);
			setExeStatus(Integer.valueOf(STATUS_FINISH));
			storePropertyValues();
			//DAOUtil.BUSI().store(this);

		} catch (Exception e) {
			throw new WFException("自由转向时出错:" + this, e);
		}
		return newInstance;

	}

	private void returnInitPostNodes(NodeInstance aNI) throws ExedoException {
		for (Iterator it = aNI.getPostNodes().iterator(); it.hasNext();) {
			NodeInstance aPost = (NodeInstance) it.next();
			returnInitPostNodes(aPost);
			aPost.setExeStatus(Integer.valueOf(NodeInstance.STATUS_INIT));
			DAOUtil.BUSI().store(aPost);
		}
		if (aNI.isFirstActivityNode()) {
			aNI.getProcessInstance().setExeStatus(ProcessInstance.STATUS_INIT);
			DAOUtil.BUSI().store(aNI.getProcessInstance());
		}
	}

	/**
	 * 回退操作
	 * 
	 */
	public void backToStart() throws WFException {

		storePropertyValues();

		try {
			NodeInstance aStartNodeI = this.getProcessInstance().getStartNode();
			if (aStartNodeI != null) {
				for (Iterator it = aStartNodeI.getPostNodes().iterator(); it
						.hasNext();) {
					NodeInstance fan = (NodeInstance) it.next();
					int type = NodeInstance.BACK_FOWARD_DIRECT;
					if (DOGlobals.WF_BACK_FORWORD == NodeInstance.BACK_FOWARD_HISTORY) {
						type = NodeInstance.BACK_FOWARD_HISTORY;
					}
					buildNewRelation(fan.getNode(), fan.getScheduleOUUid(),
							type);
					// fan.setExeStatus(new Integer(NodeInstance.STATUS_RUN));
					// fan.setBackType(new Integer(NodeInstance.BACK_RETURN));
					// /////////////////使用扩展5字段
				}
			}
			// for (Iterator it =
			// this.getProcessInstance().retrieveNodeInstances().iterator(); it
			// .hasNext();) {
			// NodeInstance aNI = (NodeInstance) it.next();
			// if (!(aNI != null && aNI.getNodeType() != null
			// && aNI.getNodeType().intValue() == PTNode.TYPE_START || (aNI
			// .getBackType() != null))) {
			//
			// aNI.setExeStatus(new Integer(NodeInstance.STATUS_INIT));
			// dao.store(aNI);
			// }
			// }

			// ////驳回情况自己的运行状态设为完成.
			this.setExeStatus(Integer.valueOf(NodeInstance.STATUS_FINISH));
			DAOUtil.BUSI().store(this);

		} catch (Exception e) {
			e.printStackTrace();
			throw new WFException("回退到发起人时出错:" + this, e);
		}
		// finally {
		// dao.closeSession();
		// }

	}

	/**
	 * 人工执行的节点
	 */

	public void perform() throws WFException {

		storePropertyValues();
		finishNode();
	}

	/**
	 * 存储界面的一些数据
	 * 
	 * @throws WFException
	 */
	void storePropertyValues() throws WFException {

		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		SessionContext us = DOGlobals.getInstance().getSessoinContext();

		if(us.getUser()!=null){
			setPerformer(us.getUser().getUid());
	     }

		setNodeDate(new java.sql.Timestamp(System.currentTimeMillis()));

		BOInstance formI = us.getFormInstance();
		
		System.out.println("formI==-===" + formI);
		System.out.println("formI==-===" + formI);
		System.out.println("formI==-===" + formI);
		System.out.println("formI==-===" + formI);
		System.out.println("formI==-===" + formI);
		
		// //////////////从界面获取用户的录入，更新变量对应的值
		try {
			if (formI != null) {
				for (Iterator it = this.getProcessInstance()
						.retrieveVarInstances().iterator(); it.hasNext();) {
					VarInstance vi = (VarInstance) it.next();
					vi.setVarValue(formI.getValue(vi.getVarName()));
					DAOUtil.BUSI().store(vi);
				}
				dealPropetyValues(formI);
			}

			DAOUtil.BUSI().store(this);

		} catch (Exception ex) {
			throw new WFException("人工执行节点时出错:" + this, ex);
		}
		// finally {
		// dao.closeSession();
		// }

	}



	private void dealPropetyValues(BOInstance formI) {

		PTNode node = this.getNode();
		if (node != null) {

			if (node.getPassTxt() != null
					&& !"".equals(node.getPassTxt().trim())) {
				this.setPassTxt(formI.getValue(node.getPassTxt()));
			}

			if (node.getRejectTxt() != null
					&& !"".equals(node.getRejectTxt().trim())) {
				this.setRejectTxt(formI.getValue(node.getRejectTxt()));
			} else if (formI.getValue("reject_txt") != null) {
				this.setRejectTxt(formI.getValue("reject_txt"));
			} else if (formI.getValue("rejecttxt") != null) {
				this.setRejectTxt(formI.getValue("rejecttxt"));
			}

			if (node.getNodeExt1() != null
					&& !"".equals(node.getNodeExt1().trim())) {
				this.setNodeExt1(formI.getValue(node.getNodeExt1()));
			}

			if (node.getNodeExt2() != null
					&& !"".equals(node.getNodeExt2().trim())) {
				this.setNodeExt2(formI.getValue(node.getNodeExt2()));
			}
			if (node.getRetNodeUID() != null
					&& !"".equals(node.getRetNodeUID().trim())) {
				this.setRetNodeUID(formI.getValue(node.getRetNodeUID()));
			}

		}

	}

	/**
	 * Node实例运行时处理
	 */
	public void execute() throws WFException {

		/**
		 * 空闲状态不能执行动作
		 */
		if (getExeStatus() != null && getExeStatus().intValue() == STATUS_FREE) {
			return;
		}

		// ////////如果工作流不在运行状态
		if (!isProcessInstanceRun()) {
			return;
		}
		// ///////执行状态如果是挂起.直接返回

		if (getExeStatus() != null
				&& getExeStatus().intValue() == STATUS_HANGUP) {
			return;
		}

		this.setExeStatus(Integer.valueOf(STATUS_RUN));

		/***********************************************************************
		 * 处理流程状态 用本节点的状态改变流程的状态
		 */
		dealProcessState(this.nodeStateShow);
		
		if(this.getNodeType() == PTNode.TYPE_START){
			
			System.out.println(this);
			
		}

		try {
			DAOUtil.BUSI().store(this);
		} catch (Exception ex) {
			throw new WFException("节点开始执行,持久化状态时出错:" + this, ex);
		}

		/**
		 * 人工节点人工执行
		 */
		if (getNodeType() != null
				&& (getNodeType().intValue() == PTNode.TYPE_ACTIVITY || getNodeType()
						.intValue() == PTNode.TYPE_SELF)) {
			return;
		}

		/**
		 * 自动Service 执行节点
		 */

		/**
		 * 人工节点人工执行
		 */
		if (getNodeType() != null
				&& (getNodeType().intValue() == PTNode.TYPE_SERVICE_AUTO)) {

			WFUtil.refreshWFPara(this.getProcessInstance());
			if (this.getNode().getAutoExcutesService() != null) {
				try {
					this.getNode().getAutoExcutesService().invokeAll();
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		finishNode();
	}

	/**
	 * 本节点执行结束,启动后续节点
	 * 
	 * @throws WFException
	 */
	public void finishNode() throws WFException {

		// ///////////判断是否可以执行后续节点
		if (!isExeNextNodes()) {
			return;
		}
		setExeStatus(Integer.valueOf(STATUS_FINISH));

		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		try {
			DAOUtil.BUSI().store(this);
			log.info("节点执行完成：：：：：：：：：：：：：：：" + this);
		} catch (Exception ex) {
			throw new WFException("节点执行完成,持久化状态时出错:" + this, ex);
		}
		// finally {
		// dao.closeSession();
		// }
		exeNextNodes();
	}

	/**
	 * 撤回不改变状态 只有回退 和 正常审批才改变状态
	 * 
	 * @param state
	 * @param dao
	 * @param thisNode
	 * @param isInit
	 * @throws DAOException
	 */

	private void dealProcessState(String state) {
		//
		// WFDAO dao = new WFDAO();
		// /可以被拦截
		processInstance = this.getProcessInstance();
		if (state != null && !"".equals(state.trim())) {
			processInstance.setCurState(state);
		}
		// else if (thisNode != null && thisNode.getNodeStateShow() != null
		// && !thisNode.getNodeStateShow().trim().equals("")) {
		// processInstance.setCurState(thisNode.getNodeStateShow());
		// }

		// if (!this.isFirstActivityNode()) {
		processInstance.setCurStateTime(this.getNodeDate());
		processInstance.setCurStateUser(this.getPerformer());
		// }
		// else {// /////////第一个人工节点启动 才算是真正的启动
		// processInstance.setExeStatus(ProcessInstance.STATUS_RUN);
		// }

		// if (isInit) {
		// processInstance.setExeStatus(ProcessInstance.STATUS_INIT);
		// }

		try {
			DAOUtil.BUSI().store(processInstance);
		} catch (Exception e) {
			log.info(e.fillInStackTrace());
		}

	}

	/**
	 * 执行下一些(个)节点
	 */
	public void exeNextNodes() throws WFException {

		// ////////////先处理返回的情况

		// ////////这个情况下需要提交到驳回的节点，而不是继续一步步往下走
		if (DOGlobals.WF_BACK_FORWORD == NodeInstance.BACK_FOWARD_HISTORY) {

			if (this.getBackType() != null && this.getRetNodeUID() != null) {

				NodeInstance postNI = NodeInstance.getNodeInstanceByID(this
						.getRetNodeUID());
				if (postNI != null) {
					// ///////////应该实现实例关联
					NIDependency nid = new NIDependency(); // ///实例的关联类
					// WFDAO dao = new WFDAO();
					// dao.setAutoClose(false);
					NodeInstance newInstance = null;
					try {
						// /重新提交
						newInstance = buildNewRelation(postNI.getNode(), null,
								-1);
						nid.setPreNodeInstance(this);
						nid.setPostNodeInstance(newInstance);
						DAOUtil.BUSI().store(nid);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// finally{
					// dao.closeSession();
					// }
					newInstance.execute();

				} else {
					log.warn("回退返回情况下找不到节点");
				}
				return;
			}
		}

		// ////////////获取后续节点依赖
		List postNodeDepes = initPostNodeDepes();

		if (postNodeDepes != null && postNodeDepes.size() > 0) {
			boolean isHaveOneExe = false;
			for (Iterator it = postNodeDepes.iterator(); it.hasNext();) {
				NIDependency nid = (NIDependency) it.next();
				// /////////////////如果Node 类型是XOR DECISION时
				if (this.getNodeType() != null
						&& this.getNodeType().intValue() == PTNode.TYPE_XOR_DECISION) {

					// /////////返回结果判断结果
					
					String decision = getNodeDecision();
					String condition = nid.getCondition();
					
					if(condition.indexOf("#value#")!=-1){
						condition = condition.replace("#value#", decision);
					}
					String exps = decision + condition;
					
					String result = calculate(exps);
					if (!"true".equals(result)) {
						continue;
					}
				}
				isHaveOneExe = true;
				if (nid.getPostNodeInstance() != null) {
					// ///////////先把Node的状态置为初始化
					if (nid.getPostNodeInstance().getExeStatus() != null
							&& (nid.getPostNodeInstance().getExeStatus()
									.intValue() == NodeInstance.STATUS_FREE || nid
									.getPostNodeInstance().getExeStatus()
									.intValue() == NodeInstance.STATUS_FINISH)) {
						nid.getPostNodeInstance().setExeStatus(
								 Integer.valueOf(STATUS_INIT));
					}

					// ///////////////////下一个节点的权限设定//
					storeNextNodeAuthorization(nid.getPostNodeInstance());
					nid.getPostNodeInstance().execute();
				}
			}
			if (!isHaveOneExe) {
				dealFailure("未找到后置节点执行:");
			}
		} else { // ////如果没有后续节点并且该节点时结束节点,本流程结束
			/**
			 * @todo 如果是子流程,要把变量返回给主流程
			 */

			// 如果是结束节点 并且流程中没有其它活动节点的话 流程可以结束 ，并且转移到历史表中
			if (this.getNodeType() != null
					&& this.getNodeType().intValue() == PTNode.TYPE_END
					&& processInstance.getRunNodes().size() == 0) {
				// WFDAO dao = new WFDAO();

				// //如果还有未完成的节点则流程不能结束
				List<NodeInstance> list = processInstance
						.retrieveNodeInstances();
				// for(Iterator<NodeInstance> it = list.iterator();
				// it.hasNext();){
				// NodeInstance ni = it.next();
				// if(ni.getExeStatus().intValue() == NodeInstance.STATUS_RUN){
				// return;
				// }
				// }

				processInstance = getProcessInstance();
				processInstance.setExeStatus(Integer.valueOf(
						ProcessInstance.STATUS_FINISH));

				DOBO auth = DOBO.getDOBOByName("do_authorization");
				DODataSource dss = auth.getDataBase();
				Transaction ts = dss.getTransaction();
				ts.begin();

				try {
					DAOUtil.BUSI().store(processInstance);

					// /数据表转移到历史表中
					DOService dosPi = DOService
							.getService("do_wfi_his_processinstance_insert");
					DOService dosNi = DOService
							.getService("do_wfi_his_nodeinstance_insert");
					DOService dosVi = DOService
							.getService("do_wfi_his_varinstance_insert");
					DOService dosNid = DOService
							.getService("do_wfi_his_ni_dependency_insert");

					List<NodeInstance> nis = processInstance
							.retrieveNodeInstances();
					DAOUtil.BUSI().store(processInstance, dosPi);
					for (Iterator<NodeInstance> it = nis.iterator(); it
							.hasNext();) {
						NodeInstance ni = (NodeInstance) it.next();
						DAOUtil.BUSI().store(ni, dosNi);
						List<NIDependency> nidepes = ni.getPostNodeDepes();
						for (Iterator<NIDependency> itNi = nidepes.iterator(); itNi
								.hasNext();) {
							NIDependency niD = itNi.next();
							DAOUtil.BUSI().store(niD, dosNid);
						}
					}
					List<VarInstance> piVals = processInstance
							.retrieveVarInstances();
					for (Iterator<VarInstance> it = piVals.iterator(); it
							.hasNext();) {
						VarInstance vi = it.next();
						DAOUtil.BUSI().store(vi, dosVi);
					}
					// ////删除运行时表
					DOService deletePI = DOService
							.getService("do_wfi_processinstance_delete");
					deletePI.invokeUpdate(processInstance.getObjUid());

					DOService deleteVars = DOService
							.getService("do_wfi_varinstance_deletebypiuid");
					deleteVars.invokeUpdate(processInstance.getObjUid());

					DOService deleteNIs = DOService
							.getService("do_wfi_nodeinstance_deletebypiuid");
					deleteNIs.invokeUpdate(processInstance.getObjUid());

					DOService deleteNIRels = DOService
							.getService("do_wfi_ni_dependency_deleterubbish");
					deleteNIRels.invokeUpdate();

				} catch (Exception ex1) {
					ts.rollback();
					ex1.printStackTrace();
					throw new WFException("流程结束,保存结束状态出错::" + ex1.toString(),
							ex1);
				}
				ts.end();
			} else {
				dealFailure("该节点未找到后置节点执行:");
			}
		}

	}

	private String getNodeDecision() {

		PTNode tNode = this.getNode();

		if (tNode.getDecisionType() != null
				&& tNode.getDecisionType().intValue() == PTNode.DECISION_TYPE_JAVA) {

			String decisionExpression = tNode.getDecisionExpression();

			if (decisionExpression != null
					&& !"".equals(decisionExpression.trim())) {
				WFJudge wfa = null;
				try {
					log.info("The Decision Expression::" + decisionExpression);

					Class caClass = Class.forName(decisionExpression);
					wfa = (WFJudge) caClass.newInstance();
					return wfa.doJudge(this);

				} catch (ClassNotFoundException ex) {
					log.error(ex.getMessage());
					return null;
				} catch (Exception ex1) {
					log.error(ex1);
					return null;
				}

			}
			return null;
		} else {
			return tNode.getDecisionExpression();
		}

	}

	/**
	 * 不是结束节点,找不到后续节点的处理
	 * 
	 * @throws WFException
	 */
	private void dealFailure(String error) throws WFException {
		// WFDAO dao = new WFDAO();
		getProcessInstance().setExeStatus(
				 Integer.valueOf(ProcessInstance.STATUS_FAILURE));
		try {
			DAOUtil.BUSI().store(getProcessInstance());
		} catch (Exception ex1) {
			throw new WFException("保存工作流实例失败状态出错", ex1);
		}
		throw new WFException(error + this);
	}

	/**
	 * 判断是否可以执行下一个(些)节点.
	 * 
	 * @return
	 * @throws WFException
	 */
	private boolean isExeNextNodes() throws WFException {
		// ////////如果工作流不在运行状态
		if (!isProcessInstanceRun()) {
			return false;
		}
		// ///////////////处理Conjunction的情况
		// ///////AND CONJUNCTION
		// ////回退时肯定要处理
		if (this.getNodeType() != null
				&& this.getNodeType().intValue() == PTNode.TYPE_AND_CONJUNCTION) {
			// //////////////获得模板前续节点列表
			List tPreNodes = this.getNode().getPreNodes();

			List runIs = this.getNode().getCorrRunNodeInstances(
					this.getProcessInstance().getObjUid());
			if (tPreNodes.size() != (runIs.size())) {
				return false;
			}
			// ////////////////////这一块等全面支持回退后，还要修改。
			// if(tPreNodes.size() != preNodes.size()){
			// return false;
			// }
			//			
			// for (Iterator itPre = preNodes.iterator(); itPre.hasNext();) {
			// NodeInstance niPre = (NodeInstance) itPre.next();
			// if (niPre.getExeStatus() != null
			// && niPre.getExeStatus().intValue() != NodeInstance.STATUS_FINISH)
			// {
			// return false;
			// }
			// }
		}
		// ////////////////OR CONJUNCTION
		else if (this.getNodeType() != null
				&& this.getNodeType().intValue() == PTNode.TYPE_OR_CONJUNCTION) {
			List preNodes = getPreNodes();
			boolean backFinish = false;
			for (Iterator itPre = preNodes.iterator(); itPre.hasNext();) {
				NodeInstance niPre = (NodeInstance) itPre.next();
				if (niPre.getExeStatus() != null
						&& niPre.getExeStatus().intValue() == NodeInstance.STATUS_FINISH) {
					if (backFinish) {
						// WFDAO dao = new WFDAO();
						this.getProcessInstance().setExeStatus(
								Integer.valueOf(ProcessInstance.STATUS_FAILURE));
						try {
							DAOUtil.BUSI().store(this.getProcessInstance());
						} catch (Exception ex) {
							throw new WFException("OR Conjunction 路由出错", ex);
						}
						return false;
					}
					backFinish = true;
				}
			}
		}
		return true;
	}

	private boolean isProcessInstanceRun() {

		boolean isRun = true;

		if (this.getProcessInstance().getExeStatus() != null
				&& this.getProcessInstance().getExeStatus().intValue() != ProcessInstance.STATUS_RUN) {

			if (this.isFirstActivityNode()
					|| this.equals(this.processInstance.getStartNode())) {

				// WFDAO dao = new WFDAO();
				this.getProcessInstance().setExeStatus(
						Integer.valueOf(ProcessInstance.STATUS_RUN));
				try {
					DAOUtil.BUSI().store(this.getProcessInstance());
				} catch (Exception ex) {
					log.error(ex);
				}
				return true;
			}

			isRun = false;
		}
		return isRun;
	}

	/**
	 * srcExp判断表达式
	 */
	public String calculate(String srcExp) {

		// ////////////test
		log.info("表达式::::::::::::::::::::;");
		log.info(srcExp);
		String expression = srcExp.trim().toLowerCase();
		PTNode tNode = this.getNode();

		// //////////////////////////相对应业务对象
		BOInstance bi = tNode.getProcessTemplate().getDoBO().getInstance(
				this.getProcessInstance().getInstanceUid());
		// //////////来自界面输入
		BOInstance formBI = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();
		if (bi == null && formBI == null) {
			log.info("判断表达式，无法得到执行判断参考值.");
			return srcExp;
		}

		Collection boProperties = tNode.getProcessTemplate().getDoBO()
				.retrieveProperties();
		// //this.getNodeDecision 可能是一个复杂的表达式，不仅仅是一个字段 可能是多个字段的加减乘除运算
		//
		expression = StringUtil.getCalException(expression, bi, boProperties,
				"0");
		// /////////////////////流程定义的表单的值替换
		for (Iterator itVar = this.getProcessInstance().retrieveVarInstances()
				.iterator(); itVar.hasNext();) {
			VarInstance vi = (VarInstance) itVar.next();
			String value = vi.getVarValue();
			if (value == null) {
				log.info("Form====================");
				log.info(formBI);
				value = formBI.getValue(vi.getVarName());
			}
			if (value == null) {
				value = "0";
			}
			expression = expression.replaceAll(vi.getVarName(), value);
		}

		log.info("处理后的表达式");
		log.info(expression);

		String retValue = RunJs.evaluate(expression, bi);
		log.info("表达式执行后的值:" + retValue);
		return retValue;

		// ExpressionUtil exp = new ExpressionUtil();
		// try {
		// return (String) exp.calculate(expression);
		// } catch (ExpressionException ex) {
		// ex.printStackTrace();
		// return srcExp;
		// }
		// }
	}

	public String getPaneURL(int paneType) {

		DOPaneModel aPane = getAPane(paneType);

		if (aPane == null) {
			return null;
		}

		if (this.getProcessInstance().getBOInstance() != null) {
			if (aPane != null) {
				return aPane.getFullCorrHref(this.getProcessInstance()
						.getBOInstance(), null)
						+ "&contextNIUid=" + this.getObjUid();
			}

		}
		return null;
	}

	private DOPaneModel getAPane(int paneType) {
		PTNode tNode = this.getNode();

		DOPaneModel aPane = tNode.getPane();
		switch (paneType) {
		case NodeInstance.PANE_DONE:
			aPane = tNode.getDonePane();
			break;
		case NodeInstance.PANE_RESULT:
			aPane = tNode.getResultPane();
			break;
		}
		return aPane;
	}

	public String getDOJOPaneURL(int paneType) {

		String paneUrl = this.getPaneURL(paneType);
		DOPaneModel aPane = getAPane(paneType);

		if (paneUrl == null) {
			return "#";
		}
		String paneUid = "_opener";
		if (aPane.getTargetPane() != null) {
			paneUid = aPane.getTargetPane().getName();
		}
		StringBuffer buffer = new StringBuffer("javascript:doAjax.refresh('");
		buffer.append(paneUid).append("','").append(paneUrl).append("')");
		return buffer.toString();
	}

	public com.exedosoft.wf.pt.PTNode getNode() {

		return DAOUtil.INSTANCE().getByObjUid(PTNode.class, nodeUid);

		// HbmDAO dao = new HbmDAO();
		// PTNode node = null;
		// try {
		// node = (PTNode) dao.retrieve(PTNode.class, nodeUid);
		// } catch (DAOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return node;
	}

	/**
	 * @return
	 * @uml.property name="performer"
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * @param performer
	 * @uml.property name="performer"
	 */
	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public Boolean getIsback() {
		return Boolean.TRUE;
	}

	/**
	 * 当节点执行后，初始化下一个节点
	 * 
	 * @param pi
	 * @param node
	 * @param niExeStatus
	 *            TODO
	 * @return
	 */
	public static NodeInstance initNodeInstance(ProcessInstance pi,
			PTNode node, int niExeStatus) {

		NodeInstance ni = new NodeInstance();
		ni.setNodeUid(node.getObjUid());
		ni.setNodeDesc(node.getNodeName() + "_" + new Date().toLocaleString());
		// ////命名规则
		ni.setNodeName(pi.getWfiName() + "_" + node.getNodeName());
		ni.setExeStatus(Integer.valueOf(niExeStatus));
		ni.setNodeType(node.getNodeType());
		ni.setAuthType(node.getAuthType());
		ni.setSpecName(node.getSpecName());
		ni.setNodeStateShow(node.getNodeStateShow());
		ni.setNodeStateShowBack(node.getNodeStateShowBack());
		ni.setX(node.getX());
		ni.setY(node.getY());

		ni.setProcessInstance(pi);

		// if (node.getNodeType() != null
		// && node.getNodeType().intValue() == PTNode.TYPE_ACTIVITY) {
		//
		// String ouuid = DOGlobals.getInstance().getSessoinContext()
		// .getUser().getValue("deptbelonguid");
		//			
		// //pi.getProcessTemplate()
		//		 
		// //
		// if(node.getObjUid().equals("paperc003")){
		// ouuid = DOGlobals.getInstance().getSessoinContext()
		// .getUser().getValue("org_province");
		// }
		// //
		//			
		// if (DOGlobals.getInstance().getRuleContext().get("SCHEDULE_OUUID") !=
		// null) {
		// ouuid = DOGlobals.getInstance().getRuleContext().get(
		// "SCHEDULE_OUUID").getUid();
		// }
		//			
		// /**
		// * 一般是第一个人工节点
		// */
		// if(node.getSpecName()!=null && "creator".equals(node.getSpecName())){
		// ouuid = DOGlobals.getInstance().getSessoinContext()
		// .getUser().getValue("deptuid");
		// }
		//			
		// if (ouuid != null) {
		// ni.setScheduleOUUid(ouuid);
		// }
		//
		// }
		// ni.setScheduleOUUid()

		return ni;
	}

	/**
	 * 判断该节点是否是第一个人工节点
	 * 
	 * @return
	 */

	public boolean isFirstActivityNode() {

		return this.equals(this.processInstance.getFirstActivityNode());

	}
	
	
	// /////////////////////上一个节点决定下一个节点的执行，把决定对象的权限写入权限表
	private void storeNextNodeAuthorization(NodeInstance nextNodeInstance) {

		SessionContext us = DOGlobals.getInstance().getSessoinContext();
		BOInstance formI = us.getFormInstance();
		// if (nextPerformerUid == null || "".equals(nextPerformerUid.trim())) {
		// nextPerformerUid = formI.getValue("doNextPerformerUid2");
		// }
		// if (nextPerformerUid == null || "".equals(nextPerformerUid.trim())) {
		// nextPerformerUid = formI.getValue("doNextPerformerUid3");
		// }

		// /////////除了处理schedue_user 的情况，还要处理
		// schedule_dept等的情况，又跟组织结构表绑死了，可以考虑另外的方式

		// //////////////很简单 有ptNode 决定 和哪级组织结构绑定，ptnode 再增加一个字段，表示需要绑定的组织机构

		if (nextNodeInstance.getAuthType() != null
				&& (nextNodeInstance.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_USER || nextNodeInstance
						.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_ROLE)) {
////通过从界面上取值，上一个节点的SpecName决定下一个节点的使用者
			String nextPerformerUid = formI.getValue(this.getSpecName());

			// if (nextNodeInstance.isFirstActivityNode()) {
			// SessionContext context = DOGlobals.getInstance()
			// .getSessoinContext();
			// nextPerformerUid = context.getUser().getUid();
			// }

			nextNodeInstance.setScheduleOUUid(nextPerformerUid);
			// WFDAO dao = new WFDAO();
			try {
				DAOUtil.BUSI().store(nextNodeInstance);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		/**
		 * 计划执行的人员有自定义的动作来完成，这个方法并不是由上个节点决定的
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		if (nextNodeInstance.getAuthType() != null
				&& (nextNodeInstance.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_CLASS ) ){
			
			
			  DOAction doa =  ActionFactory.getAction(nextNodeInstance.getNode().getAccessClass());
			  BOInstance para = new BOInstance();
			  para.putValue("corr_nodeinstance", nextNodeInstance);
			  doa.setInstance(para);
			  if(doa!=null){
				  try {
					doa.excute();
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			
		}
		// else {
		//
		// if (nextPerformerUid != null && !nextPerformerUid.trim().equals("")
		// && nextNodeInstance != null) {
		// storePersionAccess(nextNodeInstance.getObjUid(),
		// nextPerformerUid);
		// }
		// }
	}

	/**
	 * 当权限的设定是实例级别时，上一个节点往往要决定下一个节点的执行者。 这个执行者一般具体到个人。 <br>
	 * 这个时候把决定的权限保存到相应的权限配置表中。
	 * 
	 * @param personUid
	 */

	public static void storePersionAuth(String nodeInstanceUid,
			String personUid,boolean isAccess) {

		// WFDAO dao = new WFDAO();
		DOAuthorization daNew = new DOAuthorization();
		daNew.setAuthority(Boolean.FALSE);
	
		// daNew.setIsInherit(Boolean.TRUE);
		daNew.setOuUid(personUid);
		daNew.setParterUid(OrgParter.getDefaultEmployee().getObjUid());

		daNew.setWhatType(Integer.valueOf(DOAuthorization.WHAT_WF_NODEINSTANCE));
		daNew.setWhatUid(nodeInstanceUid);
		try {
			DAOUtil.BUSI().store(daNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 一般情况下不要采用 程序判断。 最好通过sql 语句直接查询出登陆用户对象的代办节点。
	 * 
	 * NodeInstance 的可见性。 复杂度较高。 首先判断权限类型是否是实例级别的，如果是实例级别的则直接读实例的相关配置表。
	 * 再判断是否是Java 接口级别的权限配置，如果是则直接调用该类对完成对该权限的判断。 再次 判断当前登录用户是否和表单相关系。
	 * 最后判断对应模板表的权限设置。
	 * 
	 * @return
	 */
	public boolean isAccess() {

		// /////////////PTNode.AUTH_TYPE_AUTHTABLE_INSTANCE的情况 实例级别
		if (this.getAuthType() != null
				&& this.getAuthType().intValue() == PTNode.AUTH_TYPE_AUTHTABLE_INSTANCE) {
			return DOAuthorization.isAccess(Integer.valueOf(
					DOAuthorization.WHAT_WF_NODEINSTANCE), this.getObjUid(),
					null, null);
		}

		PTNode tNode = this.getNode();
		if(tNode==null){
			return true;
		}
		String accessClass = tNode.getAccessClass();

		// ///////////// PTNode.AUTH_TYPE_JAVA的情况
		if (this.getAuthType() != null
				&& this.getAuthType().intValue() == PTNode.AUTH_TYPE_JAVA) {
			if (accessClass != null && !"".equals(accessClass.trim())) {
				WFAccess wfa = null;
				try {

					Class caClass = Class.forName(accessClass);
					wfa = (WFAccess) caClass.newInstance();
					return wfa.isAccess(this);

				} catch (ClassNotFoundException ex) {
					log.error(ex.getMessage());
					return false;
				} catch (Exception ex1) {
					log.error(ex1);
					return false;
				}

			}
		}

		// //////////////////PTNode.AUTH_TYPE_SCHEDULE_USER/ROLE的情况
		if (this.getAuthType() != null
				&& (this.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_USER || this
						.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_ROLE)) {
			SessionContext context = DOGlobals.getInstance()
					.getSessoinContext();
			String accessOrg = this.getScheduleOUUid();
			if (accessOrg != null) {

				if (this.getAuthType().intValue() == PTNode.AUTH_TYPE_SCHEDULE_USER) {
					String contextUserUid = context.getUser().getUid();
					if (accessOrg.equals(contextUserUid)) {
						return true;
					} else {
						return false;
					}
				} else {
					List<OrgParterValue> parters = (List<OrgParterValue>) DOGlobals
							.getInstance().getSessoinContext().getUser()
							.getObjectValue(LoginMain.ALLAUTH);
					for (Iterator<OrgParterValue> it = parters.iterator(); it
							.hasNext();) {
						OrgParterValue pv = it.next();
						if (pv.getName().equals(accessOrg)) {
							return true;
						}
					}
					return false;
				}
			}
		}

		// ////////////////PTNode.AUTH_TYPE_AUTHTABLE的情况 和其他SpecName 约定
		if (tNode != null) {

			// ////////通过specName 确定权限，比如表单的拥有者等。可以通过表单字段确定表单的拥有者
			if (this.getAuthType() != null
					&& this.getAuthType().intValue() == PTNode.AUTH_TYPE_DATA_OWNER
					&& tNode.getSpecName() != null) {

				SessionContext context = DOGlobals.getInstance()
						.getSessoinContext();
				String contextUserUid = null;
				if (context.getUser() != null) {
					contextUserUid = context.getUser().getUid();
				}
				String accessUser = null;

				if (tNode.getSpecName().equals("creator")) {// /流程创建者
					accessUser = this.getProcessInstance().getCreator();
				} else {
					BOInstance bi = tNode.getProcessTemplate().getDoBO()
							.getInstance(
									this.getProcessInstance().getInstanceUid());
					accessUser = bi.getValue(tNode.getSpecName());

				}
				if (accessUser != null) {
					if (accessUser.equals(contextUserUid)) {
						return true;
					} else {
						return false;
					}
				}
			} else {

				return tNode.isAccess();
			}
		}
		return true;

		// if (this.getNodeType() != null
		// && this.getNodeType().intValue() == PTNode.TYPE_SELF) {
		//
		// String accessDept = this.getAccessDeptUidOfSelfNode();
		// if (accessDept == null) {
		// return false;
		// }
		// SessionContext context = DOGlobals.getInstance()
		// .getSessoinContext();
		// String contextDeptUid = context.getUser().getValue("deptuid");
		// if (accessDept.equals(contextDeptUid)) {
		// return true;
		// } else {
		// return false;
		// }
		// }

	}

	public static void main(String[] args) {
		// String decisionExpression =
		// "com.anolesoft.epiboly.zj.JudgeIsChildPaper";
		//
		// WFJudge wfa = null;
		// try {
		// System.out
		// .println("The Decision Expression::" + decisionExpression);
		//
		// Class caClass = Class.forName(decisionExpression);
		// wfa = (WFJudge) caClass.newInstance();
		// wfa.doJudge(null);
		//
		// } catch (ClassNotFoundException ex) {
		// log.error(ex.getMessage());
		//
		// } catch (Exception ex1) {
		// log.error(ex1);
		//
		// }

		System.out.println((long) Double.parseDouble("45.0"));
		//
	}

	/**
	 * @return
	 * @uml.property name="backType"
	 */
	public Integer getBackType() {
		return backType;
	}

	/**
	 * @param backType
	 * @uml.property name="backType"
	 */
	public void setBackType(Integer backType) {
		this.backType = backType;
	}

	/**
	 * @return
	 * @uml.property name="scheduleOUUid"
	 */
	public String getScheduleOUUid() {
		return scheduleOUUid;
	}

	/**
	 * @param scheduleOUUid
	 * @uml.property name="scheduleOUUid"
	 */
	public void setScheduleOUUid(String scheduleOUUid) {
		this.scheduleOUUid = scheduleOUUid;
	}

	/**
	 * @return
	 * @uml.property name="nodeDate"
	 */
	public Timestamp getNodeDate() {
		return nodeDate;
	}

	/**
	 * @param nodeDate
	 * @uml.property name="nodeDate"
	 */
	public void setNodeDate(Timestamp nodeDate) {
		this.nodeDate = nodeDate;
	}

	/**
	 * @return
	 * @uml.property name="authType"
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * @param authType
	 * @uml.property name="authType"
	 */
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	/**
	 * @return
	 * @uml.property name="nodeStateShow"
	 */
	public String getNodeStateShow() {
		return nodeStateShow;
	}

	/**
	 * @param nodeStateShow
	 * @uml.property name="nodeStateShow"
	 */
	public void setNodeStateShow(String nodeStateShow) {
		this.nodeStateShow = nodeStateShow;
	}

	/**
	 * @return
	 * @uml.property name="nodeStateShowBack"
	 */
	public String getNodeStateShowBack() {
		return nodeStateShowBack;
	}

	/**
	 * @param nodeStateShowBack
	 * @uml.property name="nodeStateShowBack"
	 */
	public void setNodeStateShowBack(String nodeStateShowBack) {
		this.nodeStateShowBack = nodeStateShowBack;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}
	
	public boolean equals(Object o){
		return super.equals(o);
	}
	
	public int hashCode(){
		return super.hashCode();
	}

}
