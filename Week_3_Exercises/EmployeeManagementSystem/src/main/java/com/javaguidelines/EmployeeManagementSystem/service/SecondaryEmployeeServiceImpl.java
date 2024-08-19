package com.javaguides.EmployeeManagementSystem.service;

import com.javaguides.EmployeeManagementSystem.entity.SecondaryEmployee;
import com.javaguides.EmployeeManagementSystem.repository.SecondaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondaryEmployeeServiceImpl implements SecondaryEmployeeService {

    @Autowired
    private SecondaryEmployeeRepository secondaryEmployeeRepository;

    @Override
    public List<SecondaryEmployee> getAllEmployees() {
        return secondaryEmployeeRepository.findAll();
    }

    @Override
    public SecondaryEmployee saveEmployee(SecondaryEmployee employee) {
        return secondaryEmployeeRepository.save(employee);
    }

}