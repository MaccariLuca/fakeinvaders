package FAKEINVADERS_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class LastGamesDAO 
{
    private static Connection connection;
    
    final String DB_REL_FILE = "src/main/java/FAKEINVADERS_Database/database.db3";
    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

    public LastGamesDAO() 
    {
        this.setConnection(connection);
        try {
			LastGamesDAO.setConnection(DriverManager.getConnection(DB_URL));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    public List<Object[]> getLastGamesForPlayer(String username) throws SQLException 
    {
        List<Object[]> lastGames = new ArrayList<>();
        String sql = "SELECT * FROM LAST_GAMES WHERE USERNAME = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) 
        {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                while (resultSet.next()) {
                    Object[] gameData = new Object[3];
                    gameData[0] = resultSet.getString("USERNAME");
                    gameData[1] = resultSet.getInt("SCORE");
                    gameData[2] = resultSet.getString("DAY");
                    lastGames.add(gameData);
                }
            }
        }
        return lastGames;
    }


    public void insertLastGame(String username, int score) throws SQLException 
    {
        //data
        LocalDateTime currentDate = LocalDateTime.now();
                
        // Creazione della query con PreparedStatement
        String query = "INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES (?, ?, ?)";
                
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
        	preparedStatement.setString(1, username);
        	preparedStatement.setInt(2, score);
        	preparedStatement.setObject(3, currentDate);
            
            // Esecuzione della query
            int rowsAffected = preparedStatement.executeUpdate(); //restituisce il numero di righe che sono state inserite nel db 
                    
            if (rowsAffected > 0) {
                System.out.println("Record inserito con successo nella tabella LAST_GAMES.");
            } else {
                System.out.println("Errore durante l'inserimento del record nella tabella LAST_GAMES.");
            }
    }
}


	public static Connection getConnection() {
		return connection;
	}


	public static void setConnection(Connection connection) {
		LastGamesDAO.connection = connection;
	}
}