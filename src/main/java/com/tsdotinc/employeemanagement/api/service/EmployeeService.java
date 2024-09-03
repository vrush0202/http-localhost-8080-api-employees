package com.tsdotinc.employeemanagement.api.service;

import com.tsdotinc.employeemanagement.api.exception.EmployeeNotFoundException;
import com.tsdotinc.employeemanagement.api.model.Employee;
import com.tsdotinc.employeemanagement.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
	
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
    	logger.info("Fetching employee with ID: {}", id);
        // your logic here
        logger.debug("Debugging some details here...");
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException
                        ("Employee by id "+id+"was not found"));
          }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee existingEmployee = this.employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException
                        ("Employee by id "+id+"was not found"));
        this.employeeRepository.delete(existingEmployee);

    }
}
