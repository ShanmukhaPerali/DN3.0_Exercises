package com.javaguides.EmployeeManagementSystem.repository;

import com.javaguides.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import java.util.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    // Derived query method to find departments by name
    Department findByName(String name);
}
