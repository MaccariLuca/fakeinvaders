package game;

import javax.swing.ImageIcon;

public class Bomb extends Sprite 
{
    private boolean destroyed;
    
    public Bomb(int x, int y) {
        initBomb(x, y);
    }

    private void initBomb(int x, int y) {

        setDestroyed(true);
        this.x = x;
        this.y = y;
        var bombImg = "src/main/java/images/shot2.png";
        var icon = new ImageIcon(bombImg);
        setImage(icon.getImage());
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }
}
