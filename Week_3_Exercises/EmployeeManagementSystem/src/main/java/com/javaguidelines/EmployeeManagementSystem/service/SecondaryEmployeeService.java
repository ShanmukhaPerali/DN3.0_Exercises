package com.javaguides.EmployeeManagementSystem.service;

import com.javaguides.EmployeeManagementSystem.entity.SecondaryEmployee;

import java.util.List;

public interface SecondaryEmployeeService {
    List<SecondaryEmployee> getAllEmployees();
    SecondaryEmployee saveEmployee(SecondaryEmployee employee);
}