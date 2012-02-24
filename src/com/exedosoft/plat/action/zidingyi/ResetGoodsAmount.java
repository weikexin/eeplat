package com.exedosoft.plat.action.zidingyi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class ResetGoodsAmount extends DOAbstractAction {
/**
 * 用于删除 销售单或采购单前，还原商品库存
 * 
 * */
	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub
		// crm_document_xsjh_update_path_size
		try {
			//获取单据中对应的商品记录:
			List<BOInstance> datas = new ArrayList<BOInstance>();
			datas = this.service.invokeSelect();
			if(datas != null && datas.size() > 0) {
				//删除单据商品
				DOService delDjServ = DOService.getService("crm_tradegoods_delete_form");
				//删除库存变更记录
				DOService delBgServ = DOService.getService("crm_stockalter_kcbg_delete_by_satgoodsuid");
				//更新商品库存
				DOService updateServ = DOService.getService("crm_goods_spzl_update_gamount");
				
				/**
				 * 记录中是否存在相同的商品uid，先统计后，在一次更新库存
				 * 
				 * **/
				//已存在的商品uid分号隔开
				String uidList = "";
				
				//商品uid,对应的商品的可相加到库存的数量 (还原规则：采购 - ; 销售 +)
				List<Object[]> goodsUidList = new ArrayList<Object[]>();
				for(int i = 0; i < datas.size(); i++) {
					BOInstance bi = datas.get(i);
					String id = bi.getUid();
					//商品uid
					String tgname = bi.getValue("tgname");
					//商品数量
					double tgamount = bi.getDoubleValue("tgamount");
					//单据类型:cgd,采购;xsd,销售;
					String tgflag = bi.getValue("tgflag");
					
					//判断是否已存在
					if(uidList.indexOf(tgname) != -1) {
						//存在，数量相加
						for(int j = 0; j < goodsUidList.size(); j++) {
							Object[] obj = goodsUidList.get(j);
							String uid = (String) obj[0];
							double amount = (Double) obj[1];
							if(uid.equals(tgname)) {
								if(tgflag != null && "-1".equals(tgflag)) {
									amount = amount+tgamount;
								} else if(tgflag != null && "1".equals(tgflag)) {
								//采购 -
									amount = amount-tgamount;
								} else {
									return "商品所属单据类型不对？？";
								}
								goodsUidList.remove(obj);
								obj = new Object[2];
								obj[0] = uid;
								obj[1] = amount;
								goodsUidList.add(obj);
							}
						}
					} else {
						//不存在，直接添加
						uidList = uidList + ";"+ tgname;
						
						Object[] obj = new Object[2];
						obj[0] = tgname;
						// 销售 +
						if(tgflag != null && "-1".equals(tgflag)) {
							obj[1] = tgamount;
						} else if(tgflag != null && "1".equals(tgflag)) {
						//采购 -
							obj[1] = -tgamount;
						} else {
							return "商品所属单据类型不对？？";
						}
						goodsUidList.add(obj);
					}
					
					//循环一条单据商品记录，单据商品记录
					delDjServ.invokeUpdate(id);
					//循环一条单据商品记录，删除一条库存变更记录
					delBgServ.invokeUpdate(id);
				}
				
				//循环结束后，更新商品库存
				//获取商品库存
				DOService selectServ = DOService.getService("crm_goods_browse_form");
				List<BOInstance> goodsList = null;
				for(int i = 0; i < goodsUidList.size(); i++) {
					Object[] obj = goodsUidList.get(i);
					String uid = (String) obj[0];
					double amount = (Double) obj[1];
					goodsList = selectServ.invokeSelect(uid);
					if(goodsList != null && goodsList.size() > 0) {
						BOInstance bi = goodsList.get(0);
						double gAmount = bi.getDoubleValue("gamount");
						double endAmount = amount + gAmount;
						double goodsAmount = OperationUtil.round(endAmount, 2);
						updateServ.invokeUpdate(goodsAmount+"",uid);
					}
					
				}
			}
		} catch (Exception e) {
			return this.DEFAULT_FORWARD;
		}

		return DEFAULT_FORWARD;
	}

}
