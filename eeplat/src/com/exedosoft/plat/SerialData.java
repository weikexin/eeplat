/**
 * 
 */
package com.exedosoft.plat;

import com.exedosoft.plat.dao.ObjectTableMapper;

/**
 * @author Administrator
 *
 */
public class SerialData {

	/**
	 * 
	 */
	public SerialData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheFactory.getCacheData().cacheAllConfigData();
		CacheFactory.getCacheData().serialCache();
	}

}
