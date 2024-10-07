package com.src.mycomplex.main.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.mycomplex.main.repository.global.ComplexRepository;
import com.src.mycomplex.main.request.dto.ComplexRequestDTO;
import com.src.mycomplex.main.response.dto.ComplexResponseDTO;

@Service
public class ComplexService {

	@Autowired
	ComplexRepository complexRepo;
	
	public List<ComplexResponseDTO> findAll() {
		return complexRepo.findAll().stream().map(e->new ComplexResponseDTO(e)).collect(Collectors.toList());
	}

	public List<ComplexResponseDTO> findAllComplexContainsKey(String key) {
		return complexRepo.findByNameContainingIgnoreCase(key).stream().map(e->new ComplexResponseDTO(e)).collect(Collectors.toList());
	}
	
	public boolean saveComplex(ComplexRequestDTO complexRequest) {
		
		return false;
	}

}
