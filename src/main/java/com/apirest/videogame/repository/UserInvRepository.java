package com.apirest.videogame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apirest.videogame.model.UserInterface;
import com.apirest.videogame.model.UserInv;

public interface UserInvRepository extends CrudRepository<UserInv, Long>{
	@Query(value = "select * from user_inv where idsucursal = ?1 and active = 1", nativeQuery = true)
	List<UserInv> getUsersListBySucursal(int idSucursal);
	
	@Query(value = "select * from user_inv where BINARY username = ?1 and pass = ?2 and active = 1", nativeQuery = true)
    UserInv authenticate(String username, String password);
	
	@Query(value = "select p.iduser, p.firstname, p.secondname, p.surname, p.secondsurname, p.email, p.phone, p.username, p.dateborn, IFNULL(u.username, 'Sistema') as useradd, p.dateadd, q.rolname as rol from user_inv p inner join roles q on p.idrol = q.idrol left join users u on p.iduseradd = u.iduser where p.active = 1 order by p.iduser desc", nativeQuery = true)
	List<Object[]> getUsersList();
	
	@Query(value = "select * from user_inv where username = ?1 and active = 1", nativeQuery = true)
	UserInv findByUserName(String username);
	
	@Query(value = "select p.iduser, p.firstname, p.secondname, p.surname, p.secondsurname, p.email, p.phone, p.username, p.dateborn, IFNULL(u.username, 'Sistema') as useradd, p.dateadd, q.rolname as rol from user_inv p inner join roles q on p.idrol = q.idrol left join users u on p.iduseradd = u.iduser where p.username like %?1% and p.active = 1 order by p.iduser desc", nativeQuery = true)
	List<Object[]> findUserByNameList(String username);
	
	@Query(value = "select * from user_inv where BINARY username = ?1 and email = ?2 and phone = ?3 and active = 1;", nativeQuery = true)
	UserInv passForget(String username, String email, String phone);
	
	@Query(value = "select * from user_inv where BINARY iduser = ?1 and pass = ?2 and active = 1", nativeQuery = true)
	UserInv changePass(long id, String pass);
	
	@Query(value = "select u.iduser, u.firstname, u.secondname, u.surname, u.secondsurname, u.username, u.pass, u.email, u.phone, u.dateborn, s.nombre, r.rolname from user_inv u inner join sucursales s on u.idsucursal = s.idsucursal inner join roles r on u.idrol = r.idrol where u.iduser = ?1 and active = 1", nativeQuery = true)
	List<Object[]> getInfoPerfil(long id);
	
	@Query(value = "select u.iduser as iduser, u.active as active, u.dateadd as dateadd, u.dateborn as dateborn,\r\n" + 
			"u.datemod as datemod, u.email as email, u.firstname as firstname, u.idrol as idrol, u.idsucursal as idsucursal,\r\n" + 
			"u.iduseradd as iduseradd, u.idusermod as idusermod, u.pass as pass, u.phone as phone, u.rolname as rolname,\r\n" + 
			"u.secondname as secondname, u.secondsurname as secondsurname, u.surname as surname, u.username as username, s.nombre as nombresucursal \r\n" + 
			"from user_inv u inner join sucursales s on u.idsucursal = s.idsucursal", nativeQuery = true)
	List<UserInterface> getUsersSucurList();
	
	@Query(value = "select u.iduser as iduser, u.active as active, u.dateadd as dateadd, u.dateborn as dateborn,\r\n" + 
			"u.datemod as datemod, u.email as email, u.firstname as firstname, u.idrol as idrol, u.idsucursal as idsucursal,\r\n" + 
			"u.iduseradd as iduseradd, u.idusermod as idusermod, u.pass as pass, u.phone as phone, u.rolname as rolname,\r\n" + 
			"u.secondname as secondname, u.secondsurname as secondsurname, u.surname as surname, u.username as username, s.nombre as nombresucursal \r\n" + 
			"from user_inv u inner join sucursales s on u.idsucursal = s.idsucursal where s.nombre = 'Bodega'", nativeQuery = true)
	List<UserInterface> getUsersBodegaList();
}
