package Entities;

import java.time.LocalDateTime;

public class RetiroSucursal extends Entrega{

	LocalDateTime fechaRetiro;
	Sucursal sucursal = new Sucursal();
	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
	
}
