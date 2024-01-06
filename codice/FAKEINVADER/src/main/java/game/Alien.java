package game;

import javax.swing.ImageIcon;

public class Alien extends Sprite 
{

    private Bomb bullet;

    public Alien(int x, int y) 
    {
        startAlien(x, y);
    }

    //Starts the alien in a position x and y. Based on the y the sprite changes
    private void startAlien(int x, int y) 
    {

        this.x = x; //x of the alien
        this.y = y; //y of the alien

        bullet = new Bomb(x, y); //Initialization of the bullet


        String alienImage = "src/main/java/images/alien.png"; //default sprite of the alien 
        if(y == 60)
        {
        	alienImage = "src/main/java/images/alien2.png"; //second sprite of the alien
        }
        else if(y < 60)
        {
        	alienImage = "src/main/java/images/alien3.png"; //third sprite of the alien
        }
        var icon = new ImageIcon(alienImage); //sets the image to the alien

        setImage(icon.getImage());
    }

    public void act(int direction)  //moves the alien
    {
        this.x += direction;
    }

    public Bomb getBomb() { //return the bomb value
        return bullet;
    }

    
}
