package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id ;
    private String firstName ;
    private String lastName ;
    private String email ;
    private double salary ;
    private String phoneNumber ;
    private LocalDate hireDate ;

}
