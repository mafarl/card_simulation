import java.util.*;
import java.io.*;


public class CardGameTest{
	


	public static void main(String[] args) {

        // Attributes
        ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
        ArrayList<Player> allPlayers = new ArrayList<Player>();
		
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




        for (int i = 0; i < 2; i++){
			Player play = new Player(i);
			allPlayers.add(play);
		}

		for (int i = 0; i < 2; i++){
			CardDeck deck = new CardDeck(i);
			allCardDecks.add(deck);
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
		
        System.out.println(allPlayers.get(0).checkHand());
        Card removed = allPlayers.get(0).removeCard();


	}
}