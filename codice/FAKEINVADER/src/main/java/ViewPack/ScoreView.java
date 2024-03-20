package ViewPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

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

import modelPack.Commons;
import modelPack.Menu;
import modelPack.ScoreModel;

public class ScoreView 
{
	private JFrame scoreframe;
	 private final JButton backButton;
	public ScoreView()
	{
		scoreframe = new JFrame();
		
	    try {
			scoreframe.setContentPane(new JPanel() 
			{
			    private static final long serialVersionUID = 1L;
			    File pathmenuscore = new File("src/main/java/images/black.png");
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

	    ImageIcon iconMainMenu = new ImageIcon("src/main/java/images/mainMenu.png");
	    ImageIcon iconMainMenuPush = new ImageIcon("src/main/java/images/mainMenuPush.png");
	
	    scoreframe.setLayout(null);
	    scoreframe.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
	    scoreframe.setLocationRelativeTo(null);
	    scoreframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    JTextArea resultTextArea = new JTextArea();
	    JScrollPane scrollPane = new JScrollPane(resultTextArea);
	    scrollPane.setBounds(85,50,500,400);
	    scoreframe.add(scrollPane);
	    
	    Color colore = new Color(0, 186, 224);
	    Border compoundBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,colore);
	
	    scrollPane.setBorder(compoundBorder);
	  
	    resultTextArea.setEditable(false);
	    resultTextArea.setBackground(Color.black);
	    resultTextArea.setForeground(Color.white);
	    
	    try {
	        ScoreModel scoreModel = new ScoreModel();
	        ResultSet resultSet = scoreModel.getResultSet(); // Assicurati che questo metodo esegua una query e restituisca un ResultSet valido
	        if (resultSet != null) {
	            while (resultSet.next()) {
	                String retrievedUsername = resultSet.getString("USERNAME");
	                int retrievedScore = resultSet.getInt("SCORE");
	                String retrievedDate = resultSet.getString("DAY");
	                resultTextArea.append("Username: " + retrievedUsername + ", Score: " + retrievedScore
	                        + ", Date: " + retrievedDate + "\n");
	            }
	        } else {
	            // Gestisci il caso in cui il ResultSet sia nullo
	            System.out.println("Il ResultSet è nullo. Assicurati di aver eseguito una query.");
	        }
	    } catch (Exception e) {
	        // Gestisci le eccezioni in modo appropriato
	        e.printStackTrace();
	    }

	
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
	    
	    backButton.addActionListener(a -> {
	        try {
	            new Menu();
	            scoreframe.dispose();
	        } catch (IOException e1) {
	            e1.printStackTrace();
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
}
