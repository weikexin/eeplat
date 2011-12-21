package com.exedosoft.plat.ui.jquery.pane;


/**
 *	左侧菜单样式的控制器
 */
public class LRSplitPaneChild extends TPaneTemplate {

	
	public LRSplitPaneChild() {
		this.templateFile = "panel/LRSplitPaneChild.ftl";
	}

//	public String getHtmlCode(DOIModel iModel) {
//		
//		
//		DOPaneModel pm = (DOPaneModel) iModel; 
//		
//		StringBuffer sb  = new StringBuffer();
//		String width = "50%";
//		if(pm.getPaneWidth()!=null&&!"".equals(pm.getPaneWidth())){
//			width = pm.getPaneWidth();
//		}
//		
//		sb.append("<TD id=td_").append(pm.getName());
//		sb.append(" width=").append(width);
//		sb.append(" >");
//		sb.append("<div  id='").append(pm.getName());
//		
//		if(pm.getAlign()!=null){
//			sb.append("' align='")
//			.append(pm.getAlign())
//			.append("' ");
//		}else{
//			sb.append("' align='center' ");
//		}
//		
//		sb.append(" class='lrschidren' >");
//		genePaneContext(sb, pm);
//		sb.append("</div></TD>");
//		return sb.toString();
//
//	}

}
