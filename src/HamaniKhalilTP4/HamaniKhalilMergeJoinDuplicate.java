package HamaniKhalilTP4;

import static HamaniKhalilTP4.SystemConfiguration.BUFFER_SIZE;
import static HamaniKhalilTP4.SystemConfiguration.THE_NONE_CHARACTER;
import static HamaniKhalilTP4.SystemErrors.MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE;

import java.util.Arrays;

public class HamaniKhalilMergeJoinDuplicate {
	
	public static final void joinWithMergeJoin(String rFilename, String sFilename, String rsFilename) {
		int	sReadLevel	= 0;
		int	rReadLevel	= 0;
		
		char []	r	= FileManager.fileToArray(rFilename, rReadLevel);
		char []	s	= FileManager.fileToArray(sFilename, sReadLevel);
		
		try {
			while(s != null) {
				while(r != null) {
					FileManager.arrayToFile(mergeJoinDuplicate(s, r), rsFilename);
					r	= FileManager.fileToArray(rFilename, ++ rReadLevel);
				}
				s	= FileManager.fileToArray(sFilename, ++ sReadLevel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static char [] mergeJoinDuplicate(char [] r, char [] s) throws Exception {
		if(r.length > BUFFER_SIZE || s.length > BUFFER_SIZE) {
			throw new Exception(MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE);
		}
		
		
		int	i	= 0;
		int	j	= 0;
		int	k	= 0;
		
		int rsIndex	= 0;
		
		/*
		 * In the case where the two arrays are fully sized (meaning all the buffer size is used)
		 * and similar with duplicate records the resulting join can't fit the buffer size
		 * and the worst case scenario is a N x N sized RS array :
		 * Example : Lets take a buffer size of 4
		 * R	= {A, A, A, A}
		 * S	= {A, A, A, A}
		 * RS	= {A, A, A, A, A, A, A, A, A, A, A, A, A, A, A, A}
		 * 
		 * || R || = || S ||	= BUFFER_SIZE				= 4
		 * || RS ||				= BUFFER_SIZE x BUFFER_SIZE = 16
		 * 
		 * A better solution is to evaluate the RS size before joining
		 */
		char []	rs	= new char[BUFFER_SIZE * BUFFER_SIZE];
		
		sort(r);
		sort(s);
		
		while(i < r.length && j < s.length && r[i] != THE_NONE_CHARACTER && s[j] != THE_NONE_CHARACTER) {
			if(r[i] == s[j]) {
				k = j;
				
				while(i < r.length && r[i] == s[j]) {
					while(j < s.length && r[i] == s[j]) {
						rs[rsIndex ++] = r[i];
						j ++;
					}
					i ++;
					j = k;
				}
			}
			else if(r[i] > s[j]) {
				j ++;
			}
			else {
				i ++;
			}
		}
		
		return rs;
	}
	
	// A naive sort method
	private static void sort(char [] array) {
		int	i	= 0;
		int	j	= 1;
		
		char intermediate = THE_NONE_CHARACTER;
		
		while(i < array.length && array[i] != THE_NONE_CHARACTER) {
			while(j < array.length && array[j] != THE_NONE_CHARACTER) {
				if(array[i] > array[j]) {
					intermediate	= array[i];
					array[i]		= array[j];
					array[j]		= intermediate;
				}
				j ++;
			}
			i ++;
		}
	}
}
