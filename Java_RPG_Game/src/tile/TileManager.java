package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GameWindow;

public class TileManager {
	GameWindow gw;
	public Tile[] tile;
	public int mapTiles[][];
	boolean drawn = false;
	public Random random = new Random();
	int counter;
	public TileManager(GameWindow gw) {
		this.gw = gw;
		random.setSeed(System.currentTimeMillis());
		tile = new Tile[32];
		mapTiles = new int[gw.getMaxWorldColumns()][gw.getMaxWorldRows()];
		
		
		getTileImage();
		loadMap("/maps/WorldMap02.txt");
}
	
	public void getTileImage() {
		
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice1alt.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/ice2alt.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/fire.png"));
			tile[2].collision = true;
			tile[2].pushBack = true;
			tile[2].damage = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike1.png"));
			tile[3].damage = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/spike2.png"));
			tile[4].damage = true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock1.png"));
			tile[5].collision = true;
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/rock2.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood1.png"));
			tile[7].grippy = true;
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wood2.png"));
			tile[8].grippy = true;
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod1.png"));
			tile[20].collision = true;
			tile[20].pushBack = true;
			tile[20].damage = true;
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod2.png"));
			tile[21].collision = true;
			tile[21].pushBack = true;
			tile[21].damage = true;
			
			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod3.png"));
			tile[22].collision = true;
			tile[22].pushBack = true;
			tile[22].damage = true;
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod4.png"));
			tile[23].collision = true;
			tile[23].pushBack = true;
			tile[23].damage = true;
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod5.png"));
			tile[24].collision = true;
			tile[24].pushBack = true;
			tile[24].damage = true;
			
			tile[25] = new Tile();
			tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod6.png"));
			tile[25].collision = true;
			tile[25].pushBack = true;
			tile[25].damage = true;
			
			tile[26] = new Tile();
			tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod7.png"));
			tile[26].collision = true;
			tile[26].pushBack = true;
			tile[26].damage = true;
			
			tile[27] = new Tile();
			tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/el_rod8.png"));
			tile[27].collision = true;
			tile[27].pushBack = true;
			tile[27].damage = true;
			
			
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

			while(col < gw.getMaxWorldColumns() && row < gw.getMaxWorldRows()) {
				
				String line = br.readLine();
				
				while(col < gw.getMaxWorldColumns()) {
					String numbers[] = line.split(" ");
					int number = Integer.parseInt(numbers[col]);
					mapTiles[col][row] = number;
					col++;
				}
				if(col == gw.getMaxWorldColumns()) {
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
		int tileIndex;
		while (worldCol<gw.getMaxWorldColumns() && worldRow < gw.getMaxWorldRows()) {
			//drawing the map according to the index of a tile extracted from the text file.
			tileIndex = mapTiles[worldCol][worldRow];
			if(!drawn) {
			switch(tileIndex) {
			case 0:
				tileIndex += randomizeTile(0,2);
				mapTiles[worldCol][worldRow] = tileIndex;
				break;
			case 3:
				tileIndex += randomizeTile(0,2);
				mapTiles[worldCol][worldRow] = tileIndex;
				break;
			case 5:
				tileIndex += randomizeTile(0,2);
				mapTiles[worldCol][worldRow] = tileIndex;
				break;
			case 7:
				tileIndex += randomizeTile(0,2);
				mapTiles[worldCol][worldRow] = tileIndex;
				break;
			}
			}
			
			if(tileIndex > 19 && tileIndex < 28) {
				if(counter>50) {
				tileIndex = randomizeTile(20,8);
				mapTiles[worldCol][worldRow] = tileIndex;
				counter = 0;
				}
				counter++;
			}
			//Calculating the relative position of the player and the map for tile drawing.
			int worldX = worldCol * gw.getTileSize();
			int worldY = worldRow * gw.getTileSize();
			int screenX = worldX - gw.player.getWorldX() + gw.player.getScreenX();
			int screenY = worldY - gw.player.getWorldY() + gw.player.getScreenY();
			
			
			
			//If statement to only draw tiles for what player sees + 4 in order to improve efficiency.
			if( worldX + gw.getTileSize()*4 > gw.player.getWorldX() - gw.player.getScreenX() &&
				worldX - gw.getTileSize()*4 < gw.player.getWorldX() + gw.player.getScreenX() &&
				worldY + gw.getTileSize()*4 > gw.player.getWorldY() - gw.player.getScreenY() &&
				worldY - gw.getTileSize()*4 < gw.player.getWorldY() + gw.player.getScreenY()) {
				
				g2.drawImage(tile[tileIndex].image, screenX, screenY, gw.getTileSize(), gw.getTileSize(), null);
				
			}
			
			
			worldCol++;
			
			if (worldCol == gw.getMaxWorldColumns()) {
				worldCol = 0;
				worldRow++;

			}
		}
		if(drawn = false) {
		drawn = true;
		}
	}
	
	int randomizeTile(int floor, int options) {
	    return random.nextInt(options)+floor;
	}
}
