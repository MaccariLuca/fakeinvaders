package ViewPack;

import javax.swing.*;

import modelPack.Commons;
import modelPack.Menu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LoginView 
{
    private final JFrame frame;
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final JTextField errorField;
    private final JButton loginButton;
    private final JButton registrationButton;

    public LoginView() throws IOException 
    {
        frame = new JFrame();
        frame.setContentPane(new JPanel() 
        {
            private static final long serialVersionUID = 1L;

            File pathmenu = new File("src/main/java/images/menu.jpg");
            BufferedImage image = ImageIO.read(pathmenu);

            public void paintComponent(Graphics g) {
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
        JLabel usernameL = new JLabel();
        usernameL.setBounds(125, 375, 200, 50);
        usernameL.setIcon(resizeIcon(iconU, usernameL.getWidth(), usernameL.getHeight()));

        usernameField = new JTextField();
        usernameField.setBounds(355, 380, 190, 40);  
        usernameField.setBackground(new Color(255, 241, 202));

        //password 
        JLabel passwordL = new JLabel();
        passwordL.setBounds(125, 445, 200, 50);
        passwordL.setIcon(resizeIcon(iconP, passwordL.getWidth(), passwordL.getHeight()));

        passwordField = new JTextField();
        passwordField.setBounds(355, 450, 190, 40);
        passwordField.setBackground(new Color(255, 241, 202));

        //login
        loginButton = new JButton();
        loginButton.setBounds(355, 520, 100, 50);
        loginButton.setIcon(resizeIcon(iconLogin, loginButton.getWidth(), loginButton.getHeight()));
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setIcon(resizeIcon(iconLoginPush, loginButton.getWidth(), loginButton.getHeight()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setIcon(resizeIcon(iconLogin, loginButton.getWidth(), loginButton.getHeight()));
            }
        });

        //Registration
        registrationButton = new JButton();
        registrationButton.setBounds(194, 523, 120, 50);
        registrationButton.setIcon(resizeIcon(iconSingin, registrationButton.getWidth(), registrationButton.getHeight()));
        registrationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registrationButton.setIcon(resizeIcon(iconSinginPush, registrationButton.getWidth(), registrationButton.getHeight()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registrationButton.setIcon(resizeIcon(iconSingin, registrationButton.getWidth(), registrationButton.getHeight()));
            }
        });

        //Error
        errorField = new JTextField();
        errorField.setBounds(179, 580, 310, 40);
        errorField.setBackground(Color.BLACK);
        errorField.setForeground(Color.RED);
        errorField.setHorizontalAlignment(SwingConstants.CENTER);

        // Aggiunta dei componenti al frame
        frame.add(usernameL);
        frame.add(passwordL);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(errorField);
        frame.add(registrationButton);

        // Impostazioni del frame
        frame.setTitle("FAKE INVADERS"); 
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Metodo per ridimensionare un'icona
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }

    // Metodo per mostrare un messaggio di errore
    public void showError(String errorMessage) {
        errorField.setText(errorMessage);
    }

    // Metodo per ottenere il nome utente
    public String getUsername() {
        return usernameField.getText();
    }

    // Metodo per ottenere la password
    public String getPassword() {
        return passwordField.getText();
    }

    // Metodo per aggiungere un listener al pulsante di login
    public void addLoginListener(ActionListener listener) {
    	
        loginButton.addActionListener(listener);
    }

    // Metodo per aggiungere un listener al pulsante di registrazione
    public void addRegistrationListener(ActionListener listener) {
        registrationButton.addActionListener(listener);
    }

    // Metodo per chiudere la finestra
    public void dispose() {
        frame.dispose();
    }

    
}
