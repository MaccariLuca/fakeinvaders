package controllerPack;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

import modelPack.Commons;

public class FakeInvaders extends JFrame  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FakeInvaders() 
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
    }
 
}