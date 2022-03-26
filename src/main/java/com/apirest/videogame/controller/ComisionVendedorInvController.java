package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IComisionVendedorInvService;
import com.apirest.videogame.model.ComisionVendedorInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/comisionVendedorInv")
public class ComisionVendedorInvController {
	
	@Autowired
	IComisionVendedorInvService comisionVendedorInvService;

	@GetMapping("/createComisionVendedor")
	public ResponseObject createComisionVendedor(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.createComisionVendedor(comisionV);
	}
	
	@PostMapping("/getComisionesByVendedorList")
	public ResponseObject getComisionesByVendedorList(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.getComisionesByVendedorList(comisionV);
	}
	
	@PostMapping("/getComisionesByVendedorAndSucursalList")
	public ResponseObject getComisionesByVendedorAndSucursalList(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.getComisionesByVendedorAndSucursalList(comisionV);
	}
	
	@PostMapping("/findComisionVendedorById")
	public ResponseObject findComisionVendedorById(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.findComisionVendedorById(comisionV);
	}

	@PostMapping("/editComisionVendedor")
	public ResponseObject editComisionVendedor(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.editComisionVendedor(comisionV);
	}
	
	@PostMapping("/deleteComisionVendedorById")
	public ResponseObject deleteComisionVendedorById(@RequestBody ComisionVendedorInv comisionV) {
		return comisionVendedorInvService.deleteComisionVendedorById(comisionV);
	}
}