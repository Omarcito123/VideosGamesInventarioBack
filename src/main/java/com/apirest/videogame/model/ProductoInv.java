package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ProductoInv")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ProductoInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idprodinv")
	private long idprodinv;
	@Column(name = "serie")
	private String serie;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "categoria")
	private String categoria;	
	@Column(name = "estado")
	private String estado;
	@Column(name = "preciocosto")
	private double preciocosto;
	@Column(name = "precioregular")
	private double precioregular;
	@Column(name = "preciooferta")
	private double preciooferta;
	@Column(name = "existencia")
	private int existencia;
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
	
	public long getIdprodinv() {
		return idprodinv;
	}
	public void setIdprodinv(long idprodinv) {
		this.idprodinv = idprodinv;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getPreciocosto() {
		return preciocosto;
	}
	public void setPreciocosto(double preciocosto) {
		this.preciocosto = preciocosto;
	}
	public double getPrecioregular() {
		return precioregular;
	}
	public void setPrecioregular(double precioregular) {
		this.precioregular = precioregular;
	}
	public double getPreciooferta() {
		return preciooferta;
	}
	public void setPreciooferta(double preciooferta) {
		this.preciooferta = preciooferta;
	}
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
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
}
