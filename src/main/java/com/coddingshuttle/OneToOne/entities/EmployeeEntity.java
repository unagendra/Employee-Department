package com.coddingshuttle.OneToOne.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL) //owner of the relationship must have cascade
    @JoinColumn(name = "worker_department_id",referencedColumnName = "id")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name="freelance_Department_Mapping",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")

    )
    private Set<DepartmentEntity> freelanceDepartment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
