package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GameWindow;

public class Vertical_Door extends Super_Object{
	public Vertical_Door(int x, int y, GameWindow gw) {
		super(x,y, gw);
		name = "Doors";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/vertical_door.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		collisionArea.height = 128;
		collisionArea.width = 64;
	}
	public void updatePlayer(Player p) {
			if(p.getKeyAmount()>0) {
				message = "You unlocked the door!";
				p.changeKeyAmount(-1);
				objRemove();
			}
			else {
				message = "You need a key...";
			}
	}
}
