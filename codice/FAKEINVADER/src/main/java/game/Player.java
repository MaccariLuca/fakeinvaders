package game;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite 
{
    private int width;

    public Player() 
    {
        initPlayer();
    }

    private void initPlayer() 
    {
        var playerImg = "src/main/java/images/ship.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 550;
        setY(START_Y);
    }

    public void act() 
    {

        x += dx;
        y += up;

        if (x <= 2) 
        {

            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) 
        {

            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) 
    {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        {

            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) 
        {

            dx = 2;
        }
        if (key == KeyEvent.VK_UP) 
        {

            up = -2;
        }
        if (key == KeyEvent.VK_DOWN) 
        {

            up = 2;
        }
    }

    public void keyReleased(KeyEvent e) 
    {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) 
        {

            dx = 0;
        }
        
        if (key == KeyEvent.VK_UP) 
        {

            up = 0;
        }

        if (key == KeyEvent.VK_DOWN) 
        {

            up = 0;
        }
    }
}
