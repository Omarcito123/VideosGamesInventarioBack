package com.apirest.videogame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IUserService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Users;
import com.apirest.videogame.model.UsersResponse;
import com.apirest.videogame.repository.UserRespository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UserService implements IUserService {

	@Autowired
	UserRespository userRespository;
	
	@Autowired
	IntegrationLogService integrationLogService;
	
	String errorEx = "";

	@Override
	public ResponseObject createUser(Users user) {
		ResponseObject responseObject = new ResponseObject();
		Users userExist = new Users();
		try {
			userExist = userRespository.findByUserName(user.getUsername());

			if (userExist == null) {
				user.setActive(1);
				userRespository.save(user);
				responseObject.setMessage("Usuario creado exitosamente");
				responseObject.setSuccess(true);
				return responseObject;
			} else {
				responseObject.setMessage("Por favor utiliza otro username");
				responseObject.setSuccess(false);
				return responseObject;
			}
		} catch (Exception e) {
			/*responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createUser");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(user.getFirstname() + " " + user.getSecondname() + " " + user.getSurname() + " " + user.getSecondsurname() + " " + user.getEmail() + " " + user.getPhone() + " " + user.getUsername() + " " + user.getPass() + " " + user.getDateborn() + " " + user.getIduseradd());
			erroresRespository.save(error);*/
			return responseObject;
		}
	}

	@Override
	public List<Users> getUser() {
		List<Users> usersList = new ArrayList<Users>();
		try {
			return (List<Users>) userRespository.findAll();
		} catch (Exception e) {
			/*Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("createUser");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("Simple select");
			erroresRespository.save(error);*/
			return usersList;
		}
	}

	@Override
	public Optional<Users> findById(long id) {
		Optional<Users> user = null;
		try {
			return userRespository.findById(id);
		} catch (Exception e) {
			/*Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findByIdUser");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: "+ id);
			erroresRespository.save(error);*/
			return user;
		}
	}

	@Override
	public ResponseObject update(long id) {
		ResponseObject responseObject = new ResponseObject();
		Users userSave = new Users();
		try {
			Optional<Users> user = userRespository.findById(id);
			if (user != null) {
				userSave.setIduser(user.get().getIduser());
				userSave.setFirstname(user.get().getFirstname());
				userSave.setSecondname(user.get().getSecondname());
				userSave.setSurname(user.get().getSurname());
				userSave.setSecondsurname(user.get().getSecondsurname());
				userSave.setEmail(user.get().getEmail());
				userSave.setPass(user.get().getPass());
				userSave.setPhone(user.get().getPhone());
				userSave.setUsername(user.get().getUsername());
				userSave.setDateborn(user.get().getDateborn());
				userSave.setIduseradd(user.get().getIduseradd());
				userSave.setDateadd(user.get().getDateadd());
				userSave.setIdusermod(user.get().getIdusermod());
				userSave.setDatemod(user.get().getDatemod());
				userSave.setIdrol(user.get().getIdrol());
				userSave.setActive(0);
				userRespository.save(userSave);
				responseObject.setMessage("Usuario actualizado exitosamente");
				responseObject.setSuccess(true);
			} else {
				responseObject.setMessage("Usuario no pudo ser actualizado");
				responseObject.setSuccess(false);
			}
			return responseObject;
		} catch (Exception e) {
			/*responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("updateUser");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: "+ id);
			erroresRespository.save(error);*/
			return responseObject;
		}
	}

	@Override
	public ResponseObject passForget(String username, String email, String phone, String password) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Users user = userRespository.passForget(username, email, phone);
			if(user != null) {
				user.setPass(password);
				userRespository.save(user);
				responseObject.setMessage("Contraseña actualizada exitosamente");
				responseObject.setSuccess(true);
				return responseObject;
			}else {
				responseObject.setMessage("El correo o numero de telefono es incorrecto");
				responseObject.setSuccess(false);
				return responseObject;
			}
		}catch(Exception e) {
			/*responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("passForget");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(username + " " + email + " " + phone + " " + password);
			erroresRespository.save(error);*/
			return responseObject;
		}
	}
	
	@Override
	public ResponseObject changePass(String username, String passold, String password) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Users user = userRespository.changePass(username, passold);
			if(user != null) {
				user.setPass(password);
				userRespository.save(user);
				responseObject.setMessage("Contraseña actualizada exitosamente");
				responseObject.setSuccess(true);
				return responseObject;
			}else {
				responseObject.setMessage("La contaseña actual ingresa no es correcta");
				responseObject.setSuccess(false);
				return responseObject;
			}
		}catch(Exception e) {
			/*responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("changePass");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros(username + " " + passold + " " + password);
			erroresRespository.save(error);*/
			return responseObject;
		}
	}
	
	@Override
	public ResponseObject deleteUserById(long id) {
		ResponseObject responseObject = new ResponseObject();
		try {
			userRespository.deleteById(id);
			responseObject.setMessage("Usuario eliminado exitosamente");
			responseObject.setSuccess(true);
			return responseObject;
		} catch (Exception e) {
			/*responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
			Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("deleteUserById");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("id: " + id);
			erroresRespository.save(error);*/
			return responseObject;
		}
	}

	@Override
	public Users authenticate(String username, String password) {
		Users user = new Users();
		try {
			user = userRespository.authenticate(username, password);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			user = new Users();
			user.setFirstname("Error");
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("authenticate");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("user: " + username + " pass: " + password);
			integrationLogService.save(error);
		} 
		return user;
	}

	public Users findUserByName(String username) {
		Users user = new Users();
		try {
			user = userRespository.findByUserName(username);
			if (user != null) {
				return user;
			} else {
				return user = new Users();
			}
		} catch (Exception e) {
			/*Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findUserByName");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("user: " + username);
			erroresRespository.save(error);*/
			return user = new Users();
		}
	}

	@Override
	public List<UsersResponse> getUsersList() {
		List<UsersResponse> usersList = new ArrayList<UsersResponse>();
		try {
			List<Object[]> objeto = userRespository.getUsersList();

			for (int i = 0; i < objeto.size(); i++) {
				UsersResponse user = new UsersResponse();
				Object[] obj = objeto.get(i);
				user.setIduser(obj[0].toString());
				user.setFirstname(obj[1].toString());
				user.setSecondname(obj[2].toString());
				user.setSurname(obj[3].toString());
				user.setSecondsurname(obj[4].toString());
				user.setEmail(obj[5].toString());
				user.setPhone(obj[6].toString());
				user.setUsername(obj[7].toString());
				user.setDateborn(obj[8].toString());
				user.setUseradd(obj[9].toString());
				user.setDateadd(obj[10].toString());
				user.setRolname(obj[11].toString());
				usersList.add(user);
			}
			return usersList;
		} catch (Exception e) {
			/*Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findUserByName");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("Simple select");
			erroresRespository.save(error);*/
			return usersList = new ArrayList<UsersResponse>();
		}
	}

	@Override
	public List<UsersResponse> findUserByNameList(String userName) {
		List<UsersResponse> usersList = new ArrayList<UsersResponse>();
		try {
			List<Object[]> objeto = userRespository.findUserByNameList(userName);

			for (int i = 0; i < objeto.size(); i++) {
				UsersResponse user = new UsersResponse();
				Object[] obj = objeto.get(i);
				user.setIduser(obj[0].toString());
				user.setFirstname(obj[1].toString());
				user.setSecondname(obj[2].toString());
				user.setSurname(obj[3].toString());
				user.setSecondsurname(obj[4].toString());
				user.setEmail(obj[5].toString());
				user.setPhone(obj[6].toString());
				user.setUsername(obj[7].toString());
				user.setDateborn(obj[8].toString());
				user.setUseradd(obj[9].toString());
				user.setDateadd(obj[10].toString());
				user.setRolname(obj[11].toString());
				usersList.add(user);
			}
			return usersList;
		} catch (Exception e) {
			/*Errores error = new Errores();
			error.setMensaje(e.getMessage());
			error.setServicio("findUserByName");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			error.setDateadd(dtf.format(now));
			error.setParametros("Simple select");
			erroresRespository.save(error);*/
			return usersList = new ArrayList<UsersResponse>();
		}
	}
}