package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ReparacionEncInv;
import com.apirest.videogame.model.ResponseObject;

public interface IReparacionEncInvService {
	public ResponseObject createReparacionEnc(ReparacionEncInv rep);
	public ResponseObject findAllByStatusAndSucursal(ReparacionEncInv rep);
    public ResponseObject findReparacionByIdAndStatus(ReparacionEncInv rep);
    public ResponseObject updateReparacionEnc(ReparacionEncInv rep);
    public ResponseObject deleteReparacionEncById(ReparacionEncInv rep);
}