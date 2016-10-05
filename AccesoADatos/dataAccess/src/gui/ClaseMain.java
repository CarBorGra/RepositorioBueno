package gui;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.BBDD;
import proyectoCatalogoRecetas.Ingrediente;
import utilidades.Utilidades;

public class ClaseMain {

	public static void main(String[] args) throws SQLException {

		int opcion = menu();
		while(opcion!=0){
			switch(opcion){
				case 1: //Mostrar Ingredientes
					BBDD.mostrarTodo("Ingredientes");
					break;
					
				case 2://Modificar Ingredientes
					String nombreParaActualizar = Utilidades.introducirCadena("Cual es el nombre del ingrediente a actualizar?");
					boolean elementoExiste = BBDD.mostrarUnIngrediente(nombreParaActualizar);
					if (elementoExiste){
						char opcionModificar = Utilidades.introducirChar("Desea cambiarlos? (Y/N)");
						if(opcionModificar=='Y'){
							Ingrediente actualizarIngrediente = ingredienteDesdeInput();
							BBDD.actualizarIngrediente(actualizarIngrediente,nombreParaActualizar);
						}else if (opcionModificar=='N'){
							System.out.println("Volviendo al menu...");
							break;
						}else{
							System.out.println("Opcion Invalida");
							break;
						}
					}else{
						System.out.println("El elemento que se pretende modificar no existe.");
					}
					break;
					
				case 3: //Añadir Ingredientes
					Ingrediente introducirIngrediente = ingredienteDesdeInput();
					BBDD.insertarValoresIngredientes(introducirIngrediente);
					break;
					
				case 4: //Borrar Ingredientes
					String nombreParaBorrar = Utilidades.introducirCadena("Cual es el nombre del ingrediente a borrar?");
					Ingrediente entradaBorrado = new Ingrediente(nombreParaBorrar);
					BBDD.BorrarFilaIngredientes(entradaBorrado);
					break;
					
				case 5://Crear Tabla Ingredientes
					BBDD.crearTablaIngredientes();
					break;
					
				case 6: //Borrar tabla Ingredientes
					BBDD.borrarTablaIngredientes();
					break;
					
				case 0: //Salir
					break;
					
				default:
					break;
			}
			opcion = menu();
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
	public static int menu(){
		Scanner reader = new Scanner(System.in);  // Reading from System.in
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
		//reader.close(); ERROR
		return opcion;
	}
}
