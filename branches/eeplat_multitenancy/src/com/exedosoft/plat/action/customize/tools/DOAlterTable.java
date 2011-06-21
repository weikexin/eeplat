package com.exedosoft.plat.action.customize.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.CacheFactory;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.action.DOAbstractAction;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
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

	private static Map<String, String> mapCommTypes = new HashMap<String, String>();
	private static Map<String, String> mapTenancyTypes = new HashMap<String, String>();
	private static Log log = LogFactory.getLog(DOAlterTable.class);

	static {
		String sqlTenancy = "12,VARCHAR32;12,VARCHAR64;12,VARCHAR128;12,VARCHAR255;12,VARCHAR1000;1,CHAR1;4,INT;-5,BIGINT;6,FLOAT;3,DECIMAL2;3,DECIMAL1;3,DECIMAL3;91,DATE;91,DATETIME;93,TIMESTAMP;2005,CLOB;";
		String sqlType = "-7,BIT;-6,TINYINT;5,SMALLINT;4,INTEGER;-5,BIGINT;6,FLOAT;7,REAL;8,DOUBLE;2,NUMERIC;3,DECIMAL;1,CHAR;12,VARCHAR;-1,LONGVARCHAR;91,DATE;92,TIME;93,TIMESTAMP;-2,BINARY;-3,VARBINARY;-4,LONGVARBINARY;1111,OTHER;2004,BLOB;2005,CLOB;16,BOOLEAN;1001,DATETIME;1002,VARCHAR2;1003,TEXT";

		String[] types = sqlType.split(";");
		for (int i = 0; i < types.length; i++) {
			String aType = types[i];
			String[] split = aType.split(",");
			mapCommTypes.put(split[1].toLowerCase(), split[0]);
		}

		types = sqlTenancy.split(";");
		for (int i = 0; i < types.length; i++) {
			String aType = types[i];
			String[] split = aType.split(",");
			mapTenancyTypes.put(split[1].toLowerCase(), split[0]);
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

		// ///////////////////////////////////////多租户

		DOBO dobo = DOBO.getDOBOByName("do_bo");

		BOInstance biTenancy = (BOInstance) DOGlobals.getInstance()
				.getSessoinContext().getUser().getObjectValue("tenancy");

		BOInstance biTenancyTable = null;

		if (biTenancy != null && dobo.getCorrInstance() != null
				&& "2".equals(dobo.getCorrInstance().getValue("type"))) {
			DOBO theBO = DOBO.getDOBOByID(dobo.getCorrInstance().getUid());
			DOService findTable = DOService
					.getService("multi_tenancy_table_findrealtable");
			biTenancyTable = findTable.getInstance(theBO.getSqlStr(), biTenancy
					.getValue("name"));

		}

		// ////////////////初始化每种类型

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
		// //////////初始化
		// /////////////////////////////////////多租户

		Transaction t = dss.getTransaction();
		t.begin();

		// 所有的业务对象下面的属性
		List list = selected.retrieveProperties();
		List propCols = new ArrayList();

		// ////////////多租户
		if (biTenancy != null && biTenancyTable != null) {

			dbtypes = form.getValueArray("type");

			DOService findTenacyCols = DOService
					.getService("multi_tenancy_column_findbytableid");

			List tenancyCols = findTenacyCols.invokeSelect(biTenancyTable
					.getUid());
			for (Iterator it = tenancyCols.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				if (!bi.getValue("col_name").equals("id")) {
					propCols.add(bi.getUid());
				}

			}
		} else {

			// ///////////////非多租户
			for (Iterator it = list.iterator(); it.hasNext();) {
				DOBOProperty dop = (DOBOProperty) it.next();
				if (dop.getIsPersistence() == null
						|| dop.getIsPersistence().intValue() == DOBOProperty.PERSISTENCE_YES) {
					propCols.add(dop.getObjUid());
				}
			}
		}

		List removeCols = new ArrayList(propCols);

		// //去掉所有没有移除的（非多租户下）
		removeCols.removeAll(listHiddenKeys);
		System.out.println("确定所有待删除的字段::" + removeCols);

		// //需要删除的字段propCols

		System.out.println("RemoveCols::" + removeCols);

		DOService removeCol = DOService
				.getService("multi_tenancy_column_delete");

		DOService getCol = DOService.getService("multi_tenancy_column_browse");

		try {
			for (int i = 0; i < removeCols.size(); i++) {
				
				String colObjuid = (String) removeCols.get(i);

				DOBOProperty dop = DOBOProperty.getDOBOPropertyByID(colObjuid);
				// /多租户
				if (dop == null && (biTenancy != null)) {
					BOInstance bi = getCol.getInstance(colObjuid);
					dop = DOBOProperty.getDOBOPropertyByName(
							selected.getName(), bi.getValue("col_name"));
				}

				if (biTenancy != null && biTenancyTable != null) {
					removeCol.invokeUpdate(colObjuid);
				} else {
					StringBuffer sb = new StringBuffer("alter table ");
					sb.append(selected.getSqlStr()).append(" drop column ")
							.append(dop.getColName());

					listSql.add(sb.toString());
				}

				pm.removeProperty(selected, dop);
			}
		} catch (ExedoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ///////////////放到删除之后
		// ////////////////////确定每种类型最大值
		if (biTenancy != null && dobo.getCorrInstance() != null
				&& "2".equals(dobo.getCorrInstance().getValue("type"))) {

			DOService findTenacyCols = DOService
					.getService("multi_tenancy_column_findbytableid");

			List tenancyCols = findTenacyCols.invokeSelect(biTenancyTable
					.getUid());

			for (Iterator it = tenancyCols.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();
				String type = bi.getValue("type").toLowerCase();
				String realCol = bi.getValue("real_col");
				if ("id".equals(realCol)) {
					continue;
				}
				int order = Integer.parseInt(realCol.substring(1));

				if (type.equals("char1")) {
					if (order >= char1) {
						char1 = order;
						char1++;
					}
				} else if (type.equals("varchar32")) {
					if (order >= varchar32) {
						varchar32 = order;
						varchar32++;
					}
				} else if (type.equals("varchar64")) {
					if (order >= varchar64) {
						varchar64 = order;
						varchar64++;
					}
				} else if (type.equals("varchar128")) {
					if (order >= varchar128) {
						varchar128 = order;
						varchar128++;
					}
				} else if (type.equals("varchar255")) {
					if (order >= varchar255) {
						varchar255 = order;
						varchar255++;
					}
				} else if (type.equals("varchar1000")) {
					if (order >= varchar1000) {
						varchar1000 = order;
						varchar1000++;
					}
				} else if (type.equals("date")) {
					if (order >= date) {
						date = order;
						date++;
					}
				} else if (type.equals("datetime")) {
					if (order >= datetime) {
						datetime = order;
						datetime++;
					}
				} else if (type.equals("timestamp")) {
					if (order >= timestamp) {
						timestamp = order;
						timestamp++;
					}
				} else if (type.equals("int")) {
					if (order >= intn) {
						intn = order;
						intn++;
					}
				} else if (type.equals("bigint")) {
					if (order >= bigint) {
						bigint = order;
						bigint++;
					}
				} else if (type.equals("float")) {
					if (order >= floatn) {
						floatn = order;
						floatn++;
					}
				} else if (type.equals("decimal2")) {
					if (order >= decimal2) {
						decimal2 = order;
						decimal2++;
					}
				} else if (type.equals("decimal1")) {
					if (order >= decimal1) {
						decimal1 = order;
						decimal1++;
					}
				} else if (type.equals("decimal3")) {
					if (order >= decimal3) {
						decimal3 = order;
						decimal3++;
					}
				} else if (type.equals("clob")) {
					if (order >= clob) {
						clob = order;
						clob++;
					}
				}

			}

		}
		// /////////////////////确定每种类型的最大值

		// 确定新增的

		DOService insertCol = DOService
				.getService("multi_tenancy_column_insert");
		Map mapTypes = new HashMap();
		Map mapCols = new HashMap();

		for (int len = 0; len < key_hiddens.length; len++) {

			String keyHidden = key_hiddens[len];
			
			if("id".equals(keyHidden)){
				continue;
			}

			// //keyHidden 为 null 后者空，即为新增
			if (keyHidden == null || keyHidden.equals("")) {
				DOBOProperty dopExists = DOBOProperty.getDOBOPropertyByName(
						selected.getName(), colNames[len]);
				// //再判断一次是否存在
				if (dopExists == null || dopExists.getColName() == null) {
					if (colNames[len] != null && !colNames[len].equals("")) {
						// multi_tenancy_column_insert
						if (biTenancy != null && biTenancyTable != null) {
							int i = len;
							Map paras = new HashMap();
							String colName = colNames[len];
							mapTypes.put(colName, dbtypes[i]);
							log.info("types[i]::" + dbtypes[len]);

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

							} else if (dbtypes[i]
									.equalsIgnoreCase("varchar128")) {
								mapCols.put(colName, "c0" + varchar128);
								if (varchar128 > 60) {
									this.setEchoValue("最多只支持20个varchar128字段");
									return NO_FORWARD;
								}
								varchar128++;
							} else if (dbtypes[i]
									.equalsIgnoreCase("varchar255")) {
								if (varchar255 < 100) {
									mapCols.put(colName, "c0" + varchar255);
								} else if (varchar255 == 100) {
									mapCols.put(colName, "c100");
								} else if (varchar255 > 100) {
									this.setEchoValue("最多只支持40个varchar255字段");
									return NO_FORWARD;
								}
								varchar255++;
							} else if (dbtypes[i]
									.equalsIgnoreCase("varchar1000")) {
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
						} else {

							StringBuffer sb = new StringBuffer("alter table ");
							sb.append(selected.getSqlStr()).append(" add  ")
									.append(colNames[len]).append(" ").append(
											dbtypes[len]);

							if (dbsizes[len] != null
									&& !dbsizes[len].equals("")) {
								sb.append(" (").append(dbsizes[len])
										.append(")");
							}
							listSql.add(sb.toString());

							System.out.println("ColName:" + colNames[len]
									+ "  DBSize:" + dbsizes[len]);
						}

						// ///////////执行新增
						int iType = 12;
						int dbsize = 63355;
						if(dbsizes!=null && dbsizes.length > 0){
							dbsize = Integer
							.parseInt(dbsizes[len]);
						}else{
							if (dbtypes[len].startsWith("VARCHAR") && dbtypes[len].length() > 7){
								dbsize = Integer
								.parseInt( dbtypes[len].substring(7));								
							}
						}
						try {
							if (mapCommTypes.get(dbtypes[len].toLowerCase()) != null) {
								iType = Integer.parseInt(mapCommTypes
										.get(dbtypes[len].toLowerCase()));
							} else

							if (mapTenancyTypes.get(dbtypes[len].toLowerCase()) != null) {
								iType = Integer.parseInt(mapTenancyTypes
										.get(dbtypes[len].toLowerCase()));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pm.addProperty(selected, colNames[len], iType, dbsize);
					}
				}
			} else {
				System.out.println("已经存在ColName::"
						+ colNames[len]);
			}

		}
		// //修改作为：先删除后新增

		// ////////////正式新增

		if (biTenancy != null && biTenancyTable != null) {

			DOService multi_col_insert = DOService
					.getService("multi_tenancy_column_insert");
			Transaction t2 = multi_col_insert.getBo().getDataBase()
					.getTransaction();
			t2.begin();
			try {
				for (Iterator<Map.Entry<String, String>> it = mapCols
						.entrySet().iterator(); it.hasNext();) {
					HashMap paras = new HashMap();
					Map.Entry<String, String> e = it.next();
					paras.put("col_name", e.getKey());
					paras.put("tenancy_table_uid", biTenancyTable.getUid());
					paras.put("type", mapTypes.get(e.getKey()));
					paras.put("real_col", e.getValue());
					multi_col_insert.invokeUpdate(paras);
				}
			} catch (ExedoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				t2.rollback();
			}

			t2.end();

			StringBuffer sb = new StringBuffer("alter view  ");
			sb.append(biTenancyTable.getValue("corr_view")).append(
					" as select ");



			DOService findTenacyCols = DOService
					.getService("multi_tenancy_column_findbytableid");

			List tenancyCols = findTenacyCols.invokeSelect(biTenancyTable
					.getUid());
			int i = 0;
			for (Iterator it = tenancyCols.iterator(); it.hasNext();) {
				BOInstance bi = (BOInstance) it.next();

				sb.append(bi.getValue("real_col")).append(" as ").append(
						bi.getValue("col_name"));

				if (i < (tenancyCols.size() - 1)) {
					sb.append(", ");
				}
				i++;
			}

			sb.append("  from  t001 where tenancyId='").append(
					biTenancy.getValue("name"))
					.append("' and tenancyTableId='").append(
							biTenancyTable.getValue("table_name")).append("'");
			log.info(" the View::::" + sb);

			// ///更新另外一个库

			Connection con = dss.getConnection();

			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(sb.toString());
				pstmt.executeUpdate();
				con.commit();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {

				try {
					if (con != null && !con.isClosed()) {
						con.close();
					}
				} catch (SQLException ex1) {
					ex1.printStackTrace();
				}
			}

		}
		// ////////////正式新增

		Connection con = null;
		try {
			con = dss.getConnection();
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();

			for (Iterator<String> it = listSql.iterator(); it.hasNext();) {
				String aSql = it.next();
				stmt.execute(aSql);
			}
			con.commit();
			stmt.close();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		CacheFactory.getCacheRelation().getData().remove(
				(new StringBuilder(String.valueOf(selected.getObjUid())))
						.append("_property").toString());
		this.setEchoValue("保存成功!");
		return DEFAULT_FORWARD;
	}

	// // //////// 每种类型的 多少
	// public int findTypeMax(String dbType, String tenancyTableId) {
	//
	// if (dbType == null) {
	// return 0;
	// }
	//
	// dbType = dbType.toLowerCase();
	// int char1 = 1;
	// int varchar32 = 11;
	// int varchar64 = 21;
	// int varchar128 = 41;
	// int varchar255 = 61;
	// int varchar1000 = 101;// max 106
	// int date = 501; // max 510
	// int datetime = 551;// max 560
	// int timestamp = 591;// max 592
	// int intn = 701;// max 720
	// int bigint = 721; // max 730
	// int floatn = 771; // max 775
	// int decimal2 = 801;// max 810
	// int decimal1 = 851; // max 855
	// int decimal3 = 871; // max 875
	// int clob = 901; // max 910
	//
	// DOService findTenacyCols = DOService
	// .getService("multi_tenancy_column_findbytableid");
	//
	// List tenancyCols = findTenacyCols.invokeSelect(tenancyTableId);
	//
	// for (Iterator it = tenancyCols.iterator(); it.hasNext();) {
	// BOInstance bi = (BOInstance) it.next();
	// String type = bi.getValue("type");
	// String realCol = bi.getValue("real_col");
	// if ("id".equals(realCol)) {
	// continue;
	// }
	// int order = Integer.parseInt(realCol.substring(1));
	//
	// if (type.equals("char1")) {
	// if (order > char1) {
	// char1 = order;
	// }
	// } else if (type.equals("varchar32")) {
	// if (order > varchar32) {
	// varchar32 = order;
	// }
	// } else if (type.equals("varchar64")) {
	// if (order > varchar64) {
	// varchar64 = order;
	// }
	// } else if (type.equals("varchar128")) {
	// if (order > varchar128) {
	// varchar128 = order;
	// }
	// } else if (type.equals("varchar255")) {
	// if (order > varchar255) {
	// varchar255 = order;
	// }
	// } else if (type.equals("varchar1000")) {
	// if (order > varchar1000) {
	// varchar1000 = order;
	// }
	// } else if (type.equals("date")) {
	// if (order > date) {
	// date = order;
	// }
	// } else if (type.equals("datetime")) {
	// if (order > datetime) {
	// datetime = order;
	// }
	// } else if (type.equals("timestamp")) {
	// if (order > timestamp) {
	// timestamp = order;
	// }
	// } else if (type.equals("int")) {
	// if (order > intn) {
	// intn = order;
	// }
	// } else if (type.equals("bigint")) {
	// if (order > bigint) {
	// bigint = order;
	// }
	// } else if (type.equals("float")) {
	// if (order > floatn) {
	// floatn = order;
	// }
	// } else if (type.equals("decimal2")) {
	// if (order > decimal2) {
	// decimal2 = order;
	// }
	// } else if (type.equals("decimal1")) {
	// if (order > decimal1) {
	// decimal1 = order;
	// }
	// } else if (type.equals("decimal3")) {
	// if (order > decimal3) {
	// decimal3 = order;
	// }
	// } else if (type.equals("clob")) {
	// if (order > clob) {
	// clob = order;
	// }
	// }
	//
	// }
	//
	// Map map = new HashMap();
	// map.put("char1", char1);
	// map.put("varchar32", varchar32);
	// map.put("varchar64", varchar64);
	// map.put("varchar128", varchar128);
	// map.put("varchar255", varchar255);
	// map.put("varchar1000", varchar1000);
	// map.put("date", date);
	// map.put("datetime", datetime);
	// map.put("timestamp", timestamp);
	// map.put("int", intn);
	// map.put("bigint", bigint);
	// map.put("float", floatn);
	// map.put("decimal2", decimal2);
	// map.put("decimal1", decimal1);
	// map.put("decimal3", decimal3);
	// map.put("clob", clob);
	//
	// return 0;
	//
	// }

	public static void main(String[] args) {

		System.out.println(Integer.parseInt("c0001"));

	}

}
