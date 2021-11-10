package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
private static Connection con=null;
	
	/*
	 * Debe ir cargado desde un xml externo
	 */
	
	private static String uri="jdbc:mysql://localhost/mediaplayer";
	private static String user="root";
	private static String password="";
	
	public static Connection getConection() {
		if(con==null) {
			try {
				con=DriverManager.getConnection(uri,user,password);
			} catch (SQLException e) {
				e.printStackTrace();
				con=null;
			}
		}
		
		return con;
	}
	
	public static void closeConection() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
