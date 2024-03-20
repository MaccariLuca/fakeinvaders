package controllerPack;

import modelPack.GameOverModel;

import java.io.IOException;

import ViewPack.GameOverView;
import modelPack.FakeInvaders;
import modelPack.Menu;

public class GameOverController 
{
	private final GameOverModel model;
	private final GameOverView view;
    private int lastScore;

    public GameOverController(GameOverModel model ,GameOverView view ) 
    {
    	this.model = model;
        this.view = view;
   
        view.addStartButtonListener(e -> startNewGame());
        view.addBackButtonListener(e -> returnToMainMenu());
        view.addExitButtonListener(e -> exitGame());
    }

    private void startNewGame() 
    {
        new FakeInvaders();
        view.dispose();
    }

    private void returnToMainMenu() 
    {
        try {
            new MenuController();
            view.dispose();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void exitGame() 
    {
        view.dispose();
    }
}
