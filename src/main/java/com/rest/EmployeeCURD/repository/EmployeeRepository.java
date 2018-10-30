package com.rest.EmployeeCURD.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest.EmployeeCURD.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	    List<Employee> findBySerialNo(String serialNo);
	    
}
