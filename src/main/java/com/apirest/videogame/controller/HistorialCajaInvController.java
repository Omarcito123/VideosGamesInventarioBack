package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IHistorialCajaInvService;
import com.apirest.videogame.model.HistorialCajaInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/historialCajaInv")
public class HistorialCajaInvController {
	
	@Autowired
	IHistorialCajaInvService historialCajaInvService;

	@PostMapping("/createHistorialCaja")
	public ResponseObject createHistorialCaja(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.createHistorialCaja(caja);
	}
	
	@PostMapping("/findCajasMensuales")
	public ResponseObject findCajasMensuales(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.findCajasMensuales(caja);
	}
	
	@PostMapping("/findCajaByDateAndSucursal")
	public ResponseObject findCajaByDateAndSucursal(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.findCajaByDateAndSucursal(caja);
	}
	
	@PostMapping("/findCajaByRangoDateAndSucursal")
	public ResponseObject findCajaByRangoDateAndSucursal(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.findCajaByRangoDateAndSucursal(caja);
	}
	
	@PostMapping("/updateHistorialCaja")
	public ResponseObject updateHistorialCaja(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.updateHistorialCaja(caja);
	}
	
	@PostMapping("/deleteHistorialCajaById")
	public ResponseObject deleteHistorialCajaById(@RequestBody HistorialCajaInv caja) {
		return historialCajaInvService.deleteHistorialCajaById(caja);
	}
}