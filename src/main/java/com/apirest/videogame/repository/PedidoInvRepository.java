package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.PedidosInv;

public interface PedidoInvRepository extends CrudRepository<PedidosInv, Long>{
	@Query(value = "select p.idpedidoinv, p.estado, p.cantidad, p.idprodinv, p.nombreproducto, IFNULL(i.existencia, 0) as existencia, IFNULL(ie.existencia, 0) as existenciabodega, p.idsucursal, p.fechaentrega, p.iduseradd, p.dateadd, p.idusermod, p.datemod \r\n" + 
			"			from pedidos_inv p left join producto_inv i on p.nombreproducto = i.nombre and p.idsucursal = i.idsucursal inner join producto_inv ie on p.nombreproducto = ie.nombre where p.idsucursal = :idsucursal and ie.idsucursal = 6 and p.estado = 'Solicitado'", nativeQuery = true)
	List<Object[]> getListPedidosBySucursal(@Param("idsucursal") long idsucursal);
}
