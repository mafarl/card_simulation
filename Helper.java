import java.util.*;
import java.io.*;

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
			}
			
		}
	}

	public static boolean checkPack(File packFile, int n) throws FileNotFoundException {
		
			  Scanner myReader = new Scanner(packFile);
			  int line_counter = 0;
			  while (myReader.hasNextLine()) {

					// Check for whitespace
					if (myReader.nextLine() == ""){
						System.out.println("Whitespace in file, invalid pack");
						return false;
					}

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
}