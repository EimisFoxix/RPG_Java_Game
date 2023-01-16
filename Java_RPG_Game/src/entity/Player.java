package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GameWindow;
import main.KeyHandler;

public class Player extends Entity{
	GameWindow gw;
	KeyHandler buttonPressed;
	double speedScale;
	String lastDirection;
	double grip;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GameWindow gw, KeyHandler buttonPressed) {
		this.gw = gw;
		this.buttonPressed = buttonPressed;
		
		screenX = gw.screenWidth/2-gw.tileSize;
		screenY = gw.screenHeight/2-gw.tileSize;
		
		collisionArea = new Rectangle(16,16,32,32);
		
		setValues();
		getPlayerImages();
	}
	public void setValues() {
		worldX = gw.tileSize * 15;
		worldY = gw.tileSize * 59;
		direction = "down";
		speedScale = 1;
		speed = (int)(3 * speedScale);
		grip = 0.005;
	}
	
	public void getPlayerImages() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/guy_up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/guy_up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/guy_down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/guy_down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/guy_left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/guy_left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/guy_right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/guy_right2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void setDirection() {
		if(buttonPressed.up == true) {
			direction = "up";
		}
		else if(buttonPressed.down == true) {
			direction = "down";
		}
		else if(buttonPressed.left == true) {
			direction = "left";
		}
		else if(buttonPressed.right == true) {
			direction = "right";
		}
	}
	
	Boolean playerMoving() {
		
		if(buttonPressed.up == true || UpScale > 0 || buttonPressed.down || DownScale > 0
				|| buttonPressed.left || LeftScale > 0 || buttonPressed.right || RightScale > 0) {
			return true;
		}
		else return false;
	}
	
	public void update() {
		
		setDirection();
		collisionOn = false;
		gw.collisionChecker.checkTile(this,buttonPressed); 
		
		if(collisionOn == false) {
			if(playerMoving()) {
				if(buttonPressed.up == true || UpScale > 0) {
					worldY -= Math.max(speed * UpScale,1);
				}
				if(buttonPressed.down == true || DownScale > 0) {
					worldY += Math.max(speed * DownScale,1);
				}
				if(buttonPressed.left == true || LeftScale > 0) {
					worldX -= Math.max(speed * LeftScale,1);
				}
				if(buttonPressed.right == true || RightScale > 0) {
					worldX += Math.max(speed * RightScale,1);
				}
		}
		
		spriteCounter++;
		if(spriteCounter > 20/speedScale) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1 || !playerMoving()) {
				image = up1;
			}
			else if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1 || !playerMoving()) {
				image = down1;
			}
			else if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1 || !playerMoving()) {
				image = left1;
			}
			else if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1 || !playerMoving()) {
				image = right1;
			}
			else if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gw.tileSize*2, gw.tileSize*2, null);
		
		g2.setColor(Color.white);
		
		g2.setTransform(AffineTransform.getScaleInstance(2.5, 2.5));
		
		g2.drawString("W: " + UpScale, 3, 33);
		
		g2.drawString("S: " + DownScale, 3, 53);
		
		g2.drawString("A: " + LeftScale, 3, 73);
		
		g2.drawString("D: " + RightScale, 3, 93);
		
		g2.drawString("Direction: " + direction, 3, 113);
	}
	
	
	public void calculateAcceleration(KeyHandler buttonPressed) {
		
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
		
		//Limitations making sure its from 0 to 1
		if(UpScale < 0) {UpScale = 0;} else if(UpScale>1) {UpScale = 1;}
		if(DownScale < 0) {DownScale = 0;} else if(DownScale>1) {DownScale = 1;}
		if(LeftScale < 0) {LeftScale = 0;} else if(LeftScale>1) {LeftScale = 1;}
		if(RightScale < 0) {RightScale = 0;} else if(RightScale>1) {RightScale = 1;}
	}
}



