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


	@Test
	public void testGetPackFileNotFound(){
		try{
			Helper.getPack(3, "RandomFile.txt");
			fail();
		} catch (FileNotFoundException e) {

		}
	}
	
	@Test
	public void testGetPackValid(){
		assertTrue(Helper.getPack(3, "testPath.txt"));
	}
	
	@Test
	public void testGetPackNumberFormat(){
		try{
			FileWriter myWriter = new FileWriter("testPath.txt");
			FileWriter myWriter = new FileWriter("testPath.txt");
            myWriter.write(-1);
            myWriter.write("\n");
        } catch (IOException e){}
        
		try{
			Helper.getPack(3, "testPath.txt");
			fail();
		} catch (NumberFormatException e) {
			//Passes if this is called. No Such element exception is thrown if the program asks for another user input
            //as the first input provided is invalid.
		}
	}
	
	@Test
	public void testGetPackInputMismatch(){
		try{
            myWriter.write("a");
            myWriter.write("\n ");
        } catch (IOException e){}
        
		try{
			Helper.getPack(3, "testPath.txt");
			fail();
		} catch (InputMismatchException e) {

		}
	}
	
	@Test
	public void testGetPackNoSuchElement(){
		try{
			Helper.getPack(3, "testPath.txt");
			fail();
		} catch (NoSuchElementException e) {

		}
	}
	

	@Test
	public void testPlayerAmount(){
		// Need to print your own number in the terminal and check
		String test1 = "string input";
		int test2 = -10;
		int test3 = 3;
		Scanner scanner1 = new Scanner(test1);
		// String input
		assertEquals(-1, Helper.playerAmount());
		Scanner scanner2 = new Scanner(test2);
		// Negative player amount input
		assertEquals(-1, Helper.playerAmount());
		Scanner scanner3 = new Scanner(test3);
		// Correct input
		assertEquals(3, Helper.playerAmount());
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