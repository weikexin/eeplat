package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.StringUtil;

/**
 * 静态列表应该单独作为一个表
 * @author IBM
 *
 */
public class DOStaticListNoEmpty extends DOBaseForm {

	public DOStaticListNoEmpty() {
		super();
	}

	public String getHtmlCode(DOIModel iModel) {

		DOFormModel property = (DOFormModel) iModel;

		List list = StringUtil.getStaticList(property.getInputConfig());

		return formSelectStr(property, list);
	}

	String formSelectStr(DOFormModel property, List list) {

		StringBuffer buffer = new StringBuffer();

		String value = property.getValue();
		// try {
		//
		// if (DOGlobals.getInstance().getSessoinContext()
		// .getFormInstance() != null) {
		// String refreshValue = DOGlobals.getInstance().getSessoinContext()
		// .getFormInstance().getValue(
		// property.getFullColName());
		// if(refreshValue!=null){
		// value = refreshValue;
		// }
		// }
		// } catch (RuntimeException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		buffer.append("<select   style='");
		if(property.getStyle()!=null)
		{
			if("100".equals(property.getStyle()))
			{
				buffer.append(" ");
			}else
			{
				buffer.append(property.getStyle());
			}
		}else
		{
			buffer.append("width:100px");
		}
		buffer.append("'");
		if (property.getInputConstraint() != null
				&& property.getInputConstraint().startsWith("@multi@")) {
			buffer.append(" size='10' multiple='multiple' ");
		} 

		buffer.append(" id=\"").append(property.getFullColID()).append("\" ");

		buffer.append(" name=\"").append(property.getFullColName()).append(
				"\" ");

		buffer.append(" title='").append(property.getL10n().trim()).append("'");

		if (isReadOnly(property)) {
			buffer.append(" disabled=\"disabled\" ");
		}

		buffer.append(this.appendValidateConfig(property));

		buffer.append(getDecoration(property));

		// /////////插入用户自定义的onclick事件
		if (property.getDoClickJs() != null
				&& !"".equals(property.getDoClickJs().trim())) {
			buffer.append(" onChange=\"");

			buffer.append("doAjax.formName='")
					.append(property.getTargetForms()).append("',");
			if (property.getContainerPaneName() != null) {
				buffer.append("doAjax.invokePaneName='").append(
						property.getContainerPaneName()).append("',");
			}
			// //默认传递两个参数
			buffer.append(property.getEscapeDOClickJs()).append("(this,'")
					.append(property.getValue()).append("')\"");
		}

		buffer.append(" >\n");


		if (list != null) {
			boolean isFirst = true;
			String defaultValue = getDefaultListValue(property);
			for (Iterator it = list.iterator(); it.hasNext();) {
				String[] half = (String[]) it.next();
				buffer.append("<option value=\"").append(half[0]);
				buffer.append("\"");
				if (isFirst) {
					if (value == null
							&& defaultValue == null
							&& (property.getDefaultValue() != null && !property
									.getDefaultValue().trim().equals(""))) {
						buffer.append(" selected=\"selected\"  ");
					}
					isFirst = false;
				}

				if (value != null) { // ////////修改的情况

					// ////////////////////add by weikx at 20070806
					// 只要修改的情况输出标签就可以了
					DOPaneModel cPaneModel = null;
					if (property.getGridModel() != null) {
						cPaneModel = property.getGridModel().getContainerPane();
					}

					if (cPaneModel != null
							&& cPaneModel.getIsCache() != null
							&& cPaneModel.getIsCache().intValue() == DOPaneModel.CACHE_DYN) {// ///当面板采用动态缓存

						String instanceName = "ins_"
								+ StringUtil.get_Name(property.getGridModel()
										.getService().getName());
						buffer.append("<% String theValue =").append(
								instanceName).append(".getValue(\"").append(
								property.getColName()).append("\");\n");

						buffer.append("if(DOStaticList.isChecked.(\"").append(
								half[0]).append("\",theValue))\n{");
						buffer.append(" out.print(selected=\"selected\"); }\n");

						buffer.append("%>");

					} else {// //////////不用输出模板的情况
						if (isChecked(half[0], value)) {
							buffer.append(" selected=\"selected\"  ");
						}
					}
					// ////////////end add by weikx at 20070806

				} else { // //////添加的情况

					if (defaultValue != null && defaultValue.equals(half[0])) {
						buffer.append(" selected=\"selected\"  ");
					}

				}
				buffer.append(">");
				buffer.append(half[1]);
				buffer.append("</option>\n");
			}
		}
		buffer.append("</select>");

		if (property.isNotNull()) {
			buffer.append("&nbsp;<font color='red'>*</font>");
		}
		return buffer.toString();
	}

	/**
	 * 静态下拉列表中,缺省的值
	 * 
	 * @param property
	 *            TODO
	 * @return
	 */
	String getDefaultListValue(DOFormModel property) {

		if (property.getInputConfig() != null) {
			if (property.getInputConfig().indexOf("@") != -1) {
				return property.getInputConfig().substring(
						property.getInputConfig().indexOf("@") + 1);
			}
		}
		return null;
	}

	/**
	 * 静态下拉列表情况,返回相应的值 翻译的作用；根据数据库存储的值，翻译为界面显示的值。
	 * 
	 * @param property
	 *            TODO
	 * @return 界面显示的值
	 */
	protected String getStaticListValue(DOFormModel property) {

		List list = StringUtil.getStaticList(property.getInputConfig());
		String value = property.getValue();
		if (value == null) {
			value = this.getDefaultListValue(property);
		}
		for (Iterator it = list.iterator(); it.hasNext();) {
			String[] halfs = (String[]) it.next();

			if ((value != null && value.equals(halfs[0]))) {
				return halfs[1];
			}
		}
//		if (value != null) {
//			return value;
//		}
		return "&nbsp;";
	}

	/**
	 * 判断值是否被选中
	 * 
	 * @param aValue
	 * @param values
	 * @return
	 */

	public static boolean isChecked(String aValue, String values) {

		if (values == null || aValue == null) {
			return false;
		}
		if (aValue.equals(values)) {
			return true;
		}

		String[] strs = values.split(";");
		for (int i = 0; i < strs.length; i++) {
			if (strs[i].equals(aValue)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
