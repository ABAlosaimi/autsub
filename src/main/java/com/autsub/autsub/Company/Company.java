package com.autsub.autsub.Company;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "company")
public class Company  implements UserDetails{

    public Company() {
    }

    public Company(String name, String password, String email, String address, String industry, String Commercial_Registration_Number) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.industry = industry;
        this.Commercial_Registration_Number = Commercial_Registration_Number;
    }
   
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Long id;


    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable = false, unique = true)
     private String name;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email should be in valid format")
     private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(columnDefinition = "VARCHAR(12) NOT NULL CHECK (LENGTH(password) >= 8 AND LENGTH(password) <= 12)")
     private String password;

    @NotBlank(message = "Address is mandatory")
    @Column(name = "address", nullable = false)
     private String address;

     @NotBlank(message = "Industry is mandatory")
     @Column(columnDefinition = "VARCHAR(255) NOT NULL CHECK (industry IN ('Finance', 'Healthcare', 'Retail', 'Manufacturing', 'Education'))")
     private String industry;

     @NotBlank(message = "Commercial Registration Number is mandatory")
     @Column(columnDefinition = "VARCHAR(10) NOT NULL CHECK (LENGTH(Commercial_Registration_Number) = 10)")
     private String Commercial_Registration_Number;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
       return password;
    }


}
