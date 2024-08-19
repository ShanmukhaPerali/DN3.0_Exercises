package com.javaguides.EmployeeManagementSystem.service;

import java.util.List;

import com.javaguides.EmployeeManagementSystem.entity.PrimaryEmployee;

public interface PrimaryEmployeeService {
    List<PrimaryEmployee> getAllEmployees();
    PrimaryEmployee saveEmployee(PrimaryEmployee employee);
}