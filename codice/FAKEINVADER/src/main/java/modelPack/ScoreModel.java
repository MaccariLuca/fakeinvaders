
package modelPack;
import database.LastGamesDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreModel 
{
    private static ResultSet resultSet;

    public ScoreModel() 
    {
    	LastGamesDAO lastGamesDAO = new LastGamesDAO();
    	
    	String username = SessionManager.getCurrentUsername();
    	
    	try {
			ResultSet resultSet = lastGamesDAO.getLastGamesForPlayer(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static ResultSet getResultSet()
    {
    	return resultSet;
    }
    	
}
