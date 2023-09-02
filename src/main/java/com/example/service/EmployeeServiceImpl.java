package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;

import com.example.exception.EmployeeNotFoundException;

import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository ;

    private  final EmployeeMapper employeeMapper ;

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = employeeMapper.EmployeesToEmployeeDTOs(employees);
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDTO employeeDTO = employeeMapper.EmployeeToEmployeeDTO(employee);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }else {
            return employeeDTO;
        }
    }




}
