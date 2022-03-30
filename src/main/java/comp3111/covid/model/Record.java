package comp3111.covid.model;

/**
 * Abstract class for all types of record on a date
 */
public abstract class Record {
	private String country;
	private String date;
	
	/**
	 * Constructor for the Record class to be called in its subclasses
	 * @param country 		The country of this record
	 * @param date 			The date of this record in the standard form of the dataset
	 */
	protected Record(String country, String date) {
        this.country = country;
        this.date = date;
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
     * @return		The date of this record in the standard form of the dataset
     */
    public final String getDate() {
    	return this.date;
    }
    
}
