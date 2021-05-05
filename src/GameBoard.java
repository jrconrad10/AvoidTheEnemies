// Jacob Conrad and Jack Handy, Final Project, May 5, GameBoard Class extends JPanel.
// The GameBoard class takes care of countless different functions of the game including the timer, mouse movement, key movement, spawing of enemies and the avatar, etc.
// More information in the comments below.

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel {	
	
	private Avatar avatar1, avatar2, avatar3;
	private int numEnemies = 5, difficulty = 3, lives = 3, time = 0, level = 1, previousMouseX, previousMouseY;
	private boolean gameStarted = false, touchingAvatar;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private AvatarHolder avatarHolder;
	private App app;
	private String gameMessage = "";
	private Timer timer;
	private boolean paused = false;
	private JLabel instructions;
	
	// GameBoard constructor
	public GameBoard(AvatarHolder avatarHolder, App app) {
        super();
        this.setSize(1000, 900);
        this.app = app;
        this.avatarHolder = avatarHolder;
        this.setBackground(Color.green);
        
        // Creates all 3 possible avatars
        avatar1 = new Avatar(1);
        avatar2 = new Avatar(2);
        avatar3 = new Avatar(3);
        
        // Defaults the current avatar to the first avatar
        avatarHolder.setAvatar(avatar1); 
        
        // Instructions label
        instructions = new JLabel("Survive for three 10 second rounds and you win. Lose all three lives and you lose, good luck.");
        
        add(instructions);
        
        this.addMouseListener(new MouseListener() {
    		@Override
    		// Sets x and values of the mouse when pressed if the point is inside the avatar, the game is started, and the game is not paused
    		public void mousePressed(MouseEvent e) {
    			if(avatarHolder.getAvatar().contains(e.getPoint()) && gameStarted && !paused)
    			{
    			previousMouseX = e.getX();
    			previousMouseY = e.getY();
    			touchingAvatar = true;
    			}
    		}
    		@Override
    		public void mouseReleased(MouseEvent e) {
    			// Prevents dragging error
    			touchingAvatar = false;
    		}
			@Override
			public void mouseClicked(MouseEvent e) {

			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
    	});
        
        this.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Takes care of dragging the avatar if the user pressed on the avatar
				if(touchingAvatar == true)
				{
					int dx = e.getX() - previousMouseX;
					int dy = e.getY() - previousMouseY;
						
					int newLocationX = avatarHolder.getAvatar().getX() + dx;
					int newLocationY = avatarHolder.getAvatar().getY() + dy;
					
					// Checks to make sure the new location won't be out of bounds
					if(newLocationX >= 0 && newLocationX + avatarHolder.getAvatar().getWidth() <= getWidth() && newLocationY >= 0 && newLocationY + avatarHolder.getAvatar().getHeight() <= getHeight())
					{
						avatarHolder.getAvatar().setLocation(newLocationX, newLocationY);	
						previousMouseX = e.getX();
						previousMouseY = e.getY();
						repaint();
					}
				}
			}
		});
        
        this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Moves avatar up if w is pressed, the game is started, and moving the avatar won't put it out of bounds
				if(e.getKeyCode() == KeyEvent.VK_W && gameStarted == true && avatarHolder.getAvatar().getY() - 10 >= 0)
				{
					avatarHolder.getAvatar().setLocation(avatarHolder.getAvatar().getX(), avatarHolder.getAvatar().getY() - 10);
					repaint();
				}
				// Moves avatar left if a is pressed, the game is started, and moving the avatar won't put it out of bounds
				if(e.getKeyCode() == KeyEvent.VK_A && gameStarted == true && avatarHolder.getAvatar().getX() - 10 >= 0)
				{
					avatarHolder.getAvatar().setLocation(avatarHolder.getAvatar().getX() - 10, avatarHolder.getAvatar().getY());
					repaint();
				}
				// Moves avatar down if s is pressed, the game is started, and moving the avatar won't put it out of bounds
				if(e.getKeyCode() == KeyEvent.VK_S && gameStarted == true && avatarHolder.getAvatar().getY() + avatarHolder.getAvatar().getHeight()+ 10 <= getHeight())
				{
					avatarHolder.getAvatar().setLocation(avatarHolder.getAvatar().getX(), avatarHolder.getAvatar().getY() + 10);
					repaint();
				}
				// Moves avatar right if d is pressed, the game is started, and moving the avatar won't put it out of bounds
				if(e.getKeyCode() == KeyEvent.VK_D && gameStarted == true && avatarHolder.getAvatar().getX() + avatarHolder.getAvatar().getWidth() + 10 <= getWidth())
				{
					avatarHolder.getAvatar().setLocation(avatarHolder.getAvatar().getX() + 10, avatarHolder.getAvatar().getY());
					repaint();
				}
			}
		});
        
        this.setFocusable(true);
        
        // Creates timer
        timer = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// When timer ticks we add to the time variable
				// Note the time is not in seconds
				time = time + 1;
				for(int i = 0; i < enemies.size(); i++)
				{
					// Move the enemies and checks for intersection, if there is intersection remove the enemy and 1 life
					enemies.get(i).move();
					if(avatarHolder.getAvatar().intersects(enemies.get(i).getHurtBox()))
					{
						enemies.remove(i);
						lives = lives - 1;
						app.changeLives();
					}
					// If lives is 0 or less the user lost, the baord is cleared, and a losing message is printed
					if(lives <= 0)
					{
						app.hidePauseButton();
						enemies.clear();
						repaint();
						gameMessage = "Game Over, You Lose";
						timer.stop();
					}
					
					//If the user survived the level this code sets up the next level
					if(time == 200 && lives > 0)
					{
						time = 0;
						level = level + 1;
						touchingAvatar = false;
						if(level != 4)
						{
							timer.restart();
							timer.stop();
							enemies.clear();
							startGame();
							avatarHolder.getAvatar().setLocation(0, 300);
							timer.start();
						}
					}
					
					// If the user wins this code clears the board and prints a winning message
					if(level == 4)
					{
						app.hidePauseButton();
						enemies.clear();
						repaint();
						gameMessage = "Game Over, You Win";
						timer.stop();
					}
				}
				repaint();
			}
		});
        
        // Starts the timer
		timer.start();
    }
	
	// Changes the current avatar in the avatar holder and repaints the board
	public void changeAvatar(int avatarIndex)
    {
    	if(avatarIndex == 1)
    	{
    		avatarHolder.setAvatar(avatar1);
    	}
    	else if(avatarIndex == 2)
    	{
    		avatarHolder.setAvatar(avatar2);
    	}
    	else
    	{
    		avatarHolder.setAvatar(avatar3);
    	}
    	repaint();
    }
	
	// Gets the paused value
	public boolean getPaused()
	{
		return paused;
	}
	
	// Sets the paused value to the opposite
	public void setPaused()
	{
		paused = !paused;
	}
	
	// Gets lives
	public int getLives()
	{
		return lives;
	}
	
	// Gets the current difficulty
	public int getDifficulty()
	{
		return difficulty;
	}  
	
	// Sets the difficulty using numEnemies and difficulty (this changes the speed of enemies)
	public void setDifficulty(int difficulty, int numEnemies)
	{
		this.difficulty = difficulty;
		this.numEnemies = numEnemies;
	}
	
	// Starts the game
	public void startGame()
	{
		instructions.setVisible(false);
		timer.start();
		gameStarted = true;
		app.hideAvatarPanel();
		app.hideDifficultyButtons();
		
		if(gameStarted)
        {
			// Adds enemies to an array list
			for(int i = 0; i < numEnemies; i++)
            {
            	Enemy enemy = new Enemy(GameBoard.this);
            	enemies.add(enemy);
            }
        }		
		repaint();
	}
	
	// Sets all values back to default, this method is used to bring the user back to the main menu when pressing quit
	public void setToDefault()
	{
		app.showPauseButton();
		instructions.setVisible(true);
		enemies.clear();
		time = 0;
		level = 1;
		lives = 3;
		gameStarted = false;
		paused = false;
		timer.restart();
		timer.stop();
		avatarHolder.getAvatar().setLocation(0, 300);
		avatarHolder.setAvatar(avatar1);
		gameMessage = "";
		repaint();
	}
	
	// Pauses the game
	public void pause(boolean paused)
	{
		if(paused == true)
		{
			timer.stop();
		}
		else
		{
			timer.start();
		}
	}

	// Paints everything that should be on the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D brush = (Graphics2D) g;
        
        // Paints the current avatar
        if(level != 4 && lives > 0)
        {
        	avatarHolder.getAvatar().paint(brush);
        }
        
        // Paints enemies
        for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).paint(brush);
        }
        
        // Used for painting a win or loss message
        Font font = new Font("arial", Font.BOLD, 40);
        brush.setFont(font);
        FontMetrics metrics = getFontMetrics(font);
        int width = metrics.stringWidth(gameMessage);
        int height = metrics.getHeight();
        brush.drawString(gameMessage, getWidth() / 2 - width / 2, getHeight() / 2 - height / 2);
    }
}