package ViewPack;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controllerPack.AlienController;

public class BoardTest 
{
	private Board board;
	
	@Before
    public void setUp() 
	{
        board = new Board();
    }
	@Test
    public void testGameStartsAtLevel1() 
	{
        assertEquals(1, board.getLevel());
    }
	
    @Test
    public void testGameInitLevel1() 
    {
        board.gameInit(1);
        assertNotNull(board.getAliens());
        assertEquals(0, board.getIncreaseColums());
        assertEquals(0, board.getIncreaseLine());
    }

    @Test
    public void testGameInitLevel2() 
    {
        board.gameInit(2);
        assertNotNull(board.getAliens());
        assertEquals(1, board.getIncreaseColums());
        assertEquals(0, board.getIncreaseLine());
    }

    @Test
    public void testGameInitLevel3() 
    {	
    	testGameInitLevel2();
        board.gameInit(3);
        
        assertNotNull(board.getAliens());
        assertEquals(1, board.getIncreaseColums());
        assertEquals(1, board.getIncreaseLine());
        
    }

    
   
    
  

}
