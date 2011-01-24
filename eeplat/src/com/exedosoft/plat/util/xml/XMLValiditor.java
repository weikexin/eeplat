package com.exedosoft.plat.util.xml;

public class XMLValiditor {
	
////	 1. Lookup a factory for the W3C XML Schema language
//    SchemaFactory factory = 
//        SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//    
//    // 2. Compile the schema. 
//    // Here the schema is loaded from a java.io.File, but you could use 
//    // a java.net.URL or a javax.xml.transform.Source instead.
//    File schemaLocation = new File("/opt/xml/docbook/xsd/docbook.xsd");
//    Schema schema = factory.newSchema(schemaLocation);
//
//    // 3. Get a validator from the schema.
//    Validator validator = schema.newValidator();
//    
//    // 4. Parse the document you want to check.
//    Source source = new StreamSource(args[0]);
//    
//    // 5. Check the document
//    try {
//        validator.validate(source);
//        System.out.println(args[0] + " is valid.");
//    }
//    catch (SAXException ex) {
//        System.out.println(args[0] + " is not valid because ");
//        System.out.println(ex.getMessage());
//    }  
//
//如果要使用xml自身指定的xsd进行校验，则使用下面的方法：
//SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
//Schema schema = factory.newSchema();
//这种方法仅适用于 XSD。
	
//	
//	
//	<?xml version="1.1" encoding="UTF-8" standalone="yes"?>
//	　　
//	　　根据W3C Schema来校验XML文档(WXS)
//	　　
//	　　XMLSchema 是XML文档的另外一种文法描述。XMLSchema非常流行市因为它和XML文档使用同样的语法并且提供了丰富的定义校验限制的特性。如果一个XML文档用"schemaLocation" 和"noNamespaceSchemaLocation"指向了一个schema，结下来你想启用根据XMLSchema校验文档这个特性，你还要做如下的步骤：
//	　　
//	　　1.和上面说的一样，调用SAXParserFactory o或DocumentBuilderFactory的setValidating函数来启用validation这个特性。 bitsCN.nET中国网管博客 
//	　　
//	　　2.把属性 "http://java.sun.com/xml/jaxp/properties/schemaLanguage" 值设为 "http://www.w3.org/2001/XMLSchema"
//	　　
//	　　注意，这种情况下，即使XML文档有DOCTYPE声明，处理器仍不会用DTD来校验这个文档。但是和前面提到的一样，为了任何一个entity reference是被正确扩展的，这个DTD还是会被装载的，
//	　　
//	　　既然"schemaLocation" 和"noNamespaceSchemaLocation"仅仅是提示，所以可以使用属性"http://java.sun.com/xml/jaxp/properties/schemaSource"从外部提供schemas来覆盖这些提示。
//	　　
//	　　对于这个属性，一些可以接受值是：
//	　　
//	　　·是一个代表schema的URL地址的字符串。
//	　　
//	　　·java.io.InputStream with the contents of the schema
//	　　
//	　　·org.xml.sax.InputSource
//	　　
//	　　·java.io.File
//	　　
//	　　·一个 java.lang.Object 的数组，数组内容是上面所提到三类中的一个。
//	　　
//	　　例如:
//	　　
//	　　SAXParserFactory spfactory = SAXParserFactory.newInstance();
//	　　spfactory.setNamespaceAware(true);
//	　　//turn the validation on
//	　　spfactory.setValidating(true);
//	　　//set the validation to be against WXS 
//	bitsCN.nET中国网管博客
//
//
//	　　saxparser.setProperty("http://java.sun.com/xml/jaxp/properties/
//	　　schemaLanguage", "http://www.w3.org/2001/XMLSchema");
//	　　//set the schema against which the validation is to be done
//	　　saxparser.setProperty("http://java.sun.com/xml/jaxp/properties/
//	　　schemaSource", new File("myschema.xsd"));



}
