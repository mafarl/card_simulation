import org.junit.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


public class HelperTest{
	
	private ArrayList<Card> cardsInDeck = new ArrayList<Card>();
	private ArrayList<Card> cardsInPlayerHand = new ArrayList<Card>();
	File myObj;
	
	// Set up and get the pack
	Card card1 = new Card(1);
	Card card2 = new Card(2);
	Card card3 = new Card(3);
	Card card4 = new Card(4);

	@Before
	public void setUp(){
		cardsInDeck.add(card1);
		cardsInDeck.add(card2);
		cardsInDeck.add(card3);
		cardsInDeck.add(card4);
		
		cardsInPlayerHand.add(card1);
		cardsInPlayerHand.add(card2);
		cardsInPlayerHand.add(card3);
		cardsInPlayerHand.add(card4);
		

			// Create a file here and input valid data with 8*numPlayers - 1
			// Then we'll be adding one invalid string in each exception test
		try {
			myObj = new File("testPath.txt");
			if (myObj.createNewFile()) {
				
				FileWriter myWriter = new FileWriter("testPath.txt", false);
				for (int i=0; i<23; i++){
					myWriter.write(Integer.toString(1));
					myWriter.write("\n");
				}
				myWriter.close();
			} 
		} catch (IOException e) {}
		
	}
	
	@After
	public void tearDown(){
		cardsInDeck = null;
		cardsInPlayerHand = null;

		myObj = new File("testPath.txt");
		try {
			FileWriter myWriterNew = new FileWriter("testPath.txt", false);
				for (int i=0; i<23; i++){
					myWriterNew.write(Integer.toString(1));
					myWriterNew.write("\n");
				}
				myWriterNew.close();
		} catch (Exception e) {}


	}
	
	@Test
	public void testGetPackValid(){
		try{
			FileWriter myWriterValid = new FileWriter("testPath.txt", true);
            myWriterValid.write(Integer.toString(1));
            myWriterValid.close();
        } catch (IOException e){}
        
		assertTrue(Helper.getPack(3, "testPath.txt"));
	}
	
	@Test
	public void testGetPackNumberFormat(){
		try{
			FileWriter myWriterFormat = new FileWriter("testPath.txt", true);
            myWriterFormat.write(Integer.toString(-1));
            myWriterFormat.close();
        } catch (IOException e){}

		assertFalse(Helper.getPack(3, "testPath.txt"));

	}
	
	@Test
	public void testGetPackInputMismatch(){
		try{
			FileWriter myWriterMismatch = new FileWriter("testPath.txt",true);
            myWriterMismatch.write("a");
            myWriterMismatch.close();
        } catch (IOException e){}
        
		assertFalse(Helper.getPack(3, "testPath.txt"));

	}
	
	@Test
	public void testGetPackNoSuchElement(){
		try{
			FileWriter myWriterNoSuchElement = new FileWriter("testPath.txt", true);
            myWriterNoSuchElement.write(" ");
            myWriterNoSuchElement.close();
        } catch (IOException e){}
        
		assertFalse(Helper.getPack(3, "testPath.txt"));
	}
	

	@Test
	public void testPlayerAmount(){
		// Need to print your own number in the terminal and check
		String test1 = "string input";
		String test2 = "-10";
		String test3 = "3";
		Scanner scanner1 = new Scanner(test1);
		// String input
		assertEquals(-1, Helper.playerAmount(scanner1));
		Scanner scanner2 = new Scanner(test2);
		// Negative player amount input
		assertEquals(-1, Helper.playerAmount(scanner2));
		Scanner scanner3 = new Scanner(test3);
		// Correct input
		assertEquals(3, Helper.playerAmount(scanner3));
	}
	
	// index out of bounds
	@Test
	public void testAddingOutputFilePlayer(){
		String fileName = "player"+ Integer.toString(2) +"_output.txt";
		Helper.addingToOutputFile(1, "test");
		Path path = Paths.get(fileName);
		assertTrue(Files.exists(path));
		try{
			Files.delete(path);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
	
	@Test
	public void testInitialOutputFilePlayer(){
		String fileName = "player"+ Integer.toString(2) +"_output.txt";
		Helper.initialOutputFile(1, "test");
		Path path = Paths.get(fileName);
		assertTrue(Files.exists(path));
		try{
			Files.delete(path);
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}
	
	@Test
	public void testOutputFileDeck(){
		String fileName = "deck"+ Integer.toString(2) +"_output.txt";
		Helper.outputFileDeck(1, cardsInDeck);
		Path path = Paths.get(fileName);
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
		String output = Helper.printHand(cardsInPlayerHand);
		assertEquals("1 2 3 4 ", output);
	}
}