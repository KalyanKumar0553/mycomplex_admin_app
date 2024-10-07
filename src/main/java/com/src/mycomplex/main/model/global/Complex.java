package com.src.mycomplex.main.model.global;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Complex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String address;
    
    private String city;
    
    private String state;
    
    private String zipcode;
    
    private ConstructionStatus constructionStatus;
    
    private Boolean registered;
    
    private String associationName;
    
    private Float lattitude;
    
    private Float longitude;
    
    private String logoLocation;
    
    private Boolean isActive;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "builder_id")
    private ComplexBuilder builder;
}
