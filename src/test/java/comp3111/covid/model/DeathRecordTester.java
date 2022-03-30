import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import comp3111.covid.model.*;


public class DeathRecordTester {
	private DeathRecord validator;
	@Before
	public void setUp() throws Exception {
		validator = new DeathRecord("China","2/1/2020");
	}
	@Test
	public void testGetCountry() {	
		String country = "China";
		String wrongcountry = "Italy";
		assertEquals(country, validator.getCountry());
		assertNotEquals(wrongcountry,validator.getCountry());
	}
	@Test
	public void testGetDate() {	
		String date = "2/1/2020";
		String wrongdate = "1/2/2020";
		assertEquals(date, validator.getDate());
		assertNotEquals(wrongdate,validator.getDate());
	}
	@Test
	public void test(){
		assertEquals(259, validator.getTotalNumDeath());
		assertEquals(0.18, validator.getNumDeathPerMillion(),0.01);
	}
}
