package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ReparacionEncInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReparacionEncInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreparacionenc")
	private long idreparacionenc;
	@Column(name = "nombrecliente")
	private String nombrecliente;
	@Column(name = "telefonocliente")
	private String telefonocliente;
	@Column(name = "duicliente")
	private String duicliente;
	@Column(name = "recibo")
	private String recibo;
	@Column(name = "descripcion")
	private String descripcion;	
	@Column(name = "idsucursal")
	private long idsucursal;
	@Column(name = "estado")
	private boolean estado;
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdreparacionenc() {
		return idreparacionenc;
	}
	public void setIdreparacionenc(long idreparacionenc) {
		this.idreparacionenc = idreparacionenc;
	}
	public String getNombrecliente() {
		return nombrecliente;
	}
	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}
	public String getTelefonocliente() {
		return telefonocliente;
	}
	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}
	public String getDuicliente() {
		return duicliente;
	}
	public void setDuicliente(String duicliente) {
		this.duicliente = duicliente;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
}
