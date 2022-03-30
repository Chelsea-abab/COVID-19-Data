package comp3111.covid.model;
import comp3111.covid.DataAnalysis;

/**
 * The implementation of abstract class {@link Record} with the data of confirmed cases
 */
public class ConfirmedCaseRecord extends Record{
	private long TotalNumConfirmedCase;
	private double NumConfirmedCasePerMillion;
	
	/**
	 * Constructor to create a ConfirmedCaseRecord object and fetch the data from the dataset
	 * @param country 		The country of this record
	 * @param date			The {@link Period} object of period of this record in the standard form of the dataset
	 */
	public ConfirmedCaseRecord(String country, String date) {
		super(country,date);
		this.TotalNumConfirmedCase = DataAnalysis.getConfirmedCasesa("COVID_Dataset_v1.0.csv" , country, date);
		this.NumConfirmedCasePerMillion = DataAnalysis.getConfirmedCasesPer("COVID_Dataset_v1.0.csv" , country, date);
	}
	
	/**
	 * Getter method to get the total number of confirmed cases of this record
	 * @return		The total number of confirmed cases of this record
	 */
	public final long getTotalNumConfirmedCase() {
        return this.TotalNumConfirmedCase;
    }

	/**
	 * Getter method to get the total number of confirmed cases per 1M of this record
	 * @return		The total number of confirmed cases of per 1M this record
	 */
    public final double getNumConfirmedCasePerMillion() {
    	return this.NumConfirmedCasePerMillion;
    }
}



