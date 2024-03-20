package FAKEINVADERS_ControllerPack;

import java.awt.Image;

import FAKEINVADERS_ModelPack.PowerShot;
import FAKEINVADERS_ModelPack.Shot;
import FAKEINVADERS_ModelPack.Sprite;
import FAKEINVADERS_ViewPack.PowerShotView;
import FAKEINVADERS_ViewPack.ShotView;

public class PowerShotController extends Sprite {

	private PowerShot model;
	private PowerShotView view;
    public PowerShotController(PowerShot s, PowerShotView sv) 
    {
    	model = s;
    	view = sv;
    }

    @Override
    public void setX(int x)
    {
    	model.setX(x);
    }
    @Override
    public void setY(int y)
    {
    	model.setY(y);
    }
    @Override
    public int getX()
    {
    	return model.getX();
    }
    @Override
    public int getY()
    {
    	return model.getY();
    }

	public PowerShotView getView() {
		return view;
	}

	public void setView(PowerShotView view) {
		this.view = view;
	}
	
	public void reload(PowerShot s)
	{
		this.model = s;
	}
	
	public void die()
	{
		this.model.die();
		this.view.die();
	}
	
	public Image getImage()
	{
		return view.getImage();
	}

    public boolean isVisible() 
    {

        return view.isVisible();
    }

    public void setVisible(boolean visible) 
    {

        view.setVisible(visible);
    }

    public void setImage(Image image) 
    {

        view.setImage(image);
    }

    
    
  
}
