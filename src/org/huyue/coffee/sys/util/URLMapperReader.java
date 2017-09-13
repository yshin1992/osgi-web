package org.huyue.coffee.sys.util;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.*;  
import org.w3c.dom.*;  
import org.xml.sax.*;

import org.huyue.coffee.sys.URLMapper;

public class URLMapperReader {
	
	public List<URLMapper> readURLs(String xmlFile) throws ParserConfigurationException, SAXException, IOException{
		List<URLMapper> urlList = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document doc = db.parse(xmlFile);
		
		NodeList actionList = doc.getElementsByTagName("action");
		
		System.out.println("共加载 " + actionList.getLength() +"个Action");
		
		for(int i = 0;i<actionList.getLength();i++){
			Node actionNode = actionList.item(i);
			Element actionElem = (Element)actionNode;
			
		}
		
		return urlList;
	}
	
}
