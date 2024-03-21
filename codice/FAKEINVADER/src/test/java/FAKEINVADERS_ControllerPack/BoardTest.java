package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import FAKEINVADERS_ModelPack.Alien;
import FAKEINVADERS_ModelPack.Commons;
import FAKEINVADERS_ViewPack.AlienView;

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
        board.setAliens(aliensMock); 
        //we expect that, moving the alien thowards right, it will bounce, changing the value of direction
        board.setDirection(1);
    	board.moveAliens();
    	assertEquals(board.getDirection(), -1);
    	
    	//viceversa, now the alien is on the extreme left of the board
    	AlienController anotherAlien = new AlienController(new Alien(Commons.BORDER_LEFT, 60), new AlienView(60));
    	aliensMock.add(anotherAlien);
        board.setAliens(aliensMock); 
        //we expect that, moving the alien thowards left, it will bounce, changing the value of direction
        board.setDirection(-1);
    	board.moveAliens();
    	assertEquals(board.getDirection(), 1);
        
	}
    
    
}
