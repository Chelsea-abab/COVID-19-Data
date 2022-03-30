package comp3111.covid.model;
import comp3111.covid.DataAnalysis;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of abstract class {@link PeriodRecord} with the data of confirmed cases
 */
public class VaccinationPeriodRecord extends PeriodRecord{
	private List<Double> VaccinationRates =new ArrayList<>();
	
	/**
	 * Constructor to create a ConfirmedCaseRecord object and fetch the data from the dataset
	 * @param country 		The country of this record
	 * @param period		The {@link Period} object of period of this record in the standard form of the dataset
	 */
	public VaccinationPeriodRecord(String country, Period period) {
		super(country,period);
		this.VaccinationRates = DataAnalysis.getVacRatePeriod("COVID_Dataset_v1.0.csv" , country, period);
	}
	
	/**
	 * Getter method to get the total number of confirmed cases of this record
	 * @return		The list of total number of confirmed cases of this record
	 */
	
    public final List<Double> getVaccinationRates() {
    	return this.VaccinationRates;
    }
}