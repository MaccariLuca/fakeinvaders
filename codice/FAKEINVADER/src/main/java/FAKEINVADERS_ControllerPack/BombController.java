package FAKEINVADERS_ControllerPack;

import FAKEINVADERS_ModelPack.Bomb;
import FAKEINVADERS_ModelPack.Sprite;
import FAKEINVADERS_ViewPack.BombView;

public class BombController extends Sprite 
{
	private Bomb bomb;
	BombView view;
    
    public BombController(Bomb model, BombView view)
    {
    	this.setBomb(model);
    	this.view = view;
    	model = new Bomb(x, y);
    	view = new BombView();
    }
    
    public void setDestroyed(boolean destroyed) {

        this.getBomb().setDestroyed(destroyed);
    }

    public boolean isDestroyed() {
        return this.getBomb().isDestroyed();
    }

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

}
