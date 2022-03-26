package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IReparacionDetInvService;
import com.apirest.videogame.model.ReparacionDetInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/reparacionDetInv")
public class ReparacionDetInvController {
	
	@Autowired
	IReparacionDetInvService reparacionDetInvService;

	@PostMapping("/createReparacionDet")
	public ResponseObject createReparacionDet(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.createReparacionDet(rep);
	}
	
	@PostMapping("/findReparacionDetById")
	public ResponseObject findReparacionDetById(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.findReparacionDetById(rep);
	}
	
	@PostMapping("/findReparacionDetByIdEnc")
	public ResponseObject findReparacionDetByIdEnc(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.findReparacionDetByIdEnc(rep);
	}
	
	@PostMapping("/findDetallesByIdEnc")
	public ResponseObject findDetallesByIdEnc(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.findDetallesByIdEnc(rep);
	}
	
	@PostMapping("/updateReparacionDet")
	public ResponseObject updateReparacionDet(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.updateReparacionDet(rep);
	}
	
	@PostMapping("/deleteReparacionDetById")
	public ResponseObject deleteReparacionDetById(@RequestBody ReparacionDetInv rep) {
		return reparacionDetInvService.deleteReparacionDetById(rep);
	}
}