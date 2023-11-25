import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

public class CardDeckTest{

	private CardDeck cardDeck1;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	@Before
	public void setUp(){
		
		// Populate the deck
		for (int i = 0; i <4; i++){
			cards.add(new Card(i));
		}
		
		cardDeck1 = new CardDeck(cards);
	}
	
	
	@After
	public void tearDown(){
		// Empty the deck
		cards = null;
	}
	
	
	@Test
	public void testGetDeck(){
		assertEquals(cards,cardDeck1.getDeck());
	}
	
	
	@Test
	public void testAddCard(){
		ArrayList<Card> newCards = new ArrayList<Card>();
		// Populate cards
		for (int i = 0; i <4; i++){
			newCards.add(new Card(i));
		}
		
		for (int cardInd = 0; cardInd < newCards.size(); cardInd++){
			cardDeck1.addCard(newCards.get(cardInd));
			//assertEquals(cards.size()+(cardInd), cardDeck1.getDeck().size());
		}
		assertEquals(8, cardDeck1.getDeck().size());
	}
	
	
	@Test
	public void testRemoveTopCard(){
		assertEquals(cardDeck1.getDeck().get(0), cardDeck1.removeTopCard());
	}
	
}