package com.apirest.videogame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.videogame.interfaces.IUserInvService;
import com.apirest.videogame.model.Errores;
import com.apirest.videogame.model.PerfilInv;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Roles;
import com.apirest.videogame.model.UserInterface;
import com.apirest.videogame.model.UserInv;
import com.apirest.videogame.model.UsersResponse;
import com.apirest.videogame.repository.RolesRespository;
import com.apirest.videogame.repository.UserInvRepository;
import com.apirest.videogame.utils.metodosGenericos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UserInvService implements IUserInvService {

	@Autowired
	UserInvRepository userInvRespository;
	
	@Autowired
	RolesRespository rolesRepository;
	
	@Autowired
	IntegrationLogService integrationLogService;
	
	String errorEx = "";

	@Override
	public ResponseObject createUser(UserInv user) {
		ResponseObject responseObject = new ResponseObject();
		UserInv userExist = new UserInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<Roles> rol = rolesRepository.findById((long) user.getIdrol());
			if(rol.isPresent()) {
				userExist = userInvRespository.findByUserName(user.getUsername());
				if (userExist == null) {
					user.setActive(1);
					user.setDateadd(fechaSal.getFecha());
					user.setRolname(rol.get().getRolname());
					userInvRespository.save(user);
					responseObject.setState("Success");
					responseObject.setMessage("Usuario creado exitosamente");
					responseObject.setSuccess(true);				
				} else {
					responseObject.setState("Error");
					responseObject.setMessage("Por favor utiliza otro username");
					responseObject.setSuccess(false);
				}
			}else {
				responseObject.setState("Error");
				responseObject.setMessage("No se encontro el rol seleccionado");
				responseObject.setSuccess(false);
			}			
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("createUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + user.getUsername());
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject getUser() {
		ResponseObject responseObject = new ResponseObject();
		List<UserInv> usersList = new ArrayList<UserInv>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			usersList = (List<UserInv>) userInvRespository.findAll();
			responseObject.setState("Success");
			responseObject.setMessage("Usuarios cargados exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(usersList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("no hay parametros");
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject findById(long id) {
		ResponseObject responseObject = new ResponseObject();
		Optional<UserInv> user = null;
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			user =  userInvRespository.findById(id);
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(user);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findByIdUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + id);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getInfoPerfil(long id) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> object =  userInvRespository.getInfoPerfil(id);
			Object[] obj = object.get(0);
			PerfilInv perfil = new PerfilInv();
			perfil.setIduser(obj[0].toString());
			perfil.setFirstname(obj[1].toString());
			perfil.setSecondname(obj[2].toString());
			perfil.setSurname(obj[3].toString());
			perfil.setSecondsurname(obj[4].toString());
			perfil.setEmail(obj[7].toString());
			perfil.setPass(obj[6].toString());
			perfil.setPhone(obj[8].toString());
			perfil.setUsername(obj[5].toString());
			perfil.setDateborn(obj[9].toString());
			perfil.setRol(obj[11].toString());
			perfil.setSucursal(obj[10].toString());
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(perfil);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findByIdUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + id);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject update(UserInv user) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<Roles> rol = rolesRepository.findById((long) user.getIdrol());
			if(rol.isPresent()) {
				Optional<UserInv> userFind = userInvRespository.findById(user.getIduser());
				if (userFind.isPresent()) {
					user.setDatemod(fechaSal.getFecha());
					user.setIduser(userFind.get().getIduser());
					user.setRolname(rol.get().getRolname());
					userInvRespository.save(user);
					responseObject.setState("Success");
					responseObject.setMessage("Usuario actualizado exitosamente");
					responseObject.setSuccess(true);
				} else {
					responseObject.setState("Error");
					responseObject.setMessage("Usuario no pudo ser actualizado");
					responseObject.setSuccess(false);
				}
			}else {
				responseObject.setState("Error");
				responseObject.setMessage("No se encontro el rol seleccionado");
				responseObject.setSuccess(false);
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("updateUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + user.getIduser());
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject passForget(String username, String email, String phone, String password) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			UserInv user = userInvRespository.passForget(username, email, phone);
			if(user != null) {
				user.setPass(password);
				userInvRespository.save(user);
				responseObject.setState("Success");
				responseObject.setMessage("Contraseña actualizada exitosamente");
				responseObject.setSuccess(true);
			}else {
				responseObject.setState("Error");
				responseObject.setMessage("El correo o numero de telefono es incorrecto");
				responseObject.setSuccess(false);
			}
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("passForgetUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + username);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject deleteUserById(long id) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			userInvRespository.deleteById(id);
			responseObject.setState("Success");
			responseObject.setMessage("Usuario eliminado exitosamente");
			responseObject.setSuccess(true);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteUserInvById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("id: " + id);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public UserInv authenticate(String username, String password) {
		UserInv user = new UserInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			user = userInvRespository.authenticate(username, password);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("deleteUserInvById");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + username);
			integrationLogService.save(error);
			user = null;
		} 
		return user;
	}

	public ResponseObject findUserByName(String username) {
		ResponseObject responseObject = new ResponseObject();
		UserInv user = new UserInv();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			user = userInvRespository.findByUserName(username);
			if (user != null) {
				responseObject.setState("Success");
				responseObject.setMessage("Usuario cargado exitosamente");
				responseObject.setSuccess(true);
				responseObject.setData(user);
			} else {
				responseObject.setState("Error");
				responseObject.setMessage("Usuario no encontrado");
				responseObject.setSuccess(false);
			}
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findUserInvByName");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + username);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject getUsersList() {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Iterable<UserInv> objeto = userInvRespository.findAll();
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(objeto);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getUsersInvList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject findUserByNameList(String userName) {
		ResponseObject responseObject = new ResponseObject();
		List<UsersResponse> usersList = new ArrayList<UsersResponse>();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<Object[]> objeto = userInvRespository.findUserByNameList(userName);

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
			responseObject.setState("Success");
			responseObject.setMessage("Usuarios cargados exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(usersList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("findUserInvByNameList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + userName);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject updatePerfilInv(UserInv user) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Optional<UserInv> userU = userInvRespository.findById(user.getIduser());
			if(userU.isPresent()) {
				UserInv updatePerfil = new UserInv();
				updatePerfil.setIduser(userU.get().getIduser());
				updatePerfil.setFirstname(user.getFirstname());
				updatePerfil.setSecondname(user.getSecondname());
				updatePerfil.setSurname(user.getSurname());
				updatePerfil.setSecondsurname(user.getSecondsurname());
				updatePerfil.setEmail(user.getEmail());
				updatePerfil.setPass(userU.get().getPass());
				updatePerfil.setPhone(user.getPhone());
				updatePerfil.setUsername(userU.get().getUsername());
				updatePerfil.setDateborn(user.getDateborn());				
				updatePerfil.setIduseradd(userU.get().getIduseradd());
				updatePerfil.setDateadd(userU.get().getDateadd());
				updatePerfil.setIdusermod(userU.get().getIdusermod());
				updatePerfil.setDatemod(userU.get().getDatemod());				
				updatePerfil.setIdrol(userU.get().getIdrol());
				updatePerfil.setIdsucursal(userU.get().getIdsucursal());
				updatePerfil.setActive(userU.get().getActive());				
				userInvRespository.save(updatePerfil);
				responseObject.setState("Success");
				responseObject.setMessage("Perfil actualizado exitosamente");
				responseObject.setSuccess(true);
			}else {
				responseObject.setState("Error");
				responseObject.setMessage("El perfil no pudo ser actualizado no es correcta");
				responseObject.setSuccess(false);
			}
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("changePassUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("username: " + user.getUsername());
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject changePass(long id, String passold, String newpass) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			UserInv userU = userInvRespository.changePass(id, passold);
			if(userU != null) {
				userU.setPass(newpass);
				userInvRespository.save(userU);
				responseObject.setState("Success");
				responseObject.setMessage("Contraseña actualizada exitosamente");
				responseObject.setSuccess(true);
			}else {
				responseObject.setState("Error");
				responseObject.setMessage("La contaseña actual ingresa no es correcta");
				responseObject.setSuccess(false);
			}
		}catch(Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("changePassUserInv");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("iduser: " + id);
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject getUsersListBySucursal(UserInv user) {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			Iterable<UserInv> objeto = userInvRespository.getUsersListBySucursal(user.getIdsucursal());
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(objeto);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getUsersInvList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}

	@Override
	public ResponseObject getUsersSucurList() {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<UserInterface> userList =  userInvRespository.getUsersSucurList();			
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(userList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getUsersInvList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}
	
	@Override
	public ResponseObject getUsersBodegaList() {
		ResponseObject responseObject = new ResponseObject();
		metodosGenericos fechaSal = new metodosGenericos();
		try {
			List<UserInterface> userList =  userInvRespository.getUsersBodegaList();			
			responseObject.setState("Success");
			responseObject.setMessage("Usuario cargado exitosamente");
			responseObject.setSuccess(true);
			responseObject.setData(userList);
		} catch (Exception e) {
			errorEx = e.getCause().getMessage();
			Errores error = new Errores();
			error.setMensaje(errorEx);
			error.setServicio("getUsersInvList");
			error.setDateadd(fechaSal.getFecha());
			error.setParametros("sin parametros: ");
			integrationLogService.save(error);
			responseObject.setState("Error");
			responseObject.setMessage(e.getMessage());
			responseObject.setSuccess(false);
		}
		return responseObject;
	}
}