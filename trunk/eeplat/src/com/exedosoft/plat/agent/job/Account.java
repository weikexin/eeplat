package com.exedosoft.plat.agent.job;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exedosoft.plat.ui.DOPaneModel;

public class  Account{

	/**
	 * 账户号
	 */
	private String accountNo;
	/**
	 * 开户单位/人
	 */
	private String accountName;
	/**
	 * 开户日期
	 */
	private Date  buildDate;

	/**
	 * 账户金额（人民币账户）
	 */
	private float  accountMoney;
	public Account(String accountNo,String accountName,Date buildDate,float accountMoney){
		


	}
	
	public static void main(String[] args){

		DOPaneModel pm = DOPaneModel.getPaneModelByID("0220575e5565477a9f9f4ead5af20212");
		System.out.println("PaneModel::" + pm.getGridModel());
		
		
	}
	
}
