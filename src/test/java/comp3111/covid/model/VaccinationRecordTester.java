import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import comp3111.covid.model.*;


public class VaccinationRecordTester {
	private VaccinationRecord validator;
	@Before
	public void setUp() throws Exception {
		validator = new VaccinationRecord("Hong Kong","4/24/2021");
	}
	@Test
	public void testGetCountry() {	
		String country = "Hong Kong";
		String wrongcountry = "Italy";
		assertEquals(country, validator.getCountry());
		assertNotEquals(wrongcountry,validator.getCountry());
	}
	@Test
	public void testGetDate() {	
		String date = "4/24/2021";
		String wrongdate = "1/2/2020";
		assertEquals(date, validator.getDate());
		assertNotEquals(wrongdate,validator.getDate());
	}
	@Test
	public void test(){
		assertEquals(424807, validator.getNumVaccination());
		assertEquals(5.67, validator.getVaccinationRate(),0.01);
		String str = "5.67%";
		assertEquals(str,validator.getRateInPercentage());
		assertEquals("424807", validator.getNumString());
	}
}