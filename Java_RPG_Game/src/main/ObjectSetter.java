package main;

import object.Bomb;
import object.Ice_Skates;
import object.Vertical_Door;
import object.Key;
import object.Steroids;

public class ObjectSetter {
	GameWindow gw;
	
	public ObjectSetter(GameWindow gw) {
		this.gw = gw;
	}
	
	public void setObject() {
		gw.obj[0] = new Key(20,60,gw); //1: x coordinate, 2: y coordinate, 3: Current GameWindow
		
		gw.obj[1] = new Key(30,50,gw);
		
		gw.obj[2] = new Vertical_Door(31,58,gw);
		
		gw.obj[3] = new Bomb(22,58,gw);
		
		gw.obj[4] = new Ice_Skates(41,58,gw);
			
		gw.obj[5] = new Steroids(53,58,gw);
		
	}
}
