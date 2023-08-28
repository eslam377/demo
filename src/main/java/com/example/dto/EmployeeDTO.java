package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "Salary must be a positive value")
    private double salary;

    @Column(unique = true)
    @Pattern(regexp = "01[0-9]{9}", message = "Invalid phone number format for Vodafone Egypt")
    private String phoneNumber;

    @PastOrPresent(message = "Hire date must be in the past or present")
    private LocalDate hireDate;


}
