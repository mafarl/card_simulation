import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class CardGameTest {
    private CardGame.PlayerThread playerThread1;
    private CardGame.PlayerThread playerThread2;

    @Before
	public void setUp(){
        ArrayList<Card> cards = new ArrayList<Card>();
        Player player1 = new Player(0, cards);
        Player player2 = new Player(1, cards);

        playerThread1 = new CardGame.PlayerThread(player1, 0);
        playerThread2 = new CardGame.PlayerThread(player2, 1);
        
        
	}

    @Test
    public void testGame()  {

        int initialThreadCount = Thread.activeCount();

        playerThread1.start();
        playerThread2.start();

        assertEquals("Thread-0", playerThread1.getName());
        assertEquals("Thread-1", playerThread2.getName());

        int finalThreadCount = Thread.activeCount();

        assertEquals(initialThreadCount+2, finalThreadCount);


        }
    }

