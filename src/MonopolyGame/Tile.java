package MonopolyGame;

import java.awt.Image;
import java.util.Vector;

public class Tile {
	private int x;
	private int y;
	public int prevX, prevY;
	public int nextX, nextY;
	private String name;
	private Image mImage;
	
	private Tile nextTile;
	private Tile prevTile;
	private Vector<GamePawn> pawns = new Vector<GamePawn>(); 
	private boolean occupied = false;
	private MonopolyObject mObject;
	
	public Tile(Image inImage, String name, int x, int y ) {
		mImage = inImage;
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public Tile(MonopolyObject mo, Image inImage, String name, int x, int y ) {
		mImage = inImage;
		this.name = name;
		mObject = mo;
		this.x = x;
		this.y = y;
	}
	
	//public void doAction();
	
	public void setLocation( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public void setMonopolyObject( MonopolyObject mo ) {
		mObject = mo;
	}
	
	public MonopolyObject getMonopolyObject() {
		return mObject;
	}
	
	public Tile getNext() {
		return nextTile;
	}

	public Tile getPrev() { return prevTile; }

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setNextTile(Tile t){
		nextTile = t;
	}
	public void setPrevTile(Tile t){
		prevTile = t;
	}
	public boolean isOccupied() { return occupied; }
	
	public Vector<GamePawn> getPawns() {
		return pawns;
	}
	
	public void addPawn( GamePawn gp ) {
		pawns.add( gp );
	}

	public void removePawn( GamePawn gp ) {
		pawns.remove( gp );
	}

}
