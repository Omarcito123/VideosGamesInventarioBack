package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ComisionInv;
import com.apirest.videogame.model.ResponseObject;

public interface IComisionInvService {
	public ResponseObject createComision(ComisionInv comision);
    public ResponseObject getComisionesList();
    public ResponseObject findComisionById(ComisionInv comision);
    public ComisionInv findComisionByTipoComision(String tipocomision);
    public ResponseObject editComision(ComisionInv comision);
    public ResponseObject deleteComisionById(ComisionInv comision);
}