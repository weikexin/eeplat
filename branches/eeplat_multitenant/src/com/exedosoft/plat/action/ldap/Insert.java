package com.exedosoft.plat.action.ldap;

import java.sql.Connection;
import java.sql.SQLException;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.ldap.LDAPManager;

public class Insert extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5883945689393365123L;

	@Override
	public String excute() throws ExedoException {
		Connection conn = null;
		try {
			conn = LDAPManager.INSTANCE.getConnection();
			String bakSql = this.service.getMainSql();
			String mainSql = this.service.getMainSql().replace("/*dn*/", this.actionForm.getValue(this.service.getBo().getValueCol()));
			this.service.setMainSql(mainSql);
			this.service.invokeUpdate(this.actionForm);
			this.service.setMainSql(bakSql);
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
