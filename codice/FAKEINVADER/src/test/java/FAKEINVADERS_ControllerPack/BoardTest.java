package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import FAKEINVADERS_ModelPack.Alien;
import FAKEINVADERS_ModelPack.Commons;
import FAKEINVADERS_ModelPack.Player;
import FAKEINVADERS_ModelPack.PowerShot;
import FAKEINVADERS_ViewPack.AlienView;
import FAKEINVADERS_ViewPack.PlayerView;
import FAKEINVADERS_ViewPack.PowerShotView;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() 
    {
        board = new Board();
    }

    @Test
    public void testLevelIncrementation() 
    {
        
        board.setDeaths(18);

     
        assertEquals(1, board.getLevel());	//verfica che il game parte a livello 1 

        //verifica degli incrementi iniziali 
        assertEquals(0, board.getIncreaseLine());
        assertEquals(0, board.getIncreaseColums());

        board.incrementLevel();	//incremento

        assertEquals(2, board.getLevel());	//verifica incremento 

        // Verify increaseLine and increaseColumns incrementation
        assertEquals(0, board.getIncreaseLine());
        assertEquals(1, board.getIncreaseColums());
    }
    
    @Test
    public void testPointsPerDeath()
    {
    	AlienController a = new AlienController(new Alien(0,0), new AlienView(0)); //creates only an alien, we don't need really 18 aliens
    	List<AlienController> aliensMock = new ArrayList<>();
        for (int i = 0; i < 18; i++) { aliensMock.add(a);} //we put 18 aliens in the arraylist
        
        
        board.setAliens(aliensMock);//the aliens are set in the board
        for(int i = 0; i < 18; i++)
        {
        	if(!aliensMock.get(i).isDying()) //if the alien is still alive
        	{
        		board.setDeaths(board.getDeaths() + 1);
        	}
        }
        
        assertEquals(board.getDeaths(), 18);
    }
    
    @Test
    public void alienTouchesTheBorder()
	{
    	List<AlienController> aliensMock = new ArrayList<>();
    	//creates an alien and puts it in the extreme right if the frame
    	AlienController lastAlien = new AlienController(new Alien((Commons.BOARD_WIDTH - Commons.BORDER_RIGHT), 60), new AlienView(60));
        aliensMock.add(lastAlien);
        
        int yStart = aliensMock.get(0).getY(); //we take the y-value at the start
        board.setAliens(aliensMock); 
        //we expect that, moving the alien thowards right, it will bounce, changing the value of direction
        board.setDirection(1);
    	board.moveAliens();
    	assertEquals(board.getDirection(), -1); //the direction was 1, but now it needs to be -1 because the aliens touched the border
    	//the y-value needs to be smaller than before. In the board is used a fixed value, and we expect that the y value has decreased by that value
    	assertEquals(aliensMock.get(0).getY(), yStart + Commons.GO_DOWN); 
    	
    	//viceversa, now the alien is on the extreme left of the board
    	AlienController anotherAlien = new AlienController(new Alien(Commons.BORDER_LEFT, 60), new AlienView(60));
    	aliensMock.add(anotherAlien);
    	yStart = aliensMock.get(1).getY(); //we take the y-value at the start (1 because the index 0 was occupied by the alien up here)
        board.setAliens(aliensMock); 
        //we expect that, moving the alien thowards left, it will bounce, changing the value of direction
        board.setDirection(-1);
    	board.moveAliens();
    	assertEquals(board.getDirection(), 1);
    	assertEquals(aliensMock.get(1).getY(), yStart + Commons.GO_DOWN);
        
	}
    
    @Test
    public void alienInvasion()
    //If an alien reaches the bottom of the board, you lose
    {
    	int yET = Commons.GROUND;
    	List<AlienController> aliensMock = new ArrayList<>();
    	//creates an alien and puts it at the bottom of the frame
    	AlienController ET = new AlienController(new Alien(50, yET), new AlienView(yET));
        aliensMock.add(ET);
        
        board.setAliens(aliensMock); 
        
        assertEquals(board.isInGame(), true);
        board.handleAlienCollisions();
        assertEquals(board.isInGame(), false);
    }
    
   /* @Test
    public void onlyOnePowerShot()
    //in every level, the player can shoot only one powerShot
    {
    	//it's a powershot that has reached the top of the board. So it has to be shooted
    	PowerShot model = new PowerShot(0, Commons.BOARD_HEIGHT);
    	PowerShotView view = new PowerShotView();
    	PowerShotController powerShot = new PowerShotController(model, view);
        
    	board.setPowerShot(powerShot);
    	board.updatePowerShot();
    	assertEquals(board.isPowerShotShooted(), true);
    	board.handlePowerShotCollisions();
    	assertEquals(board.isPowerShotShooted(), false);
    	
    	//at the next level, tha player can shoot another powershot
    	board.incrementLevel();
    	assertEquals(board.isPowerShotShooted(), true);
    	board.handlePowerShotCollisions();
    	assertEquals(board.isPowerShotShooted(), false);
    }*/
}