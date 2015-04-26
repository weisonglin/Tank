package movable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

//import Sound.Sound;
import Wall.Wall;
import Explosion.Explosion;
import GameEvents.GameEvents;
import HealthBar.HealthBar;

//import Explosion.Explosion;
//import GameEvents.*;
//import HealthBar.HealthBar;

//import HealthBar.HealthBar;

public class tank extends movable implements Observer
{
	
	private Bullet[] b1;
	private int i = -1;
	private HealthBar h1;
	//private MyPlane[] hahaha;
	private boolean player2;
	private int NumOfkill = 4;
	private boolean unbeat = false;
	private int blood = 4;
	//private Explosion[] Explo;
	private ArrayList<Wall> walls,Dwalls;
	private tank[] enemy;
	private int tempX,tempY;
	//private Sound sound;

	public tank(Image img, int x, int y, int speed, boolean player2,
			Explosion[] Exp, ArrayList<Wall> walls, ArrayList<Wall>Dwalls,tank[] enemy)
	{
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = 64;
		this.height = 64;
		this.boom = false;
		this.show = true;
		b1 = new Bullet[20];
		this.goodSide = true;
		h1 = new HealthBar(player2);
		this.player2 = player2;
	
		this.walls=walls;
		this.Dwalls=Dwalls;
		this.enemy=enemy;
		//sound=new Sound();
		
		//System.out.println(width+"   "+height);

	}

	/*
	 * public void set_graphic(Graphics2D g) { super.set_graphic(g); for (int
	 * i=0; i<10; i++) b1[i].set_graphic(g); }
	 */
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getNumOfkill()
	{
		return NumOfkill;
	}
	public int getBlood()
	{
		return blood;
	}

	public void draw(ImageObserver obs, Graphics2D g2)
	{
		// h1.draw(obs, g2);
		if (unbeat == true)
			draw_shield(obs, g2);
		if(powerup==0)
		{
			if(player2)
			{
				Image tank2_1 = null;
				try
				{
					tank2_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_red_base_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank2_1;
			}
			else
			{
				Image tank_1 = null;
				try
				{
					tank_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_blue_base_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank_1;
			}
		}
		else if(powerup==1)
		{
			if(player2)
			{
				Image tank2_1 = null;
				try
				{
					tank2_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_red_light_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank2_1;
			}
			else
			{
				Image tank_1 = null;
				try
				{
					tank_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_blue_light_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank_1;
			}
		}
		else if(powerup==2)
		{
			if(player2)
			{
				Image tank2_1 = null;
				try
				{
					tank2_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_red_basic_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank2_1;
			}
			else
			{
				Image tank_1 = null;
				try
				{
					tank_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_blue_basic_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank_1;
			}
		}
		else if(powerup==3)
		{
			if(player2)
			{
				Image tank2_1 = null;
				try
				{
					tank2_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_red_heavy_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank2_1;
			}
			else
			{
				Image tank_1 = null;
				try
				{
					tank_1 = ImageIO.read(new File(
							"ResourcesTank/Chapter11/Tank_blue_heavy_strip60.png"));
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				this.img=tank_1;
			}
		}
		super.draw(obs, g2);
		for (int i = 0; b1[i] != null && i < 19; i++)
		{
			b1[i].draw(obs, g2);
		}

	}
	
	
	

	public void draw_shield(ImageObserver obs, Graphics2D g2)
	{
		Image shield = null;
		try
		{
			shield = ImageIO.read(new File("Resources/haha.png"));

		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		g2.drawImage(shield, x - 10, y - 10, obs);
	}
	
	

	public void draw_heathBar(ImageObserver obs, Graphics2D g2)
	{
		h1.draw(obs, g2,x, y);
	}
	

	public void update_bullet(GameEvents gameEvents, ArrayList<Wall> walls, ArrayList<Wall> Dwalls, tank[] enemy, Explosion[] Exp)
	{
		for (int i = 0; b1[i] != null && i < 19; i++)
		{
			b1[i].update(gameEvents, walls, Dwalls,enemy, Exp);
		}
	}
	public int getTempX()
	{
		return tempX;
	}
	public int getTempY()
	{
		return tempY;
	}
	
	
	
	public void check_dead(boolean player2)
	{
		if (blood == 0)
		{
			if(player2)
			{
				Tank.Tank.boom2=true;
				tempX=x;
				tempY=y;
				x=-500;
				y=-500;
			}
			else
			{
				Tank.Tank.boom1=true;
				tempX=x;
				tempY=y;
				x=-500;
				y=-500;
			}
			/*
			Explo=new Explosion[20];
			for (int j = 0; j < 20; j++)
			{
				if (Explo[j] == null)
				{
					Explo[j] = new Explosion(x, y, true);
					if (j == 19)
						Explo[0] = null;
					else
						Explo[j + 1] = null;
					break;
				}
			}
			*/
			
		}
	}
	
	
	public void checkColiW()
	{
		if(player2)
		{
			if(enemy[0].collision(x, y, width, height))
			{
				y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
				x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
			}
			else
			{
				for(int i=0; i<walls.size();i++)				
				{
					if(walls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}
				for(int i=0; i<Dwalls.size();i++)
				{
					if(Dwalls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}	
			}
		}
		else
		{
			if(enemy[1].collision(x, y, width, height))
			{
				y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
				x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
			}
			else
			{
				for(int i=0; i<walls.size();i++)				
				{
					if(walls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}
				for(int i=0; i<Dwalls.size();i++)
				{
					if(Dwalls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y += 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x -= 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}	
			}
		}
	
	}
	public void checkColiS()
	{
		if(player2)
		{
			if(enemy[0].collision(x, y, width, height))
			{
				y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
				x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
			}
			else
			{
				for(int i=0; i<walls.size();i++)				
				{
					if(walls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}
				for(int i=0; i<Dwalls.size();i++)
				{
					if(Dwalls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}	
			}
		}
		else
		{
			if(enemy[1].collision(x, y, width, height))
			{
				y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
				x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
			}
			else
			{
				for(int i=0; i<walls.size();i++)				
				{
					if(walls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}
				for(int i=0; i<Dwalls.size();i++)
				{
					if(Dwalls.get(i).collision(x, y, width, height))
					{
						//System.out.println("hhahaha");
						y -= 15 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x += 15 * speed*Math.cos(Math.toRadians(angleDsix*6));
						break;
					}								
				}	
			}
		}
	}

	public void update(Observable obj, Object arg)
	{
		if (player2 == false)
		{
			GameEvents ge = (GameEvents) arg;

			if (ge.type == 1)
			{
				KeyEvent e = (KeyEvent) ge.event;
				switch (e.getKeyChar())
				{
				case 'a':
					if(angleDsix==0)
						angleDsix=59;
					else
						angleDsix -=  speed;
					break;
				case 'd':
					if(angleDsix==59)
						angleDsix=0;
					else
						angleDsix +=  speed;
					break;
				case 'w':
					
						y -= 5 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x += 5 * speed*Math.cos(Math.toRadians(angleDsix*6));
						checkColiW();										
					break;
					
				case 's':
						y += 5 * speed*Math.sin(Math.toRadians(angleDsix*6));
						x -= 5 * speed*Math.cos(Math.toRadians(angleDsix*6));
						checkColiS();
					break;
				case 'o':
					if (unbeat == false)
						unbeat = true;
					else
						unbeat = false;
					
				case 'l':
					Image bullet1 = null;
					try
					{
						bullet1 = ImageIO.read(new File(
								"Resources/weisonghahaha.png"));
					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
					
					i += 1;
					if (i == 19)
						i = 0;
					b1[i] = new Bullet(bullet1, 5, x, y ,
							0, 5, player2,1);
					break;
					

				default:
					if (e.getKeyChar() == ' ')
					{
						//sound.playF();
						System.out.println("Fire");
						if (powerup == 3)
						{							
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_heavy_strip60.png"));
							} catch (IOException e1)
							{
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y +20,
									angleDsix, powerup, player2,60);
						}
						else if (powerup == 2)
						{
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_basic_strip60.png"));
							} catch (IOException e1)
							{
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y +20,
									angleDsix, powerup, player2,60);
						} else if(powerup==1)
						{
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_light_strip60.png"));
							} catch (IOException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y+20, angleDsix,
									powerup, player2,60);
									
						}
						else
							System.out.println("no bullet");
					} 
				}
			}

			else if (ge.type == 2)
			{
				String msg = (String) ge.event;
				if (msg.equals("beat"))
				{
					NumOfkill++;
					System.out.println("You hit the Enemy, player1 score is "
							+ NumOfkill);

				} else if (msg.equals("beated"))
				{
					if (unbeat == false)
					{
						h1.reduceBlood();
						blood--;
						NumOfkill--;
						check_dead(player2);
						System.out
								.println("You get hited by the Enemy! Reduce Health");
					}
				} else if (msg.equals("1Powerup"))
				{
					powerup = 1;
					//i=0;
				}
				else if (msg.equals("2Powerup"))
				{
					powerup = 2;
					//i=0;
				}
				else if (msg.equals("3Powerup"))
				{
					powerup = 3;
					//i=0;
				}
				else if (msg.equals("4Powerup"))
					h1.addBlood();
				

			}

		} 
		
		
		else if (player2 == true)
		{
			GameEvents ge = (GameEvents) arg;

			if (ge.type == 1)
			{
				KeyEvent e = (KeyEvent) ge.event;
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_LEFT:
					if(angleDsix==0)
						angleDsix=59;
					else
						angleDsix -=  speed;
					break;
				case KeyEvent.VK_RIGHT:
					if(angleDsix==59)
						angleDsix=0;
					else
						angleDsix +=  speed;
					break;
				case KeyEvent.VK_UP:
					y -= 5 * speed*Math.sin(Math.toRadians(angleDsix*6));
					x += 5 * speed*Math.cos(Math.toRadians(angleDsix*6));
					checkColiW();
					break;
				case KeyEvent.VK_DOWN:
					y += 5 * speed*Math.sin(Math.toRadians(angleDsix*6));
					x -= 5 * speed*Math.cos(Math.toRadians(angleDsix*6));
					checkColiS();
					break;
				default:
					if (e.getKeyChar() == '0')
					{
						//sound.playF();
						System.out.println("Fire!");
						if (powerup == 3)
						{
							
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_heavy_strip60.png"));
							} catch (IOException e1)
							{
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y +20,
									angleDsix, powerup, player2,60);
						}
						else if (powerup == 2)
						{
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_basic_strip60.png"));
							} catch (IOException e1)
							{
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y +20,
									angleDsix, powerup, player2,60);
						} else if(powerup==1)
						{
							Image bullet = null;
							try
							{
								bullet = ImageIO.read(new File(
										"ResourcesTank/Chapter11/Shell_light_strip60.png"));
							} catch (IOException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							i += 1;
							if (i == 19)
							{
								powerup=0;
								i = 0;
							}
							b1[i] = new Bullet(bullet, 5, x+20, y+20, angleDsix,
									powerup, player2,60);
									
						}
						else
							System.out.println("no bullet");
					} else if (e.getKeyChar() == '/')
					{
						if (unbeat == false)
							unbeat = true;
						else
							unbeat = false;

					}
				}
			} else if (ge.type == 2)
			{
				String msg = (String) ge.event;
				if (msg.equals("Explosion2"))
				{
					if (unbeat == false)
					{
						h1.reduceBlood();
						blood--;
						//check_dead(player2);
						System.out.println("Explosion! Player2 Reduce Health");
					}

				} else if (msg.equals("beat2"))
				{
					NumOfkill++;
					System.out.println("You hit the Enemy, player2 score is "
							+ NumOfkill);

				} else if (msg.equals("beated2"))
				{
					if (unbeat == false)
					{
						h1.reduceBlood();
						blood--;
						NumOfkill--;
						check_dead(player2);
						System.out
								.println("You get hited by the Enemy! Player2 Reduce Health");
					}

				} else if (msg.equals("1Powerup2"))
				{
					powerup = 1;
					//i=0;
				}
				else if (msg.equals("2Powerup2"))
				{
					powerup = 2;
					//i=0;
				}
				else if (msg.equals("3Powerup2"))
				{
					powerup = 3;
					//i=0;
				}
				else if (msg.equals("4Powerup2"))
					h1.addBlood();
				else if (msg.equals("dead2"))
				{
					for(int i=0; i<4;i++)
					{
					if (unbeat == false)
					{
						h1.reduceBlood();
						blood--;
						NumOfkill--;
						check_dead(player2);
						System.out
								.println("hahaha");
					}
					}
				}

			}
			
		}
		

	}
}
