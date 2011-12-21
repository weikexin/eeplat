package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.DOGlobals;

public class DOXmlDownLoadWithTip implements DOIView {

	public String getHtmlCode(DOIModel aModel) {
		// newrecord
		DOFormModel fm = (DOFormModel) aModel;

		// paneModelUid
		if (fm.getData() != null) {

			if (fm.getLinkPaneModel() == null) {
				return "&nbsp;";
			}

			StringBuffer strLink = new StringBuffer();

			strLink.append("<a href=\"");

			strLink.append(DOGlobals.PRE_FULL_FOLDER).append(
					"file/downloadfile_xml.jsp?paneModelUid=").append(
					fm.getLinkPaneModel().getObjUid())
					.append("&contextBOName=").append(fm.getDoBO().getName())
					.append("&contextInstanceUid=").append(
							fm.getData().getUid());
			if (fm.getLinkService() != null) {
				strLink.append("&contextServiceName=").append(
						fm.getLinkService().getName());
			}

			strLink.append("\">下载</a>");

			if (fm.getData().getValue("newrecord") == null) {

				strLink.append("<img src='").append(DOGlobals.PRE_FULL_FOLDER)
						.append("images/").append("newrecord.gif")
						.append("'/>");
			}
			return strLink.toString();

		} else {
			return "&nbsp;";
		}
	}

}
