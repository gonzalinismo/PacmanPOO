import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Main {
	private static final String NOMBRE_BASEDATOS = "test.db";
	private static final String SQL_TABLA_CONFIG ="CREATE TABLE IF NOT EXISTS 	configuraciones \n"+
							"(conf varchar(20) , valor BOOLEAN, primary key(conf))";
	private static final String SQL_TABLA_CONFIG1= "DROP TABLE configuraciones";
	
	public static void main(String[] args) 
	{
		   Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   PreparedStatement pstmt= null;
			new Emulador();
				try {
				            
				            String url = "jdbc:sqlite:"+NOMBRE_BASEDATOS;
				             
				            conn = DriverManager.getConnection(url); // Si no existe crea el archivo de la base de datos
				           	System.out.println("Conectado a  SQLite.");
				
				           	stmt = conn.createStatement();
				           	String sql; 
				            sql =SQL_TABLA_CONFIG;
				            
				            stmt.executeUpdate(sql);
				            if (!stmt.executeQuery("SELECT * FROM configuraciones").next()      )                               //si rs esta vacio inserta en la tabla
				            {
				            	sql="INSERT INTO configuraciones (conf,valor) values('pantallac',0),('sonido',1)";
				            	stmt.executeUpdate(sql);
				            }
				            sql="SELECT * FROM configuraciones";
				            rs = stmt.executeQuery(sql);
				            while(rs.next()){
			                    System.out.println(rs.getString("conf") +" " + rs.getBoolean("valor") );

			                     
				            }
				            sql="CREATE TABLE IF NOT EXISTS 	teclado \n" + 
				            		"						(teclado varchar(20), valor integer,primary key(teclado))";
				        
				            stmt.executeUpdate(sql);
				            
				            
				            if (!stmt.executeQuery("SELECT * FROM teclado").next()      )                               //si rs esta vacio inserta en la tabla
				            {
				            	sql="INSERT INTO teclado (teclado,valor) values('arriba',38),('abajo',40),('izquierda',37),('derecha',39),('pausa',80)";
				            	stmt.executeUpdate(sql);
				            }
				            
				            
				            sql="SELECT * FROM teclado";
				            rs = stmt.executeQuery(sql);
				            
				            while(rs.next()){
			                    System.out.println(rs.getString("teclado") +" " + rs.getInt("valor") );

			                     
			                }
				            
				            
				            sql="CREATE TABLE IF NOT EXISTS 	botonesname \n" + 
				            		"						(botones varchar(20), valor varchar(20),primary key(botones))";
				            stmt.executeUpdate(sql);
				           
				            
				            
				            if (!stmt.executeQuery("SELECT * FROM botonesname").next()      )                               //si rs esta vacio inserta en la tabla
				            {
				            	sql="INSERT INTO botonesname(botones,valor) values('arriba','arriba'),('abajo','abajo'),('izquierda','izquierda'),('derecha','derecha'),('pausa','P')";
				            	stmt.executeUpdate(sql);
				            }
				            
				            sql="SELECT * FROM botonesname";
				            rs = stmt.executeQuery(sql);
				            while(rs.next())
				            {
				                 
				            	System.out.println(rs.getString("botones") +" " + rs.getString("valor") );
				                  
				             }
				            
				            stmt.close();
				         	
				            
				        } catch (SQLException e) {
				            System.out.println(e.getMessage());
				            try {
				                if (conn != null) {
				                    conn.close();
				                }
				            } catch (SQLException ex) {
				                System.out.println(ex.getMessage());
				            }
				        
				        }

			
		}


	}


