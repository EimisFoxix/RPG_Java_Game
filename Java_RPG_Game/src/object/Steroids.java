package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GameWindow;

public class Steroids extends Super_Object{
	public Steroids(int x, int y, GameWindow gw) {
		super(x,y,gw);
		name = "Steroids";
		message = "You ate some steroids! Legs feel faster?";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Steroids.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void updatePlayer(Player p) {
		p.changeSpeed(0.6);
		objRemove();
	}
}
