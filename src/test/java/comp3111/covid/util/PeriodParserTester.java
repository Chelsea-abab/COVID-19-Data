package comp3111.covid.util;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class PeriodParserTester {

	@Test
	public void TransformtoPeriodtester() {
		Date[] res=new Date[2];
		res[0]=new Date(121,0,15);
		res[1]=new Date(121,1,9);
		assertEquals(res,PeriodParser.transformToPeriod("from Jan 15, 2021 to Feb 9, 2021"));
		res[0]=new Date(120,2,1);
		res[1]=new Date(121,6,20);
		assertEquals(res,PeriodParser.transformToPeriod("from Mar 1, 2020 to Jul 20, 2021"));
		res[0]=new Date(120,4,1);
		res[1]=new Date(120,5,2);
		assertEquals(res,PeriodParser.transformToPeriod("from May 1, 2020 to Jun 2, 2020"));
		res[0]=new Date(121,6,18);
		res[1]=new Date(121,6,19);
		assertEquals(res,PeriodParser.transformToPeriod("from Jul 18, 2021 to Jul 19, 2021"));
		res[0]=new Date(120,5,11);
		res[1]=new Date(121,5,11);
		assertEquals(res,PeriodParser.transformToPeriod("from Jun 11, 2020 to Jun 11, 2021"));
		assertEquals(null,PeriodParser.transformToPeriod("fro Jun 11, 2020 to Jun 11, 2021"));
		assertEquals(null,PeriodParser.transformToPeriod("from Jun 11, 2020 o Jun 11, 2021"));
		
	}
	
	@Test
	public void testTransformPeriod() {
		String[] res=new String[2];
		res[0]="3/1/2020";
		res[1]="7/20/2021";
		assertEquals(res,PeriodParser.transformToPeriodString("from Mar 1, 2020 to Jul 20, 2021"));
		res[0]="5/1/2020";
		res[1]="6/2/2020";
		assertEquals(res,PeriodParser.transformToPeriodString("from May 1, 2020 to Jun 2, 2020"));
		res[0]="7/18/2021";
		res[1]="7/19/2021";
		assertEquals(res,PeriodParser.transformToPeriodString("from Jul 18, 2021 to Jul 19, 2021"));
		res[0]="6/11/2020";
		res[1]="6/11/2021";
		assertEquals(res,PeriodParser.transformToPeriodString("from Jun 11, 2020 to Jun 11, 2021"));
		assertEquals(null,PeriodParser.transformToPeriodString("fro Jun 11, 2020 to Jun 11, 2021"));
		assertEquals(null,PeriodParser.transformToPeriodString("from Jun 11, 2020 o Jun 11, 2021"));
		
	}
	
	@Test
	public void testValidPeriod() {
		assertFalse(PeriodParser.isValidDateInPeriod("fro Jun 11, 2020 t Jun 11, 2021"));
		assertFalse(PeriodParser.isValidDateInPeriod("from to"));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun 11, 2020 to "));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun 11, 2020 to Jun 11, 2021 to Jun 12, 2021"));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun 1, 2020 to to "));
		assertFalse(PeriodParser.isValidDateInPeriod("from  Jun 11, 2020 to Jun 11, 2021"));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun  1, 2020 to Jun 11, 2021"));
		assertFalse(PeriodParser.isValidDateInPeriod("totofrom May 1, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isValidDateInPeriod(" from May 1, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isValidDateInPeriod(" rom May 1, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun 1, 2020 tooo Jun 11, 2021"));
		assertFalse(PeriodParser.isValidDateInPeriod("from Jun 1, 2020 to Jun 1, 2021  "));
		assertFalse(PeriodParser.isValidDateInPeriod("from Ju 11, 2020 to Jun 11, 2011"));
		
		assertTrue(PeriodParser.isValidDateInPeriod("from May 11, 2020 to Jun 11, 2021"));
		assertTrue(PeriodParser.isValidDateInPeriod("from May 1, 2020 to Jun 2, 2020"));
	}
	
	@Test
	public void testDatewithRangeinPeriod() {
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("fro Jan 1, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("from Jan 1,, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("from Jan 1, 2020 to Jun 2, 2020"));
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("from Feb 28, 2020 to Jul 21, 2020"));
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("from Mar 30, 2020 to Jul 21, 2021"));
		assertFalse(PeriodParser.isDateWithinRangeInPeriod("from Jan 32, 2021 to Jun 2, 2021"));
		
		assertTrue(PeriodParser.isDateWithinRangeInPeriod("from Jan 1, 2021 to Jun 2, 2021"));
		assertTrue(PeriodParser.isDateWithinRangeInPeriod("from Aug 1, 2020 to Jan 12, 2021"));
	}
	
	@Test
	public void testStartBeforeEnd() {
		assertFalse(PeriodParser.isStartBeforeEnd("from Mar 0, 2021 to Jul 20, 2020"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Mar 1, 2021 to Jul 32, 2020"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Mar 1, 2021 to Jul 20, 2020"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Mar 1, 2021 to Mar 1, 2020"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Oct 20, 2020 to Jul 19, 2020"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Apr 12, 2021 to Apr 1, 2021"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Mar 2, 2021 to Mar 1, 2021"));
		assertFalse(PeriodParser.isStartBeforeEnd("from Jul 20, 2020 to Jul 20, 2020"));
		
		assertTrue(PeriodParser.isStartBeforeEnd("from Jul 20, 2020 to Jul 21, 2020"));
		assertTrue(PeriodParser.isStartBeforeEnd("from Jul 20, 2020 to Jul 20, 2021"));
	}
	
	
	
	
	
	
	

}