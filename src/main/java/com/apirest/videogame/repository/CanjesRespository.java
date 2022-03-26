package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apirest.videogame.model.Canjes;

public interface CanjesRespository extends CrudRepository<Canjes, Long>{
	@Query(value = "select p.idcanje, u.iduser, p.idproduct, p.nameproduct, p.pointsprodct, p.pointuser, p.totalpointuser, p.iduseradd, p.dateadd, q.username as useradd, u.username from canjes p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser order by p.idcanje desc" , nativeQuery = true)
	List<Object[]> getListCanjes();
	
	@Query(value = "select p.idcanje, u.iduser, p.idproduct, p.nameproduct, p.pointsprodct, p.pointuser, p.totalpointuser, p.iduseradd, p.dateadd, q.username as useradd, u.username from canjes p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser where p.iduser = :idUser OR p.iduseradd = :idUser order by p.idcanje desc", nativeQuery = true)
	List<Object[]> getListCanjesVendedor(@Param("idUser") long idUser);
	
	@Query(value = "select p.idcanje, u.iduser, p.idproduct, p.nameproduct, p.pointsprodct, p.pointuser, p.totalpointuser, p.iduseradd, p.dateadd, q.username as useradd, u.username from canjes p inner join users u on p.iduser = u.iduser inner join users q on p.iduseradd = q.iduser where p.iduser = :idUser order by p.idcanje desc", nativeQuery = true)
	List<Object[]> getListCanjesUser(@Param("idUser") long idUser);
}