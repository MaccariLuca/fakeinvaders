package modelPack;

import javax.swing.ImageIcon;


public class Player extends Sprite 
{
    private int width;
    private int height;

    public Player() 
    {
        initPlayer();
    }

    private void initPlayer() 
    {
        var playerImg = "src/main/java/images/ship.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 550;
        setY(START_Y);
    }

    public void act() {
        x += getDx();
        y += getUp();

        
        if (x <= 2) {
            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - width) {
            x = Commons.BOARD_WIDTH - width;
        }

        // Blocca il giocatore a metà dell'altezza della board
        
        int halfBoardHeight = Commons.BOARD_HEIGHT / 2;
        if (y < halfBoardHeight) {
            y = halfBoardHeight;
        } else if (y > Commons.BOARD_HEIGHT - height) {
            y = Commons.BOARD_HEIGHT - height;
        }
        
        // Blocca il giocatore al terreno 
        if (y > 550) {
            y = 550;
        }
    }
}
