package com.kn.serviceimpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.kn.service.EmployeeXmlDataService;

@Service
public class EmployeeXmlDataServiceImpl implements EmployeeXmlDataService {

	private Document document;
	
	@Override
	public Document readXmlFile(String filePath) throws IOException {
		// TODO Auto-generated method stub
 		File file = new File(filePath);
		FileInputStream fis = null;
		if (file.exists()) {
			fis = new FileInputStream(file);
		} else
			throw new IOException("The " +filePath +" do not exist");
		 this.document=Jsoup.parse(fis, "UTF-8", "", Parser.xmlParser());
		 System.out.println("reading file completed ..");
		 return document;
	}

	@Override
	public Document modifyXmlElement(Element xlmElement, Document document, boolean insert) {
		
		return insert ? insertEmployeeElement(xlmElement, document) : removeEmployeeElement(xlmElement, document);

	}

	private Document insertEmployeeElement(Element xlmElement, Document document) {

		System.out.println("before Insertion size of Elements: " + document.select("employee").size());
		System.out.println("inserting new Element : " + xlmElement);
		document.select("employees").first().appendChild(xlmElement);
		System.out.println("after insertion size of Elements : " + document.select("employee").size());
		return document;
	}

	private Document removeEmployeeElement(Element xlmElement, Document document) {
		Elements elementList = document.select("employee");
		System.out.println("size is " + elementList.size());
		for (Element e : elementList) {
			System.out.println(e.hasSameValue(xlmElement));
			if (e.hasSameValue(xlmElement)) {
				e.remove();
			}
		}
		return document;
	}

	public Element getElement(String xmlString) {
		Document document = Jsoup.parse(xmlString, "", Parser.xmlParser());
		return document.select("employee").first();
	}

	
	@Override
	public String saveXmlFile(Document document, String fileName) {
		// TODO Auto-generated method stub
		BufferedWriter htmlWriter = null;
		try {
 			File file = new File(fileName);				 
			htmlWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			document.outputSettings().indentAmount(0).prettyPrint(false);
			htmlWriter.write(document.toString());
			htmlWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return "file saved successfully";
	}
}