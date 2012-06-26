package com.exedosoft.plat.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Attribute;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.io.XMLWriter;
import java.io.*;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class I18n {

	private static Map<String, String> zhL10ns = new HashMap<String, String>();
	private static Map<String, String> enL10ns = new HashMap<String, String>();
	private static I18n i18n = new I18n();

	private I18n() {
		parserXML();
		System.out.println(i18n);
	}

	public static I18n instance() {
		return i18n;
	}

	public String get(String key) {

		if ("en".equals(DOGlobals.getValue("lang.local"))) {

			String value = enL10ns.get(key);
			if (value != null) {
				return value;
			}
		} else {
			String value = zhL10ns.get(key);
			if (value != null) {
				return value;
			}
		}

		return key;

	}

	public static void parserXML() {

		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(I18n.class
					.getResourceAsStream("/i18n.xml"));

			Element rootElement = document.getRootElement();

			for (Iterator it = rootElement.iterator(); it.hasNext();) {
				Node node = (Node) it.next();
				if (node instanceof Element) {
					Element elem = (Element) node;
					Attribute attrKey = elem.attribute("name");
					for (int i = 0; i < elem.nodeCount(); i++) {
						Node childNode = elem.node(i);
						if (childNode instanceof Element) {

							Element elemChild = (Element) childNode;
							Attribute attrLocal = elemChild.attribute("local");
							if ("zh".equals(attrLocal.getValue())) {
								zhL10ns.put(attrKey.getValue(),
										elemChild.node(0).getText());
							}
							if ("en".equals(attrLocal.getValue())) {
								enL10ns.put(attrKey.getValue(),
										elemChild.node(0).getText());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		
		
		System.out.println(I18n.instance().get("SomeStep1"));

//		try {
//			SAXReader saxReader = new SAXReader();
//			Document document = saxReader.read(I18n.class
//					.getResourceAsStream("/i18n.xml"));
//
//			Element rootElement = document.getRootElement();
//
//			for (Iterator it = rootElement.iterator(); it.hasNext();) {
//				Node node = (Node) it.next();
//				if (node instanceof Element) {
//					Element elem = (Element) node;
//					Attribute attrKey = elem.attribute("name");
//					for (int i = 0; i < elem.nodeCount(); i++) {
//						Node childNode = elem.node(i);
//						if (childNode instanceof Element) {
//
//							Element elemChild = (Element) childNode;
//							Attribute attrLocal = elemChild.attribute("local");
//							if ("zh".equals(attrLocal.getValue())) {
//								zhL10ns.put(attrKey.getValue(),
//										elemChild.node(0).getText());
//							}
//						}
//					}
//				}
//			}
//
//			System.out.println(zhL10ns);
			// List list = document.selectNodes("//key/@name");
			// Iterator iter = list.iterator();
			// while (iter.hasNext()) {
			// Attribute attribute = (Attribute) iter.next();
			// System.out.println(attribute.getValue());
			//
			// }

			// list = document.selectNodes("//article/@date");
			// iter = list.iterator();
			// while (iter.hasNext()) {
			// Attribute attribute = (Attribute) iter.next();
			// if (attribute.getValue().equals("December-2001"))
			// attribute.setValue("October-2002");
			// }
			// list = document.selectNodes("//article");
			// iter = list.iterator();
			// while (iter.hasNext()) {
			// Element element = (Element) iter.next();
			// Iterator iterator = element.elementIterator("title");
			// while (iterator.hasNext()) {
			// Element titleElement = (Element) iterator.next();
			// if (titleElement.getText().equals(
			// "Java configuration with XML      Schema"))
			// titleElement
			// .setText("Create flexible and extensible XML schema");
			// }
			// }
			// list = document.selectNodes("//article/author");
			// iter = list.iterator();
			// while (iter.hasNext()) {
			// Element element = (Element) iter.next();
			// Iterator iterator = element.elementIterator("firstname");
			// while (iterator.hasNext()) {
			// Element firstNameElement = (Element) iterator.next();
			// if (firstNameElement.getText().equals("Marcello"))
			// firstNameElement.setText("Ayesha");
			// }
			// }
			// list = document.selectNodes("//article/author");
			// iter = list.iterator();
			// while (iter.hasNext()) {
			// Element element = (Element) iter.next();
			// Iterator iterator = element.elementIterator("lastname");
			// while (iterator.hasNext()) {
			// Element lastNameElement = (Element) iterator.next();
			// if (lastNameElement.getText().equals("Vitaletti"))
			// lastNameElement.setText("Malik");
			// }
			// }
			// XMLWriter output = new XMLWriter(new FileWriter(new File(
			// "c:/catalog/catalog-modified.xml")));
			// output.write(document);
			// output.close();
//		}
//
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

}
