package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GameWindow;

public class TileManager {
	GameWindow gw;
	public Tile[] tile;
	public int mapTiles[][];
	public TileManager(GameWindow gw) {
		this.gw = gw;
		
		tile = new Tile[12];
		mapTiles = new int[gw.maxWorldColumns][gw.maxWorldRows];
		
		
		getTileImage();
		loadMap("/maps/WorldMap02.txt");
}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice1.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock1.png"));
			tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fire.png"));
			tile[2].collision = true;
			tile[2].pushBack = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice2.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock2.png"));
			tile[5].collision = true;
			

			
			
		} catch(IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void loadMap(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;

			while(col < gw.maxWorldColumns && row < gw.maxWorldRows) {
				
				String line = br.readLine();
				
				while(col < gw.maxWorldColumns) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[col]);
					mapTiles[col][row] = number;
					col++;
				}
				if(col == gw.maxWorldColumns) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		int tileDistribution = 1;
		while (worldCol<gw.maxWorldColumns && worldRow < gw.maxWorldRows) {
			//drawing the map according to the index of a tile extracted from the text file.
			int tileIndex = mapTiles[worldCol][worldRow];
			if((tileIndex == 0 || tileIndex == 1)){
				tileIndex = randomizeTile(tileIndex,tileDistribution);
				//mapTiles[worldCol][worldRow] = tileIndex;
				if (tileDistribution == 0) {tileDistribution = 1;}
				else {tileDistribution = 0;}
			}
			//Calculating the relative position of the player and the map for tile drawing.
			int worldX = worldCol * gw.tileSize;
			int worldY = worldRow * gw.tileSize;
			int screenX = worldX - gw.player.worldX + gw.player.screenX;
			int screenY = worldY - gw.player.worldY + gw.player.screenY;
			
			//If statement to only draw tiles for what player sees + 4 in order to improve efficiency.
			if( worldX + gw.tileSize*4 > gw.player.worldX - gw.player.screenX &&
				worldX - gw.tileSize*4 < gw.player.worldX + gw.player.screenX &&
				worldY + gw.tileSize*4 > gw.player.worldY - gw.player.screenY &&
				worldY - gw.tileSize*4 < gw.player.worldY + gw.player.screenY) {
				
				g2.drawImage(tile[tileIndex].image, screenX, screenY, gw.tileSize, gw.tileSize, null);
				
			}
			
			worldCol++;
			
			if (worldCol == gw.maxWorldColumns) {
				worldCol = 0;
				worldRow++;

			}
		}
		
	}
	
	int randomizeTile(int tileIndex, int tileDistribution) {
		if(tileDistribution == 1) {
			tileIndex += 4;
		}
		return tileIndex;
	}
}
