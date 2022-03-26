package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ComisionVendedorInv;
import com.apirest.videogame.model.ResponseObject;

public interface IComisionVendedorInvService {
	public ResponseObject createComisionVendedor(ComisionVendedorInv comisionV);
    public ResponseObject getComisionesByVendedorList(ComisionVendedorInv comisionV);
    public ResponseObject getComisionesByVendedorAndSucursalList(ComisionVendedorInv comisionV);
    public ResponseObject getComisionesByIdVenta(long id);
    public ResponseObject findComisionVendedorById(ComisionVendedorInv comisionV);
    public ResponseObject editComisionVendedor(ComisionVendedorInv comisionV);
    public ResponseObject deleteComisionVendedorById(ComisionVendedorInv comisionV);
}