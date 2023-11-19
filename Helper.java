import java.util.*;
import java.io.*;

/**
 * Helper class for methods in other classes. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class Helper{

	public static int playerAmount(){
		// Check no players
		while (true){
			try {
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter number of players: ");
                int numPlayers = myObj.nextInt();
				if (numPlayers >= 1){
                	return(numPlayers);
                    
				}
				System.out.println("Enter an integer greater than 0");
			} catch (InputMismatchException e) {
				System.out.println("Invalid player number input");
			}
		}
    }

	public static File getPack(int playerAmount){
		while (true){
			try{
				// Getting filename
				Scanner myObj2 = new Scanner(System.in);
				System.out.println("Enter file path");
				String fileName = myObj2.nextLine();
				File packFile = new File(fileName); 

				if (!packFile.exists()){
					throw new FileNotFoundException();
				}
				
				// Check if the pack is valid
				if (Helper.checkPack(packFile, playerAmount)){
					System.out.println("Card pack loaded successfully");
					return packFile;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid card face value, non-integer found");
			} catch (FileNotFoundException e){
				System.out.println("Incorrect file name/path");
			} catch (NumberFormatException e){
				System.out.println("Invalid card face value, integer less than 1 found");
			} catch (NoSuchElementException e){
				System.out.println("Invalid pack, whitespace in file");
			}
			
		}
	}

	public static boolean checkPack(File packFile, int n) throws FileNotFoundException, NoSuchElementException {
		
			  Scanner myReader = new Scanner(packFile);
			  int line_counter = 0;
			  while (myReader.hasNextLine()) {

					int number = myReader.nextInt();
					line_counter ++;

					// Verify if each row is non negative int
					if (number < 1){
						throw new NumberFormatException();
					}
			  }

			  // Verify no rows
			  if (line_counter != 8*n){
			  	  System.out.println("Incorrect number of cards in pack");
			  	  return false;
			  }
			  
			  myReader.close();
			  return true;
	}
	
	
	public static void outputFilePlayer(int playerIndex, ArrayList<Card> cardsInHand){
		try {
			String filename = "player"+ Integer.toString(playerIndex +1) +"_output.txt";
			File myObj = new File(filename);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				
				FileWriter myWriter = new FileWriter(filename);
				for (int i=0; i<3; i++){
					myWriter.write(Integer.toString(cardsInHand.get(i).getValueOf()));
					myWriter.write("\n");
				}
				myWriter.write(Integer.toString(cardsInHand.get(3).getValueOf()));
				myWriter.close();
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	
	public static void outputFileDeck(int deckIndex, ArrayList<Card> cardsInDeck){
		try {
			String filename = "deck"+ Integer.toString(deckIndex +1) +"_output.txt";
			File myObj = new File(filename);
			if (myObj.createNewFile()) {
				
				FileWriter myWriter = new FileWriter(filename);
				for (int i=0; i<3; i++){
					myWriter.write(Integer.toString(cardsInDeck.get(i).getValueOf()));
					myWriter.write("\n");
				}
				myWriter.write(Integer.toString(cardsInDeck.get(3).getValueOf()));
				myWriter.close();
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static String printHand(int playerIndex, ArrayList<Card> cardsInHand){
		String toOutput = "";
		
		for (int i = 0; i < 4; i++){
			toOutput += Integer.toString(cardsInHand.get(i).getValueOf()) + " ";
		}
		
		return toOutput;
	}
	
}