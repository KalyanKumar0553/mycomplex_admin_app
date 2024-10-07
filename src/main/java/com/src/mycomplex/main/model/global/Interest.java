package com.src.mycomplex.main.model.global;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Interest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	private Long createdBy;
	
	private Date createdOn;
	
	@OneToMany(mappedBy = "interest", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserInterests> userInterests;
}
