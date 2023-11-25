import java.util.*;
import java.io.*;

/**
 * Helper class for methods in other classes. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class Helper{

	public static int playerAmount(Scanner myObj){
		// Check no players
			try {
                int numPlayers = myObj.nextInt();
				if (numPlayers >= 1){
                    return(numPlayers);
				}
				System.out.println("Enter an integer greater than 0");
			} catch (InputMismatchException e) {
				System.out.println("Invalid player number input");
			}
			return(-1);
		}


	public static boolean getPack(int playerAmount, String fileName){
		try{
			// Getting filename
			File packFile = new File(fileName); 

			if (!packFile.exists()){
				throw new FileNotFoundException();
			}
			
			// Check if the pack is valid
			boolean validity = true; 
			Scanner myReader = new Scanner(packFile);
			int line_counter = 0;
			while (myReader.hasNextLine()) {

				int number = myReader.nextInt();
				line_counter ++;

			// Verify if each row is non negative int

				if (number < 1){
					myReader.close();
					throw new NumberFormatException();
				}
			}
			myReader.close();

			  // Verify no rows
			if (line_counter != 8*playerAmount){
			  	System.out.println("Incorrect number of cards in pack");
			  	validity = false;
			}

			if (validity == true){
				System.out.println("Card pack loaded successfully");
				return true;
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
		return(false);
	}

/* public static boolean checkPack(File packFile, int n) throws FileNotFoundException, NoSuchElementException {
		
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
	} */
	
	
	public static void outputFilePlayer(int playerIndex, ArrayList<Card> cardsInHand){
		try {
			String filename = "player"+ Integer.toString(playerIndex +1) +"_output.txt";
			File myObj = new File(filename);
			if (myObj.createNewFile()) {
				
				FileWriter myWriter = new FileWriter(filename);
				for (int i=0; i<3; i++){
					myWriter.write(Integer.toString(cardsInHand.get(i).getValueOf()));
					myWriter.write("\n");
				}
				myWriter.write(Integer.toString(cardsInHand.get(3).getValueOf()));
				myWriter.close();
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