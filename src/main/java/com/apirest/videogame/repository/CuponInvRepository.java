package com.apirest.videogame.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.CuponInv;

public interface CuponInvRepository extends CrudRepository<CuponInv, Long>{	
	@Query(value = "select * from cupon_inv where cupon = :codigo order by idcupon desc LIMIT 1" , nativeQuery = true)
	CuponInv findCuponByCodigo(@Param("codigo") String codigo);
	
	@Query(value = "select * from cupon_inv where cupon = :codigo and DATE(fechavalido) >= DATE(:fecha) order by idcupon desc LIMIT 1" , nativeQuery = true)
	CuponInv findCuponByCodigoAndDate(@Param("codigo") String codigo, @Param("fecha") String fecha);
}
