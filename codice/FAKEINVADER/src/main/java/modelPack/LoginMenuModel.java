package modelPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.CreateDB;

public class LoginMenuModel extends MainMenu 
{
	private JFrame frame;

	private JLabel usernameL;
	private JTextField username;
	private JLabel passwordL;
	private JTextField password;
	private JButton buttonLogin;
	private JButton buttonRegistration;
	private JTextField error;
	LoginMenuModel() throws IOException
	{
		this.setFrame(new JFrame());
		getFrame().setContentPane(new JPanel() 
		{			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			File pathmenu  = new File("src/main/java/images/menu.jpg");
			BufferedImage image = ImageIO.read(pathmenu);
			
	        public void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            g.drawImage(image, -45, -100, 768, 768, this);
	        }
	    });
			
		//Listener 
		
		getButtonLogin().addActionListener(e -> {
		    try {
		        // Ottieni l'istanza del PlayerDAO utilizzando la connessione al database dal Singleton
		        PlayerDAO playerDAO = new PlayerDAO(CreateDB.getInstance().getConnection());

		        // Verifica delle credenziali dell'utente nel database
		        String usernameInput = username.getText();
		        String passwordInput = getPassword().getText();

		        if (playerDAO.checkCredentials(usernameInput, passwordInput)) {
		            // Se le credenziali sono corrette, imposta l'utente corrente e apri il menu
		            SessionManager.setCurrentUsername(usernameInput);
		            frame.dispose();
		            try {
		                new Menu();
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            // Se le credenziali non sono corrette, mostra un messaggio di errore
		            System.out.println("Utente non registrato");
		            getError().setText("Attention: unregistered user!");
		        }
		    } catch (SQLException ex) {
		        // Gestione delle eccezioni
		        ex.printStackTrace();
		    } finally {
		        // Resetta i campi indipendentemente dal risultato della query
		        username.setText("");
		        getPassword().setText("");
		    }
		});


		getButtonRegistration().addActionListener(e -> {
		    try (Connection conn =CreateDB.getInstance().getConnection()) {
		        if (conn != null) {
		            DatabaseMetaData meta = conn.getMetaData();
		            System.out.println("The driver name is " + meta.getDriverName());
		        }

		        // Validazione dell'input
		        String inputUsername = username.getText();
		        String inputPassword = getPassword().getText();

		        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
		            System.out.println("Attenzione: Inserire username e password");
		            getError().setText("Attention: Enter your username and password");
		        } else {
		            try {
		                // Creazione del PlayerDAO utilizzando la connessione al database
		                PlayerDAO playerDAO = new PlayerDAO(conn);

		                // Verifica se l'utente è già registrato
		                if (playerDAO.playerExists(inputUsername)) {
		                    System.out.println("Utente già registrato");
		                    username.setText("");
		                    getPassword().setText("");
		                    getError().setText("Attention: User already registered!");
		                } else {
		                    // Inserisci il nuovo utente
		                    if (playerDAO.insertPlayer(inputUsername, inputPassword)) {
		                        System.out.println("Utente inserito con successo");
		                        frame.dispose();
		                        new Menu();
		                    } else {
		                        System.out.println("Errore durante l'inserimento dell'utente");
		                        getError().setText("Error while registering user");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                // Gestione degli errori SQL
		                System.out.println("Errore SQL: " + ex.getMessage());
		                getError().setText("Error while accessing database");
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        // Gestione degli errori SQL
		        System.out.println("Errore SQL: " + ex.getMessage());
		        getError().setText("Error while accessing database");
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        // Gestione di altri tipi di errori
		        System.out.println("Errore: " + ex.getMessage());
		        getError().setText("Unknown error");
		    }
		});

	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JLabel getUsernameL() {
		return usernameL;
	}
	public void setUsernameL(JLabel usernameL) {
		this.usernameL = usernameL;
	}
	public JTextField getUsername() {
		return username;
	}
	public void setUsername(JTextField username) {
		this.username = username;
	}
	public JLabel getPasswordL() {
		return passwordL;
	}
	public void setPasswordL(JLabel passwordL) {
		this.passwordL = passwordL;
	}
	public JTextField getPassword() {
		return password;
	}
	public void setPassword(JTextField password) {
		this.password = password;
	}
	public JButton getButtonLogin() {
		return buttonLogin;
	}
	public void setButtonLogin(JButton buttonLogin) {
		this.buttonLogin = buttonLogin;
	}
	public JButton getButtonRegistration() {
		return buttonRegistration;
	}
	public void setButtonRegistration(JButton buttonRegistration) {
		this.buttonRegistration = buttonRegistration;
	}
	public JTextField getError() {
		return error;
	}
	public void setError(JTextField error) {
		this.error = error;
	}
	
	
}



