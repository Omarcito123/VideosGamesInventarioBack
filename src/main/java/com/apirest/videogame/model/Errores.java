package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Errores")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Errores {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iderror")
	private long iderror;
	@Column(name = "mensaje")
	private String mensaje;	
	@Column(name = "servicio")
	private String servicio;	
	@Column(name = "parametros")
	private String parametros;	
	@Column(name = "dateadd")
	private String dateadd;
	
	public long getIderror() {
		return iderror;
	}
	public void setIderror(long iderror) {
		this.iderror = iderror;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getParametros() {
		return parametros;
	}
	public void setParametros(String parametros) {
		this.parametros = parametros;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}	
}
