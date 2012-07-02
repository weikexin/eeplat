package com.exedosoft.plat.action.customize.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.StringUtil;

public class DOExportService extends DOExportSimple {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568992871873045123L;

	@Override
	public String excute() throws ExedoException {

		StringBuilder sb = new StringBuilder("<export>");
		DOBO bo = DOBO.getDOBOByName("DO_Service");
		DOBO boPara = DOBO.getDOBOByName("DO_Parameter");
		DOBO boRule = DOBO.getDOBOByName("DO_Rule");
		BOInstance biService = bo.getCorrInstance();
		if (biService != null) {
			DOService pm = DOService.getService(biService.getUid());

			// 业务对象下面的服务
			sb.append("\n<service><li>").append(StringUtil.filter(biService.toJSONString())).append(
					"</li></service>");

			// 服务下面的参数
			DOService servParaService = DOService
					.getService("DO_Parameter_Service_findbyserviceUid");
			sb.append("\n<parameter_service>");
			List paraServices = servParaService
					.invokeSelect(biService.getUid());
			appendLi(sb, paraServices);
			sb.append("</parameter_service>");
			
			List paras = new ArrayList();
			for(Iterator it = paraServices.iterator(); it.hasNext();){
				BOInstance biParaService = (BOInstance)it.next();
				BOInstance biPara = boPara.getInstance(biParaService.getValue("parameterUid"));
				paras.add(biPara);
			}
			sb.append("\n<parameter>");
			appendLi(sb, paras);
			sb.append("</parameter>");
			

			// 服务下面的规则
			DOService servRuleService = DOService
					.getService("DO_Service_Rule_findbyserviceuid");
			sb.append("\n<rule_service>");
			List ruleServices = servRuleService
					.invokeSelect(biService.getUid());
			appendLi(sb, ruleServices);
			sb.append("</rule_service>");
			
			List rules = new ArrayList();
			for(Iterator it =ruleServices.iterator(); it.hasNext();){
				BOInstance biRuleService = (BOInstance)it.next();
				BOInstance biRule = boRule.getInstance(biRuleService.getValue("ruleUid"));
				rules.add(biRule);
			}
			sb.append("\n<rule>");
			appendLi(sb, rules);
			sb.append("</rule>");
			
			
		}
		sb.append("</export>");
		this.setEchoValue(sb.toString());
		return DEFAULT_FORWARD;

	}

}
