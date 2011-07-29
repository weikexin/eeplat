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

		if (DOGlobals.getInstance().getSessoinContext().getDeleGate() != null) {
			accountUid = DOGlobals.getInstance().getSessoinContext()
					.getDeleGate().getUid();
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

		// /多租户下对应创建者，内置角色
		BOInstance user = DOGlobals.getInstance().getSessoinContext().getUser();
		if ("2".equals(user.getValue("asrole"))) {
			OrgParterValue pvRole = new OrgParterValue(roleParter,
					"40288031288a2b8501288a3d009d000d",
					"Defualt_System_Manager");
			allAuths.add(pvRole);
		}

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

		// /定义返回的授权菜单列表
		List authMenuUids = new ArrayList();
		// //定义服务，该服务可以根据用户查找所有授权的菜单
		// 该服务基于平台的缺省实现，即菜单只对角色授权，服务对应的SQL语句
		// SELECT a.objUID, a.whatUid
		// FROM do_authorization a INNER JOIN
		// do_org_user_role ur ON a.ouUid = ur.ROLE_UID
		// WHERE (a.whatType = 16) AND (ur.USER_UID = ?)
		DOService rfService = DOService.getService("s_menu_byuserid");
		// ////////执行服务，并组装到authMenuUids
		if (rfService != null) {
			log.info("定义有s_menu_byuserid服务！");

			List rfList = rfService.invokeSelect(accountUid);
			for (Iterator it = rfList.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				authMenuUids.add(bi.getValue("whatuid"));
			}
		}

		// return authMenuUids;
		// 开发阶段 ，可以实时看到新增的菜单
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
