package com.exedosoft.plat.gene;


/**
 * @author   IBM
 */
public class ForwarderFactory {
	
   private static final ForwarderFactory ff = new ForwarderFactory();
	
	
	private ForwarderFactory(){}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static ForwarderFactory getDefaultForwarderFactory(){
		return ff;
	}
	
	public static ATableForwarder getTableForwarder(String aTable, String aDataSourceUid, String aBusiPackageUid){
		return new ATableForwarderImp(aTable,aDataSourceUid,aBusiPackageUid);
	}
	
//	public static ATableForwarder getTableForwarder(String aTable, String aDataSourceUid){
		
//		HbmDAO dao = new HbmDAO();
//		BusiPackage bp = new BusiPackage();
//		bp.setL10n()
		
		
//		return new ATableForwarderImp(aTable,aDataSourceUid,aBusiPackageUid);
		//return null;
//	}


}
