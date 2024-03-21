package FAKEINVADERS_ViewPack;

import javax.swing.ImageIcon;

import FAKEINVADERS_ModelPack.Sprite;

public class ShotView extends Sprite
{

	public ShotView() 
	{
	    var shotImg = "src/main/java/FAKEINVADERS_Images/shot.png";
	    var ii = new ImageIcon(shotImg);
	    setImage(ii.getImage());
	}
}
