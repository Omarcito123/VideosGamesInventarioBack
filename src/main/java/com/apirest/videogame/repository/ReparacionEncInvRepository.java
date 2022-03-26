package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.ReparacionEncInv;

public interface ReparacionEncInvRepository extends CrudRepository<ReparacionEncInv, Long>{	
	@Query(value = "select * from reparacion_enc_inv where idreparacionenc = :idreparacionenc and estado = :estado order by idreparacionenc desc LIMIT 1" , nativeQuery = true)
	ReparacionEncInv findReparacionByIdAndStatus(@Param("idreparacionenc") long idreparacionenc, @Param("estado") boolean estado);
	
	@Query(value = "select * from reparacion_enc_inv where idsucursal = :idsucursal and estado = :estado order by idreparacionenc desc" , nativeQuery = true)
	List<ReparacionEncInv> findAllByStatusAndSucursal(@Param("estado") boolean estado, @Param("idsucursal") long idsucursal);
}
