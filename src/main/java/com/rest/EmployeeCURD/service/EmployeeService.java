package com.rest.EmployeeCURD.service;

import java.util.List;
import com.rest.EmployeeCURD.model.Employee;

public interface EmployeeService {


	 boolean addEmployee(Employee employee);
	 void updateEmployee(Employee employee);
     void deleteEmployee(int employeeId);
     List<Employee> getAllEmployees();
     Employee getEmployeeById(Integer id);
	
}
