package modelPack;

import database.LastGamesDAO;
import java.sql.SQLException;


public class GameOverModel 
{
    private int lastScore;

    public GameOverModel(int lastScore) 
    {
        this.lastScore = lastScore;
        LastGamesDAO game = new LastGamesDAO();
        
        String username = SessionManager.getCurrentUsername();
        try {
			game.insertLastGame(username, lastScore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}