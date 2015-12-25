package MonopolyGame;

import java.awt.Image;
import java.util.Vector;

public class Chance extends MonopolyObject {

	private Vector<GamePawn> pawnsOccupied;
	private SinglePlayer owner;
	private boolean isOwned = false;
	
	public Chance( int x, int y, String label, Image inImage, int value, int design ) {
		super( x, y, value, design );
		this.label = label;
		this.image = inImage;
		pawnsOccupied = new Vector<GamePawn>();
	}
	
	public void setOwner( SinglePlayer sp ) {
		owner = sp;
	}
	
	public void setOwnerStatus( boolean b ) {
		isOwned = b;
	}

	public void setValue( int v ) {
		value = v;
	}
	
	public int getValue() {
		return value;
	}
	
	public void addPawn( GamePawn p ) {
		if ( !pawnsOccupied.contains( p ) ) {
			pawnsOccupied.add( p );
		}
	}
	
	public void removePawn( GamePawn p ) {
		if ( pawnsOccupied.contains( p ) ) {
			pawnsOccupied.remove( p );
		}
	}
	
	

}
