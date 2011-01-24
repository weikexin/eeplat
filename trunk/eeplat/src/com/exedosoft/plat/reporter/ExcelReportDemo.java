package com.exedosoft.plat.reporter;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exedosoft.plat.bo.BOInstance;
import com.exedosoft.plat.bo.DOService;

import net.sf.jxls.transformer.XLSTransformer;

import com.exedosoft.plat.util.DOGlobals;

public class ExcelReportDemo {


	
	private  static Map getEmpolyee( String name, int age, double payment, double bonus){
		
		Map employee = new HashMap();
		employee.put("name", name);
		employee.put("payment", payment);
		employee.put("age", age);
		employee.put("bonus", bonus);
		
		return employee;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String aFolder = "test";
		try{
			aFolder = DOGlobals.getInstance().getSessoinContext().getUser().getName();
		}catch(Exception  e){
			
		}
		
		String destFileName = DOGlobals.UPLOAD_TEMP + "/"  + aFolder ;
		File aFile = new File(destFileName);
		aFile.mkdir();
		destFileName = destFileName  + "/basictags_output.xls"; 
		
		
		URL url = DOGlobals.class.getResource("/globals.xml");
		String fullFilePath = url.getPath();
		String prefix = fullFilePath.substring(0, fullFilePath.toLowerCase()
				.indexOf("web-inf")+7);
		
		String templateFileName = prefix + "/xls/basictags.xls";
//		String templateFileName = prefix + "/xls/simple.xls";
		
        List departments = new ArrayList();
        Map department = new HashMap();
        
        Map chief = getEmpolyee("Derek", 35, 3000, 0.30);
        
        department.put("name","IT");
        department.put("chief",chief);
        
        List emps = new ArrayList();
        emps.add(getEmpolyee("Elsa", 28, 1500, 0.15));
        emps.add(getEmpolyee("Oleg", 32, 2300, 0.25));
        emps.add(getEmpolyee("Neil", 34, 2500, 0.00));
        emps.add(getEmpolyee("Maria", 34, 1700, 0.15));
        emps.add(getEmpolyee("John", 35, 2800, 0.20));
        
        department.put("staff", emps);
        departments.add(department);
        
        
        
        ///////////////////////////////sencond department
        Map   department2 = new HashMap();
        List emps2 = new ArrayList();
        chief = getEmpolyee("Betsy", 37, 2200, 0.30);
        
        department2.put("name","HR");
        department2.put("chief",chief);

        emps2.add(getEmpolyee("Olga", 26, 1400, 0.20));
        emps2.add(getEmpolyee("Helen", 30, 2100, 0.10));
        emps2.add(getEmpolyee("Keith", 24, 1800, 0.15));
        emps2.add(getEmpolyee("Cat", 34, 1900, 0.15));
        
        department2.put("staff", emps2);
        departments.add(department2);

        
///////////////////////////third department        
        
        Map department3 = new HashMap();
        List emps3 = new ArrayList();
        chief = getEmpolyee("Wendy", 35, 2900, 0.35);
        department3.put("name","BA");
        department3.put("chief",chief);

        emps3.add(getEmpolyee("Denise", 30, 2400, 0.20));
        emps3.add(getEmpolyee("LeAnn", 32, 2200, 0.15));
        emps3.add(getEmpolyee("Natali", 28, 2600, 0.10));
        emps3.add(getEmpolyee("Martha", 33, 2150, 0.25));
        
        department3.put("staff", emps3);
        departments.add(department3);
        
        Map beans = new HashMap();
        beans.put("departments", departments);
        
        DOService aService = DOService.getService("t_expense_list");
        List<BOInstance> list = aService.invokeSelect();
        
        beans.put("allexpenses", list);
        
        
        XLSTransformer transformer = new XLSTransformer();
        try {
			transformer.transformXLS(templateFileName, beans, destFileName);
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
