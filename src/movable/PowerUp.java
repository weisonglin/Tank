package movable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

import GameEvents.GameEvents;
//import Sound.Sound;

public class PowerUp
{
	Image img;
	int height, width,x,y,power;
	boolean show;
	Random gen;
	Rectangle bbox;
//	Sound sound;
	//int R;
	
	public PowerUp(Image img, int x, int y,int power,Random gen)
	{
		this.gen=gen;
		//int R=gen.nextInt(100);
		//int Ry=gen.nextInt(100);
		this.img=img;
		this.power=power;
		height = 64;
		width = 64;
		this.x=x+gen.nextInt(500);
		this.y=y+gen.nextInt(500);
		show=true;
		//sound=new Sound();
				
	}
	/*
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
	*/
	public void update(GameEvents gameEvents, tank[] m)
	{
		//y+=speed;
		for (int i = 0; i < 2; i++)
		{
			if(m[i]!=null)
			{
			if (m[i].collision(x, y, width, height))
			{
				show = false;
				// You need to remove this one and increase score etc
				if(power==1)
				{
				if(i==0)
					gameEvents.setValue("1Powerup");
				else
					gameEvents.setValue("1Powerup2");
				gameEvents.setValue("");
				show=true;
				this.reset();
				}
				else if(power==2)
				{
					if(i==0)
						gameEvents.setValue("2Powerup");
					else
						gameEvents.setValue("2Powerup2");
					gameEvents.setValue("");
					show=true;
					this.reset();
				}
				else if(power==3)
				{
					if(i==0)
						gameEvents.setValue("3Powerup");
					else
						gameEvents.setValue("3Powerup2");
					gameEvents.setValue("");
					show=true;
					this.reset();
				}
				else if(power==4)
				{
					if(i==0)
						gameEvents.setValue("4Powerup");
					else
						gameEvents.setValue("4Powerup2");
					gameEvents.setValue("");
					show=true;
					this.reset();
				}
				//sound.playUg();	
				
			} else
				gameEvents.setValue("");
			}
		}
//		if (y > 500)
//			this.reset();

	}
	
	public void draw(ImageObserver obs, Graphics2D g2)
	{
		g2.drawImage(img, x, y, x+width, y+height, (power-1)*width, 0, power*width, height, obs);
	}
	
	public void reset()
	{	
		this.x =x+ gen.nextInt(500);
		this.y =y+ gen.nextInt(500);
	}
	
}
