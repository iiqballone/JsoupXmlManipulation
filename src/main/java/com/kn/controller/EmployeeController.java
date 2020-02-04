package com.kn.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kn.model.Employee;
import com.kn.serviceimpl.EmployeeXmlDataServiceImpl;
import com.kn.serviceimpl.EmployeeXmlService;
import com.kn.utill.Constants;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	@Autowired
	private EmployeeXmlService employeeXmlService;

	
	@Autowired
	private EmployeeXmlDataServiceImpl employeeXmlDataServiceImpl;

	@PostMapping("/modify_emp")
	public String modifyEmployee(@RequestBody Employee employee) {
		System.out.println("request to modify employee " + employee.getXmlString());
		return employeeXmlService.processXml(employee).toString();
	}

	@GetMapping("/view_all_emp")
	public String getAllUsers() throws IOException {
		return employeeXmlDataServiceImpl.readXmlFile(Constants.FILENAME).toString();
	}
 

}
