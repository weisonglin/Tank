package Wall;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Wall
{
	Rectangle bbox;
	Image image;
	int x,y,width,height;
	boolean solid;
	int tempX, tempY;
	int time=1;
	public Wall(Image image, int x, int y, int width, int height, boolean solid)
	{
		this.image=image;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.solid=solid;
		
	}
	
	public void update()
	{
		
	}
	public int getSize()
	{
		return width;
	}
	public int getTempX()
	{
		return tempX;
	}
	public int getTempY()
	{
		return tempY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public void reset()
	{
		tempX=x;
		tempY=y;
		x=-50;
		y=-50;
		time=-500;
		
	}
	
	public boolean collision(int x, int y, int w, int h)
	{
		bbox = new Rectangle(this.x, this.y, this.width, this.height);
		Rectangle otherBBox = new Rectangle(x, y, w, h);
		if (this.bbox.intersects(otherBBox))
		{
			return true;
		}
		return false;
	}
	
	public void draw(ImageObserver obs, Graphics2D g2)
	{
		g2.drawImage(image, x, y, width, height, obs);
		time++;

	}
	public void restore()
	{
		x=tempX;
		y=tempY;
	}
	public int getTime()
	{
		return time;
	}
}
