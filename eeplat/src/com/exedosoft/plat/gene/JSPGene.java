package com.exedosoft.plat.gene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.exedosoft.plat.bo.DODataSource;
import com.exedosoft.plat.bo.DOBOProperty;
import com.exedosoft.plat.bo.DOService;
import com.exedosoft.plat.bo.DOServiceRedirect;
import com.exedosoft.plat.DAOUtil;
import com.exedosoft.plat.ExedoException;
import com.exedosoft.plat.Transaction;
import com.exedosoft.plat.ui.DOController;
import com.exedosoft.plat.ui.DOFormModel;
import com.exedosoft.plat.ui.DOGridModel;

import com.exedosoft.plat.ui.DOPaneModel;
import com.exedosoft.plat.ui.jquery.form.TPane;
import com.exedosoft.plat.ui.jquery.form.TService;
import com.exedosoft.plat.ui.jquery.form.TClose;
import com.exedosoft.plat.ui.jquery.form.my97.DatePicker;
import com.exedosoft.plat.ui.jquery.form.DOInputText;
import com.exedosoft.plat.ui.jquery.form.DOTextArea;
import com.exedosoft.plat.ui.jquery.form.DOValueDate;
import com.exedosoft.plat.ui.jquery.form.DOValueSimple;

import com.exedosoft.plat.ui.jquery.grid.GridList;

import com.exedosoft.plat.ui.jquery.pane.ContentPane;
import com.exedosoft.plat.ui.jquery.pane.ContentPaneScroll;
import com.exedosoft.plat.util.StringUtil;

public class JSPGene  {

	private static Log log = LogFactory.getLog(JSPGene.class);

	private final static String SQL_SELECT_PANES = "select * from DO_UI_PaneModel";
	
	private final static String PRIFIX = "E:/eclipse/workspace/eeplat_dbmanager/";



	public static void main(String[] args) {

		List<DOPaneModel> panes = DAOUtil.INSTANCE().select(DOPaneModel.class, SQL_SELECT_PANES);
		int i = 1;
		for(DOPaneModel aPane:panes){
			i++;
			geneFile(aPane.getHtmlCode(),aPane.getName().replace("PM_", ""));
			if(i > 10){
				return;
			}
		}

	}
	
	private static void geneFile(String htmlCode,String fileName) {
		if(htmlCode==null){
			return;
		}
		
		try {

			File indexFile = new File(PRIFIX + "jsp_template/template.jsp");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(indexFile), "utf-8"));
			StringBuffer sb = new StringBuffer();
			while (true) {
				String aLine = in.readLine();
				if (aLine == null) {
					break;
				}
				
				if (aLine.indexOf("#pml_template#") != -1) {
					aLine = aLine.replace("#pml_template#", htmlCode);
				}
				sb.append(aLine).append("\n");// \n\r
			}
			in.close();

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(PRIFIX + "jsp_files/" + fileName + ".jsp"), "utf-8"));
			out.append(sb.toString());
			out.flush();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
