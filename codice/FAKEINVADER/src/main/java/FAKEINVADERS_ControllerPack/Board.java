package FAKEINVADERS_ControllerPack;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import FAKEINVADERS_ModelPack.Alien;
import FAKEINVADERS_ModelPack.Bomb;
import FAKEINVADERS_ModelPack.Commons;
import FAKEINVADERS_ModelPack.GameOverModel;
import FAKEINVADERS_ModelPack.Player;
import FAKEINVADERS_ModelPack.PowerShot;
import FAKEINVADERS_ModelPack.ShotModel;
import FAKEINVADERS_ViewPack.AlienView;
import FAKEINVADERS_ViewPack.BoardView;
import FAKEINVADERS_ViewPack.BombView;
import FAKEINVADERS_ViewPack.GameOverView;
import FAKEINVADERS_ViewPack.PlayerView;
import FAKEINVADERS_ViewPack.PowerShotView;
import FAKEINVADERS_ViewPack.ShotView;

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
    private ShotModel shot;
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
    
    private int targetDeaths = -1;	//valore che è diverso dal valore iniziale di deaths

    public Board() 
    {
        initializeViews();
        initializePlayer();
        initializeShot();
        initializePowerShot();
        initializeKeyAndFocus();
        initializeTimer();
        gameInit(level);
    }

    //Initializes the view of the board
    private void initializeViews() 
    {
        this.view = new BoardView();
        view.setFocusable(true);
    }

    //Initialize the player by an instance of every class in MVC
    private void initializePlayer() 
    {
        playerModel = new Player();
        playerView = new PlayerView();
        playerController = new PlayerController(playerModel, playerView);
    }

  //Initialize the shot by an instance of every class in MVC
    private void initializeShot() 
    {
        shot = new ShotModel();
        shotView = new ShotView();
        shotController = new ShotController(shot, shotView);
    }

  //Initialize the power shot by an instance of every class in MVC
    private void initializePowerShot() 
    {
        powerShot = new PowerShot();
        powerShotView = new PowerShotView();
        powerShotController = new PowerShotController(getPowerShot(), powerShotView);
    }

    //Adds a key listener and set focus on true
    private void initializeKeyAndFocus() 
    {
        addKeyListener(new PlayerInput());
        setFocusable(true);
    }

    //Starts the timer of the game. Timer is used to run the script 
    private void initializeTimer() 
    {
        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
    }
    
    //The game begins
    void gameInit(int level) 
    {
        aliens = new ArrayList<>(); //the aliens are arranged in an arrayList because it's a simple method
        
        //if the level is even, there are more columns
        if(level % 2 == 0) 
        {
        	increaseColums++;
        }
        
        //if the level isn't the first level, an additional alien per line is added
        else if(level != 1)
        {
    		increaseLine++;
        }
        
        //the aliens are initialized
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
        targetDeaths = (3 + increaseLine)* (6 + increaseColums); //targetDeaths is the number of aliens to kill for the win
    }

    //draws the aliens using foreach. It's used also to control the status of the aliens
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

    //draws the player. It's used also to control the status of it
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

    //draws the shot
    private void drawShot(Graphics g) 
    {
        if (shotController.getView().isVisible()) 
        {
            g.drawImage(shotController.getView().getImage(), shotController.getX(), shotController.getY(), this);
        }
    }

    //draws the powerShot
    private void drawPowerShot(Graphics g) 
    {
		if (powerShotController.isVisible()) 
		{
            g.drawImage(powerShotController.getImage(), powerShotController.getX(), powerShotController.getY(), this);
        }
    }
    
    //draws the aliens' bombs
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

    //paintComponent is part of the JComponent class
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g); //our function to print the board
    }

    private void doDrawing(Graphics g) 
    {
        view.doDrawing(g); 
        if (inGame) //if the player is still alive
        {            
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawPowerShot(g);
            drawBomb(g);
        } 
        else //(the timer is not running -> the game is over)
        {
            if (timer.isRunning()) //if it's still running, then it needs to stop
            {
                timer.stop();
            }
            
            gameOver(g);
        }

    }

    //used when the player loose the game
    private void gameOver(Graphics g) 
    {
    	Timer timer = new Timer(500, new ActionListener() 
    	{
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	//the board is a JPanel embedded in a JFrame, so we need to first find the frame, and then dispose every graphic
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

        timer.setRepeats(false); 
        timer.start(); 
    }
    
    //Used every now and then to update the status of the game
    private void update() 
    {
        updateGameState();
        updatePlayer();
        updateShots();
        updateAliens();
        updateBombs();
       
    }

    //used to increase the led and reset the score
    private void updateGameState() 
    {
        if (deaths == targetDeaths) 
        {
            level++;
            setPowerShotShooted(true);
            
            deaths = 0;
            gameInit(level);
        }
    }

    //list of function used to update the state of every element in the game
    private void updatePlayer() 
    {
        playerController.act();
        handlePlayerCollisions();
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
    public void handlePlayerCollisions() 
    {
    	int playerX = playerController.getX();
    	int playerY = playerController.getY();
    	
    	System.out.println(playerX);

        for (AlienController alien : aliens) 
        {
            int alienX = alien.getX();
            int alienY = alien.getY();

            if (alien.isVisible() && playerController.isVisible() && playerX + Commons.PLAYER_WIDTH >= alienX && playerX <= alienX + Commons.ALIEN_WIDTH
                    && playerY + Commons.PLAYER_HEIGHT >= alienY && playerY <= alienY + Commons.ALIEN_HEIGHT) 
            {

            	inGame = false; 
            }
        }
    }
 

    //used to handle every time a shot kills an alien
    public void handleStandardShotCollisions() 
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
        if(shotY >= Commons.BOARD_HEIGHT)
        {
        	
        	shotController.die();
        }
    }

    //draws the bullets
    private void moveStandardShot() 
    {
        int y = shotController.getY() - 4;
        if (y < 1) 
        {
            shotController.die();
        } else 
        {
            shotController.setY(y);
        }
    }

    //used to handle the status of the powershot
    public void updatePowerShot() 
    {
        if (powerShotController.isVisible()) 
        {
            handlePowerShotCollisions();
            movePowerShot();
        }       
    }

    //used when the powershot hits an alien
    public void handlePowerShotCollisions() 
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
            
            if(pShotY >= Commons.BOARD_HEIGHT)
            {
            	setPowerShotShooted(false);
            }
        }
    }

    //used to draw the powershot
    public void movePowerShot() 
    {
        int y = powerShotController.getY() - 4;
        if (y < 0) 
        {       	
        	powerShotController.die();
        } else 
        {
        	powerShotController.setY(y);
        }
    }

    
    private void updateAliens()
    {
        moveAliens();
        handleAlienCollisions();
    }

    public void moveAliens() 
    {
        for (AlienController alien : aliens) 
        {
            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && getDirection() != -1) 
            {
                setDirection(-1);
                moveAliensDown();
            }

            if (x <= Commons.BORDER_LEFT && getDirection() != 1) 
            {
                setDirection(1);
                moveAliensDown();
            }

            alien.act(getDirection());
        }
    }

    private void moveAliensDown() 
    {
        for (AlienController alien : aliens) 
        {
            alien.setY(alien.getY() + Commons.GO_DOWN);
        }
    }

    public void handleAlienCollisions() 
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

    public List<AlienController> getAliens() {
        return this.aliens;
    }
    
    public void setAliens(List<AlienController> aliens) {
        this.aliens = aliens;
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
    
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    
    public int getDeaths() {
        return deaths;
    }

    public void incrementLevel() {
        updateGameState();
    }

    public int getCurrentLevel() {
        return level;
    }

    public boolean isInGame() {
        return inGame;
    }

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isPowerShotShooted() {
		return powerShotShooted;
	}

	public void setPowerShotShooted(boolean powerShotShooted) {
		this.powerShotShooted = powerShotShooted;
	}

	public PowerShot getPowerShot() {
		return powerShot;
	}
	 
	public void setTargetDeaths(int targetDeaths) {
		this.targetDeaths = targetDeaths;
	}
	
	public void setShotController(ShotController shotController) {
		this.shotController = shotController;
	}
	
	public int getScore() {
		return score;
	}
		
    private class GameCycle implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            doGameCycle();
        }
    }

    private class PlayerInput extends KeyAdapter 
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
            

            if (key == KeyEvent.VK_SPACE && inGame && !shotController.isShotActive()) 
            {
            	shotController.setShotActive(true);
                shotController.getView().setVisible(true);
                shot = new ShotModel(x, y);
                shotController.reload(shot);
            }

            if(key == KeyEvent.VK_R && isPowerShotShooted())
            {
    			setPowerShotShooted(false);
            	
        		if(!powerShotController.isVisible() && inGame)
        		{
        			powerShot = new PowerShot(x, y);
        			powerShotView = new PowerShotView();
        			powerShotController = new PowerShotController(getPowerShot(), powerShotView);
        		}			
            }
        }
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

  

}