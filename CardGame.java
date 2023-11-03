import java.util.*;
import java.io.*;


public class CardGame{
	
	// Attributes
	private ArrayList<CardDeck> allCardDecks = new ArrayList<CardDeck>();
	private ArrayList<Player> allPlayers = new ArrayList<Player>();

	public static void main(String[] args) {
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter number of players: ");
			String numPlayers = myObj.nextLine();
		
			try {
				int number = Integer.parseInt(numPlayers);
				if (number < 1){
					throw new NumberFormatException();
				}
				System.out.println("Converted integer: " + number);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Invalid player number input");
			}
		}
		
		while (true){
			Scanner myObj = new Scanner(System.in);
			System.out.println("Enter file path: ");
			String fileName = myObj.nextLine();
			
			try {
			  File myObj2 = new File(fileName);
			  Scanner myReader = new Scanner(myObj2);
			  while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			  }
			  myReader.close();
			  break;
			} catch (FileNotFoundException e) {
			  System.out.println("An error occurred.");
			  e.printStackTrace();
			}
		}
	}
	
	
	
}