package FAKEINVADERS_ControllerPack;

import java.awt.Image;

import FAKEINVADERS_ModelPack.Alien;
import FAKEINVADERS_ModelPack.Bomb;
import FAKEINVADERS_ModelPack.Sprite;
import FAKEINVADERS_ViewPack.AlienView;

public class AlienController extends Sprite
{
	private Alien model;
	private AlienView view;

    public AlienController(Alien a, AlienView av) 
    {
        model = a;
        view = av;
    }

    public void act(int direction)  //moves the alien
    {
        model.setX(model.getX() + direction);
    }

    public Bomb getBomb() { //return the bomb value
        return model.getBomb();
    }
    
    public Image getImage()
    {
    	return view.getImage();
    }
    
    public void setImage(Image i)
    {
    	view.setImage(i);
    }
    
    public int getX()
    {
    	return model.getX();
    }
    
    public int getY()
    {
    	return model.getY();
    }
    
    public void setY(int y)
    {
    	model.setY(y);
    }
    
    @Override
    public boolean isDying()
    {
    	return model.isDying();
    }
    
    public void setDying(boolean d)
    {
    	model.setDying(d);
    	view.setDying(d);
    }
    
    @Override
    public void die()
    {
    	model.die();
    }
    
    public boolean isVisible()
    {
    	return model.isVisible();
    }
    
}
