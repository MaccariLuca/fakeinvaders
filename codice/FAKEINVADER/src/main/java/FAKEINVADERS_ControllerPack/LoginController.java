package FAKEINVADERS_ControllerPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import FAKEINVADERS_ModelPack.LoginModel;
import FAKEINVADERS_ModelPack.SessionManager;
import FAKEINVADERS_ViewPack.LoginView;
import FAKEINVADERS_ViewPack.MenuView;

public class LoginController 
{
    private final LoginModel model;
    private final LoginView view;

    public LoginController() throws IOException 
    {
    	model = new LoginModel();
    	view = new LoginView();
		
    
        view.addLoginListener(new LoginButtonListener());
        view.addRegistrationListener(new RegistrationButtonListener());
    }

    // ActionListener per il pulsante di login
    private class LoginButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String username = view.getUsername();
            String password = view.getPassword();

            if (model.checkCredentials(username, password)) 
            {
            	SessionManager.setCurrentUsername(username);
                view.dispose();
                try {
					new MenuController(new MenuView());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                view.showError("Attention: unregistered user!");
            }
        }
    }

    // ActionListener per il pulsante di registrazione
    private class RegistrationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String username = view.getUsername();
            String password = view.getPassword();
            if (model.registerPlayer(username, password)) 
            {
            	SessionManager.setCurrentUsername(username);
			    view.dispose();
			    try {
					new MenuController(new MenuView());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
			    view.showError("Attention: User already registered!");
			}
        }
    }
}
