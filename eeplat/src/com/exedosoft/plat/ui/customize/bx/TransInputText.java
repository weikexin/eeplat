package com.exedosoft.plat.ui.customize.bx;

import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.DOBaseForm;

public class TransInputText extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel property) {
		// TODO Auto-generated method stub
		DOFormModel fm = (DOFormModel) property;
		StringBuffer buffer = new StringBuffer();
		buffer
				.append(
						"<input  style='border:#B3B3B3 1px solid;'   onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\"  type='text' name='")
				.append(fm.getColName()).append("' id='").append(
						fm.getColName()).append("'");
		buffer.append(getDecoration(fm));

		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

//		String theValue = fm.getValue();
		String sn = null;
		if(fm != null && fm.getData() != null)
			sn = fm.getData().getValue("sn");
		if((sn == null || sn.trim().equals("")) && fm != null && fm.getData() != null)
			sn = fm.getData().getValue("toname");
		if((sn == null || sn.trim().equals("")) && fm != null && fm.getData() != null)
			sn = fm.getData().getValue("projectmanageruid");
		if((sn == null || sn.trim().equals("")) && fm != null && fm.getData() != null)
			sn = fm.getData().getValue("mainsaleruid");
		
			
		String cn = null;
		if(sn != null && !sn.trim().equals("")){
			cn = LDAPPeopleUtil.getLDAPCNBySN(sn);
		} 
		
		DOPaneModel cPaneModel = null;
		if (fm.getGridModel() != null) {
			cPaneModel = fm.getGridModel().getContainerPane();
		}

		if (cn != null) {

			buffer.append(" value='").append(cn).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(this.appendValidateConfig(fm));
		appendHtmlJs(buffer, fm);

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");

		if (fm.isNotNull()) {

			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		if (fm.getNote() != null && !"".equals(fm.getNote())) {
			if (fm.getStyle() != null && !"".equals(fm.getStyle())) {
				buffer.append("&nbsp;&nbsp;&nbsp;<span style=\"").append(
						fm.getStyle()).append("\">").append(fm.getNote())
						.append("</span>");
			} else {
				buffer.append(fm.getNote());
			}
		}

		return buffer.toString();
	}

}
