//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.zephyr.erpscan.struts.searchPic;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-20-2006
 * 
 * XDoclet definition:
 * @struts.form name="searchPicForm"
 */
public class SearchPicForm extends ActionForm {

	// --------------------------------------------------------- Instance Variables
	private String serialno;
	private String scandate;
	private String depno;
	private int id;
	private String status_code;
	private String operator;
	
	// added 2006.10.23 
	private String serialno_begin;
	private String serialno_end;
	private String scandate_begin;
	private String scandate_end;
	
	private String status_code_ex;
	private String status_code_in;
	private String status_code_as;
	private String status_code_le;
	private String invoice_type; //应付发票中的子票类型
	private String contract_type;//合同的子票类型
	
	private String link;
	
	
	// added 2007.01.16
	private String form_number;
	private String form_number_in;
	private String form_number_as;
	private String form_number_le;
	
	/**
	 * add Nov 9, 2009, 9:47:04 AM
	 */
	private String form_sunmoney_contract; //金额
	private String form_abstract_contract; //摘要
	private String status_code_contract;   //状态
	
	
	// --------------------------------------------------------- Methods

	/**
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link 要设置的 link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return scandate_begin
	 */
	public String getScandate_begin() {
		return scandate_begin;
	}

	/**
	 * @param scandate_begin 要设置的 scandate_begin
	 */
	public void setScandate_begin(String scandate_begin) {
		this.scandate_begin = scandate_begin;
	}

	/**
	 * @return scandate_end
	 */
	public String getScandate_end() {
		return scandate_end;
	}

	/**
	 * @param scandate_end 要设置的 scandate_end
	 */
	public void setScandate_end(String scandate_end) {
		this.scandate_end = scandate_end;
	}

	/**
	 * @return serialno_begin
	 */
	public String getSerialno_begin() {
		return serialno_begin;
	}

	/**
	 * @param serialno_begin 要设置的 serialno_begin
	 */
	public void setSerialno_begin(String serialno_begin) {
		this.serialno_begin = serialno_begin;
	}

	/**
	 * @return serialno_end
	 */
	public String getSerialno_end() {
		return serialno_end;
	}

	/**
	 * @param serialno_end 要设置的 serialno_end
	 */
	public void setSerialno_end(String serialno_end) {
		this.serialno_end = serialno_end;
	}

	/**
	 * @return status_code
	 */
	public String getStatus_code() {
		return status_code;
	}

	/**
	 * @param status_code 要设置的 status_code
	 */
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getScandate() {
		return scandate;
	}

	public void setScandate(String scandate) {
		this.scandate = scandate;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		try {
			request.setCharacterEncoding("GBK");
		}
		catch (UnsupportedEncodingException ex) {
			//log.error("encoding error! ",ex);
			ex.printStackTrace();
		}
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		try {
			request.setCharacterEncoding("GBK");
		}
		catch (UnsupportedEncodingException ex) {
			//log.error("encoding error! ",ex);
			ex.printStackTrace();
		}
	}

	public String getDepno() {
		return depno;
	}

	public void setDepno(String depno) {
		this.depno = depno;
	}

	/**
	 * @return status_code_ex
	 */
	public String getStatus_code_ex() {
		return status_code_ex;
	}

	/**
	 * @param status_code_ex 要设置的 status_code_ex
	 */
	public void setStatus_code_ex(String status_code_ex) {
		this.status_code_ex = status_code_ex;
	}

	/**
	 * @return status_code_in
	 */
	public String getStatus_code_in() {
		return status_code_in;
	}

	/**
	 * @param status_code_in 要设置的 status_code_in
	 */
	public void setStatus_code_in(String status_code_in) {
		this.status_code_in = status_code_in;
	}

	public String getStatus_code_as() {
		return status_code_as;
	}

	public void setStatus_code_as(String status_code_as) {
		this.status_code_as = status_code_as;
	}

	public String getStatus_code_le() {
		return status_code_le;
	}

	public void setStatus_code_le(String status_code_le) {
		this.status_code_le = status_code_le;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getForm_number_as() {
		return form_number_as;
	}

	public void setForm_number_as(String form_number_as) {
		this.form_number_as = form_number_as;
	}

	public String getForm_number_in() {
		return form_number_in;
	}

	public void setForm_number_in(String form_number_in) {
		this.form_number_in = form_number_in;
	}

	public String getForm_number_le() {
		return form_number_le;
	}

	public void setForm_number_le(String form_number_le) {
		this.form_number_le = form_number_le;
	}

	public String getForm_number() {
		return form_number;
	}

	public void setForm_number(String form_number) {
		this.form_number = form_number;
	}

	public String getForm_sunmoney_contract() {
		return form_sunmoney_contract;
	}

	public void setForm_sunmoney_contract(String form_sunmoney_contract) {
		this.form_sunmoney_contract = form_sunmoney_contract;
	}

	public String getForm_abstract_contract() {
		return form_abstract_contract;
	}

	public void setForm_abstract_contract(String form_abstract_contract) {
		this.form_abstract_contract = form_abstract_contract;
	}

	public String getStatus_code_contract() {
		return status_code_contract;
	}

	public void setStatus_code_contract(String status_code_contract) {
		this.status_code_contract = status_code_contract;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public String getContract_type() {
		return contract_type;
	}

	public void setContract_type(String contract_type) {
		this.contract_type = contract_type;
	}

}

