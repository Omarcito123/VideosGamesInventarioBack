package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ISucursalesService;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Sucursales;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalesController {
	
	@Autowired
	ISucursalesService sucursalesService;

	@GetMapping("/getSucursalesList")
	public ResponseObject getSucursalesList() {
		return sucursalesService.getSucursales();
	}
	
	@PostMapping("/getSucursalById")
	public ResponseObject getSucursalById(@RequestBody Sucursales suc) {
		return sucursalesService.getSucursalById(suc);
	}
	
	@PostMapping("/saveSucursal")
	public ResponseObject saveSucursal(@RequestBody Sucursales suc) {
		return sucursalesService.saveSucursal(suc);
	}
	
	@PutMapping("/updateSucursal")
	public ResponseObject updateSucursal(@RequestBody Sucursales suc) {
		return sucursalesService.updateSucursal(suc);
	}
	
	@PostMapping("/deleteSucursal")
	public ResponseObject deleteSucursal(@RequestBody Sucursales suc) {
		return sucursalesService.deleteSucursal(suc);
	}
}