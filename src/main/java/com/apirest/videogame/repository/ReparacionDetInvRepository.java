package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.ReparacionDetInv;

public interface ReparacionDetInvRepository extends CrudRepository<ReparacionDetInv, Long>{	
	@Query(value = "select * from reparacion_det_inv where idreparaciondet = :idreparaciondet and idreparacionenc = :idreparacionenc order by idreparacionenc" , nativeQuery = true)
	List<ReparacionDetInv> findReparacionDetByIdEnc(@Param("idreparaciondet") long idreparaciondet, @Param("idreparacionenc") long idreparacionenc);
	
	@Query(value = "select * from reparacion_det_inv where idreparacionenc = :idreparacionenc order by idreparacionenc" , nativeQuery = true)
	List<ReparacionDetInv> findDetallesByIdEnc(@Param("idreparacionenc") long idreparacionenc);
}
