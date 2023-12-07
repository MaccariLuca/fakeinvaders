package game.pack;

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


        String alienImage = "src/game/pack/images/alien.png"; //default sprite of the alien 
        if(y == 60)
        {
        	alienImage = "src/game/pack/images/alien2.jpg"; //second sprite of the alien
        }
        else if(y < 60)
        {
        	alienImage = "src/game/pack/images/alien3.jpg"; //third sprite of the alien
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
            var bombImg = "src/game/pack/images/shot2.png";
            var icon = new ImageIcon(bombImg);
            setImage(icon.getImage());
        }

        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }
    }
}
