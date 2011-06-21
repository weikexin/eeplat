package com.exedosoft.plat.multitenancy;

import com.exedosoft.plat.bo.BaseObject;

public class Tenancy extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1971045119708996696L;

	private String name;
	
	private String modelDB;
	
	private String company;
	
	private String linkMan;
	
	private String linkManTel;
	
	private String linkManEMail;
	
	private String linkManAdress;
	
	private String linkManCard;
	
	private java.sql.Date startDate;
	
	private Integer payType;
	
	private Integer tenancyType;
	
	private Integer tenancyCategory;
	
	private String note;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelDB() {
		return modelDB;
	}

	public void setModelDB(String modelDB) {
		this.modelDB = modelDB;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkManTel() {
		return linkManTel;
	}

	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getTenancyType() {
		return tenancyType;
	}

	public void setTenancyType(Integer tenancyType) {
		this.tenancyType = tenancyType;
	}

	public Integer getTenancyCategory() {
		return tenancyCategory;
	}

	public void setTenancyCategory(Integer tenancyCategory) {
		this.tenancyCategory = tenancyCategory;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getLinkManEMail() {
		return linkManEMail;
	}

	public void setLinkManEMail(String linkManEMail) {
		this.linkManEMail = linkManEMail;
	}

	public String getLinkManAdress() {
		return linkManAdress;
	}

	public void setLinkManAdress(String linkManStreet) {
		this.linkManAdress = linkManStreet;
	}

	public String getLinkManCard() {
		return linkManCard;
	}

	public void setLinkManCard(String linkManCard) {
		this.linkManCard = linkManCard;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}


}
