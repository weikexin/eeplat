/**
 * 
 */
package com.zephyr.erpscan;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.exception.PropertyNotFoundException;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.framework.util.IConstants;
import com.zephyr.erpscan.object.PicObject;
import java.sql.PreparedStatement;


/**
 * @author t
 *
 */
public class ManagePicServiceImpl extends ErpScanServiceImpl {
	
	private Logger log = Logger.getLogger(ManagePicServiceImpl.class);

	/**
	 * 
	 */
	public ManagePicServiceImpl() throws DatastoreException{
		super();
		
	}
	
	public boolean useOriginalPic(String serialno) throws DatastoreException, PropertyNotFoundException{
		
		// TODO
		//search duplicate pic path
		String sql = "select i.id, i.serialno, i.scandate, i.clerk, i.depno, i.status,i.flag, i.type, i.form_number ,i.path, i.backuppath from xxxcmbc_erpscan.imageinfo i where serialno='"+serialno+"'" +
				" and flag="+IConstants.PIC_STATE_DUPLICATE;

		log.debug("use original: " + sql);
		
		String prefix = Global.getPicPathPrefix();
		
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			
			ResultSet rs = stm.executeQuery(sql);

			while(rs.next()){
				String path = rs.getString("path");
				
				//delete duplicate pics
				File f = new File(prefix+path);
				if(f.isFile()){
					f.delete();
				}
				
				//add sql to batch
				stm.addBatch("delete from xxxcmbc_erpscan.imageinfo where path='"+path+"'");
			}
			
			stm.executeBatch();
			
			return true;
			
		} catch (SQLException ex) {

			throw DatastoreException.datastoreError(ex);
		} finally {
			Global.releaseCon(stm, dbcon);
		}
		//return false;
	}
	
	public boolean useNewPic(String serialno) throws DatastoreException{
		
		Connection dbcon = null;
		
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			stm = dbcon.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE, 
					ResultSet.CONCUR_UPDATABLE
					);
			
			stm.getConnection().setAutoCommit(false);
			
			String sql = "select status,form_number from xxxcmbc_erpscan.imageinfo where serialno='"+serialno+"' and flag=0";
			
			System.out.println("sql is  "+sql);
			//change state of pics from normal to already backup 
			String sqlBackup = "update xxxcmbc_erpscan.imageinfo set flag="+IConstants.PIC_STATE_ALREADY_BACKUP
						+" where serialno='"+serialno+"' and flag="+IConstants.PIC_STATE_NORMAL;
			// change state from duplicate to normal
			String sqlUseNew = "update xxxcmbc_erpscan.imageinfo set flag="+IConstants.PIC_STATE_NORMAL
						+" where serialno='"+serialno+"' and flag="+IConstants.PIC_STATE_DUPLICATE;
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()){
				int statues = rs.getInt(1);
				if(statues == 2 || statues == 5){
					String form_number = rs.getString(2);
					if(form_number==null){
						sqlUseNew = "update xxxcmbc_erpscan.imageinfo set status=5,flag="+IConstants.PIC_STATE_NORMAL
						+",form_number='' where serialno='"+serialno+"' and flag="+IConstants.PIC_STATE_DUPLICATE;
					}else{
						sqlUseNew = "update xxxcmbc_erpscan.imageinfo set status=5,flag="+IConstants.PIC_STATE_NORMAL
						+",form_number='"+form_number+"' where serialno='"+serialno+"' and flag="+IConstants.PIC_STATE_DUPLICATE;
					}
				}
			}
			
			stm.getConnection().commit();
			//两句sql有先后顺序
			stm.addBatch(sqlBackup);
			stm.addBatch(sqlUseNew);
			stm.executeBatch();
			/*
			boolean ok = stm.execute(sqlBackup);
			if(ok){
				stm = dbcon.createStatement();
				ok = stm.execute(sqlUseNew);
				if(!ok)
					return false;
			}
			else
				return false;*/
			
			return true;
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
			throw DatastoreException.datastoreError(ex);
		} 
		finally {
			Global.releaseCon(stm, dbcon);
		}
	}


	public boolean changeInvoicePicStatus(String serialno, String status, String form_number) throws DatastoreException {
		// 
		
		StringBuffer sqlStr = new StringBuffer("update xxxcmbc_erpscan.imageinfo set ");
		
		if(status != null){
			sqlStr.append(" status='");
			sqlStr.append(status);
			sqlStr.append("'");
			
		}
		
		if(form_number != null){
			if(sqlStr.indexOf("'") != -1)
				sqlStr.append(",");
			sqlStr.append(" form_number='");
			sqlStr.append(form_number);
			sqlStr.append("'");
		}
		
		sqlStr.append(" where serialno='"+serialno+"'");
	//	sqlStr.append(" and type=" + type);
		
		/*String sql = "update imageinfo set status=" + status
					+ " where serialno='"+serialno+"'"
					+ " and type=" + IConstants.PIC_TYPE_INVOICE;*/
		
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			log.info("update sql:"+sqlStr.toString());
			stm.executeUpdate(sqlStr.toString());
			
			return true;
		} 
		catch (SQLException ex) {
			log.error("change pic status failure");
			ex.printStackTrace();
			
			throw DatastoreException.datastoreError(ex);
			
		} 
		finally {
			Global.releaseCon(stm, dbcon);
		}
		
		
	}

	/**
	 * 
	  * Method Name:addContentInvoicePicStatus
	  * Note：增加金额，摘要
	  * @param serialno
	  * @param status
	  * @param form_number
	  * @return
	  * @throws DatastoreException
	  * Date：Nov 9, 2009, 11:55:43 AM
	  * @Author:liBing
	 */
	public boolean changeContentInvoicePicStatus(String serialno, String status, String form_number,String sumoney, String formAbstract) throws DatastoreException {
// 
		
		StringBuffer sqlStr = new StringBuffer("update xxxcmbc_erpscan.imageinfo set ");
		//金额
		if(sumoney!=null && !sumoney.equals("")){
			sqlStr.append(" FORM_SUMONEY='");
			sqlStr.append(sumoney);
			sqlStr.append("'");
		}
		//摘要
		if(formAbstract!=null && !formAbstract.equals("")){
			if(sqlStr.indexOf("'") != -1){
				sqlStr.append(",");
			}
			sqlStr.append(" FORM_ABSTRACT='");
			sqlStr.append(formAbstract);
			sqlStr.append("'");
		}
		if(status != null && !status.equals("")){
			if(sqlStr.indexOf("'") != -1){
				sqlStr.append(",");
			}
			sqlStr.append(" status='");
			sqlStr.append(status);
			sqlStr.append("'");
			
		}
		
		if(form_number != null && !form_number.equals("")){
			if(sqlStr.indexOf("'") != -1)
				sqlStr.append(",");
			sqlStr.append(" form_number='");
			sqlStr.append(form_number);
			sqlStr.append("'");
		}
		
		sqlStr.append(" where serialno='"+serialno+"'");
	//	sqlStr.append(" and type=" + type);
		
		/*String sql = "update imageinfo set status=" + status
					+ " where serialno='"+serialno+"'"
					+ " and type=" + IConstants.PIC_TYPE_INVOICE;*/
		
		Connection dbcon = null;
		Statement stm = null;
		
		try {
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			log.info("update sql:"+sqlStr.toString());
			stm.executeUpdate(sqlStr.toString());
			
			return true;
		} 
		catch (SQLException ex) {
			log.error("change pic status failure");
			ex.printStackTrace();
			
			throw DatastoreException.datastoreError(ex);
			
		} 
		finally {
			Global.releaseCon(stm, dbcon);
		}
		
		
	}
	
}
