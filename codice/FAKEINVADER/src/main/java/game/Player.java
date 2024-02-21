package game;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Sprite 
{
    private int width;
    private int height;

    public Player() 
    {
        initPlayer();
    }

    private void initPlayer() 
    {
        var playerImg = "src/main/java/images/ship.png";
        var imageIcon = new ImageIcon(playerImg);

        width = imageIcon.getImage().getWidth(null);
        height = imageIcon.getImage().getHeight(null);
        setImage(imageIcon.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 550;
        setY(START_Y);
    }

    public void act() {
        x += dx;
        y += up;

        
        if (x <= 2) {
            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - width) {
            x = Commons.BOARD_WIDTH - width;
        }

        // Blocca il giocatore a metà dell'altezza della board
        
        int halfBoardHeight = Commons.BOARD_HEIGHT / 2;
        if (y < halfBoardHeight) {
            y = halfBoardHeight;
        } else if (y > Commons.BOARD_HEIGHT - height) {
            y = Commons.BOARD_HEIGHT - height;
        }
        
        // Blocca il giocatore al terreno 
        if (y > 550) {
            y = 550;
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
