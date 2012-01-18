package com.exedosoft.plat.action.wf;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.wf.pt.NodeDenpendency;
import com.exedosoft.wf.pt.PTNode;
import com.exedosoft.wf.pt.ProcessTemplate;

public class DOPTRead extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997150272030863948L;

	@SuppressWarnings("unchecked")
	@Override
	public String excute() throws ExedoException {

		DOBO bo = DOBO.getDOBOByName("do_pt_processtemplate");
		BOInstance curPt = bo.getCorrInstance();
		BOInstance echo = new BOInstance();
		if (curPt == null) {
			System.out.println("没有选择工作流模板!");
			this.setEchoValue("没有选择工作流模板!");
			return NO_FORWARD;
		}
		ProcessTemplate pt = ProcessTemplate.getPTByID(curPt.getUid());

		StringBuilder xml = new StringBuilder("<wf>	<processtemplate name='")
		.append(pt.getPtName())
		.append("'>");
		StringBuilder strNodeList = new StringBuilder("<nodes>");
		StringBuilder strFlowList = new StringBuilder("<transitions>");

		////鏈夋椂闂存妸+鏀规帀
		for (Iterator<PTNode> it = pt.retrieveNodes().iterator(); it.hasNext();) {
			PTNode aNode = it.next();
			String autoServiceName = "";
			String deciType = "";
			String authType = "";
			if(aNode.getAutoExcutesService()!=null){
				 autoServiceName = aNode.getAutoExcutesService().getName();
			}
			if(aNode.getDecisionType()!=null){
				deciType = aNode.getDecisionType().toString();
			}
			if(aNode.getAuthType()!=null){
				authType = aNode.getAuthType().toString();
			}
			String paneName = "";
			if(aNode.getPane()!=null){
				paneName = aNode.getPane().getName();
			}
			String aNodeStr = "<node id='" + aNode.getObjUid() + "' nodeType='"
					+ aNode.getNodeTypeStr() + "' nodeName='"
					+ aNode.getNodeName()

					+ "' nodeStateShow='" + getDefault(aNode.getNodeStateShow())
					+ "' nodeStateShowBack='" + getDefault(aNode.getNodeStateShowBack())
					+ "' accessClass='" + getDefault(aNode.getAccessClass())
					+ "' specName='" + getDefault(aNode.getSpecName())
					+ "' passTxt='" + getDefault(aNode.getPassTxt()) + "' rejectTxt='"
					+ getDefault(aNode.getRejectTxt()) + "' autoService='"
					+ autoServiceName + "' authType='"
					+ authType + "' paneName='"
					+ paneName 
					 + "' subflow='"
						+ getDefault(aNode.getNodeExt1())
					+ "' decisionExpression='"
					+ getDefault(aNode.getDecisionExpression()) + "' decisionType='"
					+ deciType + "' nodeDesc='"
					+ getDefault(aNode.getNodeDesc())

					+ "' x='" + aNode.getX() + "' y='" + aNode.getY()
					+ "'/>\n";

			strNodeList.append(aNodeStr);

			List postNodes = aNode.getPostNodeDepes();
			if (postNodes != null && postNodes.size() > 0) {

				for (Iterator<NodeDenpendency> itd = postNodes.iterator(); itd
						.hasNext();) {
					NodeDenpendency nd = itd.next();
					//这其实有问题
					
					
					if(nd.getPostNode()==null){
						System.out.println("the dependency has not postnode::" + nd.getPostNode());
						continue;
					}
					String aLineStr = "<transition id='" + nd.getObjUid()
							+ "' from='" + nd.getPreNode().getObjUid()
							+ "' to='" + nd.getPostNode().getObjUid();
					if (nd.getCondition() != null) {
						aLineStr = aLineStr
								+ "' condition='"
								+ nd.getCondition().replace("&", "&amp;")
										.replace(">", "&gt;").replace("<",
												"&lt;").replace("'", "&apos;")
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

	
	private String getDefault(String src){
		if(src==null){
			return "";
		}
		return src;
	}
	public static void main(String[] args) {
		
		System.out.println("a"==null?"1":"a");

//		DOPTRead ds = new DOPTRead();
//		try {
//			ds.excute();
//		} catch (ExedoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
