package com.coddingshuttle.OneToOne.services;


import com.coddingshuttle.OneToOne.entities.EmployeeEntity;
import com.coddingshuttle.OneToOne.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity createNewEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

}
