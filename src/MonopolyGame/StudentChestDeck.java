package MonopolyGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class StudentChestDeck extends Deck {
		
	public StudentChestDeck( File configurationFile ) {
		super( "Student Chest", Constants.NUM_STUDENT_CHEST_CARDS );
		
		Scanner sc = null;
		
		try {
			sc = new Scanner( configurationFile );
		} catch( FileNotFoundException fnfe ) {
			System.out.println( "FNFE: " + fnfe.getMessage() );
		}
		
		while ( sc != null && sc.hasNext() ) {
			String cardInstructions = sc.nextLine();
			System.out.println( cardInstructions );
			addCard( cardInstructions );
		}
			
	}
	
	public static void main( String [] args ) {
		new StudentChestDeck( new File( "src/MonopolyGame/student_chest_cards.txt" ) );
	}
	
}
