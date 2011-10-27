package com.exedosoft.plat.action;

import java.util.List;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.org.DOAuthorization;
import com.exedosoft.plat.bo.org.OrgParter;
import com.exedosoft.plat.bo.org.SessionParterFactory; //import com.exedosoft.plat.dao.DAOException;
//import com.exedosoft.plat.dao.WFDAO;
import com.exedosoft.plat.ui.DOMenuModel;
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

	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		String parterUid = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue("parterUid");
		if (parterUid == null) {
			return null;
		}
		String boNames = DOGlobals.getInstance().getSessoinContext()
		.getFormInstance().getValue("boNames");
		if(boNames!=null){
			String[] arraybns = boNames.split(",");
			for(int i = 0 ; i < arraybns.length ; i++){
				String aBNS = arraybns[i];
				boInstanceAuth(parterUid, aBNS);
			}
		}
		formAuth(parterUid);

		menuAuth(parterUid);

		return DEFAULT_FORWARD;
	}

	/**
	 * 配置基础数据的权限，xes 如服务中心，年级 ，科目
	 * 
	 * @param parterUid
	 */
	private void boInstanceAuth(String parterUid, String boName) {

		List allAuthBIs = SessionParterFactory.getSessionParter()
				.getBIAuthConfigByRole(parterUid);
		System.out.println("allAuthBIs:::" + allAuthBIs);

		DOBO authBO = DOBO.getDOBOByName(boName);
		baseAuth(parterUid, allAuthBIs, boName,
				DOAuthorization.WHAT_BOINSTANCE,authBO.getObjUid());

	}

	/**
	 * 配置按钮的权限 ,导出到excel?
	 * 
	 * @param parterUid
	 */

	private void formAuth(String parterUid) {

		List allAuthForms = SessionParterFactory.getSessionParter()
				.getFormAuthConfigByRole(parterUid);
		System.out.println("allAuthForms:::" + allAuthForms);

		baseAuth(parterUid, allAuthForms, "fmAuth",
				DOAuthorization.WHAT_UI_FORM,null);

	}

	/**
	 * 配置菜单的权限
	 * 
	 * @param parterUid
	 */
	private void menuAuth(String parterUid) {
		List allAuthMenus = SessionParterFactory.getSessionParter()
				.getMenuAuthConfigByRole(parterUid);
		
		System.out.println("allAuthMenus:::" + allAuthMenus);

		baseAuth(parterUid, allAuthMenus, "menuAuth",
				DOAuthorization.WHAT_UI_MENU,null);

	}

	private void baseAuth(String parterUid, List allAuthMenus, String formStrKey,
			int type,String whereDOBO) {

		DOBO boRole = OrgParter.getDefaultRole().getDoBO();
		BOInstance role = boRole.getInstance(parterUid);

		if (role == null) {
			System.out.println("User is null!!!!!!!!!");
			return;
		}

		String authcofig = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance().getValue(formStrKey);
		String[] menuConfigs = authcofig.split(";");
		
		System.out.println("Key::" + formStrKey + "; menuConfigs::" + menuConfigs);

		// WFDAO dao = new WFDAO();
		// dao.setAutoClose(false);
		try {
			for (int i = 0; i < menuConfigs.length; i++) {
				String[] aConfig = menuConfigs[i].split(",");
				if (aConfig[0] != null && !"".equals(aConfig[0])) {
					
					if(DOAuthorization.WHAT_UI_MENU==type){
						DOMenuModel dmm = DOMenuModel.getMenuModelByID(aConfig[0]);
						if(dmm!=null && !dmm.isFilter()){
							continue;
						}
					}
					
					String containKey = aConfig[0];
					if(DOAuthorization.WHAT_BOINSTANCE==type){
						containKey = whereDOBO +  aConfig[0];
					}

					if (allAuthMenus != null
							&& allAuthMenus.contains(containKey)) {
						if (aConfig[1].equals("0")) {// ///移除权限
							removeAuth(aConfig[0], role, type, whereDOBO);
						}
					} else if (aConfig[1].equals("1")) {
						addAuth(aConfig[0], role, type, whereDOBO);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// /////////增加角色权限
	private void addAuth(String whatUid, BOInstance role, int type,
			String whereDOBO) throws ExedoException {

		DOAuthorization da = new DOAuthorization();
		da.setParterUid(OrgParter.getDefaultRole().getObjUid());
		da.setOuUid(role.getUid());
		da.setWhatType(type);
		da.setWhatUid(whatUid);
		da.setWhereDOBO(whereDOBO);
		da.setAuthority(Boolean.TRUE);
		da.setIsInherit(Boolean.TRUE);
		DAOUtil.BUSI().store(da);
	}

	// /////////删除角色权限
	private void removeAuth(String whatUid, BOInstance role, int type,
			String whereDOBO) {

		DOAuthorization.removeDOAuthorizations(OrgParter.getDefaultRole(), role
				.getUid(), type, whatUid, whereDOBO, null);

	}

	public static void main(String[] args) {

		String a = "402881c01be33416011be360f8d10007cc";
		System.out.println(a.substring(32));

	}

}
