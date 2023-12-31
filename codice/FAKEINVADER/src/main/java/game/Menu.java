package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Menu extends MainMenu 
{
	Menu() throws IOException
	{
		JFrame frame = new JFrame();
		ImageIcon icon = new ImageIcon("src/main/java/images/alien.png");
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
		ImageIcon iconStartNewGamePush = new ImageIcon("src/main/java/images/newGamePush.png");
		
		ImageIcon iconScore = new ImageIcon("src/main/java/images/score.png");
		ImageIcon iconScorePush = new ImageIcon("src/main/java/images/scorePush.png");
		
		ImageIcon iconRules = new ImageIcon("src/main/java/images/rules.png");
		ImageIcon iconRulesPush = new ImageIcon("src/main/java/images/rulesPush.png");
		
		ImageIcon iconExit = new ImageIcon("src/main/java/images/exit.png");
		ImageIcon iconExitPush = new ImageIcon("src/main/java/images/exitPush.png");
		
		//Start New Game
		JButton buttonStart = new JButton();
		buttonStart.setBounds(185, 380, 300, 60);
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
    		// fare select che visualizzi 
    	});
    	
    	
    	
    	//Rules 
    	JButton buttonRules = new JButton();
    	buttonRules.setBounds(345, 453, 150, 45);
    	buttonRules.setIcon(resizeIcon(iconRules, buttonRules.getWidth(), buttonRules.getHeight()));
    	//buttonRules.setBackground(Color.BLACK);
    	buttonRules.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	buttonRules.setIcon(resizeIcon(iconRulesPush, buttonRules.getWidth(), buttonRules.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }
    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	buttonRules.setIcon(resizeIcon(iconRules, buttonRules.getWidth(), buttonRules.getHeight())); 
    	    }
    	});

    	
    	
    	
    	//Exit
    	JButton buttonExit = new JButton();
    	buttonExit.setBounds(285, 520, 100, 50);
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


