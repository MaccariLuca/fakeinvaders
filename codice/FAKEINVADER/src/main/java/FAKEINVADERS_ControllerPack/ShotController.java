package FAKEINVADERS_ControllerPack;


import FAKEINVADERS_ModelPack.ShotModel;
import FAKEINVADERS_ModelPack.Sprite;
import FAKEINVADERS_ViewPack.ShotView;

public class ShotController extends Sprite 
{
	private boolean shotActive;
	private ShotModel model;
	private ShotView view;
    public ShotController(ShotModel s, ShotView sv) 
    {
    	model = s;
    	setView(sv);
    	shotActive = false;
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

	public ShotView getView() 
	{
		return view;
	}

	public void setView(ShotView view) 
	{
		this.view = view;
	}
	
	public void reload(ShotModel shot)
	{
		this.model = shot;
	}
	
	public void die()
	{
		this.model.die();
		this.view.die();
		shotActive = false;
	}
	
	public boolean isShotActive() {
        return shotActive;
    }

    public void setShotActive(boolean active) {
        shotActive = active;
    }
}
