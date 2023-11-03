import java.util.ArrayList;
import java.util.Random;

public class Player{

	// Attributes
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int playerIndex;
	
	// Method
	public ArrayList<Card> getHand(){
		return cards;
	}
	
	public int getIndex(){
		return playerIndex;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	// Check if it's the winning the hand
	public boolean checkHand(){
		boolean isWinning = false;
		for (int i = 1; i < 4; i++){
			if (cards.get(0) == cards.get(i)){
				isWinning = true;
			} else {
				isWinning = false;
				break;
			}
		}
		return isWinning;
	}
	
	public int removeCard(){
		Random rand = new Random();
		int randomElement;
		while (true){
			randomElement = cards.get(rand.nextInt(cards.size())).getValueOf();
			if (randomElement != playerIndex){
				break;
			}
		}
		cards.remove(Integer.valueOf(randomElement));
		return randomElement;
	}
	
	
	// Constructor
	public Player(ArrayList<Card> cards, int playerIndex){
		this.cards = cards;
		this.playerIndex = playerIndex;
	}

}