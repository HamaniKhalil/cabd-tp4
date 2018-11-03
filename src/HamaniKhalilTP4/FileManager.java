/*
 * TP N°		: 04
 * Version N°	: 01
 * 
 * Titre du TP	: Merge join duplicates
 * 
 * Date			: 02 Novembre 2018
 * 
 * Nom			: Hamani
 * Prenom		: Khalil
 * N° Etudiant	: 21810826
 * 
 * Email		: hamani_khalil@yahoo.fr
 * 
 * Remarques	: N/A
 * 
 * */

/*
 * This one is for file management :
 * 
 * - Read from file and put to char array
 * - Read from char array and put to file
 * 
 * */


package HamaniKhalilTP4;

import static HamaniKhalilTP4.SystemConfiguration.BUFFER_SIZE;
import static HamaniKhalilTP4.SystemConfiguration.DISCRIMINATION_INDEX;
import static HamaniKhalilTP4.SystemConfiguration.FIRST_ARRAY_ELEMENT_INDEX;
import static HamaniKhalilTP4.SystemConfiguration.THE_NONE_CHARACTER;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {

	private static final int	ELEMENT_INDEX		= 0;
	
	public static final char [] fileToArray(String filename, int startAtLevel) {
		int		startingIndex	= BUFFER_SIZE * startAtLevel;
		
		char []	result		= new char[BUFFER_SIZE];
		int		tabIndex	= FIRST_ARRAY_ELEMENT_INDEX;
		
		try {
			FileReader		inFR	= new FileReader(filename);
			BufferedReader	inBR	= new BufferedReader(inFR);
			
			
			String	line 	= null;
			
			gotoLevel(inBR, startingIndex);
			
			while(tabIndex < BUFFER_SIZE && (line = inBR.readLine()) != null) {
				result[tabIndex ++] = (char) line.charAt(ELEMENT_INDEX);
			}
			
			
			inFR.close();
			
			return result[DISCRIMINATION_INDEX] == THE_NONE_CHARACTER ?
					null :
						result;
		} catch (Exception e) {
			e.printStackTrace();
			result[0]	= 'E';
			result[1]	= 'R';
			result[2]	= 'R';
			result[3]	= 'O';
			result[4]	= 'R';
			return result;
		}
		
	}
	
	public static final void arrayToFile(char [] rs, String filename) {
		try {
			File	rsFile	= new File(filename);
			if(!rsFile.exists()) {
				rsFile.createNewFile();
			}
			
			if(rs == null) {
				return;
			}
			
			FileWriter		outFW	= new FileWriter(filename, true);
			BufferedWriter	outBW	= new BufferedWriter(outFW);
			
			// Checking if the file is empty before writing on it
			FileReader		outFR	= new FileReader(filename);
			BufferedReader	outBR	= new BufferedReader(outFR);
			
			String line;
			
			boolean	fileEmpty	= (line = outBR.readLine()) == null;
			
			outBR.close();
			
			// This loop writes one caracter per line and skips the line each time before writing
			// unless the file is empty at first (first writing content on it)
			for(int i = FIRST_ARRAY_ELEMENT_INDEX; i < rs.length; i ++) {
				if(rs[i] != THE_NONE_CHARACTER) {
					if(!fileEmpty) {
						outBW.append("\n" + rs[i]);
					}
					else {
						if(i > FIRST_ARRAY_ELEMENT_INDEX) {
							outBW.append("\n");
						}
						outBW.append(rs[i]);
					}
				}
				else {
					break;
				}
			}
			outBW.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final void gotoLevel(BufferedReader br, int startingIndex) throws Exception {
		int	counter	= 0;
		
		String	line 	= null;
		
		while(counter < startingIndex && (line = br.readLine()) != null) {
			counter ++;
		}
	}
}
