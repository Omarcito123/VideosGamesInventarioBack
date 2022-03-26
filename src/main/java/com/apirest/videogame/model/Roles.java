package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Roles {
	@Id
	@Column(name = "idrol")
    private long idrol;
	@Column(name = "rolname")
    private String rolname;
    @Column(name = "iduseradd")
    private int iduseradd;
    @Column(name = "dateadd")
	private String dateadd;
    @Column(name = "idusermod")
    private int idusermod;
    @Column(name = "datemod")
	private String datemod;
    
	public long getIdrol() {
		return idrol;
	}
	public void setIdrol(long idrol) {
		this.idrol = idrol;
	}
	public String getRolname() {
		return rolname;
	}
	public void setRolname(String rolname) {
		this.rolname = rolname;
	}
	public int getIduseradd() {
		return iduseradd;
	}
	public void setIduseradd(int iduseradd) {
		this.iduseradd = iduseradd;
	}
	public String getDateadd() {
		return dateadd;
	}
	public void setDateadd(String dateadd) {
		this.dateadd = dateadd;
	}
	public int getIdusermod() {
		return idusermod;
	}
	public void setIdusermod(int idusermod) {
		this.idusermod = idusermod;
	}
	public String getDatemod() {
		return datemod;
	}
	public void setDatemod(String datemod) {
		this.datemod = datemod;
	}
}
