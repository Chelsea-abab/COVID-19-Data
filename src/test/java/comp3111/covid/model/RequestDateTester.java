import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import comp3111.covid.model.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDateTester {
	private RequestOnDate validatorx;
	private RequestOnDate validatory;
	private RequestOnDate validatorz;
	private ArrayList<String> countrys;
	
	@Before
	public void setUp() throws Exception {
		countrys = new ArrayList<>();
		countrys.add("Hong Kong");
		countrys.add("Afghanistan");
		validatorx = new RequestOnDate(countrys,"2/1/2021",1);
		validatory = new RequestOnDate(countrys,"2/1/2021",2);
		validatorz = new RequestOnDate(countrys,"4/24/2021",3);
	}
	
	@Test
	public void test() {
		DeathRecord x = (DeathRecord) validatorx.getRecord("Afghanistan");
		assertEquals(2404, x.getTotalNumDeath());
		assertEquals(61.754, x.getNumDeathPerMillion(),0.01);
		ConfirmedCaseRecord y = (ConfirmedCaseRecord) validatory.getRecord("Afghanistan");
		assertEquals(55059, y.getTotalNumConfirmedCase());
		assertEquals(1414.368, y.getNumConfirmedCasePerMillion(),0.01);
		VaccinationRecord z = (VaccinationRecord) validatorz.getRecord("Hong Kong");
		assertEquals(424807, z.getNumVaccination());
		assertEquals(5.67, z.getVaccinationRate(),0.01);
	}
	
	
}	
