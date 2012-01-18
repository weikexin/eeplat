package com.exedosoft.plat.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.exedosoft.plat.bo.DOService;

/**
 * 对服务进行批处理
 * @author aa
 * 在批量删除，修改时需要。
 * 
 * 增加是否批量需要考虑
 *
 */
public class DOServiceBatch {
	
	
	
	
	private List batchService;
	
	public DOServiceBatch(){
		batchService = new ArrayList();
	}
	
	/**
	 * batch只接受增删改的service 查询service 不接受
	 * @param aService
	 */
	public void addService(DOService aService){
		
		if (aService.getType() != null
				&& (aService.getType().intValue() == DOService.TYPE_SELECT
						|| aService.getType().intValue() == DOService.TYPE_SELECT_SIMPLE || aService
						.getType().intValue() == DOService.TYPE_SELECT_AUTO_CONDITION)) {
			return;
		}
	
		batchService.add(aService);
	}

	/**
	 * sql批处理  每个任务接受不同的参数 可以

	 *
	 */
	public void excuteBatch(){
		
		if(batchService==null || batchService.size()==0){
			return;
		}
		DOService firstService = (DOService)batchService.get(0);
		
		
		Connection con = null;
		try {
			con = firstService.getBo().getDataBase().getContextConnection();
			Statement pstmt = con.createStatement();
			for(Iterator it = batchService.iterator();it.hasNext();){
				DOService aService = (DOService)it.next();
				pstmt.addBatch(aService.getMainSql());
				
			}
		
//			uid = putParas(null, pstmt);
//			pstmt.executeUpdate();
//			clearCorreCache(uid);
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				firstService.getBo().getDataBase().ifCloseConnection(con);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}

		
		
	}
	


}
