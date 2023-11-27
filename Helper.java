import java.util.*;
import java.io.*;

/**
 * Helper class for methods in other classes. 
 * @author Ashley Card
 * @author Maryia Fralova
 * @version 1.0 
 */

public class Helper{

	/**
	 * Calculates the number of players based on user input.
	 * @param  myObj  the Scanner object used to get user input
	 * @return numPlayers number of players or -1 if invalid input
	 */
	public static int playerAmount(Scanner myObj){
			try {
				// Throws exception if invalid input
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


	/**
	 * Validates and loads a card pack from a file.
	 * @param playerAmount the number of players
	 * @param fileName the file name/path of the pack
	 * @return boolean true if the pack is valid, false otherwise
	 */
	public static boolean getPack(int playerAmount, String fileName){
		try{
			// Get pack file
			File packFile = new File(fileName); 
			if (!packFile.exists()){
				throw new FileNotFoundException();
			}
			
			// Check pack validity
			boolean validity = true; 
			Scanner myReader = new Scanner(packFile);
			int line_counter = 0;
			while (myReader.hasNextLine()) {

				int number = myReader.nextInt();
				line_counter ++;

			// Verify if each row is non-negative integer
				if (number < 1){
					myReader.close();
					throw new NumberFormatException();
				}
			}
			myReader.close();

			// Verify number of rows is 8*playerAmount
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
	
	/**
	 * Writes into first part of player output file
	 * @param playerIndex index of player
	 * @param text string to write to file
	 */
	public static void initialOutputFile(int playerIndex, String text){
		String filename = "player"+ Integer.toString(playerIndex +1) +"_output.txt";
		// Creates file to write to
		File myObj = new File(filename);
		try {
			FileWriter myWriter = new FileWriter(filename, false);
			myWriter.write(text);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes into player output file
	 * @param playerIndex index of player
	 * @param text string to write to file
	 */
	public static void addingToOutputFile(int playerIndex, String text){
		try{
			String filename = "player"+ Integer.toString(playerIndex +1) +"_output.txt";
			FileWriter myWriter = new FileWriter(filename, true);
			myWriter.write("\n");
			myWriter.write(text);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes deck contents into deck output file
	 * @param deckIndex index of deck
	 * @param cardsInDeck arraylist of cards in deck
	 */
	public static void outputFileDeck(int deckIndex, ArrayList<Card> cardsInDeck){
		try {
			String filename = "deck"+ Integer.toString(deckIndex +1) +"_output.txt";
			File myObj = new File(filename);
			FileWriter myWriter = new FileWriter(filename);
			String text = "Deck " + Integer.toString(deckIndex +1) + " contents: " + printHand(cardsInDeck);	
			myWriter.write(text);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates string of hand in one line
	 * @param cardsInHand
	 * @return toOutput string of hand
	 */
	public static String printHand(ArrayList<Card> cardsInHand){
		String toOutput = "";
		for (int i = 0; i < cardsInHand.size(); i++){
			toOutput += Integer.toString(cardsInHand.get(i).getValueOf()) + " ";
		}
		return toOutput;
	}
	
}