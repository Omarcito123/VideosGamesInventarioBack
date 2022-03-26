package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.Points;

public interface PointsRespository extends CrudRepository<Points, Long>{
	String JDBC_MAX_ROWS = "idpoint";

	@Query(value = "select * from points where iduser = :idUser order by idpoint DESC limit :limit", nativeQuery = true)
	List<Points> pointsByUser(@Param("idUser") long idUser, @Param("limit") int limit);
	
	@Query(value = "select p.idpoint, u.username, p.points, p.totals, p.codefactura, p.description, q.username as useradd, p.dateadd from points p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser order by p.idpoint desc", nativeQuery = true)
	List<Object[]> getListPoints();
	
	@Query(value = "select p.idpoint, u.username, p.points, p.totals, p.codefactura, p.description, q.username as useradd, p.dateadd from points p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser where p.iduser = :idUser OR p.iduseradd = :idUser order by p.idpoint desc", nativeQuery = true)
	List<Object[]> getListPointsVendedor(@Param("idUser") long idUser);
	
	@Query(value = "select p.idpoint, u.username, p.points, p.totals, p.codefactura, p.description, q.username as useradd, p.dateadd from points p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser where p.iduser = :idUser order by p.idpoint desc", nativeQuery = true)
	List<Object[]> getListPointsUser(@Param("idUser") long idUser);
	
	@Query(value = "select * from points where iduser = :idUser and points = :points and totals = :totals and codefactura = :codefactura and description = :description and iduseradd = :iduseradd and dateadd = :dateadd and datemod = :datemod", nativeQuery = true)
	Points pointsByCanje(@Param("idUser") long idUser, @Param("points") int points, @Param("totals") int totals, @Param("codefactura") String codefactura, @Param("description") String description, @Param("iduseradd") int iduseradd, @Param("dateadd") String dateadd, @Param("datemod") String datemod);
}