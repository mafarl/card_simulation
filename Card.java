public class Card{
	
	// Attributes
	int cardIndex;
	int cardValue;
	
	// Methods
	
	public int getValueOf(){
		return cardValue;
	}
	
	// Constructor
	public Card(int cardIndex, int cardValue){
		this.cardIndex = cardIndex;
		this.cardValue = cardValue;
	}
}