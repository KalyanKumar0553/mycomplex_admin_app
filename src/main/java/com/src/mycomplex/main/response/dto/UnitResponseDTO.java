package com.src.mycomplex.main.response.dto;

import com.src.mycomplex.main.model.complex.Unit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitResponseDTO {
	
	private Long id;
	
	public UnitResponseDTO(Unit input) {
		this.id = input.getId();
	}
		
}
