package FAKEINVADERS_Database;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class PlayerDAOTest 
{
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private PlayerDAO playerDAO;

    @Before
    public void setUp() throws Exception
    {
    	// simulo il comportametno degli oggetti connection, prepare statemen e result set tramite mock
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        
        playerDAO = new PlayerDAO();
        PlayerDAO.setConnection(mockConnection);
    }

    @Test
    public void testInsertPlayer() throws SQLException 
    {
      
        String username = "testUser";
        String password = "testPassword";

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement); // quando viene chiamato il metodo prepareStatement su mockConnection, restituisce mockPreparedStatement
        
        when(mockPreparedStatement.executeUpdate()).thenReturn(1); // quando viene chiamato il metodo executeUpdate su mockPreparedStatement, restituisce 1 (se l'inserimento è andato a buon fine, restituisce un valore maggiore di zero, e significa che almeno una riga è stata modificata.)
      
        assertTrue(playerDAO.insertPlayer(username, password));   // verifica che il metodo insertPlayer ritorni true

        // verifica che i metodi siano stati chiamati con i parametri corretti
        
        verify(mockConnection).prepareStatement(any(String.class));  
        verify(mockPreparedStatement).setString(1, username);  
        verify(mockPreparedStatement).setString(2, password); 
        verify(mockPreparedStatement).executeUpdate();  
    }


    @Test
    public void testPlayerExists() throws SQLException 
    {
        String username = "existingUser";

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        assertTrue(playerDAO.playerExists(username));

        verify(mockConnection).prepareStatement(any(String.class));
        verify(mockPreparedStatement).setString(1, username);
        verify(mockResultSet).next();
    }

    @Test
    public void testCheckCredentials() throws SQLException 
    {
        String username = "validUser";
        String password = "validPassword";

        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        assertTrue(playerDAO.checkCredentials(username, password));

        verify(mockConnection).prepareStatement(any(String.class));
        verify(mockPreparedStatement).setString(1, username);
        verify(mockPreparedStatement).setString(2, password);
        verify(mockResultSet).next();
    }
}
