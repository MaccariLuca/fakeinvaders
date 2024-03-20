package FAKEINVADERS_ModelPack;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import FAKEINVADERS_Database.LastGamesDAO;

public class ScoreModel 
{
    private static ResultSet resultSet;
    private static LastGamesDAO lastGamesDAO;
    
    public ScoreModel() 
    {
    	lastGamesDAO = new LastGamesDAO();
    	
    	String username = SessionManager.getCurrentUsername();
    	
    }
    
    public List<Object[]> getLastGamesForPlayer(String username) throws SQLException {
        return lastGamesDAO.getLastGamesForPlayer(username);
    }


}