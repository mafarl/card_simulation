import java.util.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;


public class CardGame{

	// Attributes
	private static ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
	private static ArrayList<Player> allPlayers = new ArrayList<Player>();
	private static ArrayList<PlayerThread> allPlayersThreads = new ArrayList<PlayerThread>();
	
	// Records if a player has won the game
	// Volatile to force compiler to check won every time
	static volatile boolean won = false;
	// To make sure only one player wins in a single game
	static AtomicBoolean counter = new AtomicBoolean(false);

	static class PlayerThread extends Thread{

		private Player player;
		private int playerIndex;

		@Override
		public void run(){
			if (player.checkHand()) {
				if (counter.get() == false){
					counter = new AtomicBoolean(true);
					won = true;
					gameWon(player, playerIndex);
				}
			}
			else{
				while (!won){
					
					if (player.checkHand()){
						if (counter.get() == false){
							counter = new AtomicBoolean(true);
							won = true;
							gameWon(player, playerIndex);
						}
					}
					try {
						Thread.sleep(100);
					}
					catch (InterruptedException e){
						break;
					}
					

					// removing card from player hand, adds it to deck to right of player
					Card removedCard = player.removeCard();
					String text = "Player " + Integer.toString(playerIndex + 1) + " discards a " + Integer.toString(removedCard.getValueOf()) + " to deck " + Integer.toString((playerIndex+1)%(allPlayers.size())+1);
					Helper.addingToOutputFile(playerIndex, text);
					allCardDecks.get((playerIndex+1)%(allPlayers.size())).addCard(removedCard);

					// takes card from top of deck to left of player, adds to hand
					Card topCard = allCardDecks.get((playerIndex)).removeTopCard();
					String text2 = "Player " + Integer.toString(playerIndex+1) + " draws a " + Integer.toString(topCard.getValueOf()) + " from deck " + Integer.toString(playerIndex + 1);
					Helper.addingToOutputFile(playerIndex, text2);
					player.addCard(topCard);
					
					String text3 = "Player " + Integer.toString(playerIndex + 1) + " current hand is " + Helper.printHand(playerIndex, player.getHand());
					Helper.addingToOutputFile(playerIndex, text3);
					
				}
			}
		}


		// Constructor
		public PlayerThread(Player player, int playerIndex){
			this.player = player;
			this.playerIndex = playerIndex;
		}
	}
	
	
	// What each the thread that won prints out at the end
	public static synchronized void gameWon(Player player, int playerIndex){
		System.out.println("Player " + Integer.toString(playerIndex + 1) + " wins");
		System.out.println("Player " + Integer.toString(playerIndex + 1) + " exits");
		System.out.println("Player " + Integer.toString(playerIndex + 1) + " final hand: " + Helper.printHand(playerIndex, player.getHand()));
		
		for (int i = 0; i < allPlayers.size(); i++ ){
			//Helper.finalOutputFilePlayer(i, allPlayers.get(i).getHand());
			Helper.outputFileDeck(i, allCardDecks.get(i).getDeck());
			if (i != playerIndex){
				allPlayersThreads.get(i).interrupt();
			}
		}
		
		Thread.currentThread().interrupt();
	}
	

	public static void main(String[] args) {
		
		int numPlayers;
		while (true){
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter number of players: ");
				numPlayers = Helper.playerAmount(myObj);
				if (numPlayers != -1) {
					break;
				}
			}
		
		String fileName;
		while (true){
			Scanner myObj2 = new Scanner(System.in);
			System.out.println("Enter file path");
			fileName = myObj2.nextLine();
			if (Helper.getPack(numPlayers, fileName)){
				break;
			}
		}
		
		File packFile = new File(fileName);



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

		
		// Do separate functions for that
		// making all the players and decks
        for (int i = 0; i < numPlayers; i++){
			Player play = new Player(i, new ArrayList<Card>());
			allPlayers.add(play);
			CardDeck deck = new CardDeck(new ArrayList<Card>());
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
		
		// Print out initial hands of players
		for (int i = 0; i < allPlayers.size(); i++){
			String text4 = "Player " + Integer.toString(i + 1) + " initial hand " + Helper.printHand(i, allPlayers.get(i).getHand());
			Helper.addingToOutputFile(i, text4);
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

