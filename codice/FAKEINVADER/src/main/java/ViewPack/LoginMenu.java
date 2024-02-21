package ViewPack;

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
import javax.swing.SwingConstants;

import game.SessionManager;
import modelPack.Commons;

public class LoginMenu extends MainMenu 
{
	

	private JLabel usernameL = new JLabel();
	private JTextField username = new JTextField();
	private JLabel passwordL = new JLabel();
	private JTextField password = new JTextField();
	private JButton buttonRegistration = new JButton();
	private JButton buttonLogin = new JButton();
	private JFrame frame = new JFrame();
	private JTextField error = new JTextField();
	
	LoginMenu() throws IOException
	{
		
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
		usernameL.setBounds(125, 375, 200, 50);
		usernameL.setIcon(resizeIcon(iconU, usernameL.getWidth(), usernameL.getHeight()));
		
    	username.setBounds(355, 380, 190, 40);	
    	username.setBackground(new Color(255, 241, 202));
    	
    	
    	//password 
    	passwordL.setBounds(125, 445, 200, 50);
    	passwordL.setIcon(resizeIcon(iconP, passwordL.getWidth(), passwordL.getHeight()));
    	
    	
    	password.setBounds(355, 450, 190, 40);
    	password.setBackground(new Color(255, 241, 202));
    	
    	//Error
    	error.setBounds(179, 580, 310, 40);
    	error.setBackground(Color.BLACK);
    	error.setForeground(Color.RED);
    	error.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	
    	//login
    	getButtonLogin().setBounds(355, 520, 100, 50);
    	getButtonLogin().setIcon(resizeIcon(iconLogin, getButtonLogin().getWidth(), getButtonLogin().getHeight()));
    	
    	getButtonLogin().addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	getButtonLogin().setIcon(resizeIcon(iconLoginPush, getButtonLogin().getWidth(), getButtonLogin().getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	getButtonLogin().setIcon(resizeIcon(iconLogin, getButtonLogin().getWidth(), getButtonLogin().getHeight())); 
    	    }
    	});
    	
    	//Registration
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
    	
    	
    	
    	
    	
    	frame.add(usernameL);
    	frame.add(passwordL);
        frame.add(username);
        frame.add(password);
        frame.add(getButtonLogin());
        frame.add(error);
        frame.add(buttonRegistration);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		


	public JButton getButtonLogin() {
		return buttonLogin;
	}

	public void setButtonLogin(JButton buttonLogin) {
		this.buttonLogin = buttonLogin;
	}
	public JTextField getUsername() {
		return username;
	}
	public void setUsername(JTextField username) {
		this.username = username;
	}
	public JTextField getPassword() {
		return password;
	}
	public void setPassword(JTextField password) {
		this.password = password;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public JTextField getError() {
		return error;
	}
	public void setError(JTextField error) {
		this.error = error;
	}
	public JButton getButtonRegistration() {
		return buttonRegistration;
	}
	public void setButtonRegistration(JButton buttonRegistration) {
		this.buttonRegistration = buttonRegistration;
	}
}



