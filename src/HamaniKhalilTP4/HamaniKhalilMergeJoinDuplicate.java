package HamaniKhalilTP4;

import static HamaniKhalilTP4.SystemConfiguration.BUFFER_SIZE;

import java.util.Arrays;

public class HamaniKhalilMergeJoinDuplicate {
	
	public static char [] mergeJoinDuplicate(char [] r, char [] s) {
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
			else if(r[i] > s[i]) {
				j ++;
			}
			else {
				i ++;
			}
		}
		
		return rs;
	}
}
