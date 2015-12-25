package MonopolyGame;

import java.util.Random;

public class Die {

	private Random random;
	
	public Die() {
		random = new Random();
	}
	
	public int rollDie() {
		return random.nextInt( 6 ) + 1;
	}

}
