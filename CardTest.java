import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;

public class CardTest{

	private Card card;
	
	@Before
	public void setUp(){
		card = new Card(1);
	}
	
	@Test
	public void testGetValueOf(){
		assertEquals(1, card.getValueOf());
	}
	
}