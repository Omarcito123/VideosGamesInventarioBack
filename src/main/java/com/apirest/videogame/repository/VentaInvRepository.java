package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.VentaInv;

public interface VentaInvRepository extends CrudRepository<VentaInv, Long>{	
	@Query(value = "select * from venta_inv where idsucursal = :idsucursal and DATE(dateadd) = :fecha order by idventa desc" , nativeQuery = true)
    List<VentaInv> getListVentaBySucursal(@Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
	
	@Query(value = "select * from venta_inv where idsucursal = :idsucursal and DATE(dateadd) BETWEEN :fechainicio AND :fechafin and tipoventa = 'Reserva' order by idventa desc" , nativeQuery = true)
    List<VentaInv> getListVentaReservaBySucursal(@Param("idsucursal") long idsucursal, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
	
	@Query(value = "select * from venta_inv where idsucursal = :idsucursal and tipoventa = 'Reserva' and estadoreserva = 'Reserva' order by idventa desc" , nativeQuery = true)
    List<VentaInv> getListVentaReservaOnlyBySucursal(@Param("idsucursal") long idsucursal);
	
	@Query(value = "select * from venta_inv where iduseradd = :iduseradd and idsucursal = :idsucursal and DATE(dateadd) = :fecha order by idventa desc" , nativeQuery = true)
    List<VentaInv> getListVentaBySucursalAndByVendedor(@Param("iduseradd") long iduseradd, @Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
	
	@Query(value = "select * from venta_inv where iduseradd = :iduseradd and idsucursal = :idsucursal and DATE(dateadd) = :fecha and tipoventa = 'Reserva' order by idventa desc" , nativeQuery = true)
    List<VentaInv> getListVentaReservaBySucursalAndByVendedor(@Param("iduseradd") long iduseradd, @Param("idsucursal") long idsucursal, @Param("fecha") String fecha);
	
	@Query(value = "select SUM(preciototal) as preciototal, dateadd from venta_inv where idsucursal = :idsucursal and MONTH(TIMESTAMP(dateadd)) = :mes and YEAR(TIMESTAMP(dateadd)) = :anio group by DAY(TIMESTAMP(dateadd)) order by idventa desc;" , nativeQuery = true)
	List<Object[]> getListVentaMensualBySucursal(@Param("idsucursal") long idsucursal, @Param("mes") String mes, @Param("anio") String anio);
	
	@Query(value = "select v.idventa as idventa, v.factura as factura, v.recibo as recibo, v.nombreproducto as nombreproducto, v.cantidad as cantidad, \r\n" + 
			"p.preciocosto * v.cantidad as preciocosto, v.preciototal as precioventa,\r\n" + 
			"(v.preciototal - (p.preciocosto * v.cantidad)) as ganancia, CONCAT(u.firstname, ' ', u.surname) as vendedor, v.categoria as categoria, v.dateadd as fechaventa\r\n" + 
			"from venta_inv v inner join user_inv u\r\n" + 
			"on v.iduseradd = u.iduser inner join producto_inv p\r\n" + 
			"on v.idproducto = p.idprodinv where v.idsucursal = :idsucursal and DATE(v.dateadd) BETWEEN :fechainicio AND :fechafin order by idventa desc" , nativeQuery = true)
	List<Object[]> getVentasReporte(@Param("idsucursal") long idsucursal, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
	
	@Query(value = "select v.idventa as idventa, v.factura as factura, v.recibo as recibo, v.nombreproducto as nombreproducto, v.cantidad as cantidad,  \r\n" + 
			"			0 as preciocosto, v.preciototal as precioventa, \r\n" + 
			"			(v.preciototal - 0) as ganancia, CONCAT(u.firstname, ' ', u.surname) as vendedor, \r\n" + 
			"            v.categoria as categoria, v.dateadd as fechaventa\r\n" + 
			"			from venta_inv v inner join user_inv u\r\n" + 
			"			on v.iduseradd = u.iduser  where v.idsucursal = :idsucursal and v.idproducto = 0\r\n" + 
			"            and DATE(v.dateadd) BETWEEN :fechainicio AND :fechafin order by idventa desc" , nativeQuery = true)
	List<Object[]> getVentasReporteProductoNoExiste(@Param("idsucursal") long idsucursal, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
}
