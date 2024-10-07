package com.src.mycomplex.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.src.mycomplex.main.response.dto.ComplexResponseDTO;
import com.src.mycomplex.main.response.dto.JSONResponseDTO;
import com.src.mycomplex.main.services.ComplexService;
import com.src.mycomplex.main.utils.AppUtils;

import lombok.extern.log4j.Log4j2;

@RestController()
@RequestMapping("/api/user/complex")
@CrossOrigin
@Log4j2
public class UserComplexController {

	@Autowired
	private ComplexService complexService;

	@GetMapping
	public List<ComplexResponseDTO> listComplex() {
		return complexService.findAll();
	}
	
	@GetMapping("/{key}")
	public JSONResponseDTO<List<ComplexResponseDTO>> fetchUnit(@PathVariable("key") String key) {
		return AppUtils.getJSONObject(complexService.findAllComplexContainsKey(key));
	}

}
