package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.HistorialCajaInv;

public interface HistorialCajaInvRepository extends CrudRepository<HistorialCajaInv, Long>{	
	@Query(value = "select * from historial_caja_inv where idsucursal = :idsucursal and DATE(dateadd) = :fecha order by idhistorialcaja desc LIMIT 1" , nativeQuery = true)
	List<HistorialCajaInv> findCajaByDateAndSucursal(@Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
		
	@Query(value = "select * from historial_caja_inv where idsucursal = :idsucursal and MONTH(TIMESTAMP(c.dateadd)) = :month order by idhistorialcaja desc" , nativeQuery = true)
	List<HistorialCajaInv> findCajasMensuales(@Param("idsucursal") long idsucursal, @Param("month") String month);
	
	@Query(value = "select * from historial_caja_inv where idsucursal = :idsucursal and DATE(dateadd) BETWEEN :fechainicio AND :fechafin order by idhistorialcaja desc" , nativeQuery = true)
	List<HistorialCajaInv> findCajaByRangoDateAndSucursal(@Param("idsucursal") long idsucursal, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
}
