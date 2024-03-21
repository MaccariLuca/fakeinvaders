package FAKEINVADERS_ControllerPack;

import java.io.IOException;

import FAKEINVADERS_ViewPack.MenuView;
import FAKEINVADERS_ViewPack.RulesView;

public class RulesController 
{
	private RulesView view;

    public RulesController() throws IOException 
    {
        view = new RulesView();
        view.addBackButtonListener(e -> returnToMainMenu());
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
}