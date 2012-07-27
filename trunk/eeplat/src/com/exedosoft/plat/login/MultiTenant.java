package com.exedosoft.plat.login;

import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.bo.BaseObject;

public class MultiTenant extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4734086650266195266L;
	private String id;
	private String name;
	private String l10n;
	private java.sql.Date startDate;
	private String multi_datasource_uid;
	private String model_datasource_uid;
	private String datasource_host_uid;


	public String getObjUid() {
		return id;
	}

	public void setObjUid(String objId) {
		this.id = objId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getL10n() {
		return l10n;
	}



	public void setL10n(String l10n) {
		this.l10n = l10n;
	}



	public java.sql.Date getStartDate() {
		return startDate;
	}



	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}



	public String getMulti_datasource_uid() {
		return multi_datasource_uid;
	}



	public void setMulti_datasource_uid(String multi_datasource_uid) {
		this.multi_datasource_uid = multi_datasource_uid;
	}



	public String getModel_datasource_uid() {
		return model_datasource_uid;
	}



	public void setModel_datasource_uid(String model_datasource_uidl) {
		this.model_datasource_uid = model_datasource_uidl;
	}



	public String getDatasource_host_uid() {
		return datasource_host_uid;
	}



	public void setDatasource_host_uid(String datasource_host_uid) {
		this.datasource_host_uid = datasource_host_uid;
	}



	public static MultiTenant findTenant(String tenantName){
		
		if(tenantName==null){
			return null;
		}
		
		MultiTenant ma = DAOUtil.currentDataSource("/ds_multi.xml").getBySql(
				MultiTenant.class, "select * from multi_tenancy where name=?",tenantName);
		
		if(ma!=null && ma.getName()!=null){
			return ma;
		}
		
		return null;
		
	} 
	
	
	
	public static void main(String[] args) {

		MultiTenant ma = MultiTenant.findTenant("badminton1");

		System.out.println(ma);

	}

}
