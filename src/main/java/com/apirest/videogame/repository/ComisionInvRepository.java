package com.apirest.videogame.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.ComisionInv;

public interface ComisionInvRepository extends CrudRepository<ComisionInv, Long>{	
	@Query(value = "select * from comision_inv where tipocomision = :tipocomision order by idcomision desc LIMIT 1" , nativeQuery = true)
	ComisionInv findComisionByTipoComision(@Param("tipocomision") String tipocomision);
	
}
