package com.apirest.videogame.model;

public class ResponseLogin {
    private long iduser;
    private int idrol;
    private String firstname;
	private String username;
	private String dateborn;
	private String token;
	private String rolname;
	private int idsucursal;
	
	public long getIduser() {
		return iduser;
	}
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}
	public int getIdrol() {
		return idrol;
	}
	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDateborn() {
		return dateborn;
	}
	public void setDateborn(String dateborn) {
		this.dateborn = dateborn;
	}
	public int getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(int idsucursal) {
		this.idsucursal = idsucursal;
	}
	public String getRolname() {
		return rolname;
	}
	public void setRolname(String rolname) {
		this.rolname = rolname;
	}
}
