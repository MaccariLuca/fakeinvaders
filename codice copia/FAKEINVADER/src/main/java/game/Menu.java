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
		
		ImageIcon iconStartNewGame = new ImageIcon("src/main/java/images/NewGame.png");
		ImageIcon iconScore = new ImageIcon("src/main/java/images/score.png");
		ImageIcon iconExit = new ImageIcon("src/main/java/images/exit.png");
		
		//Start New Game
		
		JButton buttonStart = new JButton();
		buttonStart.setBounds(185, 380, 300, 60);
    	int offset = buttonStart.getInsets().left;
    	buttonStart.setIcon(resizeIcon(iconStartNewGame, buttonStart.getWidth() - offset, buttonStart.getHeight() - offset));
    	buttonStart.setBackground(Color.BLACK);
    	buttonStart.addActionListener(e -> 
    	{
    	    new FakeInvaders();
    	    frame.dispose();
    	});
    	
    	//Score
    	
    	//TODO
    	JButton buttonScore = new JButton();
    	buttonScore.setBounds(260, 450, 150, 55);
    	int offset1 = buttonScore.getInsets().left;
    	buttonScore.setIcon(resizeIcon(iconScore, buttonScore.getWidth() - offset1, buttonScore.getHeight() - offset1));
    	buttonScore.setBackground(Color.BLACK);
    	buttonScore.addActionListener(e -> 
    	{
    		try {
				new Login();
				frame.dispose();
			} catch (IOException e1) {
				System.out.println("Nothing");
				e1.printStackTrace();
			}
    	});
    	
    	//Exit
    	JButton buttonExit = new JButton();
    	buttonExit.setBounds(285, 520, 100, 50);
    	int offset2 = buttonExit.getInsets().left;
    	buttonExit.setIcon(resizeIcon(iconExit, buttonExit.getWidth() - offset2, buttonExit.getHeight() - offset2));
    	buttonExit.setBackground(Color.BLACK);
    	buttonExit.addActionListener(e -> 
    	{
    		frame.dispose();
    	});
    	
        frame.add(buttonStart);
        frame.add(buttonScore);
        frame.add(buttonExit);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
}


