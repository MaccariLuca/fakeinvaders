package game.pack;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MainMenu
{

	public static void main(String[] args) throws Exception
	{
		new Menu();
		
	}
	
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) 
	{
		Image img = icon.getImage();  
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
		return new ImageIcon(resizedImage);
	}

}

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
			
			BufferedImage image = ImageIO.read(getClass().getResource("images\\menu.jpg"));
	        public void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            g.drawImage(image, -70, -100, 768, 768, this);
	        }
	    });
		ImageIcon icon = new ImageIcon("src\\game\\pack\\images\\startnewgame.png");
		ImageIcon icon1 = new ImageIcon("src\\game\\pack\\images\\credits1.png");
		ImageIcon icon2 = new ImageIcon("src\\game\\pack\\images\\exit.png");
		
		//Start New Game
		JButton buttonStart = new JButton();
		buttonStart.setBounds(145, 380, 350, 60);
    	int offset = buttonStart.getInsets().left;
    	buttonStart.setIcon(resizeIcon(icon, buttonStart.getWidth() - offset, buttonStart.getHeight() - offset));
    	buttonStart.setBackground(Color.BLACK);
    	buttonStart.addActionListener(e -> 
    	{
    	    var s = new SpaceInvaders();
    	    //s.dispose();
   
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
		frame.setTitle("SPACE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}
	
}

class Login extends MainMenu
{
	Login() throws IOException
	{
		JFrame f1 = new JFrame();
		ImageIcon icon2 = new ImageIcon("src\\game\\pack\\images\\exit.png");
		f1.setContentPane(new JPanel() 
		{

	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			BufferedImage image = ImageIO.read(getClass().getResource("images\\credits.jpg"));
	        public void paintComponent(Graphics g) 
	        {
	            super.paintComponent(g);
	            g.drawImage(image, 1, 70, 100, 500, this);
	        }
	    });
		
		//Exit
		JButton b3 = new JButton();
		b3.setBounds(265, 570, 100, 50);
    	int offset2 = b3.getInsets().left;
    	b3.setIcon(resizeIcon(icon2, b3.getWidth() - offset2, b3.getHeight() - offset2));
    	b3.setBackground(Color.BLACK);
    	b3.addActionListener(e -> 
    	{
    		try {
				new Menu();
			} catch (IOException e1) 
    		{
				System.out.println("Nothing");
				e1.printStackTrace();
			}
    		f1.dispose();
    	});
		
		//CREDITS
		
    	JTextField login = new JTextField(10);
    	JPasswordField passwordField = new JPasswordField(8);
    	JOptionPane.showMessageDialog(null, login, "Username",
                JOptionPane.INFORMATION_MESSAGE);
    	JOptionPane.showMessageDialog(null, passwordField, "Password",
                 JOptionPane.QUESTION_MESSAGE);
		char[] password = null;
		password = passwordField.getPassword();
		
		
		JLabel l = new JLabel("LOGIN");
 		l.setFont(new Font("Century Gothic", Font.BOLD, 50));
 		l.setForeground(Color.WHITE);
 		l.setBounds(210, -160, 550, 400);
 		JLabel l1 = new JLabel("USERNAME : " + login.getText());
 		l1.setFont(new Font("Century Gothic", Font.BOLD, 25));
 		l1.setForeground(Color.YELLOW);
 		l1.setBounds(115, 80, 550, 100);
 		JLabel l2 = new JLabel("PASSWORD : " + String.valueOf(password));
 		l2.setFont(new Font("Century Gothic", Font.BOLD, 25));
 		l2.setForeground(Color.ORANGE);
 		l2.setBounds(115, 170, 550, 100);
 		
 		f1.add(b3);
 		f1.add(l);
 		f1.add(l1);
 		f1.add(l2);
 		f1.getContentPane().setBackground(Color.BLACK);
 		f1.setTitle("SPACE INVADERS"); 
 		f1.setLayout(null);
 		f1.setVisible(true);
 		f1.setResizable(false);
 		f1.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
 		f1.setLocationRelativeTo(null);
 		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

