import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import comp3111.covid.model.Period;

public class PeriodTester {
	
	private Period validator;
	
	@Before
	public void setUp() throws Exception {
		validator = new Period("1/1/2020","1/3/2020");
	}
	@Test
	public void testGetStartDate() {	
		String startdate = "1/1/2020";
		String wrongdate = "1/2/2020";
		assertEquals(startdate, validator.getstartDate());
		assertNotEquals(wrongdate,validator.getstartDate());
	}
	@Test
	public void testGetEndDate() {	
		String enddate = "1/3/2020";
		String wrongdate = "1/2/2020";
		assertEquals(enddate, validator.getendDate());
		assertNotEquals(wrongdate,validator.getendDate());
	}
	@Test
	public void testGetDatesBetween() {
		List<String> result = new ArrayList<>();
		result.add("1/1/2020");
		result.add("1/2/2020");
		result.add("1/3/2020");
		assertEquals(result, validator.getDatesBetween());
	}
}
