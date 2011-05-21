/**
 * 
 */
package com.zephyr.erpscan.object;

import java.io.Serializable;

import com.zephyr.erpscan.framework.util.IConstants;

/**
 * 
 * Class Name：PicObject.java
 * Note：图片表的对象
 * Date: Jan 18, 2010,3:05:02 PM
 * @Author:liBing
 */
public class PicObject implements Serializable{
	
	private String serialno;
	private int indexno;
	private String depno;
	private String scanDate;
	private String clerk;
	private String path;
	private String backupPath;
	private int flag;
	private int status;
	private int type;
	private int id;
	private String operator;
	private String bu_code;
	private String bar_code;
	private int invoice_type;      //应付发票的子票据类型
	private String form_sunmoney;  //金额
	private String form_abstract;  //摘要
	private String simple_abstract;//简单摘要
	private int contract_type;//合同发票的子票据类型
	
	
	// 在OracleERP系统中用于描述单据处理状态
	private String description;
	
	// 在ORACLE系统中的编号
	private String form_number;
	
	private boolean invoice;

	public String getBackupPath() {
		return backupPath;
	}

	public void setBackupPath(String backupPath) {
		this.backupPath = backupPath;
	}

	public String getForm_number() {
		return form_number;
	}

	public void setForm_number(String form_number) {
		this.form_number = form_number;
	}

	public String getClerk() {
		return clerk;
	}

	public void setClerk(String clerk) {
		this.clerk = clerk;
	}

	public String getDepno() {
		return depno;
	}

	public void setDepno(String depno) {
		this.depno = depno;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getScanDateStr() {
		if(scanDate.length()==8){
			scanDate = scanDate.substring(0, 4) + "."
					+scanDate.substring(4, 6) + "."
					+scanDate.substring(6, 8);
		}
		return scanDate;
	}
	
	public String getScanDate() {
		
		return scanDate;
	}

	public void setScanDate(String scanDate) {
		this.scanDate = scanDate;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	/**
	 * 
	 */
	public PicObject() {
		// TODO Auto-generated constructor stub
	}

	public int getIndexno() {
		return indexno;
	}

	public void setIndexno(int indexno) {
		this.indexno = indexno;
	}

	/**
	 * @return state
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * 
	  * Method Name:getStatusStr
	  * Note：显示用户状态
	  * @return
	  * Date：Jan 14, 2010, 9:48:56 AM
	  * @Author:liBing
	 */
	public String getStatusStr(){
		switch (status){
			//已录入
			case IConstants.PIC_ALREADY_INPUT:
				return "jsp.pic.list.status.already";
			//未录入
			case IConstants.PIC_NOT_INPUT:
				return "jsp.pic.list.status.not";
			//曾录入
			case IConstants.PIC_OLD_INPUT:
				return "jsp.pic.list.status.old";
			//处理中
			case IConstants.PIC_RELAY_INPUT:
				return "jsp.pic.list.status.relay";
			//其它
			case IConstants.PIC_STATUS_UNKNOW:
				return "jsp.pic.list.status.unknow";
			default:
				return "";
		}
		
	}

	/**
	 * @param state 要设置的 state
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return invoice
	 */
	public boolean isInvoice() {
		return invoice;
	}

	/**
	 * @param invoice 要设置的 invoice
	 */
	public void setInvoice(boolean invoice) {
		this.invoice = invoice;
	}

	/**
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description 要设置的 description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getBu_code() {
		return bu_code;
	}

	public void setBu_code(String bu_code) {
		this.bu_code = bu_code;
	}

	public String getBar_code() {
		return bar_code;
	}

	public void setBar_code(String bar_code) {
		this.bar_code = bar_code;
	}

	public String getForm_sunmoney() {
		return form_sunmoney;
	}

	public void setForm_sunmoney(String form_sunmoney) {
		this.form_sunmoney = form_sunmoney;
	}

	public String getForm_abstract() {
		return form_abstract;
	}

	public void setForm_abstract(String form_abstract) {
		this.form_abstract = form_abstract;
	}

	public String getSimple_abstract() {
		return simple_abstract;
	}

	public void setSimple_abstract(String simple_abstract) {
		this.simple_abstract = simple_abstract;
	}

	public int getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(int invoice_type) {
		this.invoice_type = invoice_type;
	}

	public int getContract_type() {
		return contract_type;
	}

	public void setContract_type(int contract_type) {
		this.contract_type = contract_type;
	}
	
	

}
