package game;

import javax.swing.ImageIcon;

public class PowerShot extends Shot {

    public PowerShot() 
    {
    }

    public PowerShot(int x, int y) 
    {
        var shotImg = "src/main/java/images/powerShot.png";
        var imageIcon = new ImageIcon(shotImg);
        setImage(imageIcon.getImage());

        int height = 17;
        setX(x + height);

        int width = 1;
        setY(y - width);
    }
}
