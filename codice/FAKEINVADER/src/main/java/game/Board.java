package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Board extends JPanel 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2797967856640653592L;//AUTO_GENERATED
	
	private Dimension d;
    private List<Alien> aliens;
    private Player player;
    private Shot shot;
    private PowerShot powerShot;
    boolean powerShotShooted = true;
    
    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;


    public Board() {

        initBoard();
        gameInit();
    }

    private void initBoard() 
    {

        addKeyListener(new TAdapter()); //keylistener to listen to the player's keyboard
        setFocusable(true); //board focusable
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT); //Dimension based on the default dimension
        setBackground(Color.black); //background black like the space

        timer = new Timer(Commons.DELAY, new GameCycle()); //used to control the state of the aliens in the game
        timer.start();

        gameInit();
    }


    private void gameInit() 
    {

        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j < 6; j++) 
            {

                var alien = new Alien(Commons.ALIEN_INIT_X + 50 * j,
                        Commons.ALIEN_INIT_Y + 50 * i);
                aliens.add(alien); //paints the alien...
            }
        }

        player = new Player();//...the player...
        shot = new Shot();//...and the shot
        powerShot = new PowerShot();
    }

    private void drawAliens(Graphics g) 
    {

        for (Alien alien : aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    private void drawPowerShot(Graphics g) {

		if (powerShot.isVisible()) {

            g.drawImage(powerShot.getImage(), powerShot.getX(), powerShot.getY(), this);
        }
    }
    private void drawBomb(Graphics g) {

        for (Alien a : aliens) {

            Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) { //TODO

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, Commons.GROUND, Commons.BOARD_WIDTH, Commons.GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawPowerShot(g);
            drawBomb(g);

        } 
        else 
        {

            if (timer.isRunning()) {
                timer.stop();
        }

            gameOver(g);
        }

        //Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        String messageWithPoints = (message + " - " + deaths + " points");
        g.drawString(messageWithPoints, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(messageWithPoints)) / 2,
                Commons.BOARD_WIDTH / 2);
        
        
        g.drawRect(265, 510, 100, 50); //x, y, larg, alt
        g.setColor(Color.white);
        g.setFont(small);
        String message = "Menu";
        
        //TODO
       g.dispose();
    }

    private void update() {

        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
            
        }

        // player
        player.act();

        // shot
        if (shot.isVisible() || powerShot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();
            
            int pShotX = powerShot.getX();
            int pShotY = powerShot.getY();

            for (Alien alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                        && shotX <= (alienX + Commons.ALIEN_WIDTH)
                        && shotY >= (alienY)
                        && shotY <= (alienY + Commons.ALIEN_HEIGHT)){

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;//TODO
                        shot.die();
                    }
                }
                if (alien.isVisible() && powerShot.isVisible()) {
                    if (pShotX >= (alienX)
                        && pShotX <= (alienX + Commons.ALIEN_WIDTH)
                        && pShotY >= (alienY)
                        && pShotY <= (alienY + Commons.ALIEN_HEIGHT)){

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;//TODO
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
            
            
        }

     // shot
        if (powerShot.isVisible()) {

            int pShotX = shot.getX();
            int pShotY = shot.getY();

            for (Alien alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (pShotX >= (alienX)
                        && pShotX <= (alienX + Commons.ALIEN_WIDTH)
                        && pShotY >= (alienY)
                        && pShotY <= (alienY + Commons.ALIEN_HEIGHT)){

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;//TODO
                        shot.die();
                    }
                }
            }

            int y = powerShot.getY();
            y -= 4;

            if (y < 0) {
                powerShot.die();
            } else {
                powerShot.setY(y);
            }
            
            
        }

        // aliens

        for (Alien alien : aliens) {

            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<Alien> i1 = aliens.iterator();

                while (i1.hasNext()) {

                    Alien a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<Alien> i2 = aliens.iterator();

                while (i2.hasNext()) {

                    Alien a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<Alien> it = aliens.iterator();

        while (it.hasNext()) {

            Alien alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alien.act(direction);
            }
        }

        // bombs
        var generator = new Random();

        for (Alien alien : aliens)
        {

            int shot = generator.nextInt(350);//random number that defines the time value of the shot (1 in 350 chance to shoot)
            Bomb bomb = alien.getBomb();

            if (shot == 0 && alien.isVisible() && bomb.isDestroyed()) //if the alien is still alive and the bomb is destroyed
            {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) //used to determine if the player is shot
            {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) 
                {

                    var icon = new ImageIcon(explImg); //image of the player being shot
                    player.setImage(icon.getImage());
                    player.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) 
            {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) 
                {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() 
    {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter 
    {

        @Override
        public void keyReleased(KeyEvent e) 
        {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shot.isVisible()) {

                        shot = new Shot(x, y);
                    }
                }
            }
            if(key == KeyEvent.VK_R && powerShotShooted)
            {

    			powerShotShooted = false;
            	if(inGame)
            	{
            		if(!powerShot.isVisible())
            		{
            			powerShot = new PowerShot(x, y);
            		}
            	}			
            }
        }
    }
}
