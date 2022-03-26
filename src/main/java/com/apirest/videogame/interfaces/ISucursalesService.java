package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.Sucursales;

public interface ISucursalesService {
	public ResponseObject getSucursales();
	public ResponseObject saveSucursal(Sucursales sucu);
	public ResponseObject getSucursalById(Sucursales sucu);
	public ResponseObject updateSucursal(Sucursales sucu);
	public ResponseObject deleteSucursal(Sucursales sucu);
	public ResponseObject getIdSucursalBodeg();
}