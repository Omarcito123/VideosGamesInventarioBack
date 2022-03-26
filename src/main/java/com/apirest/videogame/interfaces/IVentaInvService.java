package com.apirest.videogame.interfaces;

import com.apirest.videogame.model.ResponseObject;
import com.apirest.videogame.model.VentaInv;

public interface IVentaInvService {
	public ResponseObject createVenta(VentaInv[] ventaList);
	public ResponseObject getListVentaMensualBySucursal(VentaInv venta);
	public ResponseObject getListVentaReservaBySucursal (VentaInv venta);
    public ResponseObject getListVentaBySucursal(VentaInv venta);
    public ResponseObject getListVentaBySucursalAndByVendedor(VentaInv venta);
    public ResponseObject getListVentaReservaBySucursalAndByVendedor(VentaInv venta);
    public ResponseObject findVentaById(VentaInv venta);
    public ResponseObject editVenta(VentaInv venta);
    public ResponseObject deleteVentaById(VentaInv venta);
    public ResponseObject entregarReserva(VentaInv venta);
    public ResponseObject getVentasReporte(VentaInv venta);
    public ResponseObject getListVentaReservaOnlyBySucursal(VentaInv venta);
}