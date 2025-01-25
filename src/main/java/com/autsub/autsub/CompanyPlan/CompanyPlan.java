package com.autsub.autsub.CompanyPlan;

import java.time.Instant;
import java.util.Date;

import com.autsub.autsub.Company.Company;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company_plan")
@Getter
@Setter
public class CompanyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Company.class)
    @JoinColumn(name = "company_name", referencedColumnName = "name", nullable = false, updatable = true)
    private Company company_name;
      
    @Column(name = "titel", nullable = false)
    @NotBlank(message = "Titel is mandatory")
    private String titel;

    @Column(name ="category", nullable = false)
    @NotBlank(message = "Category is mandatory")
    private String category;
   
    @Column(name = "description", nullable = false)
    @NotBlank(message = "Description is mandatory")
    private String description;
    
    @Column(columnDefinition = "VARCHAR(9) NOT NULL CHECK (recurring IN ('Monthly', 'Yearly', 'Weekly'))", nullable = true)
    private String recurring;
    
    @Column(name = "price", nullable = false)
    @NotBlank(message = "Price is mandatory")
    private int price;
    
    @Column(name = "trial", nullable = true)
    private boolean trial;

    @Column(name = "subscriptions", nullable = true)
     private double subscriptions;

    @Column(name = "cancelation", nullable = true)
    private double cancelation;

    @Column(name = "stumbled_subscription", nullable = true)
    private double stumbled_subscription;

    @Column(name = "last_offer_price", nullable = true)
    private double last_offer_price;

    @Column(name = "last_offer_date",  nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date last_offer_date;


    public void setLast_offer_date(String last_offer_date) {
        this.last_offer_date = Date.from(Instant.parse(last_offer_date));
    }

    public CompanyPlan() {
    }

    public CompanyPlan(String titel, String category, String description, String recurring, int price, boolean trial) {
        this.titel = titel;
        this.category = category;
        this.description = description;
        this.recurring = recurring;
        this.price = price;
        this.trial = trial;
    }

  }
