package ViewPack;

import javax.swing.ImageIcon;

import modelPack.Sprite;

public class PlayerView extends Sprite{
	

	public PlayerView()
	{

        var playerImg = "src/main/java/images/ship.png";
        var ii = new ImageIcon(playerImg);

        setImage(ii.getImage());
	}
}
