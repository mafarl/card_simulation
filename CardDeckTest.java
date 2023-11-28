import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.util.ArrayList;

public class CardDeckTest{

	private CardDeck cardDeck1;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	// Populate the deck
	@Before
	public void setUp(){
		for (int i = 0; i <4; i++){
			cards.add(new Card(i));
		}
		cardDeck1 = new CardDeck(cards);
	}
	
	// Empty the deck
	@After
	public void tearDown(){
		cards = null;
	}
	
	// Test deck getter
	@Test
	public void testGetDeck(){
		assertEquals(cards,cardDeck1.getDeck());
	}
	
	// Test adding card to deck
	@Test
	public void testAddCard(){
		ArrayList<Card> newCards = new ArrayList<Card>();
		// Populate cards
		for (int i = 0; i <4; i++){
			newCards.add(new Card(i));
		}
		
		for (int cardInd = 0; cardInd < newCards.size(); cardInd++){
			cardDeck1.addCard(newCards.get(cardInd));
		}
		assertEquals(8, cardDeck1.getDeck().size());
	}

	// Test removing card from deck
	@Test
	public void testRemoveTopCard(){
		assertEquals(cardDeck1.getDeck().get(0), cardDeck1.removeTopCard());
	}
}