package Entities;

import java.time.LocalDateTime;

public class EnvioDomicilio extends Entrega{

	float costo;
	LocalDateTime fechaEntrega;
	String direccionEnvio;
	Localidad localidad = new Localidad();
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public LocalDateTime getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDateTime fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	
}
