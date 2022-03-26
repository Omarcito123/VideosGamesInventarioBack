package com.apirest.videogame.model;

public class ComprasReporte {
	private long idcompra;
    private String factura;
	private String recibo;
    private String nombre;
	private long cantidad;
    private double preciounidad;
	private double total;
	private String vendedor;
	private String fechaventa;
	
	public long getIdcompra() {
		return idcompra;
	}
	public void setIdcompra(long idcompra) {
		this.idcompra = idcompra;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public double getPreciounidad() {
		return preciounidad;
	}
	public void setPreciounidad(double preciounidad) {
		this.preciounidad = preciounidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getFechaventa() {
		return fechaventa;
	}
	public void setFechaventa(String fechaventa) {
		this.fechaventa = fechaventa;
	}
}
