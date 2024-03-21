package FAKEINVADERS_ControllerPack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import FAKEINVADERS_ModelPack.ScoreModel;
import FAKEINVADERS_ModelPack.SessionManager;
import FAKEINVADERS_ViewPack.MenuView;
import FAKEINVADERS_ViewPack.ScoreView;

public class ScoreController 
{
	private ScoreView view;
	private ScoreModel model;

    public ScoreController() throws IOException 
    {
        view = new ScoreView();
        model = new ScoreModel();
        view.addBackButtonListener(e -> returnToMainMenu());
        
        getLastGamesForPlayer(SessionManager.getCurrentUsername());
    }

    private void returnToMainMenu() 
    {
    	view.dispose();
        try {
            new MenuController(new MenuView());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    public void getLastGamesForPlayer(String username) {
        try {
            List<Object[]> lastGames = model.getLastGamesForPlayer(username);
            view.showLastGames(lastGames);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    
}
