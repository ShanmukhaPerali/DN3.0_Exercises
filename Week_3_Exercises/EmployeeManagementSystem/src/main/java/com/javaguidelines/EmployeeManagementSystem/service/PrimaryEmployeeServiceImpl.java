package com.javaguides.EmployeeManagementSystem.service;

import com.javaguides.EmployeeManagementSystem.entity.PrimaryEmployee;
import com.javaguides.EmployeeManagementSystem.repository.PrimaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimaryEmployeeServiceImpl implements PrimaryEmployeeService {

    @Autowired
    private PrimaryEmployeeRepository primaryEmployeeRepository;

    @Override
    public List<PrimaryEmployee> getAllEmployees() {
        return primaryEmployeeRepository.findAll();
    }

    @Override
    public PrimaryEmployee saveEmployee(PrimaryEmployee employee) {
        return primaryEmployeeRepository.save(employee);
    }

}