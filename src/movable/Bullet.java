package movable;

import java.awt.Image;
import java.util.ArrayList;

import Explosion.Explosion;
import GameEvents.GameEvents;
import Sound.Sound;
import Wall.Wall;

public class Bullet extends movable
{
//	Sound sound1;
	boolean player2;
	Sound sound;
	float dir;
	
	Bullet(Image img, int speed, int x, int y, int angleDsix, int powerup, boolean player2,int multi)
	{
		this.img = img;
		this.speed = speed;
		height = img.getHeight(null);
		width =img.getWidth(null)/multi;
		this.x = x;
		this.y = y;
		this.show = true;
		this.angleDsix = angleDsix;
		this.powerup = powerup;
		this.player2=player2;
		sound=new Sound();
		sound.playF();
		
	}

	public void update(GameEvents gameEvents, ArrayList<Wall> walls, ArrayList<Wall> Dwalls, tank[] m, Explosion [] Exp)
	{
		if (show)
		{
			if(powerup==5)
			{
				dir=(float) Math.atan2(m[1].getY()-y, m[1].getX()-x);
				
			//if(m[1].getX()>x)
			//	{					
					y =(int) (y+ speed*Math.sin(dir));
					//System.out.println("Y increase "+ speed*Math.sin(dir));
				//	System.out.println("Y pos "+y);
					x =(int) (x+ speed*Math.cos(dir));
				//	System.out.println("X increase "+ speed*Math.cos(dir));
				//	System.out.println("X pos "+x);
			//	}

			}
			else
			{
			y -= 5 * speed*Math.sin(Math.toRadians(angleDsix*6));
			x += 5 * speed*Math.cos(Math.toRadians(angleDsix*6));
			}
			
		if (powerup == 3||powerup==5)
		{
			for (int i = 0; i<Dwalls.size(); i++)
			{
				if (Dwalls.get(i).collision(x, y, width, height))
				{
					addExp(Exp,false);
					Dwalls.get(i).reset();
					//this.reset();
					// show = true;
				} else
					gameEvents.setValue("");
			}
			for (int i = 0; i<walls.size(); i++)
			{
				if (walls.get(i).collision(x, y, width, height))
				{								
					addExp(Exp,true);
					reset();
				} else
					gameEvents.setValue("");					
			}
			if(player2)
			{
				if(m[0].collision(x, y, width, height))
				{
					if(powerup==5)
						gameEvents.setValue("dead");
					else
					gameEvents.setValue("beated");
					addExp(Exp,true);
					reset();
				}
		
			}
			else if(player2==false)
			{
				if(m[1].collision(x, y, width, height))
				{
					if(powerup==5)
						gameEvents.setValue("dead2");
					else
						gameEvents.setValue("beated2");
					addExp(Exp,true);
					reset();
				}
			}

		}
		else if (powerup == 2)
			{
				for (int i = 0; i<Dwalls.size(); i++)
				{
					if (Dwalls.get(i).collision(x, y, width, height))
					{
						addExp(Exp,false);
						Dwalls.get(i).reset();
						this.reset();
						// show = true;
					} else
						gameEvents.setValue("");
				}
				for (int i = 0; i<walls.size(); i++)
				{
					if (walls.get(i).collision(x, y, width, height))
					{								
						addExp(Exp,false);
						reset();
					} else
						gameEvents.setValue("");					
				}
				if(player2)
				{
					if(m[0].collision(x, y, width, height))
					{
						gameEvents.setValue("beated");
						addExp(Exp,false);
						reset();
					}
			
				}
				else if(player2==false)
				{
					if(m[1].collision(x, y, width, height))
					{
						gameEvents.setValue("beated2");
						addExp(Exp,false);
						reset();
					}
				}

			} else if(powerup<=1)
			{				
				for (int i = 0; i<Dwalls.size(); i++)
				{
					if (Dwalls.get(i).collision(x, y, width, height))
					{								
						addExp(Exp,false);
						reset();
					} else
						gameEvents.setValue("");
					
				}
				for (int i = 0; i<walls.size(); i++)
				{
					if (walls.get(i).collision(x, y, width, height))
					{								
						addExp(Exp,false);
						reset();
					} else
						gameEvents.setValue("");					
				}
				if(player2)
				{
					if(m[0].collision(x, y, width, height))
					{
						gameEvents.setValue("beated");
						addExp(Exp,false);
						reset();
					}
			
				}
				else if(player2==false)
				{
					if(m[1].collision(x, y, width, height))
					{
						gameEvents.setValue("beated2");
						addExp(Exp,false);
						reset();
					}
				}
			}
		}
		
		

	}
	
	public void addExp(Explosion [] Exp, boolean bigExp)
	{
		for (int j=0; j<20; j++)
		{
			if(Exp[j]==null)
			{
				Exp[j]=new Explosion(x,y,bigExp);

				if(j==19)
					Exp[0]=null;
				else
					Exp[j+1]=null;
				break;
			}							
		}
		
	}
	

	public void reset()
	{
		show = false;
		x = -150;
		y = -150;
	}
}
