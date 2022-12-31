package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GameWindow extends JPanel implements Runnable{
	final int originalTileSize = 16; //16x16 pixels
	final int scale = 2; //scale up the tiles for higher resolution screens
	
	public final int tileSize = originalTileSize * scale;
	public final int maximumWindowColumns = 32;
	public final int maximumWindowRows = 24;
	public final int screenWidth = tileSize * maximumWindowColumns;
	public final int screenHeight = tileSize * maximumWindowRows;
	public final int maxWorldColumns = 160; //32
	public final int maxWorldRows = 72;
	public final int worldWidth = tileSize*maxWorldColumns;
	public final int worldHeight = tileSize*maxWorldRows;
	
	
	int FPS = 165; //Set FPS for the game
	KeyHandler buttonPressed = new KeyHandler();
	TileManager tiles = new TileManager(this);
	Thread gameThread;
	public checkCollision collisionChecker = new checkCollision(this);
	public Player player = new Player(this,buttonPressed);
	
	//setPlayer's default position;
	int realFPS;
	
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
			
			player.calculateAcceleration(buttonPressed);
			
		}
		// TODO Auto-generated method stub
		
	}
	public void update() {
		//
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tiles.draw(g2);
		
		player.draw(g2);
		
		g2.setColor(Color.white);
		
		g2.setTransform(AffineTransform.getScaleInstance(2.5, 2.5));
		
		g2.drawString("FPS:" + realFPS, 3, 13);
		
		g2.dispose();
	}
	
}
