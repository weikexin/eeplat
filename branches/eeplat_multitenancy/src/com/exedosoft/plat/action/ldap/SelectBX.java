package com.exedosoft.plat.action.ldap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.ldap.LDAPManager;
import com.exedosoft.plat.login.zidingyi.excel.LDAPPeopleUtil;
import com.exedosoft.plat.search.customize.SearchTransCode;

public class SelectBX extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		Connection conn = null;
		try {
			conn = LDAPManager.INSTANCE.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(this.service
					.getMainSql());
			ResultSet rs = pstmt.executeQuery();// sql
			SearchTransCode stc = new SearchTransCode(null);
			List<BOInstance> list = new ArrayList<BOInstance>();
			while (rs.next()) {
				BOInstance bi = stc.transRSToBOInstance(rs, null);
				bi.setBo(this.service.getBo());
				String sn = rs.getString("sn");
				String tcn = rs.getString("cn");
				if (sn != null && !sn.trim().equals("")) {
				//	bi.putValue("uid", bi.getValue("sn"));
					String cn = LDAPPeopleUtil.getLDAPCNBySN(sn);
					System.out.println(tcn + "<=======================>" + cn);
					bi.putValue("cn", cn);
					list.add(bi);
				}
			}
			this.setInstances(list);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return DEFAULT_FORWARD;
	}
	
	public static void main(String[] args){
		
		DOService aService = DOService.getService("zf_employee_list_ldapbyaction");
		
		List list = aService.invokeSelect();
		
		BOInstance anItem = (BOInstance)list.get(5);
		
		
		System.out.println("An item::" + anItem);
		System.out.println("Name::" + anItem.getName());
		System.out.println("Uid::" + anItem.getUid());
		
	}

}
