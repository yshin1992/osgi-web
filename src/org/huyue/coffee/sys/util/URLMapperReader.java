package org.huyue.coffee.sys.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.huyue.coffee.sys.URLMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class URLMapperReader {
	
	public static Map<String,URLMapper> readURLs(InputSource xmlFile) throws ParserConfigurationException, SAXException, IOException{
		Map<String,URLMapper> urlMapper = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		
		
		Document doc = db.parse(xmlFile);
		
		NodeList actionList = doc.getElementsByTagName("action");
		
		System.out.println("共加载 " + actionList.getLength() +"个Action");
		if(actionList.getLength() > 0){
			urlMapper = new HashMap<String,URLMapper>(actionList.getLength());
		}else{
			return null;
		}
		
		for(int i = 0;i<actionList.getLength();i++){
			Node actionNode = actionList.item(i);
			Element actionElem = (Element)actionNode;
			
			String idAttr = actionElem.getAttribute("id");
			String classAttr = actionElem.getAttribute("class");
			String methodAttr = actionElem.getAttribute("method");
			urlMapper.put(idAttr, new URLMapper(idAttr,classAttr,methodAttr));
		}
		
		return urlMapper;
	}
	
}
