package FAKEINVADERS_ModelPack;

import java.sql.SQLException;

import FAKEINVADERS_Database.LastGamesDAO;


public class GameOverModel 
{
	@SuppressWarnings("unused")
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