import java.util.*;
import java.io.*;


public class CardGame{
	
	// Attributes
	private ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
	private ArrayList<Player> allPlayers = new ArrayList<Player>();

	public static void main(String[] args) {
		
		String numPlayers;
		// Check no players
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter number of players: ");
			numPlayers = myObj.nextLine();
		
			try {
				int number = Integer.parseInt(numPlayers);
				if (number < 1){
					throw new NumberFormatException();
				}
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid player number input");
			}
		}
		
		// Validate the file + pack
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter file path: ");
			String fileName = myObj.nextLine();
			
			boolean validity = Helper.checkPack(fileName, Integer.parseInt(numPlayers));
			if (validity == true){
				break;
			}
		}
	}
	
}