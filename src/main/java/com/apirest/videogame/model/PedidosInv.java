package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PedidosInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PedidosInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpedidoinv")
	private long idpedidoinv;	
	@Column(name = "estado")
	private String estado;	
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "existencia")
	private int existencia;
	@Column(name = "existenciabodega")
	private int existenciabodega;
	@Column(name = "idprodinv")
	private int idprodinv;
	@Column(name = "nombreproducto")
	private String nombreproducto;
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "fechaentrega")
	private String fechaentrega;
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdpedidoinv() {
		return idpedidoinv;
	}
	public void setIdpedidoinv(long idpedidoinv) {
		this.idpedidoinv = idpedidoinv;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public int getExistenciabodega() {
		return existenciabodega;
	}
	public void setExistenciabodega(int existenciabodega) {
		this.existenciabodega = existenciabodega;
	}
	public int getIdprodinv() {
		return idprodinv;
	}
	public void setIdprodinv(int idprodinv) {
		this.idprodinv = idprodinv;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public long getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(long idsucursal) {
		this.idsucursal = idsucursal;
	}
	public String getFechaentrega() {
		return fechaentrega;
	}
	public void setFechaentrega(String fechaentrega) {
		this.fechaentrega = fechaentrega;
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
}
