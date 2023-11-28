import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CardGameTest {
    private CardGame.PlayerThread playerThread1;
    private CardGame.PlayerThread playerThread2;
    private Player player1;
    private Player player2;
    private ArrayList<Player> allPlayers = new ArrayList<Player>();
    private ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();

    // Create players with a hand and their threads
    @Before
	public void setUp(){
        ArrayList<Card> cards = new ArrayList<Card>();
        // Populate cards array
        cards.add(new Card(1));
        cards.add(new Card(2));
        cards.add(new Card(3));
        cards.add(new Card(4));

        // Add players to the allPlayers ArrayList
        player1 = new Player(0, cards);
        player2 = new Player(1, cards);
        allPlayers.add(player1);
        allPlayers.add(player2);

        // Add card decks with the same cards ArrayList to allCardDecks
        allCardDecks.add(new CardDeck(cards));
        allCardDecks.add(new CardDeck(cards));

        // Create player threads
        playerThread1 = new CardGame.PlayerThread(player1, 0);
        playerThread2 = new CardGame.PlayerThread(player2, 1);
        playerThread1.setName("playerThread1");
        playerThread2.setName("playerThread2");
          
	}

    // Tests thread starting
    @Test
    public void testGame()  {

        int initialThreadCount = Thread.activeCount();
        playerThread1.start();
        playerThread2.start();



        assertEquals("playerThread1", playerThread1.getName());
        assertEquals("playerThread2", playerThread2.getName());

        int finalThreadCount = Thread.activeCount();

        assertEquals(initialThreadCount+2, finalThreadCount);

        }


    // No need to test threads here since was tested in the function above
    @Test
    public void testGameWonOutput() {
        // Save the current System.out to restore it later
        PrintStream originalOut = System.out;

        try {
            // Create a ByteArrayOutputStream to capture printed output
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));

            CardGame.gameWon(allPlayers, allCardDecks, player1, 0);

            // Part that deletes the files created by the Helper functions called inside gameWon()
            String playerfileName;
            String deckfileName;
            Path playerpath;
            Path deckpath;
            for (int i = 1; i <3; i++){
                playerfileName = "player"+ Integer.toString(i) +"_output.txt";
                deckfileName = "deck"+ Integer.toString(i) +"_output.txt";
                playerpath = Paths.get(playerfileName);
                deckpath = Paths.get(deckfileName);
                try{
                    Files.delete(playerpath);
                    Files.delete(deckpath);
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            
            // Get the printed content from the ByteArrayOutputStream
            String printedOutput = outputStreamCaptor.toString().trim();

            // Assert - Check the expected output printed to System.out
            assertTrue(printedOutput.contains("Player 1 wins"));

        } finally {
            // Restore System.out to its original state
            System.setOut(originalOut);
        }
    }

}

   
