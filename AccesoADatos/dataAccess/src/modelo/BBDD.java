package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;

public class BBDD implements InteraccionBaseDatos 
{  

	public void mostrarIngredientes() throws SQLException {
		if(comprobarExisteTabla("Ingredientes")){
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
			String sql = "SELECT * FROM Ingredientes";
			resultado = sentencia.executeQuery(sql);
			if(resultado.next()){
				System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
				System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
				do {
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
				} while (resultado.next());
			}else{
				System.out.println("La tabla no tiene datos");
			}
			c.close();
		}else{
			System.out.println("La tabla Ingredientes no existe.");
		}

	}
	public void mostrarRecetas() throws SQLException {
		if(comprobarExisteTabla("Recetas")){
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
			String sql = "SELECT * FROM Recetas";
			resultado = sentencia.executeQuery(sql);
			if(resultado.next()){
				System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", "Nombre","Recipiente","Notas","Preparacion","ing1","ing2","ing3");
				System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
				do {
					String nombre = resultado.getString("Nombre");
					String Recipiente = resultado.getString("Recipiente");
					String Notas = resultado.getString("Notas");
					String Preparacion = resultado.getString("Preparacion");
					String Ing1 = resultado.getString("Ing1");
					String Ing2 = resultado.getString("Ing2");
					String Ing3 = resultado.getString("Ing3");
					System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", nombre, Recipiente, Notas, Preparacion, Ing1, Ing2, Ing3 );
					
				} while (resultado.next());
			}else{
				System.out.println("La tabla no tiene datos");
			}
			c.close();
		}else{
			System.out.println("No existe la tabla Recetas");
		}
	}

	public void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException{
		if (comprobarExisteTabla("Ingredientes")){
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
		}else{
			System.out.println("No existe la tabla Ingredientes");
		}
	}
	

	public void crearTablaIngredientes() throws SQLException{
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

	public void borrarTablaIngredientes() throws SQLException{
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

	public void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException{
		if (comprobarExisteTabla("Ingredientes")){
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
		}else{
			System.out.println("No existe la tabla Ingredientes");
		}

	}

	public boolean mostrarUnIngrediente(String ingrediente) throws SQLException {
		if(comprobarExisteTabla("Ingredientes")){
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
			System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
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
		}else{
			System.out.println("No existe la tabla Ingredientes");
			return false;
		}

	}

	public void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException {
		if(comprobarExisteTabla("Ingredientes")){
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
		    	tieneGas=0;
		    }
		    String sql = "UPDATE Ingredientes SET "
		    		+"Nombre='"+actualizarIngrediente.getNombre()
		    		+"', Sabor='"+actualizarIngrediente.getSabor()
		    		+"', Base ='"+actualizarIngrediente.getBase()
		    		+"', Alcohol="+actualizarIngrediente.getAlcohol()
		    		+", Gas="+tieneGas
		    		+" WHERE Nombre = '"+ nombreParaActualizar+"'";
		    sentencia.execute(sql);
		    int filasAfectadas = sentencia.getUpdateCount();
		    System.out.println("Numero de filas afectadas: "+filasAfectadas);
		    c.close();
		}else{
			System.out.println("No existe la tabla ingredientes");
		}

	}

	public void BorrarFilaRecetas(Receta RecetaConNombre) throws SQLException {
		if(comprobarExisteTabla("Recetas")){
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
		    String sql = "DELETE FROM 'Recetas' WHERE nombre='"+RecetaConNombre.getNombre()+"'";
		    sentencia.execute(sql);
		    int filasAfectadas = sentencia.getUpdateCount();
		    System.out.println("Numero de filas afectadas: "+filasAfectadas);
		    c.close();
		}else{
			System.out.println("No existe la tabla Recetas");
		}

	}

	public void crearTablaRecetas() throws SQLException {
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
	    String sqlCheck = "SELECT name FROM sqlite_master WHERE type='table' AND name='Recetas'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	System.out.println("La tabla ya estaba creada.");
	    }else{
		    String sql = "CREATE TABLE IF NOT EXISTS 'Recetas' (Nombre string PRIMARY KEY, Recipiente string, notas string, preparacion string, ing1 string, ing2 string, ing3 string)";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido creada.");
	    }
	    c.close();
	}

	public void borrarTablaRecetas() throws SQLException {
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
	    String sqlCheck = "SELECT name FROM sqlite_master WHERE type='table' AND name='Recetas'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	String sql = "DROP TABLE 'Recetas'";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido borrada.");
	    }else{
	    	System.out.println("La tabla no existe.");
	    }
	    c.close();
	}

	public void insertarValoresRecetas(Receta Receta) throws SQLException {
		if(comprobarExisteTabla("Recetas")){
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
		    String sqlCheck = "SELECT * FROM Recetas WHERE Nombre='"+Receta.getNombre()+"'";
		    resultado = sentencia.executeQuery(sqlCheck);
		    if(resultado.next()){
		    	System.out.println("El valor ya existia (Comprobado via Primary Key, el nombre)");
		    }else{
			    String sql = "INSERT INTO Recetas VALUES ('"
			    +Receta.getNombre()+"','"
			    +Receta.getRecipiente()+"','"
			    +Receta.getNotas()+"','"
			    +Receta.getPreparacion()+"','"
			    +Receta.getIng1().getNombre()+"','"
			    +Receta.getIng2().getNombre()+"','"
			    +Receta.getIng3().getNombre()+"')";
			    sentencia.execute(sql);
		    	System.out.println("El dato ha sido introducido.");
		    }
		    c.close();
		}else{
			System.out.println("No existe la tabla recetas");
		}

	}

	
	public boolean mostrarUnRecetas(String Receta) throws SQLException {
		if(comprobarExisteTabla("Recetas")){
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
			String sql = "SELECT * FROM 'Recetas' WHERE nombre='"+Receta+"'";
			resultado = sentencia.executeQuery(sql);
			if(resultado.next()){
			System.out.println("Los valores actuales del elemento "+Receta+" son:");
			System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", "Nombre","Recipiente","Notas","Preparacion","ing1","ing2","ing3");
			System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
				String nombre = resultado.getString("Nombre");
				String Recipiente = resultado.getString("Recipiente");
				String Notas = resultado.getString("Notas");
				String Preparacion = resultado.getString("Preparacion");
				String Ing1 = resultado.getString("Ing1");
				String Ing2 = resultado.getString("Ing2");
				String Ing3 = resultado.getString("Ing3");
				System.out.printf("%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||%15.15s||\n", nombre, Recipiente, Notas, Preparacion, Ing1, Ing2, Ing3 );
				c.close();
				return true;
			}else{
				c.close();
				return false;
			}
		}else{
			System.out.println("No existe la tabla Recetas");
			return false;
		}

	}

	public void actualizarRecetas(Receta actualizarReceta, String nombreParaActualizar) throws SQLException {
		if(comprobarExisteTabla("Recetas")){
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
		    String sql = "UPDATE Recetas SET "
		    		+"Nombre='"+actualizarReceta.getNombre()
		    		+"', Recipiente='"+actualizarReceta.getRecipiente()
		    		+"', Notas ='"+actualizarReceta.getNotas()
		    		+"', Preparacion='"+actualizarReceta.getPreparacion()
		    		+"', ing1='"+actualizarReceta.getIng1().getNombre()
		    		+"', ing2='"+actualizarReceta.getIng2().getNombre()
		    		+"', ing3='"+actualizarReceta.getIng3().getNombre()
		    		+"' WHERE Nombre = '"+ nombreParaActualizar+"'";
		    sentencia.execute(sql);
		    int filasAfectadas = sentencia.getUpdateCount();
		    System.out.println("Numero de filas afectadas: "+filasAfectadas);
		    c.close();
		}else{
			System.out.println("No existe la tabla Recetas");
		}

	}
	
	public boolean comprobarExisteTabla(String nombretabla) throws SQLException{
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
	    String sqlCheck = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+nombretabla+"'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
		    c.close();
	    	return true;
	    }else{
		    c.close();
	    	return false;
	    }
	}
	
}