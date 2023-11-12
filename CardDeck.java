// Read cards from the file --> create Card instance and give value of the next card in the file
// Add to an array
// Create n Players
// Distribute cards within players (array for each Player eg. player 1: [card1,card3,card5,card7])
// Create n CardDecks
// Distribute cards within CardDecks (array for each CardDeck eg. carddeck 1: [card10,card13,card15,card17])
import java.util.*;
import java.io.*;

public class CardDeck{

		// Attributes
		private ArrayList<Card> cards = new ArrayList<Card>();
		private int deckIndex;
		
		// Methods	
		public ArrayList<Card> getDeck(){
			return cards;
		}

		// add card to bottom of deck
		public synchronized void addCard(Card card){
			cards.add(card);
		}
		
		// remove card from top of deck
		public synchronized Card removeTopCard(){
			return cards.remove(0);
		}
		
		
		// Constructor
		public CardDeck(int deckIndex){
			this.deckIndex = deckIndex;
			this.cards = cards;
		}

}
