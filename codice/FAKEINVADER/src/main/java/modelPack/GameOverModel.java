package modelPack;

import database.CreateDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GameOverModel {
    private int lastScore;

    public GameOverModel(int lastScore) {
        this.lastScore = lastScore;
        saveScoreToDatabase();
    }

    private void saveScoreToDatabase() {
        try (Connection conn = CreateDB.getInstance().getConnection()) {
            if (conn != null) {
                String username = SessionManager.getCurrentUsername();
                LocalDateTime currentDate = LocalDateTime.now();
                String query = "INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, username);
                    pstmt.setInt(2, lastScore);
                    pstmt.setObject(3, currentDate);
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Record inserito con successo nella tabella LAST_GAMES.");
                    } else {
                        System.out.println("Errore durante l'inserimento del record nella tabella LAST_GAMES.");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
