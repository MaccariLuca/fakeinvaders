package modelPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class LastGamesDAO {
    private Connection connection;

    public LastGamesDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Object[]> getLastGamesForPlayer(String username) throws SQLException {
        List<Object[]> lastGames = new ArrayList<>();
        String sql = "SELECT * FROM LAST_GAMES WHERE USERNAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Object[] gameData = new Object[3];
                    gameData[0] = resultSet.getString("USERNAME");
                    gameData[1] = resultSet.getInt("SCORE");
                    gameData[2] = resultSet.getString("DAY");
                    lastGames.add(gameData);
                }
            }
        }
        return lastGames;
    }

    public void insertLastGame(String username, int score, String currentDate) throws SQLException {
        String sql = "INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, score);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setString(3, dateFormat.format(currentDate));
            preparedStatement.executeUpdate();
        }
    }
}
