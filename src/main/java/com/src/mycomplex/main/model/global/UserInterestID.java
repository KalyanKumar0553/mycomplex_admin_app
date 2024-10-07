package com.src.mycomplex.main.model.global;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode
public class UserInterestID implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userID;
    private Long interestID;
}