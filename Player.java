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
	 * @return cards hand of player
	 */
	public ArrayList<Card> getHand(){
		return cards;
	}
	
	/**
	 * Returns the player index.
	 * @return playerIndex index of player
	 */
	public int getPlayerIndex(){
		return playerIndex;
	}

	/**
	 * Checks if cards in players hand all match
	 * @return isWinning boolean value of check
	 */
	public boolean checkHand(){
		boolean isWinning = false;
		for (int i = 1; i < 4; i++){
			// Checks if every card is the same value
			if (cards.get(0).getValueOf() == cards.get(i).getValueOf()){
				isWinning = true;
			} else {
				isWinning = false;
				break;
			}
		}
		return isWinning;
	}
	
	/**
	 * Removes a card from the hand randomly.
	 * @return Card random card removed
	 */
	public Card removeCard(){
		Random rand = new Random();
		Card randomElement;
		while (true){
			// Picks card randomly that is not the preferred denomination
			randomElement = cards.get(rand.nextInt(cards.size())); 
			if (randomElement.getValueOf() != playerIndex + 1){
				break;
			}
		}
		cards.remove(randomElement);
		return randomElement;
	}

	/**
	 * Adds card to player hand
	 * @param Card card to be added
	 */
	public void addCard(Card card){
		cards.add(card);
	}
	
	// Constructor

	/**
	 * Creates a player object
	 * @param playerIndex index of player
	 * @param cards players hand
	 * @return Player object
	 */
	public Player(int playerIndex, ArrayList<Card> cards){
		this.cards = cards;
		this.playerIndex = playerIndex;
	}
}