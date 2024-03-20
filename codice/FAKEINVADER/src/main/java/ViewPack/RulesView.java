package ViewPack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

public class RulesView 
{
	private JFrame rulesframe;
	 private final JButton backButton;
	public RulesView()
	{
		
		rulesframe = new JFrame();
	    
		try {
			rulesframe.setContentPane(new JPanel() 
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

    rulesframe.setLayout(null);
    rulesframe.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
    rulesframe.setLocationRelativeTo(null);
    rulesframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextArea rulesTextArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(rulesTextArea);
    scrollPane.setBounds(85,50,500,400);
    rulesframe.add(scrollPane);
    
    Color colore = new Color(0, 186, 224);
    Border compoundBorder = BorderFactory.createMatteBorder(2, 2, 2, 2,colore);

    scrollPane.setBorder(compoundBorder);
  
    rulesTextArea.setEditable(false);
    rulesTextArea.setBackground(Color.black);
    rulesTextArea.setForeground(Color.white);
    
    rulesTextArea.setText("HOW TO PLAY THE GAME\n");
    rulesTextArea.append("KILL ALL THE ALIENS TO GET THE HARDEST LEVEL POSSIBLE!\n\n");
    rulesTextArea.append("Arrows:\t Move the player's ship\n");
    rulesTextArea.append("Spacebar:\t Shoot your bullet. You can't shoot until the previous bullet has reached an alien\n\t or the border of the gameboard\n");
    rulesTextArea.append("R button:\t Shoot your powershot! You can shoot only once per level\n\n");
    rulesTextArea.append("If an alien reaches the bottom border OR an alien's shot kills you, YOU LOSE!");

    backButton = new JButton();
    backButton.setBounds(215, 470, 260, 47);
    backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight()));
    
    backButton.addMouseListener(new java.awt.event.MouseAdapter() 
    {
	    public void mouseEntered(java.awt.event.MouseEvent evt) 
	    {
	    	backButton.setIcon(resizeIcon(iconMainMenuPush, backButton.getWidth(), backButton.getHeight())); // cambia icona quando il mouse entra nell'area del pulsante
	    }
	    public void mouseExited(java.awt.event.MouseEvent evt) 
	    {
	    	backButton.setIcon(resizeIcon(iconMainMenu, backButton.getWidth(), backButton.getHeight())); 
	    }

    });

    backButton.addActionListener(a -> {
        try {
            new Menu();
            rulesframe.dispose();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
            });

    rulesframe.add(backButton);
    rulesframe.setVisible(true);
    rulesframe.setTitle("RULES");
    
	}
	
	 private Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	        Image img = icon.getImage();
	        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
	        return new ImageIcon(resizedImage);
	    }
	    
    public void dispose() 
    {
    	rulesframe.dispose();
    }
  
    public void addBackButtonListener(ActionListener listener) {
    	backButton.addActionListener(listener);
    }

}


