package com.rest.EmployeeCURD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rest.EmployeeCURD.model.Department;
import com.rest.EmployeeCURD.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("addDepartment")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {

		boolean flag = departmentService.addDepartment(department);
		if (flag == false) {
			return new ResponseEntity<Department>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Department>(department, HttpStatus.CREATED);

	}

}
