package com.apirest.videogame.interfaces;

import java.util.List;
import java.util.Optional;

import com.apirest.videogame.model.Products;
import com.apirest.videogame.model.ResponseObject;

public interface IProductsService {
		public ResponseObject createProduct(Products product);
	    public List<Products> getProducts(String promocion);
	    public List<Products> getListProducts();
	    public List<Products> findProductbyName(String nameProduct);
	    public Optional<Products> findById(long id);
	    public ResponseObject editProduct(Products product);
	    public ResponseObject deleteProductsById(long id);
	}