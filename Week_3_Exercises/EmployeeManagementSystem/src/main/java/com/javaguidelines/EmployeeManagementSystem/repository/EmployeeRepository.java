package com.javaguides.EmployeeManagementSystem.repository;

import com.javaguides.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
// import com.javaguides.EmployeeManagementSystem.entity.Employee;
import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {
    
    // Derived query method to find employees by name
    List<Employee> findByName(String name);
    
    // Derived query method to find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Custom query to find employees by email
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmail(@Param("email") String email);

    // Custome query to find employees by department id
    @Query("SELECT e FROM Employee e WHERE e.department.id = :departmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);

    // Using named query findByDepartment
    @Query(name = "Employee.findByDepartment")
    List<Employee> findByDepartment(@Param("departmentName") String departmentName);

    // Using named query findAllOrderByName
    @Query(name = "Employee.findAllOrderByName")
    List<Employee> findAllOrderByName();

    
}
