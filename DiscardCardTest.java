import org.junit.*;
import static org.junit.Assert.*;

public class DiscardCardTest {

    @Test
    public void testCardDiscard() {

        Player player1 = new Player(0);
        CardDeck cardDeck1 = new CardDeck();

        assertEquals(0, player1.getHand().size());
        assertEquals(0, cardDeck1.getDeck().size());


        Card card1 = new Card(1);
        Card card2 = new Card(2);
        cardDeck1.addCard(card1);
        cardDeck1.addCard(card2);
        
        Card cardToRemove = player1.removeCard();
        cardDeck1.addCard(cardToRemove);

        // Checking that the removed card is not 1
        Assert.assertEquals(card2.getValueOf(), cardToRemove.getValueOf());
        Assert.assertEquals(3, cardDeck1.getDeck().size());


    }
}
