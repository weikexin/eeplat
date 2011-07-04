package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.gene.ATableForwarderImp;
import com.exedosoft.plat.gene.jquery.SqlCol;

public class DOGeneMultiTenancyDefaultTable extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 558266835833057020L;

	private static Log log = LogFactory
			.getLog(DOGeneMultiTenancyDefaultTable.class);
	
	private static DODataSource dss = DODataSource.getDataSourceByID("mysqlexample");
	@Override
	public String excute() throws ExedoException {
		// TODO Auto-generated method stub

		return null;

	}

	public boolean createMultiTenancyTable(String tenant, String aTableName) {


		ATableForwarderImp ai = new ATableForwarderImp(null);
		Collection<SqlCol> cc = ai.getCols(aTableName);
		StringBuffer buffer = new StringBuffer("create view ").append(tenant)
				.append("_").append(aTableName).append("  as select ");

		for (Iterator<SqlCol> it = cc.iterator(); it.hasNext();) {
			SqlCol sc = it.next();
			/**
			 * 表do_org_user毕竟特殊，需要知道其所属的租户ID
			 */
			if (!aTableName.equals("do_org_user")
					&& sc.getName().equalsIgnoreCase("tenancyId")) {
				continue;
			}
			buffer.append(sc.getName());
			buffer.append(",");
		}
		/**
		 * 删除多余的","
		 */
		buffer.deleteCharAt(buffer.length() - 1);

		buffer.append(" from ").append(aTableName).append(" where tenancyId='")
				.append(tenant).append("'");

		log.info("create view sql::" + buffer);

		Connection con = dss.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(buffer.toString());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean createMultiTennacyTableOfWf(String tenant) {

		StringBuffer wfBj = new StringBuffer(
				"CREATE VIEW #TENANT#_wf_bj AS select distinct wpi.WFI_Desc AS wfi_desc,wpi.startUser AS")
				.append(
						" startuser,wpi.startTime AS starttime,ni.OBJUID AS contextNiUid,wpi.OBJUID AS ")

				.append(
						" contextPIUid,wpi.curState AS curState,wpi.instance_uid AS instance_uid,ni.performerUid AS ")

				.append(
						" USER_UID,ni.nodeDate AS nodeDate from (do_wfi_his_nodeinstance ni join  ")

				.append(
						" do_wfi_his_processinstance wpi on((ni.PI_UID = wpi.OBJUID))) where ((ni.ExeStatus = 3) and  ")

				.append(
						" (wpi.ExeStatus = 3)   and ni.tenancyid='#TENANT#' and wpi.tenancyid='#TENANT#') ");
		
		

		StringBuffer wfDb = new StringBuffer(
				" CREATE VIEW #TENANT#_wf_db AS select distinct wpi.curState AS curstate,ni.node_uid AS   ")

				.append(
						" node_uid,ni.nodeDate AS nodeDate,ni.OBJUID AS contextNIUid,wpi.OBJUID AS   ")

				.append(
						" contextPIUid,wpi.instance_uid AS instance_uid,ni.pass_txt AS pass_txt,ni.reject_txt AS   ")

				.append(
						" reject_txt,ur.USER_UID AS user_uid,wpi.WFI_Desc AS WFI_Desc,wpi.startUser AS   ")

				.append(
						" startUser,wpi.startTime AS startTime from (((do_wfi_nodeinstance ni join   ")

				.append(
						" do_wfi_processinstance wpi) join do_org_user_role ur) join do_authorization a) where   ")

				.append(
						" ((wpi.OBJUID = ni.PI_UID) and (a.parterUid = _utf8'9') and (a.ouUid = ur.ROLE_UID) and   ")

				.append(
						" (ni.node_uid = a.whatUid) and (ni.ExeStatus = 2) and (wpi.ExeStatus = 2)  and   ")

				.append(
						" ni.tenancyid='#TENANT#' and wpi.tenancyid='#TENANT#'  and a.tenancyid='#TENANT#')   ");

		StringBuffer wfYb = new StringBuffer(
				" CREATE VIEW #TENANT#_wf_yb AS select distinct wpi.WFI_Desc AS wfi_desc,wpi.startUser AS ")

				.append(
						" startuser,wpi.startTime AS starttime,ni.OBJUID AS contextNIUid,wpi.OBJUID AS   ")

				.append(
						" contextPIUid,wpi.curState AS curState,wpi.instance_uid AS instance_uid,ni.performerUid AS   ")

				.append(
						" USER_UID,ni.nodeDate AS nodeDate from (do_wfi_nodeinstance ni join do_wfi_processinstance   ")

				.append(
						" wpi on((ni.PI_UID = wpi.OBJUID))) where ((ni.ExeStatus = 3) and (wpi.ExeStatus = 2)  ")
				.append(
						" and ni.tenancyid='#TENANT#' and wpi.tenancyid='#TENANT#')  ");
		
		Connection con = dss.getConnection();
		try {
			Statement pstmt = con.createStatement();
			pstmt.execute(wfBj.toString().replace("#TENANT#", tenant));
			pstmt.execute(wfDb.toString().replace("#TENANT#", tenant));
			pstmt.execute(wfYb.toString().replace("#TENANT#", tenant));
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) {

		DOGeneMultiTenancyDefaultTable gtd = new DOGeneMultiTenancyDefaultTable();
		gtd.createMultiTenancyTable("wmt", "do_authorization");
		gtd.createMultiTenancyTable("wmt", "do_code_maxsequence");
		gtd.createMultiTenancyTable("wmt", "do_log");
		gtd.createMultiTenancyTable("wmt", "do_log_data");
		gtd.createMultiTenancyTable("wmt", "do_org_dept");
		gtd.createMultiTenancyTable("wmt", "do_org_role");
		gtd.createMultiTenancyTable("wmt", "do_org_user");
		gtd.createMultiTenancyTable("wmt", "do_org_user_delegate");
		gtd.createMultiTenancyTable("wmt", "do_org_user_role");
		gtd.createMultiTenancyTable("wmt", "do_wfi_his_ni_dependency");
		gtd.createMultiTenancyTable("wmt", "do_wfi_his_nodeinstance");
		gtd.createMultiTenancyTable("wmt", "do_wfi_his_processinstance");
		gtd.createMultiTenancyTable("wmt", "do_wfi_his_varinstance");
		gtd.createMultiTenancyTable("wmt", "do_wfi_ni_dependency");
		gtd.createMultiTenancyTable("wmt", "do_wfi_nodeinstance");
		gtd.createMultiTenancyTable("wmt", "do_wfi_processinstance");
		gtd.createMultiTenancyTable("wmt", "do_wfi_varinstance");
		
		gtd.createMultiTennacyTableOfWf("wmt");
		
//		String a = "#TENANT#---------#TENANT#fdsf";
//		System.out.println(a.replace("#TENANT#", "?"));
	}

}
