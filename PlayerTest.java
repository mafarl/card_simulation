import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

public class PlayerTest{
	
	private int playerIndex = 0;
	private Player player1;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	// Creates an initial player with a hand
	@Before
	public void setUp(){
		for (int i = 0; i <4; i++){
			cards.add(new Card(i));
		}
		
		player1 = new Player(playerIndex, cards);
	}
	
	// Empties the hand
	@After
	public void tearDown(){
		cards.removeAll(cards);
	}

	// Tests player is constructed correctly
	@Test
	public void testConstructPlayer(){
		assertEquals(playerIndex, player1.getPlayerIndex());
		assertEquals(cards.size(), player1.getHand().size());
	}
	
	// Tests get hand returns hand
	@Test
	public void testGetHand(){
		assertEquals(cards, player1.getHand());
	}
	
	// Tests check hand returns false for a non-winning hand
	@Test
	public void testCheckHand(){
		assertFalse(player1.checkHand());
	}
	
	// Tests a card is removed, and that it is not the preferred denomination
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
	
	// Test adding card to player hand
	@Test
	public void testAddCard(){
		ArrayList<Card> newCards = new ArrayList<Card>();
		for (int i = 0; i <4; i++){
			newCards.add(new Card(i));
		}
		
		for (int cardInd = 0; cardInd < newCards.size(); cardInd++){
			player1.addCard(newCards.get(cardInd));
		}
		assertEquals(8, player1.getHand().size());
	}

}
