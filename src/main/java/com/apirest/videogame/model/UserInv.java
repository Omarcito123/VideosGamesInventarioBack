package com.apirest.videogame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "UserInv")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser")
    private long iduser;
	@Column(name = "firstname")
    private String firstname;
    @Column(name = "secondname")
	private String secondname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "secondsurname")
	private String secondsurname;
    @Column(name = "email")
    private String email;
    @Column(name = "pass")
	private String pass;
    @Column(name = "phone")
    private String phone;
    @Column(name = "username")
	private String username;
    @Column(name = "dateborn")
	private String dateborn;
    @Column(name = "iduseradd")
    private int iduseradd;
    @Column(name = "dateadd")
	private String dateadd;
    @Column(name = "idusermod")
    private int idusermod;
    @Column(name = "datemod")
	private String datemod;
    @Column(name = "idrol")
    private int idrol;
    @Column(name = "idsucursal")
    private int idsucursal;
    @Column(name = "active")
    private int active;
    @Column(name = "rolname")
	private String rolname;
    
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSecondsurname() {
		return secondsurname;
	}
	public void setSecondsurname(String secondsurname) {
		this.secondsurname = secondsurname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDateborn() {
		return dateborn;
	}
	public void setDateborn(String dateborn) {
		this.dateborn = dateborn;
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
	public int getIdrol() {
		return idrol;
	}
	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	public int getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(int idsucursal) {
		this.idsucursal = idsucursal;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getRolname() {
		return rolname;
	}
	public void setRolname(String rolname) {
		this.rolname = rolname;
	}
}
