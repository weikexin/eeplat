package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.Escape;

public abstract class DOBaseForm implements DOIView {

	public DOBaseForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public abstract String getHtmlCode(DOIModel property);

	protected boolean isReadOnly(DOFormModel property) {

		return property.isFormModelReadOnly();
	}

	protected int getInputSize(DOFormModel property) {

		int inputSize = 25;
		try {
			if (property.getInputConfig() != null
					&& !"".equals(property.getInputConfig())) {
				if (Integer.parseInt(property.getInputConfig()) != 0) {
					inputSize = Integer.parseInt(property.getInputConfig());
				}
			}
		} catch (NumberFormatException ex) {
			return inputSize;

		}

		return inputSize;

	}

	protected String getDecoration(DOFormModel property) {

		// List decorations = DOUIDecoration.getEvent4FormModel(property);

		StringBuffer buffer = new StringBuffer();
		// for (Iterator it = decorations.iterator(); it.hasNext();) {
		//
		// DOUIDecoration dec = (DOUIDecoration) it.next();
		// buffer.append(" ").append(dec.getDecoPoint());
		// }

		// if (property.getOnBlurJs() == null ||
		// "".equals(property.getOnBlurJs().trim())) {
		// String validconfig = property.getExedojoType();
		// if (validconfig != null && !"".equals(validconfig.trim())) {
		// if (validconfig.indexOf(";") != -1) {
		// String typeAndLength[] = validconfig.split(";");
		// if (typeAndLength[0] != null) {// //Email,Integer,RealNumber
		// validconfig = typeAndLength[0];
		// }
		// }
		// if (validconfig != null && !"".equals(validconfig.trim())) {
		// buffer.append(" onblur=\"validateCheck('")
		// .append(validconfig)
		// .append("',this)\"");
		// }
		// }
		// } else {
		// buffer.append(" onblur='").append(property.getOnBlurJs()).append(
		// "' ");
		// }
		return buffer.toString();

	}

	protected static void appendHtmlJs(StringBuffer buffer, DOFormModel fm) {
		// /////////插入用户自定义的onclick事件
		if (fm.getDoClickJs() != null && !"".equals(fm.getDoClickJs().trim())) {
			buffer.append("  onclick='").append(fm.getDoClickJs()).append("'");
		}
		// /////////插入用户自定义的validJs事件
		if (fm.getOnBlurJs() != null && !"".equals(fm.getOnBlurJs().trim())) {
			buffer.append(" onblur='").append(fm.getOnBlurJs()).append("' ");
		}

		if (fm.getOnChangeJs() != null && !"".equals(fm.getOnChangeJs().trim())) {
//			oninput
//			buffer.append(" onDOMAttrModified='").append(fm.getOnChangeJs())
//					.append("' ");
			buffer.append(" onpropertychange='").append(fm.getOnChangeJs())
					.append("' ");
		}

		if (fm.getOnFocusJs() != null && !"".equals(fm.getOnFocusJs().trim())) {
			buffer.append(" onfocus='").append(fm.getOnFocusJs()).append("' ");
		}

	}

	protected static void appendDOAjaxJs(StringBuffer buffer, DOFormModel fm) {
		// /////////插入用户自定义的onclick事件
		if (fm.getDoClickJs() != null && !"".equals(fm.getDoClickJs().trim())) {
			buffer.append(",doAjax.doClickJs='")
					.append(fm.getEscapeDOClickJs()).append("'");
		}
		// /////////插入用户自定义的validJs事件
		if (fm.getValidJs() != null && !"".equals(fm.getValidJs().trim())) {
			buffer.append(",doAjax.validJs='").append(fm.getValidJs()).append(
					"'");
		}

	}

	protected String appendValidateConfig(DOFormModel fm) {

		StringBuffer buffer = new StringBuffer();
		if (fm.isNotNull()) {
			buffer.append(" exedo_notnull='NotNull'");
		}
		/**
		 * * 客户端验证配置，分为３部分，以;隔开 １，类型：Integer RealNumber EMail Text Others 2, 长度
		 * ３, 其他Script 约束
		 * 
		 */

		if (fm.getExedojoType() != null
				&& !"".equals(fm.getExedojoType().trim())) {
			if (fm.getExedojoType().indexOf(";") != -1) {

				String typeAndLength[] = fm.getExedojoType().split(";");
				if (typeAndLength[0] != null) {// //Email,Integer,RealNumber
					buffer.append(" exedo_validconfig='").append(
							typeAndLength[0]).append("'");
				}

				if (typeAndLength[1] != null) {
					buffer.append(" exedo_length='").append(typeAndLength[1])
							.append("'");
				}
				if (typeAndLength.length > 2) {
					buffer.append(" exedo_script='").append(typeAndLength[2])
							.append("'");
				}
			} else {
				if (fm.getExedojoType() != null) {
					buffer.append(" exedo_validconfig='").append(
							fm.getExedojoType()).append("'");
				}
				buffer.append(" exedo_length='").append("").append("'");
			}
		}
		return buffer.toString();
	}

	public String getAjaxLink(BOInstance bi, String defaultPaneID,
			String doClickJs) {
		return this.getAjaxLink(null, defaultPaneID, doClickJs);
	}

	public String getAjaxLink(BOInstance bi, DOPaneModel paneModel,
			String defaultOpenPaneID, String doClickJs) {

		if (bi.getBo() == null) {
			return bi.getName();
		}

		// /权限下一步要加上的
		// || !this.bo.getMainPaneModel().isAccess()
		if (bi.getBo().getMainPaneModel() == null) {
			return bi.getName();
		}

		if (paneModel == null) {
			paneModel = bi.getBo().getMainPaneModel();
		}

		if (paneModel == null) {
			return "";
		}

		// javascript:doAjax.actionName=

		String dealBus = "&dataBus=setContext&contextKey="
				+ bi.getBo().getName() + "&contextValue=" + bi.getUid();

		StringBuffer buffer = new StringBuffer(
				"<a href=\"javascript:popupDialog('");
		buffer.append(paneModel.getName()).append("','").append(
				paneModel.getTitle()).append("','").append(
				paneModel.getFullCorrHref(bi, null)).append(dealBus);

		if (paneModel.getPaneWidth() != null) {
			buffer.append("','").append(paneModel.getPaneWidth());
		}

		if (paneModel.getPaneHeight() != null) {
			buffer.append("','").append(paneModel.getPaneHeight());
		}

		buffer.append("')\"> ")

		.append(bi.getName()).append("</a>");
		return buffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
