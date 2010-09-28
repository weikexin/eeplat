package com.exedosoft.plat.util.id;

import com.exedosoft.plat.bo.DOBO;
import com.exedosoft.plat.bo.DODataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class HLIDInit {
  public HLIDInit() {
  }

  public void initHLID(){
	  
	  /**
	   * Keys 表只放到配置数据库中，以便统一维护。
	   */

    String sql = "SELECT * FROM DO_KEYS";
    Connection con = null;
    Connection conBus = null;
    PreparedStatement stmt = null;
    try {
      //query
      con = DODataSource.getDefaultCon();

      stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
          String tableName = rs.getString("table_name");
          DOBO bo = DOBO.getDOBOByName(tableName);
          if(bo!=null && 
        		  (bo.getKeyType()!=null && bo.getKeyType().intValue() == DOBO.KEY_TYPE_HIGH_LOW)){
            String keyCol =  bo.getKeyCol();
            conBus = bo.getDataBase().getConnection();
            String maxKeySql =
                new StringBuffer("select max(")
                .append(keyCol).append(") from ")
                .append(tableName).toString();
            PreparedStatement stmtBusi = conBus.prepareStatement(maxKeySql);
            ResultSet rsMaxKey = stmtBusi.executeQuery();
            Integer intMaxValue = new Integer(1);
            System.out.println(tableName);
            if(rsMaxKey.next()){
              intMaxValue = new Integer(rsMaxKey.getInt(1));
            }
            rsMaxKey.close();
            stmtBusi.close();
            conBus.close();
            
            
            /////////////////update
            StringBuffer sqlUpdate = new StringBuffer(
                "update DO_KEYS SET next_hi=")
                .append(intMaxValue.intValue() +
                        1).append(" WHERE table_name=?");
            PreparedStatement  stmtUpdate = con.prepareStatement(sqlUpdate.toString());
            stmtUpdate.setString(1, tableName);
            stmtUpdate.executeUpdate();
            stmtUpdate.close();

          }
        }

    }
    catch (Exception ex) {
    //  ex.printStackTrace();
    }
    finally{//Close Connection
     try {
       stmt.close();
       con.close();
       if( conBus != null ){
    	   conBus.close();
       }
     }
     catch (Exception ex1) {
       ex1.printStackTrace();
     }
    }
  }

  public static void main(String[] args){

    HLIDInit tii = new HLIDInit();
    tii.initHLID();
  }

}