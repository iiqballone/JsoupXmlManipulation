package com.kn.serviceimpl;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kn.model.Employee;
import com.kn.utill.Constants;

@Service
public class EmployeeXmlService {

	@Autowired
	private EmployeeXmlDataServiceImpl employeeXmlDataServiceImpl;

	public Document processXml(Employee emp) {

		Document document = null;
		try {
			document = employeeXmlDataServiceImpl.readXmlFile(Constants.FILENAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element element = employeeXmlDataServiceImpl.getElement(emp.getXmlString());
		employeeXmlDataServiceImpl.modifyXmlElement(element, document,
				emp.getInsert());
		employeeXmlDataServiceImpl.saveXmlFile(document, Constants.FILENAME);
		return document;
	}

}
