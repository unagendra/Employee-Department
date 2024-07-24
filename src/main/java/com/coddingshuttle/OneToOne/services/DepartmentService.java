package com.coddingshuttle.OneToOne.services;


import com.coddingshuttle.OneToOne.entities.DepartmentEntity;
import com.coddingshuttle.OneToOne.entities.EmployeeEntity;
import com.coddingshuttle.OneToOne.repositories.DepartmentRepository;
import com.coddingshuttle.OneToOne.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;


    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        //Retrieve DepartmentId from Repository
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        //Retrieve EmployeeId from Repository
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

      return  departmentEntity.flatMap(department->
            employeeEntity.map(employee->{
                department.setManager(employee);
                return  departmentRepository.save(department);
            })).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
        //Retrieve EmployeeId from Repository
//        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);
//       return employeeEntity.map(employee->{
//            return employee.getManagedDepartment();
//        }).orElse(null);

        //using findByManager(employeeEntity.get())
        //2API calls
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);
        return  departmentRepository.findByManager(employeeEntity.get());

        //1 API Call
//        EmployeeEntity employeeEntity=EmployeeEntity.builder().id(employeeId).build();
//        return  departmentRepository.findByManager(employeeEntity);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        //Retrieve DepartmentId from Repository
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        //Retrieve EmployeeId from Repository
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

        return  departmentEntity.flatMap(department->
                employeeEntity.map(employee->{
                    //First update the Employee entity and save it as it owns the relationship
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);

                    department.getWorkers().add(employee);
                    return department;

                    //here you are only saving department entity, First update the Employee entity and save it as it owns the relationship
//                    return  departmentRepository.save(department);

                })).orElse(null);
    }

    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {

        //Retrieve DepartmentId from Repository
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        //Retrieve EmployeeId from Repository
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

        return  departmentEntity.flatMap(department->
                employeeEntity.map(employee->{
                    //First update the Employee entity and save it as it owns the relationship
                    employee.getFreelanceDepartment().add(department);
                    employeeRepository.save(employee);

                    //populate the Set<Employee> Freelancers with employees
                    department.getFreelancers().add(employee);
                    return department;
                    //here you are only saving department entity, First update the Employee entity and save it as it owns the relationship
//                    return  departmentRepository.save(department);

                })).orElse(null);
    }
}
