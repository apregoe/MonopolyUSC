package MonopolyGame;

import javax.swing.*;
import java.awt.Image;

public class MonopolyObject {

	protected String label = "";
	protected Image image = null;
	protected int value;
	protected int x;
	protected int y;
	protected int design;
	protected MonopolyObject( int x, int y, int value, int design ) {
		this.x = x;
		this.y = y;
		this.design = design;
		this.value = value;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public int getValue(){ return value; }

	public Image getImage(){
		return image;
	}
}
