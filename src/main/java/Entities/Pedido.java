package Entities;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Pedido {
	
	int id;
	String fecha;
	LinkedList<Producto> productos = new LinkedList<Producto>();
	LinkedList<LineaCompra> lineasCompra = new LinkedList<LineaCompra>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public LocalDateTime getFecha() {
//		return fecha;
//	}
//	public void setFecha(LocalDateTime fecha) {
//		this.fecha = fecha;
//	}
	
	public LinkedList<Producto> getProductos() {
		return productos;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}
	public LinkedList<LineaCompra> getLineasCompra() {
		return lineasCompra;
	}
	public void setLineasCompra(LinkedList<LineaCompra> lineasCompra) {
		this.lineasCompra = lineasCompra;
	}
	
	

}
