package com.apirest.videogame.interfaces;

import java.util.List;
import java.util.Optional;

import com.apirest.videogame.model.Canjes;
import com.apirest.videogame.model.CanjesResponse;
import com.apirest.videogame.model.ResponseObject;

public interface ICanjesService {
	public ResponseObject createCanjes(Canjes canjes);
    public List<Canjes> getCanjes();
    public List<CanjesResponse> getListCanjes(String idUser, String idRol);
    public Optional<Canjes> findById(long id);
    public boolean update(Canjes canjes);
    public ResponseObject deleteCanjesById(long id);
}