package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ReparacionDetInv;
import com.apirest.videogame.model.ResponseObject;

public interface IReparacionDetInvService {
	public ResponseObject createReparacionDet(ReparacionDetInv rep);
    public ResponseObject findReparacionDetById(ReparacionDetInv rep);
    public ResponseObject findReparacionDetByIdEnc(ReparacionDetInv rep);
    public ResponseObject findDetallesByIdEnc(ReparacionDetInv rep);
    public ResponseObject updateReparacionDet(ReparacionDetInv rep);
    public ResponseObject deleteReparacionDetById(ReparacionDetInv rep);
}