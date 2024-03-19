package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import modelPack.PlayerDAO;

public class LoginMenu extends MainMenu 
{
	LoginMenu() throws IOException
	{
		JFrame frame = new JFrame();
		frame.setContentPane(new JPanel() 
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
		
		ImageIcon iconLogin = new ImageIcon("src/main/java/images/login.png");
		ImageIcon iconLoginPush = new ImageIcon("src/main/java/images/loginPush.png");
		
		ImageIcon iconU = new ImageIcon("src/main/java/images/username.png");
		ImageIcon iconP = new ImageIcon("src/main/java/images/password.png");
		
		ImageIcon iconSingin = new ImageIcon("src/main/java/images/signin.png");
		ImageIcon iconSinginPush = new ImageIcon("src/main/java/images/signinPush.png");
		
		
		//username 
		JLabel usernameL = new JLabel();
		usernameL.setBounds(125, 375, 200, 50);
		usernameL.setIcon(resizeIcon(iconU, usernameL.getWidth(), usernameL.getHeight()));
		
    	JTextField username = new JTextField();
    	username.setBounds(355, 380, 190, 40);	
    	username.setBackground(new Color(255, 241, 202));
    	
    	
    	//password 
    	JLabel passwordL = new JLabel();
    	passwordL.setBounds(125, 445, 200, 50);
    	passwordL.setIcon(resizeIcon(iconP, passwordL.getWidth(), passwordL.getHeight()));
    	
    	JTextField password = new JTextField();
    	password.setBounds(355, 450, 190, 40);
    	password.setBackground(new Color(255, 241, 202));
    	
    	
    	//login
    	JButton buttonLogin = new JButton();
    	buttonLogin.setBounds(355, 520, 100, 50);
    	buttonLogin.setIcon(resizeIcon(iconLogin, buttonLogin.getWidth(), buttonLogin.getHeight()));
    	
    	
    	buttonLogin.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonLogin.setIcon(resizeIcon(iconLoginPush, buttonLogin.getWidth(), buttonLogin.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonLogin.setIcon(resizeIcon(iconLogin, buttonLogin.getWidth(), buttonLogin.getHeight())); 
    	    }
    	});
    	
    	
    	//Registration
    	JButton buttonRegistration = new JButton();
    	buttonRegistration.setBounds(194, 523, 120, 50);
    	buttonRegistration.setIcon(resizeIcon(iconSingin, buttonRegistration.getWidth(), buttonRegistration.getHeight()));
   
    	buttonRegistration.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonRegistration.setIcon(resizeIcon(iconSinginPush, buttonRegistration.getWidth(), buttonRegistration.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonRegistration.setIcon(resizeIcon(iconSingin, buttonRegistration.getWidth(), buttonRegistration.getHeight())); 
    	    }
    	});
    	
    	//Error
    	JTextField error = new JTextField();
    	error.setBounds(179, 580, 310, 40);
    	error.setBackground(Color.BLACK);
    	error.setForeground(Color.RED);
    	error.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	
    	//Frame
    	frame.add(usernameL);
    	frame.add(passwordL);
        frame.add(username);
        frame.add(password);
        frame.add(buttonLogin);
        frame.add(error);
        frame.add(buttonRegistration);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		
		//Listener 
		
		buttonLogin.addActionListener(e -> {
		    try {
		        // Ottieni l'istanza del PlayerDAO utilizzando la connessione al database dal Singleton
		        PlayerDAO playerDAO = new PlayerDAO(CreateDB.getInstance().getConnection());

		        // Verifica delle credenziali dell'utente nel database
		        String usernameInput = username.getText();
		        String passwordInput = password.getText();

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
		            error.setText("Attention: unregistered user!");
		        }
		    } catch (SQLException ex) {
		        // Gestione delle eccezioni
		        ex.printStackTrace();
		    } finally {
		        // Resetta i campi indipendentemente dal risultato della query
		        username.setText("");
		        password.setText("");
		    }
		});


		buttonRegistration.addActionListener(e -> {
		    try (Connection conn =CreateDB.getInstance().getConnection()) {
		        if (conn != null) {
		            DatabaseMetaData meta = conn.getMetaData();
		            System.out.println("The driver name is " + meta.getDriverName());
		        }

		        // Validazione dell'input
		        String inputUsername = username.getText();
		        String inputPassword = password.getText();

		        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
		            System.out.println("Attenzione: Inserire username e password");
		            error.setText("Attention: Enter your username and password");
		        } else {
		            try {
		                // Creazione del PlayerDAO utilizzando la connessione al database
		                PlayerDAO playerDAO = new PlayerDAO(conn);

		                // Verifica se l'utente è già registrato
		                if (playerDAO.playerExists(inputUsername)) {
		                    System.out.println("Utente già registrato");
		                    username.setText("");
		                    password.setText("");
		                    error.setText("Attention: User already registered!");
		                } else {
		                    // Inserisci il nuovo utente
		                    if (playerDAO.insertPlayer(inputUsername, inputPassword)) {
		                        System.out.println("Utente inserito con successo");
		                        frame.dispose();
		                        new Menu();
		                    } else {
		                        System.out.println("Errore durante l'inserimento dell'utente");
		                        error.setText("Error while registering user");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                // Gestione degli errori SQL
		                System.out.println("Errore SQL: " + ex.getMessage());
		                error.setText("Error while accessing database");
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        // Gestione degli errori SQL
		        System.out.println("Errore SQL: " + ex.getMessage());
		        error.setText("Error while accessing database");
		    } catch (Exception ex) {
		        ex.printStackTrace();
		        // Gestione di altri tipi di errori
		        System.out.println("Errore: " + ex.getMessage());
		        error.setText("Unknown error");
		    }
		});

	}
	
}



