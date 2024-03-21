package FAKEINVADERS_Database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LastGamesDAOTest 
{

    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private LastGamesDAO lastGamesDAO;

    @Before
    public void setUp() throws Exception 
    {
    	//configuro i mock
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        
        //...e la connessione 
        lastGamesDAO = new LastGamesDAO();
        LastGamesDAO.setConnection(mockConnection);
    }

    @Test
    public void testGetLastGamesForPlayer() throws SQLException 
    {
        String username = "testUser";
        List<Object[]> expectedLastGames = new ArrayList<>();
        expectedLastGames.add(new Object[] { "testUser", 100, "2024-03-21" });

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getString("USERNAME")).thenReturn("testUser");
        when(mockResultSet.getInt("SCORE")).thenReturn(100);
        when(mockResultSet.getString("DAY")).thenReturn("2024-03-21");

        List<Object[]> actualLastGames = lastGamesDAO.getLastGamesForPlayer(username);	//esecuzione query 

        assertEquals(expectedLastGames.size(), actualLastGames.size());
        assertArrayEquals(expectedLastGames.get(0), actualLastGames.get(0));

        //verifica 
        verify(mockConnection).prepareStatement(any(String.class));
        verify(mockPreparedStatement).setString(1, username);
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet).getString("USERNAME");
        verify(mockResultSet).getInt("SCORE");
        verify(mockResultSet).getString("DAY");
    }

    //in questo test non posso inserire il parametro current date, perchè considera anche i secondi e non potrà mai essere uguale
    @Test
    public void testInsertLastGame() throws SQLException 
    {
        String username = "testUser";
        int score = 100;
       

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        lastGamesDAO.insertLastGame(username, score);

        verify(mockConnection).prepareStatement(any(String.class));
        verify(mockPreparedStatement).setString(1, username);
        verify(mockPreparedStatement).setInt(2, score);
        verify(mockPreparedStatement).executeUpdate();
    }

}
