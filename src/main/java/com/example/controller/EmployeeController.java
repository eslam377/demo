package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    private final EmployeeService employeeService ;

    @GetMapping("/employees")
    public ResponseEntity<?> findAllEmployee(){
        try {
            List<EmployeeDTO> employeeDTOs = employeeService.findAllEmployees();
            return new ResponseEntity<>(employeeDTOs ,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable("id") long id){
            return new ResponseEntity<>(employeeService.findEmployeeById(id),HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) throws MethodArgumentNotValidException{

            EmployeeDTO empDTO = employeeService.addNewEmployee(employeeDTO);
            return new ResponseEntity<>(empDTO,HttpStatus.CREATED);

    }








}
