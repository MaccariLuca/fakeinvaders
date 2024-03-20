package FAKEINVADERS_ViewPack;

import javax.imageio.ImageIO;
import javax.swing.*;

import FAKEINVADERS_ControllerPack.GameOverController;
import FAKEINVADERS_ModelPack.Commons;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverView 
{
    private final JFrame frame;
    private final JLabel scoreLabel;
    private final JButton startButton;
    private final JButton backButton;
    private final JButton exitButton;

    public GameOverView(int lastScore) throws IOException 
    {
        frame = new JFrame();
        frame.setContentPane(new JPanel() 
        {
            private static final long serialVersionUID = 1L;

            File pathmenu = new File("src/main/java/FAKEINVADERS_Images/gameOver.jpg");
            BufferedImage image = ImageIO.read(pathmenu);

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, -45, -100, 768, 768, this);
            }
        });

        
        ImageIcon iconStartNewGame = new ImageIcon("src/main/java/FAKEINVADERS_Images/newGame.png");
		ImageIcon iconStartNewGamePush = new ImageIcon("src/main/java/FAKEINVADERS_Images/newGamePush.png");
		
		ImageIcon iconExit = new ImageIcon("src/main/java/FAKEINVADERS_Images/exit.png");
		ImageIcon iconExitPush = new ImageIcon("src/main/java/FAKEINVADERS_Images/exitPush.png");
		
		ImageIcon iconMainMenu = new ImageIcon("src/main/java/FAKEINVADERS_Images/mainMenu.png");
		ImageIcon iconMainMenuPush = new ImageIcon("src/main/java/FAKEINVADERS_Images/mainMenuPush.png");
		
        //SCORE LABEL
        scoreLabel = new JLabel();
        scoreLabel.setBounds(272, 340, 135, 40);
        scoreLabel.setText("YOUR SCORE : " + String.valueOf(lastScore));
        scoreLabel.setForeground(Color.RED);
        Font customFont = new Font(scoreLabel.getFont().getName(), Font.PLAIN, 16);
        scoreLabel.setFont(customFont);

        //START NEW GAME BUTTON
        startButton = new JButton();
        startButton.setBounds(195, 400, 300, 60);
        startButton.setIcon(resizeIcon(iconStartNewGame, startButton.getWidth(), startButton.getHeight()));
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(resizeIcon(iconStartNewGamePush, startButton.getWidth(), startButton.getHeight()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(resizeIcon(iconStartNewGame, startButton.getWidth(), startButton.getHeight()));
            }
        });
        
        //BACK BUTTON
        backButton = new JButton();
        backButton.setBounds(215, 470, 260, 47);
        backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight()));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(resizeIcon(iconMainMenuPush, backButton.getWidth(), backButton.getHeight()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight()));
            }
        });
     

        //EXIT BUTTON
        exitButton = new JButton();
        exitButton.setBounds(285, 530, 100, 50);
        exitButton.setIcon(resizeIcon(iconExit, exitButton.getWidth(), exitButton.getHeight()));
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(resizeIcon(iconExitPush, exitButton.getWidth(), exitButton.getHeight()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(resizeIcon(iconExit, exitButton.getWidth(), exitButton.getHeight()));
            }
        });

        // Aggiunta dei componenti al frame
        frame.add(scoreLabel);
        frame.add(startButton);
        frame.add(backButton);
        frame.add(exitButton);

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
    private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // Metodo per chiudere la finestra
    public void dispose() 
    {
        frame.dispose();
    }
    
    public void addStartButtonListener(ActionListener listener) {
    	startButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }
}