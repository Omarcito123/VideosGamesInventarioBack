package com.apirest.videogame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.videogame.interfaces.IUserService;
import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Users;
import com.apirest.videogame.model.UsersResponse;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	IUserService userService;

	@PostMapping("/getUsersList")
	public List<UsersResponse> getUsersList() {
		return userService.getUsersList();
	}
	
	@PostMapping("/saveUsers")
	public ResponseObject createUser(@RequestBody Users user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/changePass")
	public ResponseObject changePass(@RequestHeader("username") String username, @RequestHeader("passold") String passold, @RequestHeader("newpass") String newpass) {
		return userService.changePass(username, passold, newpass);
	}
	
	@PostMapping("/findUserByName")
	public Users createUser(@RequestHeader("username") String username) {
		return userService.findUserByName(username);
	}
	
	@PostMapping("/findUserByNameList")
	public List<UsersResponse> findUserByNameList(@RequestHeader("username") String username) {
		return userService.findUserByNameList(username);
	}
	
	@PostMapping("/deleteUserById")
	public ResponseObject deleteUserById(@RequestHeader("iduser") Long iduser) {
		return userService.deleteUserById(iduser);
	}
	
	@PostMapping("/desactiveUserById")
	public ResponseObject update(@RequestHeader("iduser") Long iduser) {
		return userService.update(iduser);
	}
}
