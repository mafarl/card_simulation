import org.junit.*;
import static org.junit.Assert.*;

import java.nio.file.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class HelperTest{
	
	private int numPlayers = 3;
	private File tempFile;
	private ArrayList<Card> cardsInDeck = new ArrayList<Card>();
	private ArrayList<Card> cardsInPlayerHand = new ArrayList<Card>();
	
	
	// Set up and get the pack
	Card card1 = new Card(1);
	Card card2 = new Card(2);
	Card card3 = new Card(3);

	@Before
	public void setUp(){
		cardsInDeck.add(card1);
		cardsInDeck.add(card2);
		cardsInDeck.add(card3);
		
		cardsInPlayerHand.add(card1);
		cardsInPlayerHand.add(card2);
		cardsInPlayerHand.add(card3);
	}
	
	@After
	public void tearDown(){
		cardsInDeck = null;
		cardsInPlayerHand = null;
	}

	// doesnt work this way
	@Test
	public void testGetPack(){
		tempFile = Helper.getPack(2);
		String filePath = "testPath.txt";

		String simulatedUserInput = filePath;

		InputStream savedStandardInputStream = System.in;
		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

		// code that needs multiple user inputs

		System.setIn(savedStandardInputStream);

		assertTrue(tempFile.exists());
		}

	// doesnt work this way
	// possible to use system.in to simulate user input
	@Test
	public void testPlayerAmount(){
		// Need to print your own number in the terminal and check
		assertEquals("3", Helper.playerAmount());
	}
	
	
	@Test
	public void testCheckPack(){
		try{
			assertTrue(Helper.checkPack(tempFile, numPlayers));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	// index out of bounds
	@Test
	public void testOutputFilePlayer(){
		String fileName = "player"+ Integer.toString(1) +"_output.txt";
		Path path = Paths.get(fileName);
		Helper.outputFileDeck(1, cardsInPlayerHand);
		assertTrue(Files.exists(path));
		try{
			Files.delete(path);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	
	@Test
	public void testOutputFileDeck(){
		String fileName = "deck"+ Integer.toString(1) +"_output.txt";
		Path path = Paths.get(fileName);
		Helper.outputFileDeck(1, cardsInDeck);
		assertTrue(Files.exists(path));
		try{
			Files.delete(path);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	// index out of bounds
	@Test
	public void testPrintHand(){
		String output = Helper.printHand(1, cardsInPlayerHand);
		assertEquals("1 2 3 ", output);
	}
}