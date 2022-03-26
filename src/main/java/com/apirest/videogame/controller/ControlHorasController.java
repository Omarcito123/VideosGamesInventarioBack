package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IControlHorasService;
import com.apirest.videogame.model.ControlHorasInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/controlHorasInv")
public class ControlHorasController {
	@Autowired
	IControlHorasService controlHorasService;

	@PostMapping("/getControlByUserFechaTurno")
	public ResponseObject getControlByUserFechaTurno(@RequestBody ControlHorasInv control) {
		return controlHorasService.getControlByUserFechaTurno(control);
	}
	
	@PostMapping("/getControlByUserFecha")
	public ResponseObject getControlByUserFecha(@RequestBody ControlHorasInv control) {
		return controlHorasService.getControlByUserFecha(control);
	}
	
	@PostMapping("/getControlByUserMonth")
	public ResponseObject getControlByUserMonth(@RequestBody ControlHorasInv control) {
		return controlHorasService.getControlByUserMonth(control);
	}
	
	@PostMapping("/findControlById")
	public ResponseObject findControlById(@RequestBody ControlHorasInv control) {
		return controlHorasService.findById(control);
	}

	@PostMapping("/createControl")
	public ResponseObject createControl(@RequestBody ControlHorasInv control) {
		return controlHorasService.createControl(control);
	}
	
	@PutMapping("/editControlHoras")
	public ResponseObject editControlHoras(@RequestBody ControlHorasInv control) {
		return controlHorasService.update(control);
	}
	
	@PostMapping("/deleteControlById")
	public ResponseObject deleteControlById(@RequestBody ControlHorasInv control) {
		return controlHorasService.deleteControlById(control);
	}
}