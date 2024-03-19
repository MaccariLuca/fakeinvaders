<<<<<<< HEAD
<<<<<<< Updated upstream:codice/FAKEINVADER/src/main/java/game/GameOverMenu.java
package game;
=======
 package modelPack;
>>>>>>> Stashed changes:codice/FAKEINVADER/src/main/java/modelPack/GameOverMenu.java

import java.awt.Color;
=======
 package modelPack;

import java.awt.Color;

import java.time.LocalDateTime;
>>>>>>> 87262c081c5e6465d423307c817733412d7861fd
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
<<<<<<< HEAD
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

=======
import java.sql.PreparedStatement;
import java.sql.SQLException;
>>>>>>> 87262c081c5e6465d423307c817733412d7861fd
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
<<<<<<< HEAD

class GameOverMenu extends MainMenu 
{
	GameOverMenu(int lastScore) throws IOException
=======
import ViewPack.FakeInvaders;
import database.CreateDB;

public class GameOverMenu extends MainMenu 
{
	public GameOverMenu(int lastScore) throws IOException
>>>>>>> 87262c081c5e6465d423307c817733412d7861fd
	{
		JFrame frame = new JFrame();

		ImageIcon icon = new ImageIcon("src/main/java/images/ship.png");
		frame.setIconImage(icon.getImage());
		frame.setContentPane(new JPanel() 
		{			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			File pathmenu  = new File("src/main/java/images/gameOver.jpg");
			BufferedImage image = ImageIO.read(pathmenu);
			
	        public void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            g.drawImage(image, -45, -100, 768, 768, this);
	            //System.out.println(lastScore);
	        }
	       
	    });
		
		//IMAGES
		
		ImageIcon iconStartNewGame = new ImageIcon("src/main/java/images/newGame.png");
		ImageIcon iconStartNewGamePush = new ImageIcon("src/main/java/images/newGamePush.png");
		
		ImageIcon iconExit = new ImageIcon("src/main/java/images/exit.png");
		ImageIcon iconExitPush = new ImageIcon("src/main/java/images/exitPush.png");
		
		ImageIcon iconMainMenu = new ImageIcon("src/main/java/images/mainMenu.png");
		ImageIcon iconMainMenuPush = new ImageIcon("src/main/java/images/mainMenuPush.png");
		
		
		//SCORE LABEL
		
    	JLabel scoreLabel = new JLabel();
    	scoreLabel.setBounds(272, 340, 135, 40);
    	scoreLabel.setText("YOUR SCORE : " + String.valueOf(lastScore));
    	scoreLabel.setForeground(Color.RED);
    	Font customFont = new Font(scoreLabel.getFont().getName(), Font.PLAIN, 16);
    	scoreLabel.setFont(customFont);
 
		System.out.println(String.valueOf(lastScore));
    	
		
		//START NEW GAME BUTTON
		
    	JButton buttonStart = new JButton();
		buttonStart.setBounds(195, 400, 300, 60);
    	buttonStart.setIcon(resizeIcon(iconStartNewGame, buttonStart.getWidth() , buttonStart.getHeight() ));
    	//buttonStart.setBackground(Color.BLACK);
    	buttonStart.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonStart.setIcon(resizeIcon(iconStartNewGamePush, buttonStart.getWidth(), buttonStart.getHeight() )); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonStart.setIcon(resizeIcon(iconStartNewGame, buttonStart.getWidth(), buttonStart.getHeight() )); 
    	    }
    	});
    	
    	buttonStart.addActionListener(e -> 
    	{
    	    new FakeInvaders();
    	    frame.dispose();
    	});
    	
    	
    	
    	//BACK BUTTON
		
		JButton buttonBack = new JButton();
		buttonBack.setBounds(215, 470, 260, 47);
		buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight()));
		buttonBack.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonBack.setIcon(resizeIcon(iconMainMenuPush, buttonBack.getWidth(), buttonBack.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight())); 
    	    }
    	});
		buttonBack.addActionListener(e -> {
    		try { new Menu() ; frame.dispose(); } catch (IOException e1) { e1.printStackTrace(); }
		});
    	
		
    	
    	//EXIT BUTTON
		
    	JButton buttonExit = new JButton();
    	buttonExit.setBounds(285, 530, 100, 50);
    	buttonExit.setIcon(resizeIcon(iconExit, buttonExit.getWidth(), buttonExit.getHeight()));
    	//buttonExit.setBackground(Color.BLACK);

    	buttonExit.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonExit.setIcon(resizeIcon(iconExitPush, buttonExit.getWidth(), buttonExit.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonExit.setIcon(resizeIcon(iconExit, buttonExit.getWidth(), buttonExit.getHeight())); 
    	    }
    	});
    
		buttonExit.addActionListener(e -> {
		    frame.dispose();
		});
<<<<<<< HEAD
		
		
		
		
    	//LIST
=======
	
		
    	//frame
>>>>>>> 87262c081c5e6465d423307c817733412d7861fd
        frame.add(buttonStart);
        frame.add(buttonExit);
        frame.add(scoreLabel);
        frame.add(buttonBack);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
<<<<<<< HEAD
		
		
		//QUERY PART
		//TODO
		
        final String DB_REL_FILE = "src/main/java/database/database.db3";
		final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
		try 
		{			
		 Connection conn = DriverManager.getConnection(DB_URL);
		 if (conn != null) 
		 {
		   DatabaseMetaData meta = conn.getMetaData();
		   System.out.println("The driver name is " + meta.getDriverName());
		   System.out.println("A new database has been created.");
		 }
		 
		 Statement stmt = conn.createStatement();
		 
		stmt.close();
  		conn.close();
  		System.out.println("query eseguita con successo");
  		
 		} catch (SQLException ex) {
 		  System.out.println(ex.getMessage());
 		}
		
	}
	
}


=======
		//parte query
		try (Connection conn = CreateDB.getInstance().getConnection()) {
		    if (conn != null) {
		        //username
		        String username = SessionManager.getCurrentUsername();
		                
		        //data
		        LocalDateTime currentDate = LocalDateTime.now();
		                
		        // Creazione della query con PreparedStatement
		        String query = "INSERT INTO LAST_GAMES (USERNAME, SCORE, DAY) VALUES (?, ?, ?)";
		                
		        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
		            pstmt.setString(1, username);
		            pstmt.setInt(2, lastScore);
		            pstmt.setObject(3, currentDate);
		                    
		            // Esecuzione della query
		            int rowsAffected = pstmt.executeUpdate(); //restituisce il numero di righe che sono state inserite nel db 
		                    
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
>>>>>>> 87262c081c5e6465d423307c817733412d7861fd