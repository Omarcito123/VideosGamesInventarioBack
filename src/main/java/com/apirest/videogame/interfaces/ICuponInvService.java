package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.CuponInv;
import com.apirest.videogame.model.ResponseObject;

public interface ICuponInvService {
	public ResponseObject createCupon(CuponInv cupon);
    public ResponseObject getCuponesList();
    public ResponseObject findCuponById(CuponInv cupon);
    public ResponseObject findCuponByCodigoAndDate(CuponInv cupon);
    public ResponseObject editCupon(CuponInv cupon);
    public ResponseObject deleteCuponById(CuponInv cupon);
}