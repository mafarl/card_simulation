import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an individual player. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class Player{

	// Attributes
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int playerIndex;
	
	// Method

	/**
	 * Returns players arraylist of card objects
	 * @return cards arraylist of cards
	 */
	public ArrayList<Card> getHand(){
		return cards;
	}

	/**
	 * Checks if cards in players hand all match
	 * @return isWinning boolean value of check
	 */
	public boolean checkHand(){
		boolean isWinning = false;

		for (int i = 1; i < 4; i++){

			if (cards.get(0).getValueOf() == cards.get(i).getValueOf()){
				isWinning = true;
			} else {
				isWinning = false;
				break;
			}
		}
		return isWinning;
	}
	
	public Card removeCard(){
		Random rand = new Random();
		Card randomElement;
		while (true){
			randomElement = cards.get(rand.nextInt(cards.size()));
			if (randomElement.getValueOf() != playerIndex + 1){
				break;
			}
		}
		
		cards.remove(randomElement);
		return randomElement;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	// Constructor

	/**
	 * Creates a player object
	 * @param playerIndex index of player
	 */
	public Player(int playerIndex){
		this.cards = cards;
		this.playerIndex = playerIndex;
	}



}