package com.employee.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	public ResponseEntity<Employee> getEmployeeById(Long id)throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(id).orElseThrow( () -> 
			new ResourceNotFoundException("Employee not found for the id:"+id));
		return ResponseEntity.ok().body(employee);		
		}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	public ResponseEntity<Employee> updateEmployeeById(Long id,Employee employeeDetails)throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee not found for the id" +id));
		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
	    employee.setFirstName(employeeDetails.getFirstName());
	    final Employee updateEmployee = employeeRepository.save(employee);
	    return ResponseEntity.ok().body(updateEmployee);
		
	}
	public ResponseEntity<HttpStatus> deleteEmployeeById(Long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee not found for the id" +id));
		employeeRepository.deleteById(id);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	

}
