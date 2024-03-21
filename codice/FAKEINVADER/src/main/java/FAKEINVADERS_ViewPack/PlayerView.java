package FAKEINVADERS_ViewPack;

import javax.swing.ImageIcon;

import FAKEINVADERS_ModelPack.Sprite;

public class PlayerView extends Sprite{
	

	public PlayerView()
	{

        var playerImg = "src/main/java/FAKEINVADERS_Images/ship.png";
        var ii = new ImageIcon(playerImg);

        setImage(ii.getImage());
	}
}
