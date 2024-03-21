package FAKEINVADERS_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO 
{
private static Connection connection;
    
    final String DB_REL_FILE = "src/main/java/FAKEINVADERS_Database/database.db3";
    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;


    public PlayerDAO() {
    	 PlayerDAO.setConnection(connection);
         try {
        	 PlayerDAO.setConnection(DriverManager.getConnection(DB_URL));
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }

    public boolean insertPlayer(String username, String password) throws SQLException {
        String sql = "INSERT INTO PLAYERS (USERNAME, PASSWORD) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean playerExists(String username) throws SQLException {
        String sql = "SELECT * FROM PLAYERS WHERE USERNAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    
    public boolean checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT * FROM PLAYERS WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    
    public static void setConnection(Connection connection) {
    	PlayerDAO.connection = connection;
	}
  
}

