package ViewPack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controllerPack.AlienController;
import controllerPack.BombController;
import controllerPack.PlayerController;
import controllerPack.ShotController;
import modelPack.Alien;
import modelPack.Bomb;
import modelPack.Commons;
import modelPack.Player;
import modelPack.PowerShot;
import modelPack.Shot;
import game.GameOverMenu;

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
import java.io.IOException;
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
	
	private Dimension dimension;
    private List<AlienController> aliens;
    private PlayerController player;
    private Player playerView = new Player();
    private Shot shot;
    private ShotView shotView;
    private ShotController shotController;
    private PowerShot powerShot;
    boolean powerShotShooted = true;
    
    private int direction = -1;
    private int deaths = 0;
    private int score = deaths;

    private boolean inGame = true;
    private Timer timer;

    private int level = 1;
    private int increaseLine = 0;
    private int increaseColums = 0;
    
    private int targetDeaths = -1;

    public Board() {

        initBoard();
        gameCycle();
    }

    private void initBoard() 
    {

        addKeyListener(new TAdapter()); //keylistener to listen to the player's keyboard
        setFocusable(true); //board focusable
        dimension = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT); //Dimension based on the default dimension
        setBackground(Color.black); //background black like the space

        timer = new Timer(Commons.DELAY, new GameCycle()); //used to control the state of the alienModels in the game
        timer.start();

        gameCycle();
    }
  
    private void gameCycle()
    {
    	gameInit(level);
    }
    
    void gameInit(int level) 
    {
        aliens = new ArrayList<>();
        
        if(level % 2 == 0) 
        {
        	increaseColums++;
        }else
        {
        	if(level != 1)
        		increaseLine++;
        }
        
        for (int i = 0; i < 3 + increaseLine; i++) 
        {
            for (int j = 0; j < 6 + increaseColums; j++) 
            {
                var alien = new Alien(Commons.ALIEN_INIT_X + 50 * j,
                        Commons.ALIEN_INIT_Y + 50 * i);
                //alienModels.add(alien);
                var alienView = new AlienView(Commons.ALIEN_INIT_Y + 50 * i);
                //alienViews.add(alienView);
                var alienController = new AlienController(alien, alienView);
                aliens.add(alienController);
            }
        }
        
        targetDeaths = (3 + increaseLine)* (6 + increaseColums);
        
        player = new PlayerController(playerView);//...the player...
        shot = new Shot();//...and the shot
        shotView = new ShotView();
        shotController = new ShotController(shot, shotView);
        powerShot = new PowerShot();
    }

    private void drawAliens(Graphics g) 
    {

        for (AlienController alien : aliens) {

            if (alien.isVisible()) {

                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {

                alien.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (playerView.isVisible()) {

            g.drawImage(playerView.getImage(), playerView.getX(), playerView.getY(), this);
        }

        if (playerView.isDying()) {

            playerView.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shotController.getView().isVisible()) {

            g.drawImage(shotController.getView().getImage(), shotController.getX(), shotController.getY(), this);
        }
    }

    private void drawPowerShot(Graphics g) {

		if (powerShot.isVisible()) {

            g.drawImage(powerShot.getImage(), powerShot.getX(), powerShot.getY(), this);
        }
    }
    
    private void drawBomb(Graphics g) {

        for (AlienController alien : aliens) {

            Bomb b = alien.getBomb();
            BombView bombView = new BombView();
            BombController bombController = new BombController(b, bombView);

            if (!bombController.isDestroyed()) {

                g.drawImage(bombView.getImage(), bombController.getBomb().getX(), bombController.getBomb().getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, dimension.width, dimension.height);
        g.setColor(Color.green);

        if (inGame) 
        {
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

    }

    private void gameOver(Graphics g) 
    {
    	Timer timer = new Timer(500, new ActionListener() 
    	{
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Board.this);
	            frame.dispose();
	            g.dispose();
	
	            try {
	                new GameOverMenu(score);
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
        });

        timer.setRepeats(false); // Imposta il timer per eseguire l'azione solo una volta
        timer.start(); // Avvia il timer
    }
    
    /*
    private void update() 
    {
        if (deaths == targetDeaths) 
        {
        	level++;
        	powerShotShooted = true;
        	deaths = 0;
        	gameCycle();
        }
        

        // player
        playerView.act();

        // shot
        if (shotController.getView().isVisible() || powerShot.isVisible()) 
        {
            int shotX = shotController.getX();
            int shotY = shotController.getY();
            
            int pShotX = powerShot.getX();
            int pShotY = powerShot.getY();

            for (AlienController alien : aliens) 
            {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) 
                {
                    if (shotX >= (alienX)
                        && shotX <= (alienX + Commons.ALIEN_WIDTH)
                        && shotY >= (alienY)
                        && shotY <= (alienY + Commons.ALIEN_HEIGHT))
                    {

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;
                        score++;
                        shotController.die();
                    }
                }
                if (alien.isVisible() && powerShot.isVisible()) 
                {
                    if (pShotX >= (alienX)
                        && pShotX <= (alienX + Commons.ALIEN_WIDTH)
                        && pShotY >= (alienY)
                        && pShotY <= (alienY + Commons.ALIEN_HEIGHT))
                    {

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;
                        score++;
                        shotController.die();
                    }
                }
            }

            int y = shotController.getY();
            y -= 4;

            if (y < 1) {
                shotController.die();
            } else {
                shotController.setY(y);
            }
        }

     // shot
        if (powerShot.isVisible()) 
        {

            int pShotX = shotController.getX();
            int pShotY = shotController.getY();

            for (AlienController alien : aliens) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shotController.isVisible()) 
                {
                    if (pShotX >= (alienX)
                        && pShotX <= (alienX + Commons.ALIEN_WIDTH)
                        && pShotY >= (alienY)
                        && pShotY <= (alienY + Commons.ALIEN_HEIGHT)){

                        var icon = new ImageIcon(explImg);
                        alien.setImage(icon.getImage());
                        alien.setDying(true);
                        deaths++;
                        score++;
                        shotController.die();
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

        for (AlienController alien : aliens) {

            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<AlienController> i1 = aliens.iterator();

                while (i1.hasNext()) {

                    AlienController a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<AlienController> i2 = aliens.iterator();

                while (i2.hasNext()) {

                    AlienController a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator <AlienController> it = aliens.iterator();

        while (it.hasNext()) {

            AlienController alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                }

                alien.act(direction);
            }
        }

        // bombs
        
        var generator = new Random();

        for (AlienController alien : aliens)
        {

            int shot = generator.nextInt(350);//random number that defines the time value of the shot (1 in 350 chance to shoot)
            Bomb bomb = alien.getBomb();
            BombView bombView = new BombView();
            BombController bombController = new BombController(bomb, bombView);

            if (shot == 0 && alien.isVisible() && bombController.isDestroyed()) //if the alien is still alive and the bomb is destroyed
            {
                bombController.setDestroyed(false);
                bombController.getBomb().setX(alien.getX());
                bombController.getBomb().setY(alien.getY());
            }

            int bombX = bombController.getBomb().getX();
            int bombY = bombController.getBomb().getY();
            int playerX = playerView.getX();
            int playerY = playerView.getY();

            if (playerView.isVisible() && !bombController.isDestroyed()) //used to determine if the player is shot
            {

                if (bombX >= (playerX)
                        && bombX <= (playerX + Commons.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + Commons.PLAYER_HEIGHT)) 
                {

                    var icon = new ImageIcon(explImg); //image of the player being shot
                    playerView.setImage(icon.getImage());
                    playerView.setDying(true);
                    bombController.setDestroyed(true);
                }
            }

            if (!bombController.isDestroyed()) 
            {

                bombController.getBomb().setY(bombController.getBomb().getY() + 1);

                if (bombController.getBomb().getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) 
                {

                    bombController.getBomb().setDestroyed(true);
                }
            }
        }
    }
    */
    private void update() {
        if (deaths == targetDeaths) {
            level++;
            powerShotShooted = true;
            deaths = 0;
            gameCycle();
        }

        // Rimuovere gli alieni morti
        Iterator<AlienController> iterator = aliens.iterator();
        while (iterator.hasNext()) {
            AlienController alien = iterator.next();
            if (!alien.isVisible()) {
                iterator.remove(); // Rimuovere l'alieno dalla lista tramite l'iteratore
            }
        }

        // Rimuovere le bombe esplose
        iterator = aliens.iterator();
        while (iterator.hasNext()) {
            AlienController alien = iterator.next();
            if (alien.getBomb().isDestroyed()) {
                iterator.remove(); // Rimuovere la bomba dalla lista tramite l'iteratore
            }
        }

        // Aggiornare gli alieni rimasti
        for (AlienController alien : aliens) {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {
                direction = -1;
                for (AlienController a2 : aliens) {
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {
                direction = 1;
                for (AlienController a : aliens) {
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }

            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                   
                }
                alien.act(direction);
            }
        }

        // Generare bombe
        Random generator = new Random();
        for (AlienController alien : aliens) {
            int shot = generator.nextInt(350);
            Bomb bomb = alien.getBomb();
            if (shot == 0 && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }
            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = playerView.getX();
            int playerY = playerView.getY();
            if (playerView.isVisible() && !bomb.isDestroyed()) {
                if (bombX >= (playerX) && bombX <= (playerX + Commons.PLAYER_WIDTH) &&
                    bombY >= (playerY) && bombY <= (playerY + Commons.PLAYER_HEIGHT)) {
                    playerView.setDying(true);
                    bomb.setDestroyed(true);
                }
            }
            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {
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

            int x = playerView.getX();
            int y = playerView.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                	shotController.getView().setVisible(true);
                    if (shotController.getView().isVisible()) {

                        shot = new Shot(x, y);
                        shotController.reload(shot);
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
    
    public List<AlienController> getAliens() {
        return this.aliens;
    }
    public int getIncreaseLine() {
        return this.increaseLine;
    }
    
    public int getIncreaseColums() {
        return this.increaseColums;
    }
    
    public int getLevel() {
        return this.level;
    }
}
