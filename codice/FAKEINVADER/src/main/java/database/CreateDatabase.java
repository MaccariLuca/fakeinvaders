package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

<<<<<<< Updated upstream
=======
/*
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
}
=======
	}
}
*/

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabase {
    private static CreateDatabase instance;
    public static String DB_REL_FILE = "../database/database.db3";
    public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

    private CreateDatabase() 
    {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

            // controllo che il file esista a questo punto
            //System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized CreateDatabase getInstance() {
        if (instance == null) {
            instance = new CreateDatabase();
           
        }
        return instance;
    }

    public static void main(String[] args) 
    {
        CreateDatabase dbInstance = CreateDatabase.getInstance();
        //System.out.println(dbInstance);
    }
}




>>>>>>> Stashed changes
