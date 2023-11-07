import java.util.*;
import java.io.*;


public class CardGame{
	
	public static void main(String[] args) {
		
		// Attributes
		ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
		ArrayList<Player> allPlayers = new ArrayList<Player>();
		
		for (int i = 0; i < 2; i++){
			Player play = new Player(i);
			allPlayers.add(play);
		}
		
		for (int i = 0; i < 2; i++){
			CardDeck deck = new CardDeck(i);
			allCardDecks.add(deck);
		}
		
		String numPlayersCheck;
		// Check no players
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter number of players: ");
			numPlayersCheck = myObj.nextLine();
		
			try {
				int number = Integer.parseInt(numPlayersCheck);
				if (number < 1){
					throw new NumberFormatException();
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid player number input");
			}
		}
		int numPlayers = Integer.parseInt(numPlayersCheck);
		
		// Validate the file + pack
		String fileName;
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter file path: ");
			fileName = myObj.nextLine();
			
			boolean validity = Helper.checkPack(fileName, numPlayers);
			if (validity == true){
				break;
			}
		}
		
		// Read cards into an array
		ArrayList<Card> initialPack =  new ArrayList<Card>();
		File packFile = new File(fileName);
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
		
		for (int i = 0; i < 4; i++){ 
			System.out.println(allCardDecks.get(1).getDeck().get(i).getValueOf());
		}
	}
	
}