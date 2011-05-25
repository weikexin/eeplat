package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;

/**
 * 
 * 
 * 做增加，不做修改和删除
 * 
 * 可以对增加做扫描
 * 
 * @author anolesoft
 * 
 */

public class DOCreateTable extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 232432342L;
	private static Log log = LogFactory.getLog(DOCreateTable.class);

	public String excute() {

		BOInstance form = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();
		String tenancyUid = DOGlobals.getInstance().getSessoinContext()
		.getUser().getValue("tenancy_uid");
		
		DOService service = DOService
		.getService("multi_tenancy_browse");
		BOInstance theTenancy = service
				.getInstance(tenancyUid);

		String tableName = form.getValue("tableName");
		if(theTenancy!=null){
			tableName = theTenancy.getValue("name") + "_" + tableName;
		}
		String[] colNames = form.getValueArray("col_name");
		String[] colL10ns = form.getValueArray("col_l10n");
		String[] dbtypes = form.getValueArray("dbtype");

		DOService multi_table_insert = DOService
				.getService("multi_tenancy_table_insert");

		Transaction t = multi_table_insert.getBo().getDataBase()
				.getTransaction();

		DOService multi_col_insert = DOService
				.getService("multi_tenancy_column_insert");

		Map<String, String> mapCols = new HashMap<String, String>();
		Map<String, String> mapL10ns = new HashMap<String, String>();
		Map<String, String> mapTypes = new HashMap<String, String>();

		int char1 = 1;
		int varchar32 = 11;
		int varchar64 = 21;
		int varchar128 = 41;
		int varchar255 = 61;
		int varchar1000 = 101;// max 106
		int date = 501; // max 510
		int datetime = 551;// max 560
		int timestamp = 591;// max 592
		int intn = 701;// max 720
		int bigint = 721; // max 730
		int floatn = 771; // max 775
		int decimal2 = 801;// max 810
		int decimal1 = 851; // max 855
		int decimal3 = 871; // max 875
		int clob = 901; // max 910

		mapCols.put("id", "id");
		mapTypes.put("id", "varchar32");

		for (int i = 0; i < colNames.length; i++) {

			String colName = colNames[i];

			if (!colName.equals("")) {
				// 建立字段名和本地化翻译的对应
				mapL10ns.put(colName, colL10ns[i]);
				mapTypes.put(colName, dbtypes[i]);
				log.info("dbtypes[i]::" + dbtypes[i]);

				// //建立字段名和实际字段名之间的对应
				if (dbtypes[i].equalsIgnoreCase("char1")) {
					if (char1 < 10) {
						mapCols.put(colName, "c00" + char1);
					} else if (char1 == 10) {
						mapCols.put(colName, "c010");
					} else {
						this.setEchoValue("最多只支持10个char1字段");
						return NO_FORWARD;
					}
					char1++;
				} else if (dbtypes[i].equalsIgnoreCase("varchar32")) {
					mapCols.put(colName, "c0" + varchar32);
					if (varchar32 > 20) {
						this.setEchoValue("最多只支持10个varchar32字段");
						return NO_FORWARD;
					}
					varchar32++;
				} else if (dbtypes[i].equalsIgnoreCase("varchar64")) {
					mapCols.put(colName, "c0" + varchar64);
					if (varchar64 > 40) {
						this.setEchoValue("最多只支持20个varchar64字段");
						return NO_FORWARD;
					}
					varchar64++;

				} else if (dbtypes[i].equalsIgnoreCase("varchar128")) {
					mapCols.put(colName, "c0" + varchar128);
					if (varchar128 > 60) {
						this.setEchoValue("最多只支持20个varchar128字段");
						return NO_FORWARD;
					}
					varchar128++;
				} else if (dbtypes[i].equalsIgnoreCase("varchar255")) {
					if (varchar255 < 100) {
						mapCols.put(colName, "c0" + varchar255);
					} else if (varchar255 == 100) {
						mapCols.put(colName, "c100");
					} else if (varchar255 > 100) {
						this.setEchoValue("最多只支持40个varchar255字段");
						return NO_FORWARD;
					}
					varchar255++;
				} else if (dbtypes[i].equalsIgnoreCase("varchar1000")) {
					mapCols.put(colName, "c" + varchar1000);
					if (varchar1000 > 106) {
						this.setEchoValue("最多只支持6个varchar1000字段");
						return NO_FORWARD;
					}
					varchar1000++;
				} else if (dbtypes[i].equalsIgnoreCase("date")) {
					mapCols.put(colName, "c" + date);
					if (date > 510) {
						this.setEchoValue("最多只支持10个date字段");
						return NO_FORWARD;
					}
					date++;
				} else if (dbtypes[i].equalsIgnoreCase("datetime")) {
					mapCols.put(colName, "c" + datetime);
					if (datetime > 560) {
						this.setEchoValue("最多只支持10个datetime字段");
						return NO_FORWARD;
					}
					datetime++;
				} else if (dbtypes[i].equalsIgnoreCase("timestamp")) {
					mapCols.put(colName, "c" + timestamp);
					if (timestamp > 592) {
						this.setEchoValue("最多只支持2个timestamp字段");
						return NO_FORWARD;
					}
					timestamp++;
				} else if (dbtypes[i].equalsIgnoreCase("int")) {
					mapCols.put(colName, "c" + intn);
					if (intn > 720) {
						this.setEchoValue("最多只支持20个int字段");
						return NO_FORWARD;
					}
					intn++;
				} else if (dbtypes[i].equalsIgnoreCase("bigint")) {
					mapCols.put(colName, "c" + bigint);
					if (bigint > 730) {
						this.setEchoValue("最多只支持10个bigint字段");
						return NO_FORWARD;
					}
					bigint++;
				} else if (dbtypes[i].equalsIgnoreCase("float")) {
					mapCols.put(colName, "c" + floatn);
					if (floatn > 775) {
						this.setEchoValue("最多只支持5个float字段");
						return NO_FORWARD;
					}
					floatn++;
				} else if (dbtypes[i].equalsIgnoreCase("decimal1")) {
					mapCols.put(colName, "c" + decimal1);
					if (decimal1 > 855) {
						this.setEchoValue("最多只支持5个decimal1字段");
						return NO_FORWARD;
					}
					decimal1++;
				} else if (dbtypes[i].equalsIgnoreCase("decimal2")) {
					mapCols.put(colName, "c" + decimal2);
					if (decimal2 > 810) {
						this.setEchoValue("最多只支持10个decimal2字段");
						return NO_FORWARD;
					}
					decimal2++;
				} else if (dbtypes[i].equalsIgnoreCase("decimal3")) {
					mapCols.put(colName, "c" + decimal3);
					if (decimal3 > 875) {
						this.setEchoValue("最多只支持5个decimal3字段");
						return NO_FORWARD;
					}
					decimal3++;

				} else if (dbtypes[i].equalsIgnoreCase("clob")) {
					mapCols.put(colName, "c" + clob);
					if (clob > 910) {
						this.setEchoValue("最多只支持10个clob字段");
						return NO_FORWARD;
					}
					clob++;
				}
			}
		}// /end mapping

		// 事务开始
		t.begin();

		Map<String, String> paras = new HashMap<String, String>();
		try {
			paras.put("table_name", tableName);
			paras.put("corr_view", tableName);
			paras.put("real_table", "t001");
			BOInstance tBI = multi_table_insert.invokeUpdate(paras);

			for (Iterator<Map.Entry<String, String>> it = mapCols.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> e = it.next();
				paras.put("col_name", e.getKey());
				paras.put("tenancy_table_uid", tBI.getUid());
				paras.put("type", mapTypes.get(e.getKey()));
				paras.put("real_col", e.getValue());
				multi_col_insert.invokeUpdate(paras);
			}

			StringBuffer sb = new StringBuffer("create view  ");
			sb.append(tableName).append(" as select ");

			int i = 0;
			for (Iterator<Map.Entry<String, String>> it = mapCols.entrySet()
					.iterator(); it.hasNext();) {
				Map.Entry<String, String> e = it.next();

				sb.append(e.getValue()).append(" as ").append(e.getKey());

				if (i < (mapCols.size() - 1)) {
					sb.append(", ");
				}
				i++;
			}

			sb.append("  from  t001 where tenancyId='").append(tenancyUid)
					.append("' and tenancyTableId='").append(tableName).append(
							"'");
			log.info(" the View::::" + sb);

			// ///更新另外一个库
			DOBO bo = DOBO.getDOBOByName("do_datasource");
			DODataSource dss = DODataSource.getDataSourceByL10n(bo
					.getCorrInstance().getValue("l10n"));
			Connection con = dss.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sb.toString());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();

		} catch (Exception ex) {
			this.setEchoValue(ex.getMessage());
			t.rollback();
			return NO_FORWARD;
		} finally {
			t.end();
		}
		// TODO Auto-generated method stub
		this.setEchoValue("建表成功!");
		return DEFAULT_FORWARD;
	}

}
