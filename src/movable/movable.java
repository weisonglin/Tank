package movable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

public abstract class movable
{
	Image img;

	int x, y, width, height, speed;
	Random gen;
	boolean show;
	Rectangle bbox;
	boolean boom;
	boolean goodSide;
	int powerup=0;
	int angleDsix=0;

	// protected Graphics2D g2;

	/*
	 * public void set_graphic(Graphics2D g) { g2 = g; }
	 */

	public void draw(ImageObserver obs, Graphics2D g2)
	{
		if (show)
		{
			g2.drawImage(img, x, y, x+width, y+height, angleDsix*width, 0, (angleDsix+1)*width, height, obs);
		}
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
	// abstract public void update();

}