package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.HistorialCajaInv;
import com.apirest.videogame.model.ResponseObject;

public interface IHistorialCajaInvService {
	public ResponseObject createHistorialCaja(HistorialCajaInv caja);
    public ResponseObject findCajasMensuales(HistorialCajaInv caja);
    public ResponseObject findCajaByDateAndSucursal(HistorialCajaInv caja);
    public ResponseObject findCajaByRangoDateAndSucursal(HistorialCajaInv caja);
    public ResponseObject updateHistorialCaja(HistorialCajaInv caja);
    public ResponseObject deleteHistorialCajaById(HistorialCajaInv caja);
}