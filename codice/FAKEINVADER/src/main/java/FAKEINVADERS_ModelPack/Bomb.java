package FAKEINVADERS_ModelPack;


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
    }

    public void setDestroyed(boolean destroyed) {

        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {

        return destroyed;
    }

    
}
