package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueHtmlXss implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		if (fm.getValue() != null && !fm.getValue().trim().equals("")) {
			StringBuffer sb=new StringBuffer();
			sb.append("<div style=").append("\"WIDTH:100%; HEIGHT: 631px; BACKGROUND-COLOR: #FFFFFF; overflow-y:scroll; SCROLLBAR-FACE-COLOR: #c2d3fc; SCROLLBAR-HIGHLIGHT-COLOR: #c2d3fc; SCROLLBAR-SHADOW-COLOR: BLACK; SCROLLBAR-3DLIGHT-COLOR: #c2d3fc; SCROLLBAR-ARROW-COLOR:#000000; SCROLLBAR-TRACK-COLOR: FFFFFF; SCROLLBAR-DARKSHADOW-COLOR: EAECEC\"").append(">").append(StringUtil.unFilterXss(fm.getValue())).append("</div>");
			return sb.toString();
		} else {
			return "&nbsp;";
		}
	}

}
