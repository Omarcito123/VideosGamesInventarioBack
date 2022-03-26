package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "VentaInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VentaInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idventa")
	private long idventa;
	@Column(name = "tipopago")
	private String tipopago;
	@Column(name = "tipoventa")
	private String tipoventa;	
	@Column(name = "estadoreserva")
	private String estadoreserva;
	@Column(name = "post")
	private String post;
	@Column(name = "factura")
	private String factura;
	@Column(name = "recibo")
	private String recibo;
	@Column(name = "idproducto")
	private long idproducto;
	@Column(name = "categoria")
	private String categoria;	
	@Column(name = "nombreproducto")
	private String nombreproducto;
	@Column(name = "serie")
	private String serie;	
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "existencia")
	private int existencia;
	@Column(name = "preciounitario")
	private double preciounitario;
	@Column(name = "preciototal")
	private double preciototal;
	@Column(name = "descuento")
	private double descuento;
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdventa() {
		return idventa;
	}
	public void setIdventa(long idventa) {
		this.idventa = idventa;
	}
	public String getTipopago() {
		return tipopago;
	}
	public void setTipopago(String tipopago) {
		this.tipopago = tipopago;
	}
	public String getTipoventa() {
		return tipoventa;
	}
	public void setTipoventa(String tipoventa) {
		this.tipoventa = tipoventa;
	}	
	public String getEstadoreserva() {
		return estadoreserva;
	}
	public void setEstadoreserva(String estadoreserva) {
		this.estadoreserva = estadoreserva;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
	public long getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}	
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
	public double getPreciounitario() {
		return preciounitario;
	}
	public void setPreciounitario(double preciounitario) {
		this.preciounitario = preciounitario;
	}
	public double getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public long getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
	}
	public long getIduseradd() {
		return iduseradd;
	}
	public void setIduseradd(long iduseradd) {
		this.iduseradd = iduseradd;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}
	public long getIdusermod() {
		return idusermod;
	}
	public void setIdusermod(long idusermod) {
		this.idusermod = idusermod;
	}
	public String getDatemod() {
		return datemod;
	}
	public void setDatemod(String datemod) {
		this.datemod = datemod;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
}
