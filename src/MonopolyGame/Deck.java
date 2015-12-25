package MonopolyGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import MonopolyGame.Constants;

public class Deck {

	// cards in Card[]
	private String name;
	private int numCards;
	
	private List<Card> cards;
	private int index;
	private Random random = new Random();

	public Deck( String name, int numCards ) {
		this.name = name;
		this.numCards = numCards;
		cards = new ArrayList<Card>();
	}
	
	public String getDeckLabel() {
		return name;
	}
	
	public void addCard( String cardInstructions ) {
		// add in a new card's information
		cards.add( new Card( index++, cardInstructions ) );
	}
	
	public Card drawCard() {
		if ( index == numCards ) {
			index %= numCards;
			Collections.shuffle( cards, random );
		}
		return cards.get( index++ );
	}
	
	class Card {
		private final int index;
		private final String instructions;
		
		Card( int index, String instructions ) {
			this.index = index;
			this.instructions = instructions;
		}
		
		public String getInstruction() {
			return instructions;
		}
		public int getIndex() {
			return index;
		}
	}
}
