package Entities;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Pedido {
	
	int id;
	LocalDateTime fecha;
	LinkedList<Producto> productos = new LinkedList<Producto>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public LinkedList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}
	
	

}
