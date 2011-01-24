package com.exedosoft.wf.wfi;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.SessionContext;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.wf.WFEngine;
import com.exedosoft.wf.WFException;
import com.exedosoft.wf.pt.NodeDenpendency;
import com.exedosoft.wf.pt.PTNode;
import com.exedosoft.wf.pt.PTVar;
import com.exedosoft.wf.pt.ProcessTemplate;

/**
 * @todo 支持回退
 * @todo 为每个ActionInstance 对应一个变量.而不是简单的映射
 * @todo 消息机制.支持主动通知 工作流实例实现
 *       <p>
 *       Title:
 *       </p>
 *       <p>
 *       Description:
 *       </p>
 *       <p>
 *       Copyright: Copyright (c) 2004
 *       </p>
 *       <p>
 *       Company:
 *       </p>
 * @author not attributable
 * @version 1.0
 */
public class WFEngineImpl implements WFEngine {

	public WFEngineImpl() {
	}

	public ProcessInstance startProcess(ProcessTemplate pt) throws WFException {

		NodeInstance niStart = this.startProcessNoSubmitHelper(pt, true);

		
		////如果流程包含启动节点
		
		// ////启动流程和开始节点及开始节电后续活动节点的执行看成了一个原子操作，启动后开始节点后续活动节点
		if (pt.getIsModify() != null && pt.getIsModify().booleanValue()) {
			for (Iterator it = niStart.getPostNodes().iterator(); it.hasNext();) {
				NodeInstance ni = (NodeInstance) it.next();
				if (ni.getNodeType() != null
						&& ni.getNodeType().intValue() == PTNode.TYPE_ACTIVITY) {
					ni.perform();
					break;
				}
			}
		}

		DOBO piBO = DOBO.getDOBOByName("do_wfi_processinstance");
		piBO.refreshContext(niStart.getProcessInstance().getObjUid());

		return niStart.getProcessInstance();
	}

	public NodeInstance initProcess(ProcessTemplate pt) throws WFException {

		return this.startProcessNoSubmitHelper(pt, false);

	}

	private NodeInstance startProcessNoSubmitHelper(ProcessTemplate pt,
			boolean isRun) throws WFException {

		System.out.println("正在启动工作流=====================");
		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		// dao.setIsTransaction(true);

		DOBO wfBO = DOBO.getDOBOByName("do_wfi_processinstance");

		Transaction t = new Transaction(wfBO.getDataBase());

		ProcessInstance pi = new ProcessInstance();
		NodeInstance niStart = null; // ///开始节点
		t.begin();
		try {

			pi.setPtName(pt.getPtName());
			pi.setWfiDesc(pt.getPtDesc());

			pi.setExeStatus(new Integer(ProcessInstance.STATUS_INIT));
			pi.setPtUid(pt.getObjUid());
			String ptName = pt.getPtName();
			BOInstance bi = pt.getDoBO().getCorrInstance();
			if (bi == null) {
				t.rollback();
				throw new WFException("启动工作流失败:没有与业务表关联！");
			}
			pi.setInstanceUid(bi.getUid());
			if (bi.getName() != null && !bi.getName().equals("N/A")) {
				ptName = bi.getName();
			}

			if (pt.getDoBO2() != null) {
				BOInstance bi2 = DOGlobals.getInstance().getSessoinContext()
						.get(pt.getDoBO2());
				if (bi2 != null) {
					pi.setInstanceUid2(bi2.getUid());
				}
			}
			if (pt.getDoBO3() != null) {

				BOInstance bi3 = DOGlobals.getInstance().getSessoinContext()
						.get(pt.getDoBO3());
				if (bi3 != null) {
					pi.setInstanceUid3(bi3.getUid());
				}
			}

			pi.setWfiName(ptName);
			pi.setStartTime(new java.sql.Timestamp(System.currentTimeMillis()));

			SessionContext us = DOGlobals.getInstance().getSessoinContext();

			pi.setStartUser(us.getUser().getUid());

			if (isRun) {
				pi.setExeStatus(new Integer(ProcessInstance.STATUS_RUN));
			}

			DAOUtil.BUSI().store(pi); // /////保存工作流实例

			PTNode startNode = pt.getStartNode();
			niStart = NodeInstance.initNodeInstance(pi, startNode,
					NodeInstance.STATUS_FREE);
			DAOUtil.BUSI().store(niStart);

			// // /////////如果模板存在节点
			// if (pt.getNodes() != null && pt.getNodes().size() > 0) {
			//
			// // ///存储Node 和 NodeInstance 的对应关系
			// // /////原因是要找到开始Node才能建立关联,要两次遍历
			// // //否则没必要用Map 保持他们的联系
			// PTNode start = null; // ////定义开始节点
			// HashMap mapNode = new HashMap();
			// // ///////////// 保存节点实例及下面的Actions，并返回开始节点
			// start = copyToNodeInstances(dao, pi, mapNode);
			// // ////////////建立该模板下的节点实例之间的关系。
			// buildInstanceRelation(pt, mapNode, dao);
			//							
			// niStart = (NodeInstance) mapNode.get(start);
			// } else {
			// throw new WFException("启动工作流失败:没有为工作流模板定义节点");
			// }
			// /////end copy nodes

			// /////////copy vars
			if (pt.retrievePtVars() != null && pt.retrievePtVars().size() > 0) {
				copyToVarInstances(pi);
			}
			// ///////////end copy vars

			// ////////////把控制权交给 Start NodeInstance.
			niStart.setExeStatus(new Integer(NodeInstance.STATUS_INIT));

			// niStart.perform();
		} catch (Exception ex) {
			t.rollback();
			throw new WFException("启动工作流失败", ex);
		} finally {
			t.end();
		}

		System.out.println("Start::" + niStart);
		// ///niStart 是开始节点，首先执行开始节点
		niStart.execute();
		return niStart;

	}

	/**
	 * 
	 * @param pt
	 * @param svoUid
	 * @return
	 */
	/**
	 * 存储节点实例，并返回开始节点。
	 * 
	 * @param pt
	 * @param dao
	 * @param pi
	 * @param map
	 * @return
	 * @throws ExedoException
	 * @throws DAOException
	 */
	private PTNode copyToNodeInstances(ProcessInstance pi, HashMap map)
			throws ExedoException {
		PTNode start = null;
		for (Iterator it = pi.getProcessTemplate().retrieveNodes().iterator(); it
				.hasNext();) {

			PTNode node = (PTNode) it.next();
			NodeInstance ni = NodeInstance.initNodeInstance(pi, node,
					NodeInstance.STATUS_FREE);
			// //////找到开始节点
			if (node.getNodeType() != null
					&& node.getNodeType().intValue() == PTNode.TYPE_START) {
				start = node;
			}
			DAOUtil.BUSI().store(ni);

			map.put(node, ni);
		}
		return start;
	}

	/**
	 * 建立实例直接的关联关系。
	 * 
	 * @param start
	 * @param map
	 * @param dao
	 * @throws ExedoException
	 */
	private void buildInstanceRelation(ProcessTemplate pt, HashMap map)
			throws ExedoException {
		for (Iterator it = pt.getNodeDependency().iterator(); it.hasNext();) {

			NodeDenpendency nd = (NodeDenpendency) it.next();
			// ////////前置节点
			NodeInstance pre = (NodeInstance) map.get(nd.getPreNode());
			// ///后置节点
			NodeInstance post = (NodeInstance) map.get(nd.getPostNode());
			NIDependency nid = new NIDependency(); // ///实例的关联类
			nid.setPreNodeInstance(pre);
			nid.setPostNodeInstance(post);
			nid.setCondition(nd.getCondition());
			DAOUtil.BUSI().store(nid);
		}
	}

	private void copyToVarInstances(ProcessInstance pi) throws ExedoException {

		for (Iterator it = pi.getProcessTemplate().retrievePtVars().iterator(); it
				.hasNext();) {
			PTVar ptVar = (PTVar) it.next();
			VarInstance vi = new VarInstance();
			vi.setPtVarUid(ptVar.getObjUid());
			vi.setProcessInstance(pi);
			vi.setVarName(ptVar.getVarName());
			DAOUtil.BUSI().store(vi);
		}

	}

	public Collection getProcessInstances(ProcessTemplate pt)
			throws WFException {
		return pt.getProcessInstances();
	}

	public ProcessTemplate loadProcessTemplate(String ptUID) throws WFException {

		return DAOUtil.INSTANCE().getByObjUid(ProcessTemplate.class, ptUID);
		// HbmDAO dao = new HbmDAO();
		// ProcessTemplate pt = null;
		// try {
		// pt = (ProcessTemplate) dao.retrieve(ProcessTemplate.class, ptUID);
		// DOBO piBO = DOBO.getDOBOByName("do.pt.processtemplate");
		// piBO.refreshContext(pt.getObjUid());
		//
		// } catch (DAOException ex) {
		// throw new WFException("无法加载过程模板", ex);
		// }
		// return pt;
	}

	public ProcessInstance loadProcessInstance(String piUID) throws WFException {

		return DAOUtil.BUSI().getByObjUid(ProcessInstance.class, piUID);

		// WFDAO dao = new WFDAO();
		// ProcessInstance pi = null;
		// try {
		// pi = (ProcessInstance) dao.retrieve(ProcessInstance.class, piUID);
		// DOBO piBO = DOBO.getDOBOByName("do.wfi.processinstance");
		// piBO.refreshContext(pi.getObjUid());
		// } catch (DAOException ex) {
		// throw new WFException("无法加载工作流实例", ex);
		// }
		// return pi;
	}

	public void killProcessInstance(String piUID) throws WFException {
		loadProcessInstance(piUID).killProcessInstance();
	}

	public ProcessInstance hangUpProcessInstance(String piUID)
			throws WFException {
		return loadProcessInstance(piUID).hangUpProcessInstance();
	}

	public ProcessInstance reStartProcess(ProcessInstance pi)
			throws WFException {
		return pi.reStartProcess();
	}

	public NodeInstance reStartFromNodeInstance(String niUid)
			throws WFException {

		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		NodeInstance ni = null;
		try {
			ni = DAOUtil.BUSI().getByObjUid(NodeInstance.class, niUid);
			List preNodes = ni.getPreNodes();
			if (preNodes != null && preNodes.size() > 0) {
				if (preNodes.size() == 1) {
					NodeInstance niPre = (NodeInstance) preNodes.get(0);
					// ///如果它的前置节点是空闲状态
					if (niPre.getExeStatus().intValue() == NodeInstance.STATUS_FREE
							|| niPre.getExeStatus().intValue() == NodeInstance.STATUS_RUN) {
						ni.setExeStatus(new Integer(NodeInstance.STATUS_FREE)); // //设置为空闲状态
						DAOUtil.BUSI().store(ni);
						return ni;
					}
				} else {
					throw new WFException("无法恢复conjunction类型的节点:" + ni);
				}
			}
		} catch (Exception ex) {
			throw new WFException("无法获得节点实例", ex);
		}
		// finally {
		// dao.closeSession();
		// }
		ni.setExeStatus(new Integer(NodeInstance.STATUS_INIT)); // //设置为初始化状态
		ni.execute();
		return ni;
	}

	public NodeInstance hangUpNodeInstance(String niUID) throws WFException {
		//
		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		NodeInstance ni = null;
		try {
			ni = DAOUtil.BUSI().getByObjUid(NodeInstance.class, niUID);
			if (ni.getNodeType() != null
					&& (ni.getNodeType().intValue() == PTNode.TYPE_AND_CONJUNCTION || ni
							.getNodeType().intValue() == PTNode.TYPE_OR_CONJUNCTION)) {
				throw new WFException("Conjunction类型的节点无法挂起");
			}
			ni.setExeStatus(new Integer(NodeInstance.STATUS_HANGUP));
			DAOUtil.BUSI().store(ni);
			return ni;
		} catch (Exception ex) {
			throw new WFException("节点挂起时出错" + ni, ex);
		}
		// finally {
		// dao.closeSession();
		// }

	}

	/**
	 * 在给定节点实例ni前面插入一个NodeInstance
	 * 
	 * @throws WFException
	 */
	public NodeInstance insertBeforNodeInstance(NodeInstance ni,
			NodeInstance insertNi) throws WFException {

		return insertNi;
	}

	/**
	 * 在给定节点实例ni后面插入一个NodeInstance
	 * 
	 * @throws WFException
	 */
	public NodeInstance insertAfterNodeInstance(NodeInstance ni,
			NodeInstance insertNi) throws WFException {
		return insertNi;
	}

	/**
	 * 删除给定的动作实例
	 * 
	 * @throws WFException
	 */
	public void deleteNodeInstance(NodeInstance ni) throws WFException {

	}

	/**
	 * 可以启动流程模板集合
	 * 
	 * @param us
	 * @return
	 * @throws WFException
	 */
	public Collection getProcesses() throws WFException {

		String hql = "select pt.* from DO_PT_ProcessTemplate pt";
		return DAOUtil.INSTANCE().select(ProcessTemplate.class, hql);

		//
		// HbmDAO dao = new HbmDAO();
		// String hql = "select pt from ProcessTemplate pt";
		// List list = null;
		// try {
		// list = dao.list(hql);
		// } catch (DAOException e) {
		// e.printStackTrace();
		// }
		// Collection cc = new ArrayList();
		// for (Iterator it = list.iterator(); it.hasNext();) {
		// ProcessTemplate pt = (ProcessTemplate) it.next();
		// if (pt.isAccess()) {
		// cc.add(pt);
		// }
		//
		// }
		// return cc;
	}

	/**
	 * 获取活动实例
	 * 
	 * @param us
	 * @return
	 */
	private Collection getNodeInstances(String nodeType, String exeStatus,
			String piStatus) {

		String hql = "select ni.* from do_wfi_nodeinstance ni, do_wfi_processInstance pi where ni.pt_uid=pi.objuid and (ni.nodeType = ? or ni.nodeType = 11)  and ni.exeStatus = ?  and pi.exeStatus = ?";
		return DAOUtil.INSTANCE().select(NodeInstance.class, hql, nodeType,
				exeStatus, piStatus);

		// WFDAO dao = new WFDAO();
		// String hql = "select ni from NodeInstance ni where (ni.nodeType = ?
		// or ni.nodeType = 11) and ni.exeStatus = ? and
		// ni.processInstance.exeStatus = ? ";
		// List list = null;
		// try {
		// String[] args = new String[3];
		// args[0] = nodeType;
		// args[1] = exeStatus;
		// args[2] = piStatus;
		// list = dao.list(hql, args);
		// } catch (DAOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Collection cc = new ArrayList();
		// for (Iterator it = list.iterator(); it.hasNext();) {
		// NodeInstance ni = (NodeInstance) it.next();
		// if (ni.isAccess()) {
		// cc.add(ni);
		// }
		// }
		// return cc;
	}

	/**
	 * 获取活动实例
	 * 
	 * @param us
	 * @return
	 */
	private Collection getNodeInstances(String nodeType, String exeStatus,
			String ptUid, String piStatus) {

		String hql = "select ni.* from do_wfi_nodeinstance ni, do_wfi_processinstance pi where ni.pt_uid=pi.objuid and (ni.nodeType = ? or ni.nodeType = 11)  and ni.exeStatus = ? and pi.PT_UID = ? and pi.exeStatus = ?";
		return DAOUtil.INSTANCE().select(NodeInstance.class, hql, nodeType,
				exeStatus, ptUid, piStatus);

		// WFDAO dao = new WFDAO();
		// String hql = "select ni from NodeInstance ni where (ni.nodeType = ?
		// or ni.nodeType = 11) and ni.exeStatus = ? and
		// ni.processInstance.ptUid = ? and ni.processInstance.exeStatus = ?";
		// List list = null;
		// try {
		// String[] args = new String[4];
		// args[0] = nodeType;
		// args[1] = exeStatus;
		// args[2] = ptUid;
		// args[3] = piStatus;
		// list = dao.list(hql, args);
		// } catch (DAOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Collection cc = new ArrayList();
		// for (Iterator it = list.iterator(); it.hasNext();) {
		// NodeInstance ni = (NodeInstance) it.next();
		// if (ni.isAccess()) {
		// cc.add(ni);
		// }
		// }
		// return cc;
	}

	public Collection getMyPending() throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_RUN), String
				.valueOf(ProcessInstance.STATUS_RUN));
	}

	public Collection getMyPending(String processTemplateUID)
			throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_RUN), processTemplateUID, String
				.valueOf(ProcessInstance.STATUS_RUN));

	}

	public Collection getHaveDone() throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_FINISH), String
				.valueOf(ProcessInstance.STATUS_RUN));
	}

	public Collection getHaveDone(String processTemplateUID) throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_FINISH), processTemplateUID,
				String.valueOf(ProcessInstance.STATUS_RUN));
	}

	/**
	 * 本人处理过的待归档(流程已经走完)的发文
	 * 
	 * @param us
	 * @return
	 * @throws WFException
	 */
	public Collection getMyResult() throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_FINISH), String
				.valueOf(ProcessInstance.STATUS_FINISH));
	}

	public Collection getMyResult(String processTemplateUID) throws WFException {
		return getNodeInstances(String.valueOf(PTNode.TYPE_ACTIVITY), String
				.valueOf(NodeInstance.STATUS_FINISH), processTemplateUID,
				String.valueOf(ProcessInstance.STATUS_FINISH));
	}

}
