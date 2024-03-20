package ViewPack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelPack.Commons;

public class MenuView 
{
    private JFrame frame;
    private final JButton startButton;
    private final JButton rulesButton;
    private final JButton scoreButton;
    private final JButton exitButton;

    public MenuView() throws IOException 
    {
        frame = new JFrame();
        
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

        
		//START
		startButton = new JButton();
		startButton.setBounds(185, 380, 300, 60);
    	startButton.setIcon(resizeIcon(iconStartNewGame, startButton.getWidth() , startButton.getHeight() ));
    	//startButton.setBackground(Color.BLACK);
    	startButton.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	startButton.setIcon(resizeIcon(iconStartNewGameHover, startButton.getWidth(), startButton.getHeight() )); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	startButton.setIcon(resizeIcon(iconStartNewGame, startButton.getWidth(), startButton.getHeight() )); 
    	    }
    	});
    	
    	
    	//RULES
    	rulesButton = new JButton();
    	rulesButton.setBounds(345, 453, 150, 45);
    	rulesButton.setIcon(resizeIcon(iconRules, rulesButton.getWidth(), rulesButton.getHeight()));
    	rulesButton.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	rulesButton.setIcon(resizeIcon(iconRulesHover, rulesButton.getWidth(), rulesButton.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }
    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	rulesButton.setIcon(resizeIcon(iconRules, rulesButton.getWidth(), rulesButton.getHeight())); 
    	    }
    	});
    	
    	scoreButton = new JButton();
    	scoreButton.setBounds(175, 450, 150, 50);
    	scoreButton.setIcon(resizeIcon(iconScore, scoreButton.getWidth() , scoreButton.getHeight() ));
    	scoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	scoreButton.setIcon(resizeIcon(iconScoreHover, scoreButton.getWidth(), scoreButton.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	scoreButton.setIcon(resizeIcon(iconScore, scoreButton.getWidth(), scoreButton.getHeight())); 
    	    }
    	});
    	
    	exitButton= new JButton();
    	exitButton.setBounds(285, 520, 100, 50);
    	exitButton.setIcon(resizeIcon(iconExit, exitButton.getWidth(), exitButton.getHeight()));
    	exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	exitButton.setIcon(resizeIcon(iconExitHover, exitButton.getWidth(), exitButton.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	exitButton.setIcon(resizeIcon(iconExit, exitButton.getWidth(), exitButton.getHeight())); 
    	    }
    	});
    	
    	
        frame.setTitle("FAKE INVADERS");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(startButton);
        frame.add(scoreButton);
        frame.add(exitButton);
        frame.add(rulesButton); 
        
    }

    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    public void dispose() 
    {
        frame.dispose();
    }
   
    public void addStartButtonListener(ActionListener listener) {
    	startButton.addActionListener(listener);
    }

    public void addRulesButtonListener(ActionListener listener) {
        rulesButton.addActionListener(listener);
    }

    public void addScoreButtonListener(ActionListener listener) {
        scoreButton.addActionListener(listener);
    }
    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}