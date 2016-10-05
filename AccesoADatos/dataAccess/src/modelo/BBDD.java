package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoCatalogoRecetas.Ingrediente;

public class BBDD
{

	  //http://stackoverflow.com/questions/24963259/need-help-setting-up-sqlite-on-eclipse-with-java-for-the-first-time
	  //TODO capturar excepciones
	  //TODO Preguntar sobre como manejar los borrados
	  //TODO se puede abrir y cerrar la conexion con un par de metodos?
	  //TODO Mejorar borrado de ingredientes, cosa de uppercase
	  //TODO Controlar excepcion puede que no exista la tabla, quedan algunas
  

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
		if(resultado.next()){
			System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
			System.out.printf("-------------------------------------------------------------------------\n");
			while(resultado.next()){
				String nombre = resultado.getString("Nombre");
				String sabor = resultado.getString("Sabor");
				String base = resultado.getString("Base");
				double alcohol = resultado.getDouble("Alcohol");
				String gas = null;
				if (resultado.getInt("Gas")==1){
					gas = "Tiene gas";
				}else{
					gas = "No tiene gas";
				}
				System.out.printf("%15.15s||%15.15s||%10.10s||%10.1f||%15.15s\n", nombre, sabor, base, alcohol,gas);
			}
		}else{
			System.out.println("La tabla no tiene datos");
		}
		c.close();
	}

	/**while (rs.next()) {
		  String lastName = rs.getString("Lname");
		  System.out.println(lastName + "\n");
		}**/
	public static void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException{
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    Statement sentencia = null;
	    sentencia = c.createStatement();
	    String sql = "DELETE FROM 'Ingredientes' WHERE nombre='"+IngredienteConNombre.getNombre()+"'";
	    sentencia.execute(sql);
	    int filasAfectadas = sentencia.getUpdateCount();
	    System.out.println("Numero de filas afectadas: "+filasAfectadas);
	    c.close();
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
	    c.close();
	}
	public static void borrarTablaIngredientes() throws SQLException{
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
	    	String sql = "DROP TABLE 'Ingredientes'";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido borrada.");
	    }else{

	    	System.out.println("La tabla no existe.");
	    }
	    c.close();
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
	    c.close();
	}

	public static boolean mostrarUnIngrediente(String ingrediente) throws SQLException {
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
		String sql = "SELECT * FROM 'Ingredientes' WHERE nombre='"+ingrediente+"'";
		resultado = sentencia.executeQuery(sql);
		if(resultado.next()){
		System.out.println("Los valores actuales del elemento "+ingrediente+" son:");
		System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
		System.out.printf("-------------------------------------------------------------------------\n");
			String nombre = resultado.getString("Nombre");
			String sabor = resultado.getString("Sabor");
			String base = resultado.getString("Base");
			double alcohol = resultado.getDouble("Alcohol");
			String gas = null;
			if (resultado.getInt("Gas")==1){
				gas = "Tiene gas";
			}else{
				gas = "No tiene gas";
			}
			System.out.printf("%15.15s||%15.15s||%10.10s||%10.1f||%15.15s\n", nombre, sabor, base, alcohol,gas);
			c.close();
			return true;
		}else{
			c.close();
			return false;
		}
	}

	public static void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException {
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    Statement sentencia = null;
	    sentencia = c.createStatement();
	    int tieneGas;
	    if (actualizarIngrediente.isGas()){
	    	tieneGas=1;
	    }else{
	    	tieneGas=2;
	    }
	    String sql = "UPDATE Ingredientes SET "
	    		+"Nombre='"+actualizarIngrediente.getNombre()
	    		+"', Sabor='"+actualizarIngrediente.getSabor()
	    		+"', Base ='"+actualizarIngrediente.getBase()
	    		+"', Alcohol="+actualizarIngrediente.getAlcohol()
	    		+", Gas="+tieneGas
	    		+" WHERE Nombre = '"+ nombreParaActualizar+"'";
	    System.out.println(sql);
	    sentencia.execute(sql);
	    int filasAfectadas = sentencia.getUpdateCount();
	    System.out.println("Numero de filas afectadas: "+filasAfectadas);
	    c.close();
	}
	
}