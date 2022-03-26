package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.Products;

public interface ProductsRespository extends CrudRepository<Products, Long>{
	@Query(value = "select * from products where nameproduct LIKE %:name% order by idproduct desc" , nativeQuery = true)
    List<Products> findProductbyName(@Param("name") String name);
	
	@Query(value = "select * from products where nameproduct = ?1 order by idproduct desc" , nativeQuery = true)
    List<Products> findProductsbyName(String username);
	
	@Query(value = "select * from products where promocion = ?1 order by idproduct desc", nativeQuery = true)
	List<Products> getProductsList(String promocion);	
	
	@Query(value = "select * from products order by idproduct desc", nativeQuery = true)
	List<Products> getListProducts();
}
