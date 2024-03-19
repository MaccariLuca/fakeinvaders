package ViewPack;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import modelPack.Commons;
import modelPack.LoginMenuModel;
import modelPack.MainMenu;

public class LoginMenuView extends MainMenu{
	
	private LoginMenuModel model;
	public LoginMenuView(LoginMenuModel lmm)
	{
		this.model = lmm;
	}
	public void paintButtons()
	{
		ImageIcon iconLogin = new ImageIcon("src/main/java/images/login.png");
		ImageIcon iconLoginPush = new ImageIcon("src/main/java/images/loginPush.png");
		
		ImageIcon iconU = new ImageIcon("src/main/java/images/username.png");
		ImageIcon iconP = new ImageIcon("src/main/java/images/password.png");
		
		ImageIcon iconSingin = new ImageIcon("src/main/java/images/signin.png");
		ImageIcon iconSinginPush = new ImageIcon("src/main/java/images/signinPush.png");
		
		
		//username 
		model.setUsernameL(new JLabel());
		model.getUsernameL().setBounds(125, 375, 200, 50);
		model.getUsernameL().setIcon(resizeIcon(iconU, model.getUsernameL().getWidth(), model.getUsernameL().getHeight()));
		
		model.setUsername(new JTextField());
		model.getUsername().setBounds(355, 380, 190, 40);	
		model.getUsername().setBackground(new Color(255, 241, 202));
		
		
		//password 
		model.setPasswordL(new JLabel());
		model.getPasswordL().setBounds(125, 445, 200, 50);
		model.getPasswordL().setIcon(resizeIcon(iconP, model.getPasswordL().getWidth(), model.getPasswordL().getHeight()));
		
		model.setPassword(new JTextField());
		model.getPassword().setBounds(355, 450, 190, 40);
		model.getPassword().setBackground(new Color(255, 241, 202));
		
		
		//login
		model.setButtonLogin(new JButton());
		model.getButtonLogin().setBounds(355, 520, 100, 50);
		model.getButtonLogin().setIcon(resizeIcon(iconLogin, model.getButtonLogin().getWidth(), model.getButtonLogin().getHeight()));
		
    	//Registration
		model.setButtonRegistration(new JButton());
		model.getButtonRegistration().setBounds(194, 523, 120, 50);
		model.getButtonRegistration().setIcon(resizeIcon(iconSingin, model.getButtonRegistration().getWidth(), model.getButtonRegistration().getHeight()));
   
		model.getButtonRegistration().addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	model.getButtonRegistration().setIcon(resizeIcon(iconSinginPush, model.getButtonRegistration().getWidth(), model.getButtonRegistration().getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	model.getButtonRegistration().setIcon(resizeIcon(iconSingin, model.getButtonRegistration().getWidth(), model.getButtonRegistration().getHeight())); 
    	    }
    	});
    	
    	model.getButtonLogin().addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	    	model.getButtonLogin().setIcon(resizeIcon(iconLoginPush, model.getButtonLogin().getWidth(), model.getButtonLogin().getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	    	model.getButtonLogin().setIcon(resizeIcon(iconLogin, model.getButtonLogin().getWidth(), model.getButtonLogin().getHeight())); 
    	    }
    	});
    	
    	//Error
    	model.setError(new JTextField());
    	model.getError().setBounds(179, 580, 310, 40);
    	model.getError().setBackground(Color.BLACK);
    	model.getError().setForeground(Color.RED);
    	model.getError().setHorizontalAlignment(SwingConstants.CENTER);
    	
    	
    	JFrame frame = model.getFrame();
    	//Frame
    	frame.add(model.getUsernameL());
    	frame.add(model.getPasswordL());
        frame.add(model.getUsername());
        frame.add(model.getPassword());
        frame.add(model.getButtonLogin());
        frame.add(model.getError());
        frame.add(model.getButtonRegistration());
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
}
