package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ReparacionDetInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ReparacionDetInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreparaciondet")
	private long idreparaciondet;
	@Column(name = "idreparacionenc")
	private long idreparacionenc;
	@Column(name = "nombreproducto")
	private String nombreproducto;
	@Column(name = "idproducto")
	private long idproducto;
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "nota")
	private String nota;
	@Column(name = "categoriaproducto")
	private String categoriaproducto;
	@Column(name = "precioregular")
	private double precioregular;
	@Column(name = "preciototal")
	private double preciototal;
	@Column(name = "iduseradd")
	private long iduseradd;
	@Column(name = "dateadd")
	private String dateadd;
	@Column(name = "idusermod")
	private long idusermod;
	@Column(name = "datemod")
	private String datemod;
	
	public long getIdreparaciondet() {
		return idreparaciondet;
	}
	public void setIdreparaciondet(long idreparaciondet) {
		this.idreparaciondet = idreparaciondet;
	}
	public long getIdreparacionenc() {
		return idreparacionenc;
	}
	public void setIdreparacionenc(long idreparacionenc) {
		this.idreparacionenc = idreparacionenc;
	}
	public String getNombreproducto() {
		return nombreproducto;
	}
	public void setNombreproducto(String nombreproducto) {
		this.nombreproducto = nombreproducto;
	}
	public long getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getCategoriaproducto() {
		return categoriaproducto;
	}
	public void setCategoriaproducto(String categoriaproducto) {
		this.categoriaproducto = categoriaproducto;
	}
	public double getPrecioregular() {
		return precioregular;
	}
	public void setPrecioregular(double precioregular) {
		this.precioregular = precioregular;
	}
	public double getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
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
