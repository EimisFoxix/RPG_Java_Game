package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.Bomb;
import object.Key;

public class UI {
	
	GameWindow gw;
	Font Arial_12;
	BufferedImage keyImage,bombImage;
	boolean messageOn = false;
	String message = "";
	int messageCounter = 0;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GameWindow gw) {
		
		this.gw = gw;
		Arial_12 = new Font("Arial",Font.PLAIN,12);
		Key key = new Key();
		keyImage = key.getImage();;
		Bomb bomb = new Bomb();
		bombImage = bomb.getImage();
		
	}
	
	public void showMessage(String text) {
		messageCounter = 0;
		message = text;
		messageOn = true;
	}
	
	public void drawTransparentRectangle(Graphics2D g2) {
		Composite oldComposite = g2.getComposite();

	    // Set the transparency level
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65F));
	    
	    g2.setColor(Color.black);
	    // Draw a rectangle with the desired transparency level
	    g2.fillRect(0, 0, 85, 60);

	    // Restore the original composite
	    g2.setComposite(oldComposite);
	}
	
	public void draw(Graphics2D g2) {
		
		drawTransparentRectangle(g2);
		
		g2.setFont(Arial_12);
		
		g2.setColor(Color.white);
		
		g2.drawImage(keyImage,3,18,gw.tileSize/2,gw.tileSize/2,null);
		
		g2.drawString("x " + gw.player.getKeyAmount(), 22, 30);
		
		g2.drawString("FPS:" + gw.realFPS, gw.tileSize*17 + gw.tileSize/2 + gw.tileSize/16, 13);
		
		g2.drawImage(bombImage,3,38,gw.tileSize/2,gw.tileSize/2,null);
		
		g2.drawString("x " + gw.player.getBombAmount(), 22, 50);
		
		//Time
		playTime += (double)1/165;
		g2.drawString("Time: " + dFormat.format(playTime), 3, 13);
		
		if(messageOn == true) {
			if(!gw.ui.message.isEmpty()) {
			g2.setColor(Color.black);
			g2.fillRect(0, gw.tileSize*13+gw.tileSize/2, gw.tileSize*30, gw.tileSize);
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(15F));
			g2.drawString(message,5,gw.tileSize*14+gw.tileSize/8);
			
			messageCounter++;
			
			if(messageCounter > 500) {
				messageCounter = 0;
				messageOn = false;
			}
			}
		}
	}
}
