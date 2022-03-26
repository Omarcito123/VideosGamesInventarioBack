package com.apirest.videogame.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apirest.videogame.model.Sucursales;

public interface SucursalesRepository extends CrudRepository<Sucursales, Long>{
	@Query(value = "select * from sucursales where nombre = 'bodega' order by idsucursal desc LIMIT 1" , nativeQuery = true)
	Sucursales getIdSucursalBodeg();
}
