package Entities;

import java.time.LocalDateTime;

public class PrecioProducto {

	int importe;
	LocalDateTime fechasHasta;
	Producto prod = new Producto();
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
	public LocalDateTime getFechasHasta() {
		return fechasHasta;
	}
	public void setFechasHasta(LocalDateTime fechasHasta) {
		this.fechasHasta = fechasHasta;
	}
	public Producto getProd() {
		return prod;
	}
	public void setProd(Producto prod) {
		this.prod = prod;
	}
	
	
}
