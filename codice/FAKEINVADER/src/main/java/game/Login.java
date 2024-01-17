package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Login extends MainMenu
{
	Login() throws IOException
	{
		JFrame f1 = new JFrame();
		ImageIcon icon2 = new ImageIcon("src/main/java/images/exit.png");
		f1.setContentPane(new JPanel() 
		{

	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			File pathCredits = new File("src/main/java/images/credits.jpg");
			BufferedImage image = ImageIO.read(pathCredits);
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
 		f1.setTitle("FAKE INVADERS"); 
 		f1.setLayout(null);
 		f1.setVisible(true);
 		f1.setResizable(false);
 		f1.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
 		f1.setLocationRelativeTo(null);
 		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

