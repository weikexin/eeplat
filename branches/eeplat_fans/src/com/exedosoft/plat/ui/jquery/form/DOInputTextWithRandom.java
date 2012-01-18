package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;

//13683008834
public class DOInputTextWithRandom extends DOBaseForm {

	public String getHtmlCode(DOIModel aModel) {

		// dojo.widget.byId('mianpeihaoma').isEmpty =
		// this.checked;dojo.widget.byId('mianpeihaoma').updateClass('Empty');

		DOFormModel fm = (DOFormModel) aModel;
		System.out.println(fm.getGridModel().getController().getClass());
		StringBuffer buffer = new StringBuffer();

		buffer.append("<input  style='border:#B3B3B3 1px solid;'   onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\"  type='text' name='").append(fm.getFullColName())
				.append("' id='").append(fm.getFullColName()).append("'");
		buffer.append(getDecoration(fm));


		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

		// buffer.append(" dojoType='");
		// if (fm.getExedojoType() != null
		// && !"".equals(fm.getExedojoType().trim())) {
		// buffer.append(fm.getExedojoType()).append("'");
		// } else {
		// buffer.append("ValidationTextBox'");
		// }
		//		
		//		
		//	
		//
		// if (fm.getIsNull() != null
		// && !fm.getIsNull().booleanValue()) {
		// buffer.append(" required='true' ");
		// }
		
		
		
		

		String theValue = fm.getValue();

		DOPaneModel cPaneModel = null;
		if (fm.getGridModel() != null) {
			cPaneModel = fm.getGridModel().getContainerPane();
		}

				
		if (theValue != null) {

			buffer.append(" value='").append(theValue).append("'");
		}
		if (isReadOnly(fm)) {
			buffer.append(" readonly='readonly' ");

		}

		buffer.append(this.appendValidateConfig(fm));

		buffer.append(" size=\"").append(getInputSize(fm)).append("\"/>");
		
		if (fm.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		
		//if(fm.getNote()!=null&&!"".equals(fm.getNote()))
		//{
			buffer.append("<a href=\"javascript:show(document.getElementById('random'),'exedo/web/checkcode/random.jsp')\"><img ")
			.append(" style=\"width:45px;height:15px;margin-top:3px;\" ")
			.append(" src='").append("exedo/web/checkcode/random.jsp'").append(" alt=\"看不清\" name=\"random\" border=\"1px\" ")
			.append(" align=\"absmiddle\" id=\"random\" valign=\"absmiddle\" ></a>");
		//}
		


		return buffer.toString();
	}

}
