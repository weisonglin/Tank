package Explosion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Sound.Sound;

public class Explosion
{
	Sound sound1;
	int x;
	int y;
	int cout=0;
	boolean bigExp;
	Image explo1, explo2, explo3, explo4, explo5, explo6,Explo1, Explo2, Explo3, Explo4, Explo5, Explo6,Explo7;
	public Explosion(int x, int y, boolean bigExp )
	{
		try
		{
			explo1 = ImageIO.read(new File("Resources/explosion1_1.png"));
			explo2 = ImageIO.read(new File("Resources/explosion1_2.png"));
			explo3 = ImageIO.read(new File("Resources/explosion1_3.png"));
			explo4 = ImageIO.read(new File("Resources/explosion1_4.png"));
			explo5 = ImageIO.read(new File("Resources/explosion1_5.png"));
			explo6 = ImageIO.read(new File("Resources/explosion1_6.png"));
			Explo1 = ImageIO.read(new File("Resources/explosion2_1.png"));
			Explo2 = ImageIO.read(new File("Resources/explosion2_2.png"));
			Explo3 = ImageIO.read(new File("Resources/explosion2_3.png"));
			Explo4 = ImageIO.read(new File("Resources/explosion2_4.png"));
			Explo5 = ImageIO.read(new File("Resources/explosion2_5.png"));
			Explo6 = ImageIO.read(new File("Resources/explosion2_6.png"));
			Explo7 = ImageIO.read(new File("Resources/explosion2_7.png"));
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.x=x;
		this.y=y;
		this.bigExp=bigExp;
	 
		sound1=new Sound();

		
		if(bigExp)
		{			
			sound1.playBE();
		}
		else
			sound1.playSE();
			
			
	}
	

	
	public void draw(ImageObserver obs, Graphics2D g2)
	{
		
		if(bigExp==false)
		{
		if(cout<2)
			g2.drawImage(explo1, x, y, obs);
		else if(cout>=2&&cout<4)
			g2.drawImage(explo2, x, y, obs);
		else if(cout>=4&&cout<6)
			g2.drawImage(explo3, x, y, obs);
		else if(cout>=6&&cout<8)
			g2.drawImage(explo4, x, y, obs);
		else if(cout>=8&&cout<10)
			g2.drawImage(explo5, x, y, obs);
		else if(cout>=10&&cout<12)
			g2.drawImage(explo6, x, y, obs);
		}
		else
		{
		
		if(cout<2)
			g2.drawImage(Explo1, x, y, obs);
		else if(cout>=2&&cout<4)
			g2.drawImage(Explo2, x, y, obs);
		else if(cout>=4&&cout<6)
			g2.drawImage(Explo3, x, y, obs);
		else if(cout>=6&&cout<8)
			g2.drawImage(Explo4, x, y, obs);
		else if(cout>=8&&cout<10)
			g2.drawImage(Explo5, x, y, obs);
		else if(cout>=10&&cout<12)
			g2.drawImage(Explo6, x, y, obs);
		else if(cout>=12&&cout<14)
			g2.drawImage(Explo7, x, y, obs);
		}
			
		cout++;
	}
}
