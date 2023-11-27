import java.util.*;

/**
 * Represents an individual card deck. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class CardDeck{

		// Attributes
		private ArrayList<Card> cards = new ArrayList<Card>();
		
		// Methods

		/**
		 * Returns arraylist of card objects in deck
		 * @return cards arraylist of cards
		 */
		public ArrayList<Card> getDeck(){
			return cards;
		}

		/**
		 * Adds card to bottom of deck
		 * @param card card to be added
		 */
		public synchronized void addCard(Card card){
			cards.add(card);
		}
		
		/**
		 * Removes card from top of deck
		 * @param card
		 * @return Card card removed
		 */
		public synchronized Card removeTopCard(){
			return cards.remove(0);
		}
		
		// Constructor
		
        /**
         * Creates a card deck object.
         * @param cards arraylist of cards
         */
		public CardDeck(ArrayList<Card> cards){
			this.cards = cards;
		}
}
