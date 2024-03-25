package FAKEINVADERS_ControllerPack;

import java.awt.Image;
import java.awt.event.KeyEvent;

import FAKEINVADERS_ModelPack.Player;
import FAKEINVADERS_ViewPack.PlayerView;

public class PlayerController 
{
	
	private Player model;
	private PlayerView view;
	
	public PlayerController(Player player, PlayerView playerView)
	{
		this.model = player;
		this.view = playerView;
	}

	public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == 39) 
        {
        	model.setDx(-2);
        }

        if (key == KeyEvent.VK_RIGHT) 
        {
            model.setDx(2);
        }
        if (key == KeyEvent.VK_UP) 
        {
            model.setUp(-2);
        }
        if (key == KeyEvent.VK_DOWN) 
        {
        	model.setUp(2);
        }
    }

    public void keyReleased(KeyEvent e) 
    {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        {

        	model.setDx(0);
        }

        if (key == KeyEvent.VK_RIGHT) 
        {

        	model.setDx(0);
        }
        
        if (key == KeyEvent.VK_UP) 
        {

        	model.setUp(0);
        }

        if (key == KeyEvent.VK_DOWN) 
        {

        	model.setUp(0);
        }
    }
    
    public void die() 
    {
        view.setVisible(false);
    }

    public boolean isVisible() 
    {
        return view.isVisible();
    }

    public void setVisible(boolean visible) 
    {

        this.view.setVisible(visible);
    }

    public void setImage(Image image) 
    {

        this.view.setImage(image);
    }

    public Image getImage() 
    {

        return view.getImage();
    }

    public void setX(int x) 
    {

        this.model.setX(x);
    }

    public void setY(int y) 
    {

    	this.model.setY(y);
    }

    public int getY() 
    {

        return model.getY();
    }

    public int getX() 
    {

        return model.getX();
    }

    public void setDying(boolean dying) 
    {

        this.model.setDying(dying);
    }

    public boolean isDying() 
    {

        return model.isDying();
    }
    
    public void act()
    {
    	model.act();
    }
    
	public int getDx() {
		return model.getDx();
	}

	public int getUp() {
		return model.getUp();
	}

  public void setPosition(int x, int y) {
        model.setX(x);
        model.setY(y);
    }
	
}