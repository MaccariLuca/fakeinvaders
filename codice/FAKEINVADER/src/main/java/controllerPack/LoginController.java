package controllerPack;

import modelPack.LoginModel;
import ViewPack.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController 
{
    private final LoginModel model;
    private final LoginView view;

    public LoginController(LoginModel model, LoginView view) 
    {
        this.model = model;
        this.view = view;

        // Aggiunta dei listener per i pulsanti
        view.addLoginListener(new LoginButtonListener());
        view.addRegistrationListener(new RegistrationButtonListener());
    }

    // ActionListener per il pulsante di login
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            boolean loginSuccess = model.checkCredentials(username, password);
            if (loginSuccess) {
                view.dispose();
                view.openNewScreen();
            } else {
                view.showError("Attention: unregistered user!");
            }
        }
    }

    // ActionListener per il pulsante di registrazione
    private class RegistrationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();
            if (model.registerPlayer(username, password)) {
			    view.dispose();
			    view.openNewScreen();
			} else {
			    view.showError("Attention: User already registered!");
			}
        }
    }
}
