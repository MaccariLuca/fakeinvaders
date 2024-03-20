package controllerPack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ViewPack.AlienView;
import ViewPack.BoardView;
import ViewPack.BombView;
import ViewPack.GameOverView;
import ViewPack.PlayerView;
import ViewPack.PowerShotView;
import ViewPack.ShotView;
import modelPack.Alien;
import modelPack.Bomb;
import modelPack.Commons;
import modelPack.GameOverModel;
import modelPack.Player;
import modelPack.PowerShot;
import modelPack.Shot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel
{
	
	private static final long serialVersionUID = 2797967856640653592L;//AUTO_GENERATED
	
	private BoardView view;
    private List<AlienController> aliens;
    private PlayerController player;
    private PlayerView playerView;
    private Player playerModel;
    private PlayerController playerController;
    private Shot shot;
    private ShotView shotView;
    private ShotController shotController;
    private PowerShotView powerShotView;
    private PowerShot powerShot;
    private PowerShotController powerShotController;
    private boolean powerShotShooted = true;
    
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

        this.view = new BoardView();
        initBoard();
        gameCycle();
    }

    private void initBoard() 
    {

        addKeyListener(new TAdapter()); //keylistener to listen to the player's keyboard
        setFocusable(true); //board focusable
        
        view.setFocusable(true); //board focusable
        view.setBackground(Color.black); //background black like the space

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
                var alien = new Alien(Commons.ALIEN_INIT_X + 50 * j, Commons.ALIEN_INIT_Y + 50 * i);
                var alienView = new AlienView(Commons.ALIEN_INIT_Y + 50 * i);
                var alienController = new AlienController(alien, alienView);
                aliens.add(alienController);
            }
        }
        
        targetDeaths = (3 + increaseLine)* (6 + increaseColums);
        
        playerModel = new Player();
        playerView = new PlayerView();
        playerController = new PlayerController(playerModel, playerView);//...the player...
        shot = new Shot();//...and the shot
        shotView = new ShotView();
        shotController = new ShotController(shot, shotView);
        powerShot = new PowerShot(0, 0);
        powerShotView = new PowerShotView();
        powerShotController = new PowerShotController(powerShot, powerShotView);
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

    private void drawPlayer(Graphics g) 
    {

        if (playerController.isVisible()) 
        {
            g.drawImage(playerController.getImage(), playerController.getX(), playerController.getY(), this);
        }

        if (playerController.isDying()) 
        {
            playerController.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) 
    {
        if (shotController.getView().isVisible()) 
        {
            g.drawImage(shotController.getView().getImage(), shotController.getX(), shotController.getY(), this);
        }
    }

    private void drawPowerShot(Graphics g) 
    {
		if (powerShotController.isVisible()) 
		{
            g.drawImage(powerShotController.getImage(), powerShotController.getX(), powerShotController.getY(), this);
        }
    }
    
    private void drawBomb(Graphics g) 
    {
        for (AlienController alien : aliens) 
        {
            Bomb b = alien.getBomb();
            BombView bombView = new BombView();
            BombController bombController = new BombController(b, bombView);

            if (!bombController.isDestroyed()) 
            {
                g.drawImage(bombView.getImage(), bombController.getBomb().getX(), bombController.getBomb().getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) 
    {
        view.doDrawing(g);
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
            if (timer.isRunning()) 
            {
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
	                new GameOverController(new GameOverModel(score), new GameOverView(score));
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
        });

        timer.setRepeats(false); // Imposta il timer per eseguire l'azione solo una volta
        timer.start(); // Avvia il timer
    }
    
    private void update() 
    {
        updateGameState();
        updatePlayer();
        updateShots();
        updateAliens();
        updateBombs();
       
    }

    private void updateGameState() 
    {
        if (deaths == targetDeaths) 
        {
            level++;
            powerShotShooted = true;
            deaths = 0;
            gameCycle();
        }
    }

    private void updatePlayer() 
    {
        playerController.act();
    }

    private void updateShots() 
    {
        updateStandardShot();
        updatePowerShot();
    }

    private void updateStandardShot() 
    {
        if (shotController.getView().isVisible()) 
        {
            handleStandardShotCollisions();
            moveStandardShot();
        }
    }

    private void handleStandardShotCollisions() 
    {
        int shotX = shotController.getX();
        int shotY = shotController.getY();

        for (AlienController alien : aliens) 
        {
            int alienX = alien.getX();
            int alienY = alien.getY();

            if (alien.isVisible() && shot.isVisible() && shotX >= alienX && shotX <= alienX + Commons.ALIEN_WIDTH
                    && shotY >= alienY && shotY <= alienY + Commons.ALIEN_HEIGHT) 
            {

                alien.setDying(true);
                deaths++;
                score++;
                shotController.die();
            }
        }
    }

    private void moveStandardShot() 
    {
        int y = shotController.getY() - 4;
        if (y < 1) {
            shotController.die();
        } else {
            shotController.setY(y);
        }
    }

    private void updatePowerShot() 
    {
        if (powerShotController.isVisible()) 
        {
            handlePowerShotCollisions();
            movePowerShot();
        }
    }

    private void handlePowerShotCollisions() 
    {
        int pShotX = powerShotController.getX();
        int pShotY = powerShotController.getY();

        for (AlienController alien : aliens) 
        {
            int alienX = alien.getX();
            int alienY = alien.getY();

            if (alien.isVisible() && powerShotController.isVisible() && pShotX >= alienX && pShotX <= alienX + Commons.ALIEN_WIDTH
                    && pShotY >= alienY && pShotY <= alienY + Commons.ALIEN_HEIGHT) 
            {
                alien.setDying(true);
                deaths++;
                score++;
                shotController.die();
            }
        }
    }

    private void movePowerShot() 
    {
        int y = powerShotController.getY() - 4;
        if (y < 0) {
        	powerShotController.die();
        } else {
        	powerShotController.setY(y);
        }
    }

    private void updateAliens()
    {
        moveAliens();
        handleAlienCollisions();
    }

    private void moveAliens() {
        for (AlienController alien : aliens) 
        {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) 
            {
                direction = -1;
                moveAliensDown();
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) 
            {
                direction = 1;
                moveAliensDown();
            }

            alien.act(direction);
        }
    }

    private void moveAliensDown() 
    {
        for (AlienController alien : aliens) 
        {
            alien.setY(alien.getY() + Commons.GO_DOWN);
        }
    }

    private void handleAlienCollisions() 
    {
        for (AlienController alien : aliens) 
        {
            int y = alien.getY();
            if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) 
            {
                inGame = false;
            }
        }
    }

    private void updateBombs() 
    {
        Random generator = new Random();

        for (AlienController alien : aliens) {
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
             int playerX = playerController.getX();
             int playerY = playerController.getY();

             if (playerController.isVisible() && !bombController.isDestroyed()) //used to determinate if the player is shot
             {
                 if (bombX >= (playerX)
                         && bombX <= (playerX + Commons.PLAYER_WIDTH)
                         && bombY >= (playerY)
                         && bombY <= (playerY + Commons.PLAYER_HEIGHT)) 
                 {
                       playerController.setDying(true);
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

            playerController.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) 
        {
            playerController.keyPressed(e);

            int x = playerController.getX();
            int y = playerController.getY();

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
            		if(!powerShotController.isVisible())
            		{
            			powerShot = new PowerShot(x, y);
            			powerShotView = new PowerShotView();
            			powerShotController = new PowerShotController(powerShot, powerShotView);
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