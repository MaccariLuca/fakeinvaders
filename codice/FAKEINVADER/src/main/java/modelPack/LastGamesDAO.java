package modelPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LastGamesDAO 
{
    private static Connection connection;
    
    final String DB_REL_FILE = "src/main/java/database/database.db3";
    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

    public LastGamesDAO() 
    {
        try {
			LastGamesDAO.connection = DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public ResultSet getLastGamesForPlayer(String username) throws SQLException {
    	
    	
        String sql = "SELECT * FROM LAST_GAMES WHERE USERNAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
          
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
    }

    
    /*
    public static ResultSet getLastGames(String username) throws SQLException 
    {
    	String sql = "SELECT * FROM LAST_GAMES WHERE USERNAME = ?";
    	try (PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) 
        {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {

    }*/

    public void insertLastGame(String username, int score) throws SQLException 
    {
        //data
        LocalDateTime currentDate = LocalDateTime.now();
                
        // Creazione della query con PreparedStatement
        String query = "INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES (?, ?, ?)";
                
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setString(1, username);
        	preparedStatement.setInt(2, score);
        	preparedStatement.setObject(3, currentDate);
            
        	 System.out.println("username " + username );
            // Esecuzione della query
            int rowsAffected = preparedStatement.executeUpdate(); //restituisce il numero di righe che sono state inserite nel db 
                    
            if (rowsAffected > 0) {
                System.out.println("Record inserito con successo nella tabella LAST_GAMES.");
            } else {
                System.out.println("Errore durante l'inserimento del record nella tabella LAST_GAMES.");
            }
    }
}
}