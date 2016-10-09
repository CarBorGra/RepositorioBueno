package proyectoCatalogoRecetas;

import java.io.Serializable;

public class Receta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String nombre;
	String recipiente;
	String notas;
	String preparacion;
	Ingrediente ing1;
	Ingrediente ing2;
	Ingrediente ing3;
	
	public Receta(String nombre, String recipiente, String notas, String preparacion, Ingrediente ing1,
			Ingrediente ing2, Ingrediente ing3) {
		super();
		this.nombre = nombre;
		this.recipiente = recipiente;
		this.notas = notas;
		this.preparacion = preparacion;
		this.ing1 = ing1;
		this.ing2 = ing2;
		this.ing3 = ing3;
	}
	public Receta(String nombre){
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRecipiente() {
		return recipiente;
	}
	public void setRecipiente(String recipiente) {
		this.recipiente = recipiente;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public String getPreparacion() {
		return preparacion;
	}
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}
	public Ingrediente getIng1() {
		return ing1;
	}
	public void setIng1(Ingrediente ing1) {
		this.ing1 = ing1;
	}
	public Ingrediente getIng2() {
		return ing2;
	}
	public void setIng2(Ingrediente ing2) {
		this.ing2 = ing2;
	}
	public Ingrediente getIng3() {
		return ing3;
	}
	public void setIng3(Ingrediente ing3) {
		this.ing3 = ing3;
	}
}
