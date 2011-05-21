package org.textmining.text.extraction;

import java.io.*;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2003
 * Company:
 * @author
 * @version 1.0
 */

class Test
{

  public Test()
  {
  }
  public static void main(String[] args)
  {
    try
    {
    	
    	String file = "C:\\复件 大连投资政务信息化系统需求分析报告20070104.doc";
      WordExtractor extractor = new WordExtractor();
      String s = extractor.extractText(new FileInputStream(file));

      System.out.println(s);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
