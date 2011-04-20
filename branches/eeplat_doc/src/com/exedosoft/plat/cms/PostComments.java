package com.exedosoft.plat.cms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;



public class PostComments {
	

	
	public static void commitComment(HttpServletRequest request){
		String posts_id = request.getParameter("posts_id");
		String comment = request.getParameter("comment");
		DOService aService = DOService.getService("cms_comment_insert");
		BOInstance bo = new BOInstance();
		bo.putValue("comment_posts_id", posts_id);
		bo.putValue("comment_author", ((Map)SessionAttribute.getInstance().getAttributeMap().get("userinfo")).get("user_nicename"));
		bo.putValue("comment_author_content", comment);
		try {
			aService.invokeAll(bo);
		} catch (ExedoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//为了跳转到发表评论的文章,需要记录posts_id号 
		Map map = new HashMap();
		map.put("posts_id", posts_id);
		request.setAttribute("comment_posts_id", map);
	}
	

}
