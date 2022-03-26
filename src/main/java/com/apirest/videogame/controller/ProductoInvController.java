package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IProductoInvService;
import com.apirest.videogame.model.ProductoInv;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/productoInv")
public class ProductoInvController {
	
	@Autowired
	IProductoInvService productoInvService;

	@PostMapping("/getListProductsBySucursal")
	public ResponseObject getListProductsBySucursal(@RequestBody ProductoInv producto) {
		return productoInvService.getListProductsBySucursal(producto);
	}
	
	@PostMapping("/getListProductsBySucursalAndCategoria")
	public ResponseObject getListProductsBySucursalAndCategoria(@RequestBody ProductoInv producto) {
		return productoInvService.getListProductsBySucursalAndCategoria(producto);
	}
	
	@PostMapping("/getRepuestoBodegaAndCategoria")
	public ResponseObject getRepuestoBodegaAndCategoria(@RequestBody ProductoInv producto) {
		return productoInvService.getRepuestoBodegaAndCategoria(producto);
	}
	
	@PostMapping("/findProductById")
	public ResponseObject findProductById(@RequestBody ProductoInv producto) {
		return productoInvService.findProductById(producto);
	}
	
	@PostMapping("/findProductbyName")
	public ResponseObject findProductbyName(@RequestBody ProductoInv producto) {
		return productoInvService.findProductbyName(producto);
	}
	
	@PostMapping("/findProductByNameAndSucursalAndCode")
	public ResponseObject findProductByNameAndSucursalAndCode(@RequestBody ProductoInv producto) {
		return productoInvService.findProductByNameAndSucursalAndCode(producto);
	}

	@PostMapping("/saveProductInv")
	public ResponseObject saveProductInv(@RequestBody ProductoInv producto) {
		return productoInvService.createProduct(producto);
	}
	
	@PostMapping("/saveProductListInv")
	public ResponseObject saveProductListInv(@RequestBody ProductoInv[] productoList) {
		return productoInvService.saveProductListInv(productoList);
	}
	
	@PutMapping("/updateProductInv")
	public ResponseObject updateProductInv(@RequestBody ProductoInv producto) {
		return productoInvService.editProduct(producto);
	}
	
	@PostMapping("/traslaProductoSucursal")
	public ResponseObject traslaProductoSucursal(@RequestBody ProductoInv producto) {
		return productoInvService.traslaProductoSucursal(producto);
	}
	
	@PostMapping("/traslaProductoSucursales")
	public ResponseObject traslaProductoSucursales(@RequestBody ProductoInv producto) {
		return productoInvService.traslaProductoSucursales(producto);
	}
	
	@PostMapping("/updateExistenciasBodega")
	public ResponseObject updateExistenciasBodega(@RequestBody ProductoInv producto) {
		return productoInvService.updateExistenciasBodega(producto);
	}
	
	@PostMapping("/deleteProductInv")
	public ResponseObject deleteProductInv(@RequestBody ProductoInv producto) {
		return productoInvService.deleteProductsById(producto);
	}
	
	@PostMapping("/eliminarInventario")
	public ResponseObject eliminarInventario(@RequestBody ProductoInv producto) {
		return productoInvService.eliminarInventario(producto);
	}
}