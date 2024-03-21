package FAKEINVADERS_ControllerPack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testLevelIncrementation() {
        // Set up mocks for aliens and deaths
        List<AlienController> aliensMock = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            aliensMock.add(mock(AlienController.class));
        }
        board.setAliens(aliensMock);
        board.setDeaths(18);

        // Verify initial level
        assertEquals(1, board.getLevel());

        // Verify initial increaseLine and increaseColumns
        assertEquals(0, board.getIncreaseLine());
        assertEquals(0, board.getIncreaseColums());

        // Update game state
        board.incrementLevel();

        // Verify level incrementation
        assertEquals(2, board.getLevel());

        // Verify increaseLine and increaseColumns incrementation
        assertEquals(0, board.getIncreaseLine());
        assertEquals(1, board.getIncreaseColums());
    }
}
