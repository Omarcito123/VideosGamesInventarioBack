package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CuponInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CuponInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcupon")
	private long idcupon;
	@Column(name = "categoria")
	private String categoria;	
	@Column(name = "cupon")
	private String cupon;	
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "tipocupon")
	private String tipocupon;
	@Column(name = "limiteuso")
	private long limiteuso;
	@Column(name = "numerouso")
	private long numerouso;
	@Column(name = "fechavalido")
	private String fechavalido;	
	@Column(name = "descuento")
	private double descuento;	
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdcupon() {
		return idcupon;
	}
	public void setIdcupon(long idcupon) {
		this.idcupon = idcupon;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCupon() {
		return cupon;
	}
	public void setCupon(String cupon) {
		this.cupon = cupon;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechavalido() {
		return fechavalido;
	}
	public void setFechavalido(String fechavalido) {
		this.fechavalido = fechavalido;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
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
	public String getTipocupon() {
		return tipocupon;
	}
	public void setTipocupon(String tipocupon) {
		this.tipocupon = tipocupon;
	}
	public long getLimiteuso() {
		return limiteuso;
	}
	public void setLimiteuso(long limiteuso) {
		this.limiteuso = limiteuso;
	}
	public long getNumerouso() {
		return numerouso;
	}
	public void setNumerouso(long numerouso) {
		this.numerouso = numerouso;
	}
}
