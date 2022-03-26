package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.CompraInv;
import com.apirest.videogame.model.ResponseObject;

public interface ICompraInvService {
	public ResponseObject createCompra(CompraInv[] compraList);
    public ResponseObject getListComprasBySucursal(CompraInv compra);
    public ResponseObject getListComprasBySucursalAndByVendedor(CompraInv compra);
    public ResponseObject getListComprasBySucursalAndByVendedorMensuales(CompraInv compra);
    public ResponseObject findCompraById(CompraInv compra);
    public ResponseObject editCompra(CompraInv compra);
    public ResponseObject deleteCompraById(CompraInv compra);
    public ResponseObject getComprasReporte(CompraInv compra);
}