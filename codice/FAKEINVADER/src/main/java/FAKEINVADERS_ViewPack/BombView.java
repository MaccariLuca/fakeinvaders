package FAKEINVADERS_ViewPack;

import javax.swing.ImageIcon;

import FAKEINVADERS_ModelPack.Sprite;

public class BombView extends Sprite{

	public BombView()
	{
		var bombImg = "src/main/java/FAKEINVADERS_Images/shot2.png";
        var icon = new ImageIcon(bombImg);
        setImage(icon.getImage());
	}
}
