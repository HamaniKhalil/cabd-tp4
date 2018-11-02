package HamaniKhalilTP4;

import static HamaniKhalilTP4.SystemConfiguration.BUFFER_SIZE;

import static HamaniKhalilTP4.SystemErrors.MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE;

import java.io.File;
import java.util.Arrays;

import HamaniKhalilTP4.FileManager;

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
			
			// Create an empty file if the join returns no row
			File	rsFile	= new File(rsFilename);
			if(!rsFile.exists()) {
				rsFile.createNewFile();
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
		
		char []	rs	= new char[BUFFER_SIZE];
		
		Arrays.sort(r);
		Arrays.sort(s);
		
		while(i < r.length && j < s.length) {
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
}
