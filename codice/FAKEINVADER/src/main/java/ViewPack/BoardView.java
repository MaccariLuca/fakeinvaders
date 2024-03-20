package ViewPack;

import javax.swing.JPanel;
import modelPack.Commons;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class BoardView extends JPanel 
{
	private static final long serialVersionUID = 2797967856640653592L;//AUTO_GENERATED

	private Dimension dimension;
	
    public BoardView() {

        dimension = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT); //Dimension based on the default dimension
        setBackground(Color.black); //background black like the space
    }

    public void doDrawing(Graphics g) 
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, dimension.width, dimension.height);
        g.setColor(Color.black);
    }

}