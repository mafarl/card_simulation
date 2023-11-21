import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.util.ArrayList;


public class HelperTest{
	
	private int numPlayers = 3;
	private File tempFile;
	private ArrayList<Card> cardsInDeck;
	private ArrayList<Card> cardsInPlayerHand;
	
	
	// Set up and get the pack
	Card card1 = new Card(1);
	Card card2 = new Card(2);
	Card card3 = new Card(3);
	cardsInDeck.add(card1);
	cardsInDeck.add(card2);
	cardsInDeck.add(card3);
	
	cardsInPlayerHand.add(card1);
	cardsInPlayerHand.add(card2);
	cardsInPlayerHand.add(card3);

	
	@Test
	public void testGetPack(){
		tempFile = Helper.getPack();
		assertTrue(tempFile.exists());
	}

	
	@Test
	public void testPlayerAmount(){
		// Need to print your own number in the terminal and check
		assertEquals("3", Helper.playerAmount());
	}
	
	
	@Test
	public void testCheckPack(){
		assertTrue(Helper.checkPack(tempFile, numPlayers));
	}
	
	
	@Test
	public void testOutputFilePlayer(1, cardsInPlayerHand){
		File file_ = Helper.outputFileDeck(1, cardsInPlayerHand);
		assertTrue(file_.exists());
		file_.delete();
	}

	
	@Test
	public void testOutputFileDeck(){
		File file_ = Helper.outputFileDeck(1, cardsInDeck);
		assertTrue(file_.exists());
		file_.delete();
	}

	
	@Test
	public void testPrintHand(){
		String output = Helper.printHand(1, cardsInPlayerHand);
		assertEquals("1 2 3 ", output);
	}
}