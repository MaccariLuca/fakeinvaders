package FAKEINVADERS_ModelPack;

public class ShotModel extends Sprite 
{
	public ShotModel() {} 
    public ShotModel(int x, int y) 
    {
        int H_SPACE = 17;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
