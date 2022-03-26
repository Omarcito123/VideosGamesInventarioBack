package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IVentaInvService;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.VentaInv;

@RestController
@RequestMapping("/api/ventaInv")
public class VentaInvController {
	
	@Autowired
	IVentaInvService ventaInvService;

	@PostMapping("/createVenta")
	public ResponseObject createVenta(@RequestBody VentaInv[] ventaList) {
		return ventaInvService.createVenta(ventaList);
	}
	
	@PostMapping("/getListVentaReservaOnlyBySucursal")
	public ResponseObject getListVentaReservaOnlyBySucursal(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaReservaOnlyBySucursal(venta);
	}
	
	@PostMapping("/getListVentaMensualBySucursal")
	public ResponseObject getListVentaMensualBySucursal(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaMensualBySucursal(venta);
	}
	
	@PostMapping("/getListVentaBySucursal")
	public ResponseObject getListVentaBySucursal(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaBySucursal(venta);
	}
	
	@PostMapping("/getListVentaReservaBySucursal")
	public ResponseObject getListVentaReservaBySucursal(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaReservaBySucursal(venta);
	}
	
	@PostMapping("/getListVentaBySucursalAndByVendedor")
	public ResponseObject getListVentaBySucursalAndByVendedor(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaBySucursalAndByVendedor(venta);
	}
	
	@PostMapping("/getListVentaReservaBySucursalAndByVendedor")
	public ResponseObject getListVentaReservaBySucursalAndByVendedor(@RequestBody VentaInv venta) {
		return ventaInvService.getListVentaReservaBySucursalAndByVendedor(venta);
	}

	@PostMapping("/findVentaById")
	public ResponseObject findVentaById(@RequestBody VentaInv venta) {
		return ventaInvService.findVentaById(venta);
	}
	
	@PutMapping("/editVenta")
	public ResponseObject editVenta(@RequestBody VentaInv venta) {
		return ventaInvService.editVenta(venta);
	}
	
	@PutMapping("/entregarReserva")
	public ResponseObject entregarReserva(@RequestBody VentaInv venta) {
		return ventaInvService.entregarReserva(venta);
	}
	
	@PutMapping("/deleteVentaById")
	public ResponseObject deleteVentaById(@RequestBody VentaInv venta) {
		return ventaInvService.deleteVentaById(venta);
	}
	
	@PostMapping("/getVentasReporte")
	public ResponseObject getVentasReporte(@RequestBody VentaInv venta) {
		return ventaInvService.getVentasReporte(venta);
	}
}