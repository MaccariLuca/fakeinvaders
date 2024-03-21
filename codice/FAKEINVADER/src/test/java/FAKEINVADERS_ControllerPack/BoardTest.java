package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
    	List<AlienController> aliensMock = new ArrayList<>();
        for (int i = 0; i < 18; i++) { aliensMock.add(mock(AlienController.class));}
        
        board.setAliens(aliensMock);
        for(int i = 0; i < 18; i++)
        {
        	if(!aliensMock.get(i).isDying()) //se l'alieno non è morto
        	{
        		board.setDeaths(board.getDeaths() + 1);
        	}
        }
        
        assertEquals(board.getDeaths(), 18);
    }
    
    
    
}
