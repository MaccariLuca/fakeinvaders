package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LoginMenu extends MainMenu 
{
	LoginMenu() throws IOException
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
		
		
    	JTextField username = new JTextField();
    	username.setBounds(145, 380, 350, 60);
    	username.setBackground(Color.BLACK);
    	
    	JTextField password = new JTextField();
    	password.setBounds(145, 450, 350, 60);
    	password.setBackground(Color.BLACK);
    	
    	//Credits
    	JButton buttonLogin = new JButton();
    	buttonLogin.setBounds(265, 510, 100, 50);
    	
        frame.add(username);
        frame.add(password);
        frame.add(buttonLogin);
		frame.setTitle("FAKE INVADERS"); 
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		buttonLogin.addActionListener(e -> 
    	{
    		final String DB_REL_FILE = "src/main/java/database/database.db3";
    		final String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
    		try {			
    		 Connection conn = DriverManager.getConnection(DB_URL);
    		 if (conn != null) {
    		   DatabaseMetaData meta = conn.getMetaData();
    		   System.out.println("The driver name is " + meta.getDriverName());
    		   System.out.println("A new database has been created.");
    		 }
    		 // controllo che il file esista a questo punto
    		 System.out.println("il file esiste? " + new File(DB_REL_FILE).exists());
    		 
    		 Statement stmt = conn.createStatement();
    		 String sql = "INSERT INTO PLAYERS VALUES (" + " \""+username.getText()+" \"," + " \""+ password.getText() + " \" )";
    		 stmt.executeUpdate(sql);
    		 System.out.println("Utente inserito con successo");
    		 
    		 stmt = conn.createStatement();
     		 sql = "SELECT * FROM PLAYERS";
     		System.out.println("risultati:");
     		ResultSet resultSet = stmt.executeQuery(sql);
     		// stampa i risultati
     		while (resultSet.next()) {
     		    for (int i = 1; i <= 2; i++) {
     		        System.out.print(resultSet.getString(i)+ " ");
     		    }
     		    System.out.println();
     		}
     		stmt.close();
     		conn.close();
     		System.out.println("query eseguita con successo");
     		
    		} catch (SQLException ex) {
    		  System.out.println(ex.getMessage());
    		}
    		
    		frame.dispose();
    		try {
				new Menu();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
		
		
	}
	
}


