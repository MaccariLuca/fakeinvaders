package ViewPack;

import javax.swing.ImageIcon;

import modelPack.Sprite;

public class ShotView extends Sprite
{

	public ShotView() 
	{
	    var shotImg = "src/main/java/images/shot.png";
	    var ii = new ImageIcon(shotImg);
	    setImage(ii.getImage());
	}
}
