package com.exedosoft.plat.ui;

import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.DOParameterService;


public class RemoveParameterHelper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ///////给定一个服务
		// //////////给给一个需要保留的parameter列表
		// /////////修改这个服务的sql语句
		// ////////////删除不需要的参数关联
		DOService aService = DOService.getService("db.perambulate.item.insert");
		String leftParas = "OBJUID,NA_ITEM_NAME,NA_APPLY_PERSON,NA_PERAMBULATE_COMP,ID_QUALIFICATION_CARD,NA_COMP_ADDRESS,QT_PERAMBULATE_MINE,IN_PERAMBULATE_PHASE,IN_ITEM_KIND,IN_ECONOMY_TYPE,NA_GEOGRAPHY_POSITION,NA_AREA_COORDINATE,QT_BASIC_SECTION,QT_QTRBASIC_SECTION,QT_SMALL_SECTION,QT_CONVERTBASIC_SECTION,QT_TOTAL_AREA,QT_LONGEAST_START,QT_LONGEAST_END,QT_LATNORTH_START,QT_LATNORTH_END,IN_PROSPECT_KIND,QT_COST,IN_ITEM_TYPE,ID_LICENCEID,approve_org,approve_org_code";
		String theSql = aService.getMainSql().toLowerCase();
		System.out.println("Orign SQL:" + theSql);
		String[] paras = leftParas.split(",");
		List dops = aService.retrieveParaServiceLinks();
//		HbmDAO dao = new HbmDAO();
//		dao.setAutoClose(false);
		try {

			for (Iterator it = dops.iterator(); it.hasNext();) {
				DOParameterService aDop = (DOParameterService) it.next();
				boolean isLeft = false;
				for (int i = 0; i < paras.length; i++) {
					if (aDop.getDop().getName().equalsIgnoreCase(paras[i])) {
						isLeft = true;
						break;
					}
				}
				if (!isLeft) {
					String aPara = aDop.getDop().getName().toLowerCase() + ",";
					theSql = theSql.replaceAll(aPara, "");
					theSql = theSql.replaceAll("(\\,\\?\\))", ")");
					DAOUtil.INSTANCE().delete(aDop);
				}
			}
			aService.setMainSql(theSql);
			DAOUtil.INSTANCE().store(aService);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally{
//			dao.closeSession();
//		}

		System.out.println("After replace::" + theSql);

	}

}
