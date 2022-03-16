package Entities;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Compra {

	int id;
	LocalDateTime fechaCompra;
	String estado;
	Cliente cliente = new Cliente();
	LinkedList<Producto> productos = new LinkedList<Producto>();
	FormadePago fdpago = new FormadePago();
	Entrega entrega;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LinkedList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(LinkedList<Producto> productos) {
		this.productos = productos;
	}
	public FormadePago getFdpago() {
		return fdpago;
	}
	public void setFdpago(FormadePago fdpago) {
		this.fdpago = fdpago;
	}
	public Entrega getEntrega() {
		return entrega;
	}
	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}
	
	
	
}
