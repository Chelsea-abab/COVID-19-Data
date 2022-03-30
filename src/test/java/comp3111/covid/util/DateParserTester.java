package comp3111.covid.util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import comp3111.covid.util.*;

public class DateParserTester {
	
		@Test
		public void TransformtoDateTester(){
			assertEquals(new Date(121,0,15), DateParser.transformToDate("Jan 15, 2021"));
			assertEquals(new Date(121,1,9), DateParser.transformToDate("Feb 9, 2021"));
			assertEquals(new Date(120,2,1), DateParser.transformToDate("Mar 1, 2020"));
			assertEquals(new Date(121,3,30), DateParser.transformToDate("Apr 30, 2021"));
			assertEquals(new Date(120,4,20), DateParser.transformToDate("May 20, 2020"));
			assertEquals(new Date(121,5,5), DateParser.transformToDate("Jun 05, 2021"));
			assertEquals(new Date(121,6,13), DateParser.transformToDate("Jul 13, 2021"));
			assertEquals(new Date(120,7,23), DateParser.transformToDate("Aug 23, 2020"));
			assertEquals(new Date(120,8,1), DateParser.transformToDate("Sep 1, 2020"));
			assertEquals(new Date(120,9,10), DateParser.transformToDate("Oct 10, 2020"));
			assertEquals(new Date(120,10,30), DateParser.transformToDate("Nov 30, 2020"));
			assertEquals(new Date(120,11,31), DateParser.transformToDate("Dec 31, 2020"));
			
			assertEquals(null, DateParser.transformToDate("Dec 31 2020"));
			assertEquals(null, DateParser.transformToDate("Jan, 2021"));
			assertEquals(null, DateParser.transformToDate("AAA, 2021"));
		}
	
		@Test
		public void testTransformString(){
			assertEquals("1/15/2021", DateParser.transformToDateString("Jan 15, 2021"));
			assertEquals("2/9/2021", DateParser.transformToDateString("Feb 9, 2021"));
			assertEquals("3/1/2020", DateParser.transformToDateString("Mar 1, 2020"));
			assertEquals("4/30/2021", DateParser.transformToDateString("Apr 30, 2021"));
			assertEquals("5/20/2020", DateParser.transformToDateString("May 20, 2020"));
			assertEquals("6/5/2021", DateParser.transformToDateString("Jun 05, 2021"));
			assertEquals("7/13/2021", DateParser.transformToDateString("Jul 13, 2021"));
			assertEquals("8/23/2020", DateParser.transformToDateString("Aug 23, 2020"));
			assertEquals("9/1/2020", DateParser.transformToDateString("Sep 1, 2020"));
			assertEquals("10/10/2020", DateParser.transformToDateString("Oct 10, 2020"));
			assertEquals("11/30/2020", DateParser.transformToDateString("Nov 30, 2020"));
			
			
			assertEquals(null, DateParser.transformToDateString("Dec 31 2020"));
			assertEquals(null, DateParser.transformToDateString("Jan, 2021"));
			assertEquals(null, DateParser.transformToDateString("AAA, 2021"));
			
		}
		
		@Test
		public void testDateValid(){
			assertTrue(DateParser.isValidDate("Jan 1, 2021"));
			assertTrue(DateParser.isValidDate("Feb 1, 2021"));
			assertTrue(DateParser.isValidDate("Mar 1, 2020"));
			assertTrue(DateParser.isValidDate("Apr 14, 2021"));
			assertTrue(DateParser.isValidDate("May 31, 2020"));
			assertTrue(DateParser.isValidDate("Jun 8, 2021"));
			assertTrue(DateParser.isValidDate("Jul 8, 2021"));
			assertTrue(DateParser.isValidDate("Aug 8, 2020"));
			assertTrue(DateParser.isValidDate("Sep 8, 2020"));
			assertTrue(DateParser.isValidDate("Oct 8, 2020"));
			assertTrue(DateParser.isValidDate("Nov 8, 2020"));
			assertTrue(DateParser.isValidDate("Dec 8, 2020"));
			
			
			assertFalse(DateParser.isValidDate("MMM"));
			assertFalse(DateParser.isValidDate("May"));
			assertFalse(DateParser.isValidDate("1233333"));
			assertFalse(DateParser.isValidDate(""));
			
			assertFalse(DateParser.isValidDate("123456789123"));
			assertFalse(DateParser.isValidDate("Jan4567891234"));
			assertFalse(DateParser.isValidDate("May456789123"));
			
			assertFalse(DateParser.isValidDate("Jun 567891234"));
			assertFalse(DateParser.isValidDate("Jun ,1, 2020"));
			assertFalse(DateParser.isValidDate("Aug , 2020"));
			assertFalse(DateParser.isValidDate("Oct *, 1920"));
			assertFalse(DateParser.isValidDate("Jan, 2020"));
			assertFalse(DateParser.isValidDate("Aug **, 2021"));
			assertFalse(DateParser.isValidDate("Aug 1*, 2021"));
			assertFalse(DateParser.isValidDate("Aug *1, 2021"));
			
			assertFalse(DateParser.isValidDate("Aug 12, 2022"));
			assertFalse(DateParser.isValidDate("Sep 1,     "));
			assertFalse(DateParser.isValidDate("Oct 10, 2029"));
			assertFalse(DateParser.isValidDate("Oct 10, 1920"));
			assertFalse(DateParser.isValidDate("Oct 10, 1911"));
		}
		
		@Test
		public void testDateWithinRange(){
			assertFalse(DateParser.isDateWithinRange("Aug -1, 2021"));
			assertFalse(DateParser.isDateWithinRange("Sep 1,     "));
			
			assertTrue(DateParser.isDateWithinRange("Jan 1, 2021"));
			assertFalse(DateParser.isDateWithinRange("Jan 1, 2020"));
			assertFalse(DateParser.isDateWithinRange("Jan 0, 2021"));
			assertFalse(DateParser.isDateWithinRange("Jan 32, 2021"));
			
			assertTrue(DateParser.isDateWithinRange("Feb 9, 2021"));
			assertFalse(DateParser.isDateWithinRange("Feb 15, 2020"));
			assertFalse(DateParser.isDateWithinRange("Feb 0, 2021"));
			assertFalse(DateParser.isDateWithinRange("Feb 32, 2021"));
			
			assertTrue(DateParser.isDateWithinRange("Mar 16, 2021"));
			assertTrue(DateParser.isDateWithinRange("Mar 14, 2020"));
			assertFalse(DateParser.isDateWithinRange("Mar 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Mar 32, 2021"));
			
			assertTrue(DateParser.isDateWithinRange("Apr 1, 2021"));
			assertTrue(DateParser.isDateWithinRange("Apr 1, 2020"));
			assertFalse(DateParser.isDateWithinRange("Apr 0, 2021"));
			assertFalse(DateParser.isDateWithinRange("Apr 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("May 1, 2021"));
			assertTrue(DateParser.isDateWithinRange("May 1, 2020"));
			assertFalse(DateParser.isDateWithinRange("May 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("May 32, 2021"));
			
			assertTrue(DateParser.isDateWithinRange("Jun 1, 2021"));
			assertTrue(DateParser.isDateWithinRange("Jun 1, 2020"));
			assertFalse(DateParser.isDateWithinRange("Jun 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Jun 32, 2021"));
			
			assertTrue(DateParser.isDateWithinRange("Jul 20, 2021"));
			assertFalse(DateParser.isDateWithinRange("Jul 21, 2021"));
			assertTrue(DateParser.isDateWithinRange("Jul 20, 2020"));
			assertFalse(DateParser.isDateWithinRange("Jul 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("Aug 9, 2020"));
			assertFalse(DateParser.isDateWithinRange("Aug 15, 2021"));
			assertFalse(DateParser.isDateWithinRange("Aug 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Aug 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("Sep 9, 2020"));
			assertFalse(DateParser.isDateWithinRange("Sep 15, 2021"));
			assertFalse(DateParser.isDateWithinRange("Sep 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Sep 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("Oct 9, 2020"));
			assertFalse(DateParser.isDateWithinRange("Oct 15, 2021"));
			assertFalse(DateParser.isDateWithinRange("Oct 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Oct 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("Nov 9, 2020"));
			assertFalse(DateParser.isDateWithinRange("Nov 15, 2021"));
			assertFalse(DateParser.isDateWithinRange("Nov 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Nov 32, 2020"));
			
			assertTrue(DateParser.isDateWithinRange("Dec 9, 2020"));
			assertFalse(DateParser.isDateWithinRange("Dec 15, 2021"));
			assertFalse(DateParser.isDateWithinRange("Dec 0, 2020"));
			assertFalse(DateParser.isDateWithinRange("Dec 32, 2020"));
		}
		
		

}
