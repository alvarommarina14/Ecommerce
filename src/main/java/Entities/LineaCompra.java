package Entities;

public class LineaCompra {
	Integer nroPedido;
	Integer idProducto;
	Integer cantidad;
	Double precio;
	String descripcion;

	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getNroPedido() {
		return nroPedido;
	}
	public void setNroPedido(Integer nroPedido) {
		this.nroPedido = nroPedido;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
