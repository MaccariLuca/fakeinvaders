package FAKEINVADERS_ViewPack;

import javax.swing.ImageIcon;

import FAKEINVADERS_ModelPack.Shot;

public class PowerShotView extends Shot
{
	public PowerShotView()
	{
		var shotImg = "src/main/java/FAKEINVADERS_Images/powerShot.png";
	    var ii = new ImageIcon(shotImg);
	    setImage(ii.getImage());
	}
}
