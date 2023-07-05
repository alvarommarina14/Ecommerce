package Entities;

import java.time.LocalDateTime;

public class EnvioDomicilio extends Entrega{

	Double costo;
	String fechaEntregaEstimada;
	String fechaEntregaReal;
	String direccionEnvio;
	Cliente cliente = new Cliente();
	Pedido pedido = new Pedido();
	
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	public String getFechaEntregaEstimada() {
		return fechaEntregaEstimada;
	}
	public void setFechaEntregaEstimada(String fechaEntregaEstimada) {
		this.fechaEntregaEstimada = fechaEntregaEstimada;
	}
	public String getFechaEntregaReal() {
		return fechaEntregaReal;
	}
	public void setFechaEntregaReal(String fechaEntregaReal) {
		this.fechaEntregaReal = fechaEntregaReal;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	
	
}
