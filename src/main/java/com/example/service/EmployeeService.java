package com.example.service;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<EmployeeDTO> findAllEmployees();

    Optional<Employee> findEmployeeById(long id);

    Employee addNewEmployee(Employee employee);

    void deleteEmployee(@Param("id") Long id);


}
