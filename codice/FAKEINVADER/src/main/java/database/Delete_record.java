package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_record 
{
	public static void main(String [] args)
	{
		
	
	final String DB_REL_FILE = "src/main/java/database/database.db3";
	final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	{

	try (Connection conn = DriverManager.getConnection(DB_URL)) {
	    if (conn != null) {
	        // Eliminazione dei record dalla tabella players
	        String deletePlayersQuery = "DELETE FROM PLAYERS";
	        try (PreparedStatement pstmtPlayers = conn.prepareStatement(deletePlayersQuery)) {
	            int rowsAffectedPlayers = pstmtPlayers.executeUpdate();
	            System.out.println(rowsAffectedPlayers + " record eliminati dalla tabella players.");
	        } catch (SQLException ex) {
	            System.out.println("Errore durante l'eliminazione dei record dalla tabella players: " + ex.getMessage());
	        }

	        // Eliminazione dei record dalla tabella last_games
	        String deleteLastGamesQuery = "DELETE FROM LAST_GAMES";
	        try (PreparedStatement pstmtLastGames = conn.prepareStatement(deleteLastGamesQuery)) {
	            int rowsAffectedLastGames = pstmtLastGames.executeUpdate();
	            System.out.println(rowsAffectedLastGames + " record eliminati dalla tabella last_games.");
	        } catch (SQLException ex) {
	            System.out.println("Errore durante l'eliminazione dei record dalla tabella last_games: " + ex.getMessage());
	        }
	    }
	} catch (SQLException ex) {
	    System.out.println("Errore durante l'accesso al database: " + ex.getMessage());
	}
	}
	}

}
