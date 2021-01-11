package com.dbc.localitys_back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbc.localitys_back.model.CitysDto;
import com.dbc.localitys_back.model.StateDto;
import com.dbc.localitys_back.service.StateService;

@RestController
@RequestMapping("/api/v1")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping("/states")
	public ResponseEntity<List<StateDto>> getStates(){
		return ResponseEntity.ok().body(stateService.getStates());
	}
	
	@GetMapping("/states/{id}")
	public ResponseEntity<StateDto> getStateByName(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(stateService.getState(id));
	}
	
	@PostMapping("/states/{idState}/city")
	public ResponseEntity<CitysDto> salveCity(@PathVariable("idState") Long idState, @RequestBody CitysDto city){
		return ResponseEntity.ok().body(stateService.salveCity(idState, city));
	}
	
	@DeleteMapping("/states/{idState}/city/{idCity}")
	public ResponseEntity<CitysDto> deleteCity(@PathVariable("idState") Long idState, @PathVariable("idCity") Long idCity){
		stateService.deleteCity(idState, idCity);
		return ResponseEntity.noContent().build();
	}
	
	
}
