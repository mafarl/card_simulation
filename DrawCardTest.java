import org.junit.*;
import static org.junit.Assert.*;

public class DrawCardTest {

    @Test
    public void testCardDraw() {

        Player player1 = new Player(0);
        CardDeck cardDeck1 = new CardDeck();

        assertEquals(0, player1.getHand().size());
        assertEquals(0, cardDeck1.getDeck().size());


        Card card1 = new Card(1);
        Card card2 = new Card(2);
        cardDeck1.addCard(card1);
        cardDeck1.addCard(card2);

        int topCardValue = card1.getValueOf();
        Card removedCard = cardDeck1.removeTopCard();
        player1.addCard(removedCard);

        Assert.assertEquals(topCardValue, removedCard.getValueOf());
        Assert.assertEquals(1, cardDeck1.getDeck().size());


    }
}
