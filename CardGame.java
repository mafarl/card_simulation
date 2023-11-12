import java.util.*;
import java.io.*;


public class CardGame{

	// Attributes
	private static ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
	private static ArrayList<Player> allPlayers = new ArrayList<Player>();
	private static ArrayList<PlayerThread> allPlayersThreads = new ArrayList<PlayerThread>();


	static class PlayerThread extends Thread{

		private Player player;
		private int playerIndex;

		@Override
		public void run(){
			if (player.checkHand()) {
				gameWon();
			}
			else{
				while (!player.checkHand()){
					try {
					allPlayersThreads.get(playerIndex).sleep(1000);
					}
					catch ( InterruptedException e){
						System.out.println("interuptexception");
					}

					// removing card from player hand, adds it to deck to right of player
					Card removedCard = player.removeCard();
					allCardDecks.get(playerIndex % (allPlayers.size()-1)).addCard(removedCard);

					// takes card from top of deck to left of player, adds to hand
					Card topCard = allCardDecks.get(playerIndex).removeTopCard();
					System.out.println("Player" + Integer.toString(playerIndex+1) + "gets card " + Integer.toString(topCard.getValueOf()));
					player.addCard(topCard);
					
				}
				gameWon();
			}
		}

		public synchronized boolean gameWon(){
			System.out.println("Player " + Integer.toString(playerIndex + 1) + "won");
			System.out.println("PRINT THE HAND HERE for test");
			for (int i = 0; i < allPlayers.size(); i++ ){
				if (i != playerIndex){
					allPlayersThreads.get(i).interrupt();
				}
			}
			System.out.println("Player " + Integer.toString(playerIndex + 1) + "won");
			System.out.println("PRINT THE HAND HERE for test");
			allPlayersThreads.get(playerIndex).interrupt();
			return true;
		}

		// Constructor
		public PlayerThread(Player player, int playerIndex){
		this.player = player;
		this.playerIndex = playerIndex;
		}
	}

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

		

		// making all the players and decks
        for (int i = 0; i < numPlayers; i++){
			Player play = new Player(i);
			allPlayers.add(play);
			CardDeck deck = new CardDeck(i);
			allCardDecks.add(deck);
			PlayerThread playerThread = new PlayerThread(play, i);
			allPlayersThreads.add(playerThread);
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


		for (int i = 0; i < numPlayers; i++){ 
			allPlayersThreads.get(i).start();
		}


		


		






	}


}

