package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.ComisionVendedorInv;

public interface ComisionVendedorInvRepository extends CrudRepository<ComisionVendedorInv, Long>{
	@Query(value = "select c.idcomisionvendedor, c.comision, c.dateadd, c.datemod, c.idsucursal, c.iduseradd, c.idusermod, c.idusuario, c.tipocomision, u.username as vendedor, s.nombre as sucursal, c.producto, c.ventatotal from comision_vendedor_inv c inner join user_inv u on c.idusuario = u.iduser inner join sucursales s on c.idsucursal = s.idsucursal where idusuario = :idusuario and MONTH(TIMESTAMP(c.dateadd)) = :month and YEAR(TIMESTAMP(c.dateadd)) = :anio order by idcomisionvendedor desc" , nativeQuery = true)
	List<Object[]> getComisionesByVendedorList(@Param("idusuario") long idusuario, @Param("month") int month, @Param("anio") String anio);
	
	@Query(value = "select c.idcomisionvendedor, c.comision, c.dateadd, c.datemod, c.idsucursal, c.iduseradd, c.idusermod, c.idusuario, c.tipocomision, u.username as vendedor, s.nombre as sucursal from comision_vendedor_inv c inner join user_inv u on c.idusuario = u.iduser inner join sucursales s on c.idsucursal = s.idsucursal where idusuario = :idusuario and idsucursal = :idsucursal order by idcomisionvendedor desc" , nativeQuery = true)
	List<Object[]> getComisionesByVendedorAndSucursalList(@Param("idusuario") long idusuario, @Param("idsucursal") long idsucursal);
	
	@Query(value = "select * from comision_vendedor_inv where idventa = :idventa" , nativeQuery = true)
	ComisionVendedorInv getComisionesByIdVenta(@Param("idventa") long idventa);
}
