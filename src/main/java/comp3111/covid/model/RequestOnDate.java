package comp3111.covid.model;
import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of Request for a request on a specific date
 */
public class RequestOnDate extends Request{
	private List<Record> listRecord = new ArrayList<>();
	private String date;
	private List<String>listCountry = new ArrayList<>();
	
	/**
	 * Constructor to create a RequestOnDate object and create the corresponding 
	 * instances of subtype of {@link Record} for all countries on the date
	 * @param listCountry		The list of countries of interest
	 * @param date				The date of interest
	 * @param type				The type of record requested:
	 * 							1 for {@link DeathRecord}
	 * 							2 for {@link ConfirmedCaseRecord}
	 * 							3 for {@link VaccinationRecord}
	 */
	public RequestOnDate(ArrayList<String>listCountry, String date, int type) {
		this.listCountry = listCountry;
		this.date = date;
		if(type == 1) {//Death
			for(var c: listCountry) {
				Record temp = new DeathRecord(c,date);
				listRecord.add(temp);
			}
		}else if(type == 2) {//ConfirmedCase
			for(var c: listCountry) {
				Record temp = new ConfirmedCaseRecord(c,date);
				listRecord.add(temp);
			}
		}else if(type == 3) {//vaccination
			for(var c: listCountry) {
				Record temp = new VaccinationRecord(c,date);
				listRecord.add(temp);
			}
		}
		
	}
	
	/**
	 * Getter method to get the record for a specific country
	 * @param country		The country of the required record
	 * @return				The instance of subtype of {@link Record} of the required record
	 */
	public Record getRecord(String country) {
		int idx = listCountry.indexOf(country);
		return listRecord.get(idx);
	}
	
}
