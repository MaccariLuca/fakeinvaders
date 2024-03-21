package FAKEINVADERS_ModelPack;


public class Alien extends Sprite 
{

    private Bomb bullet;

    public Alien(int x, int y) 
    {
    	this.x = x; //x of the alien
        this.y = y; //y of the alien

        setBomb(new Bomb(x, y)); //Initialization of the bullet

    }

	public Bomb getBomb() {
		return bullet;
	}

	public void setBomb(Bomb bullet) {
		this.bullet = bullet;
	}
}