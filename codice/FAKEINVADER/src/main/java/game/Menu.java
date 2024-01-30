package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< Updated upstream
=======
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
>>>>>>> Stashed changes

class Menu extends MainMenu 
{
	Menu() throws IOException
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
			
			File pathmenu  = new File("src/main/java/images/menu.jpg");
			BufferedImage image = ImageIO.read(pathmenu);
			
	        public void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            g.drawImage(image, -45, -100, 768, 768, this);
	        }
	    });
		
		ImageIcon iconStartNewGame = new ImageIcon("src/main/java/images/newGame.png");
		ImageIcon iconStartNewGameHover = new ImageIcon("src/main/java/images/newGamePush.png");
		
		ImageIcon iconScore = new ImageIcon("src/main/java/images/score.png");
		ImageIcon iconScoreHover = new ImageIcon("src/main/java/images/scorePush.png");
		
		ImageIcon iconRules = new ImageIcon("src/main/java/images/rules.png");
		ImageIcon iconRulesHover = new ImageIcon("src/main/java/images/rulesPush.png");
		
		ImageIcon iconExit = new ImageIcon("src/main/java/images/exit.png");
		ImageIcon iconExitHover = new ImageIcon("src/main/java/images/exitPush.png");
		
		//Start New Game
		JButton buttonStart = new JButton();
		buttonStart.setBounds(185, 380, 300, 60);
    	buttonStart.setIcon(resizeIcon(iconStartNewGame, buttonStart.getWidth() , buttonStart.getHeight() ));
    	//buttonStart.setBackground(Color.BLACK);
    	buttonStart.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonStart.setIcon(resizeIcon(iconStartNewGameHover, buttonStart.getWidth(), buttonStart.getHeight() )); // cambia icona quando il mouse entra nell'area del pulsante
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
    	
    	
    	
    	//Score
    	JButton buttonScore = new JButton();
    	buttonScore.setBounds(175, 450, 150, 50);
    	buttonScore.setIcon(resizeIcon(iconScore, buttonScore.getWidth() , buttonScore.getHeight() ));
    	//buttonScore.setBackground(Color.BLACK);
    	
    	buttonScore.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonScore.setIcon(resizeIcon(iconScorePush, buttonScore.getWidth(), buttonScore.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonScore.setIcon(resizeIcon(iconScore, buttonScore.getWidth(), buttonScore.getHeight())); 
    	    }
    	});

    	buttonScore.addActionListener(e -> 
    	{
    		final String DB_REL_FILE = "src/main/java/database/database.db3";
		    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
    		
    		String currentUsername = SessionManager.getCurrentUsername();

    		String sql = "SELECT SCORE FROM LAST_GAMES WHERE USERNAME = ?";
    		try (Connection conn = DriverManager.getConnection(DB_URL);
    		    PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
    		    preparedStatement.setString(1, currentUsername);

    		    try (ResultSet resultSet = preparedStatement.executeQuery()) {
    		        while (resultSet.next()) {
    		            int score = resultSet.getInt("SCORE");
    		            System.out.println("Score: " + score);
    		            
    		            // Puoi elaborare il risultato come preferisci
    		        }
    		        if(!(resultSet.next())) {
   		        	 System.out.println("aggiungi lo score coglione");
   		        	 System.out.println(currentUsername);
   		    }
    		    }
    		} catch (SQLException ex) {
    		    ex.printStackTrace();
    		}


    	});
    	
    	
    	
    	//Rules 
    	JButton buttonRules = new JButton();
    	buttonRules.setBounds(345, 453, 150, 45);
    	buttonRules.setIcon(resizeIcon(iconRules, buttonRules.getWidth(), buttonRules.getHeight()));
    	//buttonRules.setBackground(Color.BLACK);
    	buttonRules.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonRules.setIcon(resizeIcon(iconRulesHover, buttonRules.getWidth(), buttonRules.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }
    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonRules.setIcon(resizeIcon(iconRules, buttonRules.getWidth(), buttonRules.getHeight())); 
    	    }
    	});
<<<<<<< Updated upstream
=======
    	
    	buttonRules.addActionListener(e -> {
		    
        	//CREAZIONE DELLA FRAME RULES
            JFrame rulesframe = new JFrame();
            try 
            {
				rulesframe.setContentPane(new JPanel() 
				{
				    private static final long serialVersionUID = 1L;
				    File pathmenuscore = new File("src/main/java/images/black.png");
				    BufferedImage image = ImageIO.read(pathmenuscore);

				    public void paintComponent(Graphics g) {
				        super.paintComponent(g);
				        g.drawImage(image, -45, -100, 768, 768, this);
				    }
				});
			} catch (IOException exception) 
            {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}

            ImageIcon iconMainMenu = new ImageIcon("src/main/java/images/mainMenu.png");
            ImageIcon iconMainMenuHover = new ImageIcon("src/main/java/images/mainMenuPush.png");

            rulesframe.setLayout(null);
            rulesframe.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
            rulesframe.setLocationRelativeTo(null);
            rulesframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextArea rulesTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(rulesTextArea);
            scrollPane.setBounds(85,50,500,400);
            rulesframe.add(scrollPane);
            
            Color colore = new Color(0, 186, 224);
            Border compoundBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,colore);

            scrollPane.setBorder(compoundBorder);
          
            rulesTextArea.setEditable(false);
            rulesTextArea.setBackground(Color.black);
            rulesTextArea.setForeground(Color.white);
            
            rulesTextArea.setText("qui ci vanno le regole :)");
 
            JButton buttonBack = new JButton();
            buttonBack.setBounds(215, 470, 260, 47);
            buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight()));
            
            buttonBack.addMouseListener(new java.awt.event.MouseAdapter() 
            {
        	    public void mouseEntered(java.awt.event.MouseEvent evt) 
        	    {
        	    	buttonBack.setIcon(resizeIcon(iconMainMenuHover, buttonBack.getWidth(), buttonBack.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
        	    }
        	    public void mouseExited(java.awt.event.MouseEvent evt) 
        	    {
        	    	buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight())); 
        	    }
 
            });
    
            buttonBack.addActionListener(a -> {
                try {
                    new Menu();
                    rulesframe.dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
		            });

            rulesframe.add(buttonBack);
            rulesframe.setVisible(true);
            rulesframe.setTitle("RULES");

            frame.dispose();
		});
    	

    	//SCORE
    	
    	JButton buttonScore = new JButton();
    	buttonScore.setBounds(175, 450, 150, 50);
    	buttonScore.setIcon(resizeIcon(iconScore, buttonScore.getWidth() , buttonScore.getHeight() ));
    	buttonScore.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonScore.setIcon(resizeIcon(iconScoreHover, buttonScore.getWidth(), buttonScore.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonScore.setIcon(resizeIcon(iconScore, buttonScore.getWidth(), buttonScore.getHeight())); 
    	    }
    	});

    	buttonScore.addActionListener(e -> {
		    final String DB_REL_FILE = "src/main/java/database/database.db3";
		    final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
		
		    try (Connection conn = DriverManager.getConnection(DB_URL)) 
		    {
		        if (conn != null) {
		            String username = SessionManager.getCurrentUsername();
		            String selectQuery = "SELECT * FROM LAST_GAMES WHERE USERNAME = ?";
		
		            try (PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) 
		            {
		                preparedStatement.setString(1, username);
		
		                try (ResultSet resultSet = preparedStatement.executeQuery()) 
		                {
		
		                	//CREAZIONE DELLA FRAME SCORE
		                    JFrame scoreframe = new JFrame();
		
		                    scoreframe.setContentPane(new JPanel() 
		                    {
		                        private static final long serialVersionUID = 1L;
		                        File pathmenuscore = new File("src/main/java/images/black.png");
		                        BufferedImage image = ImageIO.read(pathmenuscore);
		
		                        public void paintComponent(Graphics g) {
		                            super.paintComponent(g);
		                            g.drawImage(image, -45, -100, 768, 768, this);
		                        }
		                    });
		
		                    ImageIcon iconMainMenu = new ImageIcon("src/main/java/images/mainMenu.png");
		                    ImageIcon iconMainMenuHover = new ImageIcon("src/main/java/images/mainMenuPush.png");
		
		                    scoreframe.setLayout(null);
		                    scoreframe.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		                    scoreframe.setLocationRelativeTo(null);
		                    scoreframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		                    JTextArea resultTextArea = new JTextArea();
		                    JScrollPane scrollPane = new JScrollPane(resultTextArea);
		                    scrollPane.setBounds(85,50,500,400);
		                    scoreframe.add(scrollPane);
		                    
		                    Color color = new Color(0, 186, 224);
		                    Border compoundBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,color);

		                    scrollPane.setBorder(compoundBorder);
		                  
		                    resultTextArea.setEditable(false);
		                    resultTextArea.setBackground(Color.black);
		                    resultTextArea.setForeground(Color.white);
		                    
		 
		                    JButton buttonBack = new JButton();
		                    buttonBack.setBounds(215, 470, 260, 47);
		                    buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight()));
		                    
		                    buttonBack.addMouseListener(new java.awt.event.MouseAdapter() 
		                    {
	                    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	                    	    	buttonBack.setIcon(resizeIcon(iconMainMenuHover, buttonBack.getWidth(), buttonBack.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
	                    	    }
	
	                    	    public void mouseExited(java.awt.event.MouseEvent evt) {
	                    	    	buttonBack.setIcon(resizeIcon(iconMainMenu, buttonBack.getWidth(), buttonBack.getHeight())); 
	                    	    }
		                 
		                    });
		                    
		                    buttonBack.addActionListener(a -> {
		                        try {
		                            new Menu();
		                            scoreframe.dispose();
		                        } catch (IOException exception2) {
		                        	exception2.printStackTrace();
		                        }
		                    });
		
		                    scoreframe.add(buttonBack);
		                    scoreframe.setVisible(true);
		                    scoreframe.setTitle("RECORDS");
		
		                    while (resultSet.next()) 
		                    {
		                        String retrievedUsername = resultSet.getString("USERNAME");
		                        int retrievedScore = resultSet.getInt("SCORE");
		                        String retrievedDate = resultSet.getString("DAY");
		                        resultTextArea.append("Username: " + retrievedUsername + ", Score: " + retrievedScore
		                                + ", Date: " + retrievedDate + "\n");
		                    }
		                }
		            }
		        }
		
		        frame.dispose();
		
		    } catch (SQLException | IOException ex) {
		        ex.printStackTrace();
		    }
		});
>>>>>>> Stashed changes

    	
    	
    	
    	//Exit
    	JButton buttonExit = new JButton();
    	buttonExit.setBounds(285, 520, 100, 50);
    	buttonExit.setIcon(resizeIcon(iconExit, buttonExit.getWidth(), buttonExit.getHeight()));
    	//buttonExit.setBackground(Color.BLACK);

    	buttonExit.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonExit.setIcon(resizeIcon(iconExitHover, buttonExit.getWidth(), buttonExit.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonExit.setIcon(resizeIcon(iconExit, buttonExit.getWidth(), buttonExit.getHeight())); 
    	    }
    	});

    	buttonExit.addActionListener(e -> {
    	    frame.dispose();
    	});
    	
    	
        frame.add(buttonStart);
        frame.add(buttonScore);
        frame.add(buttonExit);
        frame.add(buttonRules);
        
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
}


