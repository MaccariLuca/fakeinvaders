package database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static String DB_REL_FILE = "src/main/java/database/database.db3";
    public static String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

    private static CreateDB instance = new CreateDB();

    private CreateDB() {
    }

    public static CreateDB getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(DB_URL);
    }

    public void creaDB() throws IOException, SQLException 
    {
        if (new File(DB_REL_FILE).exists()) {
            System.out.println("already existing database");
        } else {
            getConnection(); 
            System.out.println("database successfully created");
        }
    }

    public void creaTable() throws IOException, SQLException {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            String PlayersTable = "CREATE TABLE PLAYERS (USERNAME TEXT, PASSWORD TEXT, PRIMARY KEY (USERNAME))";
            String LastGameTable = "CREATE TABLE LAST_GAMES (USERNAME TEXT, SCORE INT, DAY DATETIME, PRIMARY KEY (DAY), FOREIGN KEY (USERNAME) REFERENCES PLAYERS(USERNAME))";

            stmt.executeUpdate(PlayersTable);
            stmt.executeUpdate(LastGameTable);

            System.out.println("Tables successfully created ");
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        getInstance().creaDB();
        getInstance().creaTable();
    }
}
