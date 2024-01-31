package controllerPack;

import java.awt.event.KeyEvent;

import modelPack.Player;

public class PlayerController {
	
	Player player;
	
	public PlayerController(Player player)
	{
		this.player = player;
	}

	public void keyPressed(KeyEvent e) 
    {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        {
            player.setDx(-2);
        }

        if (key == KeyEvent.VK_RIGHT) 
        {
            player.setDx(2);
        }
        if (key == KeyEvent.VK_UP) 
        {
            player.setUp(-2);
        }
        if (key == KeyEvent.VK_DOWN) 
        {
        	player.setUp(2);
        }
    }

    public void keyReleased(KeyEvent e) 
    {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) 
        {

        	player.setDx(0);
        }

        if (key == KeyEvent.VK_RIGHT) 
        {

        	player.setDx(0);
        }
        
        if (key == KeyEvent.VK_UP) 
        {

            player.setUp(0);
        }

        if (key == KeyEvent.VK_DOWN) 
        {

        	player.setUp(0);
        }
    }
}
