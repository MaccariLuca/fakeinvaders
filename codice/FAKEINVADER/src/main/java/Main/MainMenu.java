package Main;

import java.awt.Image;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ViewPack.LoginView;
import controllerPack.LoginController;
import modelPack.LoginModel;


public class MainMenu {

    public static void main(String[] args) throws IOException 
    {
        new LoginController(new LoginModel(), new LoginView());
    }
}

