package modelPack;

import java.awt.Image;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import ViewPack.LoginView;
import controllerPack.LoginController;


public class MainMenu {

    public static void main(String[] args) throws IOException 
    {
        new LoginController(new LoginModel(), new LoginView());
    }


	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) 
	{
		Image img = icon.getImage();  
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(resizedImage);
	}

}

