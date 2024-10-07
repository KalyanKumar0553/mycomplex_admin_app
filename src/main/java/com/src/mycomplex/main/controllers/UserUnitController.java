package com.src.mycomplex.main.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.src.mycomplex.main.response.dto.UnitResponseDTO;
import com.src.mycomplex.main.services.UnitService;

import lombok.extern.log4j.Log4j2;

@RestController()
@RequestMapping("/api/user/unit")
@CrossOrigin
@Log4j2
public class UserUnitController {

	@Autowired
	private UnitService unitService;

	@GetMapping
	public List<UnitResponseDTO> listUnits() {
		return unitService.findAll();
	}

	@GetMapping("/{id}")
	public UnitResponseDTO fetchUnit(@PathVariable("id") Long id) {
		return unitService.fetchUnit(id);
	}

}
