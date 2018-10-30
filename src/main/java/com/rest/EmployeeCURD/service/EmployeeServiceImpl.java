package com.rest.EmployeeCURD.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.EmployeeCURD.model.Employee;
import com.rest.EmployeeCURD.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository; 
	
	public synchronized boolean addEmployee(Employee employee){
	  
	List<Employee> list = employeeRepository.findBySerialNo(employee.getSerialNo()); 	
    
	if (list.size() > 0) {
    	   return false;
       } else {
    	   employeeRepository.save(employee);
    	   return true;
       }
	}
	
	public void updateEmployee(Employee employee) {
		
		List<Employee> list = employeeRepository.findBySerialNo(employee.getSerialNo()); 	
	    
		if (list.size() > 0)  {
			
			Employee emp = list.get(0);
			employee.setId(emp.getId());
			employeeRepository.save(employee);
	     }
	}
	
	public void deleteEmployee(int id) {
		employeeRepository.delete(getEmployeeById(id));
	}
	
	public Employee getEmployeeById(Integer id) {
		Employee obj = employeeRepository.findById(id).get();
		return obj;
	}	
	
	public List<Employee> getAllEmployees(){
		List<Employee> list = new ArrayList<Employee>();
		employeeRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	
	
	
	
}
