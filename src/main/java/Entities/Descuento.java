package Entities;

import java.time.LocalDateTime;

public class Descuento {

	float porcentage;
	LocalDateTime fechaDesde;
	LocalDateTime fechaHasta;
	Producto prod = new Producto();
	public float getPorcentage() {
		return porcentage;
	}
	public void setPorcentage(float porcentage) {
		this.porcentage = porcentage;
	}
	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public LocalDateTime getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(LocalDateTime fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	
	
}
