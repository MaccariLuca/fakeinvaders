package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import FAKEINVADERS_ModelPack.Player;
import FAKEINVADERS_ViewPack.PlayerView;

public class PlayerTest 
{
	private PlayerController player;
	private Player model;
	
	@Before
    public void setUp() 
    {
		model = new Player();
        player = new PlayerController(model, new PlayerView()); 
    }
	
	@Test
    public void testInput() 
    {
		
		int dxStart = player.getDx();
		KeyEvent e = mock(KeyEvent.class);
		
		//Right key
		when(e.getKeyCode()).thenReturn(KeyEvent.VK_RIGHT);
		
		player.keyPressed(e);
		
		assertEquals(player.getDx(), (dxStart + 2));
		
		
		//Left key
		when(e.getKeyCode()).thenReturn(KeyEvent.VK_LEFT);
		
		player.keyPressed(e);
	
		assertEquals(player.getDx(), (dxStart - 2));
		
		//Up key
		int upStart = player.getUp();
		when(e.getKeyCode()).thenReturn(KeyEvent.VK_UP);
		
		player.keyPressed(e);
	
		assertEquals(player.getUp(), (upStart - 2));
		
		//Down key
		when(e.getKeyCode()).thenReturn(KeyEvent.VK_DOWN);
		
		player.keyPressed(e);
	
		assertEquals(player.getUp(), (upStart + 2));
		
    }
    
}
