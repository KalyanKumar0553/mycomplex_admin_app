package com.src.mycomplex.main.model.global;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ComplexAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userID;
    
    private Long complexID;
    
    private Date createdOn;
    
    private Long createdBy;
    
    private Date lastModifiedOn;
    
    private Long lastModifiedBy;
}
