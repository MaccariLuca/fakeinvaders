package game;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() 
    {}

    public Shot(int x, int y) 
    {
        var shotImg = "src/main/java/images/shot.png";
        var imageIcon = new ImageIcon(shotImg);
        setImage(imageIcon.getImage());

        int height = 17;
        setX(x + height);

        int width = 1;
        setY(y - width);
    }
}
