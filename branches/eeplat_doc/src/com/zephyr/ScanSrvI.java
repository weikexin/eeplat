package com.zephyr;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

import Erp._ScanSrvDisp;
import Ice.Current;

import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.action.zidingyi.OperationUtil;
import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.util.DOGlobals;
import com.exedosoft.plat.util.id.UUIDHex;
import com.zephyr.erpscan.ErpScanServiceFactory;
import com.zephyr.erpscan.UploadPicServiceImpl;
import com.zephyr.erpscan.object.PicObject;

public class ScanSrvI extends _ScanSrvDisp {

	Logger log = Logger.getLogger(ScanSrvI.class);

	/**
	 * string info format: clkno|depno|filename|serialno string info format:
	 * clkno|depno
	 * 
	 * @param info
	 */
	private PicObject getInfo(String info) {
		StringTokenizer st = new StringTokenizer(info, "|");

		PicObject pic = new PicObject();
		String tmp[] = new String[2];

		int count = st.countTokens();

		if (count == 2) {
			for (int i = 0; i < 2; i++) {
				tmp[i] = st.nextToken();
				if (tmp[i] == null || tmp[i].length() <= 0) {
					return null;
				}
			}

		} else {
			return null;
		}

		/*
		 * Calendar c = Calendar.getInstance(); int year = c.get(Calendar.YEAR);
		 * int month = c.get(Calendar.MONTH)+1; int day =
		 * c.get(Calendar.DAY_OF_MONTH);
		 * 
		 * String monthStr = month <10 ? "0"+month : ""+month; String dayStr =
		 * day < 10 ? "0"+day : ""+day;
		 * 
		 * String scandate = new String(year+""+monthStr+dayStr);
		 */
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		java.util.Date d = new java.util.Date();

		String scandate = formatter.format(d);

		log.debug("scandate: " + scandate);

		pic.setScanDate(scandate);
		pic.setClerk(tmp[0]);
		pic.setDepno(tmp[1]);
		// pic.setPath(tmp[2]);
		// pic.setSerialno(tmp[3]);

		return pic;
	}

	/**
	 * Note:判断图片是否存在 (non-Javadoc)
	 * 
	 * @see Erp._ScanSrvOperations#isExist(java.lang.String, Ice.Current) Date:
	 *      Jan 13, 2010 , 8:33:09 PM
	 * @Author: liBing
	 */
	public int isExist(String serialno, Current __current) {
		if(serialno != null)
			serialno = serialno.trim();
		//图片名称@标记
		if(serialno.indexOf("@") != -1) {
			String[] array = serialno.split("@");
			String fname = array[0];
			String info = array[1];
			int len = info.length();
			String catalogIndex = info.substring(len-3, len);
			String catalogCode = info.substring(len-7, len-3);
			String yearCode = info.substring(len-11, len-7);
			String type = info.substring(len-13, len-11);
			String unit = info.substring(0, len-13);
			info = unit+"-"+type+"-"+yearCode+"-"+catalogCode+"-"+catalogIndex;
			File imgf = null;
			String filePath = null;
			String systemPath = DOGlobals.UPLOAD_TEMP;
			String fgf = "/";
			if (systemPath != null) {
				systemPath = systemPath.trim();
				if (systemPath.endsWith("/")) {
					fgf = "/";
				} else if (systemPath.endsWith("\\")) {
					fgf = "\\\\";
				} else if (systemPath.indexOf("/") != -1) {
					fgf = "/";
					systemPath = systemPath + fgf;
				} else if (systemPath.indexOf("\\\\") != -1) {
					fgf = "\\\\";
					systemPath = systemPath + fgf;
				}
			}
			// 创建档案文件夹
			File msflp = new File(systemPath + "MSFL-PROJECT");
			if (!msflp.exists()) {
				msflp.mkdir();
			}

			if (info.indexOf("MSFL-FK-") != -1) {
				// 创建类型文件夹
				File msfkf = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-FK");
				if (!msfkf.exists()) {
					msfkf.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-FK" + fgf;
			} else if (info.indexOf("MSFL-PS-") != -1) {
				File msfps = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-PS");
				if (!msfps.exists()) {
					msfps.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-PS" + fgf;
			} else if (info.indexOf("MSFL-ZH-") != -1) {
				File msfzh = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-ZH");
				if (!msfzh.exists()) {
					msfzh.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-ZH" + fgf;
			} else if (info.indexOf("MSFL-SS-") != -1) {
				File msfss = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-SS");
				if (!msfss.exists()) {
					msfss.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-SS" + fgf;
			} else if (info.indexOf("MSFL-BL-") != -1) {
				File msfbl = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-BL");
				if (!msfbl.exists()) {
					msfbl.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-BL" + fgf;

				// 其他类型
			} else if (info.indexOf("-") != -1) {
				String l1 = info.substring(0, info.indexOf("-"));
				String l2str = info.substring(info.indexOf("-") + 1);
				String l2 = l2str;
				if(l2str.indexOf("-") != -1) {
					l2 = l2str.substring(0, l2str.indexOf("-"));
				}
				
				String lx = l1 + "-" + l2;
				File msflx = new File(systemPath + "MSFL-PROJECT" + fgf + lx);
				if (!msflx.exists()) {
					msflx.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + lx + fgf;
			} else {
				File msforth = new File(systemPath + "MSFL-PROJECT" + fgf + info);
				if (!msforth.exists()) {
					msforth.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + info + fgf;
			}
			// 创建项目文件夹
			String projectName = info;
			if(info.indexOf("-") != -1) {
				projectName = info.substring(0, info.lastIndexOf("-"));
				filePath = filePath + projectName;
				File projectFile = new File(filePath);
				if (!projectFile.exists()) {
					projectFile.mkdir();
				}
			} else {
				filePath = filePath + projectName;
				File projectFile = new File(filePath);
				if (!projectFile.exists()) {
					projectFile.mkdir();
				}
			}
			// 创建目录文件夹
			filePath = filePath + fgf + info;
			File catalogFile = new File(filePath);
			if (!catalogFile.exists()) {
				catalogFile.mkdir();
			}
			
			//创建文件
			String fileallp = filePath + fgf + fname;
			imgf = new File(fileallp);
			if(imgf.exists()) {
				return 1;
			}
		}
		
		
		return 0;
//		if (serialno == null || serialno.length() <= 0) {
//			log.error("serialno from client is null.");
//			return -1;
//		}
//
//		log.debug("isExist? " + serialno);
//
//		ErpScanServiceFactory factory = new ErpScanServiceFactory();
//
//		int exist = -1;
//
//		try {
//			UploadPicServiceImpl service = factory
//					.createUploadPicServiceForServer();
//
//			exist = service.isExist(serialno);
//
//			log.info(serialno + " is exist: " + exist);
//			return exist;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			exist = -1;
//			return exist;
//		}

	}

	/**
	 * Note:实现Ice的骨骼，保存图片 (non-Javadoc)
	 * 
	 * @see Erp._ScanSrvOperations#saveImageFile(java.lang.String,
	 *      java.lang.String, byte[], java.lang.String, Ice.Current) Date: Jan
	 *      13, 2010 , 8:32:01 PM
	 * @Author: liBing
	 */
	public int saveImageFile(String fname, String serialno, byte[] data,
			String info, Current __current) {

		log.info("serialno: " + serialno + "saveImageFile: " + fname + "info: "
				+ info);
		/**
		 * @Author: yxx 租赁 fname:文件名,如：CATALOG20110321140445.bmp data:图片二进制数据
		 *          serialno: 序号 infor:目录，即标记
		 * 
		 *          __current：null
		 */
		if (fname != null && serialno != null && info != null && data != null) {
			fname = fname.trim();
			serialno = serialno.trim();
			info = info.trim();

			int len = info.length();
			String catalogIndex = info.substring(len-3, len);
			String catalogCode = info.substring(len-7, len-3);
			String yearCode = info.substring(len-11, len-7);
			String type = info.substring(len-13, len-11);
			String unit = info.substring(0, len-13);
			info = unit+"-"+type+"-"+yearCode+"-"+catalogCode+"-"+catalogIndex;
			
			
			File imgf = null;
			String filePath = null;
			String  fileUrl = null;
			String systemPath = DOGlobals.UPLOAD_TEMP;
			String fgf = "/";
			if (systemPath != null) {
				systemPath = systemPath.trim();
				if (systemPath.endsWith("/")) {
					fgf = "/";
				} else if (systemPath.endsWith("\\")) {
					fgf = "\\\\";
				} else if (systemPath.indexOf("/") != -1) {
					fgf = "/";
					systemPath = systemPath + fgf;
				} else if (systemPath.indexOf("\\\\") != -1) {
					fgf = "\\\\";
					systemPath = systemPath + fgf;
				}
			}
			// 创建档案文件夹
			File msflp = new File(systemPath + "MSFL-PROJECT");
			fileUrl = "MSFL-PROJECT/";
			if (!msflp.exists()) {
				msflp.mkdir();
			}

			
			if (info.indexOf("MSFL-FK-") != -1) {
				// 创建类型文件夹
				File msfkf = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-FK");
				
				if (!msfkf.exists()) {
					msfkf.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-FK" + fgf;
				fileUrl = fileUrl+"MSFL-FK/";
			} else if (info.indexOf("MSFL-PS-") != -1) {
				File msfps = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-PS");
				if (!msfps.exists()) {
					msfps.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-PS" + fgf;
				fileUrl = fileUrl+"MSFL-PS/";
			} else if (info.indexOf("MSFL-ZH-") != -1) {
				File msfzh = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-ZH");
				if (!msfzh.exists()) {
					msfzh.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-ZH" + fgf;
				fileUrl = fileUrl+"MSFL-ZH/";
			} else if (info.indexOf("MSFL-SS-") != -1) {
				File msfss = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-SS");
				if (!msfss.exists()) {
					msfss.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-SS" + fgf;
				fileUrl = fileUrl+"MSFL-SS/";
			} else if (info.indexOf("MSFL-BL-") != -1) {
				File msfbl = new File(systemPath + "MSFL-PROJECT" + fgf
						+ "MSFL-BL");
				if (!msfbl.exists()) {
					msfbl.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + "MSFL-BL" + fgf;
				fileUrl = fileUrl+"MSFL-BL/";
				// 其他类型
			} else if (info.indexOf("-") != -1) {
				String l1 = info.substring(0, info.indexOf("-"));
				String l2str = info.substring(info.indexOf("-") + 1);
				String l2 = l2str;
				if(l2str.indexOf("-") != -1) {
					l2 = l2str.substring(0, l2str.indexOf("-"));
				}
				
				String lx = l1 + "-" + l2;
				File msflx = new File(systemPath + "MSFL-PROJECT" + fgf + lx);
				if (!msflx.exists()) {
					msflx.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + lx + fgf;
				fileUrl = fileUrl+lx+"/";
			} else {
				File msforth = new File(systemPath + "MSFL-PROJECT" + fgf + info);
				if (!msforth.exists()) {
					msforth.mkdir();
				}
				filePath = systemPath + "MSFL-PROJECT" + fgf + info + fgf;
				fileUrl = fileUrl+info+"/";
			}
			// 创建项目文件夹
			String projectName = info;
			if(info.indexOf("-") != -1) {
				projectName = info.substring(0, info.lastIndexOf("-"));
				filePath = filePath + projectName;
				File projectFile = new File(filePath);
				if (!projectFile.exists()) {
					projectFile.mkdir();
				}
			} else {
				filePath = filePath + projectName;
				File projectFile = new File(filePath);
				if (!projectFile.exists()) {
					projectFile.mkdir();
				}
			}
			// 创建目录文件夹
			filePath = filePath + fgf + info;
			fileUrl = fileUrl + projectName + "/" + info;
			File catalogFile = new File(filePath);
			if (!catalogFile.exists()) {
				catalogFile.mkdir();
			}
			
			//创建文件
			String fileallp = filePath + fgf + fname;
			fileUrl = fileUrl+ "/" + fname;
			try {
				imgf = new File(fileallp);
				if(!imgf.exists()) {
					FileImageOutputStream imageOutput = new FileImageOutputStream(
							imgf);
					imageOutput.write(data, 0, data.length);
				}
				
			} catch (Exception ex1) {
				log.error(ex1);
			}
			// 取得该目录uid
			DOService catalSer = DOService
					.getService("archcatalog_browse_by_archcode_caogao");
			List<BOInstance> listcata = catalSer.invokeSelect(info);
			if (listcata != null && listcata.size() > 0) {
				BOInstance cataBi = listcata.get(0);
				String catalog_uid = cataBi.getValue("objuid");
				// 计算已有文件数目
				DOService countFile = DOService
				.getService("archfiles_browse_count");
				List<BOInstance> countList = countFile.invokeSelect(catalog_uid);
				int countNumber = 0;
				if(countList != null && countList.size() > 0) {
					BOInstance countBi = countList.get(0);
					String number = countBi.getValue("number");
					if(number != null) {
						number = number.trim();
						if(number.matches("^\\d+$")) {
							countNumber = Integer.parseInt(number);
						}
					}
				}
				
				// 新增文件记录
				
				//serialno暂时没有使用
				try {
					String uid = (String) UUIDHex.getInstance().generate();
					String fileName = fname;
					String orderno = serialno;
					if (fileName.indexOf(".") != -1) {
						fileName = fileName.substring(0, fileName.indexOf("."));
					}
					if (serialno.matches("^\\d+$")) {
						int number = 1 + countNumber; 
						orderno = number + "";
					}
					DOService insertFile = DOService
							.getService("archfiles_insert_uploadadd");

					insertFile.invokeUpdate(uid, catalog_uid, fileUrl,
							fileName, orderno);
					return 0;
				} catch (ExedoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(imgf.exists()) {
						imgf.delete();
					}
					return -1;
				}
			} else {
				if(imgf.exists()) {
					imgf.delete();
				}
			}

		}
		return -1;

		// ErpScanServiceFactory factory = new ErpScanServiceFactory();
		//
		// try {
		// UploadPicServiceImpl service =
		// factory.createUploadPicServiceForServer();
		//
		// PicObject pic = new PicObject();
		//
		// // for test data
		// //pic = this.getInfo("1001|100000");
		//
		// log.info("info from client: "+info);
		//
		// pic = this.getInfo(info);
		//
		// if(pic == null){
		// return -1;
		// }
		//
		// pic.setPath(fname);
		// pic.setSerialno(serialno);
		//
		// // judge the pic type, invoice or expense, asset, ledger
		// if(pic.getSerialno().startsWith(Global.getInvoicePrefix()))
		// {
		// pic.setSerialno(serialno.substring(0, serialno.length() - 1));
		// String ntype = serialno.substring(serialno.length() - 1,
		// serialno.length());
		// if (ntype.equals("A")){
		// //预付款
		// log.info("upload APA pics");
		// pic.setType(IConstants.INVOICE_TYPE_ADVANCE);
		// }else if (ntype.equals("B")){
		// //核销预付款
		// log.info("upload APB pics");
		// pic.setType(IConstants.INVOICE_TYPE_CANCEL);
		// }else if (ntype.equals("C")){
		// //正常付款
		// log.info("upload APC pics");
		// pic.setType(IConstants.INVOICE_TYPE_NATURAL);
		// }else{
		// //其它
		// log.info("upload AP pics");
		// pic.setType(IConstants.PIC_TYPE_INVOICE);
		// }
		// }
		// else if(pic.getSerialno().startsWith(Global.getAssetPrefix()))
		// {
		// log.info("upload FA pics");
		// pic.setType(IConstants.PIC_TYPE_ASSET);
		// }
		// else if(pic.getSerialno().startsWith(Global.getLedgerPrefix()))
		// {
		// log.info("upload GL pics");
		// pic.setType(IConstants.PIC_TYPE_LEDGER);
		// }
		// // else if(pic.getSerialno().startsWith(Global.getContractPrefix())){
		// // //添加合同类型
		// // log.info("upload HT pics");
		// // pic.setType(IConstants.PIC_TYPE_CONTRACT);
		// //
		// //
		// // }
		// else if(pic.getSerialno().startsWith(Global.getContractFzPrefix())){
		// //合同子项：房租/物业
		// log.info("upload FZ pics");
		// pic.setType(IConstants.PIC_TYPE_FZCONTRACT);
		//
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractZxPrefix())){
		// //合同子项：装修
		// log.info("upload ZX pics");
		// pic.setType(IConstants.PIC_TYPE_ZXCONTRACT);
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractGxPrefix())){
		// //合同子项：广告宣传
		// log.info("upload GX pics");
		// pic.setType(IConstants.PIC_TYPE_GXCONTRACT);
		//
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractCgPrefix())){
		// //合同子项：采购
		// log.info("upload CG pics");
		// pic.setType(IConstants.PIC_TYPE_CGCONTRACT);
		//
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractKcPrefix())){
		// //合同子项：科技采购
		// log.info("upload KC pics");
		// pic.setType(IConstants.PIC_TYPE_KCCONTRACT);
		//
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractFwPrefix())){
		// //合同子项：服务
		// log.info("upload FW pics");
		// pic.setType(IConstants.PIC_TYPE_FWCONTRACT);
		//
		//
		// }else if(pic.getSerialno().startsWith(Global.getContractQtPrefix())){
		// //合同子项：其他
		// log.info("upload QT pics");
		// pic.setType(IConstants.PIC_TYPE_QTCONTRACT);
		//
		//
		// }else //if(pic.getSerialno().startsWith(Global.getExpensePrefix()))
		// {
		// log.info("upload IE pics");
		// pic.setType(IConstants.PIC_TYPE_EXPENSE);
		// }
		//
		// int i = isExist(serialno);
		//
		// // write to db & hd
		// boolean ok = service.uploadPicZip(pic, data, i);
		//
		// log.debug("upload pic from client: "+ok);
		//
		// if(!ok){
		// log.error("upload zip file error");
		// return -1;
		// }
		//
		// } catch (ClassNotFoundException e) {
		//
		// //e.printStackTrace();
		//
		// log.error("class not found");
		//
		// return -1;
		// } catch (IllegalAccessException e) {
		//
		// //e.printStackTrace();
		//
		// log.error("Illegal access");
		//
		// return -1;
		// } catch (InstantiationException e) {
		//
		// //e.printStackTrace();
		//
		// log.error("Istantiation exception");
		//
		// return -1;
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		//
		// return -1;
		// }
		// log.info("upload pics ok! serialno : " + serialno);
		// return 0;
	}

}
