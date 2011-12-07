package com.exedosoft.plat.ui.jquery.form;

import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOIModel;
import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.util.Escape;

//
//表单中只有一个文本框时，回车页面刷新错误
//
//在一个也没中如果一个form标签中只有文本框<input type="text" />,当在输入完数据后点击回车，会发现页面进行了刷新，代码如下：
//Html代码  收藏代码
//
//    <body>      
//            <form>  
//                <input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>" />  
//                <textarea rows="2" cols="2" name="test"></textarea>  
//                <input type="text" name="noticeNo" id="noticeNo"/>  
//            </form>         
//    </body>  
//
//有如下解决方法：
//
//1.在文本域元素中加入onkeydown或者onkeypress事件，判断如果用户点击了回车就阻止默认的行为。
//Java代码  收藏代码
//
//    <body>          
//            <form>  
//                <input type="textsdfsd" name="noticeNo"  onkeypress="if(event.keyCode==13||event.which==13){return false;}" />      
//            </form>  
//    </body>  
//
// 
//
//2.在form中在加入一个隐藏的文本域
//
//<input type="text" name="test" style="display:none"/>
//Html代码  收藏代码
//
//    <body>      
//            <form>  
//                <input type="hidden" id="contextPath" name="contextPath" value="<%=request.getContextPath()%>" />  
//                <textarea rows="2" cols="2" name="test"></textarea>  
//                <input type="text" name="noticeNo" id="noticeNo"/>  
//                <input type="text" name="test" style="display:none"/>  
//            </form>         
//    </body>  
//
// 说明：大家可以发现，里面是没有提交按钮的即
//
//<input type="sumit" />,要是里面有提交按钮的话，第二种方法时不使用的，只能使用第一种方法，因为通过查看你会发现，当你点击一个表单时，或者表单的任何元素会发现，提交按钮时激活状态，所以单点击回车时就执行了提交操作。


public class DOInputText extends DOBaseForm {

	@Override
	public String getHtmlCode(DOIModel aModel) {

		DOFormModel fm = (DOFormModel) aModel;
		StringBuffer buffer = new StringBuffer();

		///onkeypress='if(event.keyCode==13||event.which==13){return false;}'  加上这个可以阻止上文所说的内容
		
		buffer
				.append(
						"<input  style='border:#B3B3B3 1px solid;'  onclick=\"this.style.borderColor='#406B9B'\" onmouseover=\"this.style.borderColor='#99E300'\" onmouseout=\"this.style.borderColor='#A1BCA3'\"  type='text' name='")
				.append(fm.getColName()).append("' id='").append(
						fm.getFullColID()).append("'");
		buffer.append(getDecoration(fm));

		buffer.append(" title='").append(fm.getL10n().trim()).append("'");

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

		if(fm.getExedojoType() != null && fm.getExedojoType().trim().length()>0) {
			String maxlen = fm.getExedojoType().trim();
			if(maxlen.startsWith(";")){
				maxlen = maxlen.substring(1).trim();
				if(maxlen.matches("^\\d+$")) {
					buffer.append(" maxlength='").append(maxlen).append("' ");
				}
			} else if("MoBile".equals(maxlen)) {
				buffer.append(" maxlength='11' ");
			} 
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
	
	public static void main(String[] args){
		
		DOFormModel fm = DOFormModel.getFormModelByID("636f5ca21e864c18835150a787d8c1bc");
		System.out.println("Is not null:::" + fm.isNotNull());

	}

}
