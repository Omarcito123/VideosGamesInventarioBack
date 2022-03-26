package com.apirest.videogame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IUserInvService;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.UserInv;
import com.apirest.videogame.model.paramsGeneric;

@RestController
@RequestMapping("/api/userInv")
public class UserInvController {
	
	@Autowired
	IUserInvService userInvService;

	@GetMapping("/getUsersList")
	public ResponseObject getUsersList() {
		return userInvService.getUsersList();
	}
	
	@GetMapping("/getUsersSucurList")
	public ResponseObject getUsersSucurList() {
		return userInvService.getUsersSucurList();
	}
	
	@PostMapping("/getUsersListBySucursal")
	public ResponseObject getUsersListBySucursal(@RequestBody UserInv user) {
		return userInvService.getUsersListBySucursal(user);
	}
	
	@GetMapping("/getUsersBodegaList")
	public ResponseObject getUsersBodegaList() {
		return userInvService.getUsersBodegaList();
	}
	
	@PostMapping("/saveUserInv")
	public ResponseObject saveUserInv(@RequestBody UserInv user) {
		return userInvService.createUser(user);
	}
	
	@PutMapping("/updateUserInv")
	public ResponseObject updateUserInv(@RequestBody UserInv user) {
		return userInvService.update(user);
	}
	
	@PostMapping("/getInfoPerfil")
	public ResponseObject getInfoPerfil(@RequestBody UserInv user) {
		return userInvService.getInfoPerfil(user.getIduser());
	}
	
	@PostMapping("/updatePerfilInv")
	public ResponseObject updatePerfilInv(@RequestBody UserInv user) {
		return userInvService.updatePerfilInv(user);
	}
	
	@PostMapping("/changePass")
	public ResponseObject changePass(@RequestBody paramsGeneric params) {
		return userInvService.changePass(params.getId(), params.getPassActual(), params.getNewPass());
	}
	
	@PostMapping("/deleteUserInv")
	public ResponseObject deleteUserInv(@RequestBody UserInv user) {
		return userInvService.deleteUserById(user.getIduser());
	}
}
