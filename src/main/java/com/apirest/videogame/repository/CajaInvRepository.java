package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.CajaInv;

public interface CajaInvRepository extends CrudRepository<CajaInv, Long>{	
	@Query(value = "select * from caja_inv where idsucursal = :idsucursal and DATE(dateadd) = :fecha order by idcaja desc LIMIT 1" , nativeQuery = true)
	CajaInv findCajaByDateAndSucursal(@Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
	
	@Query(value = "select * from caja_inv where idsucursal = :idsucursal order by idcaja desc LIMIT 1" , nativeQuery = true)
	CajaInv findLastCajaBySucursal(@Param("idsucursal") long idsucursal);
	
	@Query(value = "select * from caja_inv where idsucursal = :idsucursal and MONTH(TIMESTAMP(c.dateadd)) = :month order by idcaja desc" , nativeQuery = true)
	List<CajaInv> findCajasMensuales(@Param("idsucursal") long idsucursal, @Param("month") String month);
}
