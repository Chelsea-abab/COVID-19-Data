package comp3111.covid;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.*;
import java.time.LocalDate;
import edu.duke.*;
import java.util.stream.Collectors;

/**
 * 
 * Data Explorer on COVID-19
 * @author <a href=mailto:namkiu@ust.hk>Namkiu Chan</a>
 * @version	1.1
 * 
 */
public class DataAnalysis {
 
	/**
	 * Helper method to get the {@link org.apache.commons.csv.CSVParser} object for a dataset to be used
	 * @param dataset		The dataset to be used
	 * @return				The {@link org.apache.commons.csv.CSVParser} object
	 */
	public static CSVParser getFileParser(String dataset) {
	     FileResource fr = new FileResource("dataset/" + dataset);
	     return fr.getCSVParser(true);
		}
	
	/**
	 * Helper method to get the list of all countries in the dataset
	 * @param dataset		The dataset to be used
	 * @return				The list of all countries in the dataset
	 */
	public static List<String> getCountryList(String dataset) {
		String iso_code = "";
		List<String> name = new ArrayList<>();
		for (CSVRecord rec : getFileParser(dataset)) {
			if (!(rec.get("iso_code").equals(iso_code))) {
				iso_code = rec.get("iso_code");
				name.add(rec.get("location"));
			}
		}
		return name;
	}
	
	/**
	 * Get the required value of specified feature from the data set
	 * @param dataset		The dataset to be used
	 * @param feature		The name of the feature required
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return				The value of the feature on the date for the country
	 */
	public static Double getFeature(String dataset, String feature, String country, String date) {
		String result = "";
		for (CSVRecord rec : getFileParser(dataset)) {
			if (rec.get("location").equals(country)) {
				if (rec.get("date").equals(date))
					result = rec.get(feature);
			}
		}
		if (result.equals(""))
			return 0.0;
		else
			return Double.parseDouble(result);
	}

	/**
	 * Helper function to get the first date that has data for a country
	 * @param country		The country of interest
	 * @return				The first date that has data for the country
	 */
	public static String getStartDate(String country) {
		
		for (CSVRecord rec : getFileParser("COVID_Dataset_v1.0.csv")) {
			if (rec.get("location").equals(country)) {
				System.out.println(rec.get("date"));
				return rec.get("date");
			}
		}
		return null;
	}
	
	/**
	 * Helper function to compare two dates
	 * @param date1			The first date in the standard form of the dataset
	 * @param date2			The second date in the standard form of the dataset
	 * @return			The difference of days for the two dates if date 1 is later than date 2, 0 otherwise
	 */
	public static int laterthan(String date1, String date2) {
		System.out.println("start date is " + date1);
		System.out.println("end date is " + date2);
		String[] starts = date1.split("/");
		String[] ends = date2.split("/");
		int startDay = Integer.valueOf(starts[1]);
		int startMonth = Integer.valueOf(starts[0]);
		int startYr = Integer.valueOf(starts[2]);
		int endDay = Integer.valueOf(ends[1]);
		int endMonth = Integer.valueOf(ends[0]);
		int endYr = Integer.valueOf(ends[2]);
		LocalDate firstdate = LocalDate.of(startYr,startMonth,startDay);
		LocalDate seconddate = LocalDate.of(endYr,endMonth,endDay);
		System.out.println(firstdate.isAfter(seconddate));
		if(firstdate.isAfter(seconddate)) {
			List<LocalDate> listOfDates = seconddate.datesUntil(firstdate)
	                .collect(Collectors.toList());
			System.out.println("time diff here is " + listOfDates.size());
			return listOfDates.size();
		}
		else
			return 0;
	}

	/**
	 * Get the confirmed case data for task 0
	 * @param dataset		The dataset to be used
	 * @param iso_code		The iso_code specifing the country of interest
	 * @return			The report of the confirmed case data of the country to be shown in task 0
	 */
	public static String getConfirmedCases(String dataset, String iso_code) {
		String oReport = "";	
		long confirmedCases = 0;
		long numRecord = 0;
		long totalNumRecord = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("iso_code").equals(iso_code)) {
				String s = rec.get("new_cases");
				if (!s.equals("")) {
					confirmedCases += Long.parseLong(s);
					numRecord++;
				}
			}		
			totalNumRecord++;
		}
		
		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
		oReport += String.format("[Summary (%s)]\n", iso_code);
		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
		
		System.out.println(oReport);
		return oReport;
	}
	
	
	/**
	 * Get the death data for task 0
	 * @param dataset		The dataset to be used
	 * @param iso_code		The iso_code specifing the country of interest
	 * @return			The report of the death data of the country to be shown in task 0
	 */
	public static String getConfirmedDeaths(String dataset, String iso_code) {
			String oReport = "";	
			long confirmedDeaths = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					String s = rec.get("new_deaths");
					if (!s.equals("")) {
						confirmedDeaths += Long.parseLong(s);
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of Deaths: %,d\n", confirmedDeaths);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			System.out.println(oReport);
			return oReport;
	 }
	 
	
	/**
	 * Get the vaccination data for task 0
	 * @param dataset		The dataset to be used
	 * @param iso_code		The iso_code specifing the country of interest
	 * @return			The report of the vaccination data of the country to be shown in task 0
	 */
	 public static String getRateOfVaccination(String dataset, String iso_code) {
			String oReport = "";	
			long fullyVaccinated = 0;
			long numRecord = 0;
			long totalNumRecord = 0;
			long population = 0;
			float rate = 0.0f;
						
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("iso_code").equals(iso_code)) {
					
					String s1 = rec.get("people_fully_vaccinated");
					String s2 = rec.get("population");		
					if (!s1.equals("") && !s2.equals("")) {
						fullyVaccinated = Long.parseLong(s1);
						population = Long.parseLong(s2);						
						numRecord++;
					}
				}		
				totalNumRecord++;
			}
			
			if (population !=0)
				rate = (float) fullyVaccinated / population * 100;
			
			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
			oReport += String.format("[Summary (%s)]\n", iso_code);
			oReport += String.format("Number of People Fully Vaccinated: %,d\n", fullyVaccinated);
			oReport += String.format("Population: %,d\n", population);
			oReport += String.format("Rate of Vaccination: %.2f%%\n", rate);
			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
			System.out.println(oReport);
			return oReport;
	 }

	 
	 
	 
	 
	 
	 
	 
	 
	 
	/**
	 * Get the total confirmed case data for a country on a specific date
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return			The value of total confirmed cases on the date for the country
	 */
	public static long getConfirmedCasesa(String dataset, String country, String date) {
//		String oReport = "";	
		long confirmedCases = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("location").equals(country)) {
				if(rec.get("date").equals(date)) {
					String s = rec.get("total_cases");
					if(s != "")
						confirmedCases = Long.parseLong(s);
				}
//				String s = rec.get("new_cases");
//				if (!s.equals("")) {
//					confirmedCases += Long.parseLong(s);
//					numRecord++;
//				}
			}		
//			totalNumRecord++;
		}
		
//		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//		oReport += String.format("[Summary (%s)]\n", iso_code);
//		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//		
		return confirmedCases;
	}
	
	
	/**
	 * Get the standardized total confirmed case data for a country on a specific date
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return			The value of standardized total confirmed cases on the date for the country
	 */
	public static double getConfirmedCasesStandarded(String dataset, String country, String date) {
//		String oReport = "";	
		double confirmedCases = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("location").equals(country)) {
				if(rec.get("date").equals(date)) {
					String s = rec.get("total_cases");
					if(s != "")
						confirmedCases = Double.parseDouble(s);
				}
//				String s = rec.get("new_cases");
//				if (!s.equals("")) {
//					confirmedCases += Long.parseLong(s);
//					numRecord++;
//				}
			}		
//			totalNumRecord++;
		}
		
//		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//		oReport += String.format("[Summary (%s)]\n", iso_code);
//		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//		
		return confirmedCases;
	}
	
	
	/**
	 * Get the total confirmed case per 1M data for a country on a specific date
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return			The value of total confirmed cases per 1M on the date for the country
	 */
	public static double getConfirmedCasesPer(String dataset, String country, String date) {
//		String oReport = "";	
		double confirmedCasesP = 0;
		
		for (CSVRecord rec : getFileParser(dataset)) {
			
			if (rec.get("location").equals(country)) {
				if(rec.get("date").equals(date)) {
					String s = rec.get("total_cases_per_million");
					if(s != "")
						confirmedCasesP = Double.parseDouble(s);
				}
//				String s = rec.get("new_cases");
//				if (!s.equals("")) {
//					confirmedCases += Long.parseLong(s);
//					numRecord++;
//				}
			}		
//			totalNumRecord++;
		}
		
//		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//		oReport += String.format("[Summary (%s)]\n", iso_code);
//		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//		
		return confirmedCasesP;
	}
	
	/**
	 * Get the total confirmed case data for a country over a specific period
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param period		The {@link comp3111.covid.model.Period} instance of the period of interest
	 * @return			The list of total confirmed cases for this country of each day within the period
	 */
	public static List<Double> getConfirmedCasesPerPeriod(String dataset, String country, comp3111.covid.model.Period period) {
//		String oReport = "";	
		List<Double> result = new ArrayList<>();
		List<String> dates = period.getDatesBetween();
		
		int diff1 = laterthan(getStartDate(country),period.getstartDate());
		System.out.println("diff1 is " + diff1);
		for(int i = 0; i < diff1; i++) {
			result.add(0.0);
		}
		
		
		
		
		for (CSVRecord rec : getFileParser(dataset)) {		
			if (rec.get("location").equals(country)) {
				if(dates.contains(rec.get("date"))) {
					String s = rec.get("total_cases_per_million");
					if(s != "")
						result.add(Double.parseDouble(s));
					else
						result.add(0.0);
					System.out.println(s + "is added");
				}
//				String s = rec.get("new_cases");
//				if (!s.equals("")) {
//					confirmedCases += Long.parseLong(s);
//					numRecord++;
//				}
			}		
//			totalNumRecord++;
		}
		if(result.size() < dates.size()) {
			for(int i = result.size(); i < dates.size(); i ++) {
				result.add(0.0);
			}
		}
		
//		oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//		oReport += String.format("[Summary (%s)]\n", iso_code);
//		oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//		oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//		
		return result;
	}
	
	

	
	
	
	
	
	
	
	
	/**
	 * Get the total death data for a country on a specific date
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return			The value of total death on the date for the country
	 */
	 public static long getConfirmedDeathsa(String dataset, String country,String date) {
//			String oReport = "";	
			long confirmedDeath = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(rec.get("date").equals(date)) {
						String s = rec.get("total_deaths");
						if(s != "")
							confirmedDeath = Long.parseLong(s);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return confirmedDeath;
	 }
	 
		/**
		 * Get the total death per 1M data for a country on a specific date
		 * @param dataset		The dataset to be used
		 * @param country		The country of interest
		 * @param date			The date of interest in the standard form of the dataset
		 * @return			The value of total death per 1M on the date for the country
		 */
	 public static double getConfirmedDeathsPer(String dataset, String country,String date) {
//			String oReport = "";	
			double confirmedDeathP = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(rec.get("date").equals(date)) {
						String s = rec.get("total_deaths_per_million");
						if(s != "")
							confirmedDeathP = Double.parseDouble(s);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return confirmedDeathP;
	 }
	 
		/**
		 * Get the total death data for a country over a specific period
		 * @param dataset		The dataset to be used
		 * @param country		The country of interest
		 * @param period		The {@link comp3111.covid.model.Period} instance of the period of interest
		 * @return			The list of total death for this country of each day within the period
		 */
	public static List<Double> getDeathPerPeriod(String dataset, String country, comp3111.covid.model.Period period) {
//			String oReport = "";	
			List<Double> result = new ArrayList<>();
			List<String> dates = period.getDatesBetween();
			
			int diff = laterthan(getStartDate(country),period.getstartDate());
			for(int i = 0; i < diff; i ++) {
				result.add(0.0);
			}
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(dates.contains(rec.get("date"))) {
						String s = rec.get("total_deaths");
						if(s != "")
							result.add(Double.parseDouble(s));
						else
							result.add(0.0);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			if(result.size() < dates.size()) {
				for(int i = result.size(); i < dates.size(); i ++) {
					result.add(0.0);
				}
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return result;
		}
		
		
	
	
	
	
	
	
	/**
	 * Get the people fully vaccinated data for a country on a specific date
	 * @param dataset		The dataset to be used
	 * @param country		The country of interest
	 * @param date			The date of interest in the standard form of the dataset
	 * @return			The value of people fully vaccinated on the date for the country
	 */
	 public static long getVaccinationa(String dataset, String country,String date) {
//			String oReport = "";	
			long vaccination = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(rec.get("date").equals(date)) {
						String s = rec.get("people_fully_vaccinated");
						if(s != "")
							vaccination = Long.parseLong(s);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return vaccination;
	 }
	 
		/**
		 * Get the standardized vaccination rate data for a country on a specific date
		 * @param dataset		The dataset to be used
		 * @param country		The country of interest
		 * @param date			The date of interest in the standard form of the dataset
		 * @return			The value of standardized vaccination rate on the date for the country
		 */
		public static double getVaccinationRateStandarded(String dataset, String country, String date) {
//			String oReport = "";	
			double vaccinationRate = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(rec.get("date").equals(date)) {
						String s = rec.get("people_fully_vaccinated_per_hundred");
						if(s != "")
							vaccinationRate = Double.parseDouble(s);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return vaccinationRate;
		}
		
		/**
		 * Get the vaccination rate data for a country on a specific date
		 * @param dataset		The dataset to be used
		 * @param country		The country of interest
		 * @param date			The date of interest in the standard form of the dataset
		 * @return			The value of vaccination rate on the date for the country
		 */
	 public static double getVaccinationPer(String dataset, String country,String date) {
//			String oReport = "";	
			double vaccinationP = 0;
			
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(rec.get("date").equals(date)) {
						String s = rec.get("people_fully_vaccinated_per_hundred");
						if(s != "")
							vaccinationP = Double.parseDouble(s);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return vaccinationP;
	 }

		/**
		 * Get the vaccination rate data for a country over a specific period
		 * @param dataset		The dataset to be used
		 * @param country		The country of interest
		 * @param period		The {@link comp3111.covid.model.Period} instance of the period of interest
		 * @return			The list of vaccination rate for this country of each day within the period
		 */
	 public static List<Double> getVacRatePeriod(String dataset, String country, comp3111.covid.model.Period period) {
//			String oReport = "";	
			List<Double> result = new ArrayList<>();
			List<String> dates = period.getDatesBetween();
			int diff = laterthan(getStartDate(country),period.getstartDate());
			for(int i = 0; i < diff; i ++) {
				result.add(0.0);
			}
			for (CSVRecord rec : getFileParser(dataset)) {
				
				if (rec.get("location").equals(country)) {
					if(dates.contains(rec.get("date"))) {
						String s = rec.get("people_fully_vaccinated_per_hundred");
						if(s != "")
							result.add(Double.parseDouble(s));
						else
							result.add(0.0);
					}
//					String s = rec.get("new_cases");
//					if (!s.equals("")) {
//						confirmedCases += Long.parseLong(s);
//						numRecord++;
//					}
				}		
//				totalNumRecord++;
			}
			if(result.size() < dates.size()) {
				for(int i = result.size(); i < dates.size(); i ++) {
					result.add(0.0);
				}
			}
			
//			oReport = String.format("Dataset (%s): %,d Records\n\n", dataset, totalNumRecord);
//			oReport += String.format("[Summary (%s)]\n", iso_code);
//			oReport += String.format("Number of Confirmed Cases: %,d\n", confirmedCases);
//			oReport += String.format("Number of Days Reported: %,d\n", numRecord);
//			
			return result;
		}
}