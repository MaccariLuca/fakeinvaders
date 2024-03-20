package controllerPack;

import java.io.IOException;

import ViewPack.ScoreView;
import modelPack.Menu;
import modelPack.ScoreModel;

public class ScoreController 
{
	private ScoreView view;
	private ScoreModel model;

    public ScoreController() throws IOException 
    {
        view = new ScoreView();
        model = new ScoreModel();
        view.addBackButtonListener(e -> returnToMainMenu());
    }

    private void returnToMainMenu() 
    {
    	view.dispose();
        try {
            new MenuController();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
}
