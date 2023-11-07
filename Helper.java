import java.util.*;
import java.io.*;

public class Helper{

	public static boolean checkPack(String fileName, int n){
		try {
			  File packFile = new File(fileName);
			  Scanner myReader = new Scanner(packFile);
			  int line_counter = 0;
			  while (myReader.hasNextLine()) {
					String data = myReader.nextLine();
					line_counter ++;
					// Verify if each row is non negative int
					try {
						int number = Integer.parseInt(data);
						if (number < 1){
							throw new NumberFormatException();
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid card's face value");
						return false;
					}
			  }
			  
			  // Verify no rows
			  if (line_counter != 8*n){
			  	  System.out.println("Incorrect number of cards");
			  	  return false;
			  }
			  
			  myReader.close();
			  return true;
			  
		} catch (FileNotFoundException e) {
			  System.out.println("Incorrect file name/path");
			  return false;
		}
	}
}