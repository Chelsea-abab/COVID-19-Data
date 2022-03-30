package comp3111.covid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The implementation of Request for a request over a specific period
 */
public class RequestOverPeriod extends Request {
	private List<String>listCountry = new ArrayList<>();
	private List<PeriodRecord> listRecord= new ArrayList<PeriodRecord>();
	private Period period;
	
	/**
	 * Constructor to create a RequestOnDate object containing a {@link PeriodRecord} instance
	 * @param listCountry		The list of countries of interest
	 * @param period			The {@link Period} instance of period of interest
	 * @param type				The type of record requested:
	 * 							1 for {@link DeathRecord}
	 * 							2 for {@link ConfirmedCaseRecord}
	 * 							3 for {@link VaccinationRecord}
	 */
	public RequestOverPeriod(ArrayList<String>listCountry, Period period, int type) {
		this.listCountry = listCountry;
		this.period = period;
		if(type == 1) {//Death
			for(var c: listCountry) {
				listRecord.add(new DeathPeriodRecord(c,period));
			}
		}else if(type == 2) {//ConfirmedCase
			for(var c: listCountry) {
				listRecord.add(new ConfirmedCasePeriodRecord(c,period));
			}
		}else if(type == 3) {//vaccination
			for(var c: listCountry) {
				listRecord.add(new VaccinationPeriodRecord(c,period));
			}
		}
		
	}
	
	/**
	 * Getter method to get the record for a specific country on a specific date
	 * @param country		The country of the required record
	 * @param period		The period of the required record
	 * @return				The instance of {@link PeriodRecord} of the required record
	 */
	public PeriodRecord getPeriodRecord(String country, Period period) {
		int idxc = listCountry.indexOf(country);
		return listRecord.get(idxc);
	}
	
}
