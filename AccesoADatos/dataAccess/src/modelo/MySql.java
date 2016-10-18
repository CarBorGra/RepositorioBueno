package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import proyectoCatalogoRecetas.Ingrediente;
import proyectoCatalogoRecetas.Receta;

public class MySql implements InteraccionBaseDatos{

	//jdbc:mysql://localhost/recetas?" + "user=" + user + "&password=" + pass);
	@Override
	public boolean comprobarExisteTabla(String nombretabla) throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE '" + nombretabla+"'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(resultado.next()){
	    	return true;
	    }else{
	    	return false;
	    }
	}

	@Override
	public void mostrarIngredientes() throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;
		try {
		    statement = conn.createStatement();
		    resultado = statement.executeQuery("SELECT * FROM Ingredientes");
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
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (resultado != null) {
		        try {
		        	resultado.close();
		            statement.close();
		        } catch (SQLException sqlEx) {
		        	
		        } // ignore
		        statement = null;
		    }
		}
	}

	@Override
	public void mostrarRecetas() throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;
		try {
		    statement = conn.createStatement();
		    resultado = statement.executeQuery("SELECT * FROM Recetas");
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
		}
		catch (SQLException ex){
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
		    if (resultado != null) {
		        try {
		        	resultado.close();
		            statement.close();
		        } catch (SQLException sqlEx) {
		        	
		        } // ignore
		        statement = null;
		    }
		}
	}

	@Override
	public void BorrarFilaIngredientes(Ingrediente IngredienteConNombre) throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    if(comprobarExisteTabla("Ingredientes")){
	    	sentencia=conn.createStatement();
	    	String sql = "Delete FROM Ingredientes WHERE Nombre LIKE '"+ IngredienteConNombre.getNombre() + "'";
	    	sentencia.execute(sql);
	    	System.out.println("La fila ha sido borrada");
	    }
	}

	@Override
	public void crearTablaIngredientes() throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE 'Ingredientes'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(comprobarExisteTabla("Ingredientes")){
	    	System.out.println("La tabla ya estaba creada.");
	    }else{
		    String sql = "CREATE TABLE Ingredientes (Nombre varchar(255), Sabor varchar(255),Base varchar(255),Alcohol real,Gas Bool)";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido creada.");
	    }
	    conn.close();
		
	}

	@Override
	public void borrarTablaIngredientes() throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE 'Ingredientes'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(comprobarExisteTabla("Ingredientes")){
	    	String sql = "DROP TABLE Ingredientes";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido borrada.");

	    }else{
	    	System.out.println("No existe la tabla Ingredientes.");
	    }
	    conn.close();
	}

	@Override
	public void insertarValoresIngredientes(Ingrediente ingrediente) throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    if(comprobarExisteTabla("Ingredientes")){
	    	String sql = "INSERT INTO Ingredientes VALUES ('"+ingrediente.getNombre()+"','"+ingrediente.getSabor()+"','"+ingrediente.getBase()+"',"+ingrediente.getAlcohol()+","+ingrediente.isGas()+")";
	    	sentencia.execute(sql);
	    	System.out.println("La fila ha sido introducida");
	    }else{
	    	System.out.println("No existe la tabla Recetas");
	    }
		conn.close();
	}

	@Override
	public boolean mostrarUnIngrediente(String ingrediente) throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;

		if(comprobarExisteTabla("Ingredientes")){
			try{
				statement = conn.createStatement();
				resultado = statement.executeQuery("SELECT * FROM Ingredientes WHERE Nombre LIKE '"+ingrediente+"'");
				if(resultado.next()){
					System.out.println("Los valores actuales del elemento "+ingrediente+" son:");
					System.out.printf("%15.15s||%15.15s||%10.10s||%10.10s||%15.15s\n", "Nombre","Sabor","Base","Alcohol","Gas");
					System.out.printf("--------------------------------------------------------------------------------------------------------------------------------------------------\n");
						String nombre = resultado.getString("Nombre");
						String sabor = resultado.getString("Sabor");
						String base = resultado.getString("Base");
						double alcohol = resultado.getDouble("Alcohol");
						String gas = null;
						if (resultado.getBoolean("Gas")){
							gas = "Tiene gas";
						}else{
							gas = "No tiene gas";
						}
						System.out.printf("%15.15s||%15.15s||%10.10s||%10.1f||%15.15s\n", nombre, sabor, base, alcohol,gas);
					conn.close();
					return true;
				}else{
					System.out.println("La tabla Ingredientes no existe");
					conn.close();
					return false;
				}
			}catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		}
		return false;
	}

	@Override
	public void actualizarIngrediente(Ingrediente actualizarIngrediente, String nombreParaActualizar) throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;
		
		if(comprobarExisteTabla("Recetas")){
		    statement = conn.createStatement();
		    String sql = "UPDATE Ingredientes SET "
		    		+"Nombre='"+actualizarIngrediente.getNombre()
		    		+"', Sabor='"+actualizarIngrediente.getSabor()
		    		+"', Base ='"+actualizarIngrediente.getBase()
		    		+"', Alcohol="+actualizarIngrediente.getAlcohol()
		    		+", Gas="+actualizarIngrediente.isGas()
		    		+" WHERE Nombre = '"+ nombreParaActualizar+"'";
		    statement.execute(sql);
		    int filasAfectadas = statement.getUpdateCount();
		    System.out.println("Numero de filas afectadas: "+filasAfectadas);
		    conn.close();
		}else{
			System.out.println("No existe la tabla Recetas");
		}	
		
	}

	@Override
	public void BorrarFilaRecetas(Receta RecetaConNombre) throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    if(comprobarExisteTabla("Recetas")){
	    	sentencia=conn.createStatement();
	    	String sql = "Delete FROM Recetas WHERE Nombre LIKE '"+ RecetaConNombre.getNombre() + "'";
	    	sentencia.execute(sql);
	    	System.out.println("La fila ha sido borrada");
	    }
	}

	@Override
	public void crearTablaRecetas() throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE 'Recetas'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(comprobarExisteTabla("Recetas")){
	    	System.out.println("La tabla ya estaba creada.");
	    }else{
		    String sql = "CREATE TABLE Recetas (Nombre varchar(255),Recipiente varchar(255),notas varchar(255),preparacion varchar(255),ing1 varchar(255),ing2 varchar(255),ing3 varchar(255))";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido creada.");
	    }
	    conn.close();
	}

	@Override
	public void borrarTablaRecetas() throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    String sqlCheck = "SHOW TABLES LIKE 'Recetas'";
	    resultado = sentencia.executeQuery(sqlCheck);
	    if(comprobarExisteTabla("Recetas")){
	    	String sql = "DROP TABLE Recetas";
		    sentencia.execute(sql);
	    	System.out.println("La tabla ha sido borrada.");

	    }else{
	    	System.out.println("No existe la tabla Recetas.");
	    }
	    conn.close();
	}
//"Nombre","Recipiente","Notas","Preparacion","ing1","ing2","ing3");
	@Override
	public void insertarValoresRecetas(Receta receta) throws SQLException {
		Connection conn = conexion("root","");
		ResultSet resultado = null;
	    Statement sentencia = null;
	    sentencia = conn.createStatement();
	    if(comprobarExisteTabla("Recetas")){
	    	String sql = "INSERT INTO Recetas VALUES('"+receta.getNombre()+"','"+receta.getRecipiente()+"','"+receta.getNotas()+"','"+receta.getPreparacion()+"','"+receta.getIng1().getNombre()+"','"+receta.getIng2().getNombre()+"','"+receta.getIng3().getNombre()+"')";
	    	sentencia.execute(sql);
	    	System.out.println("La fila ha sido introducida");
	    }else{
	    	System.out.println("No existe la tabla Recetas");
	    }
		conn.close();
	}

	@Override
	public boolean mostrarUnRecetas(String Receta) throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;

		if(comprobarExisteTabla("Recetas")){
			try{
				statement = conn.createStatement();
				resultado = statement.executeQuery("SELECT * FROM Recetas WHERE Nombre LIKE '"+Receta+"'");
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
					conn.close();
					return true;
				}else{
					System.out.println("La tabla Recetas no existe");
					conn.close();
					return false;
				}
			}catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		}
		return false;
	}

	@Override
	public void actualizarRecetas(Receta actualizarReceta, String nombreParaActualizar) throws SQLException {
		Connection conn = conexion("root","");
		Statement statement = null;
		ResultSet resultado = null;
		
		if(comprobarExisteTabla("Recetas")){
		    statement = conn.createStatement();
		    String sql = "UPDATE Recetas SET "
		    		+"`Nombre`='"+actualizarReceta.getNombre()
		    		+"', `Recipiente`='"+actualizarReceta.getRecipiente()
		    		+"', `Notas` ='"+actualizarReceta.getNotas()
		    		+"', `Preparacion`='"+actualizarReceta.getPreparacion()
		    		+"', `ing1`='"+actualizarReceta.getIng1().getNombre()
		    		+"', `ing2`='"+actualizarReceta.getIng2().getNombre()
		    		+"', `ing3`='"+actualizarReceta.getIng3().getNombre()
		    		+"' WHERE `Nombre` = '"+ nombreParaActualizar+"'";
		    statement.execute(sql);
		    int filasAfectadas = statement.getUpdateCount();
		    System.out.println("Numero de filas afectadas: "+filasAfectadas);
		    conn.close();
		}else{
			System.out.println("No existe la tabla Recetas");
		}	
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
