package com.kn.service;
import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface EmployeeXmlDataService {

	public Document readXmlFile(String filePath) throws IOException;

	public Document modifyXmlElement(Element xlmElement, Document document, boolean isInsert);

	public String saveXmlFile(Document document, String fileName);
	
}
