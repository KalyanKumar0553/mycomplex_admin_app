package com.src.mycomplex.main.response.dto;

import com.src.mycomplex.main.model.global.Complex;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplexSearchResponseDTO {
	

	private Long id;
	private String name;
	
	public ComplexSearchResponseDTO(Complex input) {
		this.id = input.getId();
		this.name = input.getName();
	}
	
}
