package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import entity.Player;

public class GameWindow extends JPanel implements Runnable{
	final int originalTileSize = 16; //16x16 pixels
	final int scale = 2; //scale up the tiles for higher resolution screens
	
	public final int tileSize = originalTileSize * scale;
	final int maximumWindowColumns = 36;
	final int maximumWindowRows = 36;
	final int screenWidth = tileSize * maximumWindowColumns;
	final int screenHeight = tileSize * maximumWindowRows;
	
	
	int FPS = 165; //Set FPS for the game
	KeyHandler buttonPressed = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,buttonPressed);
	
	//setPlayer's default position;
	int realFPS;
	double UpScale, DownScale, LeftScale, RightScale;
	int PlayerX = screenWidth/2;
	int PlayerY = screenHeight/2;
	int MovementSpeed = 4;
	float PlayerMovementScale = 1;
	int PlayerSpeed = (int)(MovementSpeed * PlayerMovementScale);
	
	public GameWindow() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(buttonPressed);
		this.setFocusable(true); //Allows window to be focused in order to read keyboard inputs
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double updateInterval = 1000000000/FPS;
		double nextUpdate = System.nanoTime() + updateInterval;
		long lastTime = System.nanoTime();
		long currentTime;
		int drawFPS = 0;
		long timer = 0;
		
		while(gameThread!=null) {
			
			currentTime = System.nanoTime();
			
			timer += (currentTime - lastTime);
			
			update();
			
			repaint();
				
			drawFPS++;
			
			try {
				double remainingTime = nextUpdate - System.nanoTime();
				remainingTime = remainingTime / 1000000; //converting to nanoseconds for sleep function
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextUpdate += updateInterval;
				lastTime = currentTime;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (timer >= 1000000000) {
				realFPS = drawFPS;
				drawFPS = 0;
				timer = 0;
			}
			
			calculateAcceleration(buttonPressed);
			
		}
		// TODO Auto-generated method stub
		
	}
	public void update() {
		//
		player.update(UpScale,DownScale,LeftScale,RightScale);
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.setColor(Color.white);
		
		g2.setTransform(AffineTransform.getScaleInstance(2.5, 2.5));
		
		g2.drawString("FPS:" + realFPS, 3, 13);
		
		g2.drawString("W: " + UpScale, 3, 33);
		
		g2.drawString("S: " + DownScale, 3, 53);
		
		g2.drawString("A: " + LeftScale, 3, 73);
		
		g2.drawString("D: " + RightScale, 3, 93);
		
		g2.dispose();
	}
	public void calculateAcceleration(KeyHandler buttonPressed) {
		
		double grip = player.getGrip();
		
		if(buttonPressed.up == true) {
			if(UpScale < 1) {
				UpScale += grip;
			}
			if(DownScale>0) {
				DownScale -= grip*1.5;
			}
		}
		else if(buttonPressed.up == false) {
			if(UpScale > 0) {
				UpScale -= grip;
			}
		}
		
		if(buttonPressed.down == true) {
			if(DownScale < 1) {
				DownScale += grip;
			}
			if(UpScale>0) {
				UpScale -= grip*1.5;
			}
		}
		else if(buttonPressed.down == false) {
			if(DownScale > 0) {
				DownScale -= grip;
			}
		}
		
		if(buttonPressed.left == true) {
			if(LeftScale < 1) {
				LeftScale += grip;
			}
			if(RightScale>0) {
				RightScale -= grip*1.5;
			}
		}
		else if(buttonPressed.left == false) {
			if(LeftScale > 0) {
				LeftScale -= grip;
			}
		}
		
		if(buttonPressed.right == true) {
			if(RightScale < 1) {
				RightScale += grip;
			}
			if(LeftScale > 0) {
				LeftScale -= grip*1.5;
			}
		}
		else if(buttonPressed.right == false) {
			if(RightScale > 0) {
				RightScale -= grip;
			}
		}
		
		//Limitations
		if(UpScale < 0) {UpScale = 0;} else if(UpScale>1) {UpScale = 1;}
		if(DownScale < 0) {DownScale = 0;} else if(DownScale>1) {DownScale = 1;}
		if(LeftScale < 0) {LeftScale = 0;} else if(LeftScale>1) {LeftScale = 1;}
		if(RightScale < 0) {RightScale = 0;} else if(RightScale>1) {RightScale = 1;}
	}
}
