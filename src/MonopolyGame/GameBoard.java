package MonopolyGame;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JPanel;

// this file represents the abstract concept of the game board

public class GameBoard {

	// only valid tiles for gameboard
	private Tile[][] grid = new Tile[Constants.BOARD_DIMENSIONS][Constants.BOARD_DIMENSIONS];
	
	{
		grid = new Tile[Constants.BOARD_DIMENSIONS][Constants.BOARD_DIMENSIONS];
	}
	
	public GameBoard( File configurationFile ) {
		
		Scanner fileScan = null;
		
		try {
			fileScan = new Scanner( configurationFile );
		} catch( FileNotFoundException fnfe ) {
			System.out.println( "FNFE: " + fnfe.getMessage() );
		}
		Tile[] controlTiles = new Tile[Constants.NUM_CONTROL_TILES];
		Tile[] propertyTiles = new Tile[Constants.NUM_PROPERTY_TILES];
		Tile[] roadTiles = new Tile[Constants.NUM_ROAD_TILES];
		Tile[] studentChestTiles = new Tile[Constants.NUM_STUDENT_CHEST_TILES];
		Tile[] chanceTiles = new Tile[Constants.NUM_CHANCE_TILES];
		
		// public Tile( String name, Image inImage, int p, int x, int y )
		// public Tile( String name, int price, int x, int y );
		// public Tile( String name, int price, int x, int y, int d );
		
		String token = null;
		while ( fileScan.hasNext() ) {
			token = fileScan.next();
			if ( token.equals( "Control" ) ) {
				System.out.println( "Control" );
				int id = fileScan.nextInt();
				int val = fileScan.nextInt();
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();
				int d = fileScan.nextInt();
				String controlName = GameHelpers.getControlName(id);
                MonopolyObject monopolyObject = new Control(x, y, controlName, GameHelpers.getControlImages(id), val, d);
				grid[x][y] = new Tile(monopolyObject, GameHelpers.getControlImages(id), controlName, x, y);
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
				controlTiles[id] = grid[x][y];
			} else if ( token.equals( "Properties" ) ) {
				System.out.println( "Props" );
				int id = fileScan.nextInt();
				int val = fileScan.nextInt();
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();
				int d = fileScan.nextInt();
				String propertyName = GameHelpers.getPropertyName(id);
                MonopolyObject monopolyObject = new Property(x, y, propertyName, GameHelpers.getPropertyImage(id), val, d);
                grid[x][y] = new Tile(monopolyObject, GameHelpers.getPropertyImage(id), propertyName, x, y );
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
				propertyTiles[id] = grid[x][y];
			} else if ( token.equals( "Roads" ) ) {
				System.out.println( "Roads" ); 
				int id = fileScan.nextInt();
				int val = fileScan.nextInt();
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();
				int d = fileScan.nextInt();
				String roadName = GameHelpers.getRoadName(id);
                MonopolyObject monopolyObject = new Road(x, y, roadName, GameHelpers.getPropertyImage(id), val, d);
                grid[x][y] = new Tile(monopolyObject, GameHelpers.getPropertyImage(id), roadName, x, y);
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
				roadTiles[id] = grid[x][y];
			} else if ( token.equals( "StudentChest" ) ) {
				System.out.println( "StudentChest" );
				int id = fileScan.nextInt();
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();
				int d = fileScan.nextInt();
                MonopolyObject monopolyObject = new StudentChest(x, y, "Student Chest", GameHelpers.getStudentChestImage(), 0, d);
                grid[x][y] = new Tile(monopolyObject, GameHelpers.getStudentChestImage(), "Student Chest", x, y);
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
				studentChestTiles[id] = grid[x][y];
			} else if ( token.equals( "Chance" ) ) {
				System.out.println( "Chance" );
				int id = fileScan.nextInt();
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();
				int d = fileScan.nextInt();
                MonopolyObject monopolyObject = new Chance(x, y, "Chance", GameHelpers.getChanceImage(), 0, d);
                grid[x][y] = new Tile(monopolyObject, GameHelpers.getChanceImage(), "Chance", x, y);
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
				chanceTiles[id] = grid[x][y];
			}else if(token.equals("Jail")){
				int x = fileScan.nextInt();
				int y = fileScan.nextInt();
				int prevX = fileScan.nextInt();
				int prevY = fileScan.nextInt();
				int nextX = fileScan.nextInt();
				int nextY = fileScan.nextInt();

				MonopolyObject jail = new Jail(x, y);
				grid[x][y] = new Tile(jail, GameHelpers.getJailImage(), GameHelpers.getControlName(2), x, y);
				grid[x][y].prevX = prevX;
				grid[x][y].nextX = nextX;
				grid[x][y].prevY = prevY;
				grid[x][y].nextY= nextY;
			}
		}

		for(int i = 0; i < Constants.BOARD_DIMENSIONS; i++){
			for(int j = 0; j < Constants.BOARD_DIMENSIONS; j++){
				if(i == Constants.BOARD_DIMENSIONS-1 || i == 0 || j == Constants.BOARD_DIMENSIONS-1 || j == 0){
					if(grid[i][j] != null) {
						grid[i][j].setPrevTile(grid[grid[i][j].prevX][grid[i][j].prevY]);

						grid[i][j].setNextTile(grid[grid[i][j].nextX][grid[i][j].nextY]);
					}
				}
			}
		}


		
	}

	public Tile getTile(int x, int y){
		return grid[x][y];
	}
	
	public static void main( String [] args ) {
		new GameBoard( new File( "src/MonopolyGame/gameboard.txt" ) );
	}
}

