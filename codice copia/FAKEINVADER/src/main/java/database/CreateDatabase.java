package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase 
{

	public static String DB_REL_FILE = "../database/database.db3";
	public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	{
	
	try 
	{			
		 Connection conn = DriverManager.getConnection(DB_URL);
		 if (conn != null) 
		 {
		   DatabaseMetaData meta = conn.getMetaData();
		   System.out.println("The driver name is " + meta.getDriverName());
		   System.out.println("A new database has been created.");
		 }
		 
		 // controllo che il file esista a questo punto
		 System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
		} catch (SQLException e) {
		  System.out.println(e.getMessage());
		}
}
}
