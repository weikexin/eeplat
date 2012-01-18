package com.exedosoft.plat.login.zidingyi.excel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BaoxiaoMessagesII {
	private String dept;
	private String year;
	private String month;
	private String day;
	private String bx_people;
	private String action;
	
	private String totalmgr;
	private String deptmgr;
	private String shenhe;
	private String chuna;
	private String lingkuan;
	
	private List<BXMessages> bxmsg = new ArrayList<BXMessages>();
	
	private Double tranfee;
	private Double fixfee;
	private Double otherfee;
	private Double totalfee;
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getBx_people() {
		return bx_people;
	}
	public void setBx_people(String bxPeople) {
		bx_people = bxPeople;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTotalmgr() {
		return totalmgr;
	}
	public void setTotalmgr(String totalmgr) {
		this.totalmgr = totalmgr;
	}
	public String getDeptmgr() {
		return deptmgr;
	}
	public void setDeptmgr(String deptmgr) {
		this.deptmgr = deptmgr;
	}
	public String getShenhe() {
		return shenhe;
	}
	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	public String getChuna() {
		return chuna;
	}
	public void setChuna(String chuna) {
		this.chuna = chuna;
	}
	public String getLingkuan() {
		return lingkuan;
	}
	public void setLingkuan(String lingkuan) {
		this.lingkuan = lingkuan;
	}
	public List<BXMessages> getBxmsg() {
		return bxmsg;
	}
	public void setBxmsg(List<BXMessages> bxmsg) {
		this.bxmsg = bxmsg;
	}
	public Double getTranfee() {
		return tranfee;
	}
	public void setTranfee(Double tranfee) {
		this.tranfee = tranfee;
	}
	public Double getFixfee() {
		return fixfee;
	}
	public void setFixfee(Double fixfee) {
		this.fixfee = fixfee;
	}
	public Double getOtherfee() {
		return otherfee;
	}
	public void setOtherfee(Double otherfee) {
		this.otherfee = otherfee;
	}
	public Double getTotalfee() {
		return totalfee;
	}
	public void setTotalfee(Double totalfee) {
		this.totalfee = totalfee;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
}
