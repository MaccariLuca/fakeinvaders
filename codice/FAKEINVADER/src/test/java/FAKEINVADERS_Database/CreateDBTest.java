package FAKEINVADERS_Database;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTest {

    private CreateDB createDB;

    @Before
    public void setUp() {
        createDB = CreateDB.getInstance();
    }

    @After
    public void tearDown() {
        // Clean up the database file if it exists after each test
        File dbFile = new File(CreateDB.DB_REL_FILE);
        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    @Test
    public void testCreaDB() throws SQLException, IOException {
        // Test creation of database
        createDB.creaDB();
        assertTrue(new File(CreateDB.DB_REL_FILE).exists());
    }

    @Test
    public void testCreaTable() throws SQLException, IOException {
        // Test creation of tables
        createDB.creaDB(); // Ensure database exists before creating tables
        createDB.creaTable();
        
        // Verify that tables were created
        try (Connection conn = createDB.getConnection();
             Statement stmt = conn.createStatement()) {
            // Verify PLAYERS table
            ResultSet rsPlayers = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='PLAYERS'");
            assertTrue(rsPlayers.next());
            
            // Verify LAST_GAMES table
            ResultSet rsLastGames = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='LAST_GAMES'");
            assertTrue(rsLastGames.next());
        }
    }

    // Add more test cases as needed
}

