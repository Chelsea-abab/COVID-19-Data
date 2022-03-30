package comp3111.covid.model;
import comp3111.covid.DataAnalysis;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of abstract class {@link PeriodRecord} with the data of confirmed cases
 */
public class ConfirmedCasePeriodRecord extends PeriodRecord{
	private List<Double> NumConfirmedCasePerMillions =new ArrayList<>();
	
	/**
	 * Constructor to create a ConfirmedCaseRecord object and fetch the data from the dataset
	 * @param country 			The country of this record
	 * @param period			The date of this record in the standard form of the dataset
	 */
	public ConfirmedCasePeriodRecord(String country, Period period) {
		super(country,period);
		this.NumConfirmedCasePerMillions = DataAnalysis.getConfirmedCasesPerPeriod("COVID_Dataset_v1.0.csv" , country, period);
	}
	
	/**
	 * Getter method to get the total number of confirmed cases of this record
	 * @return		The list of total number of confirmed cases of this record
	 */
	
    public final List<Double> getNumConfirmedCasePerMillion() {
    	return this.NumConfirmedCasePerMillions;
    }
}
