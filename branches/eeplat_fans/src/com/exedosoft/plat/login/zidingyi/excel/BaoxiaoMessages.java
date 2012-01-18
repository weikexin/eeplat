package com.exedosoft.plat.login.zidingyi.excel;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BaoxiaoMessages {
	private String dept;
	private Date bx_date;
	private String bx_people;
	private String project;
	private String mgrdept;
	private String mgrcaiwu;
	private String mgrtotal;
	private String shenhe;
	
	private List<BXTranfee> lptranf = new ArrayList<BXTranfee>();
	private List<BXFixfee> lpfixf = new ArrayList<BXFixfee>();
	private List<BXOtherfee> lpotherf = new ArrayList<BXOtherfee>();
	
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


	public Date getBx_date() {
		return bx_date;
	}


	public void setBx_date(Date bxDate) {
		bx_date = bxDate;
	}


	public String getBx_people() {
		return bx_people;
	}


	public void setBx_people(String bxPeople) {
		bx_people = bxPeople;
	}


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}


	public String getMgrdept() {
		return mgrdept;
	}


	public void setMgrdept(String mgrdept) {
		this.mgrdept = mgrdept;
	}


	public String getMgrcaiwu() {
		return mgrcaiwu;
	}


	public void setMgrcaiwu(String mgrcaiwu) {
		this.mgrcaiwu = mgrcaiwu;
	}


	public String getMgrtotal() {
		return mgrtotal;
	}


	public void setMgrtotal(String mgrtotal) {
		this.mgrtotal = mgrtotal;
	}


	public String getShenhe() {
		return shenhe;
	}


	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
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


	public List<BXTranfee> getLptranf() {		
		return lptranf;
	}


	public void setLptranf(List<BXTranfee> lptranf) {
		this.lptranf = lptranf;
	}


	public List<BXFixfee> getLpfixf() {
		return lpfixf;
	}


	public void setLpfixf(List<BXFixfee> lpfixf) {
		this.lpfixf = lpfixf;
	}


	public List<BXOtherfee> getLpotherf() {
		return lpotherf;
	}


	public void setLpotherf(List<BXOtherfee> lpotherf) {
		this.lpotherf = lpotherf;
	}

	

}
