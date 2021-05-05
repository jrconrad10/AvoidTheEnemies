// Jacob Conrad and Jack Handy, Final Project, May 5, Enemy class.
// // This class creates a BufferedImage to act an Enemy/ the antagonist in the game.

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy {
	
	private int x,y, width, height;
	private BufferedImage image;
	private GameBoard gameBoard;
	private int xDirection, yDirection, xSpeed, ySpeed;
	private Rectangle hurtBox = new Rectangle();
	
	// Enemy constructor
	public Enemy(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
		
		x = gameBoard.getWidth() - 100;
		
		int randY = (int) (Math.random() * 3);
		
		if(randY == 0)
		{
			y = 100;
		}
		else if(randY == 1)
		{
			y = 350;
		}
		else
		{
			y = 600;
		}
		
	    width = 130;
	    height = 95;
	    xDirection = -1;
	    
	    int randYDirection = (int) (Math.random() * 2);
	    if(randYDirection == 0)
	    {
	    	yDirection = 1;
	    }
	    else
	    {
	    	yDirection = -1;
	    }	    
	    
	    hurtBox.setSize(width,height);
	    hurtBox.setLocation(x, y);
		
		try
		{
			image = ImageIO.read(new File("images/Ghost.png"));
	    }
	    catch(IOException e)
		{
	        e.printStackTrace();
	    }
		
		 xSpeed = (int) (Math.random() * 3) + gameBoard.getDifficulty();
		 ySpeed = (int) (Math.random() * 3) + gameBoard.getDifficulty();		
	}
	
	// Gets the hurtBox
	public Rectangle getHurtBox()
	{
		return hurtBox;
	}
	
	// Gets x value
	public int getX()
	{
		return x;
	}
	
	// Gets y value
	public int getY()
	{
		return y;
	}
	
	// Sets the x and y values as well as the hurtBox location
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		hurtBox.setLocation(x, y);
	}
	
	// Checks if the Enemy hits the right or left borders of the screen so it can bounce off
	public int xDirection()
	{
		if(x + width >= gameBoard.getWidth())
		{
			xDirection = -1;
		}
		else if(x <= 0)
		{
			xDirection = 1;
		}
		
		return xDirection;
	}
	
	// Checks if the Enemy hits the top or bottom borders of the screen so it can bounce off
	public int yDirection()
	{
		if(y + height >= gameBoard.getHeight())
		{
			yDirection = -1;
		}
		else if((y <= 0))
		{
			yDirection = 1;
		}
		
		return yDirection;
	}
	
	// Sets the x value
	public void setX(int x)
	{
		this.x = x;
	}
	
	// Sets the y value
	public void setY(int y)
	{
		this.y = y;
	}
	
	// Moves the enemy using current location, speed, and direction. Then checks if the enemy hit a border
	public void move()
	{
		setLocation(x + xSpeed*xDirection, y + ySpeed*yDirection);
		xDirection();
		yDirection();
	}
	// Draws the enemy
	public void paint(Graphics2D brush) {
		brush.drawImage(image, x, y, width, height, null);
	}
}