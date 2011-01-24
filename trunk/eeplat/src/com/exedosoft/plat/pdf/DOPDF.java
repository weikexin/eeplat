package com.exedosoft.plat.pdf;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

//http://blog.csdn.net/zerolsy/archive/2008/02/18/2103480.aspx

public class DOPDF {

	public final static Font createChineseFont(int size, int style) {

		Font chineseFont = null;
		try {
			chineseFont = new Font(BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED), size, style);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chineseFont;
	}

	public static void main(String[] args) throws DocumentException,
			MalformedURLException, IOException {

		Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		

		document.addTitle("Hello world");
		

		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:\\ITextTest.pdf"));

		/////////////////为了处理Column
		   //中间边距
        float gutter = 20;
        int numColumns = 2;
        
        
        float fullWidth = document.right() - document.left();
        float columnWidth = (fullWidth - (numColumns - 1) * gutter) / numColumns;
        
        float allColumns[] = new float[numColumns]; // left
        
        for (int k = 0; k < numColumns; ++k) {
            allColumns[k] = document.left() + (columnWidth + gutter) * k;
        }

		/////////////////为了处理Column
        
        ////////打开文档
		document.open();

		////////////获取内容
        PdfContentByte cb = writer.getDirectContent();
        
//        ColumnText ct = new ColumnText(cb);
//        ct.setSimpleColumn(fullTitle, document.left(), 0, document.right(), document.top(), 24, Element.ALIGN_JUSTIFIED);
//        ct.go();



		Image jpg = Image.getInstance("c:\\lh.jpg");
		jpg.setAlignment(Image.LEFT);
		document.add(jpg);
//		
//		jpg.setAbsolutePosition(171, 250);
//        document.add(jpg);
//        jpg.setAbsolutePosition(342, 500);
//        document.add(jpg);



		// ///建立标题
		Paragraph title1 = new Paragraph("Chapter 1", FontFactory.getFont(
				FontFactory.HELVETICA, 18, Font.BOLDITALIC,
				new Color(0, 0, 255)));
		

		// ////////标题作为第一章节的的Title
		Chapter chapter1 = new Chapter(title1, 1);
		chapter1.setNumberDepth(0);

	//	document.add(chapter1);

		// //段落1
		document.add(new Paragraph("First page of the document."));

		// //段落2
		document
				.add(new Paragraph(
						"Some more text on the first page with different color and font type.",
						FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,
								new Color(255, 150, 200))));

		//		
		// Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
		// FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new
		// Color(255, 0, 0)));
		// Section section1 = chapter1.addSection(title11);
		//		
		//		
		// Paragraph someSectionText = new Paragraph("This text comes as part of
		// section 1 of chapter 1.");
		// section1.add(someSectionText);
		//		
		//		
		// someSectionText = new Paragraph("Following is a 3 X 2 table.");
		// section1.add(someSectionText);

		document.close();

	}
	

}

	
