package comp3111.covid.model;

import comp3111.covid.DataAnalysis;

/**
 * The implementation of abstract class {@link Record} with the data of deaths
 */
public class DeathRecord extends Record{
	private long TotalNumDeath;
	private double NumDeathPerMillion;
	
	/**
	 * Constructor to create a DeathRecord object and fetch the data from the dataset
	 * @param country 		The country of this record
	 * @param date			The date of this record in the standard form of the dataset
	 */
	public DeathRecord(String country, String date) {
		super(country,date);
		this.TotalNumDeath = DataAnalysis.getConfirmedDeathsa("COVID_Dataset_v1.0.csv" , country, date);
		this.NumDeathPerMillion = DataAnalysis.getConfirmedDeathsPer("COVID_Dataset_v1.0.csv" , country, date);
	}
	
	/**
	 * Getter method to get the total number of deaths of this record
	 * @return		The total number of deaths of this record
	 */
	public final long getTotalNumDeath() {
        return this.TotalNumDeath;
    }
    
	/**
	 * Getter method to get the total number of deaths per 1M of this record
	 * @return		The total number of deaths per 1M of this record
	 */
    public final double getNumDeathPerMillion  () {
    	return this.NumDeathPerMillion;
    }
    
}
