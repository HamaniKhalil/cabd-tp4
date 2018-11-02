package mjdtest;

import static HamaniKhalilTP4.SystemConfiguration.BUFFER_SIZE;
import static HamaniKhalilTP4.SystemConfiguration.THE_NONE_CHARACTER;

import static HamaniKhalilTP4.SystemErrors.MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import HamaniKhalilTP4.HamaniKhalilMergeJoinDuplicate;

public class HamaniKhalilTestMJDAlgorithm {
	
	@Test
	public void testBadR() {
		char []	r	= new char[BUFFER_SIZE + 1];
		char []	s	= new char[BUFFER_SIZE];
		
		try {
			HamaniKhalilMergeJoinDuplicate.mergeJoinDuplicate(r, s);
		} catch (Exception e) {
			assertEquals(MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE, e.getMessage());
		}
	}
	
	@Test
	public void testBadS() {
		char []	r	= new char[BUFFER_SIZE];
		char []	s	= new char[BUFFER_SIZE + 1];
		
		try {
			HamaniKhalilMergeJoinDuplicate.mergeJoinDuplicate(r, s);
		} catch (Exception e) {
			assertEquals(MESSAGE_ARRAY_EXCEEDED_BUFFER_SIZE, e.getMessage());
		}
	}
	
	@Test
	public void testFullR() {
		char []	r	= {
				'A',
				'D',
				'F',
				'F',
				'H',
				'R',
				'S',
				'S',
				'T',
				'Z'
			};
		
		char []	s	= {
				'A',
				'B',
				'D',
				'F',
				'L',
				'M',
				'O'
			};
		
		char []	correctRS	= {
				'A',
				'D',
				'F',
				'F'
			};
		
		try {			
			char []	rs	= HamaniKhalilMergeJoinDuplicate.mergeJoinDuplicate(r, s);
			
			int i = 0;
			
			while(rs[i] != THE_NONE_CHARACTER) {
				assertEquals(rs[i], correctRS[i ++]);
			}
			
			// Check if the rs length (or element count) is correct
			assertEquals(correctRS.length, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFullS() {
		char []	r	= {
				'A',
				'B',
				'D',
				'F',
				'L',
				'M',
				'O'
		};
		
		char []	s	= {
				'A',
				'D',
				'F',
				'F',
				'H',
				'R',
				'S',
				'S',
				'T',
				'Z'
		};
		
		char []	correctRS	= {
				'A',
				'D',
				'F',
				'F'
			};
		
		try {			
			char []	rs	= HamaniKhalilMergeJoinDuplicate.mergeJoinDuplicate(r, s);
			
			int i = 0;
			
			while(rs[i] != THE_NONE_CHARACTER) {
				assertEquals(rs[i], correctRS[i ++]);
			}
			
			// Check if the rs length (or element count) is correct
			assertEquals(correctRS.length, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNoFull() {
		char []	r	= {
				'A',
				'B',
				'D',
				'F',
				'L',
				'M',
				'O'
		};
		
		char []	s	= {
				'A',
				'D',
				'F',
				'R',
				'S',
				'S',
		};
		
		char []	correctRS	= {
				'A',
				'D',
				'F'
			};
		
		try {
			char []	rs	= HamaniKhalilMergeJoinDuplicate.mergeJoinDuplicate(r, s);
			
			int i = 0;
			
			while(rs[i] != THE_NONE_CHARACTER) {
				assertEquals(rs[i], correctRS[i ++]);
			}
			
			// Check if the rs length (or element count) is correct
			assertEquals(correctRS.length, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
