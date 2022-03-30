import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import comp3111.covid.model.*;
import java.util.ArrayList;
import java.util.List;

public class RequestOverPTester {
	private RequestOverPeriod validatorx;
	private RequestOverPeriod validatory;
	private RequestOverPeriod validatorz;
	private ArrayList<String> countrys;
	private Period period;
	
	@Before
	public void setUp() throws Exception {
		period = new Period("4/24/2021","4/25/2021");
		countrys = new ArrayList<>();
		countrys.add("Hong Kong");
		countrys.add("Afghanistan");
		validatorx = new RequestOverPeriod(countrys,period,1);
		validatory = new RequestOverPeriod(countrys,period,2);
		validatorz = new RequestOverPeriod(countrys,period,3);
	}
	
	@Test
	public void test() {
		  DeathPeriodRecord x = (DeathPeriodRecord) validatorx.getPeriodRecord("Afghanistan",period);
		  assertEquals(2572.0, x.getNumDeathPerMillions().get(0),0.01);
		  ConfirmedCasePeriodRecord y = (ConfirmedCasePeriodRecord) validatory.getPeriodRecord("Afghanistan",period);
		  assertEquals(1508.669, y.getNumConfirmedCasePerMillion().get(0),0.01);
		  VaccinationPeriodRecord z = (VaccinationPeriodRecord) validatorz.getPeriodRecord("Hong Kong",period);
		  assertEquals(5.67, z.getVaccinationRates().get(0),0.01);
	}
	
	
}	

