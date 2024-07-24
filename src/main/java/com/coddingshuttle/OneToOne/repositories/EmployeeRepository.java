package com.coddingshuttle.OneToOne.repositories;


import com.coddingshuttle.OneToOne.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
