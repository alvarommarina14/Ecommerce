package Entities;

public class Localidad {
	
	int id;
	String nombre;
	Provincia provincia = new Provincia();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Provincia getProvincia() {
		return this.provincia;
	}
	
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
