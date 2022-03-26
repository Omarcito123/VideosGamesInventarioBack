package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apirest.videogame.model.Users;

public interface UserRespository extends CrudRepository<Users, Long>{
	@Query(value = "select * from users where BINARY username = ?1 and pass = ?2 and active = 1", nativeQuery = true)
    Users authenticate(String username, String password);
	
	@Query(value = "select p.iduser, p.firstname, p.secondname, p.surname, p.secondsurname, p.email, p.phone, p.username, p.dateborn, IFNULL(u.username, 'Sistema') as useradd, p.dateadd, q.rolname as rol from users p inner join roles q on p.idrol = q.idrol left join users u on p.iduseradd = u.iduser where p.active = 1 order by p.iduser desc", nativeQuery = true)
	List<Object[]> getUsersList();
	
	@Query(value = "select * from users where username = ?1 and active = 1", nativeQuery = true)
	Users findByUserName(String username);
	
	@Query(value = "select p.iduser, p.firstname, p.secondname, p.surname, p.secondsurname, p.email, p.phone, p.username, p.dateborn, IFNULL(u.username, 'Sistema') as useradd, p.dateadd, q.rolname as rol from users p inner join roles q on p.idrol = q.idrol left join users u on p.iduseradd = u.iduser where p.username like %?1% and p.active = 1 order by p.iduser desc", nativeQuery = true)
	List<Object[]> findUserByNameList(String username);
	
	@Query(value = "select * from users where BINARY username = ?1 and email = ?2 and phone = ?3 and active = 1;", nativeQuery = true)
	Users passForget(String username, String email, String phone);
	
	@Query(value = "select * from users where BINARY username = ?1 and pass = ?2 and active = 1", nativeQuery = true)
	Users changePass(String username, String passwordold);
}