package gui;

import java.sql.SQLException;
import java.util.Scanner;

import modelo.BBDD;
import modelo.DB4o;
import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;
import utilidades.Utilidades;
import modelo.InteraccionBaseDatos;
import modelo.MySql;

public class ClaseMain {

	public static void main(String[] args) throws SQLException {
		InteraccionBaseDatos objetobase = null;
		
		int opcionBase = menuBase();
		switch(opcionBase){
			case 1:
				objetobase = new BBDD();
				break;
			case 2:
				objetobase = new DB4o();
				break;
			case 3:
				objetobase = new MySql();
				break;
			default:
				break;
		}
		
		int opcionTabla = menuTabla();
		switch(opcionTabla){
			case 1://Administrar Ingredientes
			int opcionIng = menu("ingredientes");
			while(opcionIng!=0){
				switch(opcionIng){
					case 1: //Mostrar Ingredientes
						objetobase.mostrarIngredientes();
						break;
						
					case 2://Modificar Ingredientes
						String nombreParaActualizar = Utilidades.introducirCadena("Cual es el nombre del ingrediente a actualizar?");
						boolean elementoExiste = objetobase.mostrarUnIngrediente(nombreParaActualizar);
						if (elementoExiste){
							char opcionModificar = Utilidades.introducirChar("Desea cambiarlos? (Y/N)");
							if(opcionModificar=='Y'){
								Ingrediente actualizarIngrediente = ingredienteDesdeInput();
								objetobase.actualizarIngrediente(actualizarIngrediente,nombreParaActualizar);
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
						objetobase.insertarValoresIngredientes(introducirIngrediente);
						break;
						
					case 4: //Borrar Ingredientes
						String nombreParaBorrar = Utilidades.introducirCadena("Cual es el nombre del ingrediente a borrar?");
						Ingrediente entradaBorrado = new Ingrediente(nombreParaBorrar);
						objetobase.BorrarFilaIngredientes(entradaBorrado);
						break;
						
					case 5://Crear Tabla Ingredientes
						objetobase.crearTablaIngredientes();
						break;
						
					case 6: //Borrar tabla Ingredientes
						objetobase.borrarTablaIngredientes();
						break;
						
					case 0: //Salir
						break;
						
					default:
						break;
				}
				opcionIng = menu("ingredientes");
				
			}
			break;
			case 2://Administrar recetas
				int opcionRec = menu("Recetas");
				while(opcionRec!=0){
					switch(opcionRec){
					case 1: //Mostrar Recetas
						objetobase.mostrarRecetas();
						break;
					case 2: //Modificar Recetas
						String nombreParaActualizar = Utilidades.introducirCadena("Cual es el nombre de la receta a actualizar?");
						boolean elementoExiste = objetobase.mostrarUnRecetas(nombreParaActualizar);
						if (elementoExiste){
							char opcionModificar = Utilidades.introducirChar("Desea cambiarlos? (Y/N)");
							if(opcionModificar=='Y'){
								Receta actualizarReceta = recetaDesdeInput();
								objetobase.actualizarRecetas(actualizarReceta, nombreParaActualizar);
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
					case 3: //Añadir Recetas
						Receta insertarReceta = recetaDesdeInput();
						objetobase.insertarValoresRecetas(insertarReceta);
						break;
					case 4: //Borrar Recetas
						String nombreParaBorrar = Utilidades.introducirCadena("Cual es el nombre de la receta a borrar?");
						Receta entradaBorrado = new Receta(nombreParaBorrar);
						objetobase.BorrarFilaRecetas(entradaBorrado);
						break;
					case 5: //Crear Tabla Recetas
						objetobase.crearTablaRecetas();
						break;
					case 6: // Borrar Tabla Recetas
						objetobase.borrarTablaRecetas();
						break;
					}
					opcionRec = menu("Recetas");
				}
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
	public static Receta recetaDesdeInput(){
		//	public Receta(String nombre, String recipiente, String notas, String preparacion, Ingrediente ing1,Ingrediente ing2, Ingrediente ing3) {
		String nombre = Utilidades.introducirCadena("Introduzca el nombre de la receta: ");
		String recipiente = Utilidades.introducirCadena("Introduzca el nombre del recipiente: ");
		String notas = Utilidades.introducirCadena("Introduzca algunas notas: ");
		String preparacion = Utilidades.introducirCadena("Introduzca la descripcion de la preparacion: ");
		String nombreIng1 = Utilidades.introducirCadena("Introduzca el nombre de el primer ingrediente: ");
		String nombreIng2 = Utilidades.introducirCadena("Introduzca el nombre de el segundo ingrediente: ");
		String nombreIng3 = Utilidades.introducirCadena("Introduzca el nombre de el tercera ingrediente: ");
		Ingrediente Ing1 = new Ingrediente(nombreIng1);
		Ingrediente Ing2 = new Ingrediente(nombreIng2);
		Ingrediente Ing3 = new Ingrediente(nombreIng3);
		
		Receta receta = new Receta(nombre,recipiente,notas,preparacion,Ing1,Ing2,Ing3);
		return receta;
	}
	
	public static int menu(String contenido){
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int opcion = 0;
		System.out.println("Elija una opcion: ");
		System.out.println("1.- Mostrar " + contenido+".");
		System.out.println("2.- Modificar  "+contenido+".");
		System.out.println("3.- Añadir  "+contenido+".");
		System.out.println("4.- Borrar  "+contenido+".");
		System.out.println("5.- Crear tabla  "+contenido+".");
		System.out.println("6.- Borrar tabla  "+contenido+".");
		System.out.println("0.- Salir.");
		opcion = reader.nextInt();
		//reader.close();
		return opcion;
	}
	
	public static int menuBase(){
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int opcion = 0;
		System.out.println("Elija una base de datos con la que trabajar: ");
		System.out.println("1.- Sqlite.");
		System.out.println("2.- DB4o.");
		System.out.println("3.- MySql.");
		System.out.println("4.- Otramas.");
		opcion = reader.nextInt();
		return opcion;
	}
	
	public static int menuTabla(){
		Scanner reader = new Scanner(System.in);
		int opcion = 0;
		System.out.println("Elija una tabla o clase de objeto con la que trabajar: ");
		System.out.println("1.- Ingredientes.");
		System.out.println("2.- Recetas.");
		opcion = reader.nextInt();
		return opcion;
	}
}
