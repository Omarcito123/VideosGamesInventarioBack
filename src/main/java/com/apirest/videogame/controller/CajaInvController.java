package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ICajaInvService;
import com.apirest.videogame.model.CajaInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/cajaInv")
public class CajaInvController {
	
	@Autowired
	ICajaInvService cajaInvService;

	@PostMapping("/createCajaInicial")
	public ResponseObject createCaja(@RequestBody CajaInv caja) {
		return cajaInvService.createCajaInicial(caja);
	}
	
	@GetMapping("/findCajasMensuales")
	public ResponseObject findCajasMensuales(@RequestBody CajaInv caja) {
		return cajaInvService.findCajasMensuales(caja);
	}
	
	@PostMapping("/findCajaByDateAndSucursal")
	public ResponseObject findCajaByDateAndSucursal(@RequestBody CajaInv caja) {
		return cajaInvService.findCajaByDateAndSucursal(caja);
	}
	
	@PostMapping("/findCajaByDateAndSucursalAyer")
	public ResponseObject findCajaByDateAndSucursalAyer(@RequestBody CajaInv caja) {
		return cajaInvService.findCajaByDateAndSucursalAyer(caja);
	}
	
	@PostMapping("/updateCajaIC")
	public ResponseObject findCuponByCodigoAndDate(@RequestBody CajaInv caja) {
		return cajaInvService.updateCajaIC(caja);
	}
	
	@PostMapping("/saveInicioCajaMonedas")
	public ResponseObject setInicioCierreCaja(@RequestBody CajaInv caja) {
		return cajaInvService.saveInicioCajaMonedas(caja);
	}
	
	@PostMapping("/saveMonedas")
	public ResponseObject saveMonedas(@RequestBody CajaInv caja) {
		return cajaInvService.saveMonedas(caja);
	}
	
	@PostMapping("/updateInicioCierreCaja")
	public ResponseObject updateInicioCierreCaja(@RequestBody CajaInv caja) {
		return cajaInvService.updateInicioCierreCaja(caja);
	}
	
	@PostMapping("/updateCajaFS")
	public ResponseObject updateCajaFS(@RequestBody CajaInv caja) {
		return cajaInvService.updateCajaFS(caja);
	}


	@PostMapping("/deleteCajaById")
	public ResponseObject editCupon(@RequestBody CajaInv caja) {
		return cajaInvService.deleteCajaById(caja);
	}
}