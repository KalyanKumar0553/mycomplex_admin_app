package com.src.mycomplex.main.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.mycomplex.main.model.complex.Unit;
import com.src.mycomplex.main.repository.complex.UnitRepository;
import com.src.mycomplex.main.response.dto.UnitResponseDTO;

@Service
public class UnitService {

	@Autowired
	UnitRepository unitRepo;
	
	public List<UnitResponseDTO> findAll() {
		return unitRepo.findAll().stream().map(e->new UnitResponseDTO(e)).collect(Collectors.toList());
	}

	public UnitResponseDTO fetchUnit(Long id) {
		Optional<Unit> unitHolder = unitRepo.findById(id);
		if(unitHolder.isPresent()) {
			return new UnitResponseDTO(unitHolder.get());			
		} else {
			return new UnitResponseDTO();
		}
	}

}
