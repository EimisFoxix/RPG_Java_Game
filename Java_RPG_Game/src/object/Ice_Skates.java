package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Player;
import main.GameWindow;

public class Ice_Skates extends Super_Object{
	public Ice_Skates(int x, int y, GameWindow gw) {
		super(x,y,gw);
		name = "Ice Skates";
		message = "You found Ice Skates! Grip should be better now";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Ice_Skates.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void updatePlayer(Player p) {
			p.changeGrip(0.01);
			objRemove();
	}
}
