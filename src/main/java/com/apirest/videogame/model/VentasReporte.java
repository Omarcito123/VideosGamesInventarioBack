package com.apirest.videogame.model;

public class VentasReporte {
	private long idventa;
    private String factura;
	private String recibo;
    private String nombreproducto;
	private long cantidad;
    private double preciocosto;
	private double precioventa;
	private double ganancia;
	private String vendedor;
	private String categoria;
	private String fechaventa;
	
	public long getIdventa() {
		return idventa;
	}
	public void setIdventa(long idventa) {
		this.idventa = idventa;
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
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public double getPreciocosto() {
		return preciocosto;
	}
	public void setPreciocosto(double preciocosto) {
		this.preciocosto = preciocosto;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public double getGanancia() {
		return ganancia;
	}
	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFechaventa() {
		return fechaventa;
	}
	public void setFechaventa(String fechaventa) {
		this.fechaventa = fechaventa;
	}
}
