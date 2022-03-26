package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.videogame.model.ProductoInv;

public interface ProductoInvRepository extends CrudRepository<ProductoInv, Long>{
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal order by idprodinv desc" , nativeQuery = true)
    List<ProductoInv> getListProductsBySucursal(@Param("idsucursal") long idsucursal);
	
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal and categoria = :categoria order by idprodinv desc" , nativeQuery = true)
    List<ProductoInv> getListProductsBySucursalAndCategoria(@Param("idsucursal") long idsucursal, @Param("categoria") String categoria);
	
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal and nombre = :name order by idprodinv desc LIMIT 1" , nativeQuery = true)
    ProductoInv findProductByNameAndSucursal(@Param("idsucursal") long idsucursal, @Param("name") String name);
	
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal and serie = :serie order by idprodinv desc LIMIT 1" , nativeQuery = true)
    ProductoInv findProductByCodeAndSucursal(@Param("idsucursal") long idsucursal, @Param("serie") String serie);
	
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal and serie = :serie and nombre = :name order by idprodinv desc LIMIT 1" , nativeQuery = true)
    ProductoInv findProductByCodeAndSucursalAndName(@Param("idsucursal") long idsucursal, @Param("serie") String serie, @Param("name") String name);
	
	@Query(value = "select * from producto_inv where idsucursal = :idsucursal and categoria = :categoria order by idprodinv desc" , nativeQuery = true)
    List<ProductoInv> getRepuestoBodegaAndCategoria(@Param("idsucursal") long idsucursal, @Param("categoria") String categoria);
	
	@Modifying
    @Transactional
	@Query(value = "delete from producto_inv where idsucursal = :idsucursal" , nativeQuery = true)
    void eliminarInventario(@Param("idsucursal") long idsucursal);
}
