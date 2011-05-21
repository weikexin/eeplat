package com.exedosoft.plat.cms;

import java.io.Serializable;

public class CmsPosts implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -517312103903173774L;
	public String posts_except ;
	public String posts_title;
	public String posts_author;
	public String posts_content ;
	public String posts_cety ;
	public String posts_url;
	public String posts_date ;
	
	
	public String getPosts_except() {
		return posts_except;
	}
	public void setPosts_except(String postsExcept) {
		posts_except = postsExcept;
	}
	public String getPosts_title() {
		return posts_title;
	}
	public void setPosts_title(String postsTitle) {
		posts_title = postsTitle;
	}
	public String getPosts_author() {
		return posts_author;
	}
	public void setPosts_author(String postsAuthor) {
		posts_author = postsAuthor;
	}
	public String getPosts_content() {
		return posts_content;
	}
	public void setPosts_content(String postsContent) {
		posts_content = postsContent;
	}
	public String getPosts_cety() {
		return posts_cety;
	}
	public void setPosts_cety(String postsCety) {
		posts_cety = postsCety;
	}
	public String getPosts_url() {
		return posts_url;
	}
	public void setPosts_url(String postsUrl) {
		posts_url = postsUrl;
	}
	public String getPosts_date() {
		return posts_date;
	}
	public void setPosts_date(String postsDate) {
		posts_date = postsDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
