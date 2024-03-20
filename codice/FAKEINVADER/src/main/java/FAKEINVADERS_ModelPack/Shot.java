package FAKEINVADERS_ModelPack;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    public Shot() {}

    public Shot(int x, int y) 
    {
        int H_SPACE = 17;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
