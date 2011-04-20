package com.exedosoft.plat.util.xml;

import java.io.IOException;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DOMXmlUtil {
	
	
	
	public static DOMXmlUtil getInstance(){
		return new DOMXmlUtil();
	}

	public DocumentBuilder getDocumentBuilder() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		try {


			DocumentBuilder db = dbf.newDocumentBuilder();
			


			return db;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public  Document getDocument(String aFileName) throws SAXException,
			IOException {

		DocumentBuilder db = getDocumentBuilder();
	

		return db.parse("file:///" + aFileName);

	}

	public  Document getDocument(InputStream is) throws SAXException,
			IOException {

		DocumentBuilder db = getDocumentBuilder();
		return db.parse(is);

	}

	public  void transformer(Node aNode, String aFileName) {

		Transformer trans = getTransformer();
		DOMSource source = new DOMSource(aNode);
		StreamResult result = new StreamResult(aFileName);
		try {
			trans.transform(source, result);
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}

	}

	public  Transformer getTransformer() {

		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer trans = factory.newTransformer();
			return trans;
		} catch (TransformerConfigurationException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
