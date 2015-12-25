package MonopolyGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChanceDeck extends Deck {
		
	public ChanceDeck( File configurationFile ) {
		super( "Chance", Constants.NUM_CHANCE_CARDS );
		
		Scanner sc = null;
		
		try {
			sc = new Scanner( configurationFile );
		} catch( FileNotFoundException fnfe ) {
			System.out.println( "FNFE: " + fnfe.getMessage() );
		}
		
		while ( sc.hasNext() ) {
			String cardInstructions = sc.nextLine();
			System.out.println( cardInstructions );
			addCard( cardInstructions );
		}
			
	}
	
	public static void main( String [] args ) {
		new ChanceDeck( new File( "src/MonopolyGame/chance_cards.txt" ) );
	}
	
}
