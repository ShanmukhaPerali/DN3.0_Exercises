package com.javaguides.EmployeeManagementSystem.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "Employee.findByDepartment",
        query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"
    ),
    @NamedQuery(
        name = "Employee.findAllOrderByName",
        query = "SELECT e FROM Employee e ORDER BY e.name ASC"
    )
})

// @Entity
@Table(name = "employees")
public class Employee extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    // Constructors, getters, setters
    public Employee(){}
    public Employee(String name, String email, Department department){
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment(){
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
