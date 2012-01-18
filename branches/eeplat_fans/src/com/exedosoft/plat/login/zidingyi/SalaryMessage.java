package com.exedosoft.plat.login.zidingyi;

import java.sql.Date;

public class SalaryMessage {
	private String objuid;
	private Date month; 
	private String name; 
	private double basesalary;
	private double buckshee;
	private double rentdeduct; 
	private double leavededuct;
	private double factsalary;
	private double payyanglaoinsure;
	private double payshiyeinsure;
	private double payyilaioinsure;
	private double payshebaofee;
	private double payhousingsurplus;
	private double taxbefore;
	private double taxget;
	private String taxlv;
	private double taxrm;
	private double tax;
	private double taxafter;
	private String remark;
	
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBasesalary() {
		return basesalary;
	}
	public void setBasesalary(double basesalary) {
		this.basesalary = basesalary;
	}
	public double getBuckshee() {
		return buckshee;
	}
	public void setBuckshee(double buckshee) {
		this.buckshee = buckshee;
	}
	public double getRentdeduct() {
		return rentdeduct;
	}
	public void setRentdeduct(double rentdeduct) {
		this.rentdeduct = rentdeduct;
	}
	public double getLeavededuct() {
		return leavededuct;
	}
	public void setLeavededuct(double leavededuct) {
		this.leavededuct = leavededuct;
	}
	public double getFactsalary() {
		return factsalary;
	}
	public void setFactsalary(double factsalary) {
		this.factsalary = factsalary;
	}
	public double getPayyanglaoinsure() {
		return payyanglaoinsure;
	}
	public void setPayyanglaoinsure(double payyanglaoinsure) {
		this.payyanglaoinsure = payyanglaoinsure;
	}
	public double getPayshiyeinsure() {
		return payshiyeinsure;
	}
	public void setPayshiyeinsure(double payshiyeinsure) {
		this.payshiyeinsure = payshiyeinsure;
	}
	public double getPayyilaioinsure() {
		return payyilaioinsure;
	}
	public void setPayyilaioinsure(double payyilaioinsure) {
		this.payyilaioinsure = payyilaioinsure;
	}
	public double getPayshebaofee() {
		return payshebaofee;
	}
	public void setPayshebaofee(double payshebaofee) {
		this.payshebaofee = payshebaofee;
	}
	public double getPayhousingsurplus() {
		return payhousingsurplus;
	}
	public void setPayhousingsurplus(double payhousingsurplus) {
		this.payhousingsurplus = payhousingsurplus;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTaxafter() {
		return taxafter;
	}
	public void setTaxafter(double taxafter) {
		this.taxafter = taxafter;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getTaxbefore() {
		return taxbefore;
	}
	public void setTaxbefore(double taxbefore) {
		this.taxbefore = taxbefore;
	}
	public String getObjuid() {
		return objuid;
	}
	public void setObjuid(String objuid) {
		this.objuid = objuid;
	}
	public double getTaxget() {
		return taxget;
	}
	public void setTaxget(double taxget) {
		this.taxget = taxget;
	}
	public String getTaxlv() {
		return taxlv;
	}
	public void setTaxlv(String taxlv) {
		this.taxlv = taxlv;
	}
	public double getTaxrm() {
		return taxrm;
	}
	public void setTaxrm(double taxrm) {
		this.taxrm = taxrm;
	}
}
