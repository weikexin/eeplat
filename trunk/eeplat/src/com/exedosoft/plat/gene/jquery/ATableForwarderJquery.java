package com.exedosoft.plat.gene.jquery;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOParameter;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.id.UUIDHex;

public class ATableForwarderJquery {

	private static Log log = LogFactory.getLog(ATableForwarderJquery.class);

	private String table;

	private String dataSourceUid;

	private String busiPackageUid;

	private String keyCol;

	private String valueCol;

	private String BOUid;

	private String tenancyId = "";

	public ATableForwarderJquery(String aDataSourceUid) {

		this.dataSourceUid = aDataSourceUid;
	}

	public ATableForwarderJquery(String aTable, String keyCol, String valueCol,
			String aDataSourceUid, String aBusiPackageUid, String tenancyId) {
		this.keyCol = keyCol.toLowerCase();
		this.valueCol = valueCol.toLowerCase();
		table = aTable;
		dataSourceUid = aDataSourceUid;
		busiPackageUid = aBusiPackageUid;
		this.tenancyId = tenancyId;
	}

	public Connection getConnection() {

		if (this.dataSourceUid != null) {

			try {
				DODataSource dds = null;
				if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {

					dds = DOGlobals.getInstance().getSessoinContext()
							.getTenancyValues().getDataDDS();
				}
				if (dds == null) {
					dds = (DODataSource) DAOUtil.INSTANCE().getByObjUid(
							DODataSource.class, this.dataSourceUid);
				}
				return dds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return DODataSource.getDefaultCon();
		}

	}

	public boolean judgeExists() {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String boUid = this.getDOBOUid(table);
			log.info("当前添加的业务对象名称:" + table);
			log.info("当前添加的业务对象ID:" + boUid);

			if (boUid == null) { // ////如果配置中没有当前view 的配置
				String judgeSql = "select * from do_bo where name = ? ";

				PreparedStatement pstmt = con.prepareStatement(judgeSql);
				pstmt.setString(1, table);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return true;
				}
			}
			BOUid = boUid;

		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return false;

	}

	public void forwardDOBO() {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String boUid = this.getDOBOUid(table);
			log.info("当前添加的业务对象名称:" + table);
			log.info("当前添加的业务对象ID:" + boUid);

			if (boUid == null) { // ////如果配置中没有当前view 的配置
				String insertTable = "insert into DO_BO(objUid,name,l10n,sqlstr,dataSourceUid,bpUID, keycol,valueCol,iscache,type)  values(?,?,?,?,?,?,'"
						+ this.keyCol
						+ "','"
						+ this.valueCol
						+ "',1,"
						+ getType() + ")";

				PreparedStatement pstmt = con.prepareStatement(insertTable);
				boUid = UUIDHex.getInstance().generate();
				pstmt.setString(1, boUid);
				pstmt.setString(2, table);
				pstmt.setString(3, table);
				pstmt.setString(4, table);
				pstmt.setString(5, dataSourceUid);
				pstmt.setString(6, busiPackageUid);
				pstmt.executeUpdate();
			}
			BOUid = boUid;

		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

	}

	public void forwardProperty() {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			Statement stmt = con.createStatement();
			String boUid = this.getDOBOUid(table);
			if (boUid == null) {
				log.warn("boUid未制定：" + table + "可能未配置");
				return;
			}
			log.info("");
			log.info("正在处理业务对象:" + table);
			log.info("正在产生业务属性添加语句。。。。。");
			int i = 1;
			for (Iterator itCol = this.getCols(table).iterator(); itCol
					.hasNext();) {
				SqlCol sqlCol = (SqlCol) itCol.next();
				if (!isModify(sqlCol.getName(), boUid)) { // ///没有被修改过的字段才可以被覆盖
					StringBuffer buffer = new StringBuffer();
					buffer
							.append(
									"insert into DO_BO_Property(objuid,boUid,col_name,prop_name,l10n,dbType,isNull,isModify,order_num,dbSize,readonly) values(")
							.append("'").append(
									UUIDHex.getInstance().generate()).append(
									"',") // //viewUID
							.append("'").append(boUid).append("',") // /colname
							.append("'").append(sqlCol.getName()).append("',") // /colname
							.append("'").append(sqlCol.getName()).append("',") // /propname
							.append("'").append(sqlCol.getName()).append("',") // /l10n
							.append(sqlCol.getDataType()).append(","); // /datatype

					if (sqlCol.isAllowedNull()) {
						buffer.append("'1'");
					} else {
						buffer.append("'0'");
					} // /////////////////////////////isNull
					buffer.append(",").append("'0'"); // ///isModify,指修改状态，现在是未修改，是由系统生成的
					buffer.append(",").append(i * 5); // ///order numbber
					buffer.append(",").append(sqlCol.getSize()); // ///readonly
					buffer.append(",").append("'0'"); // ///readonly
					buffer.append(")");
					log.info(buffer);
					stmt.addBatch(buffer.toString());
				}
				i++;
			} // ///添加每个表字段的信息
			log.info("正在执行添加操作。。。。。");
			stmt.executeBatch();
			log.info("操作完成。");
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

	}

	public void forwardParameter() {
		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			con.setAutoCommit(false);
			String seleProps = "SELECT property.* from DO_BO_Property property, DO_BO db where property.boUid = db.objuid and db.name = ?";
			log.info("");
			log.info("正在处理参数转化。。。。。");

			PreparedStatement stmt1 = con.prepareStatement(seleProps);
			stmt1.setString(1, table);

			Statement stmt2 = con.createStatement();
			ResultSet prop = stmt1.executeQuery();

			while (prop.next()) {

				if (this.getParaUidByProp(prop.getString("objuid")) == null) {

					int paraType = DOParameter.TYPE_FORM;
					insertParameterSql(prop, paraType, stmt2);

					if (prop.getString("col_name") != null) {
						if (this.keyCol.equals(prop.getString("col_name")
								.toLowerCase())) {
							paraType = DOParameter.TYPE_CURRENT;
							insertParameterSql(prop, paraType, stmt2);
							paraType = DOParameter.TYPE_KEY;
							insertParameterSql(prop, paraType, stmt2);
						}
					}
				}
			}

			prop.close();
			stmt1.close();
			stmt2.executeBatch();
			con.commit();
			log.info("操作完成。");
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

	}

	public void forwardService() {

		this.insertService(table, 1);
		this.insertService(table, 2);
		this.insertService(table, 3);
		this.insertService(table, 4);
		this.insertService(table, 5);

	}

	// public void forwardRule() {
	//
	// Connection con = null;
	// try {
	// con = DODataSource.getDefaultCon();
	// con.setAutoCommit(false);
	// String aTableServices =
	// "SELECT service.* FROM DO_Service  service ,DO_BO db where service.bouid = db.objuid and db.name = ? ";
	// log.info("");
	// log.info("正在处理参数转化。。。。。");
	//
	// PreparedStatement stmtService = con
	// .prepareStatement(aTableServices);
	// stmtService.setString(1, table));
	//
	// Statement stmtRule = con.createStatement();
	// ResultSet services = stmtService.executeQuery();
	//
	// while (services.next()) {
	//
	// int paraType = DOParameter.TYPE_FORM;
	// insertRuleSql(services, paraType, stmtRule);
	// }
	//
	// services.close();
	// stmtService.close();
	// stmtRule.executeBatch();
	// con.commit();
	// log.info("操作完成。");
	// } catch (SQLException ex) {
	// try {
	// con.rollback();
	// } catch (SQLException ex2) {
	// ex2.printStackTrace();
	// }
	// ex.printStackTrace();
	// } finally {
	// try {
	// if (!con.isClosed()) {
	// con.close();
	// }
	// } catch (SQLException ex1) {
	// ex1.printStackTrace();
	// }
	// }
	//
	// }

	/**
	 * InsertService 利用hibernate的api或用exedo本身api来写
	 * 
	 * @param table
	 * @param type
	 * 
	 */
	private void insertService(String table, int type) {

		Connection con = null;

		log.info("");
		log.info("正在生成" + table + "的服务。。。。。。。");

		try {
			con = DODataSource.getDefaultCon();// /获取缺省的数据库连接
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();// //deal service statement
			Statement stmt2 = con.createStatement();// ////////deal parameter
			// statement
			String serviceUid = UUIDHex.getInstance().generate();// ////要生成的servcie
			// uuid

			DOBO bo = DOBO.getDOBOByName(table);// ////表对应的业务对象
			List props = new ArrayList();

			StringBuffer mainSql = null;

			String name = "";
			String l10n = "";
			String prefix = table;

			String serviceType = "null";
			Boolean isNew = null;
			switch (type) {
			case 1:
				name = prefix + "_insert";
				l10n = name;
				props = bo.retrieveProperties();
				mainSql = getInsertSql(props, table);
				serviceType = "8";
				isNew = Boolean.TRUE;
				break;
			case 2:
				name = prefix + "_update";
				l10n = name;
				props = bo.retrieveProperties();
				mainSql = this.getModiSql(props, table);
				serviceType = "7";
				isNew = Boolean.FALSE;
				break;

			case 3:
				DOBOProperty property = DOBOProperty.getDOBOPropertyByName(bo
						.getName(), this.keyCol);
				if (property == null || property.getColName() == null) {
					return;
				}
				name = prefix + "_delete";
				l10n = name;
				props.add(property);
				mainSql = new StringBuffer("delete from ").append(table)
						.append(" where ").append(this.keyCol).append(" = ?");
				serviceType = "5";
				break;
			case 4:
				property = DOBOProperty.getDOBOPropertyByName(bo.getName(),
						this.keyCol);
				if (property == null || property.getColName() == null) {
					return;
				}
				name = prefix + "_browse";
				l10n = name;
				props.add(property);
				mainSql = new StringBuffer("select * from ").append(table)
						.append(" where ").append(this.keyCol).append(" = ?");
				serviceType = "10";
				break;
			case 5:
				serviceType = "2";
				name = prefix + "_list";
				l10n = name;
				mainSql = new StringBuffer("select * from ").append(table);
			}

			// ///////根据props 定位parameter ，然后service 和 parameter 进行关联
			this.setParaLinkBatch(props, stmt2, serviceUid, isNew);

			StringBuffer aSql = new StringBuffer(
					"insert into DO_Service(objuid,l10n,name,bouid,mainSql,type) values(")
					.append("'").append(serviceUid).append("','").append(l10n)
					.append("','").append(name).append("','").append(
							this.getDOBOUid(table)).append("','").append(
							mainSql).append("',").append(serviceType).append(
							")");

			log.info("Servcice's Sql:" + aSql.toString());
			stmt.executeUpdate(aSql.toString());
			stmt2.executeBatch();
			con.commit();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

	}

	private StringBuffer getInsertSql(List allProps, String table) {

		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into ").append(table).append("(");
		log.info("正在生成服务和参数关联SQL:");
		for (Iterator itCol = allProps.iterator(); itCol.hasNext();) {
			DOBOProperty property = (DOBOProperty) itCol.next();
			insertSql.append(property.getColName()); // /colname
			if (itCol.hasNext()) {
				insertSql.append(",");
			} else {
				insertSql.append(")");
			}
		}
		insertSql.append(" values(");
		for (int i = 0; i < allProps.size(); i++) {
			insertSql.append("?");
			if (i < allProps.size() - 1) {
				insertSql.append(",");
			} else {
				insertSql.append(")");
			}

		}
		return insertSql;
	}

	private StringBuffer getModiSql(List allProps, String table) {

		StringBuffer modiSql = new StringBuffer();
		modiSql.append("update ").append(table).append(" set ");
		log.info("正在生成服务和参数关联SQL:");

		List<String> list = new ArrayList<String>();

		for (Iterator itCol = allProps.iterator(); itCol.hasNext();) {
			DOBOProperty property = (DOBOProperty) itCol.next();
			if (!this.keyCol.equals(property.getColName().toLowerCase())) {
				list.add(property.getColName());
			}
		}

		boolean hasEversion = false;
		for (Iterator<String> itCol = list.iterator(); itCol.hasNext();) {

			String property = itCol.next();
			modiSql.append(property); // /colname
			if (property.equalsIgnoreCase("eversion")) {
				hasEversion = true;
				modiSql.append("=eversion+1");
			} else {
				modiSql.append("=?");
			}
			if (itCol.hasNext()) {
				modiSql.append(",");
			} else {
				modiSql.append(" where ");
				if (hasEversion) {
					modiSql.append("eversion =? and ");
				}
				modiSql.append(this.keyCol).append(" = ?");
			}
		}
		return modiSql;
	}

	private void setParaLinkBatch(List allProps, Statement stmt2,
			String serviceUid, Boolean isNew) throws SQLException {

		int order = 1;
		String modiParaUid = null;
		String eversionUid = null;
		for (Iterator itCol = allProps.iterator(); itCol.hasNext();) {
			DOBOProperty property = (DOBOProperty) itCol.next();

			if (this.keyCol.equals(property.getColName().toLowerCase())
					&& (isNew == null)) {// ///察看情况

				String paraUid = this.getParaUidByProp(property.getObjUid(),
						DOParameter.TYPE_CURRENT);
				addParaRelationStr(stmt2, serviceUid, order, paraUid);

			} else if (this.keyCol.equals(property.getColName().toLowerCase())
					&& (isNew != null) && !isNew.booleanValue()) {// /修改情况
				// /////////这句话的意思是放到最后执行
				modiParaUid = this.getParaUidByProp(property.getObjUid(),
						DOParameter.TYPE_CURRENT);
				continue;
			} else if (this.keyCol.equals(property.getColName().toLowerCase())
					&& (isNew != null) && isNew.booleanValue()) {// /新增的情况
				String paraUid = this.getParaUidByProp(property.getObjUid(),
						DOParameter.TYPE_KEY);
				addParaRelationStr(stmt2, serviceUid, order, paraUid);
			} else if (property.getColName().equalsIgnoreCase("eversion")
					&& (isNew != null) && isNew.booleanValue()) {
				eversionUid = this.getParaUidByProp(property.getObjUid(),
						DOParameter.TYPE_FORM);
				continue;
			} else {

				String paraUid = this.getParaUidByProp(property.getObjUid(),
						DOParameter.TYPE_FORM);
				addParaRelationStr(stmt2, serviceUid, order, paraUid);

			}
			order++;
		}

		if (eversionUid != null) {
			addParaRelationStr(stmt2, serviceUid, order++, eversionUid);
		}

		if (modiParaUid != null) {
			addParaRelationStr(stmt2, serviceUid, order++, modiParaUid);
		}

	}

	/**
	 * @param stmt2
	 * @param serviceUid
	 * @param order
	 * @param paraUid
	 * @throws SQLException
	 */
	private void addParaRelationStr(Statement stmt2, String serviceUid,
			int order, String paraUid) throws SQLException {
		StringBuffer paraRelationStr = new StringBuffer(
				"insert into do_parameter_service(objuid,serviceuid,parameteruid,order_num) values('")
				.append(UUIDHex.getInstance().generate()).append("','").append(
						serviceUid).append("','").append(paraUid).append("',")
				.append(order * 5).append(")");
		log.info("关联的SQL语句:" + paraRelationStr);
		stmt2.addBatch(paraRelationStr.toString());
	}

	/**
	 * @param prop
	 * @param paraType
	 * @return
	 * @throws SQLException
	 */
	private void insertParameterSql(ResultSet prop, int paraType,
			Statement stmt2) throws SQLException {

		String colName = prop.getString("col_name");
		if (paraType == DOParameter.TYPE_CURRENT) {
			colName = this.table + "_currunt";

		}

		StringBuffer aSql = new StringBuffer(
				"insert into do_parameter(objuid,name,l10n,type,paraBoUid,paraPropertyUid) values(")
				.append("'").append(UUIDHex.getInstance().generate()).append(
						"'").append(",'").append(colName).append("'").append(
						",'").append(colName).append("',").append(paraType)
				.append(",'").append(prop.getString("bouid")).append("'")
				.append(",'").append(prop.getString("objuid")).append("')");
		log.info("添加参数的sql语句:" + aSql);
		stmt2.addBatch(aSql.toString());
	}

	/**
	 * @param prop
	 * @param paraType
	 * @return
	 * @throws SQLException
	 */
	private void insertRuleSql(ResultSet prop, int paraType, Statement stmt2)
			throws SQLException {
		// ?,?,?,?,?,?,?,?,?)9

		StringBuffer aSql = new StringBuffer(
				"insert into do_rule(objuid,name,l10n,condition,consequencescript,salience,serviceuid,onlyrun,bpuid) values(")
				.append("'").append(UUIDHex.getInstance().generate()).append(
						"'").append(",'").append("Rule_").append(
						prop.getString("name")).append("'").append(",'")
				.append("Rule_").append(prop.getString("name")).append("'")
				.append(",'true'").append(",null").append(",null").append(",'")
				.append(prop.getString("objuid")).append("'").append(",1")
				.append(",'").append(this.BOUid).append("')");
		log.info("添加参数的sql语句:" + aSql);
		stmt2.addBatch(aSql.toString());
	}

	/**
	 * 通过sql查询语句，返回viewName对应的objUID
	 * 
	 * @param boName
	 */
	public String getDOBOUid(String boName) {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String sql = "select objUID from DO_BO where type = '" + getType()
					+ "' and sqlStr = ? ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boName);
			ResultSet rs = pstmt.executeQuery();
			String boUid = null;
			if (rs.next()) {
				boUid = rs.getString(1);
			}
			return boUid;
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return null;
	}

	private String getType() {
		String type = "1";
		if (this.tenancyId.length() > 0) {
			type = "2";
		}
		return type;
	}

	protected boolean isModify(String colName, String boUid) {

		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String sql = "select objUID from DO_BO_Property where col_name = ? and bouid = ? and isModify = '1' ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, colName);
			pstmt.setString(2, boUid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// //处于修改状态，系统初始化时把该字段忽略。
				return true;
			} else {
				sql = "delete from DO_BO_Property where col_name = ? and bouid = ?";
				PreparedStatement pstmtDete = con.prepareStatement(sql);
				pstmtDete.setString(1, colName);
				pstmtDete.setString(2, boUid);
				pstmtDete.executeUpdate();
				return false;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return false;
	}

	public String getParaUidByProp(String propertyUid, int type) {

		String ret = null;
		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String sql = "select * from do_parameter  where ParaPropertyUid = ? and type = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, propertyUid);
			pstmt.setInt(2, type);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// //找得到记录
				ret = rs.getString("objUid");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return ret;
	}

	public String getParaUidByProp(String propertyUid) {

		String ret = null;
		Connection con = null;
		try {
			con = DODataSource.getDefaultCon();
			String sql = "select * from do_parameter  where ParaPropertyUid = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, propertyUid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// //找得到记录
				ret = rs.getString("objUid");
			}
			rs.close();
			pstmt.close();
		} catch (SQLException ex) {
			try {
				con.rollback();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		return ret;
	}

	public Collection getCols(String aTable) {

		
		String schema = null;
		if (this.dataSourceUid != null) {

			try {
				DODataSource dds = null;
				if ("true".equals(DOGlobals.getValue("multi.tenancy"))) {
					
					dds = DOGlobals.getInstance().getSessoinContext()
							.getTenancyValues().getDataDDS();
				}
				if (dds == null) {
					dds = (DODataSource) DAOUtil.INSTANCE().getByObjUid(
							DODataSource.class, this.dataSourceUid);
				}
				if (dds.isOracle() || dds.isDB2()
						|| "h2".equals(dds.getDialect())) {

					aTable = aTable.toUpperCase();
				}

				if (dds.isOracle()) {
					schema = dds.getUserName().trim().toUpperCase();
				}


			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		if (this.tenancyId.length() > 0) {
			aTable = this.tenancyId + "_" + aTable;
		}

		Collection cc = new ArrayList();
		Connection con = null;
		try {
			con = this.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			ResultSet rs = meta.getColumns(null, schema, aTable, null);
			while (rs.next()) {
				SqlCol qc = new SqlCol();
				qc.setName(rs.getString("COLUMN_NAME").toLowerCase());
				qc.setDataType(rs.getInt("DATA_TYPE"));
				qc.setScale(rs.getInt("DECIMAL_DIGITS"));
				qc.setNullable(rs.getString("IS_NULLABLE"));
				qc.setSize(rs.getInt("COLUMN_SIZE"));
				cc.add(qc);
			}
			log.info("All Cols::" + cc);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}

		}
		return cc;
	}

	public void forwardUI() {

		DOBO bo = DOBO.getDOBOByName("DO_BO");
		bo.refreshContext(this.BOUid);
		GeneUICompByTableJquery gt = new GeneUICompByTableJquery(this.table,
				this.BOUid);
		gt.geneConfig();

		GeneUIMain gm = new GeneUIMain(this.table);
		gm.geneConfig();
	}

	public void forwardAll() {

		if (this.judgeExists()) {
			return;
		}

		this.forwardDOBO();
		this.forwardProperty();
		this.forwardParameter();
		this.forwardService();
		// this.forwardRule();
		this.forwardUI();

	}

	public static void main(String[] args) {

		// String[] aa = {"-database.0","mydb","-dbname.0","mydb"};
		// org.hsqldb.Server.main(aa);

		// URL url = ATableForwarderImp.class.getResource("abc.xml");
		// String aPath = url.getPath();
		// System.out.println(aPath);
		// String aSubPath = aPath.substring(0,aPath.indexOf("abc.xml"));
		// System.out.println(aSubPath);
		// /url.getPath()

	}
}
