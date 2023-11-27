import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CardTest{

	private Card card;
	
	// Creates a card
	@Before
	public void setUp(){
		card = new Card(1);
	}
	
	// Tests the getValueOf() method
	@Test
	public void testGetValueOf(){
		assertEquals(1, card.getValueOf());
	}
	
}