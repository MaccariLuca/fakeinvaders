package modelPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertPlayer(String username, String password) throws SQLException {
        String sql = "INSERT INTO PLAYERS (USERNAME, PASSWORD) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            return preparedStatement.executeUpdate() > 0;
        }
    }


    public boolean playerExists(String username) throws SQLException {
        String sql = "SELECT * FROM PLAYERS WHERE USERNAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
    
    public boolean checkCredentials(String username, String password) throws SQLException {
        String sql = "SELECT * FROM PLAYERS WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }


    // Aggiungi altri metodi per le operazioni CRUD sui giocatori secondo necessità
}
