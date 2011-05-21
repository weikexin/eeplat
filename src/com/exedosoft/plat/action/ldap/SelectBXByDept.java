package com.exedosoft.plat.action.ldap;

import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class SelectBXByDept extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
//		Connection conn = null;
		try {
//			conn = LDAPManager.INSTANCE.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(this.service
//					.getMainSql());
//			ResultSet rs = pstmt.executeQuery();// sql
//			SearchTransCode stc = new SearchTransCode(null);
			
			List<BOInstance> users = new ArrayList<BOInstance>();

			try {
				DOService linkser = DOService
				.getService("zf_employee_list_copy");
				users = linkser.invokeSelect();
			} catch (Exception e) {
				return this.DEFAULT_FORWARD;
			}
			
			
			List<BOInstance> list = new ArrayList<BOInstance>();

			String dept_uid = DOGlobals.getInstance().getSessoinContext()
					.getFormInstance().getValue("wdept_uid");

			List<BOInstance> linkList = new ArrayList<BOInstance>();
			if (dept_uid != null && !"".equals(dept_uid.trim())) {
				DOService linkser = DOService
						.getService("do_org_user_link_browse_dept_by_form");
				linkList = linkser.invokeSelect(dept_uid);
				List<String> listemp = new ArrayList<String>();
				if (linkList != null && linkList.size() > 0) {
					for (int n = 0; n < linkList.size(); n++) {
						BOInstance bi = linkList.get(n);
						String emp_uid = bi.getValue("user_uid");
						if (emp_uid != null)
							listemp.add(emp_uid);
					}
				}

//				while (rs.next()) {
				for(int i = 0; i < users.size(); i ++) {
//					BOInstance bi = stc.transRSToBOInstance(rs, null);
					BOInstance bi = users.get(i);
//					bi.setBo(this.service.getBo());
					String uid = bi.getValue("uid");
					if (uid != null && listemp.contains(uid.trim())) {
						if (uid != null && !uid.trim().equals("")) {
							// bi.putValue("uid", bi.getValue("sn"));
//							String sn = LDAPPeopleUtil.getLDAPSnByUid(uid);
//							String cn = LDAPPeopleUtil.getLDAPCNBySN(sn);
//							bi.putValue("cn", cn);
							list.add(bi);
						}
					}
				}
			} else {
				for(int i = 0; i < users.size(); i ++) {
//					BOInstance bi = stc.transRSToBOInstance(rs, null);
					BOInstance bi = users.get(i);
//					bi.setBo(this.service.getBo());
					String uid = bi.getValue("uid");
					if (uid != null && !uid.trim().equals("")) {
						// bi.putValue("uid", bi.getValue("sn"));
//						String sn = LDAPPeopleUtil.getLDAPSnByUid(uid);
//						String cn = LDAPPeopleUtil.getLDAPCNBySN(sn);
//						bi.putValue("cn", cn);
						list.add(bi);
					}
				}
			}

			this.setInstances(list);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} 
		return DEFAULT_FORWARD;
	}

	public static void main(String[] args) {

		DOService aService = DOService
				.getService("zf_employee_list_ldapbyaction");

		List list = aService.invokeSelect();

		BOInstance anItem = (BOInstance) list.get(5);

		System.out.println("An item::" + anItem);
		System.out.println("Name::" + anItem.getName());
		System.out.println("Uid::" + anItem.getUid());

	}

}
