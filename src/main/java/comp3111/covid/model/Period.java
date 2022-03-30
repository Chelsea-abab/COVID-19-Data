package comp3111.covid.model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for Period to be used in chart task
 */
public class Period {
	private String startDate;
	private String endDate;
	
	/**
	 * Constructor of the Period
	 * @param startDate 	The starting date of this period in the standard form of the dataset
	 * @param endDate		The ending date of this period in the standard form of the dataset
	 */
	public Period(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * The getter method to get the starting date of this period
	 * @return			The starting date of this period in the standard form of the dataset
	 */
	public String getstartDate() {
		return startDate;
	}
	
	/**
	 * The getter method to get the ending date of this period
	 * @return			The ending date of this period in the standard form of the dataset
	 */
	public String getendDate() {
		return endDate;
	}
	
	/**
	 * The getter method to get all the dates within this period (including the starting and 
	 * ending dates)
	 * @return			The list of date within this period (including the starting and 
	 * 					ending dates) in the standard form of the dataset
	 */
	public List<String> getDatesBetween(){
		List<String> result = new ArrayList<>();
		String[] starts = this.startDate.split("/");
		String[] ends = this.endDate.split("/");
		int startDay = Integer.valueOf(starts[1]);
		int startMonth = Integer.valueOf(starts[0]);
		int startYr = Integer.valueOf(starts[2]);
		int endDay = Integer.valueOf(ends[1]);
		int endMonth = Integer.valueOf(ends[0]);
		int endYr = Integer.valueOf(ends[2]);
		LocalDate startdate = LocalDate.of(startYr,startMonth,startDay);
		LocalDate enddate = LocalDate.of(endYr,endMonth,endDay).plusDays(1);
		List<LocalDate> listOfDates = startdate.datesUntil(enddate)
                .collect(Collectors.toList());
		for(int i = 0; i < listOfDates.size(); i++) {
			String temp = "";
			temp += String.valueOf(listOfDates.get(i).getMonthValue());
			temp += "/";
			temp += String.valueOf(listOfDates.get(i).getDayOfMonth());
			temp += "/";
			temp += String.valueOf(listOfDates.get(i).getYear());
			result.add(temp);
		}
		return result;
	}
}
