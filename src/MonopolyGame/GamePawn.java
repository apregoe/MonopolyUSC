package MonopolyGame;

import MonopolyUI.CustomPanel;

import java.awt.*;

public class GamePawn extends MonopolyObject {

    public GamePawn( int x, int y, Image image, String label ){
        super(x, y, 0, 0);
        this.image = image;
        this.label = label;
    }

    public void setCoords( int x, int y ) {
        this.x = x;
        this.y = y;
    }

}
