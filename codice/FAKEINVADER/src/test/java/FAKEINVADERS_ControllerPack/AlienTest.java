package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import FAKEINVADERS_ModelPack.Alien;
import FAKEINVADERS_ViewPack.AlienView;

public class AlienTest {

	AlienController alien;
	@Before
    public void setUp() 
    {
        alien = new AlienController(new Alien(0,0), new AlienView(0));
    }
	
	@Test
	public void alienMovementTest()
    {	
		int dxStart = alien.getX();
		
		//Right movement
		alien.act(2);
		assertEquals(alien.getX(), (dxStart + 2));
		
		
		//Left movement, AKA the alien is back at the original X value
		alien.act(-2);	
		assertEquals(alien.getX(), (dxStart));
    }
	

	

}
