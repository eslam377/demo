package com.example.mapper;

import com.example.dto.EmployeeDTO;
import com.example.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;



@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee EmployeeDTOToEmployee(Employee employee);

    List<Employee> EmployeeDTOsToEmployees(List<EmployeeDTO> employees);

    @Mapping(target = "emailAddress", source = "email")
    EmployeeDTO EmployeeToEmployeeDTO(Employee employee);

    List<EmployeeDTO> EmployeesToEmployeeDTOs(List<Employee> employee);

}
