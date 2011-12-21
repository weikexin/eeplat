package com.exedosoft.plat.action.ldap;


import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

public class SelectByDeptAndNameCon extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		
		try {
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
			String cnCon = DOGlobals.getInstance().getSessoinContext()
					.getFormInstance().getValue("cn");

			// 查找已有的组员
			DOService haveEmpser = DOService
					.getService("emp_crew_browse_ifhave_zuyuan");
			List<BOInstance> empList = haveEmpser.invokeSelect();
			List<String> listemp_uid = new ArrayList<String>();
			for (int i = 0; i < empList.size(); i++) {
				BOInstance empbi = empList.get(i);
				String emp_uid = empbi.getValue("emp_uid");
				if (emp_uid != null && !"".equals(emp_uid.trim())) {
					listemp_uid.add(emp_uid);
				}
			}

			// 刚进入页面时，查询本部门的所有员工
			if (dept_uid == null) {
				DOService linkser = DOService
						.getService("do_org_user_link_browse_dept");
				List<BOInstance> linkList = linkser.invokeSelect();
				List<String> listemp = new ArrayList<String>();
				if (linkList != null && linkList.size() > 0) {
					for (int n = 0; n < linkList.size(); n++) {
						BOInstance bi = linkList.get(n);
						String emp_uid = bi.getValue("user_uid");
						if (emp_uid != null)
							listemp.add(emp_uid);
					}
					for(int i = 0; i < users.size(); i ++) {
//						BOInstance bi = stc.transRSToBOInstance(rs, null);
						BOInstance bi = users.get(i);
//						bi.setBo(this.service.getBo());
						String uid = bi.getValue("uid");
						if (cnCon != null && !cnCon.trim().equals("")) {
							if (uid != null && listemp.contains(uid.trim())) {
								if (!listemp_uid.contains(uid.trim())
										&& uid.equals(cnCon)) {
									
									list.add(bi);
								}
							}
						} else {
							if (uid != null && listemp.contains(uid.trim())) {
								if (!listemp_uid.contains(uid.trim())) {
									// bi.putValue("uid", bi.getValue("sn"));
									
									list.add(bi);
								}
							}
						}

					}
				}

			}
			// 部门为空时，查询所有员工
			else if (dept_uid != null && "".equals(dept_uid.trim())) {
				for(int i = 0; i < users.size(); i ++) {
//					BOInstance bi = stc.transRSToBOInstance(rs, null);
					BOInstance bi = users.get(i);
//					bi.setBo(this.service.getBo());
					String uid = bi.getValue("uid");
					if (cnCon != null && !cnCon.trim().equals("")) {
						if (!listemp_uid.contains(uid.trim()) && uid != null
								&& !uid.trim().equals("") && uid.equals(cnCon)) {
							
							list.add(bi);
						}
					} else {
						if (!listemp_uid.contains(uid.trim()) && uid != null
								&& !uid.trim().equals("")) {
							
							list.add(bi);
						}
					}
				}

				// 部门不为空时，查询部门的所有员工
			} else {

				DOService linkser = DOService
						.getService("do_org_user_link_browse_dept_by_form");
				List<BOInstance> linkList = linkser.invokeSelect(dept_uid);
				List<String> listemp = new ArrayList<String>();
				if (linkList != null && linkList.size() > 0) {
					for (int n = 0; n < linkList.size(); n++) {
						BOInstance bi = linkList.get(n);
						String emp_uid = bi.getValue("user_uid");
						if (emp_uid != null)
							listemp.add(emp_uid);
					}
					for(int i = 0; i < users.size(); i ++) {
//						BOInstance bi = stc.transRSToBOInstance(rs, null);
						BOInstance bi = users.get(i);
//						bi.setBo(this.service.getBo());
						String uid = bi.getValue("uid");
						if (cnCon != null && !cnCon.trim().equals("")) {
							if (uid != null && listemp.contains(uid.trim())) {
								if (!listemp_uid.contains(uid.trim())
										&& uid.equals(cnCon)) {
									
									list.add(bi);
								}
							}
						} else {
							if (uid != null && listemp.contains(uid.trim())) {
								if (!listemp_uid.contains(uid.trim())) {
									
									list.add(bi);
								}
							}
						}

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
