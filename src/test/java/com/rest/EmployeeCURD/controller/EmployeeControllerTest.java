package com.rest.EmployeeCURD.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rest.EmployeeCURD.model.Department;
import com.rest.EmployeeCURD.model.Employee;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

	@Autowired
	private TestRestTemplate template;

	@Before
	public void addDepartment() {

		HttpEntity<Object> department = getHttpEntity("{\"name\":\"Agriculture\" }");
		ResponseEntity<Department> departmentResponse = template.postForEntity("/addDepartment", department,
				Department.class);

	}

	@Test
	public void testEmployeeShouldBeInserted() {

		HttpEntity<Object> employee = getHttpEntity(
				"{\"firstName\":\"udaya\",\"lastName\":\"wara\",\"department\":{\"id\":1},\"serialNo\":\"001\" }");
		ResponseEntity<Employee> response = template.postForEntity("/addEmployee", employee, Employee.class);

		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody().getSerialNo(), "001");
	}

	@Test
	public void testEmployeeShouldBeUpdated() {

		HttpEntity<Object> newEmployee = getHttpEntity(
				"{\"firstName\":\"udayanga\",\"lastName\":\"warathanna\",\"department\":{\"id\":1},\"serialNo\":\"002\" }");
		ResponseEntity<Employee> newEmployeeResponse = template.postForEntity("/addEmployee", newEmployee,
				Employee.class);

		int empId = newEmployeeResponse.getBody().getId();

		HttpEntity<Object> updatedEmployee = getHttpEntity(
				"{\"firstName\":\"udayanga updated\",\"lastName\":\"warathanna\",\"department\":{\"id\":1},\"serialNo\":\"002\" }");
		template.put("/updateEmployee", updatedEmployee);

		ResponseEntity<Employee> getUpdatedEmployee = template.getForEntity("/getEmployee/" + empId + "",
				Employee.class);

		assertEquals(getUpdatedEmployee.getBody().getFirstName(), "udayanga updated");
		assertEquals(getUpdatedEmployee.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testEmployeeShouldBeDeleted() {

		HttpEntity<Object> newEmployee = getHttpEntity(
				"{\"firstName\":\"jhon\",\"lastName\":\"doy\",\"department\":{\"id\":1},\"serialNo\":\"003\" }");
		ResponseEntity<Employee> newEmployeeResponse = template.postForEntity("/addEmployee", newEmployee,
				Employee.class);

		int empId = newEmployeeResponse.getBody().getId();

		template.delete("/deleteEmployee/" + empId + "");

		ResponseEntity<Employee> findEmployeAlreadyDeleted = template.getForEntity("/getEmployee/" + empId + "",
				Employee.class);

		assertNull(findEmployeAlreadyDeleted.getBody().getFirstName());
		assertNull(findEmployeAlreadyDeleted.getBody().getLastName());
		assertNull(findEmployeAlreadyDeleted.getBody().getSerialNo());

	}

	@Test
	public void testEmployeeShouldBeRead() {

		HttpEntity<Object> newEmployee = getHttpEntity(
				"{\"firstName\":\"jhon read \",\"lastName\":\"doy read\",\"department\":{\"id\":1},\"serialNo\":\"004\" }");
		ResponseEntity<Employee> newEmployeeResponse = template.postForEntity("/addEmployee", newEmployee,
				Employee.class);

		int empId = newEmployeeResponse.getBody().getId();

		ResponseEntity<Employee> findEmployeInserted = template.getForEntity("/getEmployee/" + empId + "",
				Employee.class);

		assertEquals(findEmployeInserted.getBody().getFirstName(), newEmployeeResponse.getBody().getFirstName());
		assertEquals(findEmployeInserted.getBody().getLastName(), newEmployeeResponse.getBody().getLastName());
		assertEquals(findEmployeInserted.getBody().getSerialNo(), newEmployeeResponse.getBody().getSerialNo());

	}

	private HttpEntity<Object> getHttpEntity(Object body) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);

	}

}
