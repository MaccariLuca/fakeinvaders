package game;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FakeInvaders extends JFrame  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	FakeInvaders() 
	{
		var board = new Board();
        add(board);

        setTitle("FAKE INVADERS");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon icon = new ImageIcon("src/main/java/images/ship.png");
		setIconImage(icon.getImage());
        /*if(board.update() == 0)
        {
        	System.out.println("WELAHJ");
        }
        if(board.update() == 1)
        {
        	System.out.println("ORCOCANE");
        }*/
    }
 
}
