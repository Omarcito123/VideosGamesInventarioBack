package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.CajaInv;
import com.apirest.videogame.model.ResponseObject;

public interface ICajaInvService {
	public ResponseObject createCajaInicial(CajaInv caja);
    public ResponseObject findCajasMensuales(CajaInv caja);
    public ResponseObject findCajaByDateAndSucursal(CajaInv caja);
    public ResponseObject findCajaByDateAndSucursalAyer(CajaInv caja);
    public ResponseObject updateCajaIC(CajaInv caja);
    public ResponseObject updateCajaFS(CajaInv caja);
    public ResponseObject saveInicioCajaMonedas(CajaInv caja);
    public ResponseObject saveMonedas(CajaInv caja);
    public ResponseObject updateInicioCierreCaja(CajaInv caja);
    public ResponseObject deleteCajaById(CajaInv caja);
}