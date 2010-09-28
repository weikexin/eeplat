package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.gene.jquery.PropertyManager;
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

public class DOAlterTable extends DOAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, String> map = new HashMap();
	static {
		String sqlType = "-7,BIT;-6,TINYINT;5,SMALLINT;4,INTEGER;-5,BIGINT;6,FLOAT;7,REAL;8,DOUBLE;2,NUMERIC;3,DECIMAL;1,CHAR;12,VARCHAR;-1,LONGVARCHAR;91,DATE;92,TIME;93,TIMESTAMP;-2,BINARY;-3,VARBINARY;-4,LONGVARBINARY;1111,OTHER;2004,BLOB;2005,CLOB;16,BOOLEAN;1001,DATETIME;1002,VARCHAR2;1003,TEXT";
		String[] types = sqlType.split(";");
		for (int i = 0; i < types.length; i++) {
			String aType = types[i];
			String[] split = aType.split(",");
			map.put(split[1].toLowerCase(), split[0]);
		}
	}

	public String excute() {

		BOInstance form = DOGlobals.getInstance().getSessoinContext()
				.getFormInstance();

		String[] keys = form.getValueArray("checkinstance");
		String[] key_hiddens = form.getValueArray("checkinstance_hidden");
		String[] dbtypes = form.getValueArray("dbtype");
		String[] dbsizes = form.getValueArray("dbsize");
		String[] colNames = form.getValueArray("col_name");

		PropertyManager pm = new PropertyManager();

		List listSql = new ArrayList();

		List listKeys = new ArrayList();

		if (keys != null && keys.length > 0) {
			listKeys = Arrays.asList(keys);
		}
		List listHiddenKeys = Arrays.asList(key_hiddens);

		DOBO bo = DOBO.getDOBOByName("do_bo");
		DOBO selected = DOBO.getDOBOByID(bo.getCorrInstance().getUid());

		DODataSource dss = selected.getDataBase();
		Transaction t = dss.getTransaction();
		t.begin();

		// /先确定删除的
		List list = selected.retrieveProperties();
		List propCols = new ArrayList();

		for (Iterator it = list.iterator(); it.hasNext();) {
			DOBOProperty dop = (DOBOProperty) it.next();
			if (dop.getIsPersistence() == null
					|| dop.getIsPersistence().intValue() == DOBOProperty.PERSISTENCE_YES) {
				propCols.add(dop.getObjUid());
			}
		}
		List removeCols = new ArrayList(propCols);

		System.out.println("AllCols::" + removeCols);

		removeCols.removeAll(listHiddenKeys);
		// //需要删除的字段propCols

		System.out.println("RemoveCols::" + removeCols);

		for (int i = 0; i < removeCols.size(); i++) {
			String colObjuid = (String) removeCols.get(i);
			DOBOProperty dop = DOBOProperty.getDOBOPropertyByID(colObjuid);

			StringBuffer sb = new StringBuffer("alter table ");
			sb.append(selected.getSqlStr()).append(" drop column ").append(
					dop.getColName());

			listSql.add(sb.toString());

			pm.removeProperty(selected, dop);
		}

		// 先确定新增的

		for (int len = 0; len < key_hiddens.length; len++) {

			String keyHidden = key_hiddens[len];
			if (keyHidden == null || keyHidden.equals("")) {
				if (colNames[len] != null && !colNames[len].equals("")) {
					DOBOProperty dopExists =  DOBOProperty.getDOBOPropertyByName(selected.getName(),
							colNames[len]);
					if (dopExists==null || dopExists.getColName()==null) {

						StringBuffer sb = new StringBuffer("alter table ");
						sb.append(selected.getSqlStr()).append(" add  ")
								.append(colNames[len]).append(" ").append(
										dbtypes[len]);

						if (dbsizes[len] != null && !dbsizes[len].equals("")) {
							sb.append(" (").append(dbsizes[len]).append(")");
						}
						listSql.add(sb.toString());

						int iType = 12;
						try {
							iType = Integer.parseInt(map.get(dbtypes[len]
									.toLowerCase()));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("ColName:" + colNames[len]
								+ "  DBSize:" + dbsizes[len]);
						pm.addProperty(selected, colNames[len], iType, Integer
								.parseInt(dbsizes[len]));
						

					}else{
						System.out.println("已经存在ColName::" + DOBOProperty.getDOBOPropertyByName(selected.getName(),
								colNames[len]));
					}
				}
			}
		}

		// //确定修改的

		Connection con = null;
		try {
			con = dss.getConnection();
			Statement stmt = con.createStatement();

			for (Iterator<String> it = listSql.iterator(); it.hasNext();) {
				String aSql = it.next();
				stmt.execute(aSql);
			}
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			this.setEchoValue(ex.getMessage());
			return NO_FORWARD;

		} finally {

			t.end();
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		CacheFactory.getCacheRelation().getData().remove((new StringBuilder(String.valueOf(selected.getObjUid()))).append("_property").toString());
		this.setEchoValue("保存成功!");
		return DEFAULT_FORWARD;
	}

	public void tt() {

	}

	public static void main(String[] args) {

		System.out.println(DOBOProperty.getDOBOPropertyByName("DO_BO",				"cc")==null);

	}

}
