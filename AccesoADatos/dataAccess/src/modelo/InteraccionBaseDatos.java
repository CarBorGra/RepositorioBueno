package modelo;

import java.sql.SQLException;

import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;

public abstract interface InteraccionBaseDatos {
	//Comprobacion
	public boolean comprobarExisteTabla(String nombretabla) throws SQLException;
	//Ingredientes
	abstract void mostrarIngredientes() throws SQLException;
	abstract void mostrarRecetas() throws SQLException;
	abstract void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException;
	abstract void crearTablaIngredientes() throws SQLException;
	abstract void borrarTablaIngredientes() throws SQLException;
	abstract void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException;
	abstract boolean mostrarUnIngrediente(String ingrediente) throws SQLException;
	abstract void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException;
	//Recetas
	abstract void BorrarFilaRecetas(Receta RecetaConNombre) throws SQLException;
	abstract void crearTablaRecetas() throws SQLException;
	abstract void borrarTablaRecetas() throws SQLException;
	abstract void insertarValoresRecetas(Receta receta) throws SQLException;
	abstract boolean mostrarUnRecetas(String Receta) throws SQLException;
	abstract void actualizarRecetas(Receta actualizarReceta, String nombreParaActualizar) throws SQLException;
}
