package DatabaseTest;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import modelPack.LastGamesDAO;
import modelPack.PlayerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseTest 
{
    private Connection connection;

    @Before
    public void setUp() throws SQLException 
    {
        // Inizializza la connessione al database di test
    	final String DB_REL_FILE = "src/main/java/database/database.db3";
	    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	    
        connection = DriverManager.getConnection(DB_URL);

        // Popola il database di test con dati iniziali
        initializeTestData();
    }

    @After
    public void tearDown() throws SQLException 
    {
        // Chiudi la connessione al database dopo l'esecuzione dei test
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testInsertPlayer() throws SQLException 
    {
        // Esegui un'operazione di inserimento di un giocatore nel database
        PlayerDAO playerDAO = new PlayerDAO(connection);
        assertTrue(playerDAO.insertPlayer("testuser", "testpassword"));

        // Verifica che il giocatore sia stato inserito correttamente
        assertTrue(playerDAO.playerExists("testuser"));
    }

    @Test
    public void testRetrieveLastGamesForPlayer() throws SQLException 
    {
        // Esegui un'operazione di recupero degli ultimi giochi di un giocatore dal database
        LastGamesDAO lastGamesDAO = new LastGamesDAO(connection);
        List<Object[]> lastGames = lastGamesDAO.getLastGamesForPlayer("testuser");

        // Verifica che la lista non sia vuota
        assertFalse(lastGames.isEmpty());
    }

    @AfterClass
    public static void tearDownAfterClass() throws SQLException 
    {
        // Elimina i dati di test dal database dopo l'esecuzione di tutti i test
        final String DB_REL_FILE = "src/main/java/database/database.db3";
	    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	    
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            
            statement.executeUpdate("DELETE FROM PLAYERS WHERE USERNAME = 'testuser'");
            statement.executeUpdate("DELETE FROM LAST_GAMES WHERE USERNAME = 'testuser'");
        }
    }

    private void initializeTestData() throws SQLException 
    {
        // Inizializza il database con dati di test
        try (Statement statement = connection.createStatement()) 
        {
            // Crea la tabella PLAYERS se non esiste già
            statement.execute("CREATE TABLE IF NOT EXISTS PLAYERS (USERNAME TEXT, PASSWORD TEXT)");

            // Inserisci dati di test nella tabella PLAYERS
            statement.execute("INSERT INTO PLAYERS (USERNAME, PASSWORD) VALUES ('testuser', 'testpassword')");

            // Crea la tabella LAST_GAMES se non esiste già
            statement.execute("CREATE TABLE IF NOT EXISTS LAST_GAMES (USERNAME TEXT, SCORE INT, DAY DATETIME)");

            // Inserisci dati di test nella tabella LAST_GAMES
            statement.execute("INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES ('testuser', 100, '2024-02-29 12:00:00')");
        }
    }
}
