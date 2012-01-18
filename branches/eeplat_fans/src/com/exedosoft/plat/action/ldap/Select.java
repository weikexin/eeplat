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
import com.exedosoft.plat.ldap.LDAPManager;
import com.exedosoft.plat.search.customize.SearchTransCode;

public class Select  extends DOAbstractAction {

	@Override
	public String excute() throws ExedoException {
		Connection conn = null;
		try {
			conn = LDAPManager.INSTANCE.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(this.service.getMainSql());
			ResultSet rs = pstmt.executeQuery();//sql
			SearchTransCode stc = new SearchTransCode(null);
			List<BOInstance> list = new ArrayList<BOInstance>();
			while (rs.next()) {
				BOInstance  bi = stc.transRSToBOInstance(rs, null);
				bi.setUid(bi.getValue("uid"));
				list.add(bi);
			}
			this.setInstances(list);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			if(conn!=null){
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

}
