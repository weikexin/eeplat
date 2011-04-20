package com.exedosoft.plat.action;

import java.io.Serializable;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

public interface DOAction extends Serializable{

	
  /**
   * 直接转向可能有问题(有的Web Container 不支持) 所以要返回一个包含链接的对象
   * 可以支持web framework，ajax web framework.
   * paras 或 instance 可以通过注入实现。
   */
	String excute() throws ExedoException;

	/**
	 * 当前Action所配置到的Service
	 * @param aService
	 */
    void setService(DOService aService);

    /**
     * 把执行结果（如果是一个BOInstaace）塞入exedo 环境。
     * @param instance
     */
    
    void setInstance(BOInstance instance);
    
    /**
     * 把执行结果（如果是BOInstance 列表）塞入exedo环境。
     * @param instances
     */
    
    void setInstances(List instances);
}
