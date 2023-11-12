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
	

	
	// Check if it's the winning the hand
	public synchronized boolean checkHand(){
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
	
	public synchronized Card removeCard(){
		Random rand = new Random();
		Card randomElement;
		while (true){
			randomElement = cards.get(rand.nextInt(cards.size()));
			if (randomElement.getValueOf() != playerIndex + 1){
				break;
			}
		}
		System.out.println("Player " + Integer.toString(playerIndex + 1) + " discards " + Integer.toString(randomElement.getValueOf()));
		cards.remove(randomElement);
		return randomElement;
	}
	
	public void addCard(Card card){
		cards.add(card);
	}
	
	// Constructor
	public Player(int playerIndex){
		this.cards = cards;
		this.playerIndex = playerIndex;
	}



}