package ViewPack;

import javax.swing.ImageIcon;

import modelPack.Sprite;

public class AlienView extends Sprite
{
	public AlienView(int y)
	{
		String alienImage = "src/main/java/images/alien.png"; //default sprite of the alien 
        if(y == 60)
        {
        	alienImage = "src/main/java/images/alien2.png"; //second sprite of the alien
        }
        else if(y < 60)
        {
        	alienImage = "src/main/java/images/alien3.png"; //third sprite of the alien
        }
        var icon = new ImageIcon(alienImage); //sets the image to the alien

        setImage(icon.getImage());
	}
}
