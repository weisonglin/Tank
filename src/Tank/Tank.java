package Tank;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import javax.imageio.ImageIO;




















import Sound.Sound;
import Wall.Wall;
import movable.PowerUp;
import movable.tank;
import Explosion.Explosion;
import GameEvents.GameEvents;
import KeyControl.KeyControl;

import java.io.File;

public class Tank extends JApplet implements Runnable
{
	private Thread thread;
	private BufferedImage bimg,img,img2,img11,img22;
	private Graphics2D g2;
	private Image ground, wall,tank1,tank2 ,Dwall,powerup;
	private int w=2400,h=2400;
	private ArrayList<Integer> value;
	private GameEvents gameEvents;
	private tank[] m;
	private ArrayList<Wall> walls;
	private ArrayList<Integer> Dvalue;
	private ArrayList<Wall> Dwalls;
	private Explosion [] Exp;
	private Sound sound;
	private PowerUp[] p;
	private Random gen;
	public static boolean boom1, boom2;
	private int finalscore1=-1, finalscore2=-1;
	
	public void init()
	{
		
		setFocusable(true);
		setBackground(Color.black);
		String filename ="wallPos.txt";
		String filename2="DwallPos.txt";
		File file= new File(filename);
		File file2=new File(filename2);
		value=new ArrayList<Integer>();
		Dvalue=new ArrayList<Integer>();
		walls=new ArrayList<Wall>();
		Dwalls=new ArrayList<Wall>();
		m = new tank[2];
		sound=new Sound();
		Exp=new Explosion[21];
		p= new PowerUp[8];
		boom1=false;
		boom2=false;
		
		
		try
		{
			powerup=ImageIO.read(new File("ResourcesTank/Chapter11/Weapon_strip4.png"));
			tank2=ImageIO.read(new File("ResourcesTank/Chapter11/Tank_red_base_strip60.png"));
			tank1=ImageIO.read(new File("ResourcesTank/Chapter11/Tank_blue_base_strip60.png"));
			ground = ImageIO.read(new File("ResourcesTank/Chapter11/Background.png"));
			wall = ImageIO.read(new File("ResourcesTank/Chapter11/Blue_wall2.png"));
			Dwall = ImageIO.read(new File("ResourcesTank/Chapter11/Blue_wall1.png"));
			Scanner inputStream =new Scanner(file);
			while(inputStream.hasNext())
			{
				int data= inputStream.nextInt();
				value.add(data);					
			}
			inputStream.close();	
			Scanner inputStream2 =new Scanner(file2);
			while(inputStream2.hasNext())
			{
				int data= inputStream2.nextInt();
				Dvalue.add(data);					
			}
			inputStream2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		gen=new Random();
		m[0]=new tank(tank1, 400, 400, 1,false, null,walls, Dwalls, m);
		m[1]=new tank(tank2,2000,400,1,true,null,walls, Dwalls,m);
		gameEvents = new GameEvents();
		KeyControl key = new KeyControl(gameEvents);
		addKeyListener(key);
		gameEvents.addObserver(m[0]);
		gameEvents.addObserver(m[1]);
		for(int i=0; i<4;i++)
		{
			//int X=gen.nextInt(100);
			p[i]=new PowerUp(powerup,400,400,1,gen);
		}
		p[4]=new PowerUp(powerup,400,400,2,gen);
		p[5]=new PowerUp(powerup,400,400,2,gen);
		p[6]=new PowerUp(powerup,400,400,3,gen);
		p[7]=new PowerUp(powerup,400,400,4,gen);
		
		
		int widthW=wall.getWidth(this);
		int heightW=wall.getHeight(this);
		
		int numberXW =(int)(w/widthW);
		int numberYW=(int)(h/heightW);
		int pos=0;
		//int pos2=0;
		
		for (int i = 0; i < numberYW; i++)
		{
			for (int j = 0; j <numberXW; j++)
			{
				if(value.get(pos)==1)
					walls.add(new Wall(wall, j * widthW, i * heightW, widthW, heightW, true));
				if(Dvalue.get(pos)==1)
					Dwalls.add(new Wall(Dwall, j * widthW, i * heightW, widthW, heightW, false));
				pos++;
			}
		}
		
		
	}
	public void drawBackGroundImage()
	{
		//int pos=0;
		int Width = ground.getWidth(this);
		int Height = ground.getHeight(this);

		int NumberX = (int) (w / Width);
		int NumberY = (int) (h / Height);

		for (int i = 0; i < NumberY; i++)
		{
			for (int j = 0; j < NumberX; j++)
			{
				g2.drawImage(ground, j * Width, i * Height, Width, Height, this);
			}
		}
		for(int i=0; i<walls.size();i++)
		{
			walls.get(i).draw(this, g2);
		}
		for(int i=0; i<Dwalls.size();i++)
		{
			Dwalls.get(i).draw(this, g2);
			if(Dwalls.get(i).getTime()==0)
			{
				if(m[0].collision(Dwalls.get(i).getTempX(), Dwalls.get(i).getTempY(), Dwalls.get(i).getSize(), Dwalls.get(i).getSize())||m[1].collision(Dwalls.get(i).getTempX(), Dwalls.get(i).getTempY(), Dwalls.get(i).getSize(), Dwalls.get(i).getSize()))
				{
					Dwalls.get(i).reset();
				}
				else
					Dwalls.get(i).restore();
			}
		}
		/*
		int widthW=wall.getWidth(this);
		int heightW=wall.getHeight(this);
		
		int numberXW =(int)(w/widthW);
		int numberYW=(int)(h/heightW);
		int pos=0;
		
		for (int i = 0; i < numberYW; i++)
		{
			for (int j = 0; j <numberXW; j++)
			{
				if(value.get(pos)==1)
				walls.add(new Wall(wall, j * widthW, i * heightW, widthW, heightW, true));
				
				pos++;
			}
		}
		*/
		
		
	}
	
	public void drawDemo()
	{
		sound.playBack();
		for( int i=0;i<8;i++)
			p[i].update(gameEvents, m);
		for (int i = 0;i < 2; i++)
		{
			if(m[i]!=null)
			m[i].update_bullet(gameEvents, walls, Dwalls,m, Exp);
		}
		

		
		
		drawBackGroundImage();
		for(int i=0; i<8; i++)
		{
			p[i].draw(this, g2);
			
		}
		
		if(m[0]!=null)
		m[0].draw(this, g2);
		if(m[1]!=null)
		m[1].draw(this, g2);
		for(int i=0;Exp[i]!=null&&i<20;i++)
			Exp[i].draw(this, g2);
		for (int i = 0;i < 2; i++)
		{
			if(m[i]!=null)
			m[i].draw_heathBar(this, g2);
		}
		//p[7].draw(this, g2);
		
		
		if(boom1||boom2)
		{
			
			if(boom1&&finalscore1==-1)
			{
				finalscore1=m[0].getNumOfkill()*10+m[0].getBlood()*10;
				finalscore2=m[1].getNumOfkill()*10+m[1].getBlood()*10+20;
				for (int j = 0; j < 20; j++)
				{
					if (Exp[j] == null)
					{
						Exp[j] = new Explosion(m[0].getX(), m[0].getY(), true);
						if (j == 19)
							Exp[0] = null;
						else
							Exp[j + 1] = null;
						break;
					}
				}
			}
			else if(boom2&&finalscore2==-1)
			{
				finalscore2=m[1].getNumOfkill()*10+m[1].getBlood()*10;
				finalscore1=m[0].getNumOfkill()*10+m[0].getBlood()*10+20;
				for (int j = 0; j < 20; j++)
				{
					if (Exp[j] == null)
					{
						Exp[j] = new Explosion(m[1].getTempX(), m[1].getTempY(), true);
						if (j == 19)
							Exp[0] = null;
						else
							Exp[j + 1] = null;
						break;
					}
				}
				//System.out.println("haha");
			}
				
			Image gameover, score;
			try{
				int i;
				if(boom2)
					i=1;
				else 
					i=0;
				gameover = ImageIO.read(new File("Resources/gameOver.png"));
				//g2.drawImage(gameover, m[0].getTempX()-200, m[0].getTempY()-200, this);
				g2.drawImage(gameover, m[i].getTempX()-100, m[i].getTempY()-300, this);
				score = ImageIO.read(new File("Resources/bottom.png"));
				g2.drawImage(score, m[i].getTempX()-350, m[i].getTempY()-175, this);
				g2.setColor(Color.yellow);
				g2.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
				g2.drawString("Player1  ",m[i].getTempX()-270,m[i].getTempY()-150);
				g2.drawString(String.valueOf(finalscore1), m[i].getTempX()-50, m[i].getTempY()-150);
				if(finalscore1>50)
					g2.drawString("Well Done!!",m[i].getTempX()-150,m[i].getTempY()-125);
				else
					g2.drawString("Ask Weisong if he will teach you!!",m[i].getTempX()-150,m[i].getTempY()-125);
				
				g2.drawImage(score, m[i].getTempX()-350, m[i].getTempY()-100, this);
				g2.drawString("Player2  ",m[i].getTempX()-270,m[i].getTempY()-75);
				g2.drawString(String.valueOf(finalscore2),  m[i].getTempX()-50, m[i].getTempY()-75);
				if(finalscore2>50)
					g2.drawString("Well Done!!",m[i].getTempX()-150,m[i].getTempY()-50);
				else
					g2.drawString("Ask Weisong if he will teach you!!",m[i].getTempX()-150,m[i].getTempY()-50);
			
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	public void paint(Graphics g)
	{
		if (bimg == null)
		{
			Dimension windowSize = getSize();
			bimg = (BufferedImage) createImage(w,
					h);
		//	BufferedImage img=bimg.getSubimage(1000, 0, 600, 700);
			g2 = bimg.createGraphics();
			//System.out.println("haha");
			
		}
		drawDemo();
		if(boom1==false)
		{
			img=bimg.getSubimage(m[0].getX()-300, m[0].getY()-350, 595, 475);
			img11=bimg.getSubimage(m[0].getX()-300, m[0].getY()+125, 475, 225);
		}
		if(boom2==false)
		{
			img2=bimg.getSubimage(m[1].getX()-300, m[1].getY()-350, 595, 475);
			img22=bimg.getSubimage(m[1].getX()-180, m[1].getY()+125, 475, 225);
		}
		g.drawImage(img, 0, 0, this);
		g.drawImage(img11, 0, 475,this);

		g.drawImage(img2, 605,0,this);
		g.drawImage(img22, 725, 475,this);
		g.drawImage(bimg, 480, 480, 720, 700, 0, 0, 2400, 2400, this);
		
		
	}
	
	public void start()
	{
		thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}
	
	public void run()
	{

		Thread me = Thread.currentThread();
		while (thread == me)
		{
			repaint();
			try
			{
				thread.sleep(5);
			} catch (InterruptedException e)
			{
				break;
			}

		}
	}
	
	
	public static void main(String argv[])
	{
		final Tank demo = new Tank();
		demo.init();
		JFrame f = new JFrame("Scrolling Shooter");
		f.addWindowListener(new WindowAdapter()
		{
		});
		f.getContentPane().add("Center", demo);
		f.pack();
		f.setSize(new Dimension(1200, 700));
		f.setVisible(true);
		f.setResizable(false);

		demo.start();
	
	}
}
