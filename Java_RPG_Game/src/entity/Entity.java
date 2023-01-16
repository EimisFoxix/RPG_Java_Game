package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	int worldX,worldY;
	int speed;
	String direction;
	BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	int spriteCounter = 0;
	int spriteNum = 1;
	Rectangle collisionArea;
	int collisionAreaDefaultX,collisionAreaDefaultY;
	boolean collisionOn = false;
	double UpScale,DownScale,LeftScale,RightScale;
	
	public int getWorldX() {
		return worldX;
	}
	
	public int getWorldY() {
		return worldY;
	}
	
	public void moveWorldX(int amount) {
		worldX += amount;
	}
	
	public void moveWorldY(int amount) {
		worldY += amount;
	}
	
	public int getSpeed() {
		return speed; 
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setDirection(String change) {
		direction = change;
	}
	
	public Rectangle getCollisionArea() {
		return collisionArea;
	}
	
	public int getCollisionAreaDefaultX() {
		return collisionAreaDefaultX;
	}
	
	public int getCollisionAreaDefaultY() {
		return collisionAreaDefaultY;
	}
	
	public void setCollisionAreaX(int newX) {
		collisionArea.x = newX; 
	}
	
	public void setCollisionAreaY(int newY) {
		collisionArea.y = newY;
	}
	
	public void setCollisionAreaWidth(int newWidth) {
		collisionArea.width = newWidth;
	}
	
	public void setCollisionAreaHeight(int newHeight) {
		collisionArea.height = newHeight;
	}
	public void changeCollisionAreaX(int change) {
		collisionArea.x += change; 
	}
	
	public void changeCollisionAreaY(int change) {
		collisionArea.y += change;
	}
	
	public void changeCollisionAreaWidth(int change) {
		collisionArea.width += change;
	}
	
	public void changeCollisionAreaHeight(int change) {
		collisionArea.height += change;
	}
	
	public Boolean getCollisionStatus() {
		return collisionOn;
	}
	
	public void setCollisionOn(boolean status) {
		collisionOn = status;
	}
	
	public double getUpScale() {
		return UpScale;
	}
	
	public double getDownScale() {
		return DownScale;
	}
	
	public double getLeftScale() {
		return LeftScale;
	}
	
	public double getRightScale() {
		return RightScale;
	}
	
	public void setUpScale(double newUpScale) {
		UpScale = newUpScale;
	}
	
	public void setDownScale(double newDownScale) {
		DownScale = newDownScale;
	}
	
	public void setLeftScale(double newLeftScale) {
		LeftScale = newLeftScale;
	}
	
	public void setRightScale(double newRightScale) {
		RightScale = newRightScale;
	}
}

