package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;

/**
 * 
 * 选择不同类型的多个业务对象的数值的执行过程是这样：
 * 有多个类协同完成，包括
 * 
 *1, DOSelectInvoke:在这儿配置隐藏域,	if (property.getInputConfig() != null) {
			
			////////////////隐藏的inputType formModel Name
			buffer.append("<input name=\"").append(property.getInputConfig())
					.append("\" type=\"hidden\"").append(" id=\"").append(
							property.getInputConfig()).append("\" ");

			if (property.getValue() != null
					&& !property.getValue().trim().equals("")) {
				buffer.append(" value=\"").append(property.getValue()).append(
						"\"");
			}
			buffer.append(" />");
		}
		并且在DOValueService 里面把分类实际值传过去了：
			buffer.append(",doAjax.selectInvoke='").append(fm.getFullColName())
					.append("'");

 2,DOSelectDataM:在这儿把隐藏域和值传递给js,		if (fm.getInputConfig() != null
				&& fm.getInputConfig().indexOf(";") != -1) {

			String[] types = fm.getInputConfig().split(",");
			buffer.append(",doAjax.inputType='").append(types[0]).append("'");
			buffer.append(",doAjax.inputTypeValue='").append(types[1]).append(
					"'");

		}

 3,ExedoAjax 里面 selectData 执行：
 		 if($(this.inputType)!=null && this.inputTypeValue!=null ){
		   $(this.inputType).value = this.inputTypeValue;
		 }
	    

 * 
 * 
 * 
 * @author anolesoft
 *
 */
public class DOSelectDataM extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel property) {

		DOFormModel fm = (DOFormModel) property;
		StringBuffer buffer = new StringBuffer("<button ");


		// ////增加装饰
		buffer.append(getDecoration(fm));
		// ///////end 增加装饰
		buffer.append(" title='").append(fm.getL10n()).append("'");
		// /// 原来的考虑::::用随机数,而不是用formmodel.id, 这么考虑是恐怕界面上出现两个formmodel 派生的button
		// ////////现在的考虑:::直接使用fm.id 后台可以得到这个id.
		String buttonId = fm.getObjUid();

		buffer.append(" id='").append(buttonId).append("'");
		buffer.append(" onclick=\"javascript:doAjax.initExedo()");

		if (fm.getLinkService() != null) {
			buffer.append(",doAjax.actionName='").append(
					fm.getLinkService().getObjUid()).append("'");
		}

		buffer.append(",doAjax.confirmScript='");
		if (fm.getEchoJs() != null) {
			buffer.append(fm.getEscapeEchoJs());
		} else if (fm.getController().getOtherScript() != null) {
			buffer.append(fm.getController().getEscapeOtherScript());
		} else {
			buffer.append("true");
		}
		buffer.append("'");

		String theForm = fm.getTargetForms();

		buffer.append(",doAjax.formName='").append(theForm).append("'");

		// /////////插入用户自定义的onclick事件
		if (fm.getDoClickJs() != null && !"".equals(fm.getDoClickJs().trim())) {
			buffer.append(",doAjax.doClickJs='")
					.append(fm.getEscapeDOClickJs()).append("'");
		}
		if (fm.getController().getStandbyAttrs() != null) {
			buffer.append(",").append(fm.getController().getStandbyAttrs());
		}

		if (fm.getContainerPaneName() != null) {
			buffer.append(",doAjax.invokePaneName='").append(
					fm.getContainerPaneName()).append("'");
		}

		////传入界面的值有什么问题？？？？？？？？？？？？、、
		if (fm.getInputConfig() != null
				&& fm.getInputConfig().indexOf(";") != -1) {

			String[] types = fm.getInputConfig().split(",");
			buffer.append(",doAjax.inputType='").append(types[0]).append("'");
			buffer.append(",doAjax.inputTypeValue='").append(types[1]).append(
					"'"); 

		}

		// inputTypeValue
		// inputType

		buffer.append(",doAjax.selectDataM('");
		buffer.append(theForm);
		buffer.append("')\" >\n");

		buffer.append(fm.getL10n());
		buffer.append("\n</button>");
		return buffer.toString();
	}

}
