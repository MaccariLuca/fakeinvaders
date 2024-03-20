package ViewPack;

import javax.swing.ImageIcon;

import modelPack.Shot;

public class PowerShotView extends Shot
{
	public PowerShotView()
	{
		var shotImg = "src/main/java/images/powerShot.png";
	    var ii = new ImageIcon(shotImg);
	    setImage(ii.getImage());
	}
}
