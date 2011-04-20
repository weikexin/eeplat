package com.zephyr.erpscan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.zephyr.db.DBConnection;
import com.zephyr.erpscan.framework.util.Global;
import com.zephyr.erpscan.object.PicObject;

public class OperateData {

	public OperateData(){
		
	}
	
	// 将状态为“未录入”的单据，改为“处理中”
	public synchronized ArrayList<PicObject> getUpdatedData(String serialnos,String operator,String stamp,String depno,int num){
		
		ResultSet rs = null;
		Statement smt = null;
		Connection dbcon = null;
		StringBuffer sql1 =  new StringBuffer();
		StringBuffer sql = new StringBuffer();
		ArrayList<PicObject> al = null;
		PicObject pic = null;
		String s_serialnos[] = null;
		int n = 0;
		s_serialnos = serialnos.split("&");
		
		try {
			dbcon = Global.getConnection();
			smt = dbcon.createStatement();
				
			if(num>0 ){
				 sql1.append("update xxxcmbc_erpscan.imageinfo set status=4, operator='");
				 sql1.append(operator);
		    	 sql1.append("' where status=1 and flag=0");
		    	 sql1.append("  and serialno in (");
		    	 
		    	 for(int j = 0;j < num; j ++){
		    		 if (j != 0) sql1.append(",");
		    		 sql1.append("'"+s_serialnos[j]+"'");
		    	 }
		    	 sql1.append(")");
			} 
			System.out.println("sql is  "+sql1.toString());
		    n = smt.executeUpdate(sql1.toString());
		    System.out.println("n is "+n);
			smt.close();
			
			sql.append("select distinct serialno, scandate, clerk, depno, status, form_number from xxxcmbc_erpscan.imageinfo ");
			sql.append("  where operator='");
			sql.append(operator);
			sql.append("' and status=4");
			sql.append(" and type=");
			sql.append(stamp);
			if(!depno.equals("all")){
				if(depno.equals("66")){
					sql.append(" and depno like'");
					sql.append(depno);
					sql.append("%'");
				}else{
					sql.append(" and depno='");
					sql.append(depno);
					sql.append("'");
				}
			}
			
			sql.append(" order by serialno");
				
			System.out.println("sql is "+sql.toString());
			smt = dbcon.createStatement();
			rs = smt.executeQuery(sql.toString());
				    
			al = new ArrayList<PicObject>();
			while(rs.next()){
				pic = new PicObject();
				pic.setScanDate(rs.getString("scandate"));
				pic.setClerk(rs.getString("clerk"));
				pic.setSerialno(rs.getString("serialno"));
				pic.setDepno((rs.getString("depno")));
				pic.setStatus((rs.getInt("status")));
				pic.setForm_number((rs.getString("form_number")));
							
				al.add(pic);
			}
			
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			Global.releaseCon(smt, dbcon);
		}
		
		return al;
	}
	
	/*public static void main(String arg[]){
		
		ArrayList<PicObject> al = null;
		al = new ArrayList<PicObject>();
		al = new OperateData().getUpdatedData("AP2300200706250001&", "oo", 1);
	
	}*/
}
