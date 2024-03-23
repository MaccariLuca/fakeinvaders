package FAKEINVADERS_ViewPack;

import javax.swing.ImageIcon;

import FAKEINVADERS_ModelPack.ShotModel;

public class PowerShotView extends ShotModel
{
	public PowerShotView()
	{
		var shotImg = "src/main/java/FAKEINVADERS_Images/powerShot.png";
	    var ii = new ImageIcon(shotImg);
	    setImage(ii.getImage());
	}
}
