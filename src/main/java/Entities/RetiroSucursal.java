package Entities;

import java.time.LocalDateTime;

public class RetiroSucursal extends Entrega{

	Double costo;
	String fechaRetiro;
	String fechaRetiroEstimada;
	Sucursal sucursal = new Sucursal();
	Cliente cliente = new Cliente();
	Pedido pedido = new Pedido();
	
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	public String getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	public String getFechaRetiroEstimada() {
		return fechaRetiroEstimada;
	}
	public void setFechaRetiroEstimada(String fechaRetiroEstimada) {
		this.fechaRetiroEstimada = fechaRetiroEstimada;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
