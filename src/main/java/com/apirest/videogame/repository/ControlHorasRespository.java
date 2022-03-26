package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.ControlHorasInv;

public interface ControlHorasRespository extends CrudRepository<ControlHorasInv, Long>{
	@Query(value = "select * from control_horas_inv where iduser = :idUser AND DATE(datecontrol) = :fecha order by idcontrol desc", nativeQuery = true)
	List<ControlHorasInv> getControlByUserFecha(@Param("idUser") long idUser, @Param("fecha") String fecha);
	
	@Query(value = "select * from control_horas_inv where iduser = :idUser AND DATE(datecontrol) = :fecha and turno = :turno order by idcontrol desc LIMIT 1", nativeQuery = true)
	ControlHorasInv getControlByUserFechaTurno(@Param("idUser") long idUser, @Param("fecha") String fecha, @Param("turno") String turno);
	
	@Query(value = "select * from control_horas_inv where iduser = :idUser and MONTH(TIMESTAMP(dateadd)) = :fecha order by dateadd desc", nativeQuery = true)
	List<ControlHorasInv> getControlByUserMonth(@Param("idUser") long idUser, @Param("fecha") String fecha);
}