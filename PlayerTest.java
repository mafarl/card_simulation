import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

public class PlayerTest{
	
	private int playerIndex = 0;
	private Player player1;
	
	@Before
	public void setUp(){
		ArrayList<Card> cards = new ArrayList<Card>();
		// Populate hand
		for (int i = 0; i <4; i++){
			cards.add(new Card(i));
		}
		
		player1 = new Player(playerIndex, cards);
	}
	
	
	@After
	public void tearDown(){
		// Empty hand
		cards = null;
	}
	
	
	@Test
	public void testGetHand(){
		assertEquals(cards, player.getHand());
	}
	
	
	@Test
	public void testCheckHand(){
		assertFalse(player.checkHand());
	}
	
	
	@Test
	public void testRemoveCard(){
		// Need to check that the cards removed are not 1s
		for (int i = 0; i < (cards.size() - 1); i++){
			assertNotEquals(player.getHand().get(1), player.removeCard().getValueOf());
			// Checking size of the hand (should be decreasing by 1)
			assertEquals(cards.size()-(i+1), player.getHand().size());
		}
	}
	
	
	@Test
	public void testAddCard(){
		ArrayList<Card> newCards = new ArrayList<Card>();
		// Populate hand
		for (int i = 0; i <4; i++){
			newCards.add(new Card(i));
		}
		
		for (int cardInd = 0; cardInd < newCards.size(); cardInd++){
			player.addCard(newCards.get(cardInd));
			assertEquals(cards.size()+(i+1), player.getHand().size());
		}
	}

}
