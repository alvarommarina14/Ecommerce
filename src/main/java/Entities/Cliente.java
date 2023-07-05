package Entities;


import java.util.Date;

public class Cliente {

	String nombreApellido, tipDocumento, nroDocumento, email, tipoTelefono, nroTelefono, direccion, fechaNac;
	User_type user_type;
	Localidad localidad = new Localidad();
	Provincia provincia = new Provincia();
	public String getNombreApellido() {
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	public String getTipDocumento() {
		return tipDocumento;
	}
	public void setTipDocumento(String tipDocumento) {
		this.tipDocumento = tipDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTipoTelefono() {
		return tipoTelefono;
	}
	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}
	public String getNroTelefono() {
		return nroTelefono;
	}
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Cliente(String nombreApellido, String tipDocumento, String nroDocumento, String email, String tipoTelefono,
			String nroTelefono, String direccion, String fechaNac, Localidad localidad) {
		super();
		this.nombreApellido = nombreApellido;
		this.tipDocumento = tipDocumento;
		this.nroDocumento = nroDocumento;
		this.email = email;
		this.tipoTelefono = tipoTelefono;
		this.nroTelefono = nroTelefono;
		this.direccion = direccion;
		this.fechaNac = fechaNac;
		this.localidad = localidad;
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	public User_type getUser_type() {
		return user_type;
	}
	public void setUser_type(User_type user_type) {
		this.user_type = user_type;
	}
	
	
	
}
