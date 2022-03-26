package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IComisionInvService;
import com.apirest.videogame.model.ComisionInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/comisionInv")
public class ComisionInvController {
	
	@Autowired
	IComisionInvService comisionInvService;

	@GetMapping("/getComisionesList")
	public ResponseObject getComisionesList() {
		return comisionInvService.getComisionesList();
	}
	
	@PostMapping("/findComisionById")
	public ResponseObject findComisionById(@RequestBody ComisionInv comision) {
		return comisionInvService.findComisionById(comision);
	}
	
	@PostMapping("/createComision")
	public ResponseObject createComision(@RequestBody ComisionInv comision) {
		return comisionInvService.createComision(comision);
	}
	
	@PostMapping("/editComision")
	public ResponseObject editComision(@RequestBody ComisionInv comision) {
		return comisionInvService.editComision(comision);
	}

	@PostMapping("/deleteComisionById")
	public ResponseObject deleteComisionById(@RequestBody ComisionInv comision) {
		return comisionInvService.deleteComisionById(comision);
	}
}