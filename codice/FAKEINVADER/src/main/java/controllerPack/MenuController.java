package controllerPack;

import java.io.IOException;
import ViewPack.MenuView;
import modelPack.FakeInvaders;

public class MenuController 
{
    private final MenuView view;

    public MenuController(MenuView view) throws IOException 
    {
        this.view = view;
        
        view.addStartButtonListener(e -> startNewGame());
        view.addRulesButtonListener(e -> rules());
        view.addScoreButtonListener(e -> score());
        view.addExitButtonListener(e -> exitGame());
    }

    private void startNewGame() 
    {
        new FakeInvaders();
        view.dispose();
    }
    private void exitGame() 
    {
        view.dispose();
    }
    
    private void rules() 
    {
    	view.dispose();
    	try {
    		
    		new RulesController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void score()
    {
    	view.dispose();
    	try {
			new ScoreController();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}