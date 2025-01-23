package com.autsub.autsub.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "company")
public class Company {
   
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String name;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be in valid format")
     private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    @Min(8)
    @Max(12)
     private String password;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address", nullable = false)
     private String address;

     @NotBlank(message = "Industry is mandatory")
     @Column(columnDefinition = "VARCHAR(255) NOT NULL CHECK (industry IN ('Finance', 'Healthcare', 'Retail', 'Manufacturing', 'Education'))")
     private String industry;

     @NotBlank(message = "Commercial Registration Number is mandatory")
     @Column(name = "Commercial_Registration_Number", nullable = false, unique = true)
     @Min(11)
     @Max(11)
     private int Commercial_Registration_Number;

     




}
