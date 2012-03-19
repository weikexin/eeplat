package com.exedosoft.plat.action.wf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.wf.pt.NodeDenpendency;
import com.exedosoft.wf.pt.PTNode;
import com.exedosoft.wf.pt.ProcessTemplate;
import com.exedosoft.wf.wfi.NodeInstance;
import com.exedosoft.wf.wfi.ProcessInstance;

public class DOWfMonitor extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997150272030863948L;

	@SuppressWarnings("unchecked")
	@Override
	public String excute() throws ExedoException {

		DOBO ptNI = DOBO.getDOBOByName("do_wfi_processinstance");
		BOInstance curPt = ptNI.getCorrInstance();
		BOInstance echo = new BOInstance();

		ProcessInstance pi = ProcessInstance.getProcessInstance(curPt.getUid());

		if (curPt == null || pi == null) {
			System.out.println("不存在工作流实例!");
			this.setEchoValue("不存在工作流实例!");
			return NO_FORWARD;
		}

		List<NodeInstance> nis = pi.retrieveNodeInstances();
		Map<String, NodeInstance> nodeIMap = new HashMap<String, NodeInstance>();
		for (Iterator<NodeInstance> it = nis.iterator(); it.hasNext();) {
			NodeInstance ni = it.next();
			nodeIMap.put(ni.getNode().getObjUid(), ni);
		}

		ProcessTemplate pt = pi.getProcessTemplate();

		StringBuilder xml = new StringBuilder("<wf>	<processtemplate name='")
				.append(pt.getPtName()).append("'>");
		StringBuilder strNodeList = new StringBuilder("<nodes>");
		StringBuilder strFlowList = new StringBuilder("<transitions>");

		for (Iterator<PTNode> it = pt.retrieveNodes().iterator(); it.hasNext();) {
			PTNode aNode = it.next();
			String autoServiceName = "";
			String deciType = "";
			String authType = "";
			if (aNode.getAutoExcutesService() != null) {
				autoServiceName = aNode.getAutoExcutesService().getName();
			}
			if (aNode.getDecisionType() != null) {
				deciType = aNode.getDecisionType().toString();
			}
			if (aNode.getAuthType() != null) {
				authType = aNode.getAuthType().toString();
			}
			String paneName = "";
			if (aNode.getPane() != null) {
				paneName = aNode.getPane().getName();
			}

			String nodeName = aNode.getNodeName();
			NodeInstance nodeI = nodeIMap.get(aNode.getObjUid());
			if (nodeI != null && nodeI.getPerformer() != null) {
				String name = null;
				try {
					BOInstance user = OrgParter.getDefaultEmployee().getDoBO()
							.getInstance(nodeI.getPerformer());
					if (user != null) {
						name = user.getName();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (name != null) {
					nodeName = nodeName + "(" + name + ")";
				}
			}

			String status = "init";

			if (nodeI != null && nodeI.getExeStatus() != null) {
				switch (nodeI.getExeStatus().intValue()) {
				case NodeInstance.STATUS_RUN:
					status = "run";
					break;
				case NodeInstance.STATUS_FINISH:
					status = "finish";
					break;
				}
				;
			}

			String aNodeStr = "<node id='" + aNode.getObjUid() + "' nodeType='"
					+ aNode.getNodeTypeStr() + "' nodeName='" + nodeName

					+ "' nodeStateShow='"
					+ getDefault(aNode.getNodeStateShow())
					+ "' nodeStateShowBack='"
					+ getDefault(aNode.getNodeStateShowBack())
					+ "' accessClass='" + getDefault(aNode.getAccessClass())
					+ "' specName='" + getDefault(aNode.getSpecName())
					+ "' passTxt='" + getDefault(aNode.getPassTxt())
					+ "' rejectTxt='" + getDefault(aNode.getRejectTxt())
					+ "' autoService='" + autoServiceName + "' authType='"
					+ authType + "' paneName='" + paneName + "' subflow='"
					+ getDefault(aNode.getNodeExt1())
					+ "' decisionExpression='"
					+ getDefault(aNode.getDecisionExpression())
					+ "' decisionType='" + deciType + "' status='" + status
					+ "' nodeDesc='" + getDefault(aNode.getNodeDesc())

					+ "' x='" + aNode.getX() + "' y='" + aNode.getY() + "'/>\n";

			strNodeList.append(aNodeStr);

			List postNodes = aNode.getPostNodeDepes();
			if (postNodes != null && postNodes.size() > 0) {

				for (Iterator<NodeDenpendency> itd = postNodes.iterator(); itd
						.hasNext();) {
					NodeDenpendency nd = itd.next();
					// 这其实有问题

					if (nd.getPostNode() == null) {
						System.out.println("the dependency has not postnode::"
								+ nd.getPostNode());
						continue;
					}
					String aLineStr = "<transition id='" + nd.getObjUid()
							+ "' from='" + nd.getPreNode().getObjUid()
							+ "' to='" + nd.getPostNode().getObjUid();
					if (nd.getCondition() != null) {
						aLineStr = aLineStr
								+ "' condition='"
								+ nd.getCondition().replace("&", "&amp;")
										.replace(">", "&gt;")
										.replace("<", "&lt;")
										.replace("'", "&apos;")
										.replace("\"", "&quot;");
					}

					if (nd.getShowValue() != null) {
						aLineStr = aLineStr + "' showvalue='"
								+ nd.getShowValue();
					}

					aLineStr = aLineStr + "'/>\n";
					strFlowList.append(aLineStr);

				}
			}
		}

		strNodeList.append("</nodes>");
		strFlowList.append("</transitions>");

		xml.append(strNodeList).append(strFlowList);
		xml.append("</processtemplate></wf>");
		echo.putValue("xmlStr", xml.toString());
		this.setInstance(echo);
		System.out.println("PT's XML:::" + xml);
		return DEFAULT_FORWARD;
	}

	private String getDefault(String src) {
		if (src == null) {
			return "";
		}
		return src;
	}

	public static void main(String[] args) {

		System.out.println("a" == null ? "1" : "a");

		// DOPTRead ds = new DOPTRead();
		// try {
		// ds.excute();
		// } catch (ExedoException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

}
