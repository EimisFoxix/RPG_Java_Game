package tile;

import java.awt.image.BufferedImage;

public class Tile {

	BufferedImage image;
	boolean collision = false;
	boolean pushBack = false;
	boolean damage = false;
	boolean grippy = false;
	
	public Boolean getCollision() {
		return collision;
	}
	public Boolean getPushBack() {
		return pushBack;
	}
	public Boolean getDamage() {
		return damage;
	}
	public Boolean getGrippy() {
		return grippy;
	}
	public void setCollision(boolean value) {
		collision = value;
	}
	public void setPushBack(boolean value) {
		pushBack = value;
	}
	public void setDamage(boolean value) {
		damage = value;
	}
	public void setGrippy(boolean value) {
		grippy = value;
	}
}
