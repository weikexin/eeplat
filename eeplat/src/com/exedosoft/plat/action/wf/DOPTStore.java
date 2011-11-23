package com.exedosoft.plat.action.wf;

import java.io.StringReader;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.xml.DOMXmlUtil;
import com.exedosoft.plat.Transaction;

import com.exedosoft.wf.pt.NodeDenpendency;
import com.exedosoft.wf.pt.PTNode;

import com.exedosoft.plat.DAOUtil;

public class DOPTStore extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997150272030863948L;

	@Override
	public String excute() throws ExedoException {

		DOBO bo = DOBO.getDOBOByName("do_pt_processtemplate");
		BOInstance curPt = bo.getCorrInstance();
		
		Transaction t = bo.getDataBase().getTransaction();
		t.begin();


		try {
			
			// /删除选中模板的节点
			if (curPt != null) {
				DOService deleService = DOService
						.getService("do_pt_node_deletebyptuid");
				deleService.invokeUpdate(curPt.getUid());

				////删除关联
				DOService deleRelations = DOService
				.getService("do_pt_node_denpendency_deleterubbish");
				deleRelations.invokeUpdate();
			}else{
				this.setEchoValue("当前Session丢失，请重新登录!");
				return NO_FORWARD;
			}

			String ptXml = DOGlobals.getInstance().getSessoinContext()
					.getFormInstance().getValue("ptXml");
			System.out.println("PT_XML::" + ptXml);

			DOService insertNodeService = DOService.getService("do_pt_node_insert");
			DOService getNodeByUid = DOService.getService("do_pt_node_browse");
			DOService insertNodeDService = DOService
					.getService("do_pt_node_denpendency_insert");
			DOService getNodeDByUid = DOService.getService("do_pt_node_denpendency_browse");
			
			StringReader read = new StringReader(ptXml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);

			Document doc = DOMXmlUtil.getInstance().getDocumentBuilder().parse(
					source);
			NodeList nList = doc.getElementsByTagName("node");

			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Document.ELEMENT_NODE) {
					Element e = (Element) node;
					PTNode ptNode = new PTNode();
					List existNodes = getNodeByUid.invokeSelect(e.getAttribute("id"));
					if(existNodes!=null && existNodes.size() > 0){
						continue;
					}
					
					ptNode.setObjUid(e.getAttribute("id"));
					ptNode.setNodeName(e.getAttribute("nodeName"));
					ptNode.setAccessClass(e.getAttribute("accessClass"));
					if (e.getAttribute("authType") != null
							&& !e.getAttribute("authType").trim().equals("")) {
						ptNode.setAuthType(Integer.parseInt(e
								.getAttribute("authType")));
					}
					
					String autoService = e.getAttribute("autoService");
					if(autoService!=null  && !autoService.trim().equals("")){
						DOService aService = DOService.getService(autoService);
						if(aService!=null){
							ptNode.setAutoExcutesService(aService);
						}
					}
						
					String paneName = e.getAttribute("paneName");
					if(paneName!=null  && !paneName.trim().equals("")){
						DOPaneModel aPM = DOPaneModel.getPaneModelByName(paneName);
						if(aPM!=null){
							ptNode.setPane(aPM);
						}
					}
					
					ptNode.setConditon(e.getAttribute("condition"));
					ptNode.setDecisionExpression(e
							.getAttribute("decisionExpression"));
					if(e.getAttribute("decisionType")!=null && !e.getAttribute("decisionType").trim().equals("")){
						ptNode.setDecisionType(Integer.parseInt(e.getAttribute("decisionType")));
					}
					ptNode.setNodeDesc(e.getAttribute("nodeDesc"));
					ptNode.setNodeStateShow(e.getAttribute("nodeStateShow"));
					ptNode.setNodeStateShowBack(e
							.getAttribute("nodeStateShowBack"));

					ptNode.setNodeType(e.getAttribute("nodeType"));

					ptNode.setPassTxt(e.getAttribute("passTxt"));
					ptNode.setRejectTxt(e.getAttribute("rejectTxt"));
					ptNode.setSpecName(e.getAttribute("specName"));
					ptNode.setNodeExt1(e.getAttribute("subflow"));
					
					if(e.getAttribute("x")!=null){
						ptNode.setX(Integer.parseInt(e.getAttribute("x")));
					}
					
					if(e.getAttribute("y")!=null){
						ptNode.setY(Integer.parseInt(e.getAttribute("y")));
					}
					
					System.out.println("PTNODE::::::::" + ptNode);
					DAOUtil.INSTANCE().store(ptNode, insertNodeService);

				}
			}

			nList = doc.getElementsByTagName("transition");
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Document.ELEMENT_NODE) {
					Element e = (Element) node;
					List exists = getNodeDByUid.invokeSelect(e.getAttribute("id"));
					if(exists!=null && exists.size() > 0){
						continue;
					}
					NodeDenpendency nd = new NodeDenpendency();
					nd.setObjUid(e.getAttribute("id"));
					nd.setCondition(e.getAttribute("condition"));
					nd.setShowValue(e.getAttribute("showvalue"));
					PTNode preNode = PTNode.getNodeById(e.getAttribute("from"));
					nd.setPreNode(preNode);

					PTNode postNode = PTNode.getNodeById(e.getAttribute("to"));
					nd.setPostNode(postNode);

					System.out.println("NodeDenpendency::" + nd);
					DAOUtil.INSTANCE().store(nd, insertNodeDService);

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t.rollback();
		}
		t.end();

		// TODO Auto-generated method stub
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {

		DOPTStore ds = new DOPTStore();
		try {
			ds.excute();
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
