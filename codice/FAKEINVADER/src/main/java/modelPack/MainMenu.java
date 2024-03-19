package modelPack;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class MainMenu
{

	public static void main(String[] args) throws Exception
	{
		new LoginMenu();
	}
	
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) 
	{
		Image img = icon.getImage();  
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(resizedImage);
	}

}

