import java.util.*;
import java.io.*;

/**
 * Represents an individual card deck. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class CardDeck{

		// Attributes
		private ArrayList<Card> cards = new ArrayList<Card>();
		private int deckIndex;
		
		// Methods

		// Returns arraylist of card objects in deck
		public ArrayList<Card> getDeck(){
			return cards;
		}

		// Adds card to bottom of deck
		public synchronized void addCard(Card card){
			cards.add(card);
		}
		
		// Removes card from top of deck
		public synchronized Card removeTopCard(){
			return cards.remove(0);
		}
		
		// Constructor
		public CardDeck(){
			this.cards = cards;
		}
}
