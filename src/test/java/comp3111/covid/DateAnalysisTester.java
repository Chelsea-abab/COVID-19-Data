package comp3111.covid;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.*;
import java.time.LocalDate;
import edu.duke.*;
import java.util.stream.Collectors;

import comp3111.covid.model.*;

public class DateAnalysisTester {
	String dataset;
	CSVParser csvParser;
	@Before
	public void setUp() throws Exception {
		dataset="COVID_Dataset_v1.0.csv";
		csvParser=DataAnalysis.getFileParser(dataset);
	}
	
	@Test
	public void getCountryListTester() {
		List<String> name = DataAnalysis.getCountryList(dataset);
		assertEquals(231,name.size());
	}
	
	@Test
	public void getFeatureTester() {
		assertEquals((Object) Double.valueOf(76),(Object) DataAnalysis.getFeature(dataset,"total_cases","Afghanistan","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getFeature(dataset,"total_cases","Anguilla","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getFeature(dataset,"total_cases","Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getFeature(dataset,"total_cases","Anguilla","3/25/2019"));
	}
	
	@Test
	public void getConfirmedCasesTester() {
		String output = DataAnalysis.getConfirmedCases(dataset,"HKG");
		String oReport = "Dataset (COVID_Dataset_v1.0.csv): 103,907 Records\n\n";
		oReport += "[Summary (HKG)]\n";
		oReport += "Number of Confirmed Cases: 11,965\n";
		oReport += "Number of Days Reported: 545\n";
		assertEquals(oReport,output);
	}
	
	@Test
	public void getConfirmedDeathsTester() {
		String output = DataAnalysis.getConfirmedDeaths(dataset,"HKG");
		String oReport = "Dataset (COVID_Dataset_v1.0.csv): 103,907 Records\n\n";
		oReport += "[Summary (HKG)]\n";
		oReport += "Number of Deaths: 212\n";
		oReport += "Number of Days Reported: 533\n";
		assertEquals(oReport,output);
	}
	
	@Test
	public void getRateOfVaccinationTester() {
		String output = DataAnalysis.getRateOfVaccination(dataset,"HKG");
		String oReport = "Dataset (COVID_Dataset_v1.0.csv): 103,907 Records\n\n";
		oReport += "[Summary (HKG)]\n";
		oReport += "Number of People Fully Vaccinated: 2,065,375\n";
		oReport += "Population: 7,496,988\n";
		oReport += "Rate of Vaccination: 27.55%\n";
		oReport += "Number of Days Reported: 140\n";
		assertEquals(oReport ,output);
	}
	
	@Test
	public void getConfirmedCasesaTester() {
		assertEquals(76,DataAnalysis.getConfirmedCasesa(dataset,"Afghanistan","3/25/2020"));
		assertEquals(0,DataAnalysis.getConfirmedCasesa(dataset,"Anguilla","3/25/2020"));
		assertEquals(0,DataAnalysis.getConfirmedCasesa(dataset,"Cheng","3/25/2020"));
		assertEquals(0,DataAnalysis.getConfirmedCasesa(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getConfirmedCasesStandardedTester() {
		assertEquals((Object) Double.valueOf(76),(Object) DataAnalysis.getConfirmedCasesStandarded(dataset,"Afghanistan","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesStandarded(dataset,"Anguilla","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesStandarded(dataset,"Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesStandarded(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getConfirmedCasesPerTester() {
		assertEquals((Object) Double.valueOf(1.952),(Object) DataAnalysis.getConfirmedCasesPer(dataset,"Afghanistan","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesPer(dataset,"Anguilla","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesPer(dataset,"Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedCasesPer(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getStartDateTester() {
		assertEquals("2/24/2020",DataAnalysis.getStartDate("Afghanistan"));
		assertEquals(null,DataAnalysis.getStartDate("Cheng"));
	}
	
	@Test
	public void laterthanTester() {
		assertEquals(0,DataAnalysis.laterthan("2/24/2020","3/1/2020"));
		assertEquals(1,DataAnalysis.laterthan("3/2/2020","3/1/2020"));
	}
	
	@Test
	public void getConfirmedCasesPerPeriodTester() {
		List<Double> result1 = new ArrayList<>();
		result1.add(Double.valueOf(0.026));
		result1.add(Double.valueOf(0.026));
		result1.add(Double.valueOf(0.051));
		assertEquals(result1,DataAnalysis.getConfirmedCasesPerPeriod(dataset,"Afghanistan",new comp3111.covid.model.Period("3/1/2020","3/3/2020")));
		
		List<Double> result2 = new ArrayList<>();
		result2.add(Double.valueOf(0.0));
		result2.add(Double.valueOf(0.0));
		result2.add(Double.valueOf(0.0));
		assertEquals(result2,DataAnalysis.getConfirmedCasesPerPeriod(dataset,"Albania",new comp3111.covid.model.Period("3/1/2020","3/3/2020")));
		
		List<Double> result3 = new ArrayList<>();
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(10.212));	
		result3.add(Double.valueOf(10.212));	
		assertEquals(result3,DataAnalysis.getConfirmedCasesPerPeriod(dataset,"Antigua and Barbuda",new comp3111.covid.model.Period("3/11/2020","3/14/2020")));
		
		List<Double> result4 = new ArrayList<>();
		result4.add(Double.valueOf(4671.622));
		result4.add(Double.valueOf(4696.631));
		result4.add(Double.valueOf(0.0));
		assertEquals(result4,DataAnalysis.getConfirmedCasesPerPeriod(dataset,"Africa",new comp3111.covid.model.Period("7/19/2021","7/21/2021")));
	}
	
	@Test
	public void getDeathPerPeriodTester() {
		List<Double> result1 = new ArrayList<>();
		result1.add(Double.valueOf(0.0));
		result1.add(Double.valueOf(0.0));
		result1.add(Double.valueOf(0.0));
		assertEquals(result1,DataAnalysis.getDeathPerPeriod(dataset,"Afghanistan",new comp3111.covid.model.Period("3/1/2020","3/3/2020")));
		
		List<Double> result2 = new ArrayList<>();
		result2.add(Double.valueOf(21));
		result2.add(Double.valueOf(25));
		result2.add(Double.valueOf(26));
		assertEquals(result2,DataAnalysis.getDeathPerPeriod(dataset,"Algeria",new comp3111.covid.model.Period("3/25/2020","3/27/2020")));
		
		List<Double> result3 = new ArrayList<>();
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(1));	
		result3.add(Double.valueOf(2));	
		assertEquals(result3,DataAnalysis.getDeathPerPeriod(dataset,"Antigua and Barbuda",new comp3111.covid.model.Period("4/5/2020","4/8/2020")));
		
		List<Double> result5 = new ArrayList<>();
		result5.add(Double.valueOf(0.0));
		result5.add(Double.valueOf(0.0));
		result5.add(Double.valueOf(0.0));	
		result5.add(Double.valueOf(0.0));	
		assertEquals(result5,DataAnalysis.getDeathPerPeriod(dataset,"Antigua and Barbuda",new comp3111.covid.model.Period("3/11/2020","3/14/2020")));
		
		List<Double> result4 = new ArrayList<>();
		result4.add(Double.valueOf(158627));
		result4.add(Double.valueOf(159671));
		result4.add(Double.valueOf(0.0));
		assertEquals(result4,DataAnalysis.getDeathPerPeriod(dataset,"Africa",new comp3111.covid.model.Period("7/19/2021","7/21/2021")));
	}
	
	@Test
	public void getVacRatePeriodTester() {
		List<Double> result1 = new ArrayList<>();
		result1.add(Double.valueOf(0.0));
		result1.add(Double.valueOf(0.0));
		result1.add(Double.valueOf(0.0));
		assertEquals(result1,DataAnalysis.getVacRatePeriod(dataset,"Afghanistan",new comp3111.covid.model.Period("3/1/2020","3/3/2020")));
		
		List<Double> result2 = new ArrayList<>();
		result2.add(Double.valueOf(0.0));
		result2.add(Double.valueOf(0.0));
		result2.add(Double.valueOf(0.0));
		result2.add(Double.valueOf(0.0));
		assertEquals(result2,DataAnalysis.getVacRatePeriod(dataset,"Europe",new comp3111.covid.model.Period("12/25/2020","12/28/2020")));
		
		List<Double> result3 = new ArrayList<>();
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(0.0));
		result3.add(Double.valueOf(0.0));	
		result3.add(Double.valueOf(0.0));	
		assertEquals(result3,DataAnalysis.getVacRatePeriod(dataset,"Antigua and Barbuda",new comp3111.covid.model.Period("3/11/2020","3/14/2020")));
		
		List<Double> result5 = new ArrayList<>();
		result5.add(Double.valueOf(32.12));
		result5.add(Double.valueOf(32.52));
		result5.add(Double.valueOf(32.97));	
		result5.add(Double.valueOf(33.43));	
		assertEquals(result5,DataAnalysis.getVacRatePeriod(dataset,"Europe",new comp3111.covid.model.Period("7/11/2021","7/14/2021")));
		
		List<Double> result4 = new ArrayList<>();
		result4.add(Double.valueOf(25.09));
		result4.add(Double.valueOf(25.16));
		result4.add(Double.valueOf(0.0));
		assertEquals(result4,DataAnalysis.getVacRatePeriod(dataset,"Turkey",new comp3111.covid.model.Period("7/19/2021","7/21/2021")));
	}
	
	@Test
	public void getConfirmedDeathsaTester() {
		assertEquals(1823,DataAnalysis.getConfirmedDeathsa(dataset,"Europe","3/14/2020"));
		assertEquals(0,DataAnalysis.getConfirmedDeathsa(dataset,"Algeria","3/9/2020"));
		assertEquals(0,DataAnalysis.getConfirmedDeathsa(dataset,"Cheng","3/25/2020"));
		assertEquals(0,DataAnalysis.getConfirmedDeathsa(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getConfirmedDeathsPerTester() {
		assertEquals((Object) Double.valueOf(0.068),(Object) DataAnalysis.getConfirmedDeathsPer(dataset,"Algeria","3/14/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedDeathsPer(dataset,"Algeria","3/9/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedDeathsPer(dataset,"Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getConfirmedDeathsPer(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getVaccinationaTester() {
		assertEquals(0,DataAnalysis.getVaccinationa(dataset,"Europe","3/14/2020"));
		assertEquals(393952,DataAnalysis.getVaccinationa(dataset,"Europe","1/10/2021"));
		assertEquals(0,DataAnalysis.getVaccinationa(dataset,"Cheng","3/25/2020"));
		assertEquals(0,DataAnalysis.getVaccinationa(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getVaccinationPerTester() {
		assertEquals((Object) Double.valueOf(0.05),(Object) DataAnalysis.getVaccinationPer(dataset,"Europe","1/10/2021"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationPer(dataset,"Europe","3/9/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationPer(dataset,"Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationPer(dataset,"Anguilla","3/25/2019"));
	}
	
	@Test
	public void getVaccinationRateStandardedTester() {
		assertEquals((Object) Double.valueOf(0.05),(Object) DataAnalysis.getVaccinationRateStandarded(dataset,"Europe","1/10/2021"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationRateStandarded(dataset,"Europe","3/9/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationRateStandarded(dataset,"Cheng","3/25/2020"));
		assertEquals((Object) Double.valueOf(0),(Object) DataAnalysis.getVaccinationRateStandarded(dataset,"Anguilla","3/25/2019"));
	}
}
