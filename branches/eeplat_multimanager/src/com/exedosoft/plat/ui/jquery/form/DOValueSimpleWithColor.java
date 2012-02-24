package com.exedosoft.plat.ui.jquery.form;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;
import com.exedosoft.plat.util.StringUtil;

public class DOValueSimpleWithColor implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();
		

		if (value != null && !value.trim().equals("")) {
			if(fm.getInputConfig()!=null&&!"".equals(fm.getInputConfig()))
			{
				//return "<span style='"+fm.getStyle()+"'>"+value+"<";;
				StringBuffer sb=new StringBuffer();
				List list = StringUtil.getStaticList(fm.getInputConfig());
				String color="";
				
				for(Iterator it = list.iterator();it.hasNext();){
					String[] one = (String[])it.next();
					if(one != null && one.length == 2) {
						if(one[0].equals(value)) {
							color = one[1];
//							System.out.println("color====="+color);
							break;
						}
					}
				}
				if(color != null && !"".equals(color.trim())) 
					color = "color:"+color;
				sb.append("<span style='").append(color).append("'>").append(value).append("</span>");
				return sb.toString();
			}

			return value;
		} else {
			return "&nbsp;";
		}
	}

}
