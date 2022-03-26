package com.apirest.videogame.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IProductsService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.Products;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.repository.ErroresRespository;
import com.apirest.videogame.repository.ProductsRespository;

@Service
@Transactional
public class ProductsService implements IProductsService {

	@Autowired
	ProductsRespository productsRespository;
	
	@Autowired
	ErroresRespository erroresRespository;

	@Override
	public ResponseObject createProduct(Products product) {
		ResponseObject responseObject = new ResponseObject();
		List<Products> productsList = new ArrayList<Products>();
		try {
			productsList = productsRespository.findProductsbyName(product.getNameproduct());
			if(productsList.size() > 0) {
				responseObject.setMessage("Ya Existe un producto con el mismo nombre");
				responseObject.setSuccess(false);
				return responseObject;
			} else {
				productsRespository.save(product);
				responseObject.setMessage("Producto agregado exitosamente");
				responseObject.setSuccess(true);
				return responseObject;
			}
		}catch(Exception e) {
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createProduct");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(product.getNameproduct() + " " + product.getDescription() + " " + product.getPoints() + " " + product.getPromocion() + " " + product.getQuantity() + " " + product.getPrice() + " " + product.getDateadd() + " " + product.getIduseradd());
			erroresRespository.save(error);
			return responseObject;
		}
	}

	@Override
	public List<Products> getProducts(String promocion) {
		List<Products> productsList = new ArrayList<Products>();
		try {
			productsList =  productsRespository.getProductsList(promocion);			
			return productsList;
			
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getProducts");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(promocion);
			erroresRespository.save(error);
			return productsList;
		}
	}
	
	@Override
	public List<Products> getListProducts() {
		List<Products> productsList = new ArrayList<Products>();
		try {
			productsList =  productsRespository.getListProducts();			
			return productsList;
			
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("getProducts");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("");
			erroresRespository.save(error);
			return productsList;
		}
	}

	@Override
	public Optional<Products> findById(long id) {
		Optional<Products> product = null;
		try {
			return productsRespository.findById(id);
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findByIdProducts");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id " + id);
			erroresRespository.save(error);
			return product;
		}
	}

	@Override
	public ResponseObject deleteProductsById(long id) {
		ResponseObject responseObject = new ResponseObject();
		try {
			productsRespository.deleteById(id);
			responseObject.setMessage("Producto eliminado exitosamente");
			responseObject.setSuccess(true);
			return responseObject;
		}catch(Exception e) {
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("deleteProductsById");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id " + id);
			erroresRespository.save(error);
			return responseObject;
		}
	}
	
	@Override
	public ResponseObject editProduct(Products product) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Optional<Products> productById = productsRespository.findById(product.getIdproduct());
			if(productById != null) {
				productById.get().setDescription(product.getDescription());
				productById.get().setNameproduct(product.getNameproduct());
				productById.get().setPoints(product.getPoints());
				productById.get().setPrice(product.getPrice());
				productById.get().setPromocion(product.getPromocion());
				productById.get().setQuantity(product.getQuantity());
				productById.get().setIdusermod(product.getIdusermod());
				productById.get().setDatemod(product.getDatemod());
				productsRespository.save(productById.get());
				responseObject.setMessage("Producto actualizado exitosamente");
				responseObject.setSuccess(true);
				return responseObject;
			}else {
				responseObject.setMessage("Producto no encontrado");
				responseObject.setSuccess(false);
				return responseObject;
			}	
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findProductbyName");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(product.getNameproduct() + " " + product.getDescription() + " " + product.getPoints() + " " + product.getPromocion() + " " + product.getQuantity() + " " + product.getPrice() + " " + product.getDateadd() + " " + product.getIduseradd());
			erroresRespository.save(error);
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			return responseObject;
		}
	}

	@Override
	public List<Products> findProductbyName(String nameProduct) {
		List<Products> productsList = new ArrayList<Products>();
		try {
			productsList = productsRespository.findProductbyName(nameProduct);
			return productsList;
		}catch(Exception e) {
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findProductbyName");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("nameProduct " + nameProduct);
			erroresRespository.save(error);
			return productsList;
		}
	}

}
