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
//		
//		Map account = new HashMap();
//		account.put("accountNo","1234567890123456");
//		account.put("accountName","紫枫科技");
//		account.put("buildDate", new Date());
//		account.put("accountMoney", "1000000000000");
//		
//		
//		Account a = new Account("1234567890123456","紫枫科技",new Date(),1000000000000f);
//
		
		DOPaneModel pm = DOPaneModel.getPaneModelByID("0220575e5565477a9f9f4ead5af20212");
		System.out.println("PaneModel::" + pm.getGridModel());
		
		
	}
	
}
