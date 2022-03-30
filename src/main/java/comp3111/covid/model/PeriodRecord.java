package comp3111.covid.model;

/**
 * Abstract class for all types of record over a period
 */
public abstract class PeriodRecord {
	private String country;
	private Period period;
	
	/**
	 * Constructor for the PeriodRecord class to be called in its subclasses
	 * @param country 		The country of this record
	 * @param period 		The {@link Period} object of period of this record
	 */
	protected PeriodRecord(String country, Period period) {
        this.country = country;
        this.period = period;
    }

	/**
	 * Getter method to get the country of this record
	 * @return		The country of this record
	 */
    public final String getCountry() {
        return this.country;
    }
    
    /**
     * Getter method to get the date of this record
     * @return		The {@link Period} object of period of this record in the standard form of the dataset
     */
    public final Period getPeriod() {
    	return this.period;
    }
    


}
