package com.coddingshuttle.OneToOne.controllers;


import com.coddingshuttle.OneToOne.entities.DepartmentEntity;
import com.coddingshuttle.OneToOne.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping("/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        return departmentService.assignManagerToDepartment(departmentId,employeeId);

    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager( @PathVariable Long employeeId) {
        return departmentService.getAssignedDepartmentOfManager(employeeId);
    }

    @PutMapping("/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        return departmentService.assignWorkerToDepartment(departmentId,employeeId);

    }

    @PutMapping("/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        return departmentService.assignFreelancerToDepartment(departmentId,employeeId);

    }
}
