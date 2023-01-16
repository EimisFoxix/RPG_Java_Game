package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GameWindow;

public class Key extends Super_Object{
	public Key() {
		super();
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	public Key(int x, int y, GameWindow gw) {
		super(x,y,gw);
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void updatePlayer(Player p) {
			p.changeKeyAmount(1);
			objRemove();
	}
}
