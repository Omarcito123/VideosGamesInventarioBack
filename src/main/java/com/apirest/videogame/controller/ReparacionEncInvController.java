package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IReparacionEncInvService;
import com.apirest.videogame.model.ReparacionEncInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/reparacionEncInv")
public class ReparacionEncInvController {
	
	@Autowired
	IReparacionEncInvService reparacionEncInvService;

	@PostMapping("/createReparacionEnc")
	public ResponseObject createReparacionEnc(@RequestBody ReparacionEncInv rep) {
		return reparacionEncInvService.createReparacionEnc(rep);
	}
	
	@PostMapping("/findAllByStatusAndSucursal")
	public ResponseObject findAllByStatusAndSucursal(@RequestBody ReparacionEncInv rep) {
		return reparacionEncInvService.findAllByStatusAndSucursal(rep);
	}
	
	@PostMapping("/findReparacionByIdAndStatus")
	public ResponseObject findReparacionByIdAndStatus(@RequestBody ReparacionEncInv rep) {
		return reparacionEncInvService.findReparacionByIdAndStatus(rep);
	}
	
	@PostMapping("/updateReparacionEnc")
	public ResponseObject updateReparacionEnc(@RequestBody ReparacionEncInv rep) {
		return reparacionEncInvService.updateReparacionEnc(rep);
	}
	
	@PostMapping("/deleteReparacionEncById")
	public ResponseObject deleteReparacionEncById(@RequestBody ReparacionEncInv rep) {
		return reparacionEncInvService.deleteReparacionEncById(rep);
	}
}