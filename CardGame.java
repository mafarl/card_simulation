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

		// Thread run function
		@Override
		public void run(){
			if (player.checkHand()) {
				// Only one player can call gameWon due to counter variable
				if (counter.get() == false){
					counter = new AtomicBoolean(true);
					won = true;
					gameWon(player, playerIndex);
				}
			}
			else{
				while (!won){
					if (player.checkHand()){
						// Only one player can call gameWon due to counter variable
						if (counter.get() == false){
							counter = new AtomicBoolean(true);
							won = true;
							gameWon(player, playerIndex);
						}
					}

					// Wait for 100 milliseconds before every turn to avoid one thread taking cards too fast
					try {
						Thread.sleep(100);
					}
					catch (InterruptedException e){
						break;
					}
					
					// Removes card from player hand, adds it to deck to right of player
					Card removedCard = player.removeCard();
					String text = "Player " + Integer.toString(playerIndex + 1) + " discards a " + Integer.toString(removedCard.getValueOf()) + " to deck " + Integer.toString((playerIndex+1)%(allPlayers.size())+1);
					Helper.addingToOutputFile(playerIndex, text);
					allCardDecks.get((playerIndex+1)%(allPlayers.size())).addCard(removedCard);

					// Removes card from top of deck to left of player, adds to hand
					Card topCard = allCardDecks.get((playerIndex)).removeTopCard();
					String text2 = "Player " + Integer.toString(playerIndex+1) + " draws a " + Integer.toString(topCard.getValueOf()) + " from deck " + Integer.toString(playerIndex + 1);
					Helper.addingToOutputFile(playerIndex, text2);
					player.addCard(topCard);
					
					// Adds current hand to player output file
					String text3 = "Player " + Integer.toString(playerIndex + 1) + " current hand is " + Helper.printHand(player.getHand());
					Helper.addingToOutputFile(playerIndex, text3);
				}
			}
		}

		// Constructor

		/**
		 * Creates a player thread
		 * @param player object to create thread for
		 * @param playerIndex index of player
		 */
		public PlayerThread(Player player, int playerIndex){
			this.player = player;
			this.playerIndex = playerIndex;
		}
	}
	
	/**
	 * Ends game, creates all files for all players and decks
	 * @param player player that won
	 * @param playerIndex index of player
	 */
	public static synchronized void gameWon(Player player, int playerIndex){
		// Wait 100 milliseconds to make sure all players have finished their turn
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e){}

		String textWins = "Player " + Integer.toString(playerIndex + 1) + " wins";
		System.out.println(textWins);
		
		// Creates all output files for all players and decks
		for (int i = 0; i < allPlayers.size(); i++ ){
			String textExits = "Player " + Integer.toString(i + 1) + " exits";
			String textFinal = "Player " + Integer.toString(i + 1) + " final hand: " + Helper.printHand(allPlayers.get(i).getHand());
			Helper.outputFileDeck(i, allCardDecks.get(i).getDeck());
			if (i == playerIndex){
				Helper.addingToOutputFile(i, textWins);
			} else {
				String textwhowon = "Player " + Integer.toString(i + 1) + " has been informed player " + Integer.toString(playerIndex + 1) + " has won";
				Helper.addingToOutputFile(i, textwhowon);
			}
			Helper.addingToOutputFile(i, textExits);
			Helper.addingToOutputFile(i, textFinal);
		}
		// Ends game
		Thread.currentThread().interrupt();
	}
	

	public static void main(String[] args) {
		// Gets number of players
		int numPlayers;
		while (true){
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter number of players: ");
				numPlayers = Helper.playerAmount(myObj);
				if (numPlayers != -1) {
					break;
				}
			}
		
		// Gets file name and validates it with getPack function
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

		// Read cards into an array from packFile
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

		// Creates all players, decks and playerThreads
        for (int i = 0; i < numPlayers; i++){
			Player play = new Player(i, new ArrayList<Card>());
			allPlayers.add(play);
			CardDeck deck = new CardDeck(new ArrayList<Card>());
			allCardDecks.add(deck);
			PlayerThread playerThread = new PlayerThread(play, i);
			allPlayersThreads.add(playerThread);
		}

		// Distributes cards to players in round robin order
		int counter = 0;
		while(counter < 4*numPlayers){
			allPlayers.get(counter%numPlayers).addCard(initialPack.get(counter));
			counter++;
		}
		
		// Writes initial hand of players to file
		for (int i = 0; i < allPlayers.size(); i++){
			String textInitial = "Player " + Integer.toString(i + 1) + " initial hand " + Helper.printHand(allPlayers.get(i).getHand());
			Helper.initialOutputFile(i, textInitial);
		}
		
		// Distributes cards to decks in round robin order
		int counter2 = 4*numPlayers;
		while(counter2 < 8*numPlayers){
			allCardDecks.get(counter2%numPlayers).addCard(initialPack.get(counter2));
			counter2++;
		}
		
		// Starts all threads
		for (int i = 0; i < numPlayers; i++){ 
			allPlayersThreads.get(i).start();
		}
	}
}

