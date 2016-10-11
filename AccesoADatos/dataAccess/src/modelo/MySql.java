package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;

public class MySql implements InteraccionBaseDatos{

	//root ""
	@Override
	public boolean comprobarExisteTabla(String nombretabla) throws SQLException {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public void mostrarIngredientes() throws SQLException {
		
		
	}

	@Override
	public void mostrarRecetas() throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultset = null;
		
		try {
		    statement = conn.createStatement();
		    resultset = statement.executeQuery("SELECT * FROM Recetas");
		    System.out.println(resultset);
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (resultset != null) {
		        try {
		        	resultset.close();
		            statement.close();
		        } catch (SQLException sqlEx) {
		        	
		        } // ignore
		        statement = null;
		    }
		}
	}

	@Override
	public void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearTablaIngredientes() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrarTablaIngredientes() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean mostrarUnIngrediente(String ingrediente) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BorrarFilaRecetas(Receta RecetaConNombre) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearTablaRecetas() throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
		
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE 'Recetas'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	System.out.println("La tabla ya estaba creada.");
	    }else{
		    String sql = "CREATE TABLE IF NOT EXISTS Recetas (Nombre STRING PRIMARY KEY, Recipiente string, notas string, preparacion string, ing1 string, ing2 string, ing3 string)";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido creada.");
	    }
	    conn.close();
	}

	@Override
	public void borrarTablaRecetas() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertarValoresRecetas(Receta receta) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean mostrarUnRecetas(String Receta) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void actualizarRecetas(Receta actualizarReceta, String nombreParaActualizar) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Connection conexion(String user, String pass){
		Connection conexion = null;
		try{
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/recetas?" + "user=" + user + "&password=" + pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conexion;
	}
}
