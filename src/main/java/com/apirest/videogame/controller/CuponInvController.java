package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ICuponInvService;
import com.apirest.videogame.model.CuponInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/cuponInv")
public class CuponInvController {
	
	@Autowired
	ICuponInvService cuponInvService;

	@PostMapping("/createCupon")
	public ResponseObject createCupon(@RequestBody CuponInv cupon) {
		return cuponInvService.createCupon(cupon);
	}
	
	@GetMapping("/getCuponesList")
	public ResponseObject getCuponesList() {
		return cuponInvService.getCuponesList();
	}
	
	@PostMapping("/findCuponById")
	public ResponseObject findCuponById(@RequestBody CuponInv cupon) {
		return cuponInvService.findCuponById(cupon);
	}
	
	@PostMapping("/findCuponByCodigoAndDate")
	public ResponseObject findCuponByCodigoAndDate(@RequestBody CuponInv cupon) {
		return cuponInvService.findCuponByCodigoAndDate(cupon);
	}

	@PostMapping("/editCupon")
	public ResponseObject editCupon(@RequestBody CuponInv cupon) {
		return cuponInvService.editCupon(cupon);
	}
	
	@PostMapping("/deleteCuponById")
	public ResponseObject deleteCuponById(@RequestBody CuponInv cupon) {
		return cuponInvService.deleteCuponById(cupon);
	}
}