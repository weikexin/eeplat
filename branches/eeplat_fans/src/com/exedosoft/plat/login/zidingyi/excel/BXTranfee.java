package com.exedosoft.plat.login.zidingyi.excel;

import java.sql.Date;

public class BXTranfee {
	private Date beginDate;
	private String beginAddress;
	private Date endDate;
	private String endAddress;
	private String transportation;
	private Integer danjushu;
	private Double fee;
	private String desc;
	private String kongge;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getBeginAddress() {
		return beginAddress;
	}
	public void setBeginAddress(String beginAddress) {
		this.beginAddress = beginAddress;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getDanjushu() {
		return danjushu;
	}
	public void setDanjushu(Integer danjushu) {
		this.danjushu = danjushu;
	}
	public String getKongge() {
		return kongge;
	}
	public void setKongge(String kongge) {
		this.kongge = kongge;
	}

}
