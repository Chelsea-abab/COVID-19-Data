package comp3111.covid.model;
import comp3111.covid.DataAnalysis;

/**
 * The implementation of abstract class Record with the data of vaccination
 * @author ZHANG, Yuanhao
 */
public class VaccinationRecord extends Record{
	private long NumVaccination;
	private double VaccinationRate;
	private String RateInPercentage;
	private String NumString;
	
	/**
	 * Constructor to create a VaccincationRecord object and fetch the data from the dataset
	 * @param country 		The country of this record
	 * @param date			The date of this record in the standard form of the dataset
	 */
	public VaccinationRecord(String country, String date) {
		super(country,date);
		this.NumVaccination = DataAnalysis.getVaccinationa("COVID_Dataset_v1.0.csv" , country, date);
		this.VaccinationRate = DataAnalysis.getVaccinationPer("COVID_Dataset_v1.0.csv" , country, date);
		if(this.VaccinationRate < 0.0000001)
			this.RateInPercentage = "No data";
		else
			this.RateInPercentage = String.valueOf(this.VaccinationRate) + "%";
		if(this.NumVaccination == 0) {
			this.NumString = "No data";
		}else
			this.NumString = String.valueOf(this.NumVaccination);
	}
	
	/**
	 * Getter method to get the total number people fully vaccinated of this record
	 * @return		The total number of vaccinations of this record
	 */
	public final long getNumVaccination() {
        return this.NumVaccination;
    }
    
	/**
	 * Getter method to get the vaccinations rate of this record
	 * @return		The vaccinations rate of this record
	 */
    public final double getVaccinationRate() {
    	return this.VaccinationRate;
    }
    /**
	 * Getter method to get the string version of vaccinations rate of this record
	 * @return		The vaccinations rate of this record
	 */
    public final String getRateInPercentage() {
    	return this.RateInPercentage;
    }
    /**
	 * Getter method to get the string version of the total number people fully vaccinated of this record
	 * @return		The vaccinations rate of this record
	 */
    public final String getNumString() {
    	return this.NumString;
    }
}
