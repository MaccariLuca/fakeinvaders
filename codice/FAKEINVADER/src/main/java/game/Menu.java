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
	            g.drawImage(image, -70, -100, 768, 768, this);
	        }
	    });
		ImageIcon icon = new ImageIcon("src/main/java/images/startnewgame.png");
		ImageIcon icon1 = new ImageIcon("src/main/java/images/credits1.png");
		ImageIcon icon2 = new ImageIcon("src/main/java/images/exit.png");
		
		//Start New Game
		JButton buttonStart = new JButton();
		buttonStart.setBounds(145, 380, 350, 60);
    	int offset = buttonStart.getInsets().left;
    	buttonStart.setIcon(resizeIcon(icon, buttonStart.getWidth() - offset, buttonStart.getHeight() - offset));
    	buttonStart.setBackground(Color.BLACK);
    	buttonStart.addActionListener(e -> 
    	{
    	    new FakeInvaders();
   
    	});
    	
    	//Credits
    	JButton buttonLogin = new JButton();
    	buttonLogin.setBounds(215, 450, 200, 50);
    	int offset1 = buttonLogin.getInsets().left;
    	buttonLogin.setIcon(resizeIcon(icon1, buttonLogin.getWidth() - offset1, buttonLogin.getHeight() - offset1));
    	buttonLogin.setBackground(Color.BLACK);
    	buttonLogin.addActionListener(e -> 
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
    	JButton b2 = new JButton();
    	b2.setBounds(265, 510, 100, 50);
    	int offset2 = b2.getInsets().left;
    	b2.setIcon(resizeIcon(icon2, b2.getWidth() - offset2, b2.getHeight() - offset2));
    	b2.setBackground(Color.BLACK);
    	b2.addActionListener(e -> 
    	{
    		frame.dispose();
    	});
    	
        frame.add(buttonStart);
        frame.add(buttonLogin);
        frame.add(b2);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
}


