
/**
 * Represents an individual card. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class Card{
	
	// Attributes
	private int cardValue;
	
	// Methods

	/**
	* Returns integer value of card.
	* @return cardValue value of card
	*/
	public int getValueOf(){
		return cardValue;
	}
	
	// Constructor

	/**
	 * Creates a card object.
	 * @param cardValue value of card
	 */
	public Card(int cardValue){
		this.cardValue = cardValue;
	}
}
