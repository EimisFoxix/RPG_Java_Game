package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import entity.Player;
import main.GameWindow;
import main.UI;

public abstract class Super_Object{
	BufferedImage image;
	String name;
	boolean collision = false;
	boolean removeStatus = false;
	int worldX,worldY;
	Rectangle collisionArea = new Rectangle(0,0,32,32);
	int collisionAreaDefaultX = 0;
	int collisionAreaDefaultY = 0;
	String message = "";
	GameWindow gw;
	UI ui;
	
	public Super_Object() {}
	
	public Super_Object(int x, int y, GameWindow gw) {
		worldX = x * gw.getTileSize();  //32
		worldY = y * gw.getTileSize();  //32
	}
	
	public void draw(Graphics2D g2, GameWindow gw) {
		
		int screenX = worldX - gw.player.getWorldX() + gw.player.getScreenX();
		int screenY = worldY - gw.player.getWorldY() + gw.player.getScreenY();
		
		//If statement to only draw tiles for what player sees + 4 in order to improve efficiency.		
		if( worldX + gw.getTileSize()*4 > gw.player.getWorldX() - gw.player.getScreenX() &&
			worldX - gw.getTileSize()*4 < gw.player.getWorldX() + gw.player.getScreenX() &&
			worldY + gw.getTileSize()*4 > gw.player.getWorldY() - gw.player.getScreenY() &&
			worldY - gw.getTileSize()*4 < gw.player.getWorldY() + gw.player.getScreenY()) {

			g2.drawImage(image, screenX, screenY, image.getTileWidth(), image.getTileHeight(), null);	
		}
	}
	
	public int getWorldX() {
		return worldX;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Boolean getCollision() {
		return collision;
	}
	
	public abstract void updatePlayer(Player p);
	public String getMessage() {
		return message;
	}
	public void objRemove() {
		removeStatus = true;
	}
	public Boolean checkForRemoval() {
		return removeStatus;
	}
	public Rectangle getCollisionArea() {
		return collisionArea;
	}
	public void setCollisionAreaX(int newX) {
		collisionArea.x = newX;
	}
	public void setCollisionAreaY(int newY) {
		collisionArea.y = newY;
	}
	public int getCollisionAreaDefaultX() {
		return collisionAreaDefaultX;
	}
	public int getCollisionAreaDefaultY() {
		return collisionAreaDefaultY;
	}
}
