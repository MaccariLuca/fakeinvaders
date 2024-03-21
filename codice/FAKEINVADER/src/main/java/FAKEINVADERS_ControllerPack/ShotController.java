package FAKEINVADERS_ControllerPack;



import FAKEINVADERS_ModelPack.Shot;
import FAKEINVADERS_ModelPack.Sprite;
import FAKEINVADERS_ViewPack.ShotView;

public class ShotController extends Sprite {

	private Shot model;
	private ShotView view;
    public ShotController(Shot s, ShotView sv) 
    {
    	model = s;
    	setView(sv);
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

	public ShotView getView() {
		return view;
	}

	public void setView(ShotView view) {
		this.view = view;
	}
	
	public void reload(Shot s)
	{
		this.model = s;
	}
	
	public void die()
	{
		this.model.die();
		this.view.die();
	}
}
