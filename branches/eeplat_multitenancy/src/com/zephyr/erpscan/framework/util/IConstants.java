/**
 * 
 */
package com.zephyr.erpscan.framework.util;

/**
 * @author t
 * 
 */
public interface IConstants {
	
	public static final String SERVICE_FACTORY_KEY = "com.zephyr.erpscan.IErpScanServiceFactory";
	//service class name define in web.xml
	public static final String SERVICE_CLASS_KEY = "ErpScan-Service-class";
	
	public static final String SESSION_USER = "ErpScan-Session-User";		//session user
	
	//define pic state: flag
	public static final int PIC_STATE_NORMAL = 0;			//正常状态
	public static final int PIC_STATE_ALREADY_BACKUP = 1;//已备份
	public static final int PIC_STATE_DUPLICATE = 2;		//有重复单据，待批
	public static final int PIC_STATE_ABNORMAL = 9;		//非正常（预留待用）
	
	// define pic type: type
	public static final int PIC_TYPE_INVOICE=1;	//应付发票
	public static final int PIC_TYPE_EXPENSE=2;	//网上报销
	public static final int PIC_TYPE_ASSET=3;		//资产单据
	public static final int PIC_TYPE_LEDGER=4;	//总帐单据
	public static final int PIC_TYPE_CONTRACT=8;//合同单据
	
	//应付发票子类型
	public static final int INVOICE_TYPE_ADVANCE = 5; 	//预付款
	public static final int INVOICE_TYPE_CANCEL = 6; 	//核销预付款
	public static final int INVOICE_TYPE_NATURAL = 7;	//正常付款
	
	//合同单据子类型
	//FZ 房租/物业
	public static final int PIC_TYPE_FZCONTRACT=81;
	//ZX 装修
	public static final int PIC_TYPE_ZXCONTRACT=82;
	//GX 广告宣传
	public static final int PIC_TYPE_GXCONTRACT=83;
	//CG 采购
	public static final int PIC_TYPE_CGCONTRACT=84;
	//KC 科技采购
	public static final int PIC_TYPE_KCCONTRACT=85;
	//FW 服务
	public static final int PIC_TYPE_FWCONTRACT=86;
	//QT 其他
	public static final int PIC_TYPE_QTCONTRACT=87;
	
	
	// define invoice status 表示在oracle系统中是否录入: status
	// 扩展到 资产和总帐 20070614
	
	public static final int PIC_ALREADY_INPUT=2;	    //已录入
	public static final int PIC_NOT_INPUT=1;		    //未录入
	public static final int PIC_STATUS_UNKNOW=3;	    //未知
	public static final int PIC_RELAY_INPUT=4;         //处理中
	public static final int PIC_OLD_INPUT=5;           //曾录入
	
	//define priv_explain field
	//修改本人密码
	public static final String MODIFY_SELF_PWD = "modify_self_pwd";
	//用户管理
	public static final String MANAGE_CLERK = "manage_clerk_p";
	//单据查询
	public static final String BROWSER_IMAGE = "browser_image_p";
	//扫描上传
	public static final String SCAN_UPDATE = "scan_update_p";
	//审核重复单据
	public static final String CHECK_REDUPLICATE = "check_reduplicate_p";
	//	各行单据录入
	public static final String WRITER_IMAGE = "writer_image_p";
	//信用卡单据录入
	public static final String WRITER_CREDIT_IMAGE = "writer_credit_image_p";
	
}
