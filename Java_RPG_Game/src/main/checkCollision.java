package main;

import entity.Entity;
import entity.Player;

public class checkCollision {

	GameWindow gw;
	
	public checkCollision(GameWindow gw) {
		this.gw = gw;
	}
	
	public void checkTile(Entity entity, KeyHandler buttonPressed) {
		int entityLeftX = entity.getWorldX() + entity.getCollisionArea().x;
		int entityRightX = entity.getWorldX() + entity.getCollisionArea().x + entity.getCollisionArea().width;
		int entityTopY = entity.getWorldY() + entity.getCollisionArea().y;
		int entityBottomY = entity.getWorldY() + entity.getCollisionArea().y + entity.getCollisionArea().height;
		
		int entityLeftCol = entityLeftX/gw.tileSize;
		int entityRightCol = entityRightX/gw.tileSize;
		int entityTopRow = entityTopY/gw.tileSize;
		int entityBottomRow = entityBottomY/gw.tileSize;
		
		int tileNum1, tileNum2;
		switch(entity.getDirection()) {
		case "up":
			//checking for collision while walking up
			entityTopRow = (entityTopY - entity.getSpeed())/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			if(gw.tiles.tile[tileNum1].getCollision()|| gw.tiles.tile[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				cancelAcceleration(entity);
			}
			
			//implementing push back when walking into a pushback tile
			if(gw.tiles.tile[tileNum1].getPushBack()|| gw.tiles.tile[tileNum2].getPushBack()) {
				entity.setDownScale(0.5);
				buttonPressed.down = true;
				buttonPressed.up = false;
				entity.moveWorldY((int)Math.max(entity.getSpeed() * entity.getDownScale(),1)*2);
				entity.setDirection("down");
				buttonPressed.down = false;
			}
			break;
		case "down":
			//caseDown:
			entityBottomRow = (entityBottomY + entity.getSpeed())/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()||gw.tiles.tile[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].getPushBack()|| gw.tiles.tile[tileNum2].getPushBack()) {
				entity.setUpScale(0.5);
				buttonPressed.up = true;
				buttonPressed.down = false;
				
				entity.moveWorldY((-1)*(int)Math.max(entity.getSpeed() * entity.getUpScale(),1)*2);
				
				entity.setDirection("up");
				buttonPressed.up = false;
			}
			break;
		case "left":
			//caseLeft:
			entityLeftCol = (entityLeftX - entity.getSpeed())/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()|| gw.tiles.tile[tileNum2].getCollision()) {
				
				entity.setCollisionOn(true);
				
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].getPushBack()|| gw.tiles.tile[tileNum2].getPushBack()) {
				entity.setRightScale(0.5);
				buttonPressed.right = true;
				buttonPressed.left = false;
				
				entity.moveWorldX((int)Math.max(entity.getSpeed() * entity.getRightScale(),1)*2);
				
				entity.setDirection("right");
				buttonPressed.right = false;
			}
			break;
		case "right":
			//caseRight:
			entityRightCol = (entityRightX + entity.getSpeed())/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()||gw.tiles.tile[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].getPushBack()||gw.tiles.tile[tileNum2].getPushBack()) {
				entity.setLeftScale(0.5);
				buttonPressed.left = true;
				buttonPressed.right = false;
				
				entity.moveWorldX((-1)*(int)Math.max(entity.getSpeed() * entity.getLeftScale(),1)*2);
				
				entity.setDirection("left");
				buttonPressed.left = false;
			}
			break;
		}
		
		//Check for acceleration also, but always not depending on button direction.
		if(entity.getRightScale() > entity.getLeftScale()) {
			entityRightCol = (int)((entityRightX + entity.getSpeed()*entity.getRightScale())/gw.tileSize);
			tileNum1 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()|| gw.tiles.tile[tileNum2].getCollision()) {
				cancelAcceleration(entity);
			}
		}
		else if (entity.getLeftScale() > entity.getRightScale()) {
			entityLeftCol = (int)((entityLeftX + entity.getSpeed()*entity.getLeftScale())/gw.tileSize);
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()|| gw.tiles.tile[tileNum2].getCollision()) {
				cancelAcceleration(entity);
			}
		}
		if(entity.getUpScale() > entity.getDownScale()) {
			entityTopRow = (int)((entityTopY + entity.getSpeed()*entity.getUpScale())/gw.tileSize);
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			if(gw.tiles.tile[tileNum1].getCollision()|| gw.tiles.tile[tileNum2].getCollision()) {
				cancelAcceleration(entity);
			}
		}
		else if(entity.getDownScale() > entity.getUpScale()) {
			entityBottomRow = (int)((entityBottomY + entity.getSpeed()*entity.getDownScale())/gw.tileSize);
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].getCollision()||gw.tiles.tile[tileNum2].getCollision()) {
				cancelAcceleration(entity);
			}
		}
	}
	void cancelAcceleration(Entity entity) {
		entity.setUpScale(0);
		entity.setDownScale(0);
		entity.setLeftScale(0);
		entity.setRightScale(0);
	}
	
	void inverseAcceleration(Entity entity) {
		double temp;
		temp = entity.getUpScale();
		entity.setUpScale(entity.getDownScale());
		entity.setDownScale(temp);
		temp = entity.getRightScale();
		entity.setRightScale(entity.getLeftScale());
		entity.setLeftScale(temp);
	}
	
	public void checkType(Player entity) {
		int index;
		index = gw.tiles.mapTiles[entity.getWorldX()/gw.tileSize][entity.getWorldY()/gw.tileSize];
		if (gw.tiles.tile[index].getGrippy()) {
			entity.changeGripOnFloor(true);
		}
		else {entity.changeGripOnFloor(false);}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i<gw.obj.length; i++) {
			if(gw.obj[i]!= null) {
				
				//Find out entity's collision area's position
				entity.setCollisionAreaX(entity.getWorldX() + entity.getCollisionArea().x);
				entity.setCollisionAreaY(entity.getWorldY() + entity.getCollisionArea().y);
				
				//Find out object's collision area's position
				gw.obj[i].getCollisionArea().x = gw.obj[i].getWorldX() + gw.obj[i].getCollisionArea().x;
				gw.obj[i].getCollisionArea().y = gw.obj[i].getWorldY() + gw.obj[i].getCollisionArea().y;
				
				switch(entity.getDirection()) {
				case "up":
					entity.changeCollisionAreaY(entity.getSpeed()*(-1));
					if(entity.getCollisionArea().intersects(gw.obj[i].getCollisionArea())) {
						if(gw.obj[i].getCollision()) {
							entity.setCollisionOn(true);
							cancelAcceleration(entity);
							entity.moveWorldY(2);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.changeCollisionAreaY(entity.getSpeed());
					if(entity.getCollisionArea().intersects(gw.obj[i].getCollisionArea())) {
						if(gw.obj[i].getCollision()) {
							entity.setCollisionOn(true);
							cancelAcceleration(entity);
							entity.moveWorldY(-2);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.changeCollisionAreaX(entity.getSpeed()*(-1));
					if(entity.getCollisionArea().intersects(gw.obj[i].getCollisionArea())){
						if(gw.obj[i].getCollision()) {
							entity.setCollisionOn(true);
							cancelAcceleration(entity);
							entity.moveWorldX(2);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.changeCollisionAreaX(entity.getSpeed());
					if(entity.getCollisionArea().intersects(gw.obj[i].getCollisionArea())) {
						if(gw.obj[i].getCollision()) {
							entity.setCollisionOn(true);
							cancelAcceleration(entity);
							entity.moveWorldX(-2);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				entity.setCollisionAreaX(entity.getCollisionAreaDefaultX());
				entity.setCollisionAreaY(entity.getCollisionAreaDefaultY());
				gw.obj[i].setCollisionAreaX(gw.obj[i].getCollisionAreaDefaultX());
				gw.obj[i].setCollisionAreaY(gw.obj[i].getCollisionAreaDefaultY());
			}
		}
		return index;
		
	}
	
}
