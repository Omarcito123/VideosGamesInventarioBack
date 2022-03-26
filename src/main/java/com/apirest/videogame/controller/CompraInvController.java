package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.ICompraInvService;
import com.apirest.videogame.model.CompraInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/compraInv")
public class CompraInvController {
	
	@Autowired
	ICompraInvService compraInvService;

	@PostMapping("/getListComprasBySucursal")
	public ResponseObject getListComprasBySucursal(@RequestBody CompraInv compra) {
		return compraInvService.getListComprasBySucursal(compra);
	}
	
	@PostMapping("/findCompraById")
	public ResponseObject findCompraById(@RequestBody CompraInv compra) {
		return compraInvService.findCompraById(compra);
	}
	
	@PostMapping("/getComprasReporte")
	public ResponseObject getComprasReporte(@RequestBody CompraInv compra) {
		return compraInvService.getComprasReporte(compra);
	}
	
	@PostMapping("/getListComprasBySucursalAndByVendedor")
	public ResponseObject getListComprasBySucursalAndByVendedor(@RequestBody CompraInv compra) {
		return compraInvService.getListComprasBySucursalAndByVendedor(compra);
	}
	
	@PostMapping("/getListComprasBySucursalAndByVendedorMensuales")
	public ResponseObject getListComprasBySucursalAndByVendedorMensuales(@RequestBody CompraInv compra) {
		return compraInvService.getListComprasBySucursalAndByVendedorMensuales(compra);
	}

	@PostMapping("/createCompra")
	public ResponseObject createCompra(@RequestBody CompraInv[] compraList) {
		return compraInvService.createCompra(compraList);
	}
	
	@PutMapping("/editCompra")
	public ResponseObject editCompra(@RequestBody CompraInv compra) {
		return compraInvService.editCompra(compra);
	}
	
	@PostMapping("/deleteCompraById")
	public ResponseObject deleteCompraById(@RequestBody CompraInv compra) {
		return compraInvService.deleteCompraById(compra);
	}
}