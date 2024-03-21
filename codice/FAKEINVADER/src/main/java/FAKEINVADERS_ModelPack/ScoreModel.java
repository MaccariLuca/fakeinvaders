package FAKEINVADERS_ModelPack;
import java.sql.SQLException;
import java.util.List;

import FAKEINVADERS_Database.LastGamesDAO;

public class ScoreModel 
{
   private static LastGamesDAO lastGamesDAO;
    
    public ScoreModel() 
    {
    	lastGamesDAO = new LastGamesDAO();
    }
    
    public List<Object[]> getLastGamesForPlayer(String username) throws SQLException {
        return lastGamesDAO.getLastGamesForPlayer(username);
    }


}