package com.rest.EmployeeCURD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.EmployeeCURD.model.Department;
import com.rest.EmployeeCURD.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository; 
	
	
	public synchronized boolean addDepartment(Department department){
		  
		List<Department> list = departmentRepository.findByName(department.getName()); 	
	    
		if (list.size() > 0) {
	    	   return false;
	       } else {
	    	   departmentRepository.save(department);
	    	   return true;
	       }
		}
}
