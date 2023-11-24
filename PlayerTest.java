import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

public class PlayerTest{
	
	private int playerIndex = 0;
	private Player player1;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	@Before
	public void setUp(){
		
		// Populate hand
		for (int i = 0; i <4; i++){
			cards.add(new Card(i));
		}
		
		player1 = new Player(playerIndex, cards);
	}
	
	
	@After
	public void tearDown(){
		// Empty hand
		cards.removeAll(cards);
	}

	@Test
	public void testConstructPlayer(){
		assertEquals(playerIndex, player1.getPlayerIndex());
		assertEquals(cards.size(), player1.getHand().size());
	}
	
	
	@Test
	public void testGetHand(){
		assertEquals(cards, player1.getHand());
	}
	
	
	@Test
	public void testCheckHand(){
		assertFalse(player1.checkHand());
	}
	
	// expected 2 but was 3
	@Test
	public void testRemoveCard(){
		// Need to check that the cards removed are not 1s
		assertEquals(4, cards.size());
		for (int i = 0; i < (cards.size()+1); i++){
			assertNotEquals(1, player1.removeCard().getValueOf());
			// Checking size of the hand (should be decreasing by 1)
		}
		assertEquals(1, player1.getHand().size());
	}
	
	// expected 6 but was 5
	// similar to deck one
	@Test
	public void testAddCard(){
		ArrayList<Card> newCards = new ArrayList<Card>();
		// Populate hand
		for (int i = 0; i <4; i++){
			newCards.add(new Card(i));
		}
		
		for (int cardInd = 0; cardInd < newCards.size(); cardInd++){
			player1.addCard(newCards.get(cardInd));
		}
		assertEquals(8, player1.getHand().size());
	}

}
