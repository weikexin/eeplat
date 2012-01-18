package com.exedosoft.plat.util.id;

import com.exedosoft.plat.bo.DODataSource;

import java.util.Hashtable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/**
 * <p>Title: </p> <p>Description: </p> <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author  not attributable
 * @version  1.0
 */
public class HLIDBuilder {

  private static HLIDBuilder builder;
  private static Object lockObj = new Object();//lock object
  private static  Hashtable casheKey = new Hashtable();//cashe
  private static  Hashtable casheOverFlowId = new Hashtable();
  private int overFlowId = 0; //when cashe's id more than this id ,have to retrieve from db
  private int poolSize = 20;// getId numbers without accessing datebase
  private int tableId = 0;


  private HLIDBuilder() {
  }
  /**
   * Gets only one TableIdBuidler object
   * @return
   */
  public static HLIDBuilder getInstance(){

    if(builder == null){
      synchronized(lockObj){
        if(builder==null){
          builder = new HLIDBuilder();
        }
      }
    }
    return builder;
  }


  /**
    *
    * Gets the number value of max of objid +1
    */
   private int getIDFromDb(String aTableName){

//    StringBuffer buffer = new StringBuffer();
	 aTableName = aTableName.toUpperCase(); 
     String sql = "SELECT next_hi FROM DO_KEYS WHERE table_name=?";
     StringBuffer sqlUpdate = new StringBuffer("update DO_KEYS SET next_hi=next_hi+")
         .append(this.poolSize).append(" WHERE table_name=?");
     Connection con = null;
     PreparedStatement stmt = null;
     try {
       //query
       con = DODataSource.getDefaultCon();

       stmt = con.prepareStatement(sql);
       int retId = 1;

         stmt.setString(1, aTableName);
         ResultSet rs = stmt.executeQuery();

         if (rs.next()) {
           retId = rs.getInt("next_hi") + 1;
           overFlowId = retId + poolSize;//set overflow id
           casheOverFlowId.put(aTableName,new Integer(overFlowId));
         }

         //update
         stmt = con.prepareStatement(sqlUpdate.toString());
         stmt.setString(1, aTableName);
         stmt.execute();
       return retId;

     }
     catch (SQLException ex) {
       ex.printStackTrace();
       return 0;
     }
     finally{//Close Connection
      try {
        stmt.close();
        con.close();
      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
     }
   }
   /**
    * Put tableName and maxId to hashtble
    */
   private void putCashe(String aTableName,int aMaxId){
     casheKey.put(aTableName,new Integer(aMaxId));
   }
   /**
    * Gets table key by a table name
    * @return key in cashe
    */
   public  synchronized int getID(String aTableName) {

       if (casheKey.containsKey(aTableName)) {
             tableId = ( (Integer) casheKey.get(aTableName)).intValue() + 1;
       } //if cash contains table's key
       else {
         tableId = this.getIDFromDb(aTableName);
       }
       //////////////////if overflow
       if(casheOverFlowId.get(aTableName)!=null){
         overFlowId = ( (Integer) casheOverFlowId.get(aTableName)).intValue();
       }
       if (tableId >= overFlowId) {
             tableId = this.getIDFromDb(aTableName);
       }
       /////////////refesh cashe data
       putCashe(aTableName, tableId);
       return tableId;
   }

   public  synchronized Long getLong(String aTableName){
     return new Long(getID(aTableName));
   }
   public  synchronized Integer getInteger(String aTableName){
     return new Integer(getID(aTableName));
   }

   public synchronized String getString(String aTableName) {
       return  String.valueOf(getID(aTableName));
   }

   public static void main(String[] args){

//     int i = 1;
//     for(int j=0;j<2;j++){
//       i++;
//     }
//     System.out.println(++i);
	   String aa = "1100000710453";
	   System.out.println(Integer.parseInt(aa.substring(9)));

   }

}
