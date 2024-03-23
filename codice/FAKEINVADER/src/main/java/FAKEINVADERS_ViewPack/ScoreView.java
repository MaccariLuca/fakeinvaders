package FAKEINVADERS_ViewPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import FAKEINVADERS_ModelPack.Commons;

public class ScoreView 
{
	private JFrame scoreframe;
	private final JButton backButton;
	private JTextArea resultTextArea;
	
	public ScoreView()
	{
		resultTextArea = new JTextArea();
		scoreframe = new JFrame();
		
	    try {
			scoreframe.setContentPane(new JPanel() 
			{
			    private static final long serialVersionUID = 1L;
			    File pathmenuscore = new File("src/main/java/FAKEINVADERS_Images/black.png");
			    BufferedImage image = ImageIO.read(pathmenuscore);

			    public void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        g.drawImage(image, -45, -100, 768, 768, this);
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    ImageIcon iconMainMenu = new ImageIcon("src/main/java/FAKEINVADERS_Images/mainMenu.png");
	    ImageIcon iconMainMenuPush = new ImageIcon("src/main/java/FAKEINVADERS_Images/mainMenuPush.png");
	
	    scoreframe.setLayout(null);
	    scoreframe.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
	    scoreframe.setLocationRelativeTo(null);
	    scoreframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    //JTextArea resultTextArea = new JTextArea();
	    JScrollPane scrollPane = new JScrollPane(resultTextArea);
	    scrollPane.setBounds(85,50,500,400);
	    scoreframe.add(scrollPane);
	    
	    Color colore = new Color(0, 186, 224);
	    Border compoundBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,colore);
	
	    scrollPane.setBorder(compoundBorder);
	  
	    resultTextArea.setEditable(false);
	    resultTextArea.setBackground(Color.black);
	    resultTextArea.setForeground(Color.white);
	    
	    backButton = new JButton();
	    backButton.setBounds(215, 470, 260, 47);
	    backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight()));
	    
	    backButton.addMouseListener(new java.awt.event.MouseAdapter() 
	    {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	backButton.setIcon(resizeIcon(iconMainMenuPush, backButton.getWidth(), backButton.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
		    }
	
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight())); 
		    }
	 
	    });
	   
	
	    scoreframe.add(backButton);
	    scoreframe.setVisible(true);
	    scoreframe.setTitle("RECORDS");
		}
	
		private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	        Image img = icon.getImage();
	        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
	        return new ImageIcon(resizedImage);
	    }
    
	 public void addBackButtonListener(ActionListener listener) {
		 backButton.addActionListener(listener);
	    }
	   
	 public void dispose() 
	 {
		 scoreframe.dispose();
	 }
	 
	 public void showLastGames(List<Object[]> lastGames) {
		 	resultTextArea.setText("");  // Pulisce il contenuto della JTextArea prima di visualizzare i nuovi risultati
	        for (Object[] game : lastGames) 
	        {
	            String gameInfo = "Username: " + game[0] + ", Score: " + game[1] + ", Day: " + game[2] + "\n";
	            resultTextArea.append(gameInfo); 
	        }
	    }


	 
}