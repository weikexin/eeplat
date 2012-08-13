package com.exedosoft.plat.ui.jquery.form;

import java.math.BigDecimal;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOIView;

public class DOValueSimpleScriptValue implements DOIView {
	

	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		String value = fm.getValue();
		
		//exp=/1000000&type=int&suffix=M
		/**
		 * paras:exp:表达式(+?, -?, *?, /?, %?);type:结果类型：int,float(n),double(n);suffix:后缀单位	
		 * 
		 * */
		String exp = null;
		String type = null;
		String suffix = null;

		String tempValue = null;
		if(value.matches("^\\d+$")) {
			String scriptValue = fm.getScriptValue();
			if(scriptValue != null && !scriptValue.trim().equals("")) {
				String[] paras = scriptValue.split("&");
				for(int i = 0; i < paras.length; i ++) {
					String[] para = paras[i].split("=");
					if(para.length == 2) {
						if("exp".equals(para[0])) {
							exp = para[1];
						}
						if("type".equals(para[0])) {
							type = para[1];
						}
						if("suffix".equals(para[0])) {
							suffix = para[1];
						}
					}
				}
			}
			
			if(exp != null && !exp.trim().equals("")) {
				String symb = exp.trim().substring(0, 1);
				String number = exp.trim().substring(1);
				Double d = 0.00;
				if(number.matches("^\\d+$")) {
					if("+".equals(symb)) {
						d = Double.parseDouble(value) + Double.parseDouble(number);
					} else if("-".equals(symb)) {
						d = Double.parseDouble(value) - Double.parseDouble(number);
					} else if("*".equals(symb)) {
						d = Double.parseDouble(value) * Double.parseDouble(number);
					} else if("/".equals(symb)) {
						d = Double.parseDouble(value) / Double.parseDouble(number);
					} else if("%".equals(symb)) {
						d = Double.parseDouble(value) % Double.parseDouble(number);
					}
				}
				
				if(type != null && !type.trim().equals("")) {
					if(type.indexOf("int") != -1) {
						Integer inte = d.intValue();
						tempValue = inte.toString();
					} else if(type.indexOf("float") != -1) {
						String n = type.substring(type.indexOf("(")+1, type.indexOf(")"));
						if(n != null && n.trim().matches("^\\d+$"));
						int numb = Integer.parseInt(n);
						Double doub = DOValueSimpleScriptValue.round(d, numb);
						tempValue = doub.toString();
					} else if(type.indexOf("double") != -1) {
						String n = type.substring(type.indexOf("(")+1, type.indexOf(")"));
						if(n != null && n.trim().matches("^\\d+$"));
						int numb = Integer.parseInt(n);
						Double doub = DOValueSimpleScriptValue.round(d, numb);
						tempValue = doub.toString();
					}
				}
				
				
				if(suffix != null && !suffix.trim().equals("")) {
					tempValue = tempValue + suffix;
				}

			}
		}		
		
		if(tempValue != null) {
			value = tempValue;
		}
		
		if (value != null && !value.trim().equals("")) {
			if(fm.getStyle()!=null&&!"".equals(fm.getStyle()))
			{
				//return "<span style='"+fm.getStyle()+"'>"+value+"<";;
				StringBuffer sb=new StringBuffer();
				sb.append("<span style='").append(fm.getStyle()).append("'>").append(value).append("</span>");
				return sb.toString();
			}

			return value;
		} else {
			return "&nbsp;";
		}
	}
	
	

    public static double round(double v,int scale){

        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
