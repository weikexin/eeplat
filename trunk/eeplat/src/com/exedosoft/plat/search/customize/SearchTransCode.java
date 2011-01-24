package com.exedosoft.plat.search.customize;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.search.SearchImp;
import com.exedosoft.plat.util.StringUtil;

/**
 * 
 * DOGlobals.xml config plat.search.class
 * 
 * @author IBM
 */
public class SearchTransCode extends SearchImp {

	public SearchTransCode(DOService aService) {
		super(aService);
	}

	private static final long serialVersionUID = -1340442332432499139L;

	private static Log log = LogFactory.getLog(SearchTransCode.class);

	/**
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */

	@Override
	public BOInstance transRSToBOInstance(ResultSet rs, DODataSource ds)
			throws SQLException {

		BOInstance instance = new BOInstance();

		// //////查询基本没有耗费cpu时间
		// System.out.println("Search Befor getMetaData Time:::::" +
		// System.currentTimeMillis());

		ResultSetMetaData rsMeta = rs.getMetaData();

		for (int col = 1; col <= rsMeta.getColumnCount(); col++) {

			String metaName = rsMeta.getColumnName(col).toLowerCase().trim();

			String tableName = rsMeta.getTableName(col);

			DOBOProperty property = DOBOProperty.getDOBOPropertyByName(
					tableName, metaName);
			if (property == null) {
				property = new DOBOProperty();
				property.setDbType(rsMeta.getColumnType(col));
			}

			instance.putMetaProperty(metaName, property);

			if (rsMeta.getColumnType(col) == Types.BLOB
					|| rsMeta.getColumnType(col) == Types.LONGVARBINARY) {
				log.info("The BLOB COLUMN::::::::::::::" + metaName);
				continue;
			}
			
			// /DOBOProperty 获取定义的DOBOProperty

			Object oValue = rs.getObject(col);

			try {
				if (oValue != null) {
					if (property.isInt()) {

						try {
							instance.putValue(metaName, new Integer(rs
									.getInt(col)));
						} catch (Exception e) {
							instance.putValue(metaName, rs
									.getString(col));
						}
					} else if (property.isLong()) {

						try {
							instance.putValue(metaName, new Long(rs
									.getLong(col)));
						} catch (Exception e) {
							instance.putValue(metaName, rs
									.getString(col));
						}
					} else if (property.isDouble()) {
						double dd = rs.getDouble(col);
						instance.putValue(metaName, new Double(dd));
					} else if (property.isDateType()) {
						instance.putValue(metaName, rs.getDate(col));
					} else if (property.isTimestampType()) {
						instance.putValue(metaName, rs.getTimestamp(col));
					} else if (property.isString() || property.isClobType()) {
						// try {
						if (rs.getString(col) != null) {
							if (ds != null && ds.getL10n().equals("---")) {

								instance.putValue(metaName, StringUtil
										.encodeFromISO2UTF(rs
												.getString(metaName)));
							} else {

								instance.putValue(metaName, oValue);
							}
						}
					} else {
						instance.putValue(metaName, oValue);

					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("PUTValue_ERROR:::" + metaName + "___"
						+ e.getMessage());
			}
		}
		return instance;
	}

}
