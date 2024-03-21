package FAKEINVADERS_ModelPack;

import java.sql.SQLException;

import FAKEINVADERS_Database.PlayerDAO;

public class LoginModel {
    private final PlayerDAO playerDAO;

    public LoginModel() {
        // Inizializza il DAO solo una volta per evitare la duplicazione delle connessioni al FAKEINVADERS_Database
		playerDAO = new PlayerDAO();
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
