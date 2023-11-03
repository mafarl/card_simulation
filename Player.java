package card_simulation;
import java.util.ArrayList;
import java.util.Random;
import Card.*;
import CardDeck.*;

public class Player{

	// Attributes
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int playerIndex;
	
	// Method
	public int getHand(){
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
		while (true){
			int randomElement = cards.get(rand.nextInt(cards.size()));
			if (randomElement != playerIndex){
				break;
			}
		}
		cards.remove(Integer.valueOf(randomElement));
		return randomElement;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	// Constructor
	public Player(ArrayList<Card> cards, int playerIndex){
		this.cards = cards;
		this.playerIndex = playerIndex;
	}

}