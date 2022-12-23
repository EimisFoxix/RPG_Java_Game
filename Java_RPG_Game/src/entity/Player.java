package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GameWindow;
import main.KeyHandler;

public class Player extends Entity{
	GameWindow gw;
	KeyHandler buttonPressed;
	double speedScale;
	
	
	double grip;
	
	public Player(GameWindow gw, KeyHandler buttonPressed) {
		this.gw = gw;
		this.buttonPressed = buttonPressed;
		setValues();
	}
	public void setValues() {
		x = 100;
		y = 100;
		speedScale = 1.5;
		speed = (int)(3 * speedScale);
		grip = 0.005;
	}
	public double getGrip() {
		return grip;
	}
	public void update(double UpScale, double DownScale, double LeftScale, double RightScale) {
		if(buttonPressed.up == true || UpScale > 0) {
			y -= Math.max(speed * UpScale,1);
		}
		if(buttonPressed.down == true || DownScale > 0) {
			y += Math.max(speed * DownScale,1);
		}
		if(buttonPressed.left == true || LeftScale > 0) {
			x -= Math.max(speed * LeftScale,1);
		}
		if(buttonPressed.right == true || RightScale > 0) {
			x += Math.max(speed * RightScale,1);
		}
	}
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.cyan);
		
		g2.fillRect(x,y,gw.tileSize,gw.tileSize);

	}
}
