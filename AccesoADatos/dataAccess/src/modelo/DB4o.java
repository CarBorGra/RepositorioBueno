package modelo;

import java.sql.SQLException;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;

public class DB4o implements InteraccionBaseDatos{


	public void mostrarIngredientes() throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Ingrediente> Ingredientes = db.query(new Predicate<Ingrediente>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Ingrediente i){
					return true;
				}
			});
			System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
			System.out.printf("-------------------------------------------------------------------------\n");
			for(Ingrediente cadaIngrediente : Ingredientes){
				String nombre = cadaIngrediente.getNombre();
				String sabor = cadaIngrediente.getSabor();
				String base = cadaIngrediente.getBase();
				double alcohol = cadaIngrediente.getAlcohol();
				boolean gas = cadaIngrediente.isGas();
				System.out.printf("%15.15s||%15.15s||%10.10s||%10.1f||%15.15s\n", nombre, sabor, base, alcohol,gas);
			}
		}finally{
			db.close();
		}
	}

	public void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Ingrediente> Ingredientes = db.query(new Predicate<Ingrediente>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Ingrediente i){
					return i.getNombre().equals(IngredienteConNombre.getNombre());
				}
			});
			Ingrediente ingredienteBorrar = Ingredientes.get(0);
			db.delete(ingredienteBorrar);
		}finally{
			db.close();
		}
	}

	public void crearTablaIngredientes() throws SQLException {
		System.out.println("DB4o no tiene tablas, es una base de datos basada en objetos");
		
	}

	public void borrarTablaIngredientes() throws SQLException {
		System.out.println("DB4o no tiene tablas, es una base de datos basada en objetos");		
	}

	public void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			db.store(ingrediente);
		}finally{
			db.close();
		}
	}

	public boolean mostrarUnIngrediente(String ingrediente) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		boolean respuesta = false;
		try{
			List<Ingrediente> Ingredientes = db.query(new Predicate<Ingrediente>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Ingrediente i){
					return i.getNombre().equals(ingrediente);
				}
			});
			System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
			System.out.printf("-------------------------------------------------------------------------\n");
			for(Ingrediente cadaIngrediente : Ingredientes){
				String nombre = cadaIngrediente.getNombre();
				String sabor = cadaIngrediente.getSabor();
				String base = cadaIngrediente.getBase();
				double alcohol = cadaIngrediente.getAlcohol();
				boolean gas = cadaIngrediente.isGas();
				System.out.printf("%15.15s||%15.15s||%10.10s||%10.1f||%15.15s\n", nombre, sabor, base, alcohol,gas);
				respuesta = true;
			}
		}finally{
			db.close();
		}
		return respuesta;
	}

	public void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Ingrediente> Ingredientes = db.query(new Predicate<Ingrediente>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Ingrediente i){
					return i.getNombre().equals(nombreParaActualizar);
				}
			});
			Ingrediente ingredienteNuevo = Ingredientes.get(0);
			ingredienteNuevo.setNombre(actualizarIngrediente.getNombre());
			ingredienteNuevo.setBase(actualizarIngrediente.getBase());
			ingredienteNuevo.setSabor(actualizarIngrediente.getSabor());
			ingredienteNuevo.setAlcohol(actualizarIngrediente.getAlcohol());
			ingredienteNuevo.setGas(actualizarIngrediente.isGas());
			db.store(ingredienteNuevo);
		} finally{
			db.close();
		}
	}


	public void mostrarRecetas() throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Receta> Recetas = db.query(new Predicate<Receta>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Receta r){
					return true;
				}
			});
			System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", "Nombre","Recipiente","Notas","Preparacion","ing1","ing2","ing3");
			System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
			for(Receta cadaIngrediente : Recetas){
				String nombre = cadaIngrediente.getNombre();
				String Recipiente = cadaIngrediente.getRecipiente();
				String Notas = cadaIngrediente.getNotas();
				String Preparacion = cadaIngrediente.getPreparacion();
				String Ing1 = cadaIngrediente.getIng1().getNombre();
				String Ing2 = cadaIngrediente.getIng2().getNombre();
				String Ing3 = cadaIngrediente.getIng3().getNombre();
				System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", nombre, Recipiente, Notas, Preparacion, Ing1, Ing2, Ing3 );
		 }
		}finally{
			db.close();
		}
	}

	public void BorrarFilaRecetas(Receta RecetaConNombre) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Receta> Recetas = db.query(new Predicate<Receta>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Receta r){
					return r.getNombre().equals(RecetaConNombre.getNombre());
				}
			});
			Receta recetaBorrar = Recetas.get(0);
			db.delete(recetaBorrar);
		}finally{
			db.close();
		}
	}

	public void crearTablaRecetas() throws SQLException {
		System.out.println("DB4o no tiene tablas, es una base de datos basada en objetos");
	}

	public void borrarTablaRecetas() throws SQLException {
		System.out.println("DB4o no tiene tablas, es una base de datos basada en objetos");
	}

	public void insertarValoresRecetas(Receta receta) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			db.store(receta);
		}finally{
			db.close();
		}
	}

	public boolean mostrarUnRecetas(String Receta) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		boolean respuesta = false;
		try{
			List<Receta> Recetas = db.query(new Predicate<Receta>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Receta r){
					return r.getNombre().equals(Receta);
				}
			});
			System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", "Nombre","Recipiente","Notas","Preparacion","ing1","ing2","ing3");
			System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
			for(Receta cadaIngrediente : Recetas){
				String nombre = cadaIngrediente.getNombre();
				String Recipiente = cadaIngrediente.getRecipiente();
				String Notas = cadaIngrediente.getNotas();
				String Preparacion = cadaIngrediente.getPreparacion();
				String Ing1 = cadaIngrediente.getIng1().getNombre();
				String Ing2 = cadaIngrediente.getIng2().getNombre();
				String Ing3 = cadaIngrediente.getIng3().getNombre();
				System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", nombre, Recipiente, Notas, Preparacion, Ing1, Ing2, Ing3 );
				respuesta = true;
			}
		}finally{
			db.close();
		}
		return respuesta;
	}

	public void actualizarRecetas(Receta actualizarReceta, String nombreParaActualizar) throws SQLException {
		ObjectContainer db = Db4oEmbedded.openFile("DB4oDatabase.db4o");
		try{
			List<Receta> Recetas = db.query(new Predicate<Receta>(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean match(Receta r){
					return r.getNombre().equals(nombreParaActualizar);
				}
			});
			Receta recetaNuevo = Recetas.get(0);
			recetaNuevo.setNombre(actualizarReceta.getNombre());
			recetaNuevo.setRecipiente(actualizarReceta.getRecipiente());
			recetaNuevo.setNotas(actualizarReceta.getNotas());
			recetaNuevo.setPreparacion(actualizarReceta.getPreparacion());
			recetaNuevo.setIng1(actualizarReceta.getIng1());
			recetaNuevo.setIng2(actualizarReceta.getIng2());
			recetaNuevo.setIng3(actualizarReceta.getIng3());
			db.store(recetaNuevo);
		} finally{
			db.close();
		}
	}

	@Override
	public boolean comprobarExisteTabla(String nombretabla) throws SQLException {
		
		return false;
	}
}