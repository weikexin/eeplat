/**
 * 
 */
package com.zephyr.erpscan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.framework.util.IConstants;
import com.zephyr.erpscan.object.PicObject;
import com.zephyr.erpscan.struts.searchPic.SearchPicForm;

/**
 * 
 * Class Name：SearchPicServiceImpl.java
 * Note：查询各种票据的数据库操作类
 * Date: Jan 13, 2010,8:34:51 PM
 * @Author:liBing
 */
public class SearchPicServiceImpl extends ErpScanServiceImpl {

	private Logger log = Logger.getLogger(SearchPicServiceImpl.class);
	
	private final String leftjoin = " LEFT JOIN (" +
								"SELECT ap.invoice_num, ap.expense_status_code, " +
								"VL.LOOKUP_CODE, VL.DESCRIPTION " +
								"FROM ap.ap_expense_report_headers_all ap " +
								"LEFT JOIN apps.FND_LOOKUP_VALUES VL " +
								"ON ap.expense_status_code=vl.LOOKUP_CODE " +
								"and vl.lookup_type='EXPENSE REPORT STATUS' and VL.LANGUAGE='ZHS') v " +
								"ON i.serialno=v.invoice_num ";
	
	/**
	 * constructor
	 */
	public SearchPicServiceImpl() {
		super();
		
	}
	
	
	/**
	 * 通过serialno查询一笔单据的信息列表，没有部门限制，不区分AP和IE
	 * search by serialno
	 * @param serialno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getDistPicbySerialno(String serialno) throws DatastoreException{
		//PicObject pic = new PicObject();
		
		String where = " where serialno='"+serialno+"' ";
		log.debug("getPicbySerialno: "+where);
		
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	
	
	/**
	 * 通过serialno和depno查询一笔单据的信息列表，有部门限制，不区分AP和IE
	 * 
	 * @param serialno
	 * @param depno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getDistPicbySerialno(String serialno, String depno) throws DatastoreException{
		//PicObject pic = new PicObject();
		String where = null;
		if(depno.equals("66")){
			where = " where serialno='"+serialno+"' and depno like '"+depno+"%'";
		}else{
			where = " where serialno='"+serialno+"' and depno='"+depno+"'";
		}
		
		log.debug("getPicbySerialno: "+where);
		
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	
	
	/**
	 * 通过serialno得到一笔单据的多张图片，用于显示，不区分AP和IE
	 * search by serialno
	 * @param serialno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getPicbySerialno(String serialno) throws DatastoreException{
		//PicObject pic = new PicObject();
		
		String where = " where serialno='"+serialno+"'";
		log.debug("getPicbySerialno: "+where);
		
		return this.getNormalPics(where, " order by path ");
	}
	
	
	/**
	 * 
	 * @param scanDate
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getIEPicsbyScanDate(String scanDate) throws DatastoreException{
		
		String where = " where scandate='"+scanDate+"'" + " and type=" + IConstants.PIC_TYPE_EXPENSE;
		
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	
	
	/**
	 * 
	 * @param scanDate
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getAPPicsbyScanDate(String scanDate) throws DatastoreException{
		
		//String where = " where scandate='"+scanDate+"'"  + " and type=" + IConstants.PIC_TYPE_INVOICE;
		String where = " where scandate='"+scanDate+"'"  + " and type in(" + IConstants.PIC_TYPE_INVOICE+","+IConstants.INVOICE_TYPE_ADVANCE+","+IConstants.INVOICE_TYPE_CANCEL+","+IConstants.INVOICE_TYPE_NATURAL+")";
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	

	/**
	 * 
	 * @param scandate
	 * @param depno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getIEPicsbyScanDate(String scandate, String depno) throws DatastoreException {
		String where = null;
		if(depno.equals("66")){
			where = " where scandate='"+scandate+"'" +
			" and depno like '"+depno+"%'" + " and type=" + IConstants.PIC_TYPE_EXPENSE;
		}else{
			where = " where scandate='"+scandate+"'" +
			" and depno='"+depno+"'" + " and type=" + IConstants.PIC_TYPE_EXPENSE;
		}
		
		
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	
	
	/**
	 * 
	 * @param scandate
	 * @param depno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getAPPicsbyScanDate(String scandate, String depno) throws DatastoreException {
		String where = null;
		if(depno.equals("66")){
			//where = " where scandate='"+scandate+"'" +
			//" and depno like '"+depno+"%'" + " and type=" + IConstants.PIC_TYPE_INVOICE;
			where = " where scandate='"+scandate+"'" +
			" and depno like '"+depno+"%'" + " and type in(" + IConstants.PIC_TYPE_INVOICE+","+IConstants.INVOICE_TYPE_ADVANCE+","+IConstants.INVOICE_TYPE_CANCEL+","+IConstants.INVOICE_TYPE_NATURAL+")";
		}else{
			/*where = " where scandate='"+scandate+"'" +
			" and depno='"+depno+"'" + " and type=" + IConstants.PIC_TYPE_INVOICE;*/
			where = " where scandate='"+scandate+"'" +
			" and depno='"+depno+"'" + " and type in(" + IConstants.PIC_TYPE_INVOICE+","+IConstants.INVOICE_TYPE_ADVANCE+","+IConstants.INVOICE_TYPE_CANCEL+","+IConstants.INVOICE_TYPE_NATURAL+")";
		}
	    
		
		return this.getDistinctNormalPics(where, " order by serialno ");
	}
	
	
	/**
	 * 通过serialno查询重复上传的一笔单据，没有单据类型限制，显示多张图片
	 * @param serialno
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getDuplicatePicbySerialno(String serialno) throws DatastoreException{
		//PicObject pic = new PicObject();
		
		String where = " where serialno='"+serialno+"'";
		log.debug("getPicbySerialno: "+where);
		
		return this.getPics(where, " order by path ", IConstants.PIC_STATE_DUPLICATE);
	}
	
	
	/**
	 * 通过depno查询上传重复的单据信息列表，没有单据类型限制，不显示图片
	 * @param depno
	 * @return pic vector
	 * @throws DatastoreException 
	 */
	public Vector getDuplicatePicsbyDep(String depno) throws DatastoreException {
		
		String where = " where 1=1";//" where depno='"+depno+"'" ;
		
		if(!depno.equals("all")){
			if(depno.equals("66")){
				where = " where depno like '"+depno+"%'" ;
			}else{
				where = " where depno='"+depno+"'" ;
			}
			
		}
		
		return this.getDistinctDuplicatePics(where, " order by serialno ");
	}
	
	
	/**
	 * 通过serialno查询上传重复的单据信息列表，没有部门和单据类型限制，不显示图片
	 * @param depno
	 * @return pic vector
	 * @throws DatastoreException 
	 */
	public Vector getDuplicatePicsbySerialno(String serialno) throws DatastoreException {
		
		String where = " where serialno='"+serialno+"'" ;
		
		return this.getDistinctDuplicatePics(where, " order by serialno ");
	}


	/**
	 * 通过depno和ERP系统中单据的状态代码查询
	 * @param scandate
	 * @param status_code
	 * @return
	 */
	public Vector getPicsbyStatusCode(String depno, String status_code) throws DatastoreException {
		String where = null;
		if(where.equals("66")){
			where = " where depno like '"+depno+"%' and v.expense_status_code='"+status_code+"'";
		}else{
			where = " where depno='"+depno+"' and v.expense_status_code='"+status_code+"'";
		}
		
		return this.getDistinctNormalPics(where, " order by serialno");
	}
	
	
	
	/**
	 * 
	 * @param whereStr
	 * @param orderStr
	 * @return
	 * @throws DatastoreException
	 */
	private Vector getNormalPics(String whereStr, String orderStr) throws DatastoreException{

		
		return this.getPics(whereStr, orderStr, IConstants.PIC_STATE_NORMAL);
	}
	
	
	
	/**
	 * 
	 * @param whereStr
	 * @return
	 * @throws DatastoreException
	 */
	private Vector getDistinctNormalPics(String whereStr, String orderStr) throws DatastoreException{
		
		return this.getDistinctPics(whereStr, orderStr, IConstants.PIC_STATE_NORMAL);
	}
	
	/**
	 * 
	 * @param whereStr
	 * @return
	 * @throws DatastoreException
	 */
	private Vector getDistinctDuplicatePics(String whereStr, String orderStr) throws DatastoreException{
		
		return this.getDistinctPics(whereStr, orderStr, IConstants.PIC_STATE_DUPLICATE);
	}
	


	

	/**
	 * select pics for list page
	 * @param whereStr
	 * @param orderStr
	 * @param picState
	 * @return
	 * @throws DatastoreException0
	 */
	
	private Vector<PicObject> getDistinctPics(String whereStr, String orderStr, int picState) throws DatastoreException{
		
		Vector<PicObject> results = new Vector<PicObject>();
		
		String state = " and flag="+picState ;
		
		StringBuffer sql = new StringBuffer("select distinct i.serialno, i.scandate, i.clerk, i.depno, i.status, i.type, v.description, i.form_number,i.form_sumoney,i.form_abstract,i.operator from xxxcmbc_erpscan.imageinfo i ");
		
		sql.append(leftjoin);
		sql.append(whereStr);
		sql.append(state);
		sql.append(orderStr);
		
					/*+ whereStr
					+ state 
					+ orderStr;*/
		
		log.info("getDistinctPics() sql: "+sql);
		
		
		// count totle row number
		countRowNumber(sql.toString());
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			ResultSet rs = stm.executeQuery(sql.toString());
			
			
			while(rs.next()){
				PicObject pic = new PicObject();
				
				//pic.setId(rs.getInt("id"));
				pic.setSerialno(rs.getString("serialno").trim());
				pic.setScanDate(rs.getString("scandate"));
				//pic.setPath(rs.getString("path"));
				pic.setClerk(rs.getString("clerk"));
				pic.setDepno(rs.getString("depno"));
				//pic.setFlag(rs.getInt("flag"));
				
				pic.setStatus(rs.getInt("status"));
				pic.setType(rs.getInt("type"));
				pic.setDescription(rs.getString("description"));
				
				pic.setForm_number(rs.getString("form_number"));
				pic.setOperator(rs.getString("operator"));
				//金额
				String money=rs.getString("form_sumoney");
				//log.info("money is:"+money);
				if(rs.getString("form_sumoney")!=null && !rs.getString("form_sumoney").equals("")){
					//System.out.println(rs.getString("form_sumoney"));
					pic.setForm_sunmoney(rs.getString("form_sumoney"));
				}
				//摘要
				String fab=rs.getString("form_abstract");
				//log.info("abstract is:"+fab);
				if(rs.getString("form_abstract")!=null && !rs.getString("form_abstract").equals("")){
					//System.out.println(rs.getString("form_abstract"));
					//获取摘要的简单信息
					if(fab.length()>5){
						fab=fab.substring(0, 4)+"...";
						pic.setSimple_abstract(fab);
					}else{
						pic.setSimple_abstract(fab);
					}
					pic.setForm_abstract(rs.getString("form_abstract"));
				}
				
				
				results.add(pic);
			}
		}
		catch (SQLException ex) {

			throw DatastoreException.datastoreError(ex);
		}
		finally{

			Global.releaseCon(stm, dbcon);
		}
		return results;
	}
	
	
	/**
	 * select pics for show page
	 * @param whereStr
	 * @param orderStr
	 * @param picState
	 * @return
	 * @throws DatastoreException
	 */
	private Vector getPics(String whereStr, String orderStr, int picState) throws DatastoreException{
		
		Vector<PicObject> results = new Vector<PicObject>();
		
		String picStateStr = " and flag="+picState;
		
		StringBuffer sql = new StringBuffer("select i.id, i.serialno, i.scandate, i.clerk, i.depno, i.status,i.flag, i.type, i.form_number ,i.path, i.backuppath, v.description,i.operator from xxxcmbc_erpscan.imageinfo i "); 
		
		sql.append(leftjoin);
		sql.append(whereStr);
		sql.append(picStateStr);
		sql.append(orderStr);
					/*+ whereStr
					+ picStateStr 
					+ orderStr;*/
		
		log.info("getPics() sql: "+sql);
		
		// count totle row number
		countRowNumber(sql.toString());
		
		Connection dbcon = null;
		Statement stm = null;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			ResultSet rs = stm.executeQuery(sql.toString());
			
			while(rs.next()){
				PicObject pic = new PicObject();
				
				pic.setSerialno(rs.getString("serialno").trim());
				pic.setScanDate(rs.getString("scandate"));
				pic.setPath(rs.getString("path"));
				pic.setClerk(rs.getString("clerk"));
				pic.setDepno(rs.getString("depno"));
				pic.setFlag(rs.getInt("flag"));
				pic.setBackupPath(rs.getString("backuppath"));
				
				pic.setStatus(rs.getInt("status"));
				pic.setType(rs.getInt("type"));
				pic.setDescription(rs.getString("description"));
				pic.setInvoice_type(rs.getInt("type")); //应付发票的子票据类型
				pic.setForm_number(rs.getString("form_number"));
				pic.setOperator(rs.getString("operator"));
				
				results.add(pic);
			}
		}
		catch (SQLException ex) {

			throw DatastoreException.datastoreError(ex);
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		return results;
	}

	

	private void countRowNumber(String sql) {
		Connection dbcon = null;
		Statement stm = null;
		
		String tmp = "select count('X') from ("
					+ sql 
					+ ")";
		
		int count = 0;
		
		try{			
			dbcon = Global.getConnection();
			stm = dbcon.createStatement();
			
			ResultSet rs = stm.executeQuery(tmp);
			
			rs.next();
			count = rs.getInt(1);
			
			this.setTotleCount(count);
			
		}
		catch(Exception e){
			
		}
		finally{
			Global.releaseCon(stm, dbcon);
		}
		
	}


	/**
	 * 
	 * @param depno
	 * @param status_code
	 * @param serialno_begin
	 * @param serialno_end
	 * @param scandate_begin
	 * @param scandate_end
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getExpensePics(String depno, String status_code, 
								String serialno_begin, String serialno_end, 
								String scandate_begin, String scandate_end) 
	throws DatastoreException {
		
		
		StringBuffer whereStr = new StringBuffer(" where 1=1");

		if(status_code!=null && !status_code.equals("all")){
			whereStr.append(" and v.expense_status_code='");
			whereStr.append(status_code);
			whereStr.append("'");
		}
		
		if(depno!=null && !depno.equalsIgnoreCase("all")){
			if(depno.equals("66")){
				whereStr.append(" and depno like '");
				whereStr.append(depno);
				whereStr.append("%'");
			}else{
				whereStr.append(" and depno='");
				whereStr.append(depno);
				whereStr.append("'");
			}
			
		}
		
		
		if(serialno_begin != null && serialno_begin.length() > 0) 
		{
			whereStr.append(" and serialno>='");
			whereStr.append(serialno_begin);
			whereStr.append("'");
			
		}
		
		if(serialno_end != null && serialno_end.length() > 0) 
		{
			whereStr.append(" and serialno<='");
			whereStr.append(serialno_end);
			whereStr.append("'");
		}
		
		
		if(scandate_begin != null && scandate_begin.length() == 8) 
		{
			whereStr.append(" and scandate>='");
			whereStr.append(scandate_begin);
			whereStr.append("'");
		}
		
		if(scandate_end != null && scandate_end.length() == 8) 
		{
			
			whereStr.append(" and scandate<='");
			whereStr.append(scandate_end);
			whereStr.append("'");
		}
		
		whereStr.append(" and type=");
		whereStr.append(IConstants.PIC_TYPE_EXPENSE);
		// whereStr.append();

		return this.getDistinctNormalPics(whereStr.toString(), " order by serialno ");
	}
	
	
	/**
	 * 
	 * @param depno
	 * @param status_code
	 * @param serialno_begin
	 * @param serialno_end
	 * @param scandate_begin
	 * @param scandate_end
	 * @return
	 * @throws DatastoreException
	 */
	public Vector getInvoicePics(SearchPicForm form)
	throws DatastoreException {
		
		String temp = form.getInvoice_type();//应付发票的子票据类型
		
		StringBuffer whereStr = new StringBuffer(" where 1=1 ");
		
		constructWhere(form, whereStr);
		
		//构造应付发票的票据类型
		//whereStr.append(" and type=");
		//whereStr.append(IConstants.PIC_TYPE_INVOICE);
		if ((temp != null) && (!(temp.equals("all"))) && (!(temp.equals("8")))) {
			whereStr.append(" and type=");
			whereStr.append(Integer.parseInt(temp));
		} else if (!(temp.equals("all"))) {
			whereStr.append(" and type=");
			whereStr.append(IConstants.PIC_TYPE_INVOICE); //应付发票
		} else if (temp.equals("all")) {
			whereStr.append(" and type in(");
			whereStr.append(IConstants.PIC_TYPE_INVOICE+","); 
			whereStr.append(IConstants.INVOICE_TYPE_ADVANCE+","); //预付款
			whereStr.append(IConstants.INVOICE_TYPE_CANCEL+","); // 核销预付款
			whereStr.append(IConstants.INVOICE_TYPE_NATURAL+")");//正常付款
		}
		return this.getDistinctNormalPics(whereStr.toString(), " order by serialno ");
	}
	
	
	public Vector getAssetPics(SearchPicForm form)
	throws DatastoreException {

		StringBuffer whereStr = new StringBuffer(" where 1=1 ");

		constructWhere(form, whereStr);

		whereStr.append(" and type=");
		whereStr.append(IConstants.PIC_TYPE_ASSET);
		// whereStr.append();

		return this.getDistinctNormalPics(whereStr.toString(), " order by serialno ");
	}
	
	
	public Vector getLedgerPics(SearchPicForm form)
	throws DatastoreException {

		StringBuffer whereStr = new StringBuffer(" where 1=1 ");

		constructWhere(form, whereStr);

		whereStr.append(" and type=");
		whereStr.append(IConstants.PIC_TYPE_LEDGER);
		// whereStr.append();

		return this.getDistinctNormalPics(whereStr.toString(), " order by serialno ");
	}
	
	/**
	 * 
	  * Method Name:getContractPics
	  * Note：查询合同的图片信息
	  * @param form
	  * @return
	  * @throws DatastoreException
	  * Date：Nov 9, 2009, 9:40:45 AM
	  * @Author:liBing
	 */
	public Vector getContractPics(SearchPicForm form)
	throws DatastoreException {
		String temp = form.getContract_type();//合同单据的子票据类型
		StringBuffer whereStr = new StringBuffer(" where 1=1 ");

		constructWhere(form, whereStr);
		
		//判断查询合同票据的类型
		if ((temp != null) && (!(temp.equals("all")))) {
			whereStr.append(" and type=");
			whereStr.append(Integer.parseInt(temp));
		} else if (temp.equals("all")) {
			whereStr.append(" and type in(");
			//FZ 房租/物业
			whereStr.append(IConstants.PIC_TYPE_FZCONTRACT+",");
			//ZX 装修
			whereStr.append(IConstants.PIC_TYPE_ZXCONTRACT+",");
			//GX 广告宣传
			whereStr.append(IConstants.PIC_TYPE_GXCONTRACT+",");
			//CG 采购
			whereStr.append(IConstants.PIC_TYPE_CGCONTRACT+",");
			//KC 科技采购
			whereStr.append(IConstants.PIC_TYPE_KCCONTRACT+",");
			//FW 服务
			whereStr.append(IConstants.PIC_TYPE_FWCONTRACT+",");
			//QT 其他
			whereStr.append(IConstants.PIC_TYPE_QTCONTRACT+")");
		}
		//whereStr.append(" and type=");
		//whereStr.append(IConstants.PIC_TYPE_CONTRACT);
		// whereStr.append();

		return this.getDistinctNormalPics(whereStr.toString(), " order by serialno ");
	}
	
	/**
	 * @param depno
	 * @param status_code
	 * @param serialno_begin
	 * @param serialno_end
	 * @param scandate_begin
	 * @param scandate_end
	 * @param whereStr
	 */
	private void constructWhere(SearchPicForm form, StringBuffer whereStr) {
		
		String tmp = form.getStatus_code();
		if(!tmp.equals("all")){
			whereStr.append(" and status=");
			whereStr.append(tmp);
			if(tmp.equals("4")){
				whereStr.append(" and operator='");
				whereStr.append(form.getOperator());
				whereStr.append("'");
			}
		}
		
		tmp = form.getDepno();
		if(!tmp.equals("all")){
			if(tmp.equals("66")){
				whereStr.append(" and depno like '");
				whereStr.append(tmp);
				whereStr.append("%'");
			}else{
				whereStr.append(" and depno='");
				whereStr.append(tmp);
				whereStr.append("'");
			}
		}
		
		tmp = form.getSerialno_begin();
		if(tmp != null && tmp.length() > 0) 
		{
			whereStr.append(" and serialno>='");
			whereStr.append(tmp);
			whereStr.append("'");	
		}
		
		tmp = form.getSerialno_end();
		if(tmp != null && tmp.length() > 0) 
		{
			whereStr.append(" and serialno<='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
		
		tmp = form.getScandate_begin();
		if(tmp != null && tmp.length() == 8) 
		{
			whereStr.append(" and scandate>='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
		
		tmp = form.getScandate_end();
		if(tmp != null && tmp.length() == 8) 
		{
			
			whereStr.append(" and scandate<='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
		
		tmp = form.getForm_number();
		if(tmp != null && tmp.length() > 0)
		{
			whereStr.append(" and form_number='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
		
		/**
		 * 合同金额
		 * Nov 9, 2009, 9:47:04 AM
		 */
		tmp = form.getForm_sunmoney_contract();
		if(tmp != null && tmp.length() > 0)
		{
			whereStr.append(" and form_sumoney='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
		/**
		 * 合同摘要
		 * Nov 9, 2009, 9:47:04 AM
		 */
		tmp = form.getForm_abstract_contract();
		if(tmp != null && tmp.length() > 0)
		{
			whereStr.append(" and form_abstract='");
			whereStr.append(tmp);
			whereStr.append("'");
		}
	}

	/*private void constructWhere(SearchPicForm form, StringBuffer whereStr) {
		if(!status_code.equals("all")){
			whereStr.append(" and status=");
			whereStr.append(status_code);
		}
		
		if(!depno.equals("all")){
			whereStr.append(" and depno='");
			whereStr.append(depno);
			whereStr.append("'");
		}
		
		
		if(serialno_begin != null && serialno_begin.length() > 6) 
		{
			whereStr.append(" and serialno>='");
			whereStr.append(serialno_begin);
			whereStr.append("'");
			
		}
		
		if(serialno_end != null && serialno_end.length() > 6) 
		{
			whereStr.append(" and serialno<='");
			whereStr.append(serialno_end);
			whereStr.append("'");
		}
		
		
		if(scandate_begin != null && scandate_begin.length() == 8) 
		{
			whereStr.append(" and scandate>='");
			whereStr.append(scandate_begin);
			whereStr.append("'");
		}
		
		if(scandate_end != null && scandate_end.length() == 8) 
		{
			
			whereStr.append(" and scandate<='");
			whereStr.append(scandate_end);
			whereStr.append("'");
		}
	}*/
}
