package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data @NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(name = "first_name")
    private String firstName ;

    @Column(name = "last_name")
    private String lastName ;

    @Column(name = "email")
    private String email ;

    @Column(name = "salary")
    private double salary ;


    @Column(name = "phone_number")
    private String phoneNumber ;


    @Column(name = "hire_date")
    private LocalDate hireDate ;


    
}
