package com.exedosoft.plat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.bo.org.OrgParterValue;
import com.exedosoft.plat.bo.org.SessionParter;
import com.exedosoft.plat.util.DOGlobals;

public class SessionParterDefault implements SessionParter {

	private static Log log = LogFactory.getLog(SessionParterDefault.class);

	public List getParterAuths() {

		String accountUid = DOGlobals.getInstance().getSessoinContext()
				.getUser().getUid();
		
		if(DOGlobals.getInstance().getSessoinContext().getDeleGate()!=null){
			accountUid = DOGlobals.getInstance().getSessoinContext().getDeleGate().getUid();
		}
		return this.getParterAuths(accountUid);
	}

	public List getParterAuths(String accountUid) {

		// //这根据用户和角色两种parter 权限过滤

		List allAuths = new ArrayList();
		// //值根据用户进行权限过滤
		OrgParter userPater = OrgParter.getDefaultEmployee();
		OrgParterValue pv = new OrgParterValue(userPater, accountUid);
		allAuths.add(pv);

		// ////////加入角色

		OrgParter roleParter = OrgParter.getDefaultRole();

		appendRoles(allAuths, accountUid, roleParter);
		log.info("参与验证的组织元素::" + allAuths);

		return allAuths;
	}

	/**
	 * 
	 * @param allAuths
	 * @param accountUid
	 * @param parter
	 */

	private void appendRoles(List allAuths, String accountUid, OrgParter parter) {

		try {
			if (parter != null && parter.isRole()) {
				DOService findRoleService = DOService
						.getService("roles_s_byuserid");

				if (findRoleService != null) {
					List listRoles = findRoleService.invokeSelect(accountUid);

					if (listRoles != null) {
						System.out.println("listRoles::" + listRoles);

						for (Iterator roles = listRoles.iterator(); roles
								.hasNext();) {
							BOInstance boRole = (BOInstance) roles.next();
							OrgParterValue pv = new OrgParterValue(parter,
									boRole.getUid(), boRole.getName());
							allAuths.add(pv);
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List getMenuAuthConfigByAccount(String accountUid) {

		// //////////先获取角色菜单的权限

		// / remove 2010
		// List pureMenuUids = new ArrayList();
		// DOService rfService = DOService
		// .getService("do.bx.role.findbyuserid.xes.ids_xes");
		// List rfList = rfService.invokeSelect(accountUid);
		//
		// for (Iterator it = rfList.iterator(); it.hasNext();) {
		// BOInstance bi = (BOInstance) it.next();
		// pureMenuUids.add(bi.getValue("whatuid"));
		// }

		// /remove long long ago
		// ////////////////用户反向授权需要移除的权限
		// DOService fxService = DOService
		// .getService("auth.menu.user.access.0.xes");
		// List fxList = fxService.invokeSelect(accountUid);
		// for (Iterator it = fxList.iterator(); it.hasNext();) {
		// BOInstance bi = (BOInstance) it.next();
		// pureMenuUids.remove(bi.getValue("whatuid"));
		// }
		// //////////////再加上用户正向授权的权限
		// DOService aService = DOService
		// .getService("auth.menu.user.access.1.xes");
		// List list = aService.invokeSelect(accountUid);
		// for (Iterator it = list.iterator(); it.hasNext();) {
		// BOInstance bi = (BOInstance) it.next();
		// pureMenuUids.add(bi.getValue("whatuid"));
		// }
		return null;
	}

	public List getMenuAuthConfigByRole(String roleUid) {

		List pureMenuUids = new ArrayList();
		DOService rfService = DOService.getService("s_menu_byroleid");
		List rfList = rfService.invokeSelect(roleUid);

		for (Iterator it = rfList.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			pureMenuUids.add(bi.getValue("whatuid"));
		}
		return pureMenuUids;
	}

	public List getFormAuthConfigByAccount(String countUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getFormAuthConfigByRole(String roleUid) {

		List pureMenuUids = new ArrayList();
		DOService rfService = DOService.getService("s_form_byroleid");
		List rfList = rfService.invokeSelect(roleUid);

		for (Iterator it = rfList.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			pureMenuUids.add(bi.getValue("whatuid"));
		}
		return pureMenuUids;
	}

	public List getBIAuthConfigByAccount(String countUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getBIAuthConfigByRole(String roleUid) {

		List pureMenuUids = new ArrayList();
		DOService rfService = DOService.getService("s_boinstance_byroleid");
		List rfList = rfService.invokeSelect(roleUid);

		for (Iterator it = rfList.iterator(); it.hasNext();) {
			BOInstance bi = (BOInstance) it.next();
			pureMenuUids.add(bi.getValue("wheredobo") + bi.getValue("whatuid"));
		}
		return pureMenuUids;
	}

	public List getWfNIAuthConfigByAccount(String countUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getWfNIAuthConfigByRole(String roleUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getWfNodeAuthConfigByAccount(String countUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getWfNodeAuthConfigByRole(String roleUid) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		DOService findRoleService = DOService.getService("roles_s_byuserid");
		List listRoles = findRoleService.invokeSelect("aa");
		System.out.println("listRoles::" + listRoles);

	}
}
