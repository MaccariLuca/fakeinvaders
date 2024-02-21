package controllerPack;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ViewPack.LoginMenu;
import ViewPack.Menu;
import game.SessionManager;

public class LoginMenuController {
	
	private LoginMenu loginMenuView;

	public LoginMenuController(LoginMenu l)
	{
		this.loginMenuView = l;
	}
	
	public void action()
	{
		
	loginMenuView.getButtonLogin().addActionListener(e -> 
	{
	    final String DB_REL_FILE = "src/main/java/database/database.db3";
	    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

	    Connection conn = null;
	    try 
	    {
	        conn = DriverManager.getConnection(DB_URL);

	        if (conn != null) 
	        {
	            DatabaseMetaData meta = conn.getMetaData();
	            System.out.println("The driver name is " + meta.getDriverName());
	        }

	        // Controllo che il file esista a questo punto
	        System.out.println("Il file esiste? " + new File(DB_REL_FILE).exists());

	        String inputUsername = loginMenuView.getUsername().getText();
	        String inputPassword = loginMenuView.getPassword().getText();

	        if (inputUsername.isEmpty() || inputPassword.isEmpty()) 
	        {
	            loginMenuView.getError().setText("Attention: Enter your username and password");
	        }else
	        {
		        String sql = "SELECT * FROM PLAYERS WHERE username = ? AND password = ?";
		        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) 
		        {
		            preparedStatement.setString(1, loginMenuView.getUsername().getText());
		            preparedStatement.setString(2, loginMenuView.getPassword().getText());
	
		            try (ResultSet resultSet = preparedStatement.executeQuery()) 
		            {
		                if (resultSet.next()) 
		                {
		                	SessionManager.setCurrentUsername(loginMenuView.getUsername().getText());

		                	loginMenuView.getFrame().dispose();
		                    try {
		                        new Menu();
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
		                } else {
		                    System.out.println("Utente non registrato");
		                    loginMenuView.getError().setText(" Attention: unregistered user!");
		                }
		            }
		        }
		        System.out.println("Query eseguita con successo");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Chiudi la connessione qui per garantire che venga eseguita anche in caso di eccezione
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }

	        // Resetta i campi qui indipendentemente dal risultato della query
	        loginMenuView.getUsername().setText("");
	        loginMenuView.getPassword().setText("");
	    }
	});
	
	
loginMenuView.getButtonRegistration().addActionListener(e -> {
	    final String DB_REL_FILE = "src/main/java/database/database.db3";
	    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

	    try (Connection conn = DriverManager.getConnection(DB_URL)) {
	        if (conn != null) {
	            DatabaseMetaData meta = conn.getMetaData();
	            System.out.println("The driver name is " + meta.getDriverName());
	        }

	        // Controllo che il file esista a questo punto
	        System.out.println("Il file esiste? " + new File(DB_REL_FILE).exists());

	        // Validazione dell'input
	        String inputUsername = loginMenuView.getUsername().getText();
	        String inputPassword = loginMenuView.getPassword().getText();

	        if (inputUsername.isEmpty() || inputPassword.isEmpty()) 
	        {
	            System.out.println("Attenzione: Inserire username e password");
	            loginMenuView.getError().setText("Attention: Enter your username and password");
	        } else {
	            // Query di verifica se l'utente è già registrato
	            String checkSql = "SELECT * FROM PLAYERS WHERE username = ?";
	            try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
	                checkStatement.setString(1, inputUsername);

	                try (ResultSet resultSet = checkStatement.executeQuery()) {
	                    if (resultSet.next()) 
	                    {
	                    	SessionManager.setCurrentUsername(loginMenuView.getUsername().getText());
	                        System.out.println("Utente già registrato");
	                        loginMenuView.getUsername().setText("");
	                        loginMenuView.getPassword().setText("");
	                        loginMenuView.getError().setText("Attention: User already registered!");
	                    } else {
	                        // Inserisci il nuovo utente
	                        String insertSql = "INSERT INTO PLAYERS (username, password) VALUES (?, ?)";
	                        try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
	                            insertStatement.setString(1, inputUsername);
	                            insertStatement.setString(2, inputPassword);
	                            insertStatement.executeUpdate();

	                            System.out.println("Utente inserito con successo");

	                            loginMenuView.getFrame().dispose();
	                            try {
	                                new Menu();
	                            } catch (IOException e1) {
	                                e1.printStackTrace();
	                            }
	                        }
	                    }
	                }
	            }
	        }

	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        // Gestione degli errori SQL
	        System.out.println("Errore SQL: " + ex.getMessage());
	        loginMenuView.getError().setText("Error while accessing database");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        // Gestione di altri tipi di errori
	        System.out.println("Errore: " + ex.getMessage());
	        loginMenuView.getError().setText("Unknown error");
	    }
	});
}
}
	

