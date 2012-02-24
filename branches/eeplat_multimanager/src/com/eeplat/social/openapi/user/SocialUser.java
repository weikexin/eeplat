package com.eeplat.social.openapi.user;

import com.exedosoft.plat.bo.BaseObject;

public class SocialUser extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7416812774750944542L;

	
	private String name;
	private String userId;
	private String nickName;
	private String userName;
	private String userDescription;
	private String gender;
	private String province;
	private String city;
	private String location;
	
	private String userEmail;
	private String figureurl;
    private String figureurl1;
    private String figureurl2;
	
    private String openSite;

    public final static String OPEN_SITE_WEIBO = "weibo";
    public final static String OPEN_SITE_QQ = "qq";
    public final static String OPEN_SITE_163 = "163";
    public final static String OPEN_SITE_RENREN = "renren";

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user_name) {
		this.userName = user_name;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String user_description) {
		this.userDescription = user_description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFigureurl() {
		return figureurl;
	}

	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}

	public String getFigureurl1() {
		return figureurl1;
	}

	public void setFigureurl1(String figureurl1) {
		this.figureurl1 = figureurl1;
	}

	public String getFigureurl2() {
		return figureurl2;
	}

	public void setFigureurl2(String figureurl2) {
		this.figureurl2 = figureurl2;
	}


	public String getOpenSite() {
		return openSite;
	}

	public void setOpenSite(String openSite) {
		this.openSite = openSite;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
