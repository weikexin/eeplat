package com.exedosoft.plat.action;

import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.DOAuthorization;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.bo.org.SessionParterFactory;
//import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.WFDAO;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 支持反向授权的用户授权方案。 用户权限保存的思路，首先判断一下是否需要进行权限配置，如果不需要即返回。
 * 如果需要，把配置在用户上的权限全部去掉，重新进行配置。
 * 
 * @author IBM
 * 
 */

public class DOAuthRoleSave extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 101111111111L;

	private static DOService setRoleExcel = DOService
			.getService("do.bx.role.update.xes.excel");

	private static DOService setRoleExcelNull = DOService
			.getService("do.bx.role.update.xes.excel.no");

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		String parterUid = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("parterUid");
		if (parterUid == null) {
			return null;
		}

		// /////////////////////////isExcel 是否可以导出到excel
		String isExcel = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("isExcel");
		if ("5".equals(isExcel)) {
			setRoleExcel.invokeUpdate(parterUid);
		} else {
			setRoleExcelNull.invokeUpdate(parterUid);
		}
		// /////////////////////end

		// /////////formModel的权限配置

		boInstanceAuth(parterUid, "tbclassxuequ");

		boInstanceAuth(parterUid, "tbgrade");

		boInstanceAuth(parterUid, "tbkemu");

		// tbclassxuequ
		formAuth(parterUid);

		menuAuth(parterUid);

		return null;
	}

	/**
	 * 配置基础数据的权限，xes 如服务中心，年级 ，科目
	 * 
	 * @param parterUid
	 */
	private void boInstanceAuth(String parterUid, String boName) {

		List allAuthBIs = SessionParterFactory.getSessionParter()
				.getBIAuthConfigByRole(parterUid);
		DOBO boRole = OrgParter.getDefaultRole().getDoBO();
		BOInstance role = boRole.getInstance(parterUid);

		if (role == null) {
			System.out.println("Role is null!!!!!!!!!");
			return;
		}

		System.out.println("allAuthBIs:::" + allAuthBIs);

		String fmConfigs = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue(boName);

		DOBO authBO = DOBO.getDOBOByName(boName);

		if (fmConfigs == null) {
			return;
		}

		String[] fConfigs = fmConfigs.split(";");
//
//		WFDAO dao = new WFDAO();
//		dao.setAutoClose(false);
		try {
			for (int i = 0; i < fConfigs.length; i++) {
				String[] aConfig = fConfigs[i].split(",");
				if (aConfig[0] != null && !"".equals(aConfig[0])) {

					String dbAccess = "0";
					if (allAuthBIs != null
							&& allAuthBIs.contains(authBO.getObjUid()
									+ aConfig[0])) {
						dbAccess = "1";
					}
					if (aConfig[0] != null) {
						addAuth(aConfig[1], dbAccess, aConfig[0],  role,
								DOAuthorization.WHAT_BOINSTANCE, authBO
										.getObjUid());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		dao.closeSession();

	}

	/**
	 * 配置按钮的权限 ,导出到excel?
	 * 
	 * @param parterUid
	 */

	private void formAuth(String parterUid) {

		List allAuthForms = SessionParterFactory.getSessionParter()
				.getFormAuthConfigByRole(parterUid);
		DOBO boRole = OrgParter.getDefaultRole().getDoBO();
		BOInstance role = boRole.getInstance(parterUid);

		if (role == null) {
			System.out.println("Role is null!!!!!!!!!");
			return;
		}

		String fmConfigs = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("fmConfigs");
		String[] fConfigs = fmConfigs.split(";");
//
//		WFDAO dao = new WFDAO();
//		dao.setAutoClose(false);
		try {
			for (int i = 0; i < fConfigs.length; i++) {
				String[] aConfig = fConfigs[i].split(",");
				if (aConfig[0] != null && !"".equals(aConfig[0])) {

					String dbAccess = "0";
					if (allAuthForms != null
							&& allAuthForms.contains(aConfig[0])) {
						dbAccess = "1";
					}
					if (aConfig[0] != null) {
						addAuth(aConfig[1], dbAccess, aConfig[0],  role,
								DOAuthorization.WHAT_UI_FORM, null);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		dao.closeSession();
	}

	/**
	 * 配置菜单的权限
	 * 
	 * @param parterUid
	 */
	private void menuAuth(String parterUid) {
		List allAuthMenus = SessionParterFactory.getSessionParter()
				.getMenuAuthConfigByRole(parterUid);
		DOBO boRole = OrgParter.getDefaultRole().getDoBO();
		BOInstance role = boRole.getInstance(parterUid);

		System.out.println("Enter:::::::::::::DOAuthRoleSave");

		if (role == null) {
			System.out.println("User is null!!!!!!!!!");
			return;
		}

		String authcofig = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("authcofig");
		String[] menuConfigs = authcofig.split(";");

//		WFDAO dao = new WFDAO();
//		dao.setAutoClose(false);
		try {
			for (int i = 0; i < menuConfigs.length; i++) {
				String[] aConfig = menuConfigs[i].split(",");
				if (aConfig[0] != null && !"".equals(aConfig[0])) {

					String dbAccess = "0";
					if (allAuthMenus != null
							&& allAuthMenus.contains(aConfig[0])) {
						dbAccess = "1";
					}
					if (aConfig[0] != null) {
						addAuth(aConfig[1], dbAccess, aConfig[0],  role,
								DOAuthorization.WHAT_UI_MENU, null);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		dao.closeSession();
	}

	// /////////角色不做反向赋权，只有人员才做反向赋权
	private void addAuth(String uiAccess, String dbAccess, String dmmUid,
			 BOInstance role, int type, String whereDOBO) throws ExedoException
			 {
		if (!uiAccess.equals(dbAccess)) {

			System.out
					.println("22222222222222222222222222222222222222222222222222222");
			// DOAuthorization.removeDOAuthorizations(OrgParter.getDefaultRole(),
			// role.getUid(), type, dmmUid);
			DOAuthorization.removeDOAuthorizations(OrgParter.getDefaultRole(),
					role.getUid(), type, dmmUid, whereDOBO, null);

			if ("1".equals(uiAccess)) {
				DOAuthorization da = new DOAuthorization();
				da.setParterUid(OrgParter.getDefaultRole().getObjUid());
				da.setOuUid(role.getUid());
				da.setWhatType(type);
				da.setWhatUid(dmmUid);
				da.setWhereDOBO(whereDOBO);
				da.setAuthority(Boolean.TRUE);
				da.setIsInherit(Boolean.TRUE);
				DAOUtil.BUSI().store(da);
			}
		}
	}

	public static void main(String[] args) {

		String a = "402881c01be33416011be360f8d10007cc";
		System.out.println(a.substring(32));

	}

	// // /////////角色不做反向赋权，只有人员才做反向赋权
	// private void authAForm(String uiAccess, String dbAccess, String dmmUid,
	// WFDAO dao, BOInstance role) throws DAOException {
	// if (!uiAccess.equals(dbAccess)) {
	//
	// DOAuthorization.removeDOAuthorizations(OrgParter.getDefaultRole(),
	// role.getUid(), DOAuthorization.WHAT_UI_FORM, dmmUid);
	// if ("1".equals(uiAccess)) {
	// DOAuthorization da = new DOAuthorization();
	// da.setParterUid(OrgParter.getDefaultRole().getObjUid());
	// da.setOuUid(role.getUid());
	// da.setWhatType(DOAuthorization.WHAT_UI_FORM);
	// da.setWhatUid(dmmUid);
	// da.setAuthority(Boolean.TRUE);
	// da.setIsInherit(Boolean.TRUE);
	// dao.store(da);
	// }
	// }
	// }
	//	
	//	
	// // /////////角色不做反向赋权，只有人员才做反向赋权
	// private void authABOInstance(String uiAccess, String dbAccess, String
	// dmmUid,
	// WFDAO dao, BOInstance role) throws DAOException {
	// if (!uiAccess.equals(dbAccess)) {
	//
	// DOAuthorization.removeDOAuthorizations(OrgParter.getDefaultRole(),
	// role.getUid(), DOAuthorization.WHAT_BOINSTANCE, dmmUid);
	// if ("1".equals(uiAccess)) {
	// DOAuthorization da = new DOAuthorization();
	// da.setParterUid(OrgParter.getDefaultRole().getObjUid());
	// da.setOuUid(role.getUid());
	// da.setWhatType(DOAuthorization.WHAT_BOINSTANCE);
	// da.setWhatUid(dmmUid);
	// da.setAuthority(Boolean.TRUE);
	// da.setIsInherit(Boolean.TRUE);
	// dao.store(da);
	// }
	// }
	// }

}
