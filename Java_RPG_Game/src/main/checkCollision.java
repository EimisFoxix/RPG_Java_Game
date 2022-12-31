package main;

import entity.Entity;

public class checkCollision {

	GameWindow gw;
	
	public checkCollision(GameWindow gw) {
		this.gw = gw;
	}
	
	public void checkTile(Entity entity, KeyHandler buttonPressed) {
		int entityLeftX = entity.worldX + entity.collisionArea.x;
		int entityRightX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
		int entityTopY = entity.worldY + entity.collisionArea.y;
		int entityBottomY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;
		
		int entityLeftCol = entityLeftX/gw.tileSize;
		int entityRightCol = entityRightX/gw.tileSize;
		int entityTopRow = entityTopY/gw.tileSize;
		int entityBottomRow = entityBottomY/gw.tileSize;
		
		int tileNum1, tileNum2;
		switch(entity.direction) {
		case "up":
			//checking for collision while walking up
			entityTopRow = (entityTopY - entity.speed)/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			if(gw.tiles.tile[tileNum1].collision == true || gw.tiles.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				cancelAcceleration(entity);
			}
			
			//implementing push back when walking into a pushback tile
			if(gw.tiles.tile[tileNum1].pushBack == true || gw.tiles.tile[tileNum2].pushBack == true) {
				entity.DownScale = 0.5;
				buttonPressed.down = true;
				buttonPressed.up = false;
				entity.worldY += Math.max(entity.speed * entity.DownScale,1)*2;
				entity.direction = "down";
				buttonPressed.down = false;
			}
			break;
		case "down":
			//caseDown:
			entityBottomRow = (entityBottomY + entity.speed)/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].collision == true || gw.tiles.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].pushBack == true || gw.tiles.tile[tileNum2].pushBack == true) {
				entity.UpScale = 0.5;
				buttonPressed.up = true;
				buttonPressed.down = false;
				entity.worldY -= Math.max(entity.speed * entity.UpScale,1) * 2;
				entity.direction = "up";
				buttonPressed.up = false;
			}
			break;
		case "left":
			//caseLeft:
			entityLeftCol = (entityLeftX - entity.speed)/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityLeftCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityLeftCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].collision == true || gw.tiles.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].pushBack == true || gw.tiles.tile[tileNum2].pushBack == true) {
				entity.RightScale = 0.5;
				buttonPressed.right = true;
				buttonPressed.left = false;
				entity.worldX += Math.max(entity.speed * entity.RightScale,1) * 2;
				entity.direction = "right";
				buttonPressed.right = false;
			}
			break;
		case "right":
			//caseRight:
			entityRightCol = (entityRightX + entity.speed)/gw.tileSize;
			tileNum1 = gw.tiles.mapTiles[entityRightCol][entityTopRow];
			tileNum2 = gw.tiles.mapTiles[entityRightCol][entityBottomRow];
			if(gw.tiles.tile[tileNum1].collision == true || gw.tiles.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				cancelAcceleration(entity);
			}
			
			if(gw.tiles.tile[tileNum1].pushBack == true || gw.tiles.tile[tileNum2].pushBack == true) {
				entity.LeftScale = 0.5;
				buttonPressed.left = true;
				buttonPressed.right = false;
				entity.worldX -= Math.max(entity.speed * entity.LeftScale,1) * 2;
				entity.direction = "left";
				buttonPressed.left = false;
			}
			break;
		}
	}
	void cancelAcceleration(Entity entity) {
		entity.UpScale = 0;
		entity.DownScale = 0;
		entity.LeftScale = 0;
		entity.RightScale = 0;
	}
	
	void inverseAcceleration(Entity entity) {
		double temp;
		temp = entity.UpScale;
		entity.UpScale = entity.DownScale;
		entity.DownScale = temp;
		temp = entity.RightScale;
		entity.RightScale = entity.LeftScale;
		entity.LeftScale = temp;
	}
	
}
