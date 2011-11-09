package com.exedosoft.plat.ui.jquery.form;


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
	    

 */
public class DOSelectDataM extends DOSelectData {
	public DOSelectDataM() {

		this.templateFile = "form/DOSelectDataM.ftl";
	}
	

}
