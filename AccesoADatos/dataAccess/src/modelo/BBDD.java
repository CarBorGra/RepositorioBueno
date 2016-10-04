package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoCatalogoRecetas.Ingrediente;

public class BBDD
{
  public static void main( String args[] ) throws SQLException{
	  //http://stackoverflow.com/questions/24963259/need-help-setting-up-sqlite-on-eclipse-with-java-for-the-first-time
	//TODO: CERRAR CONEXIONES
	  //TODO: Si la select sale vacia no mostrar nada
	  //TODO: DARLE FORMATO
  }
  //SELECT name FROM sqlite_master WHERE type='table' AND name='table_name';
  

	public static void mostrarTodo(String opcion) throws SQLException{
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
		Statement sentencia = null;
		ResultSet resultado = null;
		sentencia = c.createStatement();
		String sql = "SELECT * FROM " + opcion;
		resultado = sentencia.executeQuery(sql);
		while(resultado.next()){
			System.out.println(resultado);
		}
	}
	
	public static void crearTablaIngredientes() throws SQLException{
		Connection c = null;
	    ResultSet resultado = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    Statement sentencia = null;
	    sentencia = c.createStatement();
	    String sqlCheck = "SELECT name FROM sqlite_master WHERE type='table' AND name='Ingredientes'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	System.out.println("La tabla ya estaba creada.");
	    }else{
		    String sql = "CREATE TABLE IF NOT EXISTS 'Ingredientes' (Gas int, Alcohol real, Base string, Sabor string, Nombre string PRIMARY KEY)";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido creada.");
	    }
	}

	
	public static void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException{
		int conversionBoolean;
		if (ingrediente.isGas()){
			conversionBoolean = 1;
		}else{
			conversionBoolean = 0;
		}
		Connection c = null;
	    ResultSet resultado = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    Statement sentencia = null;
	    sentencia = c.createStatement();
	    String sqlCheck = "SELECT * FROM Ingredientes WHERE nombre='"+ingrediente.getNombre()+"'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	System.out.println("El valor ya existia (Comprobado via Primary Key, el nombre)");
	    }else{
		    String sql = "INSERT INTO Ingredientes VALUES ("+conversionBoolean+","+ingrediente.getAlcohol()+",'"+ingrediente.getBase()+"','"+ingrediente.getSabor()+"','"+ingrediente.getNombre()+"')";
		    sentencia.execute(sql);
	    	System.out.println("El dato ha sido introducido.");
	    }
	}
}