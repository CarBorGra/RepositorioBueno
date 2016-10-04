package gui;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.BBDD;
import proyectoCatalogoRecetas.Ingrediente;
import utilidades.Utilidades;

public class ClaseMain {

	public static void main(String[] args) throws SQLException {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		int opcion = 0;
		System.out.println("Elija una opcion: ");
		System.out.println("1.- Mostrar Ingredientes.");
		System.out.println("2.- Modificar Ingredientes.");
		System.out.println("3.- Añadir Ingredientes.");
		System.out.println("4.- Borrar Ingredientes.");
		System.out.println("5.- Crear tabla Ingredientes.");
		System.out.println("6.- Borrar tabla Ingredientes.");
		System.out.println("0.- Salir.");
		opcion = reader.nextInt();
		
		switch(opcion){
			case 1:
				BBDD.mostrarTodo("Ingredientes");
				break;
			case 2:
				
				break;
			case 3:
				Ingrediente entrada = ingredienteDesdeInput();
				BBDD.insertarValoresIngredientes(entrada);
				break;
			case 4:
				BBDD.crearTablaIngredientes();
			default:

				break;
		}
	}
	public static Ingrediente ingredienteDesdeInput(){
		//public Ingrediente(boolean gas, double alcohol, String base, String sabor, String nombre) {
		String nombreIng = Utilidades.introducirCadena("Introduzca el nombre del ingrediente: ");
		String saborIng = Utilidades.introducirCadena("Describa el sabor del ingrediente: ");
		String baseIng = Utilidades.introducirCadena("Con que se hace? ");
		double alcoholIng = Utilidades.leerFloat("Cual es su graduacion alcoholica?");
		char gasInput = Utilidades.introducirChar("Tiene gas? (Y/N):");
		boolean gasIng;
		if (gasInput == 'Y'){
			gasIng=true;
		}else{
			gasIng=false;
		}
		Ingrediente resultado = new Ingrediente(gasIng, alcoholIng, baseIng, saborIng, nombreIng);
		return resultado;
	}
}
