package com.exedosoft.wf.pt;

import java.util.List;

import com.exedosoft.plat.bo.BaseObject;
import com.exedosoft.plat.ui.DOPaneModel; //import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.HbmDAO;
//import com.exedosoft.plat.dao.WFDAO;

import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.DOAuthorization;
import com.exedosoft.wf.wfi.NodeInstance;

import com.exedosoft.plat.DAOUtil;

/**
 * 
 * 要考虑到领取的过程，如果任务耗时比较长那么应该有一个领取任务的动作
 * 领取后进入在办
 * 
 * 拟办、待办、在办、办结 另外还有转发的感念。
 * 
 * 转发是指提交给下一个节点，下一个节点还未签收，已办包含在“已办”概念但是比这个概念要大。
 *   
 *  在办是自己正在办理  存在领取  过程才存在在办
 * 

      办结文件：如果整个流程办理完毕，则从“在办文件”中自动转移到“办结文件”中。


/////待办
select distinct curstate,node_uid,nodeDate, ni.OBJUID contextNIUid,wpi.objuid contextPIUid, instance_uid,pass_txt,reject_txt,user_uid from  
do_wfi_nodeinstance ni, DO_WFI_PROCESSINSTANCE wpi,do_org_user_role ur ,do_org_role r,do_authorization  a where wpi.objuid = ni.pi_uid and 
 ur.role_uid and r.objuid and a.parteruid='9' and a.ouuid= r.objuid  and node_uid = a.whatuid and ni.exestatus=2 and wpi.exestatus=2

////在办
select distinct wpi.objuid contextPIUid, curstate, instance_uid, user_uid from  
do_wfi_nodeinstance ni, DO_WFI_PROCESSINSTANCE wpi,do_org_user_role ur ,do_org_role r,do_authorization  a where wpi.objuid = ni.pi_uid and 
 ur.role_uid and r.objuid and a.parteruid='9' and a.ouuid= r.objuid  and node_uid = a.whatuid and ni.exestatus=3 and wpi.exestatus=2

 ////办结
  * select distinct wpi.objuid contextPIUid, curstate, instance_uid, user_uid from  
do_wfi_his_nodeinstance ni, DO_WFI_his_PROCESSINSTANCE wpi,do_org_user_role ur ,do_org_role r,do_authorization  a where wpi.objuid = ni.pi_uid and 
 ur.role_uid and r.objuid and a.parteruid='9' and a.ouuid= r.objuid  and node_uid = a.whatuid 
 
 * 
 * 最新的设计思路: 采用状态工作流的设计思路： Node Node是一个容器。
 * Node有多种类型，这样可以借鉴多种过程建模理论，如xpdl,activity diagram,epc（event 驱动建模）等。
 * 由于Node是一个容器，不局限类型，所以可以很好得事先微软的两种流程：过程定义良好的和流程灵活的。 为了支持流程灵活的情况，引入类型规则引擎执行机的概念，
 * 当流程被触发执行时，所有的节点都遍历一遍，每个节点有执行条件，只要满足条件，就执行（即节点处于STATUS_RUN状态）。
 * 所以每个节点（初开始节点）的执行可以有两种情况： 1，被前一个节点触发 2，被所定义的条件触发
 * 它的主题是一个Rule.根据条件触发，也就是根据状态触发。这个可以非常好的处理回退，跳转。 顺序工作流可以看成状态工作流的特例。 下面是原来的解释:
 * 考虑流程的复杂性，如执行状态，自流程 ，Node 的类型等。还是保留Node ,Process等流程对象的存在。 * Node
 * 的执行主体是DOService,参考周华对流程的实现，Node 下面分Action. DOService就相当于Action. 但Node
 * 只能包含一个DOService ，DOService 有自己的执行顺序。
 */
public class PTNode extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185569976922912331L;

	public final static int TYPE_ACTIVITY = 1;

	public final static int TYPE_SERVICE_AUTO = 21;

	public final static int TYPE_SELF = 11;

	/**
	 * 自动的会签支持，依据DOAuthorization的getAuthConfigUsers 每个会签结果保存为xml文件。
	 * 
	 */

	public final static int TYPE_SELF_CONJUNCTION = 16;

	public final static int TYPE_START = 2;

	public final static int TYPE_END = 3;

	/**
	 * 分支，不加条件
	 */
	public final static int TYPE_AND_DECISION = 4;

	/**
	 * 分支加条件
	 */
	public final static int TYPE_XOR_DECISION = 5;

	/**
	 * 有分就要有合，有decision就要有conjunction,所以还不能去掉专门的会签定义。
	 * 
	 * OR_CONJUNCTION 暂时省略掉
	 */

	public final static int TYPE_AND_CONJUNCTION = 6;

	public final static int TYPE_OR_CONJUNCTION = 7;

	public final static int TYPE_SUBPROCESS = 8;

	/**
	 * 手动方式也是一种script 方式 手动决定下一步的审批者是谁，级联下拉列表。 一个节点后面可能有多个节点，每个节点又对应多个用户
	 * 
	 */
	public final static int DECISION_TYPE_SCRIPT = 1;

	public final static int DECISION_TYPE_JAVA = 2;

	/**
	 * 权限表方式
	 */
	public final static int AUTH_TYPE_AUTHTABLE = 0;

	/**
	 * 权限表方式（对工作流实例） 这个暂时可以不实现
	 */
	public final static int AUTH_TYPE_AUTHTABLE_INSTANCE = 10;

	/**
	 * 使用脚本
	 */
	public final static int AUTH_TYPE_SCRIPT = 1;

	/**
	 * 使用java类
	 */
	public final static int AUTH_TYPE_JAVA = 2;

	/**
	 * 数据拥有着权限 (一般指创建者)
	 */

	public final static int AUTH_TYPE_DATA_OWNER = 3;

	/**
	 * 指定用户
	 */
	public final static int AUTH_TYPE_SCHEDULE_USER = 8;

	/**
	 * 指定部门,暂时不实现
	 */
	public final static int AUTH_TYPE_SCHEDULE_DEPT = 12;
	/**
	 * 指定角色
	 */
	public final static int AUTH_TYPE_SCHEDULE_ROLE = 16;
	
	/**
	 * 通过运行类指定用户
	 */
	public final static int AUTH_TYPE_SCHEDULE_CLASS = 16;
	

	private String nodeName;

	private String nodeDesc;

	/**
	 * 可以是script，也可以是java class
	 */
	private Integer decisionType;

	private String decisionExpression;

	private ProcessTemplate processTemplate;

	private Integer nodeType;

	private String specName;

	private DOService autoExcutesService;

	/**
	 * 当conditon 满足时，这个 node 就可以触发 支持自由流的condtion
	 */
	private String conditon;

	/**
	 * 当前流程状态显示,这个状态不是上面的执行状态，而是节点执行到那一步需要显示什么状态。
	 * 当然，一个流程可能同时有多个活动节点。每个节点都有可能设置nodeStateShow。 以最后一个更新为准。
	 * 在实际应用中，如果一个节点的执行并不影响整个流程的状态，可以不设置nodeStateShow 这样引擎就不会处理。
	 */
	private String nodeStateShow;

	private String nodeStateShowBack;

	private DOPaneModel pane;///待办

	private DOPaneModel donePane;///已办

	private DOPaneModel resultPane;///办结

	private Integer authType;

	/**
	 * 权限的另一种控制手段。 权限控制有两种 1，通过DOAuthrizaiton表 2，如果定义这个Class 则依照这个class
	 */
	private String accessClass;

	private String passTxt;

	private String rejectTxt;

	private String nodeExt1;

	private String nodeExt2;

	private String retNodeUID;
	
	private Integer x;
	
	private Integer y;
	
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



	/** default constructor */
	public PTNode() {
	}

	/**
	 * @return
	 */
	public java.lang.String getNodeName() {
		return this.nodeName;
	}

	/**
	 * @param nodeName
	 */
	public void setNodeName(java.lang.String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * @return
	 */
	public java.lang.String getNodeDesc() {
		return this.nodeDesc;
	}

	/**
	 * @param nodeDesc
	 */
	public void setNodeDesc(java.lang.String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}

	/**
	 * @param nodeType
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	public void setNodeType(String sType) {

		if (sType.equals("start")) {
			this.nodeType = 2;
		} else if (sType.equals("end")) {
			this.nodeType = 3;
		} else if (sType.equals("activity")) {
			this.nodeType = 1;
		} else if (sType.equals("auto")) {
			this.nodeType = 21;
		} else if (sType.equals("andDecision")) {
			this.nodeType = 4;
		} else if (sType.equals("xorDecision")) {
			this.nodeType = 5;
		} else if (sType.equals("andConjuction")) {
			this.nodeType = 6;
		} else if (sType.equals("subFlow")) {
			this.nodeType = 8;
		}

	}

	public String getNodeTypeStr() {

		if (this.nodeType == null) {
			return "activity";
		}
		switch (this.nodeType.intValue()) {
		case 2:
			return "start";
		case 3:
			return "end";
		case 21:
			return "auto";
		case 4:
			return "andDecision";
		case 5:
			return "xorDecision";
		case 6:
			return "andConjuction";
		case 8:
			return "subFlow";
		default:
			return "activity";

		}
	}

	/**
	 * @return
	 */
	public java.lang.String getDecisionExpression() {
		return this.decisionExpression;
	}

	/**
	 * @param decisionExpression
	 */
	public void setDecisionExpression(java.lang.String decisionExpression) {
		this.decisionExpression = decisionExpression;
	}

	/**
	 * @return
	 */
	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	/**
	 * @param processTemplate
	 */
	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

	/**
	 * @return
	 */
	public Integer getNodeType() {
		return nodeType;
	}

	/**
	 * @return
	 */
	public String getSpecName() {
		return specName;
	}

	/**
	 * @param specName
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public DOPaneModel getPane() {
		return pane;
	}

	/**
	 * @param pane
	 */
	public void setPane(DOPaneModel pane) {
		this.pane = pane;
	}

	public DOPaneModel getDonePane() {
		return donePane;
	}

	public void setDonePane(DOPaneModel donePane) {
		this.donePane = donePane;
	}

	public DOPaneModel getResultPane() {
		return resultPane;
	}

	public void setResultPane(DOPaneModel resultPane) {
		this.resultPane = resultPane;
	}

	public String getNodeExt1() {
		return nodeExt1;
	}

	public void setNodeExt1(String nodeExt1) {
		this.nodeExt1 = nodeExt1;
	}

	public String getNodeExt2() {
		return nodeExt2;
	}

	public void setNodeExt2(String nodeExt2) {
		this.nodeExt2 = nodeExt2;
	}

	public String getRetNodeUID() {
		return retNodeUID;
	}

	public void setRetNodeUID(String nodeExt5) {
		this.retNodeUID = nodeExt5;
	}

	public String getPassTxt() {
		return passTxt;
	}

	public void setPassTxt(String passTxt) {
		this.passTxt = passTxt;
	}

	public String getRejectTxt() {
		return rejectTxt;
	}

	public void setRejectTxt(String rejectTxt) {
		this.rejectTxt = rejectTxt;
	}

	public String getAccessClass() {
		return accessClass;
	}

	public void setAccessClass(String accessClass) {
		this.accessClass = accessClass;
	}

	public DOService getAutoExcutesService() {
		return autoExcutesService;
	}

	public void setAutoExcutesService(DOService autoExcutesService) {
		this.autoExcutesService = autoExcutesService;
	}

	public String getConditon() {
		return conditon;
	}

	public void setConditon(String conditon) {
		this.conditon = conditon;
	}

	public String getNodeStateShow() {
		return nodeStateShow;
	}

	public void setNodeStateShow(String nodeStateShow) {
		this.nodeStateShow = nodeStateShow;
	}

	public String getNodeStateShowBack() {
		return nodeStateShowBack; 
	}

	public void setNodeStateShowBack(String nodeStateShowBack) {
		this.nodeStateShowBack = nodeStateShowBack;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	/**
	 * 获得前续节点
	 * 
	 * @return
	 */
	public static PTNode getNodeById(String aID) {

		return DAOUtil.INSTANCE().getByObjUid(PTNode.class, aID);
		// HbmDAO dao = new HbmDAO();
		// String hql = "select pn from PTNode pn where pn.id = ?";
		// try {
		// List list = dao.list(hql, aID);
		// if(list!=null && list.size()>0){
		// return (PTNode)list.get(0);
		// }
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
		// return null;
	}

	/**
	 * 获得前续节点
	 * 
	 * @return
	 */
	public java.util.List getPreNodes() {

		String hql = "select node.* from DO_PT_Node_Denpendency nd,DO_PT_Node node where nd.Pre_N_UID = node.objuid and  nd.Post_N_UID = ? ";
		return DAOUtil.INSTANCE().select(PTNode.class, hql, this.getObjUid());

		// HbmDAO dao = new HbmDAO();
		// String hql =
		// "select nd.preNode from NodeDenpendency nd where nd.postNode.id = ?";
		// try {
		// return dao.list(hql, getObjUid().toString());
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 获得后续节点
	 * 
	 * @return
	 */
	public List getPostNodes() {

		String hql = "select node.* from DO_PT_Node_Denpendency nd,DO_PT_Node node where nd.Pre_N_UID = node.objuid and  nd.Pre_N_UID = ? ";
		return DAOUtil.INSTANCE().select(PTNode.class, hql, this.getObjUid());

		// HbmDAO dao = new HbmDAO();
		// String hql =
		// "select nd.postNode from NodeDenpendency nd where  nd.preNode.id = ?";
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

		String hql = "select nd.* from DO_PT_Node_Denpendency nd,DO_PT_Node node where nd.Pre_N_UID = node.objuid and  nd.Pre_N_UID = ? ";
		return DAOUtil.INSTANCE().select(NodeDenpendency.class, hql, this
				.getObjUid());

		// HbmDAO dao = new HbmDAO();
		// String hql =
		// "select nd from NodeDenpendency nd where  nd.preNode.id = ?";
		// try {
		// return dao.list(hql, getObjUid().toString());
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }
	}

	/**
	 * 获取正在运行的制定实例的nodeInstance数目。
	 * 
	 * @return
	 */

	public List getCorrRunNodeInstances(String piUid) {

		String hql = "select ni.* from do_wfi_nodeinstance ni where  ni.NODE_UID = ? and ni.exeStatus = 2 ";
		return DAOUtil.BUSI().select(NodeInstance.class, hql, this.getObjUid());

		// WFDAO dao = new WFDAO();
		// String hql =
		// "select ni from NodeInstance ni where  ni.nodeUid = ? and ni.processInstance.id = ? and ni.exeStatus = 2 and ni.processInstance.exeStatus = 2";
		// try {
		// List list = dao.list(hql, this.getObjUid(),piUid);
		// return list;
		// } catch (DAOException e) {
		// e.printStackTrace();
		// return null;
		// }

	}

	// public String toString() {
	// return nodeName;
	// }

	public boolean isAccess() {

		// System.out.println("Hello world!!!!!!!!!!!!!!!!!!");
		//		
		// System.out.println(this.getObjUid());

		return DOAuthorization.isAccess(new Integer(
				DOAuthorization.WHAT_WF_NODE), this.getObjUid(), null, null);

		// if (this.getPane() == null) {
		// return true;
		// }
		// return this.getPane().isAccess();
	}

	/**
	 * @return
	 * @uml.property name="decisionType"
	 */
	public Integer getDecisionType() {
		return decisionType;
	}

	/**
	 * @param decisionType
	 * @uml.property name="decisionType"
	 */
	public void setDecisionType(Integer decisionType) {
		this.decisionType = decisionType;
	}

	public static void main(String[] args) {

		String aFile = "c:\\aaa\\bbb\\中国\\dd.txt";
		System.out.println(aFile.substring(aFile.lastIndexOf("\\") + 1));

	}

}
