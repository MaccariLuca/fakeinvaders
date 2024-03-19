package modelPack;

import database.CreateDB;
import database.PlayerDAO;

import java.sql.SQLException;

public class LoginModel {
    private final PlayerDAO playerDAO;

    public LoginModel() {
        try {
            // Inizializza il DAO solo una volta per evitare la duplicazione delle connessioni al database
            playerDAO = new PlayerDAO(CreateDB.getInstance().getConnection());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Errore durante l'inizializzazione del modello di login", ex);
        }
    }

    public boolean checkCredentials(String username, String password) {
        try {
            return playerDAO.checkCredentials(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean registerPlayer(String username, String password) {
        try {
            if (playerDAO.playerExists(username)) {
                return false; // Utente già registrato
            } else {
                return playerDAO.insertPlayer(username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
