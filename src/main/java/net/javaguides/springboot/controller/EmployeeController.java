package net.javaguides.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	// build create employee REST API
	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// build get all employees REST API
	@GetMapping("/list-all-employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	// build get employee by id REST API
	@GetMapping("/get-employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	// build update employee REST API
	@PutMapping("/update-employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
												  ,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	// build delete employee REST API
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		
		// delete employee from DB
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
	
}