package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.NotasInv;

public interface NotasInvRepository extends CrudRepository<NotasInv, Long>{	
	@Query(value = "select n.idnota as idnota, n.nota as nota, n.iduseradd as iduseradd, n.dateadd as dateadd, \r\n" + 
			"			n.idusermod as idusermod, n.datemod as datemod, CONCAT(u.firstname, ' ', u.surname) as usuario\r\n" + 
			"			from notas_inv n inner join user_inv u\r\n" + 
			"			on n.iduseradd = u.iduser where n.iduseradd = :iduser and DATE(n.dateadd) BETWEEN  :fechainicio AND :fechafin order by n.idnota desc" , nativeQuery = true)
	List<Object[]> getNotasListByUserAndDates(@Param("iduser") long iduser, @Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
}
