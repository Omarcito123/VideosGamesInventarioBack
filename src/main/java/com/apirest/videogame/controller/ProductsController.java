package com.apirest.videogame.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IProductsService;
import com.apirest.videogame.model.Products;
import com.apirest.videogame.model.ResponseObject;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	@Autowired
	IProductsService productsService;

	@PostMapping("/getAllProducts")
	public List<Products> getAllPoint(@RequestHeader("promocion") String promocion) {
		return productsService.getProducts(promocion);
	}
	
	@PostMapping("/getListProducts")
	public List<Products> getListProducts() {
		return productsService.getListProducts();
	}
	
	@PostMapping("/findProductbyName")
	public List<Products> findProductbyName(@RequestHeader("nameProduct") String nameProduct) {
		List<Products> productsList = new ArrayList<Products>();
		productsList = productsService.findProductbyName(nameProduct);
		return productsList;
	}
	
	@PostMapping("/saveProduct")
	public ResponseObject saveProduct(@RequestBody Products products) {
		return productsService.createProduct(products);
	}
	
	@PostMapping("/editProduct")
	public ResponseObject editProduct(@RequestBody Products products) {
		return productsService.editProduct(products);
	}
	
	@PostMapping("/deleteProductsById")
	public ResponseObject deleteProductsById(@RequestHeader("idproduct") Long idproduct) {
		return productsService.deleteProductsById(idproduct);
	}
}
