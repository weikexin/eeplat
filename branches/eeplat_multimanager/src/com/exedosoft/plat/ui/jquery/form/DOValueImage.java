package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueImage implements DOIView {

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel property = (DOFormModel) aModel;

		String areaConfig = property.getInputConfig();

		int width = 0;
		int height = 0;
		if (areaConfig != null && !areaConfig.equals("")) {
			String[] configs = areaConfig.split(";");
			if (configs != null && configs.length == 2) {
				if (Integer.parseInt(configs[0]) != 0) {
					width = Integer.parseInt(configs[0]);
				}
				if (Integer.parseInt(configs[1]) != 0) {
					height = Integer.parseInt(configs[1]);
				}
			}
		}

		if (property.getValue() != null) {

			StringBuffer aPath = StringUtil.getAttachMentFile(property.getValue());
			StringBuffer buffer = new StringBuffer();
			buffer.append("<a  class='exedo_link' href=");
			buffer.append(aPath).append(">");
			// //////////对picture类型的特殊处理

			if (isPic(property.getValue())
					&& (property.getValue().indexOf(",") == -1)) {
				buffer.append("<img src=").append(aPath).append(" border='0'");
				if (width != 0) {
					buffer.append(" width='").append(width).append("' ");
				}
				if (height != 0) {
					buffer.append(" height='").append(height).append("' ");
				}
				buffer.append(">");
			} else {
				buffer.append(property.getValue());
			}
			buffer.append("</a>");
			return buffer.toString();
		}
		return "&nbsp;";

	}

	/**
	 * 判断是否为图片
	 */

	public static boolean isPic(String fileName) {

		boolean isPic = false;
		fileName = fileName.toLowerCase();
		isPic = fileName.endsWith(".jpg") || fileName.endsWith(".bmp")
				|| fileName.endsWith(".gif") || fileName.endsWith(".png")
				|| fileName.endsWith(".tif") || fileName.endsWith(".pcx");

		return isPic;
	}

}
