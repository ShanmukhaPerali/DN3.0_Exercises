package com.javaguides.EmployeeManagementSystem.controllers;

import com.javaguides.EmployeeManagementSystem.entity.Department;
import com.javaguides.EmployeeManagementSystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    // Create a new Department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }

    // Get all Departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get a single Department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    // Update a Department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        department.setName(departmentDetails.getName());
        Department updatedDepartment = departmentRepository.save(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Delete a Department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if(department == null){
            return ResponseEntity.notFound().build();
        }
        departmentRepository.delete(department);
        return ResponseEntity.noContent().build();
    }
    
}
