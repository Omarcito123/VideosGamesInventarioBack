package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.CompraInv;

public interface CompraInvRepository extends CrudRepository<CompraInv, Long>{	
	@Query(value = "select * from compra_inv where idsucursal = :idsucursal and DATE(dateadd) = :fecha order by idcompra desc" , nativeQuery = true)
    List<CompraInv> getListVentaBySucursal(@Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
	
	@Query(value = "select * from compra_inv where iduseradd = :iduseradd and idsucursal = :idsucursal and DATE(dateadd) BETWEEN :fecha AND :fechafin order by idcompra desc" , nativeQuery = true)
    List<CompraInv> getListVentaBySucursalAndByVendedor(@Param("iduseradd") long iduseradd, @Param("idsucursal") long idsucursal, @Param("fecha") String fecha, @Param("fechafin") String fechafin);
	
	@Query(value = "select * from compra_inv where idsucursal = :idsucursal and MONTH(TIMESTAMP(dateadd)) = :mes and YEAR(TIMESTAMP(dateadd)) = :anio order by idcompra desc" , nativeQuery = true)
    List<CompraInv> getListComprasBySucursalAndByVendedorMensuales(@Param("idsucursal") long idsucursal, @Param("mes") String mes, @Param("anio") String anio);
	
	@Query(value = "select c.idcompra as idcompra, c.factura as factura, c.recibo as recibo, c.nombre as nombre, \r\n" + 
			"c.cantidad as cantidad, c.precio as preciounidad, c.total as total, CONCAT(u.firstname, ' ', u.surname) as vendedor, c.dateadd as fechaventa \r\n" + 
			"from compra_inv c inner join user_inv u\r\n" + 
			"on c.iduseradd = u.iduser where c.idsucursal = :idsucursal and DATE(c.dateadd) BETWEEN  :fechainicio AND :fechafin order by idcompra desc" , nativeQuery = true)
	List<Object[]> getComprasReporte(@Param("idsucursal") long idsucursal, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
}
