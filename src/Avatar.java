// Jacob Conrad and Jack Handy, Final Project, May 5, 2021, The Avatar class.
// This class creates a BufferedImage to act an Avatar/ the protagonist in the game.

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Avatar {
	
	private int x,y, width, height;
	private BufferedImage image;
	private Rectangle hurtBox = new Rectangle(0,0);
	
	// Avatar constructor
	public Avatar(int avatarIndex)
	{
		 try {
			 	if(avatarIndex == 1)
			 	{
			 		image = ImageIO.read(new File("images/Deku.png"));
			 	}
			 	else if(avatarIndex == 2)
			 	{
			 		image = ImageIO.read(new File("images/Spy.png"));
			 	}
			 	else if(avatarIndex == 3)
			 	{
			 		image = ImageIO.read(new File("images/Apex.png"));
			 	}     	
	         }
	        catch(IOException e) {
	        	e.printStackTrace();
	        }
	        x = 0;
	        y = 300;
	        width = 130;
	        height = 130;
	        hurtBox.setLocation(x, y);
	        hurtBox.setSize(width, height);
	}
	
	// Sets x and y values and the location of the Avatar's hurtBox
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		hurtBox.setLocation(x, y);
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
	
	
	// Gets width
	public int getWidth()
	{
		return width;
	}
	
	
	// Gets height
	public int getHeight()
	{
		return height;
	}
		
	// Checks if the Avatar contains a point input
	public boolean contains(Point p)
	{
		if(x <= p.getX() && x + width >= p.getX() && y <= p.getY() && y + height >= p.getY())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Checks if an Enemy's hurtBox intersects the Avatar's hurtBox
	public boolean intersects(Rectangle enemyHurtBox)
	{
		return hurtBox.intersects(enemyHurtBox);
	}

	// Draws the Avatar
	public void paint(Graphics2D brush) {
		// TODO Auto-generated method stub
		brush.drawImage(image, x, y, width, height, null);
	}
}