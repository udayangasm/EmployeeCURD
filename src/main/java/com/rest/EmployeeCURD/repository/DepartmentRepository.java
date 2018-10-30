package com.rest.EmployeeCURD.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rest.EmployeeCURD.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{

	List<Department> findByName(String name);
    
}
