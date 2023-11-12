import java.util.*;
import java.io.*;


public class CardGame{
	
	// Attributes
	private ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
	private ArrayList<Player> allPlayers = new ArrayList<Player>();

	public static void main(String[] args) {
		
		int numPlayers = Helper.playerAmount();
		
		File packFile = Helper.getPack(numPlayers);

		// Read cards into an array
		ArrayList<Card> initialPack =  new ArrayList<Card>();
		try {
			Scanner myReader = new Scanner(packFile);
			while (myReader.hasNextLine()) {
				String data2 = myReader.nextLine();
				int data3 = Integer.parseInt(data2);
				initialPack.add(new Card(data3));
			}
		} catch (FileNotFoundException e) {
			  System.out.println("Incorrect file name/path");
		}



		// Give cards to players
		int counter = 0;
		while(counter < 4*numPlayers){
			allPlayers.get(counter%numPlayers).addCard(initialPack.get(counter));
			counter++;
		}
		
		// Give cards to decks
		int counter2 = 4*numPlayers;
		while(counter2 < 8*numPlayers){
			allCardDecks.get(counter2%numPlayers).addCard(initialPack.get(counter2));
			counter2++;
		}
		
	}
}