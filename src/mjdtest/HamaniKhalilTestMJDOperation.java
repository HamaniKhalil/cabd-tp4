package mjdtest;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import HamaniKhalilTP4.HamaniKhalilMergeJoinDuplicate;

public class HamaniKhalilTestMJDOperation {
	
	public static final String	FILE_PREFIX		= "T";
	public static final String	S_FILE_SUFFIX	= "-S.txt";
	public static final String	R_FILE_SUFFIX	= "-R.txt";
	public static final String	RS_FILE_SUFFIX	= "-RS.txt";
	public static final String	RS_CORRECT_FILE	= "-C";
	public static final String	RS_OUTPUT_FILE	= "-O";
	

	/*
	 * +------------------------------------------------+
	 * |	Join algorithm test cases (Write on files)	|
	 * +------------------------------------------------+
	 * */
	
	@Test
	public void testJoinOperation() {
		/*
		 * Automatic testing method that needs to have a standard naming as "T###-R.txt" for R relation
		 * and "T###-R.txt" for S relation
		 * and "T###-C-RS.txt" for RS join that is known to be correct
		 * and "T###-O-RS.txt" for RS join that is performed by the method that is tested
		 * 
		 * Having ### to be a tree (03) digit number as codified as a sequence of tests starting from 001 and ending with 999
		 * 
		 * P.S: It's important to have the tree (03) digits on the name
		 * */
		
		try {
			int		testNumber			= 1;
			String	correctRSFilename	= FILE_PREFIX + String.format("%03d", testNumber) + RS_CORRECT_FILE + RS_FILE_SUFFIX;
			File	correctRSFile		= new File(correctRSFilename);
			
			
			while(correctRSFile.exists()) {
				String	outputRSFilename	= FILE_PREFIX + String.format("%03d", testNumber) + RS_OUTPUT_FILE + RS_FILE_SUFFIX;
				String	rFilename			= FILE_PREFIX + String.format("%03d", testNumber) + R_FILE_SUFFIX;
				String	sFilename			= FILE_PREFIX + String.format("%03d", testNumber) + S_FILE_SUFFIX;
				
				File	outputRSFile		= new File(outputRSFilename);
				if(outputRSFile.exists()) {
					outputRSFile.delete();
				}
				
				HamaniKhalilMergeJoinDuplicate.joinWithMergeJoin(rFilename, sFilename, outputRSFilename);
				
				if(!outputRSFile.exists()) {
					outputRSFile.createNewFile();
				}
				
				FileReader		outputRSFR	= new FileReader(outputRSFilename);
				BufferedReader	outputRSBR	= new BufferedReader(outputRSFR);
				
				FileReader		correctRSFR	= new FileReader(correctRSFilename);
				BufferedReader	correctRSBR	= new BufferedReader(correctRSFR);
				
				String	outputLine	= null;
				String	correctLine	= null;
				
				boolean	correct		= true;
				while((correctLine = correctRSBR.readLine()) != null && (outputLine = outputRSBR.readLine()) != null) {
					correct	= correctLine.charAt(0) == outputLine.charAt(0);
					if(!correct) {
						break;
					}
				}
				if(correct) {
					System.out.println("Success : File '" + outputRSFilename + "' passed the test");
				}
				else {
					System.out.println("Error : File '" + outputRSFilename + "' didn't passed the test");
				}
				
				assertEquals(true, correct);
				
				// Incrementation
				testNumber ++;
				correctRSFilename	= FILE_PREFIX + String.format("%03d", testNumber) + RS_CORRECT_FILE + RS_FILE_SUFFIX;
				correctRSFile		= new File(correctRSFilename);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
