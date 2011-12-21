package com.exedosoft.plat.pdf;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.MultiColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

//http://blog.csdn.net/zerolsy/archive/2008/02/18/2103480.aspx

public class DOPDF3 {

	public final static Font createChineseFont(int size, int style,boolean isBold) {
		


		Font chineseFont = null;
		try {
			
			BaseFont bf = null;
			if(isBold){ 
				bf = BaseFont.createFont("STSong-Light,Bold", "UniGB-UCS2-H", BaseFont.EMBEDDED); 
			}else{
				bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED); 
			}
			chineseFont = new Font(bf, size, style);
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

		Document document = new Document(PageSize.A4.rotate(), 30, 30, 30, 30);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:\\ITextTest3.pdf"));
		writer.setStrictImageSequence(true);
		// ///////////////为了处理Column

		// //////打开文档
		document.open();

		MultiColumnText mct = new MultiColumnText();

		// set up 3 even columns with 10pt space between
		mct.addRegularColumns(document.left(), document.right(), 10f, 2);
		
///////加入头部分
		Image jpg = Image.getInstance("c:\\lh.jpg");
		jpg.setAlignment(Image.LEFT);
		mct.addElement(jpg);
		
	
		
		Font theFont = createChineseFont(13,Font.NORMAL,true);
		Paragraph p1 = new Paragraph("第六期", theFont);
		p1.setAlignment(Element.ALIGN_CENTER);
		mct.addElement(p1);
		
		/////////输出空行
		Paragraph pt = new Paragraph("\n", theFont);
		pt.setAlignment(Element.ALIGN_CENTER);
		mct.addElement(pt);

		Paragraph p2 = new Paragraph("中油辽河油田公司勘查处                    二零零八年二月二十二日", theFont);
		p2.setAlignment(Element.ALIGN_LEFT);
		mct.addElement(p2);
		
		//////////再输出空行
		mct.addElement(pt);
		
        PdfContentByte cb = writer.getDirectContent();
        
        ///////////划线
	    cb.setLineWidth(1);
	    ////填充划线颜色
	    cb.setColorStroke(Color.RED);
	      

	    cb.moveTo(document.left(),  document.top()-150);
	      
	    cb.lineTo((document.right() - document.left())/2,  document.top() -150);
	      
	    cb.stroke();
			
		

		// Write some iText poems
		for (int i = 0; i < 30; i++) {
			mct.addElement(new Paragraph(String.valueOf(i + 1)));
			mct.addElement(newPara(randomWord(noun), Element.ALIGN_CENTER,
					Font.BOLDITALIC));
			for (int j = 0; j < 4; j++) {
				mct.addElement(newPara(poemLine(), Element.ALIGN_LEFT,
						Font.NORMAL));
			}
			mct.addElement(newPara(randomWord(adverb), Element.ALIGN_LEFT,
					Font.NORMAL));
			mct.addElement(newPara("\n\n", Element.ALIGN_LEFT, Font.NORMAL));
		}
	

		
		////////////盖章
		
		mct.addElement(Chunk.NEWLINE);
		mct.addElement(Chunk.NEWLINE);
		mct.addElement(Chunk.NEWLINE);
		mct.addElement(Chunk.NEWLINE);

		
//////	/加入头部分
	

		Image zhang = Image.getInstance("c:\\zhang.jpg");
		zhang.setAlignment(Image.UNDERLYING);
		
	    Chunk cc = new Chunk(zhang, 0, -5);
	    mct.addElement(cc);
	    
	    		
		Phrase qianzhang = new Phrase("二零零八年二月二十二日", theFont);
//		qianzhang.add(cc);
		
		mct.addElement(qianzhang);
		
		
		
		document.add(mct);
			
		
		

		///////////两列输出完毕
		
		////////变为普通情况输出
		
		document.add(Chunk.NEWLINE);
	
		Image jpg2 = Image.getInstance("c:\\mgjt.jpg");
		jpg2.setAlignment(Image.ALIGN_CENTER);
		document.add(jpg2);
		
		
		
		
		document.add(Chunk.NEXTPAGE);

	
		
		Font theFontSign = createChineseFont(10,Font.NORMAL,false);

		Phrase ps1 = new Phrase("\n\t                                               编图",theFontSign);
//	
		Image ss1 = Image.getInstance("c:\\ss1.jpg");
		ss1.setAlignment(Image.ALIGN_CENTER);
	    Chunk ck = new Chunk(ss1, 0, -5);
	    ps1.add(ck);
	    
	    ps1.add("        制表：");
	    ps1.add(ck);
	    
		document.add(ps1);
		
	
//
		document.close();
		
		System.out.println("Out Finish!");

	}

	private static Element newPara(String text, int alignment, int type) {
		Font font =  FontFactory.getFont("Helvetica", 10, type, Color.BLACK);
		Paragraph p = new Paragraph(text, font);
		p.setAlignment(alignment);
		p.setLeading(font.getSize() * 1.2f);
		return p;
	}

	static Random rand = new Random();

	static String[] verb = { "flows", "draws", "renders", "throws exception",
			"runs", "crashes", "downloads", "usurps", "vexes", "whispers",
			"boils", "capitulates", "crashes", "craves", "looks", "defies",
			"defers", "defines", "envelops", "entombs", "falls", "fails",
			"halts", "appears", "nags", "overflows", "burns", "dies", "writes",
			"flushes" };

	static String[] noun = { "ColumnText", "paragraph", "phrase", "chunk",
			"PdfContentByte", "PdfPTable", "iText", "color",
			"vertical alignment", "horizontal alignment", "PdfWriter",
			"ListItem", "PdfStamper", "PDF", "HTML", "XML", "column", "font",
			"table", "FDF", "field", "NullPointerException", "CJK font" };

	static String[] adjective = { "foul", "broken", "gray", "slow",
			"beautiful", "throbbing", "sharp", "stout", "soundless", "neat",
			"swift", "uniform", "upright", "vibrant", "dingy", "vestigal",
			"messy", "sloppy", "baleful", "boastful", "dark", "capricious",
			"concrete", "deliberate", "sharp", "drunken", "undisciplined",
			"perfect", "bloated" };

	static String[] adverb = { "randomly", "quickly", "triumphantly",
			"suggestively", "slowly", "angrily", "uncomfortably", "finally",
			"unexpectedly", "hysterically", "thinly", "dryly", "blazingly",
			"terribly", "bleakly", "irritably", "dazzlingly", "expectantly",
			"impersonally", "abruptly", "awfully", "caressingly", "completely",
			"undesirably", "drolly", "hypocritically", "blankly", "dimly" };

	private static String randomWord(String[] type) {
		return type[rand.nextInt(type.length)];
	}

	/**
	 * Generates a random poem line.
	 * 
	 * @return a poem that is generated with some keywords.
	 */
	public static String poemLine() {
		StringBuffer results = new StringBuffer(150);
		results.append(randomWord(adjective));
		results.append(' ');
		results.append(randomWord(noun));
		results.append(' ');
		results.append(randomWord(verb));
		results.append(' ');
		results.append(randomWord(adverb));
		results.append(", ");
		return results.toString();
	}

}
