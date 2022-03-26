package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.UserInv;

public interface IUserInvService {
	public ResponseObject createUser(UserInv user);
	public UserInv authenticate(String username, String password);
    public ResponseObject getUser();
    public ResponseObject getUsersList();
    public ResponseObject getUsersSucurList();
    public ResponseObject getUsersBodegaList();
    public ResponseObject getUsersListBySucursal(UserInv user);
    public ResponseObject findById(long id);
    public ResponseObject getInfoPerfil(long id);
    public ResponseObject update(UserInv user);
    public ResponseObject updatePerfilInv(UserInv user);
    public ResponseObject changePass(long id, String passold, String newpass);
    public ResponseObject deleteUserById(long id);
    public ResponseObject findUserByName(String username);
    public ResponseObject findUserByNameList(String username);
    public ResponseObject passForget(String username, String email, String phone, String password);
}