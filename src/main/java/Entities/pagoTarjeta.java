package Entities;

public class pagoTarjeta extends FormadePago{
	Integer nroTarjeta, CVV, mesVencimiento, anioVencimiento;
	String tipoTarjeta;

	public Integer getNroTarjeta() {
		return nroTarjeta;
	}

	public void setNroTarjeta(Integer nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public Integer getCVV() {
		return CVV;
	}

	public void setCVV(Integer cVV) {
		CVV = cVV;
	}

	public Integer getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(Integer mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

	public Integer getAnioVencimiento() {
		return anioVencimiento;
	}

	public void setAnioVencimiento(Integer anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

}
