package com.exedosoft.plat.action;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DOThreadContext;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

/**
 * @author  IBM
 */

public abstract class DOAbstractAction implements Serializable, DOAction {
	
	
	public static final String DEFAULT_FORWARD = "success";
	public static final String NO_FORWARD = "noforward";


	protected static Log logger = LogFactory.getLog(DOAbstractAction.class);

	protected DOService service;


	protected BOInstance actionForm;

	protected boolean isInRuleScope = false;// ////////是否在rulescope下，一般在运行环境下都在,设置这个属性的目的用于测试。

	public DOAbstractAction() {
	}

	/**
	 * 用于测试的初始化数据库连接方法
	 * 
	 */
	public void initTransConnection() {

		try {
			if (DOGlobals.getInstance().getRuleContext().getConnection(
					this.service.getBo().getDataBase()) != null) {
				isInRuleScope = true;
			} else {
				this.service.getBo().getDataBase().registerConnection(true);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * 用于测试的关闭数据库连接方法
	 * 
	 */

	public void ifCloseTransConnection() {
		if (!isInRuleScope) {
			this.service.getBo().getDataBase().closeContextCon(true);
		}
	}

	/**
	 * @param aService
	 * @uml.property  name="service"
	 */
	public void setService(DOService aService) {
		service = aService;
		actionForm = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();
	}
	


	/**
	 * 直接转向可能有问题(有的Web Container 不支持) 所以要返回一个包含链接的对象 可以支持web framework，ajax web
	 * framework. paras 或 instance 可以通过注入实现。
	 */

	public abstract String excute() throws ExedoException;

	public void setInstance(BOInstance instance) {
		DOThreadContext context = DOGlobals.getInstance().getRuleContext();
		
		if(service!=null){
			context.put(service.getName(), instance);
		}
		
		//////当时注释掉 肯定是有道理的，恐怕影响全局？？？？？？？？？？？？？？？？？？？？？？？、
//		context.setInstance(instance);

	//////////现在放开
		context.setInstance(instance);

	

	}

	public void setInstances(List instances) {
		// TODO Auto-generated method stub
		DOThreadContext context = DOGlobals.getInstance().getRuleContext();
	//	context.put(service.getName(), instances);
		context.setInstances(instances);

	}
	
	public void setEchoValue(String aValue){
		
		DOGlobals.getInstance().getRuleContext().setEchoValue(aValue);

		
	}
	
	public String getEchoValue(){
		
			return  DOGlobals.getInstance().getRuleContext().getEchoValue();
	}


}
