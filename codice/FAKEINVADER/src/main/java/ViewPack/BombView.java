package ViewPack;

import javax.swing.ImageIcon;

import modelPack.Sprite;

public class BombView extends Sprite{

	public BombView()
	{
		var bombImg = "src/main/java/images/shot2.png";
        var icon = new ImageIcon(bombImg);
        setImage(icon.getImage());
	}
}
