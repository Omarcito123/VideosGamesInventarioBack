package com.apirest.videogame.interfaces;

import java.util.List;
import java.util.Optional;

import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Users;
import com.apirest.videogame.model.UsersResponse;

public interface IUserService {
	public ResponseObject createUser(Users user);
	public Users authenticate(String username, String password);
    public List<Users> getUser();
    public List<UsersResponse> getUsersList();
    public Optional<Users> findById(long id);
    public ResponseObject update(long id);
    public ResponseObject deleteUserById(long id);
    public Users findUserByName(String username);
    public List<UsersResponse> findUserByNameList(String username);
    public ResponseObject passForget(String username, String email, String phone, String password);
    public ResponseObject changePass(String username, String passold, String password);
}