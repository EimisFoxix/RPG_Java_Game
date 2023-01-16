package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GameWindow;

public class Bomb extends Super_Object{
	public Bomb() {
		super();
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bomb1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public Bomb(int x, int y, GameWindow gw) {
		super(x,y, gw);
		name = "Bomb";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bomb1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void updatePlayer(Player p) {
			p.changeBombAmount(1);
			objRemove();
		}
}
