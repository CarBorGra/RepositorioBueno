package proyectoCatalogoRecetas;

import java.io.Serializable;

public class Ingrediente implements Serializable{
	public Ingrediente(boolean gas, double alcohol, String base, String sabor, String nombre) {
		super();
		this.gas = gas;
		this.alcohol = alcohol;
		this.base = base;
		this.sabor = sabor;
		this.nombre = nombre;
	}
	boolean gas;
	double alcohol;
	String base;
	String sabor;
	String nombre;
	
	public boolean isGas() {
		return gas;
	}
	public void setGas(boolean gas) {
		this.gas = gas;
	}
	public double getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.base = nombre;
	}
	public String getSabor() {
		return sabor;
	}
	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
}
