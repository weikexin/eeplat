/**
 * 
 */
package com.zephyr.erpscan;

import javax.servlet.ServletContext;

import com.zephyr.erpscan.framework.exception.DatastoreException;
import com.zephyr.erpscan.framework.exception.InvalidLoginException;
import com.zephyr.erpscan.object.User;

/**
 * @author t
 *
 */
public interface IErpScanService {
	
	public void setServletContext(ServletContext ctx);
	
	public ServletContext getServletContext();
	
	public void destory();
	
	public int getCurRow();

	public void setCurRow(int curRow);
	
	public int getTotleCount();
	
	public void setTotleCount(int totleCount);

	public User authenticate(String clkno, String password)
			throws InvalidLoginException, DatastoreException;
	
	public void logout(String clkno);
}
