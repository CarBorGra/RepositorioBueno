package modelo;

import java.sql.SQLException;

import proyectoCatalogoRecetas.Ingrediente;

abstract class InteraccionBaseDatos {
	abstract void mostrarTodo(String opcion) throws SQLException;
	abstract void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException;
	abstract void crearTablaIngredientes() throws SQLException;
	abstract void borrarTablaIngredientes() throws SQLException;
	abstract void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException;
	abstract boolean mostrarUnIngrediente(String ingrediente) throws SQLException;
	abstract void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException;
}
