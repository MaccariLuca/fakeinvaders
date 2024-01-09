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
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    	username.setBounds(335, 380, 200, 40);	
    	username.setBackground(new Color(255, 241, 202));
    	
    	
    	//password 
    	JLabel passwordL = new JLabel();
    	passwordL.setBounds(125, 445, 200, 50);
    	passwordL.setIcon(resizeIcon(iconP, passwordL.getWidth(), passwordL.getHeight()));
    	
    	JTextField password = new JTextField();
    	password.setBounds(335, 450, 200, 40);
    	password.setBackground(new Color(255, 241, 202));
    	
    	
    	//login
    	JButton buttonLogin = new JButton();
    	buttonLogin.setBounds(335, 520, 100, 50);
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
    	buttonRegistration.setBounds(200, 523, 120, 50);
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
    	error.setBounds(230, 580, 230, 40);
    	error.setBackground(Color.BLACK);
    	error.setForeground(Color.RED);
    	
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
		
		
		buttonLogin.addActionListener(e -> 
		{
		    final String DB_REL_FILE = "src/main/java/database/database.db3";
		    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	
		    Connection conn = null;
		    try {
		        conn = DriverManager.getConnection(DB_URL);
	
		        if (conn != null) {
		            DatabaseMetaData meta = conn.getMetaData();
		            System.out.println("The driver name is " + meta.getDriverName());
		        }
	
		        // Controllo che il file esista a questo punto
		        System.out.println("Il file esiste? " + new File(DB_REL_FILE).exists());
	
		        String sql = "SELECT * FROM PLAYERS WHERE username = ? AND password = ?";
		        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
		            preparedStatement.setString(1, username.getText());
		            preparedStatement.setString(2, password.getText());
	
		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                if (resultSet.next()) 
		                {
		                	SessionManager.setCurrentUsername(username.getText());

		                    frame.dispose();
		                    try {
		                        new Menu();
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
		                } else {
		                    System.out.println("Utente non registrato");
		                    error.setText(" Attenzione : Utente non registrato!");
		                }
		            }
		        }
	
		        System.out.println("Query eseguita con successo");

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
		        username.setText("");
		        password.setText("");
		    }
		});


		buttonRegistration.addActionListener(e -> 
		{
		    final String DB_REL_FILE = "src/main/java/database/database.db3";
		    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

		    try (Connection conn = DriverManager.getConnection(DB_URL)) {
		        if (conn != null) {
		            DatabaseMetaData meta = conn.getMetaData();
		            System.out.println("The driver name is " + meta.getDriverName());
		        }

		        // Controllo che il file esista a questo punto
		        System.out.println("Il file esiste? " + new File(DB_REL_FILE).exists());

		        // Query di verifica se l'utente è già registrato
		        String checkSql = "SELECT * FROM PLAYERS WHERE username = ?";
		        try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
		            checkStatement.setString(1, username.getText());
		            try (ResultSet resultSet = checkStatement.executeQuery()) {
		                if (resultSet.next()) {
		                    System.out.println("Utente già registrato");
		                    username.setText("");
		                    password.setText("");
		                    error.setText(" Attenzione : Utente già registrato!");
		                } else {
		                    // Inserisci il nuovo utente
		                    String insertSql = "INSERT INTO PLAYERS (username, password) VALUES (?, ?)";
		                    try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
		                        insertStatement.setString(1, username.getText());
		                        insertStatement.setString(2, password.getText());
		                        insertStatement.executeUpdate();

		                        System.out.println("Utente inserito con successo");

		                        frame.dispose();
		                        try {
		                            new Menu();
		                        } catch (IOException e1) {
		                            e1.printStackTrace();
		                        }
		                    }
		                }
		            }
		        }

		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});

	}
	
	
}



