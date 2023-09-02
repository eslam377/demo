package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;

import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.CustomModelMapper;
import com.example.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository ;

    @Override
    public List<EmployeeDTO> findAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOs =
                CustomModelMapper.mapAll(employees,EmployeeDTO.class);

        return employeeDTOs;
    }

    @Override
    public Employee findEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id " + id + " not found");
        }else {
            return employee;
        }
    }

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee = CustomModelMapper.map(employeeDTO, Employee.class);
     	Employee emp = employeeRepository.save(employee);
        EmployeeDTO empDTO = CustomModelMapper.map(emp, EmployeeDTO.class);
        return empDTO;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
